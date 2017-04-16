package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class EvidenceMenu extends JPanel {
	
	//instance variables and components for creating the form, includes all the components required to build the UI
	private JLabel gardaLogo, pageTitle;
	private JPanel buttons, header, container;
	//these are made public as they are used by the window builders
	protected JButton witness, vehicle, forensics, suspect, back;
	
	public EvidenceMenu()
	{
		//creates the header panel that holds the garda logo and label
		header = new JPanel();
		header.setBorder(new EmptyBorder(0,0,120,0));
		
		//creates the garda logo and aligns it to the left
		gardaLogo = new JLabel(new ImageIcon("Images/gardaLogo.png"));
		gardaLogo.setAlignmentY(LEFT_ALIGNMENT);
		
		//creates the label and aligns it to the left
		pageTitle = new JLabel("Garda Case Tracking Database");
		pageTitle.setAlignmentY(LEFT_ALIGNMENT);
		pageTitle.setFont(new Font("Sans-Serif",0, 40));
		
		//adds the image and label to the header
		header.add(gardaLogo);
		header.add(pageTitle);
		
		//creates the panel that holds the buttons
		buttons = new JPanel();
		buttons.setLayout(new GridLayout (0,1, 20,20));
		
		//Creates the suspect menu button
		suspect = new JButton("Suspect Menu");
		suspect.setFont(new Font("Sans-Serif", 0, 25));
		
		//Creates the Witness menu button 
		witness = new JButton("Witness Menu");
		witness.setFont(new Font("Sans-Serif", 0, 25));
		
		//Creates the Vehicle menu button 
		vehicle = new JButton("Vehicle Menu");
		vehicle.setFont(new Font("Sans-Serif", 0, 25));
		
		//Creates the forensics menu button 
		forensics = new JButton("Forensics Menu");
		forensics.setFont(new Font("Sans-Serif", 0, 25));
		
		//Creates the back button 
		back = new JButton("Return to Main Menu");
		back.setFont(new Font("Sans-Serif", 0, 25));
		
		//adds the buttons to the button panel
		buttons.add(suspect);
		buttons.add(witness);
		buttons.add(vehicle);
		buttons.add(forensics);
		buttons.add(back);
		
		//creates the container that will hold the header and button panels
		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(new EmptyBorder(20,20,20,20));
		
		//adds the header and buttons to the panel
		container.add(header, BorderLayout.NORTH);
		container.add(buttons, BorderLayout.SOUTH);
		
		//adds the container to the main panel
		add(container);	
		
	}

}
