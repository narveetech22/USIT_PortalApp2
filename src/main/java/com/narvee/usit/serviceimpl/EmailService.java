package com.narvee.usit.serviceimpl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.narvee.usit.entity.Email;

@Configuration
public class EmailService {
	// commented for  mail error

	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMailWithInlineResources(String to, String subject, String fileToAttach){
	    MimeMessagePreparator preparator = new MimeMessagePreparator()
	    {
	        public void prepare(MimeMessage mimeMessage) throws Exception
	        {
	            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
	            mimeMessage.setFrom(new InternetAddress("arun@narveetech.com"));
	            mimeMessage.setSubject(subject);
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	            String ar =  null;
	            if(fileToAttach.equals("Resetpassword")) {
	             ar = "Please click below link to change the reset the password </br></br><a href='http://localhost:4200/reset-password'>Click here </a>";
	            }
	            else {
	            ar = "This is your new password = >"+fileToAttach;
	            }
	            //String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
	           // sendEmail(email, resetPasswordLink);
	            
	            
	            helper.setText(ar, true);
	            System.out.println(ar);
//	            FileSystemResource res = new FileSystemResource(new File(fileToAttach));
//	            helper.addInline("identifier1234", res);
	        }
	    };
	    try {
	        mailSender.send(preparator);
	    }
	    catch (MailException ex) {
	        // simply log it and go on...
	        System.err.println(ex.getMessage());
	    }
	}
	
	
	public void mailsender(Email email) {
	    MimeMessagePreparator preparator = new MimeMessagePreparator()
	    {
	        public void prepare(MimeMessage mimeMessage) throws Exception
	        {
	            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("saikiranbsoftworldusa@gmail.com"));
	            mimeMessage.setFrom(new InternetAddress("arun@narveetech.com"));
	            mimeMessage.setSubject(email.getSubject());
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	            
	            String ar =  null;
//	            if(fileToAttach.equals("Resetpassword")) {
//	             ar = "Please click below link to change the reset the password </br></br><a href='http://localhost:4200/reset-password'>Click here </a>";
//	            }
//	            else {
//	            ar = "This is your new password = >"+fileToAttach;
//	            }
	            //String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
	           // sendEmail(email, resetPasswordLink);
	            helper.setText(email.getBody(), true);
	          //  mimeMessage.setContent(email.getBody(), "multipart/mixed");
	            System.out.println(ar);
	            MimeBodyPart messageBodyPart = new MimeBodyPart();
	           // System.out.println("E:\\stores\\"+email.getAttachment()+"  ===================");
	            if(email.getAttachment()!="" || email.getAttachment()!=null || !email.getAttachment().isBlank() || !email.getAttachment().isEmpty()) {
	            	//File att = new File(new File("E:\\stores\\"), email.getAttachment());
	            	//messageBodyPart.attachFile(att);
	            	///
	            	
	            	try {
	            	    String content = new String(Files.readAllBytes(Paths.get("e:\\stores\\"+ email.getAttachment())));
	            	    System.out.println(content+" ==========");
	            	    FileSystemResource resource = new FileSystemResource(new File(content));
		            	//helper.addInline("image001", resource);
		            	helper.addAttachment(content, resource);
	            	} catch(Exception e) {
	            	    System.out.print("No file exists");
	            	}
	            	
	            	//System.out.println("E:\\stores\\"+email.getAttachment()+"  ===================");
	          //  FileSystemResource res = new FileSystemResource(new File("E:\\stores\\"+email.getAttachment()));
	           // helper.addInline("identifier1234", res);
	            }
	            
//	            FileSystemResource res = new FileSystemResource(new File(fileToAttach));
//	            helper.addInline("identifier1234", res);
	        }
	        
	        
	    };
	    try {
	        mailSender.send(preparator);
	    }
	    catch (MailException ex) {
	        // simply log it and go on...
	        System.err.println(ex.getMessage());
	    	
	}
	}
	
	

}
