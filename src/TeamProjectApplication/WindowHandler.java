package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowHandler extends JFrame implements ActionListener{
	
	LoginScreen login;
	GardaMenu mainMenu;
	CaseMenu caseMenu;
	JPanel Container;
	
	public WindowHandler()
	{
		Container = new JPanel();
		Container.setLayout(new CardLayout());
		login = new LoginScreen();
		login.setVisible(true);
		login.login.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String enteredUsername = login.userName.getText();
				String enteredPassword = login.password.getText();
				String hashedPassword = null, retrievedLogin = null, retrievedPassword=null;
				try 
				{
					MessageDigest m;
					m = MessageDigest.getInstance("SHA-512");
					m.update(enteredPassword.getBytes(),0,enteredPassword.length());
					hashedPassword = new BigInteger(1, m.digest()).toString(16);
				}
				catch (NoSuchAlgorithmException e1) 
				{
					e1.printStackTrace();
				}
			
				Statement st;
				ResultSet rs;
				
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","Password");
					st = con.createStatement();
					rs = st.executeQuery("SELECT * FROM garda.authentication WHERE LoginID = '" + enteredUsername + "';");
				
					while(rs.next())
					{
						retrievedLogin = rs.getString(1);
						retrievedPassword = rs.getString(2);
					}	
					rs.close();
					con.close();
				}
				
				catch(Exception f)
				{
					System.out.println("Error: " + f.getMessage());
				}
			
				if(retrievedLogin.equals(enteredUsername) && retrievedPassword.equals(hashedPassword))
				{
					mainMenu.setVisible(true);
					login.setVisible(false);
				}
			}
		});
		
		Container.add(login);
		

		mainMenu = new GardaMenu();
		mainMenu.setVisible(false);
		mainMenu.caseLookup.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				caseMenu.setVisible(true);
				mainMenu.setVisible(false);
			}
			
		});
		Container.add(mainMenu);
		
		
		caseMenu = new CaseMenu();
		caseMenu.setVisible(false);
		caseMenu.back.addActionListener(this);
		Container.add(caseMenu);
		add(Container);
	
		
	}
	
	public static void main (String [] args)
	{
		WindowHandler frame = new WindowHandler();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setTitle("Garda Case Tracking Database");
	    frame.setSize(1000,800);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		caseMenu.setVisible(false);
		mainMenu.setVisible(true);
		
	}
}
