package sample;

import com.sun.javafx.css.Size;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

    public String messageBody;
    Date date;
    String dateString;

    VBox mainContainer;
    HBox topContainer;
    Label senderName, messageDate;
    TextArea messageTextArea;
    Label messageLabel;

    int lines = 0;

    public Message(String messageBody, Date date, String senderName){

        this.messageBody=messageBody;
        this.date = date;

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        dateString=dateFormat.format(date);

        mainContainer = new VBox(5);
        mainContainer.setPadding(new Insets(10));
        mainContainer.setPrefWidth(300);


        topContainer = new HBox(10);
        topContainer.setPrefWidth(150);
        topContainer.setPrefHeight(20);

        this.senderName = new Label(senderName);
        messageDate = new Label(dateString);

        messageTextArea = new TextArea();

//        String testString = messageBody;
//
//        if(testString.length()>32){
//            while (testString.length()>32){
//                String subString = testString.substring(32);
//                messageTextArea.setText(messageTextArea.getText()+subString+"\n");
//                int testStringLength = testString.length();
//                testString = testString.substring(32,testStringLength);
//            }
//            messageTextArea.setText(messageTextArea.getText()+testString+"\n");
//        }
//        else
        //messageTextArea.setText(messageBody);

        messageLabel = new Label();

        messageTextArea = new TextArea();

        messageLabel.setText(getProcessedString(messageBody));

        messageTextArea.setText(getProcessedString(messageBody));

        double spacing = 18;

        messageTextArea.setEditable(false);
        messageTextArea.setMinHeight(lines*spacing);

        topContainer.getChildren().addAll(this.senderName,messageDate);

       // mainContainer.getChildren().addAll(topContainer,messageTextArea);
        mainContainer.setMinHeight(50+lines*spacing);
        mainContainer.setMaxHeight(50+lines*spacing);
        mainContainer.getChildren().addAll(topContainer,messageTextArea);

    }

    public String getProcessedString(String toProcess){

        lines=0;

        String processedString = "";
        int length = toProcess.length();
        int limit = 25;

        while (length>limit) {

            int cutIndex=limit;

            while (cutIndex>=1 && !Character.isWhitespace(toProcess.charAt(cutIndex))){
                //System.out.println(toProcess.substring(cutIndex-1,cutIndex));
                cutIndex--;
            }

            if(cutIndex==0)
                cutIndex=limit;
            else
                cutIndex++;

            //System.out.println(toProcess.substring(0, cutIndex));

            processedString+=toProcess.substring(0,cutIndex)+"\n";

            toProcess = toProcess.substring(cutIndex, length);
            length = toProcess.length();

            lines++;

        }
        processedString+=toProcess;
        lines++;
        return processedString;

    }

}
