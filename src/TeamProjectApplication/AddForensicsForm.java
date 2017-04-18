package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class AddForensicsForm extends JPanel implements ActionListener {
	
	//instance variables and components for creating the form, includes all the components required to build the UI
	JLabel forensicLabel, bioLabel, printsLabel, tracksLabel, digitalLabel, toolMarkLabel, narcoticLabel, firearmLabel, evidenceLabel;
	JTextField evidenceID, forensicID;
	String[] confirmation = {"Not Present","Present"};
	JComboBox bioBox, printsBox, tracksBox, digitalBox, toolMarkBox, narcoticBox, firearmBox;
	JButton submit, cancel;
	JPanel container, form, buttons;
	//create instance of forensic to insert the data into the database.
	Forensics f1 = new Forensics();
	
	//main class file for AddForensicForm that builds a UI and implements the listener class
	public AddForensicsForm()
	{
		//sets the layout to border layout as the default layout for a jpanel is flow.
		this.setLayout(new BorderLayout());
		
		/* Creates the form panel that will hold all the labels and text fields for the form.
		 * It is given a grid layout so that each row will be a different section of the form e.g 
		 * what case it is a part of then on the next line the ID number of the forensic file etc.*/
		form = new JPanel();
		form.setLayout(new GridLayout(0,2,5,5));
		form.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		/* this is the label and textfield that is used to link the forensic file to the case, this has to be
		 * done manually as new forensic files could be added for older cases as well as newer and there is no
		 * way to predetermine what case the file is for. */
		evidenceLabel = new JLabel("Forensic File for case: ");
		evidenceID = new JTextField();
		form.add(evidenceLabel);
		form.add(evidenceID);
		
		/* This is the label and text field that holds the information for the ID of the forensics file
		 * as this is unique and assigned automatically the field is set to uneditable by default
		 * the assignID method in the forensic class is used to assign a unique ID to the forensic file*/
		forensicLabel = new JLabel("Forensics ID: ");
		forensicID = new JTextField();
		forensicID.setEditable(false);
		f1.assignID();
		forensicID.setText(String.valueOf(f1.getForensicID()));
		form.add(forensicLabel);
		form.add(forensicID);
		
		/* This is the label and combobox for biological evidence, it uses the confirmation
		 * array to give it the values for the combobox*/
		bioLabel = new JLabel("Presence of Biological Evidence:");
		bioBox = new JComboBox(confirmation);
		form.add(bioLabel);
		form.add(bioBox);
		
		/* This is the label and combobox for print evidence e.g finger prints and toe prints, it uses the confirmation
		 * array to give it the values for the combobox*/
		printsLabel = new JLabel("Presence of Finger Prints:");
		printsBox = new JComboBox(confirmation);
		form.add(printsLabel);
		form.add(printsBox);
		
		/* This is the label and combobox for Track evidence e.g boot and tire, it uses the confirmation
		 * array to give it the values for the combobox*/
		tracksLabel = new JLabel("Presence of Vehicle/Foot Tracks:");
		tracksBox = new JComboBox(confirmation);
		form.add(tracksLabel);
		form.add(tracksBox);
		
		/* This is the label and combobox for digital evidence e.g phones and tablets, it uses the confirmation
		 * array to give it the values for the combobox*/
		digitalLabel = new JLabel("Presence of Digital Evidence: ");
		digitalBox = new JComboBox(confirmation);
		form.add(digitalLabel);
		form.add(digitalBox);
		
		/* This is the label and combobox for tool mark evidence, it uses the confirmation
		 * array to give it the values for the combobox*/
		toolMarkLabel = new JLabel("Presence of tool marks");
		toolMarkBox = new JComboBox(confirmation);
		form.add(toolMarkLabel);
		form.add(toolMarkBox);
				
		/* This is the label and combobox for narcotic evidence, it uses the confirmation
		 * array to give it the values for the combobox*/
		narcoticLabel = new JLabel("Presence of Narcotics: ");
		narcoticBox = new JComboBox(confirmation);
		form.add(narcoticLabel);
		form.add(narcoticBox);
		
		/* This is the label and combobox for firearm evidence, it uses the confirmation
		 * array to give it the values for the combobox*/
		firearmLabel = new JLabel("Presence of Firearms: ");
		firearmBox = new JComboBox(confirmation);
		form.add(firearmLabel);
		form.add(firearmBox);
		
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
	public void actionPerformed(ActionEvent arg0) 
	{
		/*creates a string of all the information entered to be used in the confirmation panel
		 * This is done to allow the user and overview of the information entered so they can review and
		 * see if the whole thing is correct*/
		String suspectInformation = "Forensic ID: " + forensicID.getText() + "\nBiological Evidence: " + bioBox.getSelectedItem() + "\nFinger Prints: " + printsBox.getSelectedItem()
		+ "\nTrack Evidence: " + tracksBox.getSelectedItem() + "\nDigital Evidence: " + digitalBox.getSelectedItem() + "\nTool Mark Evidence: " + toolMarkBox.getSelectedItem() 
		+ "\nNarcotic Evidence: " + narcoticBox.getSelectedItem()
		+ "\nFirearm Evidence: " + firearmBox.getSelectedItem();
		
		/* this is the confirmation dialog box that prints the above string in a popup box that gives the user a chance to confirm or cancel
		 * the adding of the new case */
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to add the follwoing Information?\n" + suspectInformation);
		//if the user confirms they want to add the information the following code is executed
		if(confirmation == 0)
		{
			//All the information is retrieved from the combobox and inserted into the forensic class using the set methods
			f1.setBiologicalEvidence(String.valueOf(bioBox.getSelectedItem()));
			f1.setPrintEvidence(String.valueOf(printsBox.getSelectedItem()));
			f1.setTrackEvidence(String.valueOf(tracksBox.getSelectedItem()));
			f1.setDigitalEvidence(String.valueOf(digitalBox.getSelectedItem()));
			f1.setToolMarkEvidence(String.valueOf(toolMarkBox.getSelectedItem()));
			f1.setNarcoticEvidence(String.valueOf(narcoticBox.getSelectedItem()));
			f1.setFirearmEvidence(String.valueOf(firearmBox.getSelectedItem()));
			
			/*a link is made to the case specified by the user, this is used to pull the information
			 *into the case overview*/
			int returnCase = f1.linkToCase(Integer.valueOf(evidenceID.getText()));
			
			if(returnCase == 0)
			{
				JOptionPane.showMessageDialog(null,"Case Not Found, Please Enter a case ID and try again", "Case Not Found",  JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				//calls the method to add the data to the database.
				f1.addToDatabase();
				
				//calls the clearfields method to reset the fields to default for the next entry
				clearfields();
			}
		
		}
	}
	
	//method for clearing all information from fields after succesfull add or cancellation of add
	public void clearfields()
	{
		/* assigns a new ID, if a new record is added this will increment the old one
		 * if no change has occured it will just reassign the current one*/
		f1.assignID();
		//sets the text in the case link text field to null
		evidenceID.setText(null);
		//sets the value of the forensic id to the new value if changed or same value if not
		forensicID.setText(String.valueOf(f1.getForensicID()));
		
		/*
		 * The following code uses unique models to set the comboboxes back to default values
		 * this could not be achieved using a single model as all the boxes would change at the sme time
		 * if a single model was used*/
		DefaultComboBoxModel model = new DefaultComboBoxModel(confirmation);
		bioBox.setModel(model);
		DefaultComboBoxModel model1 = new DefaultComboBoxModel(confirmation);
		printsBox.setModel(model1);
		DefaultComboBoxModel model2 = new DefaultComboBoxModel(confirmation);
		tracksBox.setModel(model2);
		DefaultComboBoxModel model3 = new DefaultComboBoxModel(confirmation);
		digitalBox.setModel(model3);
		DefaultComboBoxModel model4 = new DefaultComboBoxModel(confirmation);
		toolMarkBox.setModel(model4);
		DefaultComboBoxModel model5 = new DefaultComboBoxModel(confirmation);
		narcoticBox.setModel(model5);
		DefaultComboBoxModel model6 = new DefaultComboBoxModel(confirmation);
		firearmBox.setModel(model6);
		
	}
}
