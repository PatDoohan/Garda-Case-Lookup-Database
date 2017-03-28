package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class CaseDeleteForm extends JPanel implements ActionListener{

	//Instance Variables
	private JTextField caseID, date, time, address, eircode, Status;
	private JLabel caseIDLabel, dateLabel, timeLabel, addressLabel, statusLabel,eircodeLabel;
	protected JButton delete, cancel, caseSubmit, caseClear;
	@SuppressWarnings("unused")
	private JPanel container, form, buttons, caseSelection;
	private ActiveCase inputCase = new ActiveCase();
	
	public CaseDeleteForm()
	{
		//setting border for panel
				this.setLayout(new BorderLayout());
				JPanel form = new JPanel();
				JPanel caseSelection = new JPanel();
				caseSelection.setLayout(new GridLayout(0,4,5,5));
				caseSelection.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
				form.setLayout(new GridLayout(0,2,5,5));
				form.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
				
				
				caseIDLabel = new JLabel("Please enter the Case ID you wish to Delete:");
				caseID = new JTextField();
				caseID.setPreferredSize(new Dimension(100, 100));
				caseID.setEditable(true);
				caseSubmit = new JButton();
				caseSubmit.setText("Submit");
				caseSubmit.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						caseID.setEditable(false);
						inputCase.GetCase(caseID.getText());
						String dateTime = inputCase.getDate();
						String[] dateTimeSplit = dateTime.split("\\s");
						
						date.setText(dateTimeSplit[0]);
						time.setText(dateTimeSplit[1]);
						address.setText(inputCase.getAddress());
						eircode.setText(inputCase.getEircode());
						Status.setText(inputCase.getStatus());
					}
				});
				caseClear = new JButton("Clear");
				caseClear.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						caseID.setEditable(true);
						clearFields();
						
					}
				});
				caseSelection.add(caseIDLabel);
				caseSelection.add(caseID);
				caseSelection.add(caseSubmit);
				caseSelection.add(caseClear);
				
				dateLabel = new JLabel("Date of Crime: ");
				date = new JTextField();
				date.setEditable(false);
				form.add(dateLabel);
				form.add(date);
				
				timeLabel = new JLabel("Time of Crime: ");
				time = new JTextField();
				time.setEditable(false);
				form.add(timeLabel);
				form.add(time);
				
				addressLabel = new JLabel("Address of Crime: ");
				address = new JTextField();
				address.setEditable(false);
				form.add(addressLabel);
				form.add(address);
				
				statusLabel = new JLabel("Current status of case: ");
				Status = new JTextField();
				Status.setEditable(false);
				form.add(statusLabel);
				form.add(Status);
				
				eircodeLabel = new JLabel("Eircode of Crime");
				eircode = new JTextField();
				eircode.setEditable(false);
				form.add(eircodeLabel);
				form.add(eircode);
				
				buttons = new JPanel();
				delete = new JButton("Delete");
				delete.addActionListener(this);
				cancel = new JButton("Cancel");
				buttons.add(delete);
				buttons.add(cancel);
				
				container = new JPanel();
				container.setLayout(new GridLayout(0,1));
				container.setBorder(new EmptyBorder(200, 0, 0, 0));
				container.add(form);
				container.add(buttons);
				
				add(caseSelection,BorderLayout.NORTH);
				add(container, BorderLayout.CENTER);
			}


			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//private JTextField caseID date, time, address, eircode;
				String dataConfirmation = "Case ID: " + caseID.getText() + "\nDate: " + date.getText() + "\nTime: " + time.getText() + "\nAddress: " 
				+ address.getText() + "\nStatus: " + Status.getText() + "\nEircode: " + eircode.getText();
				int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to Delete the follwoing Case?\n" + dataConfirmation);
				if(confirmation == 0)
				{
					inputCase.deleteFromDatabase(caseID.getText());
					clearFields();
					JOptionPane.showMessageDialog(null, "Case Deleted Sucessfully", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void clearFields()
			{
				caseID.setText(null);
				date.setText(null);
				time.setText(null);
				address.setText(null);
				Status.setText(null);
				eircode.setText(null);		
				caseID.setEditable(true);
			}
			
		}	
