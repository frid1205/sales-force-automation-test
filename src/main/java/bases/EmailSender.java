package bases;

import java.io.IOException;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import utilization.Utilize;

public class EmailSender extends BaseConfig{
	private static final String EMAIL_FROM = "automationme96@gmail.com";
	private static final String APP_PASSWORD = "kgrf ymkw dwhs wvvl";
	
	public static void SentEmail() throws AddressException, MessagingException, IOException {
		String[] recipients = {
	            "fendy.ridwan@lumoshive.com",
	            "nurul.aini@lumoshive.com"
	        };
		Message message = new MimeMessage(getEmailSession());
		message.setFrom(new InternetAddress(EMAIL_FROM));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(String.join(",", recipients)));
		message.setSubject("Automation Test Result (Sales Tracking Force)");
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText("Here is the result of sales force of automation test, for more detail please contact QA engineer");

        // for attachment
        MimeBodyPart attachmentPart = new MimeBodyPart();
        String filePath = Utilize.getAbsolutePath("\\index.html");
        attachmentPart.attachFile(filePath);

        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(textPart);
        multipart.addBodyPart(attachmentPart);

        message.setContent(multipart);

        Transport.send(message);

        System.out.println("Email succesfully sent!");
	}
	
	private static Session getEmailSession() {
		return Session.getInstance(getGmailProperties(), new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(EMAIL_FROM, APP_PASSWORD);
		    }
		});
	}
	
	private static Properties getGmailProperties() {
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		return prop;
	}
}