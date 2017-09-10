package Email;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail{
public static void main(String [] args)
{    
	Scanner scanIn = new Scanner(System.in);
    String from = "";
    String pass = "";
    String to = "";
    String host = "";
    String subject = "";
    String text = "";
    int port = 0;
   try{
	   System.out.println("EmailID: ");
	   from = scanIn.nextLine();
	   System.out.println("Password: ");
	   pass = scanIn.nextLine();
	   System.out.println("To"
	   		+ ": ");
	   to = scanIn.nextLine();
	   System.out.println("host: ");
	   host = scanIn.nextLine();
	  // System.out.println("port: ");
	  // port = scanIn.nextInt();
	   System.out.println("Subject: ");
	   subject = scanIn.nextLine();
	   System.out.println("Text: ");
	   text = scanIn.nextLine();
	   System.out.println("port: ");
	   port = scanIn.nextInt();
   }catch(Exception e){
	   System.out.println("Error!!!");
   }
   

   // Get system properties
   Properties properties = System.getProperties();
   // Setup mail server
   properties.put("mail.smtp.starttls.enable", "true");
   properties.put("mail.smtp.host", host);
   properties.put("mail.smtp.user", from);
   properties.put("mail.smtp.password", pass);
   properties.put("mail.smtp.port", port);
   properties.put("mail.smtp.auth", "true");

   // Get the default Session object.
   Session session = Session.getDefaultInstance(properties);

   try{
      // Create a default MimeMessage object.
      MimeMessage message = new MimeMessage(session);

      // Set From: header field of the header.
      message.setFrom(new InternetAddress(from));

      // Set To: header field of the header.
      message.addRecipient(Message.RecipientType.TO,
                               new InternetAddress(to));

      // Set Subject: header field
      message.setSubject(subject);

      // Now set the actual message
      message.setText(text);

      // Send message
      Transport transport = session.getTransport("smtp");
      transport.connect(host, from, pass);
      transport.sendMessage(message, message.getAllRecipients());
      transport.close();
      System.out.println("Sent message successfully....");
   } catch (AddressException e) {
		System.out.println("Sent message a failed!!!");
		e.printStackTrace();
   }catch (MessagingException mex) {
	   System.out.println("Sent message a failed!!!");
      mex.printStackTrace();
      
}
}
}