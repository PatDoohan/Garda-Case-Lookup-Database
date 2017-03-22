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
		container.setBorder(new EmptyBorder(200,40,0,40));
		
		gardaLogo = new JLabel(new ImageIcon("Images/gardaLogo.png"));
		container.add(gardaLogo, BorderLayout.NORTH);
		
		TextContainer = new JPanel(new GridLayout(0,1, 5, 5));
		TextContainer.setBorder(new EmptyBorder(0,250,0,250));
		
		usernameLabel = new JLabel("Garda Identification Number: ");
		usernameLabel.setFont(new Font("Sans-Serif", 0, 20));
		userName = new JTextField(10);
		userName.setPreferredSize(new Dimension(250,25));
	
		TextContainer.add(usernameLabel);
		TextContainer.add(userName);
		
		passwordLabel = new JLabel("Garda Assigned Password:");
		passwordLabel.setFont(new Font("Sans-Serif", 0, 20));
		password = new JPasswordField(10);
		
		TextContainer.add(passwordLabel);
		TextContainer.add(password);
		
		login = new JButton("Login");
		login.setPreferredSize(new Dimension(100,35));
		login.setMnemonic('L');	
		
		buttonPane = new JPanel();
		buttonPane.add(login);
		buttonPane.setBorder(new EmptyBorder(0,250,200,250));
		
		container.add(TextContainer);
		container.add(buttonPane, BorderLayout.SOUTH);
		
		add(container);
	
	
	}

}
