package com.poc.utilities;

/**
 * Created by coviam on 30/07/17.
 */

//import com.poc.model.Order;
//import com.poc.model.Item;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.MimeMessageHelper;

//import com.poc.model.Order;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import com.coviam.model.*;


public class Mail {

    public static void sendMail(Order order) {

        final String username = "peterlnm6@gmail.com";
        final String password = "govinda511";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");



        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("peterlnm6@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(order.getUserEmail()));
            message.setSubject("Invoice : Order Details");
            message.setText("Dear ,"
                    + "\n\n Thank you for shopping");

            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText("Dear " + order.getUserEmail() +","
                    + "\n\n Thank you for shopping with us.");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();

            //Order order = new Order();
//            ByteArrayDataSource source = new ByteArrayDataSource(GeneratePdfReport.citiesReport(order),
//                    MediaType.TEXT_PLAIN.toString());
            GeneratePdfReport.invoiceReport(order);
            String filename = "Invoice.pdf";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Done");

        }
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendMailToMerchant(String email,List<Item> itemList) {

        if(email!=null) {

            final String username = "peterlnm6@gmail.com";
            final String password = "govinda511";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");


            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("peterlnm6@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(email));
                message.setSubject("Invoice : Order Details");
                message.setText("Dear ,"
                        + "\n\n Thank you for shopping");

                BodyPart messageBodyPart = new MimeBodyPart();

                // Now set the actual message
                messageBodyPart.setText("Dear " + email + ","
                        + "\n\n Thank you for shopping with us.");

                // Create a multipar message
                Multipart multipart = new MimeMultipart();

                // Set text message part
                multipart.addBodyPart(messageBodyPart);

                // Part two is attachment
                messageBodyPart = new MimeBodyPart();

                Double profit = 0.0;
                for (Item item : itemList) {
                    profit = profit + item.getProductQuantity() * item.getProductPrice();
                }

                GeneratePdfReport.merchantReport(itemList.size(), profit);
                String filename = "Merchant.pdf";
                DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(filename);
                multipart.addBodyPart(messageBodyPart);

                // Send the complete message parts
                message.setContent(multipart);

                // Send message
                Transport.send(message);

                System.out.println("Done");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}