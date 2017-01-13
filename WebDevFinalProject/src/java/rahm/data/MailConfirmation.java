/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rahm.data;

//import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Authenticator;

import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Atan
 */
public class MailConfirmation {
    String d_email = "rahmkai@gmail.com",
    d_password = "Coolgirl333",
    d_host = "smtp.gmail.com",
    d_port = "587",
    m_to = "rahmkai@yahoo.com",
    m_subject = "subject",
    m_text = "this is just a test mail";

public MailConfirmation() {
Properties props = new Properties();
props.put("mail.smtp.user", d_email);
props.put("mail.smtp.host", d_host);
props.put("mail.smtp.port", d_port);
props.put("mail.smtp.starttls.enable","true");
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.ssl.enable", true);
//props.put("mail.smtp.debug", "true");
//props.put("mail.smtp.socketFactory.port", d_port);
//props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//props.put("mail.smtp.socketFactory.fallback", "false");

SecurityManager security = System.getSecurityManager();

try {
//Authenticator auth = new SMTPAuthenticator();
Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(d_email, d_password);
			}
		  });
session.setDebug(true);

Message msg = new MimeMessage(session);
msg.setFrom(new InternetAddress(d_email));
msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(m_to));
msg.setSubject("Testing Subject");
msg.setText("Dear Mail Crawler,\n\n No spam to my email, please!");
Transport.send(msg);
} catch (Exception mex) {
mex.printStackTrace();
}
}
/**
private class SMTPAuthenticator extends javax.mail.Authenticator {
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(d_email, d_password);
    } 
}

    public static void main(String[] args){
        MailConfirmation sendmail=new MailConfirmation();
    } 
    * **/
}
