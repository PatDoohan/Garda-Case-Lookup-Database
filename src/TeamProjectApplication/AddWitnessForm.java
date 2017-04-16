package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class AddWitnessForm extends JPanel implements ActionListener{

	//instance variables and components to build the GUI
	JTextField pps, name, address, contactInfo, caseIn;
	JLabel ppsLabel, nameLabel, addressLabel, contactLabel, caseLabel;
	protected JButton submit, cancel;
	private JPanel container, form, buttons;
	//instance of witness class for database manipulation
	Witness witnessIn = new Witness();
	
	//main class for AddWitnessForm that builds the Panel
	public AddWitnessForm()
	{
		//sets the layout to border layout as the default layout for a jpanel is flow
		this.setLayout(new BorderLayout());
		
		/* Creates the form panel that will hold all the labels and text fields for the form.
		 * It is given a grid layout so that each row will be a different section of the form e.g 
		 * what case it is a part of then on the next line the ID number of the witness file etc.*/
		form = new JPanel();
		form.setLayout(new GridLayout(0,2,5,5));
		form.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		/* this is the label and text field that is used to link the witness file to the case, this has to be
		 * done manually as new witness files could be added for older cases as well as newer and there is no
		 * way to predetermine what case the file is for. */
		caseLabel = new JLabel("Witness in Case:");
		caseIn = new JTextField();
		form.add(caseLabel);
		form.add(caseIn);
		
		//this is the label and text field for the witness PPS number
		pps = new JTextField();
		ppsLabel = new JLabel("Witness PPS Number:");
		form.add(ppsLabel);
		form.add(pps);
		
		//this is the label and text field for the witness name
		name = new JTextField();
		nameLabel = new JLabel("Witness Name:");
		form.add(nameLabel);
		form.add(name);
		
		//this is the label and text field for the witness address
		address = new JTextField();
		addressLabel = new JLabel("Witness Address:");
		form.add(addressLabel);
		form.add(address);
		
		//this is the label and text field for the witness contact information e.g. email/phone
		contactInfo = new JTextField();
		contactLabel = new JLabel("Witness Contact Information:");
		form.add(contactLabel);
		form.add(contactInfo);
		
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
		String information = "Witness PPS: " + pps.getText() + "\nWitness Name: " + name.getText() + "\nWitness Address: "
		+ address.getText() + "\nContact Info: " + contactInfo.getText() + "\nPart of case: " + caseIn.getText();
		
		/* this is the confirmation dialog box that prints the above string in a popup box that gives the user a chance to confirm or cancel
		 * the adding of the new vehicle */
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to Update the follwoing Information?\n" + information);
		//if the user confirms they want to add the information the following code is executed
		if(confirmation == 0)
		{
			//uses the methods in set methods in the witness class to assign the values to variables
			witnessIn.setPPs(pps.getText());
			witnessIn.setName(name.getText());
			witnessIn.setAddress(address.getText());
			witnessIn.setContactInfo(contactInfo.getText());
			
			/*a link is made to the case specified by the user, this is used to pull the information
			 *into the case overview*/
			witnessIn.linkToCase(Integer.valueOf(caseIn.getText()));
			
			//calls the method to add the data to the database.
			witnessIn.addToDatabase();
			
			//calls the clear fields method to reset the fields to default for the next entry
			clearfields();
			
		}
	}
		
	//method for clearing all information from fields after successful add or cancellation of add
	public void clearfields()
	{
		//sets all the fields to blank
		pps.setText(null);
		name.setText(null);
		address.setText(null);
		contactInfo.setText(null);
		caseIn.setText(null);
	}

}
