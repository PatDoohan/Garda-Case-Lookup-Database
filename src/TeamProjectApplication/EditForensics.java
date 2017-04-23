package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.AbstractDocument;

public class EditForensics extends JPanel implements ActionListener{
	
	//instance variables and components for creating the form, includes all the components required to build the UI
	JLabel forensicLabel, bioLabel, printsLabel, tracksLabel, digitalLabel, toolMarkLabel, narcoticLabel, firearmLabel;
	private JFormattedTextField forensicID;
	String[] confirmation = {"Not Present","Present"};
	JComboBox bioBox, printsBox, tracksBox, digitalBox, toolMarkBox, narcoticBox, firearmBox;
	JButton submit, cancel, caseSubmit, caseClear;
	JPanel container, form, buttons, ForensicFileSelection;
	//create instance of forensic to insert the data into the database.
	Forensics f1 = new Forensics();

	
	public EditForensics()
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
		forensicLabel = new JLabel("Forensic File to Edit:");
		forensicID = new JFormattedTextField();
		((AbstractDocument)forensicID.getDocument()).setDocumentFilter(new LetterFilter());
		caseSubmit = new JButton("Submit");
		caseClear = new JButton("Clear");
		
		//adds functionality to the submit button
		caseSubmit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//sets the forensicID field to uneditable to stop it being accidently changed while in use.
				forensicID.setEditable(false);
				//uses the method in the forensics class to retrieve the information from the database and assign it to the variables in class
				int returnForensic = f1.getForensicFile(Integer.valueOf(forensicID.getText()));
				//array that is used to change the values in comboboxes that have been changed from default
				if(returnForensic == 0)
				{
					JOptionPane.showMessageDialog(null,"Forensics File Not Found, Please Enter a Forensics File ID", "Forensics File Not Found",  JOptionPane.ERROR_MESSAGE);
					forensicID.setEditable(true);
				}
				
				else
				{
					String[] editConfirmation = {"Present","Not Present"};
					
					
					/* If statements that change the default values to the values that were retrieved from the database.
					 * uses unique models for each as using one model would result in all the values changing at once */
					if(f1.getBioEvidence().equals(confirmation[1]))
					{
						DefaultComboBoxModel bioModel = new DefaultComboBoxModel(editConfirmation);
						bioBox.setModel(bioModel);
					}
					
					if(f1.getPrints().equals(confirmation[1]))
					{
						DefaultComboBoxModel printsModel = new DefaultComboBoxModel(editConfirmation);
						printsBox.setModel(printsModel);
					}
					
					if(f1.getTrackEvidence().equals(confirmation[1]))
					{
						DefaultComboBoxModel trackModel = new DefaultComboBoxModel(editConfirmation);
						tracksBox.setModel(trackModel);
					}
					
					if(f1.getDigitalEvidence().equals(confirmation[1]))
					{
						DefaultComboBoxModel digitalModel = new DefaultComboBoxModel(editConfirmation);
						digitalBox.setModel(digitalModel);
					}
					
					if(f1.getToolMarkEvidence().equals(confirmation[1]))
					{
						DefaultComboBoxModel toolMarkModel = new DefaultComboBoxModel(editConfirmation);
						toolMarkBox.setModel(toolMarkModel);
					}
					
					if(f1.getNarcoticEvidence().equals(confirmation[1]))
					{
						DefaultComboBoxModel narcoticModel = new DefaultComboBoxModel(editConfirmation);
						narcoticBox.setModel(narcoticModel);
					}
					
					if(f1.getfirearmEvidence().equals(confirmation[1]))
					{
						DefaultComboBoxModel firearmEvidence = new DefaultComboBoxModel(editConfirmation);
						firearmBox.setModel(firearmEvidence);
					}
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
		
		//adds the label, text field and button to the file selection panel
		ForensicFileSelection.add(forensicLabel);
		ForensicFileSelection.add(forensicID);
		ForensicFileSelection.add(caseSubmit);
		ForensicFileSelection.add(caseClear);
		
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
		
		//adds the container and the forensicFileSelection to the main panel.
		add(ForensicFileSelection,BorderLayout.NORTH);
		add(container, BorderLayout.CENTER);
	}
	
	//this is the action event that happens upon pressing the submit button
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		/*c reates a string of all the information entered to be used in the confirmation panel
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
			
			//calls the method to add the updated data to the database.
			f1.updateForensics();
			
			//calls the clearfields method to reset the fields to default for the next entry
			clearfields();
			JOptionPane.showMessageDialog(null, "Forensic File Sucessfully Updated", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		}
	}
		
	//method for clearing all information from fields after successful edit or cancellation of edit
	public void clearfields()
	{
		//resets the forensicID field to blank and sets it as editable again.
		forensicID.setText(null);
		forensicID.setEditable(true);
		
		/* The following code uses unique models to set the comboboxes back to default values
		 * this could not be achieved using a single model as all the boxes would change at the same time
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
