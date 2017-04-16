package TeamProjectApplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GardaMenu extends JPanel{
	
	protected JButton caseLookup, EvidenceMenu, suspectLookup, vehicleLookup, mapLookup, CrimeStatistics, Logout;
	protected JLabel gardaLogo, pageTitle, menulbl;
	protected JPanel buttons, header, container, menu;
	
	
	public GardaMenu()
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
		buttons.setLayout(new GridLayout (0,1, 20, 20));
		
		//Creates the case menu button and adds it to the button panel
		caseLookup = new JButton("Case Menu");
		caseLookup.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(caseLookup);
		
		//Creates the Evidence menu button and adds it to the button panel
		EvidenceMenu = new JButton("Evidence Menu");
		EvidenceMenu.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(EvidenceMenu);
		
		//Creates the map menu button and adds it to the button panel
		mapLookup = new JButton("Map Menu");

		mapLookup.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(mapLookup);
		
		//Creates the crime statistics button and adds it to the button panel
		CrimeStatistics = new JButton("Crime Statistics");
		CrimeStatistics.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(CrimeStatistics);
		
		//Creates the logout button and adds it to the button panel
		Logout = new JButton("Logout");
		Logout.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(Logout);
		
		//creates the container that will hold the header and button panels
		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(new EmptyBorder(20,20,20,20));
		

		//adds the header and buttons to the panel
		container.add(header, BorderLayout.CENTER);
		container.add(buttons, BorderLayout.SOUTH);
    
		//adds the container to the main panel
		add(container,BorderLayout.CENTER);
	}
}
