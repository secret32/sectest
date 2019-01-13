package org.sec.utils;

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Administrator on 2014/6/6.
 */
public class MailUtil {

    public static final String USERNAME = "test1343@163.com";
    public static final String PASSWORD = "163163";
    public static final String SMTP = "smtp.163.com";
    public static final String POP3 = "pop3.163.com";

    public static void sendMail(String subject, String content, String... recipients) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", SMTP);
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            Session session = Session.getDefaultInstance(props, new MailAuth(USERNAME, PASSWORD));
            session.setDebug(false);
            // create a message
            Message msg = new MimeMessage(session);
            // set the from and to address
            msg.setFrom(new InternetAddress(USERNAME));
            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                addressTo[i] = new InternetAddress(recipients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);
            // Setting the Subject and Content Type
            msg.setSubject(subject);
            msg.setContent(content, "text/html;charset=utf-8");
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(MailUtil.class).error("发送邮件失败", e);
        }
    }

    private static class MailAuth extends Authenticator {
        private String username;
        private String password;

        private MailAuth(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    }

    public static void main(String[] args) {
        sendMail("123123", "123123", "secret32@sina.com.cn");
    }

}
