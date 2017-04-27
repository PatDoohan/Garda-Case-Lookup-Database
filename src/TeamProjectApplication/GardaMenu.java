package TeamProjectApplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GardaMenu extends JPanel{

	protected JButton addGarda, editGarda, assignGarda, back;
	protected JLabel gardaLogo, pageTitle, MenuLabel;
	protected JPanel buttons, header, container;
	
	public GardaMenu()
	{
		//creates the header panel that holds the garda logo and label
		header = new JPanel();
		header.setBorder(new EmptyBorder(0,0,120,0));
		
		//creates the garda logo and aligns it to the left
		gardaLogo = new JLabel(new ImageIcon("Images/gardaLogo.png"));
		//gardaLogo.setAlignmentY(LEFT_ALIGNMENT);
		
		//creates the label and aligns it to the left
		pageTitle = new JLabel("Garda Case Tracking Database");
		//pageTitle.setAlignmentY(LEFT_ALIGNMENT);
		pageTitle.setFont(new Font("Sans-Serif",0, 40));
		
		//adds the image and label to the header
		header.add(gardaLogo);
		header.add(pageTitle);
		
		//creates the panel that holds the buttons
		buttons = new JPanel();
		buttons.setLayout(new GridLayout (0,1, 20,20));
		
		//Creates the menu label
		MenuLabel = new JLabel("Garda Menu", SwingConstants.CENTER);
		MenuLabel.setFont(new Font("Sans-Serif", 0, 30));
		buttons.add(MenuLabel);
		
		//creates the add suspect button
		addGarda = new JButton("Add");
		addGarda.setFont(new Font("Sans-Serif", 0, 25));
		
		//creates the update suspect button
		editGarda = new JButton("Update");
		editGarda.setFont(new Font("Sans-Serif", 0, 25));
		
		//creates the view suspect button
		assignGarda = new JButton("Assign To Case");
		assignGarda.setFont(new Font("Sans-Serif", 0, 25));
		
		//creates the return to evidence menu button
		back = new JButton("Return to Evidence Menu");
		back.setFont(new Font("Sans-Serif", 0, 25));
		
		//adds the above buttons the panel
		buttons.add(addGarda);
		buttons.add(editGarda);
		buttons.add(assignGarda);
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