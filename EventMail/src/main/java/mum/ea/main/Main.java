package mum.ea.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.MessagingException;

public class Main {
    private final static String[] configFilesCourierApp = {
            "/context/common.xml",
            "/context/applicationContext.xml",
            "/context/jms-send-email-app-context.xml"
    };

    public static void main(String[] args) throws MessagingException {
        System.out.println("    Loading send email application...");
        ApplicationContext context = new ClassPathXmlApplicationContext(configFilesCourierApp, Main.class);
    }
}
