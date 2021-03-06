package email;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class FormDangNhap extends JFrame implements ActionListener{
	private JLabel lblTitle, lblTenMailForm, lblPassword,lblSubject, lblTo, lblText, lblChonfile;
	private JTextField txtTenMailForm, txtMess,txtTenMailTo,txtSubject;
	private JTextArea txtText,txtFile;
	private JPasswordField txtPassword;
	private JButton btnSend, btnCancel,btnXoaRong, btnReceive,btnChonfile;
	private JRadioButton rbtnGoogle, rbtnYahoo, rbtnHotMail;
	private File filename;
	private int action;
	private JFileChooser fileChooser= new JFileChooser();
	private JPanel pPanel1;
	public FormDangNhap() {
		// TODO Auto-generated constructor stub
		setTitle("Phần mềm Send/Receive Email");
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel topPanel =new JPanel();
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel);
		UIChonDangNhap();
		email.ReceiveEmail receiveEmail= new email.ReceiveEmail();
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Send Email", pPanel1);
		tabbedPane.addTab("Receive Email", receiveEmail.pPanel2);
		topPanel.add( tabbedPane, BorderLayout.CENTER );
		
		
	}
	
	private void UIChonDangNhap() {
		// TODO Auto-generated method stub
		pPanel1= new JPanel();
		pPanel1.setLayout(new BorderLayout());
		JPanel pNorth =new JPanel();
		pNorth.add(lblTitle=new JLabel("Gửi Email"));
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
		pNorth.setPreferredSize(new Dimension(0,220));
		pNorth.setLayout(null);
		pPanel1.add(pNorth,BorderLayout.NORTH);
		
		JPanel pCen= new JPanel();
		pCen.add(lblTo= new JLabel("Tới:"));
	    lblTo.setBounds(200, 10, 100, 25);
	    pCen.add(lblSubject= new JLabel("Chủ đề:"));
	    lblSubject.setBounds(200, 50, 100, 25);
	    pCen.add(lblText= new JLabel("Nội Dung:"));
	    lblText.setBounds(200, 90, 100, 25);
	    pCen.add(lblChonfile= new JLabel("Chọn File"));
	    lblChonfile.setBounds(200, 260, 100, 30);
	    pCen.add(txtTenMailTo= new JTextField());
	    txtTenMailTo.setBounds(300, 10, 300, 25);
	    pCen.add(txtSubject= new JTextField());
	    txtSubject.setBounds(300, 50, 300, 25);
	    pCen.add(txtText = new JTextArea());
	    txtText.setBounds(300, 90, 400, 150);
	    pCen.add(txtFile= new JTextArea());
	    txtFile.setEditable(false);
	    txtFile.setBounds(300, 260, 300, 30);
	    pCen.add(btnChonfile= new JButton("Chọn File"));
	    btnChonfile.setBounds(650, 260,110,30);
	    pCen.add(txtMess = new JTextField());
		txtMess.setEditable(false);
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		txtMess.setFont(new Font("Arial",Font.ITALIC,15));
		txtMess.setBounds(300, 320, 300, 30);
	    pCen.setPreferredSize(new Dimension(0,400));
		pCen.setLayout(null);
		pPanel1.add(pCen,BorderLayout.CENTER);
		
		JPanel pSouth =new JPanel();
		pSouth.add(btnSend = new JButton("Gửi"));
		pSouth.add(btnXoaRong = new JButton("Soạn email mới"));
		pSouth.add(btnCancel = new JButton("Thoát"));
		pPanel1.add(pSouth,BorderLayout.SOUTH);
		btnChonfile.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnSend.addActionListener(this);
		btnCancel.addActionListener(this);
	}

	private void XoaRong() {
		txtTenMailTo.setText("");
		txtSubject.setText("");
		txtText.setText("");
		txtFile.setText("");
		txtMess.setText("");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o= e.getSource();
		 String from = "";
		   String pass = "";
		   String to = "";
		   String host = "";
		   String subject = "";
		   String text = "";
		   int port = 0;
		if(o.equals(btnSend)) {
			try{
				
				pass=txtPassword.getText().trim();
				if(rbtnGoogle.isSelected()) {
					from=txtTenMailForm.getText().trim()+"@gmail.com";
					   host="smtp.gmail.com";
						port=587;
				   }
				else if(rbtnYahoo.isSelected()) {
					from=txtTenMailForm.getText().trim()+"@yahoo.com";
					   host="smtp.mail.yahoo.com";
						port=587;
				   }
				else if(rbtnHotMail.isSelected()) {
					from=txtTenMailForm.getText().trim()+"@hotmail.com";
					   host="smtp.live.com";
						port=587;
				   }
				to=txtTenMailTo.getText().trim();
				subject=txtSubject.getText().trim();
				text=txtText.getText().trim();
			   }catch(Exception ex){
				   System.out.println("Error!!!");
			   }
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
			   if(txtFile.getText().trim().equals("")) {
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
					      JOptionPane.showMessageDialog(null, "Gửi email thành công",
				                  "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
					      XoaRong();
					   } catch (AddressException ae) {
						   JOptionPane.showMessageDialog(null, "Gửi email thất bại",
					                  "Thông Báo", JOptionPane.ERROR_MESSAGE);
							ae.printStackTrace();
					   }catch (MessagingException mex) {
						   JOptionPane.showMessageDialog(null, "Địa chỉ email hoặc mật khẫu của bạn sai!",
					                  "Thông Báo", JOptionPane.ERROR_MESSAGE);
					      mex.printStackTrace();
					      
					
					}
			   }
			   else {
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

			      // Tao message 
			         BodyPart messageBodyPart = new MimeBodyPart();
			         messageBodyPart.setText(text);
			         
			      // Tao mot multipar message
			         Multipart multipart = new MimeMultipart();
			         
			      // Thiet lap phan text message
			         multipart.addBodyPart(messageBodyPart);
			         
			         messageBodyPart = new MimeBodyPart();
						String tenFile=txtFile.getText().trim();
						
						if (action == JFileChooser.APPROVE_OPTION) {
							 filename= fileChooser.getSelectedFile();
				        } 
						
			         DataSource source = new FileDataSource(filename);
			         messageBodyPart.setDataHandler(new DataHandler(source));
			         messageBodyPart.setFileName(tenFile);
			         multipart.addBodyPart(messageBodyPart);

			         // Gui cac phan day du cua message
			         message.setContent(multipart );
			   

			      // Send message
			      Transport transport = session.getTransport("smtp");
			      transport.connect(host, from, pass);
			      transport.sendMessage(message, message.getAllRecipients());
			      transport.close();
			      JOptionPane.showMessageDialog(null, "Gửi email thành công",
		                  "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
			      XoaRong();
			   } catch (AddressException ae) {
				   JOptionPane.showMessageDialog(null, "Gửi email thất bại",
			                  "Thông Báo", JOptionPane.ERROR_MESSAGE);
					ae.printStackTrace();
			   }catch (MessagingException mex) {
				   JOptionPane.showMessageDialog(null, "Địa chỉ email hoặc mật khẫu của bạn sai!",
			                  "Thông Báo", JOptionPane.ERROR_MESSAGE);
			      mex.printStackTrace();
			      
			
			  }
			   }

		}
		else if(o.equals(btnCancel)) {
			int cancel= JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thoát không?","Thoát ứng dụng",JOptionPane.YES_NO_OPTION);
			if(cancel==JOptionPane.YES_OPTION) {
				System.exit(0);
				this.dispose();
			}
			
		}
		else if(o.equals(btnXoaRong)) {
			txtTenMailForm.setText("");
			txtPassword.setText("");
			XoaRong();
		}
		else if(o.equals(btnChonfile)) {
			fileChooser.setMultiSelectionEnabled(false);
			action= fileChooser.showOpenDialog(this);
			if (action == JFileChooser.APPROVE_OPTION) {
				txtFile.setText("");
	            txtFile.append(fileChooser.getSelectedFile().getName());
	        }
			
		}

		   
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException ex) {
			Logger.getLogger(FormDangNhap.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InstantiationException ex) {
			Logger.getLogger(FormDangNhap.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IllegalAccessException ex) {
			Logger.getLogger(FormDangNhap.class.getName()).log(Level.SEVERE, null, ex);
			} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(FormDangNhap.class.getName()).log(Level.SEVERE, null, ex);
			}
		FormDangNhap frm = new FormDangNhap();
		frm.setVisible(true);
	}

}
