package Email;

import java.util.Properties;
import java.util.Scanner;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class CheckEmail {

   public static void check(String host, String storeType, String user,
      String password) 
   {
      try {

      //create properties field
      Properties properties = new Properties();

      properties.put("mail.pop3.host", host);
      properties.put("mail.pop3.port", "995");
      properties.put("mail.pop3.starttls.enable", "true");
      Session emailSession = Session.getDefaultInstance(properties);
  
      //create the POP3 store object and connect with the pop server
      Store store = emailSession.getStore("pop3s");

      store.connect(host, user, password);

      //create the folder object and open it
      Folder emailFolder = store.getFolder("INBOX");
      emailFolder.open(Folder.READ_ONLY);

      // retrieve the messages from the folder in an array and print it
      Message[] messages = emailFolder.getMessages();
     // System.out.println("messages.length---" + messages.length);

      for (int i = 0, n = messages.length; i < n; i++) {
         Message message = messages[i];
         System.out.println("---------------------------------");
         System.out.println("Email Number " + (i + 1));
         System.out.println("From: " + message.getFrom()[0]);
         System.out.println("Subject: " + message.getSubject());
         System.out.println("Time: " + message.getSentDate());
         System.out.println("Text: " + message.getContent().toString());

      }

      //close the store and folder objects
      emailFolder.close(false);
      store.close();

      } catch (NoSuchProviderException e) {
    	  System.out.println("Thanh cong");
         e.printStackTrace();
      } catch (MessagingException e) {
    	  System.out.println("Error!!");
         e.printStackTrace();
      } catch (Exception e) {
    	  System.out.println("Error!!");
         e.printStackTrace();
      }
   }

   public static void main(String[] args) {
	   Scanner scanIn = new Scanner(System.in);
      String host = "";
      String mailStoreType = "pop3";
      String username = "";
      String password = "";

      //check(host, mailStoreType, username, password);
      try{
   	   System.out.println("EmailID: ");
   	   username = scanIn.nextLine();
   	   System.out.println("Password: ");
   	   password = scanIn.nextLine();
   	   System.out.println("host: ");
   	   host = scanIn.nextLine();
   	  
      }catch(Exception e){
   	   System.out.println("Error!!!");
      }
      
      
      check(host, mailStoreType, username, password);
   }

}