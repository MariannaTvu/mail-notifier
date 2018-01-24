package com.mariana.scheduler.mail;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by nicot on 1/7/2018.
 */
@Getter
@Setter
@Data
public class Mail {
    private String email;
    private String subject;
    private String username;
    private String title;
    private String text;

    public Mail(String email, String subject, String username, String title, String text) {
        this.email = email;
        this.subject = subject;
        this.username = username;
        this.title = title;
        this.text = text;
    }
}
