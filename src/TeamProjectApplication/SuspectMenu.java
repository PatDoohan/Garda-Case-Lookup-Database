package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class SuspectMenu extends JPanel{

	//instance variables and components for creating the form, includes all the components required to build the UI
	private JLabel gardaLogo, pageTitle;
	private JPanel buttons, header, container;
	protected JButton addSuspect, editSuspect, deleteSuspect, viewSuspect, back;
	
	public SuspectMenu()
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
		
		//creates the add suspect button
		addSuspect = new JButton("Add a new suspect");
		addSuspect.setFont(new Font("Sans-Serif", 0, 25));
		
		//creates the update suspect button
		editSuspect = new JButton("Update an existing suspect");
		editSuspect.setFont(new Font("Sans-Serif", 0, 25));
		
		//creates the delete suspect button
		deleteSuspect = new JButton("Delete a suspect");
		deleteSuspect.setFont(new Font("Sans-Serif", 0, 25));
		
		//creates the view suspect button
		viewSuspect = new JButton("View an existing suspect");
		viewSuspect.setFont(new Font("Sans-Serif", 0, 25));
		
		//creates the return to evidence menu button
		back = new JButton("Return to Evidence Menu");
		back.setFont(new Font("Sans-Serif", 0, 25));
		
		//adds the above buttons the panel
		buttons.add(addSuspect);
		buttons.add(editSuspect);
		buttons.add(deleteSuspect);
		buttons.add(viewSuspect);
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