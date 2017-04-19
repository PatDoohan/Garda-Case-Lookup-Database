package TeamProjectApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends JFrame implements ActionListener{

	LoginScreen login;
	
	public Login()
	{
		//creates the login screen from the class login screen that extends from jpanel
		login = new LoginScreen();
		//sets it to visible as it is the first screen required to be displayed
		login.setVisible(true);
		//creates a listener for the login button
		login.login.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//creates variables for the data entered by the user
				String enteredUsername = login.userName.getText();
				@SuppressWarnings("deprecation")
				String enteredPassword = login.password.getText();
				//creates variables for hasing the password and the data retrieved from the database
				String hashedPassword = null, retrievedLogin = null, retrievedPassword=null;
				//tries to has the password entered by the user, catches noSuchAlgorithmException.
				String Certified = null;
				String Status = null;
				try 
				{
					/*
					 * creates a message digest using sha 512 which encryptes the password
					 * entered by the user as the password in the database has already been
					 * encrypted, so the entered password must be encrypted to compare them.
					 */
					MessageDigest m;
					m = MessageDigest.getInstance("SHA-512");
					m.update(enteredPassword.getBytes(),0,enteredPassword.length());
					hashedPassword = new BigInteger(1, m.digest()).toString(16);
				}
				catch (NoSuchAlgorithmException e1) 
				{
					e1.printStackTrace();
				}

				//instance variales for use with the database.
				Statement st;
				ResultSet rs;
				
				try
				{
					//creates connection to database
					Class.forName("com.mysql.jdbc.Driver");
					//connects to mysql with username and password.
					java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
					//creates a statement
					st = con.createStatement();
					//creates a query and returns a result set from it based on the entered username.
					rs = st.executeQuery("SELECT idNo, password, Status, Certified FROM garda.garda WHERE idNo = '" + enteredUsername + "';");
				
					//uses the result set to set the values to the retrieved variables
					while(rs.next())
					{
						retrievedLogin = rs.getString(1);
						retrievedPassword = rs.getString(2);
						Status = rs.getString(3);
						Certified = rs.getString(4);
					}	
					//closes result set and connection.
					rs.close();
					con.close();
				}
				
				catch(Exception f)
				{
					System.out.println("Error: " + f.getMessage());
				}
			
				/*
				 * if the correct username and password is entered sets login to invisible and the main menu 
				 * to visible.
				*/
				if(retrievedLogin.equals(enteredUsername) && retrievedPassword.equals(hashedPassword) && Status.equals("Active"))
				{
					if(Certified.equals("True"))
					{
						WindowHandler frame = new WindowHandler(true);
					    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					    frame.setTitle("Garda Case Tracking Database");
					    frame.setSize(1200,1000);
					    frame.setLocationRelativeTo(null);
					    frame.setVisible(true);
					    dispose();
					}
					
					else
					{
						WindowHandler frame = new WindowHandler(false);
					    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					    frame.setTitle("Garda Case Tracking Database");
					    frame.setSize(1200,1000);
					    frame.setLocationRelativeTo(null);
					    frame.setVisible(true);
					    dispose();
					}
					
				}
				
				else if(!Status.equals("Active"))
				{
					JOptionPane.showMessageDialog(null, "Garda Is Currently Inactive, Please Contact Administrator For Further Information " , "Error", JOptionPane.ERROR_MESSAGE);
				}
				//if the wrong values are entered an error will be popped to screen
				else
				{
					JOptionPane.showMessageDialog(null, "Incorrect Username/Password Entered Please Try Again" , "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		//adds login to CardLayout container.
		add(login);
	}
	
	public static void main(String [] args)
	{
		Login frame = new Login();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setTitle("Garda Case Tracking Database");
	    frame.setSize(1200,1000);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
