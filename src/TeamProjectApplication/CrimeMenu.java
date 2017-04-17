package TeamProjectApplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CrimeMenu extends JPanel{


	protected JButton addCrime, editCrime, deleteCrime, viewCrime, back;
	protected JLabel gardaLogo, pageTitle;
	protected JPanel buttons, header, container;
	
	
	public CrimeMenu()
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
		
		//Creates the add crime button and adds it to the button panel
		addCrime = new JButton("Add Crime");
		addCrime.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(addCrime);
		
		//Creates the edit crime button and adds it to the button panel
		editCrime = new JButton("Update Crime");
		editCrime.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(editCrime);
		
		//Creates the view crime button and adds it to the button panel
		editCrime = new JButton("Update Crime");
		editCrime.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(editCrime);
		
		//Creates the deletecrime button and adds it to the button panel
		deleteCrime = new JButton("Delete Crime");
		deleteCrime.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(deleteCrime);
		
		//Creates the return to main menu button and adds it to the button panel
		back = new JButton("Return to Main Menu");
		back.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(back);
		
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
	
//	public static void main (String [] args)
//	{
//		CrimeMenu frame = new CrimeMenu();
//	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    frame.setTitle("Garda Case Tracking Database");
//	    frame.setSize(1200,1000);
//	    frame.setLocationRelativeTo(null);
//	    frame.setVisible(true);
//	}

}
