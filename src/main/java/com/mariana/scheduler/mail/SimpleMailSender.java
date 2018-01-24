package com.mariana.scheduler.mail;

import com.mariana.scheduler.mail.template.SimpleTemplateEngine;
import com.mariana.scheduler.mail.template.TemplateEngine;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class SimpleMailSender implements MailSender {
    private final MailProps mailProps;
    private final Session session = Session.getDefaultInstance(prepareProps(), null);
    public SimpleMailSender(MailProps mailProps) {
        this.mailProps = mailProps;
    }

    @Override
    public boolean sendMail(String email, String subject, String body) {
        try {
            MimeMessage message = buildMessage(email, subject, body);
            sendMessage(message);
            return true;
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void sendMessage(MimeMessage message) throws MessagingException {
        Transport transport = session.getTransport("smtp");
        try {
            transport.connect("smtp.gmail.com", mailProps.getSenderEmail(), mailProps.getPassword());
            transport.sendMessage(message, message.getAllRecipients());
        } finally {
            transport.close();
        }
    }

    private MimeMessage buildMessage(String email, String subject, String body) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = new MimeMessage(session);
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setSubject(subject);
        message.setFrom(mailProps.getSenderAddress());
        message.setContent(body, "text/html");
        return message;
    }

    private Properties prepareProps() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        return props;
    }

    public static MailSender createMailSender() {
        MailProps mailProps = new MailProps()
                .setSenderEmail("scedule.reminder.test@gmail.com")
                .setPassword("QWERTY12345");
        return new SimpleMailSender(mailProps);
    }
}
