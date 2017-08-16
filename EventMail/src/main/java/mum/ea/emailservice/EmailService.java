package mum.ea.emailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import mum.ea.domain.Event;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Service("emailService")
public class EmailService {
    private static final String LOGO = "templates/images/INFINITY-LOGO.jpg";
    private static final String JPG_MIME = "image/jpg";
    private static final String DOCX_MIME = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Autowired
    public EmailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendEventMail(
            final String recipientName, final String recipientEmail, Object object, String documentName, final Locale locale)
            throws MessagingException {

        // Prepare the Thymeleaf evaluation context
        final Context thymeContext = new Context(locale);

        thymeContext.setVariable("name", recipientName);
        if (object instanceof Event) {
            thymeContext.setVariable("event", object);
        } 

        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        message.setSubject("Event Details");

        // could have CC, BCC, will also take an array of Strings
        message.setTo(recipientEmail);

        // Create the HTML body using Thymeleaf..template is orderReceivedMail.html
        String htmlContent;
        if (object instanceof Event) {
            htmlContent = this.templateEngine.process("newEvent", thymeContext);
        } else {
            htmlContent = this.templateEngine.process("cancelEvent", thymeContext);
        }

        message.addInline("infinity", new ClassPathResource(LOGO), JPG_MIME);
        message.setText(htmlContent, true);

        // Add attachment
        if (documentName != null) {
            String documentLocation = "templates/images/" + documentName;
            message.addAttachment(documentName, new ClassPathResource(documentLocation));
        }

        this.mailSender.send(mimeMessage);
    }
}
