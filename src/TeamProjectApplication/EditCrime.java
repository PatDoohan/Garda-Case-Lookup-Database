package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.AbstractDocument;

public class EditCrime extends JPanel implements ActionListener{
	
	//instance variables and components to build the GUI
	private JFormattedTextField crimeCode, crimeName, crimeDescription;
	JLabel crimeCodeLabel, crimeNameLabel, crimeDescriptionLabel;
	protected JButton submit, cancel, caseSubmit, caseClear;
	private JPanel container, form, buttons, caseSelection;
	//instance of witness class for database manipulation
	Crime crimeIn = new Crime();
	
	public EditCrime()
	{
		
		//sets the layout to border layout as the default layout for a jpanel is flow
		this.setLayout(new BorderLayout());
		
		/* Creates the form panel that will hold all the labels and text fields for the form.
		 * It is given a grid layout so that each row will be a different section of the form e.g 
		 * what the name is and on the next line the Description of the crime etc.*/
		form = new JPanel();
		form.setLayout(new GridLayout(0,2,5,5));
		form.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));

		/* this is the panel where the information is entered to find the crime that needs to be edited */
		caseSelection = new JPanel();
		caseSelection.setLayout(new GridLayout(0,4,5,5));
		caseSelection.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		//this is the label and text field for the Crime code number and submit and clear buttons
		crimeCode = new JFormattedTextField();
		crimeCodeLabel = new JLabel("Crime Code:");
		((AbstractDocument)crimeCode.getDocument()).setDocumentFilter(new LetterFilter());
		caseSubmit = new JButton("Submit");
		caseClear = new JButton("Clear");
		
		caseSubmit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//sets the suspectID field to uneditable to stop it being accidently changed while in use.
				crimeCode.setEditable(false);
				submit.setEnabled(true);
				
				//sets the textfields to the appropriate values retrieved from the database.
				int getCrimeCode = crimeIn.getCrime(crimeCode.getText());
				
				if(getCrimeCode == 0)
				{
					JOptionPane.showMessageDialog(null,"Crime Code Not Found, Please Enter a valid Crime Code", "Crime Code Not Found",  JOptionPane.ERROR_MESSAGE);
					crimeCode.setEditable(true);
				}
				
				else
				{
					crimeName.setText(crimeIn.getCrimeName());
					crimeDescription.setText(crimeIn.getCrimeDescription());
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
		caseSelection.add(crimeCodeLabel);
		caseSelection.add(crimeCode);
		caseSelection.add(caseSubmit);
		caseSelection.add(caseClear);
		
		//this is the label and text field for the crime name
		crimeName = new JFormattedTextField();
		crimeNameLabel = new JLabel("Crime Name:");
		((AbstractDocument)crimeName.getDocument()).setDocumentFilter(new NumberFilter());
		form.add(crimeNameLabel);
		form.add(crimeName);
		
		//this is the label and text field for the crime Description
		crimeDescription = new JFormattedTextField();
		crimeDescriptionLabel = new JLabel("Crime Description:");
		((AbstractDocument)crimeDescription.getDocument()).setDocumentFilter(new NumberFilter());
		form.add(crimeDescriptionLabel);
		form.add(crimeDescription);
		
		/* these are the buttons to cancel and sumbit and the panel that holds them
		 * Submit uses the inner class listener to perform a function
		 * and the cancel button is used by the window handler class to
		 * return to the previous menu*/		
		buttons = new JPanel();
		submit = new JButton("Submit");
		submit.addActionListener(this);
		submit.setEnabled(false);
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
	public void actionPerformed(ActionEvent e) 
	{
		/*creates a string of all the information entered to be used in the confirmation panel
		 * This is done to allow the user and overview of the information entered so they can review and
		 * see if the whole thing is correct*/
		String information = "Crime Code: " + crimeCode.getText() + "\nCrime Name: " + crimeName.getText() + "\nCrime Description: "
		+ crimeDescription.getText();
		
		/* this is the confirmation dialog box that prints the above string in a popup box that gives the user a chance to confirm or cancel
		 * the adding of the new vehicle */
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to Update the follwoing Information?\n" + information);
		//if the user confirms they want to add the information the following code is executed
		if(confirmation == 0)
		{
			//uses the methods in set methods in the crime class to assign the values to variables
		
			crimeIn.setCrimeName(crimeName.getText());
			crimeIn.setCrimeDescription(crimeDescription.getText());
			
			//calls the method to add the data to the database.
			crimeIn.updateCrime();
			
			//calls the clear fields method to reset the fields to default for the next entry
			clearfields();
			JOptionPane.showMessageDialog(null, "Crime Sucessfully Updated", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
			
		}
		
	}
	
	//method for clearing all information from fields after successful add or cancellation of add
		public void clearfields()
		{
			//sets all the fields to blank
			crimeCode.setEditable(true);
			crimeCode.setText(null);
			crimeName.setText(null);
			crimeDescription.setText(null);
			submit.setEnabled(false);
		}
}
