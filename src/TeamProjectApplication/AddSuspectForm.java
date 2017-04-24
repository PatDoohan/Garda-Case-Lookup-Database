package TeamProjectApplication;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.AbstractDocument;

public class AddSuspectForm extends JPanel implements ActionListener{
	
	//instance variables and components to build the GUI
	private JFormattedTextField SuspectID, pps, name, address, description, priorConvictions, caseNumber;
	private JLabel SuspectIDLabel, ppsLabel, nameLabel, addressLabel, descriptionLabel, priorConvictionsLabel, statusLabel, caseNumberLabel;
	private String[] status = {"Suspect", "Fugitive", "Arrested", "Processed"};
	private JComboBox setStatus;
	protected JButton submit, cancel;
	private JPanel container, form, buttons;
	//instance of suspect class for data manipulation
	Suspect suspectIn = new Suspect();
	
	//main class for AddSuspectForm that builds the Panel
	public AddSuspectForm()
	{
		//sets the layout to border layout as the default layout for a jpanel is flow.
		this.setLayout(new BorderLayout());
		
		/* Creates the form panel that will hold all the labels and text fields for the form.
		 * It is given a grid layout so that each row will be a different section of the form e.g 
		 * what case it is a part of then on the next line the ID number of the suspect file etc.*/
		form = new JPanel();
		form.setLayout(new GridLayout(0,2,5,5));
		form.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		/* this is the label and textfield that is used to link the suspect file to the case, this has to be
		 * done manually as new suspect files could be added for older cases as well as newer and there is no
		 * way to predetermine what case the file is for. */
		caseNumberLabel = new JLabel("Suspect in Case:");
		caseNumber = new JFormattedTextField();
		((AbstractDocument)caseNumber.getDocument()).setDocumentFilter(new LetterFilter());
		form.add(caseNumberLabel);
		form.add(caseNumber);
		
		/* This is the label and text field that holds the information for the ID of the suspect file
		 * as this is unique and assigned automatically the field is set to uneditable by default
		 * the assignID method in the suspect class is used to assign a unique ID to the suspect file*/
		SuspectIDLabel = new JLabel("Suspect ID: ");
		SuspectID = new JFormattedTextField();
		SuspectID.setEditable(false);
		suspectIn.assignID();
		SuspectID.setText(String.valueOf(suspectIn.getSuspectID()));
		form.add(SuspectIDLabel);
		form.add(SuspectID);
		
		//this is the label and textfield for the suspect pps number if known
		ppsLabel = new JLabel("Suspect PPS Number:");
		pps = new JFormattedTextField();
		((AbstractDocument)pps.getDocument()).setDocumentFilter(new SpecialCharacterFilter());
		form.add(ppsLabel);
		form.add(pps);
		
		//this is the label and textfield for the suspect name if known
		nameLabel = new JLabel("Suspect Name:");
		name = new JFormattedTextField();
		((AbstractDocument)name.getDocument()).setDocumentFilter(new NumberFilter());
		form.add(nameLabel);
		form.add(name);
		
		//this is the label and textfield for the suspect address if known
		addressLabel = new JLabel("Suspect Address:");
		address = new JFormattedTextField();
		((AbstractDocument)address.getDocument()).setDocumentFilter(new SpecialCharacterFilter());
		form.add(addressLabel);
		form.add(address);
		
		//this is the label and textfield for the suspect description
		descriptionLabel = new JLabel("Suspect Description:");
		description = new JFormattedTextField();
		((AbstractDocument)description.getDocument()).setDocumentFilter(new SpecialCharacterFilter());
		form.add(descriptionLabel);
		form.add(description);
		
		//this is the label and textfield for the suspects priors if known
		this.priorConvictionsLabel = new JLabel("Prior Convictions:");
		this.priorConvictions = new JFormattedTextField();
		((AbstractDocument)priorConvictions.getDocument()).setDocumentFilter(new SpecialCharacterFilter());
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
		
		//adds the container to the main panel.
		add(container, BorderLayout.CENTER);
			
	}
		
	//this is the action event that happens upon pressing the submit button
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		/*creates a string of all the information entered to be used in the confirmation panel
		 * This is done to allow the user and overview of the information entered so they can review and
		 * see if the whole thing is correct*/
		String suspectInformation = "Suspect ID: " + SuspectID.getText() + "\nSuspect PPS: " + pps.getText() + "\nName: " + name.getText()
		+ "\nAddress: " + address.getText() + "\nDescription: " + description.getText() + "\nPriorConvictions: " + priorConvictions.getText()
		+ "\nCurrent Status: " + setStatus.getSelectedItem();
		
		/* this is the confirmation dialog box that prints the above string in a popup box that gives the user a chance to confirm or cancel
		 * the adding of the new suspect */
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to add the follwoing Information?\n" + suspectInformation);
		
		//if the user confirms they want to add the information the following code is executed
		if(confirmation == 0)
		{
			//All the information is retrieved from the text fields and inserted into the suspect class using the set methods
			suspectIn.setSuspectPPS(pps.getText());
			suspectIn.setName(name.getText());
			suspectIn.setAddress(address.getText());
			suspectIn.setSuspectDescription(description.getText());
			suspectIn.setSuspectPriors(priorConvictions.getText());
			suspectIn.setSuspectStatus(setStatus.getSelectedItem());
			
			/*a link is made to the case specified by the user, this is used to pull the information
			 *into the case overview*/
			int returnCase = suspectIn.linkToCase(Integer.valueOf(caseNumber.getText()));	
			
			if(returnCase == 0)
			{
				JOptionPane.showMessageDialog(null,"Case Not Found, Please Enter a case ID and try again", "Case Not Found",  JOptionPane.ERROR_MESSAGE);
			}
			
			else
			{
				//calls the method to add the data to the database.
				suspectIn.addToDatabase();
				
				//calls the clearfields method to reset the fields to default for the next entry
				clearfields();
				JOptionPane.showMessageDialog(null, "Suspect Entered Sucessfully", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
	}
	
	//method for clearing all information from fields after succesfull add or cancellation of add
	public void clearfields()
	{
		/* assigns a new ID, if a new record is added this will increment the old one
		 * if no change has occured it will just reassign the current one*/
		suspectIn.assignID();
		//sets the value of the forensic id to the new value if changed or same value if not
		SuspectID.setText(String.valueOf(suspectIn.getSuspectID()));
		//sets the textfields to blank
		pps.setText(null);
		name.setText(null);
		address.setText(null);
		description.setText(null);
		priorConvictions.setText(null);
		//sets the text in the case link text field to nullz
		caseNumber.setText(null);
	}
}
