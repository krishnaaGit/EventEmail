package mum.ea.listener;

import mum.ea.domain.Event;
import mum.ea.emailservice.EmailService;

import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.mail.MessagingException;
import java.util.Locale;

public class SendCancelEventEmailListener implements MessageListener {
    private final EmailService emailService;

    @Autowired
    public SendCancelEventEmailListener(EmailService emailService) {
        this.emailService = emailService;
    }

    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            Event event = (Event) objectMessage.getObject();
            System.out.println("Event Received: ");
            System.out.println("         Event id: " + event.getId());
            String status = "added";

            if (event.isStatus()) {
                status = "Added";
            } else{
                status = "Cancelled";
            }

            System.out.println("         Event Status: " + status);

            emailService.sendEventMail(
                    "Valuable User","kkarmacharya@gmail.com",
                    event, null, new Locale("en")
            );
        } catch (JMSException | MessagingException e) {
            e.printStackTrace();
        }
    }
}
