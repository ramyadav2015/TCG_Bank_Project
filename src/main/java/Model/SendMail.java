package Model;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail{
	
	public static String send(String to,String subject,String msg){  
		  
		final String user="yk543852@gmail.com";  
		final String pass="nftwaulsiwexfnvs";  
		  
		//1st step) Get the session object    
		Properties pro = new Properties();  
		pro.put("mail.smtp.auth",true);
		pro.put("mail.smtp.starttls.enable", true);
		pro.put("mail.smtp.port",587 );
		pro.put("mail.smtp.host","smtp.gmail.com");
		pro.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		  
		Session instance=Session.getInstance(pro, new Authenticator()
		{
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(user,pass);
		}});
		//2nd step)compose message 
		try {  
		 Message message = new MimeMessage(instance);  
		 message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
		 message.setFrom(new InternetAddress("yk543852@gmail.com"));  
		 message.setSubject(subject);  
		 message.setText(msg);     
		 Transport.send(message);  
			 
		    
		 } catch (Exception e) {  
		    e.printStackTrace();  
		 } 
		return "Your Mail and Password Send Your Email Address";
		      
	}  
}
