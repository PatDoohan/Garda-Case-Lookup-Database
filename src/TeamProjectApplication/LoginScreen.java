package TeamProjectApplication;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class LoginScreen extends JFrame{
	
	protected JTextField userName;
	protected JPasswordField password;
	protected JLabel gardaLogo, usernameLabel, passwordLabel;
	protected JPanel container, TextContainer, buttonPane;
	protected JButton login;
	
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
		
		buttonPane = new JPanel();
		buttonPane.add(login);
		buttonPane.setBorder(new EmptyBorder(0,250,200,250));
		
		
		container.add(TextContainer, BorderLayout.CENTER);
		container.add(buttonPane, BorderLayout.SOUTH);
		
		add(container);
	
	
	}
	
	public static void main (String [] args)
	{
		LoginScreen frame = new LoginScreen();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setTitle("Garda Case Tracking Database");
	    frame.setSize(1000,800);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
	

}
