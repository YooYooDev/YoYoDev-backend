package com.yooyoo.util;

import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import com.yooyoo.vo.StudentEmailVO;

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
				return new PasswordAuthentication("yooyooschools@gmail.com", "5a508a44-f861-4f53-9c27-9c33796fdf15");
			}
		});
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("yooyooschools@gmail.com", false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
		msg.setSubject("Password Email");
		msg.setContent("Your Password Is : "+passWord, "text/html");
		msg.setSentDate(new Date());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(YooYooAppConstants.FORGOTPASSWORD +passWord+"<BR>"+YooYooAppConstants.DONTSHARE+"<BR><BR>"+YooYooAppConstants.SOCIAL+"<BR><BR>"+YooYooAppConstants.LAST, "text/html");

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
	
	public static void sendStudentEmail(StudentEmailVO studentVo) throws MessagingException{

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.imap.ssl.enable", "true");
		props.put("mail.imap.auth.mechanisms", "XOAUTH2");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("yooyooschools@gmail.com", "5a508a44-f861-4f53-9c27-9c33796fdf15");
			}
		});
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("yooyooschools@gmail.com", false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(studentVo.getEmial()));
		msg.setSubject(YooYooAppConstants.STUDENT_SUBJECT);
		msg.setSentDate(new Date());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("<p>" + YooYooAppConstants.MESSAGE_PRE + studentVo.getName() + ",<BR>"
				+ YooYooAppConstants.MESSAGE_POST + "</p><BR><BR>" + YooYooAppConstants.STEP1 + "<BR><BR>"
				+ YooYooAppConstants.STEP2 + YooYooAppConstants.SCHOOL_CODE + studentVo.getSchoolCode() + "<BR>"
				+ YooYooAppConstants.EMAILADDRESS + studentVo.getEmial() + YooYooAppConstants.PRIMARY_EMAIL + "<BR>"
				+ YooYooAppConstants.PASSWORD + studentVo.getPassword()+"<BR><BR>"+YooYooAppConstants.LAST+"<BR><BR>"+YooYooAppConstants.SOCIAL, "text/html");

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
		StudentEmailVO vo = new StudentEmailVO();
		vo.setName("Name ");
		vo.setEmial("kanhu629@gmail.com");
		vo.setSchoolCode("1234");
		vo.setPassword("myPass");
		try {
			sendStudentEmail(vo);
			//sendmail("kanhu629@gmail.com", "Niranjan629");
			System.out.println("Emain sent...");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}