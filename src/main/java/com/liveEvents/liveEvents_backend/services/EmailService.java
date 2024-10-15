package com.liveEvents.liveEvents_backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    public void sendConfirmationEmail(String email, String userFirstName, String userLastName, String userMessage){

        String subject = "Nationsound | Merci de nous avoir contactés !";
        String body = "Bonjour " + userFirstName + " " + userLastName + ",\n\n" +
                "Nous avons bien reçu votre message :\n" +
                userMessage + "\n\n" +
                "Nous vous répondrons dans les brefs délais.\n\n" +
                "Cordialement,\n" +
                "L'équipe NationSound";

        SimpleMailMessage message= new SimpleMailMessage();
                message.setTo(email);
                message.setSubject(subject);
                message.setText(body);
        try {
            mailSender.send(message);
            System.out.println("Email envoyé avec succès.");
        } catch (MailException e) {
            System.err.println("Erreur lors de l'envoi de l'email : " + e.getMessage());
        }

    }

    public void sendResponseEmail(String email,String userFirstName, String userLastName, String response){
        String subject = "Nationsound | Réponse à votre question !";
        String body = "Bonjour " + userFirstName + " " + userLastName + ",\n\n" +
                "Voici la réponse à votre question :\n" +
                response + "\n\n" +
                "Cordialement,\n" +
                "L'équipe NationSound";

        SimpleMailMessage message= new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);
        try {
            mailSender.send(message);
            System.out.println("Email envoyé avec succès.");
        } catch (MailException e) {
            System.err.println("Erreur lors de l'envoi de l'email : " + e.getMessage());
        }

    }

}
