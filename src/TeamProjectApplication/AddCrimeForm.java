package TeamProjectApplication;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCrimeForm extends JPanel implements ActionListener{
	
	//instance variables and components to build the GUI
	JTextField crimeCode, crimeName, crimeDescription;
	JLabel crimeCodeLabel, crimeNameLabel, crimeDescriptionLabel;
	protected JButton submit, cancel;
	private JPanel container, form, buttons;
	//instance of witness class for database manipulation
	Crime crimeIn = new Crime();
	
	//main class for AddWitnessForm that builds the Panel
	public AddCrimeForm()
	{
		//sets the layout to border layout as the default layout for a jpanel is flow
		this.setLayout(new BorderLayout());
		
		/* Creates the form panel that will hold all the labels and text fields for the form.
		 * It is given a grid layout so that each row will be a different section of the form e.g 
		 * what the name is and on the next line the Description of the crime etc.*/
		form = new JPanel();
		form.setLayout(new GridLayout(0,2,5,5));
		form.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		//this is the label and text field for the Crime code number
		crimeCode = new JTextField();
		crimeCodeLabel = new JLabel("Crime Code:");
		form.add(crimeCodeLabel);
		form.add(crimeCode);
		
		//this is the label and text field for the crime name
		crimeName = new JTextField();
		crimeNameLabel = new JLabel("Crime Name:");
		form.add(crimeNameLabel);
		form.add(crimeName);
		
		//this is the label and text field for the Crime Description
		crimeDescription = new JTextField();
		crimeDescriptionLabel = new JLabel("Crime Description:");
		form.add(crimeDescriptionLabel);
		form.add(crimeDescription);
		
		/* these are the buttons to cancel and sumbit and the panel that holds them
		 * Submit uses the inner class listener to perform a function
		 * and the cancel button is used by the window handler class to
		 * return to the previous menu*/		
		buttons = new JPanel();
		submit = new JButton("Submit");
		submit.addActionListener(this);
		cancel = new JButton("Cancel");
		buttons.add(submit);
		buttons.add(cancel);
		
		//this is the container that holds both the button and form panel.
		container = new JPanel();
		container.setLayout(new GridLayout(0,1));
		container.setBorder(new EmptyBorder(200, 0, 0, 0));
		container.add(form);
		container.add(buttons);
		
		//adds the container to the main panel.
		add(container, BorderLayout.CENTER);
	}

	//this is the action event that happens upon pressing the submit button
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		/*creates a string of all the information entered to be used in the confirmation panel
		 * This is done to allow the user and overview of the information entered so they can review and
		 * see if the whole thing is correct*/
		String information = "Crime Code: " + crimeCode.getText() + "\nCrime Name: " + crimeName.getText() + "\nCrime Description: "
		+ crimeDescription.getText();
		
		/* this is the confirmation dialog box that prints the above string in a popup box that gives the user a chance to confirm or cancel
		 * the adding of the new vehicle */
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to Add the follwoing Information?\n" + information);
		//if the user confirms they want to add the information the following code is executed
		if(confirmation == 0)
		{
			//uses the methods in set methods in the crime class to assign the values to variables
			crimeIn.setCrimeCode(Integer.valueOf(crimeCode.getText()));
			crimeIn.setCrimeName(crimeName.getText());
			crimeIn.setCrimeDescription(crimeDescription.getText());
			
			//calls the method to add the data to the database.
			crimeIn.addToDatabase();
			
			//calls the clear fields method to reset the fields to default for the next entry
			clearfields();
			
		}
	}	
	
	//method for clearing all information from fields after successful add or cancellation of add
	public void clearfields()
	{
		//sets all the fields to blank
		crimeCode.setText(null);
		crimeName.setText(null);
		crimeDescription.setText(null);
	}

}
