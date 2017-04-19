package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

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

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

@SuppressWarnings("serial")
public class EditCaseForm extends JPanel implements ActionListener{
	
	//instance variables and components for creating the form, includes all the components required to build the UI
	private JTextField caseID, date, time, address, eircode;
	private JLabel caseIDLabel, dateLabel, timeLabel, addressLabel, statusLabel,eircodeLabel;
	private String[] status = {"Open", "Active", "In Court", "Closed"};
	private String[] defaultStatus = {"Open", "Active", "In Court", "Closed"};
	private JComboBox setStatus;
	protected JButton submit, cancel, caseSubmit, caseClear;
	private JPanel container, form, buttons, caseSelection;
	//creating instance of active case to manipulate database data
	private ActiveCase inputCase = new ActiveCase();
	JDatePickerImpl datePicker;
	TimePicker timepicker;
	
	//main class file for AddForensicForm that builds a UI and implements the listener class
	public EditCaseForm()
	{
		//sets the layout to border layout as the default layout for a jpanel is flow.
		this.setLayout(new BorderLayout());
		
		/* Creates the form panel that will hold all the labels and text fields for the form.
		 * It is given a grid layout so that each row will be a different section of the form e.g 
		 * what case it is a part of then on the next line the ID number of the case file etc.*/
		form = new JPanel();
		form = new JPanel();
		form.setLayout(new GridLayout(0,2,5,5));
		form.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		/* Creates the panel that will hold the panels and buttons for the forensic file selection
		 * the layout is set to allow all the items in the panel to exist on the same row */
		caseSelection = new JPanel();
		caseSelection.setLayout(new GridLayout(0,4,5,5));
		caseSelection.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		//creates the panel, text field and buttons for the file selection panel.
		caseIDLabel = new JLabel("Please enter the Case ID you wish to Update:");
		caseID = new JTextField();
		caseID.setPreferredSize(new Dimension(100, 100));
		caseID.setEditable(true);
		caseSubmit = new JButton();
		caseSubmit.setText("Submit");
		caseClear = new JButton("Clear");
		
		//adds functionality to the submit button
		caseSubmit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//sets the caseID field to uneditable to stop it being accidently changed while in use.
				caseID.setEditable(false);
				
				//uses the method in the case class to retrieve the information from the database and assign it to the variables in class
				int returnCase = inputCase.GetCase(caseID.getText());
				
				if(returnCase == 0)
				{
					JOptionPane.showMessageDialog(null,"Witness Not Found, Please Enter a valid Witness pps", "Witness Not Found",  JOptionPane.ERROR_MESSAGE);
					caseID.setEditable(true);
				}
				
				else
				{
					//splits the date and time so that is correctly displayed as the sql format holds both values in one.
					String retrievedTime = inputCase.getTime();
					
					String[] parts = retrievedTime.split(":");
				
					//sets the textfields to the appropriate values retrieved from the database.
					datePicker.getJFormattedTextField().setText(inputCase.getDate());
					timepicker.setTime(parts[0], parts[1]);
					address.setText(inputCase.getAddress());
					eircode.setText(inputCase.getEircode());
					
					//checks to see what the default status is from the available array and sets it to default using a model
					for (int i = 0; i < status.length; i++)
					{
						//if statement that checks what the status is equal to in the array, then it swaps that to the first slot in the array
						if(status[i].equals(inputCase.getStatus()))
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
				clearFields();
			}
		});
		//adds the label, text field and buttons to the caseSelection panel
		caseSelection.add(caseIDLabel);
		caseSelection.add(caseID);
		caseSelection.add(caseSubmit);
		caseSelection.add(caseClear);
		
		//this is the label and textfield for the address where the crime took place
		addressLabel = new JLabel("Address of Crime: ");
		address = new JTextField();
		form.add(addressLabel);
		form.add(address);
		
		//adds the datepicker to the panel from the JDatePicker Library
		dateLabel = new JLabel("Date of Crime: ");
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		form.add(dateLabel);
		form.add(datePicker);
		
		
		//this is the label and textfield for the time the crime took place
		timeLabel = new JLabel("Time of Crime: ");
		timepicker = new TimePicker();
		form.add(timeLabel);
		form.add(timepicker);
		
		//this is the label and textfield for the current status of the case
		statusLabel = new JLabel("Current status of case: ");
		setStatus = new JComboBox(status);
		form.add(statusLabel);
		form.add(setStatus);
		
		//this is the label and textfield for the eircode where the crime took place
		eircodeLabel = new JLabel("Eircode of Crime");
		eircode = new JTextField();
		form.add(eircodeLabel);
		form.add(eircode);
		
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
		String dataConfirmation = "Case ID: " + caseID.getText() + "\nDate: " + datePicker.getJFormattedTextField().getText() + "\nTime: " + timepicker.getTime() + "\nAddress: " 
		+ address.getText() + "\nStatus: " + setStatus.getSelectedItem() + "\nEircode: " + eircode.getText();
		
		/* this is the confirmation dialog box that prints the above string in a popup box that gives the user a chance to confirm or cancel
		 * the adding of the new case */
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to Update the follwoing Information?\n" + dataConfirmation);
		
		//if the user confirms they want to add the information the following code is executed
		if(confirmation == 0)
		{
			//All the information is retrieved from the text fields and inserted into the suspect class using the set methods
			inputCase.setDate(datePicker.getJFormattedTextField().getText());
			inputCase.setTime(timepicker.getTime());
			inputCase.setAddress(address.getText());
			inputCase.setActiveStatus(setStatus.getSelectedItem());
			inputCase.setEirCode(eircode.getText());
			
			//calls the method to add the updated data to the database.
			inputCase.updateDatabase(caseID.getText());
			
			//calls the clearfields method to reset the fields to default for the next entry
			clearFields();
			
			//confirmation pane for succesfully entered case.
			JOptionPane.showMessageDialog(null, "Case Entered Sucessfully", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
			
	//method for clearing all information from fields after successful edit or cancellation of edit
	public void clearFields()
	{
		//resets the caseID field to blank and sets it as editable again.
		caseID.setEditable(true);
		caseID.setText(null);
		
		//sets all the text fields to blank
		datePicker.getJFormattedTextField().setText("");
		timepicker.resetTimer();
		address.setText(null);
		eircode.setText(null);		
	
		//uses a model a to set the combobox back to the default values with suspect being the default
		DefaultComboBoxModel model = new DefaultComboBoxModel(defaultStatus);
		setStatus.setModel(model);
	}
	
}