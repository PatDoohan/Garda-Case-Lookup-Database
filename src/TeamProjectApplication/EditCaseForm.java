package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

@SuppressWarnings("serial")
public class EditCaseForm extends JPanel implements ActionListener{
	
	private JTextField caseID, date, time, address, eircode;
	private JLabel caseIDLabel, dateLabel, timeLabel, addressLabel, statusLabel,eircodeLabel;
	private String[] status = {"Open", "Active", "In Court", "Closed"};
	private String[] defaultStatus = {"Open", "Active", "In Court", "Closed"};
 	@SuppressWarnings("rawtypes")
	private JComboBox setStatus;
	protected JButton submit, cancel, caseSubmit, caseClear;
	@SuppressWarnings("unused")
	private JPanel container, form, buttons, caseSelection;
	private ActiveCase inputCase = new ActiveCase();
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EditCaseForm()
	{
		//setting border for panel
				this.setLayout(new BorderLayout());
				form = new JPanel();
				caseSelection = new JPanel();
				caseSelection.setLayout(new GridLayout(0,4,5,5));
				caseSelection.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
				form.setLayout(new GridLayout(0,2,5,5));
				form.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
				
				
				caseIDLabel = new JLabel("Please enter the Case ID you wish to Update:");
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
						
						for (int i = 0; i < status.length; i++)
						{
							if(status[i].equals(inputCase.getStatus()))
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
				caseClear = new JButton("Clear");
				caseClear.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						caseID.setEditable(true);
						clearFields();
						DefaultComboBoxModel model = new DefaultComboBoxModel(defaultStatus);
						setStatus.setModel(model);
						
					}
				});
				caseSelection.add(caseIDLabel);
				caseSelection.add(caseID);
				caseSelection.add(caseSubmit);
				caseSelection.add(caseClear);
				
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


			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//private JTextField caseID date, time, address, eircode;
				String dataConfirmation = "Case ID: " + caseID.getText() + "\nDate: " + date.getText() + "\nTime: " + time.getText() + "\nAddress: " 
				+ address.getText() + "\nStatus: " + setStatus.getSelectedItem() + "\nEircode: " + eircode.getText();
				int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to Update the follwoing Information?\n" + dataConfirmation);
				
				if(confirmation == 0)
				{
					inputCase.setDateTime(date.getText() + " " + time.getText());
					inputCase.setAddress(address.getText());
					inputCase.setActiveStatus(setStatus.getSelectedItem());
					inputCase.setEirCode(eircode.getText());
					inputCase.updateDatabase(caseID.getText());
					clearFields();
					DefaultComboBoxModel model = new DefaultComboBoxModel(defaultStatus);
					setStatus.setModel(model);
					JOptionPane.showMessageDialog(null, "Case Entered Sucessfully", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void clearFields()
			{
				caseID.setText(null);
				date.setText(null);
				time.setText(null);
				address.setText(null);
				DefaultComboBoxModel model = new DefaultComboBoxModel(defaultStatus);
				setStatus.setModel(model);
				eircode.setText(null);		
				caseID.setEditable(true);
			}
			
		}