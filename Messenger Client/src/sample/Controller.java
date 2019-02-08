package sample;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Date;

public class Controller {

    ArrayList<Message> sentMessages;
    ArrayList<Message> receivedMessages;

    public Client client;

    public Label status;
    public TextArea textArea;
    public Button sendMessageButton;
    public Button connectButton;
    public TextField textField;

    public VBox sentMessagesLayout;
    public VBox receivedMessagesLayout;

    int sentMessagesIndex = 0, receivedMessagesIndex = 0;

    boolean sentRecently=false;

    public Controller(){

    }

    public void startClient(){

        sentMessages = new ArrayList<>();
        receivedMessages = new ArrayList<>();

        client = new Client();
        client.startClient(status,receivedMessages,receivedMessagesLayout,sentMessagesLayout,receivedMessagesIndex);

    }

    public void sendMessage(){

        String senderName = "Matias";
        String message = textField.getText();
        client.sendMessage(message);

        sentMessages.add(new Message(message, new Date(),senderName));

        Message testMessage = sentMessages.get(sentMessagesIndex);
        sentMessagesLayout.getChildren().add(testMessage.mainContainer);

        VBox fillReceivedList = new VBox();

        sentRecently=client.getSentRecently();

        if(client.isFirstTimeSending()){
            client.setFirstTimeReceiving();
            fillReceivedList.setMinHeight(testMessage.mainContainer.getMinHeight() / 2);
            fillReceivedList.setMaxHeight(testMessage.mainContainer.getMaxHeight() / 2);
            System.out.println("Relleno con MENOS espacio al enviar: " + fillReceivedList.getMinHeight());
        }else {
            if (!sentRecently) {
                fillReceivedList.setMinHeight(0);
                fillReceivedList.setMaxHeight(0);
                System.out.println("Relleno con MENOS espacio al enviar: " + fillReceivedList.getMinHeight());
            } else {
                fillReceivedList.setMinHeight(testMessage.mainContainer.getMinHeight());
                fillReceivedList.setMaxHeight(testMessage.mainContainer.getMaxHeight());
                System.out.println("Relleno con MAS espacio al enviar" + fillReceivedList.getMinHeight());
            }
        }
        fillReceivedList.setMaxWidth(testMessage.mainContainer.getMaxWidth());
        fillReceivedList.setMinWidth(testMessage.mainContainer.getMinWidth());

        receivedMessagesLayout.getChildren().add(fillReceivedList);

        client.setSentRecently(true);
        //client.setSentRecently(sentRecently);
        sentMessagesIndex++;
        //receivedMessagesIndex++;

        System.out.println("Message sent at: "+testMessage.dateString+". "+ testMessage.messageBody );

        textField.setText("");

    }

}
