package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class AddWitnessForm extends JPanel implements ActionListener{

	JTextField pps, name, address, contactInfo, caseIn;
	JLabel ppsLabel, nameLabel, addressLabel, contactLabel, caseLabel;
	protected JButton submit, cancel;
	private JPanel container, form, buttons;
	Witness witnessIn = new Witness();
	
	public AddWitnessForm()
	{
		this.setLayout(new BorderLayout());
		form = new JPanel();
		form.setLayout(new GridLayout(0,2,5,5));
		form.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		caseLabel = new JLabel("Witness in Case:");
		caseIn = new JTextField();
		form.add(caseLabel);
		form.add(caseIn);
		
		pps = new JTextField();
		ppsLabel = new JLabel("Witness PPS Number:");
		form.add(ppsLabel);
		form.add(pps);
		
		name = new JTextField();
		nameLabel = new JLabel("Witness Name:");
		form.add(nameLabel);
		form.add(name);
		
		address = new JTextField();
		addressLabel = new JLabel("Witness Address:");
		form.add(addressLabel);
		form.add(address);
		
		contactInfo = new JTextField();
		contactLabel = new JLabel("Witness Contact Information:");
		form.add(contactLabel);
		form.add(contactInfo);
		
		buttons = new JPanel();
		submit = new JButton("Submit");
		submit.addActionListener(this);
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
	public void actionPerformed(ActionEvent arg0) {
		
		String information = "Witness PPS: " + pps.getText() + "\nWitness Name: " + name.getText() + "\nWitness Address: "
		+ address.getText() + "\nContact Info: " + contactInfo.getText() + "\nPart of case: " + caseIn.getText();
		
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to Update the follwoing Information?\n" + information);
		if(confirmation == 0)
		{
			witnessIn.setPPs(pps.getText());
			witnessIn.setName(name.getText());
			witnessIn.setAddress(address.getText());
			witnessIn.setContactInfo(contactInfo.getText());
			witnessIn.linkToCase(Integer.valueOf(caseIn.getText()));
			witnessIn.addToDatabase();
			clearfields();
			
		}
	}
		
	public void clearfields()
	{
		pps.setText(null);
		name.setText(null);
		address.setText(null);
		contactInfo.setText(null);
		caseIn.setText(null);
	}

}
