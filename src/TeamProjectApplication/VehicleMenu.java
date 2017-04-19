package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VehicleMenu extends JPanel{

	//instance variables and components for creating the form, includes all the components required to build the UI
	private JLabel gardaLogo, pageTitle;
	private JPanel buttons, header, container;
	protected JButton addVehicle, editVehicle, deleteVehicle, viewVehicle, back;
	
	public VehicleMenu(boolean validation)
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
		
		//creates the add vehicle button
		addVehicle = new JButton("Add a new Vehicle");
		addVehicle.setFont(new Font("Sans-Serif", 0, 25));
		
		if(validation == true)
		{
			//creates the edit vehicle button
			editVehicle = new JButton("Update an existing Vehicle");
			editVehicle.setFont(new Font("Sans-Serif", 0, 25));
			buttons.add(editVehicle);
		}
		
		//creates the view vehicle button
		viewVehicle = new JButton("View an existing Vehicle");
		viewVehicle.setFont(new Font("Sans-Serif", 0, 25));
		
		//creates the back button
		back = new JButton("Return to Evidence Menu");
		back.setFont(new Font("Sans-Serif", 0, 25));
		
		//adds the above buttons the panel
		buttons.add(addVehicle);

		buttons.add(viewVehicle);
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
			editVehicle.setVisible(true);
		}
	}
}
