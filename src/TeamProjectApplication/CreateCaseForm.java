package TeamProjectApplication;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class CreateCaseForm extends JPanel implements ActionListener{

	//Instance Varibales
	private JTextField caseID, date, time, address, eircode;
	private JLabel caseIDLabel, dateLabel, timeLabel, addressLabel, statusLabel,eircodeLabel, titleLabel;
	private String[] status = {"Open", "Active", "In Court", "Closed"};
	private JComboBox setStatus;
	protected JButton submit, cancel;
	private JPanel container, form, buttons, title;
	private ActiveCase inputCase = new ActiveCase();

	public CreateCaseForm()
	{
		
	
		//setting border for panel
		this.setLayout(new BorderLayout());
		JPanel form = new JPanel();
		form.setLayout(new GridLayout(0,2,5,5));
		form.setBorder(new CompoundBorder(new EmptyBorder(0,0,0,0), new CompoundBorder(new EtchedBorder(), new EmptyBorder(0,0,0,0))));
		
	
	
		titleLabel = new JLabel("New Case");
		JLabel blankLabel = new JLabel("");
		titleLabel.setFont(new Font("Sans-Serif",0, 40));
		form.add(titleLabel);
		form.add(blankLabel);
		
		
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
		
		buttons = new JPanel();
		submit = new JButton("Submit");
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
		
		if(confirmation == 0)
		{
			inputCase.setDateTime(date.getText() + " " + time.getText());
			inputCase.setAddress(address.getText());
			inputCase.setActiveStatus(setStatus.getSelectedItem());
			inputCase.setEirCode(eircode.getText());
			inputCase.addToDatabase();
			inputCase.assignID();
			caseID.setText(String.valueOf(inputCase.getCaseID()));
			date.setText(null);
			time.setText(null);
			address.setText(null);
			setStatus.setSelectedItem(0);
			eircode.setText(null);		
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
	}
}
