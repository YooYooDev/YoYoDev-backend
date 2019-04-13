package com.yooyoo.util;

import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtil {
	public static void sendmail(String toEmail, String passWord) throws AddressException, MessagingException, IOException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.imap.ssl.enable", "true");
		props.put("mail.imap.auth.mechanisms", "XOAUTH2");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("yooyooschools@gmail.com", "Welcome@123");
			}
		});
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("yooyooschools@gmail.com", false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
		msg.setSubject("Forgot Password Email");
		msg.setContent("Your Password Is : "+passWord, "text/html");
		msg.setSentDate(new Date());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("Mail  From YooYooSchoool, Your Password Is : "+passWord, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		/*
		 * MimeBodyPart attachPart = new MimeBodyPart();
		 * 
		 * attachPart.attachFile("/var/tmp/image19.png");
		 * multipart.addBodyPart(attachPart);
		 */
		msg.setContent(multipart);
		Transport.send(msg);
	}
	
	public static void main(String[] args) {
		try {
			sendmail("kanhu629@gmail.com", "Niranjan629");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}