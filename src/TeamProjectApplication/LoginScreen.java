package TeamProjectApplication;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginScreen extends JPanel{
	
	protected JTextField userName;
	protected JPasswordField password;
	protected JLabel gardaLogo, usernameLabel, passwordLabel;
	protected JPanel container, TextContainer, buttonPane;
	protected JButton login;
	private boolean validated = false;
		
	public LoginScreen()
	{
		container = new JPanel();
		container.setLayout(new BorderLayout(5,5));
		container.setBorder(new EmptyBorder(100,40,0,40));
		
		gardaLogo = new JLabel(new ImageIcon("Images/gardaLogo.png"));
		container.add(gardaLogo, BorderLayout.NORTH);
		
		TextContainer = new JPanel(new GridLayout(0,1, 5, 5));
		TextContainer.setBorder(new EmptyBorder(0,250,0,250));
		
		usernameLabel = new JLabel("Garda Identification Number: ");
		userName = new JTextField(10);
	
		TextContainer.add(usernameLabel);
		TextContainer.add(userName);
		
		passwordLabel = new JLabel("Garda Assigned Password:");
		password = new JPasswordField(10);
		
		TextContainer.add(passwordLabel);
		TextContainer.add(password);
		
		login = new JButton("Login");
		login.setPreferredSize(new Dimension(100,35));
//		login.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				String enteredUsername = userName.getText();
//				String enteredPassword = password.getText();
//				String hashedPassword = null, retrievedLogin = null, retrievedPassword=null;
//				try 
//				{
//					MessageDigest m;
//					m = MessageDigest.getInstance("SHA-512");
//					m.update(enteredPassword.getBytes(),0,enteredPassword.length());
//					hashedPassword = new BigInteger(1, m.digest()).toString(16);
//				}
//				catch (NoSuchAlgorithmException e1) 
//				{
//					e1.printStackTrace();
//				}
//			
//				Statement st;
//				ResultSet rs;
//				
//				try
//				{
//					Class.forName("com.mysql.jdbc.Driver");
//					java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","Password");
//					st = con.createStatement();
//					rs = st.executeQuery("SELECT * FROM garda.authentication WHERE LoginID = '" + enteredUsername + "';");
//				
//					while(rs.next())
//					{
//						retrievedLogin = rs.getString(1);
//						retrievedPassword = rs.getString(2);
//					}	
//					rs.close();
//					con.close();
//				}
//				
//				catch(Exception f)
//				{
//					System.out.println("Error: " + f.getMessage());
//				}
//			
//				if(retrievedLogin.equals(enteredUsername) && retrievedPassword.equals(hashedPassword))
//				{
//					GardaMenu MenuFrame = new GardaMenu();
//					MenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//					MenuFrame.setTitle("Garda Case Tracking Database");
//					MenuFrame.setSize(1000,800);
//					MenuFrame.setLocationRelativeTo(null);
//					MenuFrame.setVisible(true);
//				}
//			}
//		});	
		
		buttonPane = new JPanel();
		buttonPane.add(login);
		buttonPane.setBorder(new EmptyBorder(0,250,200,250));
		
		container.add(TextContainer, BorderLayout.CENTER);
		container.add(buttonPane, BorderLayout.SOUTH);
		
		add(container);
	
	
	}
	
	public boolean isValidated()
	{
		return validated;
	}
	
	public static void main (String [] args)
	{
//		LoginScreen frame = new LoginScreen();
//	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    frame.setTitle("Garda Case Tracking Database");
//	    frame.setSize(1000,800);
//	    frame.setLocationRelativeTo(null);
//	    frame.setVisible(true);
	}
	

}
