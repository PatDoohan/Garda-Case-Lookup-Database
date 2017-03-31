package TeamProjectApplication;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;


@SuppressWarnings("serial")
public class AddSuspectForm extends JPanel implements ActionListener{
	
	private JTextField SuspectID, pps, name, address, description, priorConvictions, caseNumber;
	private JLabel SuspectIDLabel, ppsLabel, nameLabel, addressLabel, descriptionLabel, priorConvictionsLabel, statusLabel, caseNumberLabel;
	private String[] status = {"Suspect", "Fugitive", "Arrested", "Processed"};
	private JComboBox setStatus;
	protected JButton submit, cancel;
	private JPanel container, form, buttons;
	Suspect suspectIn = new Suspect();
	
	
	public AddSuspectForm()
	{
		this.setLayout(new BorderLayout());
		form = new JPanel();
		form.setLayout(new GridLayout(0,2,5,5));
		form.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		caseNumberLabel = new JLabel("Suspect in Case:");
		caseNumber = new JTextField();
		form.add(caseNumberLabel);
		form.add(caseNumber);
		
		SuspectIDLabel = new JLabel("SuspectID: ");
		SuspectID = new JTextField();
		SuspectID.setEditable(false);
		suspectIn.assignID();
		SuspectID.setText(String.valueOf(suspectIn.getSuspectID()));
		form.add(SuspectIDLabel);
		form.add(SuspectID);
		
		ppsLabel = new JLabel("Suspect PPS Number:");
		pps = new JTextField();
		form.add(ppsLabel);
		form.add(pps);
		
		nameLabel = new JLabel("Suspect Name:");
		name = new JTextField();
		form.add(nameLabel);
		form.add(name);
		
		addressLabel = new JLabel("Suspect Address:");
		address = new JTextField();
		form.add(addressLabel);
		form.add(address);
		
		descriptionLabel = new JLabel("Suspect Description:");
		description = new JTextField();
		form.add(descriptionLabel);
		form.add(description);
		
		this.priorConvictionsLabel = new JLabel("Prior Convictions:");
		this.priorConvictions = new JTextField();
		form.add(priorConvictionsLabel);
		form.add(priorConvictions);
		
		this.statusLabel = new JLabel("Current Status:");
		this.setStatus = new JComboBox(status);
		form.add(statusLabel);
		form.add(setStatus);
				
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
		
		String suspectInformation = "Suspect ID: " + SuspectID.getText() + "\nSuspect PPS: " + pps.getText() + "\nName: " + name.getText()
		+ "\nAddress: " + address.getText() + "\nDescription: " + description.getText() + "\nPriorConvictions: " + priorConvictions.getText()
		+ "\nCurrent Status: " + setStatus.getSelectedItem();
		
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to add the follwoing Information?\n" + suspectInformation);
		if(confirmation == 0)
		{
			suspectIn.setSuspectPPS(pps.getText());
			suspectIn.setName(name.getText());
			suspectIn.setAddress(address.getText());
			suspectIn.setSuspectDescription(description.getText());
			suspectIn.setSuspectPriors(priorConvictions.getText());
			suspectIn.setSuspectStatus(setStatus.getSelectedItem());
			suspectIn.linkToCase(Integer.valueOf(caseNumber.getText()));	
			suspectIn.addToDatabase();
			clearfields();
		}
	}
	
	public void clearfields()
	{
		suspectIn.assignID();
		SuspectID.setText(String.valueOf(suspectIn.getSuspectID()));
		pps.setText(null);
		name.setText(null);
		address.setText(null);
		description.setText(null);
		priorConvictions.setText(null);
		caseNumber.setText(null);
	}
}
