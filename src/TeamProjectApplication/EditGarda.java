package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.AbstractDocument;

public class EditGarda extends JPanel implements ActionListener{
	
	//instance variables and components to build the GUI
	private JFormattedTextField gardaID, barracksID, name, address, phoneNO, PPSNumber;
	private JLabel gardaIDLabel, barracksIDLabel, nameLabel, addressLabel, phoneNOLabel, PPSNumberLabel, statusLabel, certificationLabel;
	private String[] status = {"Active", "Suspended", "Transferred", "Retired"};
	private String[] defaultstatus = {"Active", "Suspended", "Transferred", "Retired"};
	private String[] certified = {"True", "False"};
	private String[] defaultCertified = {"False", "True"};
	protected JButton submit, cancel, caseSubmit, caseClear;
	private JComboBox setStatus, setCertification;
	private JPanel container, form, buttons, caseSelection;
	//instance of suspect class for data manipulation
	Garda gardaIn = new Garda();
	
	public EditGarda()
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
		
		gardaID = new JFormattedTextField();
		gardaIDLabel = new JLabel("Garda ID Number:");
		((AbstractDocument)gardaID.getDocument()).setDocumentFilter(new SpecialCharacterFilter());
		caseSubmit = new JButton("Submit");
		caseClear = new JButton("Clear");
		
		caseSubmit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//sets the suspectID field to uneditable to stop it being accidently changed while in use.
				gardaID.setEditable(false);
				
				//assigns the return value from the get garda method to an int
				int getGarda = gardaIn.getGarda(gardaID.getText());
				
				//if the returned value is 0 it means that the case does not exist.
				if(getGarda == 0)
				{
					JOptionPane.showMessageDialog(null, "Garda" + gardaID.getText() + " could not be found, please check the ID and try again ", "Error", JOptionPane.ERROR_MESSAGE);
					gardaID.setEditable(true);
				}
				
				else
				{
					//sets the textfields to the appropriate values retrieved from the database.
					barracksID.setText(gardaIn.getBarracks());
					name.setText(gardaIn.getName());
					address.setText(gardaIn.getAddress());
					phoneNO.setText(gardaIn.getPhoneNo());
					PPSNumber.setText(gardaIn.getPPS());
					
					//checks to see what the default status is from the available array and sets it to default using a model
					for (int i = 0; i < status.length; i++)
					{
						//if statement that checks what the status is equal to in the array, then it swaps that to the first slot in the array
						if(status[i].equals(gardaIn.getStatus()))
						{
							String temp = status[0];
							status[0] = status [i];
							status[i] = temp;
						}
					}
					//the new array is then turned into a model and that model is set as the default for the status Combobox
					DefaultComboBoxModel model = new DefaultComboBoxModel(status);
					setStatus.setModel(model);
					
					if(gardaIn.getCertification().equals("True"))
					{
						DefaultComboBoxModel cModel = new DefaultComboBoxModel(certified);
						setCertification.setModel(cModel);
					}
					submit.setEnabled(true);
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
		caseSelection.add(gardaIDLabel);
		caseSelection.add(gardaID);
		caseSelection.add(caseSubmit);
		caseSelection.add(caseClear);
		
		//this is the label and text field for the witness name
		barracksID = new JFormattedTextField();
		barracksIDLabel = new JLabel("Garda Barracks ID:");
		((AbstractDocument)barracksID.getDocument()).setDocumentFilter(new SpecialCharacterFilter());
		form.add(barracksIDLabel);
		form.add(barracksID);
		
		//this is the label and text field for the witness address
		name = new JFormattedTextField();
		nameLabel = new JLabel("Garda Name:");
		((AbstractDocument)name.getDocument()).setDocumentFilter(new NumberFilter());
		form.add(nameLabel);
		form.add(name);
		
		//this is the label and text field for the witness contact information e.g. email/phone
		address = new JFormattedTextField();
		addressLabel = new JLabel("Home Address:");
		((AbstractDocument)address.getDocument()).setDocumentFilter(new SpecialCharacterFilter());
		form.add(addressLabel);
		form.add(address);
		
		phoneNO = new JFormattedTextField();
		phoneNOLabel = new JLabel("Phone Number:");
		((AbstractDocument)phoneNO.getDocument()).setDocumentFilter(new LetterFilter());
		form.add(phoneNOLabel);
		form.add(phoneNO);
		
		PPSNumber = new JFormattedTextField();
		PPSNumberLabel = new JLabel("PPS Number: ");
		((AbstractDocument)PPSNumber.getDocument()).setDocumentFilter(new SpecialCharacterFilter());
		form.add(PPSNumberLabel);
		form.add(PPSNumber);
		
		//this is the label and combobox for the current status of the Gardda
		this.statusLabel = new JLabel("Current Status:");
		this.setStatus = new JComboBox(status);
		form.add(statusLabel);
		form.add(setStatus);
		
		
		this.certificationLabel = new JLabel("Certified: ");
		this.setCertification = new JComboBox(defaultCertified);
		form.add(certificationLabel);
		form.add(setCertification);
		
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
	
	//this is the action event that happens upon pressing the submit button
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		/*creates a string of all the information entered to be used in the confirmation panel
		 * This is done to allow the user and overview of the information entered so they can review and
		 * see if the whole thing is correct*/
		String information = "GardaID: " + gardaID.getText() + "\nBarracksID: " + barracksID.getText() + "\nGarda Name: "
		+ name.getText() + "\nHome Address: " + address.getText() + "\nPhone Number: " + phoneNO.getText() + "\nPPSNumber: " + PPSNumber.getText();
		
		/* this is the confirmation dialog box that prints the above string in a popup box that gives the user a chance to confirm or cancel
		 * the adding of the new vehicle */
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to ADd the follwoing Information?\n" + information);
		//if the user confirms they want to add the information the following code is executed
		if(confirmation == 0)
		{
			//uses the methods in set methods in the witness class to assign the values to variables
			gardaIn.setBarrack(barracksID.getText());
			gardaIn.setName(name.getText());
			gardaIn.setAddress(address.getText());
			gardaIn.setPhoneNo(phoneNO.getText());
			gardaIn.setPPS(PPSNumber.getText());
			gardaIn.setStatus(String.valueOf(setStatus.getSelectedItem()));
			gardaIn.setCertified(String.valueOf(setCertification.getSelectedItem()));
			
			//calls the method to add the data to the database.
			gardaIn.updateGarda();
			
			//calls the clear fields method to reset the fields to default for the next entry
			clearfields();
			JOptionPane.showMessageDialog(null, "Garda Sucessfully Updated", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void clearfields()
	{
		//sets all the fields to blank
		gardaID.setEditable(true);
		gardaID.setText(null);
		barracksID.setText(null);
		name.setText(null);
		address.setText(null);
		phoneNO.setText(null);
		PPSNumber.setText(null);
		
		//uses a model a to set the combobox back to the default values with suspect being the default
		DefaultComboBoxModel model = new DefaultComboBoxModel(defaultstatus);
		setStatus.setModel(model);
		
		DefaultComboBoxModel cmodel = new DefaultComboBoxModel(defaultCertified);
		setCertification.setModel(cmodel);
		submit.setEnabled(false);
	}
}
