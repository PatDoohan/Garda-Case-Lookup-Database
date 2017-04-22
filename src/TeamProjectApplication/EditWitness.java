package TeamProjectApplication;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class EditWitness extends JPanel implements ActionListener{

	//instance variables and components to build the GUI
	JTextField pps, name, address, contactInfo;
	JLabel ppsLabel, nameLabel, addressLabel, contactLabel;
	protected JButton submit, cancel, caseSubmit, caseClear;
	private JPanel container, form, buttons, caseSelection;
	//instance of witness class for database manipulation
	Witness witnessIn = new Witness();
	
	//main class for EditWitness that builds the Panel
	public EditWitness()
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
		caseSelection = new JPanel();
		caseSelection.setLayout(new GridLayout(0,4,5,5));
		caseSelection.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		//creates the panel, text field and buttons for the file selection panel.
		pps = new JTextField();
		ppsLabel = new JLabel("Witness PPS Number to edit:");
		caseSubmit = new JButton("Submit");
		caseClear = new JButton("Clear");
		
		//adds functionality to the submit button
		caseSubmit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				//sets the suspectID field to uneditable to stop it being accidently changed while in use.
				pps.setEditable(false);
				
				//sets the textfields to the appropriate values retrieved from the database.
				int returnWitness = witnessIn.getWitness(pps.getText());
				
				if(returnWitness == 0)
				{
					JOptionPane.showMessageDialog(null,"Witness Not Found, Please Enter a valid Witness pps", "Witness Not Found",  JOptionPane.ERROR_MESSAGE);
					pps.setEditable(true);
				}
				
				else
				{
					name.setText(witnessIn.getName());
					address.setText(witnessIn.getAddress());
					contactInfo.setText(witnessIn.getContactInfo());
				}
				
			}
		});
		
		//functionality for the clear button that clears all the fields and sets them to defaults
		caseClear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//calls the clearfields method to clear all the information
				clearfields();
				
			}
		});
		
		//adds the label, text field and buttons to the caseSelection panel
		caseSelection.add(ppsLabel);
		caseSelection.add(pps);
		caseSelection.add(caseSubmit);
		caseSelection.add(caseClear);
		
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
		
		//adds the container and case selection to the panel
		add(caseSelection,BorderLayout.NORTH);
		add(container, BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		/*creates a string of all the information entered to be used in the confirmation panel
		 * This is done to allow the user and overview of the information entered so they can review and
		 * see if the whole thing is correct*/
		String information = "Witness PPS: " + pps.getText() + "\nWitness Name: " + name.getText() + "\nWitness Address: "
		+ address.getText() + "\nContact Info: " + contactInfo.getText();
		
		/* this is the confirmation dialog box that prints the above string in a popup box that gives the user a chance to confirm or cancel
		 * the adding of the new vehicle */
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to add the follwoing Information?\n" + information);
		
		//if the user confirms they want to add the information the following code is executed
		if(confirmation == 0)
		{
			//uses the methods in set methods in the witness class to assign the values to variables
			witnessIn.setName(name.getText());
			witnessIn.setAddress(address.getText());
			witnessIn.setContactInfo(contactInfo.getText());
			
			//calls the method to update the data in the database.
			witnessIn.UpdateWitness(pps.getText());
			JOptionPane.showMessageDialog(null, "Witness Sucessfully Updated", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
			
			//calls the clear fields method to reset the fields to default for the next entry
			clearfields();
		}
		
	}
	
	//method for clearing all information from fields after successful add or cancellation of add
	public void clearfields()
	{
		//sets all the fields to blank
		pps.setEditable(true);
		pps.setText(null);
		name.setText(null);
		address.setText(null);
		contactInfo.setText(null);
	}

}
