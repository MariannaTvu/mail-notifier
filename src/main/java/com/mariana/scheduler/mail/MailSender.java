package com.mariana.scheduler.mail;

public interface MailSender {

    boolean sendMail(String email, String subject, String body);
}
