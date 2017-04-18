package TeamProjectApplication;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class CreateCaseForm extends JPanel implements ActionListener{

	//Instance Varibales
	private JTextField caseID, date, time, address, eircode, crimecode1, crimecode2, crimecode3, crimecode4, crimecode5;
	private JLabel caseIDLabel, dateLabel, timeLabel, addressLabel, statusLabel, eircodeLabel, crimeLabel1, crimeLabel2, crimeLabel3, crimeLabel4, crimeLabel5;
	private String[] status = {"Open", "Active", "In Court", "Closed"};
	private JComboBox setStatus;
	protected JButton submit, cancel;
	private JPanel container, form, buttons;
	private ActiveCase inputCase = new ActiveCase();
	private Crime crimeIn = new Crime();

	public CreateCaseForm()
	{
		//setting border for panel
		this.setLayout(new BorderLayout());
		form = new JPanel();
		form.setLayout(new GridLayout(0,2,5,5));
		form.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		caseIDLabel = new JLabel("Case ID:");
		inputCase.assignID();
		caseID = new JTextField();
		caseID.setText(String.valueOf(inputCase.getCaseID()));
		caseID.setPreferredSize(new Dimension(100, 100));
		caseID.setEditable(false);
		form.add(caseIDLabel);
		form.add(caseID);
		
		dateLabel = new JLabel("Date of Crime: ");
		date = new JTextField();
		form.add(dateLabel);
		form.add(date);
		
		timeLabel = new JLabel("Time of Crime: ");
		time = new JTextField();
		form.add(timeLabel);
		form.add(time);
		
		addressLabel = new JLabel("Address of Crime: ");
		address = new JTextField();
		form.add(addressLabel);
		form.add(address);
		
		statusLabel = new JLabel("Current status of case: ");
		setStatus = new JComboBox(status);
		form.add(statusLabel);
		form.add(setStatus);
		
		eircodeLabel = new JLabel("Eircode of Crime");
		eircode = new JTextField();
		form.add(eircodeLabel);
		form.add(eircode);
		
		crimeLabel1 = new JLabel("Crime Code 1: ");
		crimecode1 = new JTextField("");
		form.add(crimeLabel1);
		form.add(crimecode1);
		
		crimeLabel2 = new JLabel("Crime Code 2: ");
		crimecode2 = new JTextField("");
		form.add(crimeLabel2);
		form.add(crimecode2);
		
		crimeLabel3 = new JLabel("Crime Code 3: ");
		crimecode3 = new JTextField("");
		form.add(crimeLabel3);
		form.add(crimecode3);
		
		crimeLabel4 = new JLabel("Crime Code 4: ");
		crimecode4 = new JTextField("");
		form.add(crimeLabel4);
		form.add(crimecode4);
		
		crimeLabel5 = new JLabel("Crime Code 5: ");
		crimecode5 = new JTextField("");
		form.add(crimeLabel5);
		form.add(crimecode5);
		
		buttons = new JPanel();
		submit = new JButton();
		cancel = new JButton("Cancel");
		buttons.add(submit);
		buttons.add(cancel);
		
		container = new JPanel();
		container.setLayout(new GridLayout(0,1));
		container.setBorder(new EmptyBorder(200, 0, 0, 0));
		container.add(form);
		container.add(buttons);
		
		add(container, BorderLayout.CENTER);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		//private JTextField caseID date, time, address, eircode;
		String dataConfirmation = "Case ID: " + caseID.getText() + "\nDate: " + date.getText() + "\nTime: " + time.getText() + "\nAddress: " 
		+ address.getText() + "\nStatus: " + setStatus.getSelectedItem() + "\nEircode: " + eircode.getText();
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to add the follwoing Information?\n" + dataConfirmation);
		
		int crimecheck1, crimecheck2, crimecheck3, crimecheck4, crimecheck5;
		
		crimecheck1 = crimeIn.checkCrime(crimecode1.getText());
		crimecheck2 = crimeIn.checkCrime(crimecode2.getText());
		crimecheck3 = crimeIn.checkCrime(crimecode3.getText());
		crimecheck4 = crimeIn.checkCrime(crimecode4.getText());
		crimecheck5 = crimeIn.checkCrime(crimecode5.getText());
		Boolean allClear = true;
		
		if(crimecheck1 == 0 || crimecheck2 == 0 || crimecheck3 == 0 || crimecheck4 == 0 || crimecheck5 == 0)
		{
			if(crimecheck1 == 0)
			{
				JOptionPane.showMessageDialog(null,"Crime Code Not" + crimecode1.getText() + " Found, Please Enter a Crime Code 1 and try again", "Crime Code Not Found",  JOptionPane.ERROR_MESSAGE);
				allClear = false;
			}
			
			else if(crimecheck2 == 0)
			{
				JOptionPane.showMessageDialog(null,"Crime Code Not" + crimecode2.getText() + " Found, Please Enter Crime Code 2 and try again", "Crime Code Not Found",  JOptionPane.ERROR_MESSAGE);
				allClear = false;
			}
			
			else if(crimecheck3 == 0)
			{
				JOptionPane.showMessageDialog(null,"Crime Code Not" + crimecode3.getText() + " Found, Please Enter Crime Code 2 and try again", "Crime Code Not Found",  JOptionPane.ERROR_MESSAGE);
				allClear = false;
			}
			
			else if(crimecheck4 == 0)
			{
				JOptionPane.showMessageDialog(null,"Crime Code Not" + crimecode4.getText() + " Found, Please Enter Crime Code 2 and try again", "Crime Code Not Found",  JOptionPane.ERROR_MESSAGE);
				allClear = false;
			}
			
			else if(crimecheck5 == 0)
			{
				JOptionPane.showMessageDialog(null,"Crime Code Not" + crimecode5.getText() + " Found, Please Enter Crime Code 2 and try again", "Crime Code Not Found",  JOptionPane.ERROR_MESSAGE);
				allClear = false;
			}
		}
		
		if(confirmation == 0 && allClear == true)
		{
			inputCase.setDateTime(date.getText() + " " + time.getText());
			inputCase.setAddress(address.getText());
			inputCase.setActiveStatus(setStatus.getSelectedItem());
			inputCase.setEirCode(eircode.getText());
			inputCase.addToDatabase();
			
			crimeIn.getCrime(crimecode1.getText());
			crimeIn.linkToCase(String.valueOf(inputCase.getCaseID()));
			crimeIn.getCrime(crimecode2.getText());
			crimeIn.linkToCase(String.valueOf(inputCase.getCaseID()));
			crimeIn.getCrime(crimecode3.getText());
			crimeIn.linkToCase(String.valueOf(inputCase.getCaseID()));
			crimeIn.getCrime(crimecode4.getText());
			crimeIn.linkToCase(String.valueOf(inputCase.getCaseID()));
			crimeIn.getCrime(crimecode5.getText());
			crimeIn.linkToCase(String.valueOf(inputCase.getCaseID()));
			
			inputCase.assignID();
			clearFields();
			JOptionPane.showMessageDialog(null, "Case Entered Sucessfully", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		}		
	}
	
	public void clearFields()
	{
		caseID.setText(String.valueOf(inputCase.getCaseID()));
		date.setText(null);
		time.setText(null);
		address.setText(null);
		setStatus.setSelectedItem(0);
		eircode.setText(null);		
		crimecode1.setText(null);
		crimecode2.setText(null);
		crimecode3.setText(null);
		crimecode4.setText(null);
		crimecode5.setText(null);
	}
}
