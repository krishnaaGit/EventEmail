package mum.ea.listener;

import java.util.Locale;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import mum.ea.domain.Event;
import mum.ea.emailservice.EmailService;

@Component
public class SendEventEmailListener implements MessageListener {
	private final EmailService emailService;

	@Autowired
	public SendEventEmailListener(@Qualifier("emailService") EmailService emailService) {
		this.emailService = emailService;
	}

	public void onMessage(Message message) {

		try {
			System.out.println(message);
			ObjectMessage objectMessage = (ObjectMessage) message;
			Event event = (Event) objectMessage.getObject();
			System.out.println("Event received: " + event.getName());

			emailService.sendEventMail("Krishnaa", "kkarmacharya@mum.edu", event, null, new Locale("en"));
		} catch (MessagingException | JMSException e) {
			System.out.println(e.getMessage());
			// e.printStackTrace();
		}
	}

}
