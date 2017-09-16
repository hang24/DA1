package Email;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
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


public class CheckMail extends JFrame implements ActionListener{
	private JLabel lblTitle, lblTenMailForm, lblPassword,lblSubject, lblTo, lblText;
	private JTextField txtTenMailForm, txtMess,txtTenMailTo,txtSubject;
	private JTextArea txtText;
	private JPasswordField txtPassword;
	private JButton btnXemTiep, btnCancel,btnReceive,btnXoaRong;
	private JRadioButton rbtnGoogle, rbtnYahoo, rbtnHotMail;
	private int count=0;
	public CheckMail() {
		// TODO Auto-generated constructor stub
		setTitle("Receive Email");
		setSize(900, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		UIReceiveEmail();
	}
	
	private void UIReceiveEmail() {
		// TODO Auto-generated method stub
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
		pNorth.setPreferredSize(new Dimension(0,220));
		pNorth.setLayout(null);
		add(pNorth,BorderLayout.NORTH);
		
		JPanel pCen= new JPanel();
		pCen.add(lblTo= new JLabel("Từ:"));
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
		pSouth.add(btnReceive= new JButton("Xem thư thứ 1"));
		pSouth.add(btnXemTiep = new JButton("Xem thư tiếp theo"));
		pSouth.add(btnXoaRong= new JButton("Xóa rổng"));
		pSouth.add(btnCancel = new JButton("Cancel"));
		add(pSouth,BorderLayout.SOUTH);
		moKhoaTextfields(false);
		btnReceive.addActionListener(this);
		btnXemTiep.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnCancel.addActionListener(this);
	}

	private void moKhoaTextfields(boolean b){
		txtTenMailTo.setEnabled(b);
		txtSubject.setEnabled(b);
		txtText.setEnabled(b);
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
		String user = "";
		String password="";
		String host = "";
		String storeType = "pop3";
		if(o.equals(btnReceive)) {
			moKhoaTextfields(true);
			try{
				user=txtTenMailForm.getText().trim();
				password=txtPassword.getText().trim();
				if(rbtnGoogle.isSelected()) {
					   host="pop.gmail.com";
				   }
				else if(rbtnYahoo.isSelected()) {
					   host="pop.mail.yahoo.com";
				   }
				else if(rbtnHotMail.isSelected()) {
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

			      for (int i = 0, n = messages.length; i < 1; i++) {
			          Message message = messages[i];
			          txtTenMailTo.setText(""+message.getFrom()[0]);
			          txtSubject.setText("" + message.getSubject());
			          txtText.setText("Thời gian nhận: "+message.getSentDate()+ "\nNội dung: "+message.getContent().toString());

			       }
			      //close the store and folder objects
			      emailFolder.close(false);
			      store.close();

			      } catch (NoSuchProviderException n) {
			    	  txtMess.setText("Nhận email thành công");
			         n.printStackTrace();
			      } catch (MessagingException m) {
			    	  txtMess.setText("Error!!");
			         m.printStackTrace();
			      } catch (Exception ex) {
			    	  txtMess.setText("Error!!");
			         ex.printStackTrace();
			      }
			}
		else if(o.equals(btnXemTiep)) {
			try{
				user=txtTenMailForm.getText().trim();
				password=txtPassword.getText().trim();
				if(rbtnGoogle.isSelected()) {
					   host="pop.gmail.com";
				   }
				else if(rbtnYahoo.isSelected()) {
					   host="pop.mail.yahoo.com";
				   }
				else if(rbtnHotMail.isSelected()) {
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
			      
			      count++;
			          Message message = messages[count];
			          txtTenMailTo.setText(""+message.getFrom()[0]);
			          txtSubject.setText("" + message.getSubject());
			          txtText.setText(message.getSentDate()+ "\n"+message.getContent().toString());
		
			      //close the store and folder objects
			      emailFolder.close(false);
			      store.close();

			      } catch (NoSuchProviderException n) {
			    	  txtMess.setText("Nhận email thành công");
			         n.printStackTrace();
			      } catch (MessagingException m) {
			    	  txtMess.setText("Error!!");
			         m.printStackTrace();
			      } catch (Exception ex) {
			    	  txtMess.setText("Error!!");
			         ex.printStackTrace();
			      }
		}
		else if(o.equals(btnXoaRong)){
			XoaRong();
			moKhoaTextfields(false);
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