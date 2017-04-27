package TeamProjectApplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;

import java.awt.*;

public class LoginScreen extends JPanel{
	
	protected JTextField userName;
	protected JPasswordField password;
	protected JLabel gardaLogo, usernameLabel, passwordLabel;
	protected JPanel container, TextContainer, buttonPane;
	public static JButton login;
		
	public LoginScreen()
	{
		//creates the container that holds all the components
		container = new JPanel();
		container.setLayout(new BorderLayout(5,5));
		container.setBorder(new EmptyBorder(200,40,0,40));
		
		//creates the garda logo and adds it to border layout north in the container panel
		gardaLogo = new JLabel(new ImageIcon("Images/gardaLogo.png"));
		container.add(gardaLogo, BorderLayout.NORTH);
		
		//creates the panel that holds the login information
		TextContainer = new JPanel(new GridLayout(0,1, 5, 5));
		TextContainer.setBorder(new EmptyBorder(0,250,0,250));
		
		//creates the username label and textfield
		usernameLabel = new JLabel("Garda Identification Number: ");
		usernameLabel.setFont(new Font("Sans-Serif", 0, 20));
		userName = new JTextField(10);
		userName.setPreferredSize(new Dimension(250,25));
		((AbstractDocument)userName.getDocument()).setDocumentFilter(new SpecialCharacterFilter());
	
		//adds the above label and textfield to the text container panel
		TextContainer.add(usernameLabel);
		TextContainer.add(userName);
		
		//creates the password label and password field
		passwordLabel = new JLabel("Garda Assigned Password:");
		passwordLabel.setFont(new Font("Sans-Serif", 0, 20));
		password = new JPasswordField(10);
		
		//adds the above label and textfield to the text container panel
		TextContainer.add(passwordLabel);
		TextContainer.add(password);
		
		//creates the login button and adds a mnemonic to it
		login = new JButton("Login");
		login.setPreferredSize(new Dimension(100,35));
		login.setMnemonic('L');	
		
		//creates a panel that holds the buttons
		buttonPane = new JPanel();
		buttonPane.add(login);
		buttonPane.setBorder(new EmptyBorder(0,250,200,250));
		
		//adds the textContainer and button pane to the main container
		container.add(TextContainer);
		container.add(buttonPane, BorderLayout.SOUTH);
		
		//adds the container panel to the main panel
		add(container);
	}
}
