package sample;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class Client {

    Socket socket;

    int sentMessagesIndex = 0;

    ClientThread clientThread;



    final String ip = "127.0.0.1";
    final int port = 9000;

    public void startClient(Label status, ArrayList<Message> receivedMessageList,VBox receivedMessagesLayout, VBox sentMessagesLayout, int receivedMessagesIndex) {
        try {

            socket = new Socket(ip, port);
            System.out.println("Client started successfully!");
            status.setText("ONLINE!!");

            clientThread = new ClientThread(socket, receivedMessageList,receivedMessagesLayout, sentMessagesLayout,receivedMessagesIndex);
            clientThread.start();

        } catch (Exception e) { }
    }

    public boolean getSentRecently(){
        return clientThread.sentRecently;
    }

    public void setSentRecently(boolean sentRecently){
        clientThread.sentRecently = sentRecently;
    }

    public void sendMessage(String message) {
        try {

            DataOutputStream outputStream;
            outputStream = new DataOutputStream(socket.getOutputStream());

            outputStream.writeUTF(message);

        } catch (IOException e) { }
    }

    public boolean isFirstTimeSending(){
        return clientThread.firstTimeSending;
    }

    public void setFirstTimeReceiving(){
        clientThread.firstTimeReceiving = false;
        clientThread.firstTimeSending = false;
    }

}
