package TeamProjectApplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CaseMenu extends JPanel{
	
	//instance variables and components for creating the form, includes all the components required to build the UI
	private JLabel gardaLogo, pageTitle;
	//these are made public as they are used by the window builder
	protected JButton createCase, updateCase, deleteCase, viewCase ,back;
	private JPanel buttons, header, container;
	
	public CaseMenu(boolean validation)
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
		
		//Creates the add a new case button and adds it to the button panel
		createCase  = new JButton("Create a new case");
		createCase.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(createCase);
		
		if(validation == true)
		{
			//creates the edit case button and adds it to the button panel
			updateCase = new JButton("Update an existing case");
			updateCase.setFont(new Font("Sans-Serif", 0, 25));
			buttons.add(updateCase);
		}
		
		//creates the view case button and adds it to the button panel
		viewCase = new JButton("View an Existing Case");
		viewCase.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(viewCase);
		
		//creates  the return button and adds it to the button panel
		back = new JButton("Return to main menu");
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
}
