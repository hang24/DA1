package Email;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMail extends JFrame implements ActionListener{
	private JLabel lblTitle, lblTenMailForm, lblPassword,lblSubject, lblTo, lblText;
	private JTextField txtTenMailForm, txtMess,txtTenMailTo,txtSubject;
	private JTextArea txtText;
	private JPasswordField txtPassword;
	private JButton btnSend, btnCancel,btnXoaRong, btnReceive;
	private JRadioButton rbtnGoogle, rbtnYahoo, rbtnHotMail;
	public SendMail() {
		// TODO Auto-generated constructor stub
		setTitle("Send Email");
		setSize(900, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		UIChonDangNhap();
		
		
	}
	
	private void UIChonDangNhap() {
		// TODO Auto-generated method stub
		JPanel pNorth =new JPanel();
		pNorth.add(lblTitle=new JLabel("Đăng nhập"));
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
		add(pNorth,BorderLayout.NORTH);
		
		JPanel pCen= new JPanel();
		pCen.add(lblTo= new JLabel("Tới:"));
	    lblTo.setBounds(200, 10, 100, 25);
	    pCen.add(lblSubject= new JLabel("Chủ đề:"));
	    lblSubject.setBounds(200, 50, 100, 25);
	    pCen.add(lblText= new JLabel("Nội Dung:"));
	    lblText.setBounds(200, 90, 100, 25);
	    pCen.add(txtTenMailTo= new JTextField());
	    txtTenMailTo.setBounds(300, 10, 300, 25);
	    pCen.add(txtSubject= new JTextField());
	    txtSubject.setBounds(300, 50, 300, 25);
	    pCen.add(txtText = new JTextArea());
	    txtText.setBounds(300, 90, 400, 150);
	    pCen.add(txtMess = new JTextField());
		txtMess.setEditable(false);
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		txtMess.setFont(new Font("Arial",Font.ITALIC,15));
		txtMess.setBounds(300, 270, 300, 30);
	    pCen.setPreferredSize(new Dimension(0,300));
		pCen.setLayout(null);
		add(pCen,BorderLayout.CENTER);
		
		JPanel pSouth =new JPanel();
		pSouth.add(btnSend = new JButton("Send"));
		pSouth.add(btnXoaRong = new JButton("Soạn email mới"));
		pSouth.add(btnReceive= new JButton("Nhận Email"));
		pSouth.add(btnCancel = new JButton("Cancel"));
		add(pSouth,BorderLayout.SOUTH);
		btnXoaRong.addActionListener(this);
		btnReceive.addActionListener(this);
		btnSend.addActionListener(this);
		btnCancel.addActionListener(this);
	}

	private void XoaRong() {
		txtTenMailForm.setText("");
		txtPassword.setText("");
		txtTenMailTo.setText("");
		txtSubject.setText("");
		txtText.setText("");
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
				from=txtTenMailForm.getText().trim();
				pass=txtPassword.getText().trim();
				if(rbtnGoogle.isSelected()) {
					   host="smtp.gmail.com";
						port=587;
				   }
				else if(rbtnYahoo.isSelected()) {
					   host="smtp.mail.yahoo.com";
						port=587;
				   }
				else if(rbtnHotMail.isSelected()) {
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
			      txtMess.setText("Gửi email thành công");
			   } catch (AddressException ae) {
				   txtMess.setText("Gửi email thất bại");
					ae.printStackTrace();
			   }catch (MessagingException mex) {
				   txtMess.setText("Gửi email thất bại");
			      mex.printStackTrace();
			      
			
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
			XoaRong();
		}
		else if(o.equals(btnReceive)) {
			CheckMail re= new CheckMail();
			re.setVisible(true);
		}

		   
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SendMail frm = new SendMail();
		frm.setVisible(true);
	}

}
