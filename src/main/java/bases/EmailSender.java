package bases;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
	
	@Test
	@Parameters({"email"})
	public static void testSendEmail(String email) {
		if(email.equals("yes")) {
    		try {
    			SentEmail();
    		} catch (MessagingException | IOException e) {
    			e.printStackTrace();
    		}
    	}
	}
	
	public static void SentEmail() throws AddressException, MessagingException, IOException {
		String[] recipients = {
				"fendy.ridwan@lumoshive.com",
	            "nurul.aini@lumoshive.com"
		};

        // Buat pesan email
        Message message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(EMAIL_FROM));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(String.join(",", recipients)));
        message.setSubject("Automation Test Result (Sales Tracking Force)");

        // Part untuk teks email
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText("Here is the result of sales force of automation test, for more details please contact the QA engineer.");

        // Daftar file attachment (tambah file sesuai kebutuhan)
        List<String> attachmentPaths = Arrays.asList(
        	Utilize.getAbsolutePath("\\automationtestreport\\SuperadminCompanyConfig.html"),
            Utilize.getAbsolutePath("\\automationtestreport\\AdminCompany.html"),
            Utilize.getAbsolutePath("\\automationtestreport\\SalesInApplicationMobile.html")
        );

        // Multipart untuk menggabungkan text dan attachments
        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(textPart);

        // Tambahkan setiap attachment
        for (String filePath : attachmentPaths) {
            File file = new File(filePath);
            if (file.exists()) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.attachFile(file);
                multipart.addBodyPart(attachmentPart);
            } else {
                System.err.println("File tidak ditemukan: " + filePath);
            }
        }

        // Set content email
        message.setContent(multipart);

        // Kirim email
        Transport.send(message);

        System.out.println("Email successfully sent!");
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