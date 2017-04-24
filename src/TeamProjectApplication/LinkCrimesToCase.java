package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.AbstractDocument;

public class LinkCrimesToCase extends JFrame implements ActionListener{
	
	private JFormattedTextField crimeCode, CaseToLink, crimeName, CrimeDescription;
	private JLabel crimeCodeLabel, CaseLabel, crimeNameLabel, CrimeDescLabel;
	private JButton add, cancel, submit, clear;
	private JPanel search, container, buttons ;
	private Crime crimeIn = new Crime();
	
	public LinkCrimesToCase()
	{
		container = new JPanel();
		container.setLayout(new GridLayout(0,2,5,5));
		container.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		search = new JPanel();
		search.setLayout(new GridLayout(0,4,5,5));
		search.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		crimeCodeLabel = new JLabel("CrimeCode To Search:");
		crimeCode = new JFormattedTextField();
		((AbstractDocument)crimeCode.getDocument()).setDocumentFilter(new LetterFilter());
		submit = new JButton("Submit");
		clear = new JButton("Clear");
		submit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				crimeCode.setEditable(false);
				
				int crimecheck = crimeIn.checkCrime(crimeCode.getText());
				if(crimecheck == 0)
				{
					JOptionPane.showMessageDialog(null,"Crime Code " + crimeCode.getText() + " Not Found, Please Enter a valid Crime Code and try again", "Crime Code Not Found",  JOptionPane.ERROR_MESSAGE);
					clearfields();
				}
				
				else
				{
					crimeIn.getCrime(crimeCode.getText());
					add.setEnabled(true);
					crimeName.setText(crimeIn.getCrimeName());
					CrimeDescription.setText(crimeIn.getCrimeDescription());
				}
			}
		});
		
		clear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearfields();
			}
		});
		
		search.add(crimeCodeLabel);
		search.add(crimeCode);
		search.add(submit);
		search.add(clear);
		
		crimeName = new JFormattedTextField();
		crimeNameLabel = new JLabel("Crime Name: ");
		((AbstractDocument)crimeName.getDocument()).setDocumentFilter(new NumberFilter());
		crimeName.setEditable(false);
		
		CrimeDescription = new JFormattedTextField();
		CrimeDescLabel = new JLabel("Crime Description:");
		((AbstractDocument)CrimeDescription.getDocument()).setDocumentFilter(new NumberFilter());
		CrimeDescription.setEditable(false);
		
		CaseLabel = new JLabel("Case to add to: ");
		CaseToLink = new JFormattedTextField();		
		
		container.add(crimeNameLabel);
		container.add(crimeName);
		container.add(CrimeDescLabel);	
		container.add(CrimeDescription);
		container.add(CaseLabel);
		container.add(CaseToLink);
		
		add = new JButton("Add");
		add.addActionListener(this);
		add.setEnabled(false);
		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		
		buttons = new JPanel();
		submit = new JButton("Submit");
		submit.addActionListener(this);
		buttons.add(add);
		buttons.add(cancel);
		
		add(search, BorderLayout.NORTH);
		add(container, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		String information = "are you sure you wish to add crime code " + crimeCode.getText() + " To Case " + CaseLabel.getText() + "?";
		
		int confirmation = JOptionPane.showConfirmDialog(null, information);
		
		if(confirmation == 0)
		{
			crimeIn.linkToCase(CaseToLink.getText());
			clearfields();
			JOptionPane.showMessageDialog(null, "Crime Sucessfully Added", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	protected void clearfields()
	{
		crimeCode.setEditable(true);
		crimeCode.setText(null);
		crimeName.setText(null);
		CrimeDescription.setText(null);
		CaseToLink.setText(null);
	}
}
