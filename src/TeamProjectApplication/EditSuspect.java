package TeamProjectApplication;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class EditSuspect extends JPanel implements ActionListener {

	//instance variables and components to build the GUI
	private JTextField SuspectID, pps, name, address, description, priorConvictions;
	private JLabel SuspectIDLabel, ppsLabel, nameLabel, addressLabel, descriptionLabel, priorConvictionsLabel, statusLabel;
	private String[] status = {"Suspect", "Fugitive", "Arrested", "Processed"};
	private String[] defaultstatus = {"Suspect", "Fugitive", "Arrested", "Processed"};
	private JComboBox setStatus;
	protected JButton submit, cancel, caseSubmit, caseClear;
	private JPanel container, form, buttons, caseSelection;
	Suspect suspectIn = new Suspect();
	
	//main class for EditSuspect that builds the Panel
	public EditSuspect()
	{
		//sets the layout to border layout as the default layout for a jpanel is flow.
		this.setLayout(new BorderLayout());
		
		/* Creates the form panel that will hold all the labels and text fields for the form.
		 * It is given a grid layout so that each row will be a different section of the form e.g 
		 * what case it is a part of then on the next line the ID number of the suspect file etc.*/
		form = new JPanel();
		form.setLayout(new GridLayout(0,2,5,5));
		form.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		/* Creates the panel that will hold the panels and buttons for the suspect file selection
		 * the layout is set to allow all the items in the panel to exist on the same row */
		caseSelection = new JPanel();
		caseSelection.setLayout(new GridLayout(0,4,5,5));
		caseSelection.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
	
		//creates the panel, text field and buttons for the file selection panel.
		SuspectIDLabel = new JLabel("Suspect ID To Update: ");
		SuspectID = new JTextField();
		caseSubmit = new JButton("Submit");
		caseClear = new JButton("Clear");
		
		//adds functionality to the submit button
		caseSubmit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//sets the suspectID field to uneditable to stop it being accidently changed while in use.
				SuspectID.setEditable(false);
				
				//uses the method in the suspect class to retrieve the information from the database and assign it to the variables in class
				suspectIn.getSuspect(Integer.valueOf(SuspectID.getText()));
				
				//sets the textfields to the appropriate values retrieved from the database.
				pps.setText(suspectIn.getSuspectPPS());
				name.setText(suspectIn.getName());
				address.setText(suspectIn.getAddress());
				priorConvictions.setText(suspectIn.getSuspectPriors());
				description.setText(suspectIn.getSuspectDescription());
				
				//checks to see what the default status is from the available array and sets it to default using a model
				for (int i = 0; i < status.length; i++)
				{
					//if statement that checks what the status is equal to in the array, then it swaps that to the first slot in the array
					if(status[i].equals(suspectIn.getSuspectStatus()))
					{
						String temp = status[0];
						status[0] = status [i];
						status[i] = temp;
					}
				}
				//the new array is then turned into a model and that model is set as the default for the status Combobox
				DefaultComboBoxModel model = new DefaultComboBoxModel(status);
				setStatus.setModel(model);
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
		caseSelection.add(SuspectIDLabel);
		caseSelection.add(SuspectID);
		caseSelection.add(caseSubmit);
		caseSelection.add(caseClear);
		
		//this is the label and textfield for the suspect pps number if known
		ppsLabel = new JLabel("Suspect PPS Number:");
		pps = new JTextField();
		form.add(ppsLabel);
		form.add(pps);
		
		//this is the label and textfield for the suspect name if known
		nameLabel = new JLabel("Suspect Name:");
		name = new JTextField();
		form.add(nameLabel);
		form.add(name);
		
		//this is the label and textfield for the suspect address if known
		addressLabel = new JLabel("Suspect Address:");
		address = new JTextField();
		form.add(addressLabel);
		form.add(address);
		
		//this is the label and textfield for the suspect description
		descriptionLabel = new JLabel("Suspect Description:");
		description = new JTextField();
		form.add(descriptionLabel);
		form.add(description);
		
		//this is the label and textfield for the suspects priors if known
		this.priorConvictionsLabel = new JLabel("Prior Convictions:");
		this.priorConvictions = new JTextField();
		form.add(priorConvictionsLabel);
		form.add(priorConvictions);
		
		//this is the label and combobox for the current status of the suspect
		this.statusLabel = new JLabel("Current Status:");
		this.setStatus = new JComboBox(status);
		form.add(statusLabel);
		form.add(setStatus);
				
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

	//this is the action event that happens upon pressing the submit button
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{		
		/*creates a string of all the information entered to be used in the confirmation panel
		 * This is done to allow the user and overview of the information entered so they can review and
		 * see if the whole thing is correct*/
		String suspectInformation = "Suspect ID: " + SuspectID.getText() + "\nSuspect PPS: " + pps.getText() + "\nName: " + name.getText()
		+ "\nAddress: " + address.getText() + "\nDescription: " + description.getText() + "\nPriorConvictions: " + priorConvictions.getText()
		+ "\nCurrent Status: " + setStatus.getSelectedItem();
		
		/* this is the confirmation dialog box that prints the above string in a popup box that gives the user a chance to confirm or cancel
		 * the adding of the new case */
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to update the follwoing Information?\n" + suspectInformation);
		
		//if the user confirms they want to add the information the following code is executed
		if(confirmation == 0)
		{
			//All the information is retrieved from the text fields and inserted into the suspect class using the set methods
			suspectIn.setPPS(pps.getText());
			suspectIn.setName(name.getText());
			suspectIn.setAddress(address.getText());
			suspectIn.setSuspectDescription(description.getText());
			suspectIn.setSuspectPriors(priorConvictions.getText());
			suspectIn.setSuspectStatus(setStatus.getSelectedItem());	
			
			//calls the method to add the updated data to the database.
			suspectIn.updateSuspect();
			
			//calls the clearfields method to reset the fields to default for the next entry
			clearfields();
		}
	}
	
	//method for clearing all information from fields after successful edit or cancellation of edit
	public void clearfields()
	{
		//resets the forensicID field to blank and sets it as editable again.
		SuspectID.setEditable(true);
		SuspectID.setText(null);
		
		//sets all the text fields to blank
		pps.setText(null);
		name.setText(null);
		address.setText(null);
		description.setText(null);
		priorConvictions.setText(null);
		SuspectID.setText(null);
		
		//uses a model a to set the combobox back to the default values with suspect being the default
		DefaultComboBoxModel model = new DefaultComboBoxModel(defaultstatus);
		setStatus.setModel(model);
	}

}
