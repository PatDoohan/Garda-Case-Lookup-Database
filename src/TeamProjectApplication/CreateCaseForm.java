package TeamProjectApplication;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.AbstractDocument;
import org.jdatepicker.impl.*;

@SuppressWarnings("serial")
public class CreateCaseForm extends JPanel implements ActionListener{

	//Instance Varibales
	private JFormattedTextField caseID, address, eircode, crimecode1, crimecode2, crimecode3, crimecode4, crimecode5;
	private JLabel caseIDLabel, dateLabel, timeLabel, addressLabel, statusLabel, eircodeLabel, crimeLabel1, crimeLabel2, crimeLabel3, crimeLabel4, crimeLabel5;
	private String[] status = {"Open", "Active", "In Court", "Closed"};
	private JComboBox setStatus;
	protected JButton submit, cancel;
	private JPanel container, form, buttons;
	private ActiveCase inputCase = new ActiveCase();
	private Crime crimeIn = new Crime();
	JDatePickerImpl datePicker;
	TimePicker timepicker;

	public CreateCaseForm()
	{
		//setting border for panel
		this.setLayout(new BorderLayout());
		form = new JPanel();
		form.setLayout(new GridLayout(0,2,5,5));
		form.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		caseIDLabel = new JLabel("Case ID:");
		inputCase.assignID();
		caseID = new JFormattedTextField();
		caseID.setText(String.valueOf(inputCase.getCaseID()));
		caseID.setPreferredSize(new Dimension(100, 100));
		caseID.setEditable(false);
		form.add(caseIDLabel);
		form.add(caseID);
		
		addressLabel = new JLabel("Address of Crime: ");
		address = new JFormattedTextField();
		((AbstractDocument)address.getDocument()).setDocumentFilter(new SpecialCharacterFilter());
		form.add(addressLabel);
		form.add(address);
		
		dateLabel = new JLabel("Date of Crime: ");
		UtilDateModel model = new UtilDateModel();
		//model.setDate(20,04,2014);
		// Need this...
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		// Don't know about the formatter, but there it is...
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		form.add(dateLabel);
		form.add(datePicker);
		
		timeLabel = new JLabel("Time of Crime: ");
		timepicker = new TimePicker();
		form.add(timeLabel);
		form.add(timepicker);
	
		statusLabel = new JLabel("Current status of case: ");
		setStatus = new JComboBox(status);
		form.add(statusLabel);
		form.add(setStatus);
		
		eircodeLabel = new JLabel("Eircode of Crime");
		eircode = new JFormattedTextField();
		((AbstractDocument)eircode.getDocument()).setDocumentFilter(new SpecialCharacterFilter());
		form.add(eircodeLabel);
		form.add(eircode);
		
		crimeLabel1 = new JLabel("Crime Code 1: ");
		crimecode1 = new JFormattedTextField();
		((AbstractDocument)crimecode1.getDocument()).setDocumentFilter(new LetterFilter());
		form.add(crimeLabel1);
		form.add(crimecode1);
		
		crimeLabel2 = new JLabel("Crime Code 2: ");
		crimecode2 = new JFormattedTextField();
		((AbstractDocument)crimecode2.getDocument()).setDocumentFilter(new LetterFilter());
		form.add(crimeLabel2);
		form.add(crimecode2);
		
		crimeLabel3 = new JLabel("Crime Code 3: ");
		crimecode3 = new JFormattedTextField();
		((AbstractDocument)crimecode3.getDocument()).setDocumentFilter(new LetterFilter());
		form.add(crimeLabel3);
		form.add(crimecode3);
		
		crimeLabel4 = new JLabel("Crime Code 4: ");
		crimecode4 = new JFormattedTextField();
		((AbstractDocument)crimecode4.getDocument()).setDocumentFilter(new LetterFilter());
		form.add(crimeLabel4);
		form.add(crimecode4);
		
		crimeLabel5 = new JLabel("Crime Code 5: ");
		crimecode5 = new JFormattedTextField();
		((AbstractDocument)crimecode5.getDocument()).setDocumentFilter(new LetterFilter());
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
		String dataConfirmation = "Case ID: " + caseID.getText() + "\nDate: " + datePicker.getJFormattedTextField().getText() + "\nTime: " + timepicker.getTime() + "\nAddress: " 
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
			if(crimecheck1 == 0 && !crimecode1.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Crime Code " + crimecode1.getText() + " Not Found, Please Enter a Crime Code 1 and try again", "Crime Code Not Found",  JOptionPane.ERROR_MESSAGE);
				allClear = false;
			}
			
			else if(crimecheck2 == 0 && !crimecode2.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Crime Code " + crimecode2.getText() + " Not Found, Please Enter Crime Code 2 and try again", "Crime Code Not Found",  JOptionPane.ERROR_MESSAGE);
				allClear = false;
			}
			
			else if(crimecheck3 == 0 && !crimecode3.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Crime Code " + crimecode3.getText() + " Not Found, Please Enter Crime Code 3 and try again", "Crime Code Not Found",  JOptionPane.ERROR_MESSAGE);
				allClear = false;
			}
			
			else if(crimecheck4 == 0 && !crimecode4.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Crime Code " + crimecode4.getText() + " Not Found, Please Enter Crime Code 4 and try again", "Crime Code Not Found",  JOptionPane.ERROR_MESSAGE);
				allClear = false;
			}
			
			else if(crimecheck5 == 0 && !crimecode5.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Crime Code " + crimecode5.getText() + " Not Found, Please Enter Crime Code 5 and try again", "Crime Code Not Found",  JOptionPane.ERROR_MESSAGE);
				allClear = false;
			}
		}
		
		if(confirmation == 0 && allClear == true)
		{
			inputCase.setDate(datePicker.getJFormattedTextField().getText());
			inputCase.setTime(timepicker.getTime());
			inputCase.setAddress(address.getText());
			inputCase.setActiveStatus(setStatus.getSelectedItem());
			inputCase.setEirCode(eircode.getText());
			inputCase.addToDatabase();
			
			if(!crimecode1.getText().isEmpty())
			{
				crimeIn.getCrime(crimecode1.getText());
				crimeIn.linkToCase(String.valueOf(inputCase.getCaseID()));
			}
			
			if(!crimecode2.getText().isEmpty())
			{
				crimeIn.getCrime(crimecode2.getText());
				crimeIn.linkToCase(String.valueOf(inputCase.getCaseID()));
			}
			
			if(!crimecode3.getText().isEmpty())
			{
				crimeIn.getCrime(crimecode3.getText());
				crimeIn.linkToCase(String.valueOf(inputCase.getCaseID()));
			}
			
			if(!crimecode4.getText().isEmpty())
			{
				crimeIn.getCrime(crimecode4.getText());
				crimeIn.linkToCase(String.valueOf(inputCase.getCaseID()));
			}
			
			if(!crimecode5.getText().isEmpty())
			{
				crimeIn.getCrime(crimecode5.getText());
				crimeIn.linkToCase(String.valueOf(inputCase.getCaseID()));
			}
			
			inputCase.assignID();
			clearFields();
			JOptionPane.showMessageDialog(null, "Case Entered Sucessfully", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		}		
	}
	
	public void clearFields()
	{
		caseID.setText(String.valueOf(inputCase.getCaseID()));
		timepicker.resetTimer();
		datePicker.getJFormattedTextField().setText("");
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
