package sample;


import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class ClientThread extends Thread {

    Socket socket;

    ArrayList<Message> receivedMessagesList;
    VBox receivedMessagesLayout;
    VBox sentMessagesLayout;

    int sentMessagesIndex;
    int receivedMessagesIndex;

    boolean sentRecently=true,firstTimeReceiving = true,firstTimeSending=true;
   // TextArea textArea;

    DataInputStream inputStream;

    ClientThread(Socket socket, ArrayList<Message> receivedMessagesList, VBox receivedMessagesLayout,VBox sentMessagesLayout,int receivedMessagesIndex) {
        this.socket = socket;
        this.receivedMessagesList = receivedMessagesList;
        this.receivedMessagesLayout = receivedMessagesLayout;
        this.sentMessagesLayout = sentMessagesLayout;

        this.receivedMessagesIndex = receivedMessagesIndex;
    }

    public void run() {
        try {
            String receivedMessage;
            inputStream = new DataInputStream(socket.getInputStream());
            while (true) {
                receivedMessage = inputStream.readUTF();

                insertReceivedMessage(receivedMessage);

                //textArea.setText(textArea.getText()+"Client incoming message: "+receivedMessage+"\n");

                System.out.println("Received message: " + receivedMessage);
                System.out.println("Incoming server message: " + receivedMessage);
            }
        } catch (IOException e) { }
    }

    public void insertReceivedMessage(String receivedMessage){

        receivedMessagesList.add(new Message(receivedMessage,new Date(),"Server"));

        Message testMessage = receivedMessagesList.get(receivedMessagesIndex);
        Platform.runLater(()->{
            receivedMessagesLayout.getChildren().add(testMessage.mainContainer);

            VBox fillSentList = new VBox();

            //fillSentList.setMinHeight(testMessage.mainContainer.getMinHeight());
            //fillSentList.setMaxHeight(testMessage.mainContainer.getMaxHeight());

            //fillSentList.setMaxWidth(testMessage.mainContainer.getMaxWidth());
            //fillSentList.setMinWidth(testMessage.mainContainer.getMinWidth());
            if(firstTimeReceiving){
                firstTimeSending=false;
                firstTimeReceiving=false;
                fillSentList.setMinHeight(testMessage.mainContainer.getMinHeight() / 2);
                fillSentList.setMaxHeight(testMessage.mainContainer.getMaxHeight() / 2);
                System.out.println("Relleno con MENOS espacio  al recibir: " + fillSentList.getMinHeight());
            } else {
                if (sentRecently) {
                    fillSentList.setMinHeight(0);
                    fillSentList.setMaxHeight(0);
                    System.out.println("Relleno con MENOS espacio  al recibir: " + fillSentList.getMinHeight());
                } else {
                    fillSentList.setMinHeight(testMessage.mainContainer.getMinHeight());
                    fillSentList.setMaxHeight(testMessage.mainContainer.getMaxHeight());
                    System.out.println("Relleno con MAS espacio al recibir" + fillSentList.getMinHeight());
                }
            }
            sentRecently = false;
            fillSentList.setMaxWidth(testMessage.mainContainer.getMaxWidth());
            fillSentList.setMinWidth(testMessage.mainContainer.getMinWidth());

            sentMessagesLayout.getChildren().add(fillSentList);
        });

        receivedMessagesIndex++;
        //sentMessagesIndex++;

        System.out.println("Message received at: "+testMessage.dateString+". "+ testMessage.messageBody );

    }

    public boolean isFirstTimeSending(){
        return firstTimeSending;
    }

    public void setFirstTimeReceiving(boolean firstTimeReceiving){
        this.firstTimeReceiving=firstTimeReceiving;
    }

}

