package TeamProjectApplication;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;


public class DeleteSuspectForm extends JPanel implements ActionListener{

		private JTextField SuspectID, pps, name, address, description, priorConvictions;
		private JLabel SuspectIDLabel, ppsLabel, nameLabel, addressLabel, descriptionLabel, priorConvictionsLabel, statusLabel;
		private String[] status = {"Suspect", "Fugitive", "Arrested", "Processed"};
		private String[] defaultstatus = {"Suspect", "Fugitive", "Arrested", "Processed"};
		private JComboBox setStatus;
		protected JButton submit, cancel, caseSubmit, caseClear;;
		private JPanel container, form, buttons, caseSelection;
		Suspect suspectIn = new Suspect();
		
		public DeleteSuspectForm()
		{
			this.setLayout(new BorderLayout());
			form = new JPanel();
			caseSelection = new JPanel();
			caseSelection.setLayout(new GridLayout(0,4,5,5));
			caseSelection.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
			form.setLayout(new GridLayout(0,2,5,5));
			form.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
			
			SuspectIDLabel = new JLabel("Suspect ID To Delete: ");
			SuspectID = new JTextField();
			caseSubmit = new JButton("Submit");
			caseClear = new JButton("Clear");
			caseSubmit.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					SuspectID.setEditable(false);
					suspectIn.getSuspect(Integer.valueOf(SuspectID.getText()));
					//System.out.println(suspectIn.getSuspectPPS());
					pps.setText(suspectIn.getSuspectPPS());
					name.setText(suspectIn.getName());
					address.setText(suspectIn.getAddress());
					priorConvictions.setText(suspectIn.getSuspectPriors());
					description.setText(suspectIn.getSuspectDescription());
					
					for (int i = 0; i < status.length; i++)
					{
						if(status[i].equals(suspectIn.getSuspectStatus()))
						{
							String temp = status[0];
							status[0] = status [i];
							status[i] = temp;
						}
					}
					DefaultComboBoxModel model = new DefaultComboBoxModel(status);
					setStatus.setModel(model);
					
				}
						
			});
			caseClear.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					SuspectID.setEditable(true);
					clearfields();
					DefaultComboBoxModel model = new DefaultComboBoxModel(defaultstatus);
					setStatus.setModel(model);
					
				}
			});
			
			caseSelection.add(SuspectIDLabel);
			caseSelection.add(SuspectID);
			caseSelection.add(caseSubmit);
			caseSelection.add(caseClear);
			
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
			submit = new JButton("Delete");
			submit.addActionListener(this);
			cancel = new JButton("Cancel");
			buttons.add(submit);
			buttons.add(cancel);
			
			container = new JPanel();
			container.setLayout(new GridLayout(0,1));
			container.setBorder(new EmptyBorder(200, 0, 0, 0));
			container.add(form);
			container.add(buttons);
			
			pps.setEditable(false);
			address.setEditable(false);
			name.setEditable(false);
			priorConvictions.setEditable(false);
			description.setEditable(false);
			setStatus.setEditable(false);
			
			add(caseSelection,BorderLayout.NORTH);
			add(container, BorderLayout.CENTER);
			
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			

			String suspectInformation = "Suspect ID: " + SuspectID.getText() + "\nSuspect PPS: " + pps.getText() + "\nName: " + name.getText()
			+ "\nAddress: " + address.getText() + "\nDescription: " + description.getText() + "\nPriorConvictions: " + priorConvictions.getText()
			+ "\nCurrent Status: " + setStatus.getSelectedItem();
			int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to Delete the follwoing Information?\n" + suspectInformation);
			if(confirmation == 0)
			{
				suspectIn.setPPS(pps.getText());
				suspectIn.setName(name.getText());
				suspectIn.setAddress(address.getText());
				suspectIn.setSuspectDescription(description.getText());
				suspectIn.setSuspectPriors(priorConvictions.getText());
				suspectIn.setSuspectStatus(setStatus.getSelectedItem());	
				suspectIn.deleteFromDatabase(SuspectID.getText());
				clearfields();
				DefaultComboBoxModel model = new DefaultComboBoxModel(defaultstatus);
				setStatus.setModel(model);
			}
		
		}
		
		public void clearfields()
		{
			SuspectID.setText(String.valueOf(suspectIn.getSuspectID()));
			SuspectID.setEditable(true);
			pps.setText(null);
			name.setText(null);
			address.setText(null);
			description.setText(null);
			priorConvictions.setText(null);
			SuspectID.setText(null);
			suspectIn.clearData();
		}

}
