package Email;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;


public class ReceiveEmail extends JFrame implements ActionListener{
	private JLabel lblTitle, lblTenMailForm, lblPassword;
	private JTextField txtTenMailForm, txtMess;
	private JList ListNoiDung;
	private JPasswordField txtPassword;
	private JButton btnCancel,btnReceive,btnXoaRong;
	private JRadioButton rbtnGoogle, rbtnYahoo, rbtnHotMail;
	private DefaultListModel<String> model = new DefaultListModel<>();
	private String saveDirectory = "D:/DAA";
	public JPanel pPanel2;
	public ReceiveEmail() {
		// TODO Auto-generated constructor stub
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		UIReceiveEmail();
	}
	
	private void UIReceiveEmail() {
		// TODO Auto-generated method stub
		pPanel2 =new JPanel();
		pPanel2.setLayout(new BorderLayout());
		JPanel pNorth =new JPanel();
		pNorth.add(lblTitle=new JLabel("Nhận Email"));
		Font fp= new Font("Time New Roman",Font.BOLD,30);
		lblTitle.setFont(fp);
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setBounds(350, 10 ,400 , 40);
		
		pNorth.add(rbtnGoogle = new JRadioButton("Đăng nhập bằng Google"));
		rbtnGoogle.setBounds(300, 70, 300, 30);
		pNorth.add(rbtnYahoo =new JRadioButton ("Đăng Nhập bằng Yahoo"));
		rbtnYahoo.setBounds(300, 100, 300, 30);
		pNorth.add(rbtnHotMail =new JRadioButton ("Đăng Nhập bằng HotMail"));
		rbtnHotMail.setBounds(300, 130, 300, 30);
		// chọn 1 trong nhiều
		ButtonGroup group = new ButtonGroup();
	       group.add (rbtnGoogle);
	       group.add (rbtnYahoo);
	       group.add (rbtnHotMail);
		
		int x= 200,x2=300, width =100, width2=300,height=25;
		pNorth.add(lblTenMailForm= new JLabel("Địa chỉ Email:"));
		lblTenMailForm.setBounds(x, 160, width, height);
		pNorth.add(lblPassword = new JLabel("Mật Khẩu:"));
		lblPassword.setBounds(x, 190,width, height);
		
		pNorth.add(txtTenMailForm = new JTextField());
		txtTenMailForm.setBounds(x2, 160, width2, height);
		pNorth.add(txtPassword = new JPasswordField());
		txtPassword.setBounds(x2, 190, width2, height);
		pNorth.add(txtMess = new JTextField());
		txtMess.setEditable(false);
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		txtMess.setFont(new Font("Arial",Font.ITALIC,15));
		txtMess.setBounds(x2, 230, 300, 30);
		pNorth.setPreferredSize(new Dimension(0,270));
		pNorth.setLayout(null);
		pPanel2.add(pNorth,BorderLayout.NORTH);
		
		String[] noidung= {" ---------------------------------------Nội dung nhận được là: --------------------------------------"};
		ListNoiDung= new JList(noidung);
		ListNoiDung.setVisibleRowCount(13);
		JScrollPane listPane=new JScrollPane(ListNoiDung);
		JPanel pCen= new JPanel();
		pCen.setBorder(BorderFactory.createTitledBorder("Nội dung nhận:"));
		pCen.add(listPane);
		pPanel2.add(pCen,BorderLayout.CENTER);
		
		JPanel pSouth =new JPanel();
		pSouth.add(btnReceive= new JButton("Xem thư"));
		pSouth.add(btnXoaRong= new JButton("Xóa rổng"));
		pSouth.add(btnCancel = new JButton("Exit"));
		pPanel2.add(pSouth,BorderLayout.SOUTH);
		btnReceive.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnCancel.addActionListener(this);
	}

	
	private void XoaRong() {
		txtTenMailForm.setText("");
		txtPassword.setText("");
		txtMess.setText("");
		model.clear();
		model.addElement(" ---------------------------------------Nội dung nhận được là : --------------------------------------");
		ListNoiDung.setModel(model);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o= e.getSource();
		String user = "";
		String password="";
		String host = "";
		String from="";
		if(o.equals(btnReceive)) {
			try{
				password=txtPassword.getText().trim();
				if(rbtnGoogle.isSelected()) {
					user=txtTenMailForm.getText().trim()+"@gmail.com";
					   host="pop.gmail.com";
				   }
				else if(rbtnYahoo.isSelected()) {
					user=txtTenMailForm.getText().trim();
					   host="pop.mail.yahoo.com";
				   }
				else if(rbtnHotMail.isSelected()) {
					user=txtTenMailForm.getText().trim()+"@hotmail.com";
					   host="pop3.live.com";
				   }
		      }catch(Exception ex){
		   	   System.out.println("Error!!!");
		      }
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
			      
			      	for (int i = 0, n = messages.length; i < n; i++) {
			      		Message message = messages[i];
					    Address[] fromAddress = message.getFrom();
				        from = fromAddress[0].toString();
				        String subject = message.getSubject();
				        String sentDate = message.getSentDate().toString();
				 
				        String contentType = message.getContentType();
				        String messageContent = "";
				                
				        String attachFiles = "";
			        	  
				        if (contentType.contains("multipart")) {
			        	  Multipart multiPart = (Multipart) message.getContent();
			        	  int numberOfParts = multiPart.getCount();
		                    for (int partCount = 0; partCount < numberOfParts; partCount++) {
		                        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
		                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
		                            // this part is attachment
		                            String fileName = part.getFileName();
		                            attachFiles += fileName + ", ";
		                            part.saveFile(saveDirectory + File.separator + fileName);
		                        } else {
		                            // this part may be the message content
		                            messageContent = part.getContent().toString();
		                        }
		                    }
		 
		                    if (attachFiles.length() > 1) {
		                        attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
		                    }
		                } else if (contentType.contains("text/plain") || contentType.contains("text/html")) {
		                    Object content = message.getContent();
		                    if (content != null) {
		                        messageContent = content.toString();
		                    }
		                }
				        txtMess.setText("");
				        
				       // model.addElement(" ---------------------------------------Nội dung nhận được là : --------------------------------------");
				        model.addElement("Email Number " + (i + 1) + ":");
				        model.addElement("From: " + from);
				        model.addElement("Subject: " + subject);
				        model.addElement("Time: " + sentDate);
				        model.addElement("Text: " + messageContent);
				        if(!attachFiles.equals("")) {
				        	model.addElement("Tên File: " + attachFiles);
				        	model.addElement("File của bạn được lưu ở D:/DAA");
				        }
				        model.addElement("-----------------------------------------");
				        
			      }
			      	txtMess.setText("");
			      	if(from.equals("")) {
			        	txtMess.setText("Email của bạn rỗng!");
			        	model.addElement(" -------------------------------------------- Không có thư mới! -----------------------------------------");
			        }
			      		ListNoiDung.setModel(model);
			      //close the store and folder objects
			      emailFolder.close(false);
			      store.close();

			      } catch (NoSuchProviderException n) {
			    	  txtMess.setText("Gửi email thất bại!!");
			         n.printStackTrace();
			      } catch (MessagingException m) {
			    	  txtMess.setText("Địa chỉ email hoặc mật khẩu sai! ");
			         m.printStackTrace();
			      } catch (Exception ex) {
			    	  txtMess.setText("Gửi email thất bại!");
			         ex.printStackTrace();
			      }
			}
		else if(o.equals(btnXoaRong)){
			XoaRong();
		}
		else if(o.equals(btnCancel)) {
			int cancel= JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát không?","Thoát ứng dụng",JOptionPane.YES_NO_OPTION);
			if(cancel==JOptionPane.YES_OPTION) {
				System.exit(0);
				this.dispose();
			}
			
		}
	}
}