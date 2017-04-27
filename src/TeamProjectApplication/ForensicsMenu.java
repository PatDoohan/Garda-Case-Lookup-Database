package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ForensicsMenu extends JPanel{


	//instance variables and components for creating the form, includes all the components required to build the UI
	private JLabel gardaLogo, pageTitle, MenuLabel;
	private JPanel buttons, header, container;
	//these are made public as they are used by the window builder
	protected JButton addForensics, editForensics, deleteForensics, viewForensics, back;
	
	
	public ForensicsMenu(boolean validation)
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
		
		//Creates the menu label
		MenuLabel = new JLabel("Forensics Menu", SwingConstants.CENTER);
		MenuLabel.setFont(new Font("Sans-Serif", 0, 30));
		buttons.add(MenuLabel);
		
		//Creates the add a new forensic file button 
		addForensics = new JButton("Add Report");
		addForensics.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(addForensics);
		
		if(validation== true)
		{
			//Creates the edit a forensic file button 
			editForensics = new JButton("Update Report");
			editForensics.setFont(new Font("Sans-Serif", 0, 25));
			buttons.add(editForensics);
		}
		
		//Creates the view a forensic file button 
		viewForensics = new JButton("View Report");
		viewForensics.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(viewForensics);
		
		//Creates the back button
		back = new JButton("Return to Evidence Menu");
		back.setFont(new Font("Sans-Serif", 0, 25));
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
	

	public void validate(Boolean Admin)
	{
		if(Admin)
		{
			editForensics.setVisible(true);
		}
	}
}
