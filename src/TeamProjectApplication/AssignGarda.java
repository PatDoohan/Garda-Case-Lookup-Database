package TeamProjectApplication;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class AssignGarda extends JPanel implements ActionListener{
	
	//instance variables and components to build the GUI
	private JTextField gardaID, barracksID, name, address, phoneNO, PPSNumber, caseNumber;
	private JLabel gardaIDLabel, barracksIDLabel, nameLabel, addressLabel, phoneNOLabel, PPSNumberLabel, statusLabel, caseLabel;
	private String[] status = {"Active", "Suspended", "Transferred", "Retired"};
	private String[] defaultstatus = {"Active", "Suspended", "Transferred", "Retired"};
	protected JButton submit, cancel, caseSubmit, caseClear;
	private JComboBox setStatus;
	private JPanel container, form, buttons, caseSelection;
	//instance of suspect class for data manipulation
	Garda gardaIn = new Garda();
	
	public AssignGarda()
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
		
		gardaID = new JTextField();
		gardaIDLabel = new JLabel("Garda ID Number to be assigned:");
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
		
		//this is the label and text field for the case number
		caseNumber = new JTextField();
		caseLabel = new JLabel("Case to be assigned to: ");
		form.add(caseLabel);
		form.add(caseNumber);
		
		//this is the label and text field for the garda barracks
		barracksID = new JTextField();
		barracksIDLabel = new JLabel("Garda Barracks ID:");
		barracksID.setEditable(false);
		form.add(barracksIDLabel);
		form.add(barracksID);
		
		//this is the label and text field for the garda name
		name = new JTextField();
		nameLabel = new JLabel("Garda Name:");
		name.setEditable(false);
		form.add(nameLabel);
		form.add(name);
		
		//this is the label and text field for the garda home address
		address = new JTextField();
		addressLabel = new JLabel("Home Address:");
		address.setEditable(false);
		form.add(addressLabel);
		form.add(address);
		
		//this is the label and text field for the garda phone number
		phoneNO = new JTextField();
		phoneNOLabel = new JLabel("Phone Number:");
		phoneNO.setEditable(false);
		form.add(phoneNOLabel);
		form.add(phoneNO);
		
		//this is the label and text field for the garda pps number
		PPSNumber = new JTextField();
		PPSNumberLabel = new JLabel("PPS Number: ");
		PPSNumber.setEditable(false);
		form.add(PPSNumberLabel);
		form.add(PPSNumber);
		
		//this is the label and combobox for the current status of the Garda
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
		String information = "GardaID: " + gardaID.getText() + "\nBarracksID: " + barracksID.getText() + "\nGarda Name: "
		+ name.getText() + "\nHome Address: " + address.getText() + "\nPhone Number: " + phoneNO.getText() + "\nPPSNumber: " + PPSNumber.getText() + "\nCase To Be assigned to: "
		+ caseNumber.getText();
		
		/* this is the confirmation dialog box that prints the above string in a popup box that gives the user a chance to confirm or cancel
		 * the adding of the new vehicle */
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to assign the garda to the following case?\n" + information);
		//if the user confirms they want to add the information the following code is executed
		if(confirmation == 0)
		{
			//gets the return value from the assign to case method in the garda class
			int assignToCase = gardaIn.assignToCase(caseNumber.getText());
			
			//if the method returned 0 it means that the garda has already been assigned to the case.
			if(assignToCase == 0)
			{
				JOptionPane.showMessageDialog(null, "Garda" + gardaID.getText() + " Already Assigned to Case " + caseNumber.getText(), "Garda Already Assigned", JOptionPane.ERROR_MESSAGE);
				clearfields();
			}
			
			//if the method returned 1 it means that the garda is currently not active
			else if(assignToCase == 1)
			{
				JOptionPane.showMessageDialog(null, "Garda " + gardaID.getText() +" is currently not active, Please Recheck Information and try again" , "Inactive Garda", JOptionPane.ERROR_MESSAGE);
			}
			//if the method returned 2 it means that the case does not exists
			else if(assignToCase == 2)
			{
				JOptionPane.showMessageDialog(null, "Case " + caseNumber.getText() + " does not exist" , "Case Not Found", JOptionPane.ERROR_MESSAGE);
				
			}
			
			//if the insert was successful a message pops up to confirm that the garda has been assigned to the case.
			else
			{
				JOptionPane.showMessageDialog(null, "Garda " + gardaID.getText() + " Successfully assigned to case " + caseNumber.getText(), "Success", JOptionPane.INFORMATION_MESSAGE);
				clearfields();
			}
		}
	}

	public void clearfields()
	{
		//sets all the fields to blank
		gardaID.setEditable(true);
		caseNumber.setText(null);
		gardaID.setText(null);
		barracksID.setText(null);
		name.setText(null);
		address.setText(null);
		phoneNO.setText(null);
		PPSNumber.setText(null);
		
		//uses a model a to set the combobox back to the default values with suspect being the default
		DefaultComboBoxModel model = new DefaultComboBoxModel(defaultstatus);
		setStatus.setModel(model);
	}
}
