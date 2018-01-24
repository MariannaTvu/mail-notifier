package com.mariana.scheduler.service;

import com.mariana.scheduler.mail.Mail;
import com.mariana.scheduler.mail.MailSender;
import com.mariana.scheduler.mail.SimpleMailSender;
import com.mariana.scheduler.mail.template.SimpleTemplateEngine;
import com.mariana.scheduler.mail.template.TemplateEngine;
import org.quartz.*;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

import static javax.swing.UIManager.get;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

/**
 * Created by nicot on 1/6/2018.
 */
@Component
public class NoteEmailNotificationJob implements Job {
    private final String STANDART_TEMPLATE = "standart_msg.html";
    private LocalDate TRIGGER_DATE = null;
    private MailSender mailSender = SimpleMailSender.createMailSender();
    private TemplateEngine templateEngine = SimpleTemplateEngine.createTemplateEngine();

    public NoteEmailNotificationJob() throws SchedulerException {
    }
    public void execute(JobExecutionContext context) throws JobExecutionException {
       Mail mail = null;
        try {
            mail = (Mail) context.getScheduler().getContext().get("mail");
            TRIGGER_DATE = (LocalDate) context.getScheduler().getContext().get("date");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
sendMail(mail.getUsername(), mail.getTitle(), mail.getText(), mail.getEmail(), mail.getSubject());
    }

    public void sendMail(String userName, String title, String text, String email, String subject) {
        String body = templateEngine.build(STANDART_TEMPLATE, new HashMap<String,String>() {{
            put("name", userName);
            put("title", title);
            put("text", text);
        }});
        boolean success = mailSender.sendMail(email, subject, body);
        System.out.println("Mail success: " + success);
    }

    public JobDetail getJob() {
        return newJob(NoteEmailNotificationJob.class)
                .withIdentity("job1", "group1")
                .build();
    }

    public SimpleTrigger getTrigger() {
        return (SimpleTrigger) newTrigger()
                .withIdentity("trigger1", "group1")
                .startAt(Date.from(TRIGGER_DATE.atStartOfDay(ZoneId.systemDefault()).toInstant())) // some Date
                .forJob("job1", "group1") // identify job with name, group strings
                .build();
    }
}
