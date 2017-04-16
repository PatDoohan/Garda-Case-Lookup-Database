package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class DeleteForensics extends JPanel implements ActionListener{
	
	//instance variables and components for creating the form, includes all the components required to build the UI
	JLabel forensicLabel, bioLabel, printsLabel, tracksLabel, digitalLabel, toolMarkLabel, narcoticLabel, firearmLabel;
	JTextField forensicID;
	String[] confirmation = {"Not Present","Present"};
	JTextField bioBox, printsBox, tracksBox, digitalBox, toolMarkBox, narcoticBox, firearmBox;
	JButton submit, cancel, caseSubmit, caseClear;
	JPanel container, form, buttons, ForensicFileSelection;
	Forensics f1 = new Forensics();
	DefaultComboBoxModel model = new DefaultComboBoxModel(confirmation);

	public DeleteForensics()
	{
		//sets the layout to border layout as the default layout for a jpanel is flow.
		this.setLayout(new BorderLayout());
		
		/* Creates the form panel that will hold all the labels and text fields for the form.
		 * It is given a grid layout so that each row will be a different section of the form e.g 
		 * what case it is a part of then on the next line the ID number of the forensic file etc.*/
		form = new JPanel();
		form.setLayout(new GridLayout(0,2,5,5));
		form.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		/* Creates the panel that will hold the panels and buttons for the forensic file selection
		 * the layout is set to allow all the items in the panel to exist on the same row */
		ForensicFileSelection = new JPanel();
		ForensicFileSelection.setLayout(new GridLayout(0,4,5,5));
		ForensicFileSelection.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		//creates the panel, text field and buttons for the file selection panel.
		forensicLabel = new JLabel("Forensic File to Delete:");
		forensicID = new JTextField(null);
		caseSubmit = new JButton("Submit");
		caseClear = new JButton("Clear");
		
		//adds functionality to the submit button
		caseSubmit.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				//sets the file selection field to false so it cannot be changed by mistake
				forensicID.setEditable(false);
				//uses the method in the forensic file to retrieve the values from the database and assign them to variables
				f1.getForensicFile(Integer.valueOf(forensicID.getText()));
				
				//sets the textfields to the values from the database
				bioBox.setText(f1.getBioEvidence());
				printsBox.setText(f1.getPrints());
				tracksBox.setText(f1.getTrackEvidence());
				digitalBox.setText(f1.getDigitalEvidence());
				toolMarkBox.setText(f1.getToolMarkEvidence());
				narcoticBox.setText(f1.getToolMarkEvidence());
				firearmBox.setText(f1.getfirearmEvidence());
				
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
		
		//adds the label, text field and button to the file selection panel
		ForensicFileSelection.add(forensicLabel);
		ForensicFileSelection.add(forensicID);
		ForensicFileSelection.add(caseSubmit);
		ForensicFileSelection.add(caseClear);
		
		/* This is the label and textfield for biological evidence, it is set to uneditable as 
		 * this information is only for re-view before deletion*/
		bioLabel = new JLabel("Presence of Biological Evidence:");
		bioBox = new JTextField();
		bioBox.setEditable(false);
		form.add(bioLabel);
		form.add(bioBox);
		
		/* This is the label and textfield for print evidence e.g finger prints and toe prints, it is set to uneditable as 
		 * this information is only for re-view before deletion*/
		printsLabel = new JLabel("Presence of Finger Prints:");
		printsBox = new JTextField();
		printsBox.setEditable(false);
		form.add(printsLabel);
		form.add(printsBox);
		
		/* This is the label and textfield for Track evidence e.g boot and tire, it is set to uneditable as 
		 * this information is only for re-view before deletion*/
		tracksLabel = new JLabel("Presence of Vehicle/Foot Tracks:");
		tracksBox = new JTextField();
		tracksBox.setEditable(false);
		form.add(tracksLabel);
		form.add(tracksBox);
		
		/* This is the label and textfield for digital evidence e.g phones and tablets, it is set to uneditable as 
		 * this information is only for re-view before deletion*/
		digitalLabel = new JLabel("Presence of Digital Evidence: ");
		digitalBox = new JTextField();
		digitalBox.setEditable(false);
		form.add(digitalLabel);
		form.add(digitalBox);
		
		/* This is the label and textfield for tool mark evidence, it is set to uneditable as 
		 * this information is only for re-view before deletion*/
		toolMarkLabel = new JLabel("Presence of tool marks");
		toolMarkBox = new JTextField();
		toolMarkBox.setEditable(false);
		form.add(toolMarkLabel);
		form.add(toolMarkBox);
		
		/* This is the label and textfield for narcotic evidence, it is set to uneditable as 
		 * this information is only for re-view before deletion*/
		narcoticLabel = new JLabel("Presence of Narcotics: ");
		narcoticBox = new JTextField();
		narcoticBox.setEditable(false);
		form.add(narcoticLabel);
		form.add(narcoticBox);
		
		/* This is the label and textfield for print firearm evidence, it is set to uneditable as 
		 * this information is only for re-view before deletion*/
		firearmLabel = new JLabel("Presence of Firearms: ");
		firearmBox = new JTextField();
		firearmBox.setEditable(false);
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
		
		//adds the container and the forensicFileSelection to the main panel.
		add(ForensicFileSelection,BorderLayout.NORTH);
		add(container, BorderLayout.CENTER);
	}
	
	//method for clearing all information from fields after successful delete or cancellation of delete
	public void clearfields()
	{
		//sets the textfields to blank and sets the file selection field to editable again
		forensicID.setText(null);
		forensicID.setEditable(true);
		bioBox.setText(null);
		printsBox.setText(null);
		tracksBox.setText(null);
		digitalBox.setText(null);
		toolMarkBox.setText(null);
		narcoticBox.setText(null);
		firearmBox.setText(null);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		/*c reates a string of all the information entered to be used in the confirmation panel
		 * This is done to allow the user and overview of the information entered so they can review and
		 * see if the whole thing is correct*/
		String suspectInformation = "Forensic ID: " + forensicID.getText() + "\nBiological Evidence: " + bioBox.getText() + "\nFinger Prints: " + printsBox.getText()
		+ "\nTrack Evidence: " + tracksBox.getText() + "\nDigital Evidence: " + digitalBox.getText() + "\nTool Mark Evidence: " + toolMarkBox.getText()
		+ "\nNarcotic Evidence: " + narcoticBox.getText()
		+ "\nFirearm Evidence: " + firearmBox.getText();
		
		/* this is the confirmation dialog box that prints the above string in a popup box that gives the user a chance to confirm or cancel
		 * the adding of the new case */
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the follwoing Information?\n" + suspectInformation);
		//if the user confirms they want to add the information the following code is executed
		if(confirmation == 0)
		{
			//calls the delete method from the class that uses the entered forensicID to delete the record from the database
			f1.deleteFromDatabase(forensicID.getText());
			clearfields();
		}
	}

}
