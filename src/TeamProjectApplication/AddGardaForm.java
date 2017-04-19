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

public class AddGardaForm extends JPanel implements ActionListener{

	//instance variables and components to build the GUI
	private JTextField gardaID, barracksID, name, address, phoneNO, PPSNumber, Password;
	private JLabel gardaIDLabel, barracksIDLabel, nameLabel, addressLabel, phoneNOLabel, PPSNumberLabel, statusLabel, certificationLabel, PasswordLabel;
	protected JButton submit, cancel;
	private String[] status = {"Active", "Suspended", "Transferred", "Retired"};
	private String[] defaultstatus = {"Active", "Suspended", "Transferred", "Retired"};
	private String[] certified = {"True", "False"};
	private String[] defaultCertified = {"False", "True"};
	private JComboBox setStatus, setCertification;
	private JPanel container, form, buttons;
	private String password;
	//instance of suspect class for data manipulation
	Garda gardaIn = new Garda();

	public AddGardaForm()
	{
		//sets the layout to border layout as the default layout for a jpanel is flow
		this.setLayout(new BorderLayout());
				
		/* Creates the form panel that will hold all the labels and text fields for the form.
		 * It is given a grid layout so that each row will be a different section of the form e.g 
		 * what case it is a part of then on the next line the ID number of the suspect file etc.*/
		form = new JPanel();
		form.setLayout(new GridLayout(0,2,5,5));
		form.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		
		gardaID = new JTextField();
		gardaIDLabel = new JLabel("Garda ID Number:");
		form.add(gardaIDLabel);
		form.add(gardaID);
		
		//this is the label and text field for the witness name
		barracksID = new JTextField();
		barracksIDLabel = new JLabel("Garda Barracks ID:");
		form.add(barracksIDLabel);
		form.add(barracksID);
		
		//this is the label and text field for the witness address
		name = new JTextField();
		nameLabel = new JLabel("Garda Name:");
		form.add(nameLabel);
		form.add(name);
		
		//this is the label and text field for the witness contact information e.g. email/phone
		address = new JTextField();
		addressLabel = new JLabel("Home Address:");
		form.add(addressLabel);
		form.add(address);
		
		phoneNO = new JTextField();
		phoneNOLabel = new JLabel("Phone Number:");
		form.add(phoneNOLabel);
		form.add(phoneNO);
		
		PPSNumber = new JTextField();
		PPSNumberLabel = new JLabel("PPS Number: ");
		form.add(PPSNumberLabel);
		form.add(PPSNumber);
		
		//this is the label and combobox for the current status of the Gardda
		this.statusLabel = new JLabel("Current Status:");
		this.setStatus = new JComboBox(status);
		form.add(statusLabel);
		form.add(setStatus);
		
		Password = new JTextField();
		PasswordLabel = new JLabel("New Login Password: ");
		form.add(PasswordLabel);
		form.add(Password);
		
		
		this.certificationLabel = new JLabel("Certification: ");
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
		String information = "GardaID: " + gardaID.getText() + "\nBarracksID: " + barracksID.getText() + "\nGarda Name: "
		+ name.getText() + "\nHome Address: " + address.getText() + "\nPhone Number: " + phoneNO.getText() + "\nPPSNumber: " + PPSNumber.getText()
		+ "\nCurrent Status: " + setStatus.getSelectedItem() + "\nPassword: " + Password.getText() +  "\n Certification: " + setCertification.getSelectedItem(); 
		
		/* this is the confirmation dialog box that prints the above string in a popup box that gives the user a chance to confirm or cancel
		 * the adding of the new vehicle */
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to ADd the follwoing Information?\n" + information);
		//if the user confirms they want to add the information the following code is executed
		if(confirmation == 0)
		{
			//uses the methods in set methods in the witness class to assign the values to variables
			gardaIn.setidNo(gardaID.getText());
			gardaIn.setBarrack(barracksID.getText());
			gardaIn.setName(name.getText());
			gardaIn.setAddress(address.getText());
			gardaIn.setPhoneNo(phoneNO.getText());
			gardaIn.setPPS(PPSNumber.getText());
			gardaIn.setStatus(String.valueOf(setStatus.getSelectedItem()));
			gardaIn.setPassword(Password.getText());
			gardaIn.setCertified(String.valueOf(setCertification.getSelectedItem()));
			
			//calls the method to add the data to the database.
			gardaIn.addToDatabase();
			
			//calls the clear fields method to reset the fields to default for the next entry
			clearfields();
		}
	}
	
	//method for clearing all information from fields after successful add or cancellation of add
	public void clearfields()
	{
		//sets all the fields to blank
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
	}

}
