package TeamProjectApplication;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class EditWitness extends JPanel implements ActionListener{

	JTextField pps, name, address, contactInfo;
	JLabel ppsLabel, nameLabel, addressLabel, contactLabel;
	protected JButton submit, cancel, caseSubmit, caseClear;
	private JPanel container, form, buttons, caseSelection;
	Witness witnessIn = new Witness();
	
	public EditWitness()
	{
		this.setLayout(new BorderLayout());
		form = new JPanel();
		caseSelection = new JPanel();
		caseSelection.setLayout(new GridLayout(0,4,5,5));
		caseSelection.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		form.setLayout(new GridLayout(0,2,5,5));
		form.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		pps = new JTextField();
		ppsLabel = new JLabel("Witness PPS Number to edit:");
		caseSubmit = new JButton("Submit");
		caseClear = new JButton("Clear");
		caseSubmit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				pps.setEditable(false);
				witnessIn.getWitness(pps.getText());
				name.setText(witnessIn.getName());
				address.setText(witnessIn.getAddress());
				contactInfo.setText(witnessIn.getContactInfo());
			}
					
		});
		caseClear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				pps.setEditable(true);
				clearfields();
				
			}
		});
		caseSelection.add(ppsLabel);
		caseSelection.add(pps);
		caseSelection.add(caseSubmit);
		caseSelection.add(caseClear);
		
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
		

		add(caseSelection,BorderLayout.NORTH);
		add(container, BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String information = "Witness PPS: " + pps.getText() + "\nWitness Name: " + name.getText() + "\nWitness Address: "
				+ address.getText() + "\nContact Info: " + contactInfo.getText();
				
				int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to add the follwoing Information?\n" + information);
				if(confirmation == 0)
				{
					witnessIn.setName(name.getText());
					witnessIn.setAddress(address.getText());
					witnessIn.setContactInfo(contactInfo.getText());
					witnessIn.UpdateWitness(pps.getText());
					clearfields();
				}
		
	}
	
	public void clearfields()
	{
		pps.setEditable(true);
		pps.setText(null);
		name.setText(null);
		address.setText(null);
		contactInfo.setText(null);
	}

}
