package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SuspectMenu extends JPanel{

	private JLabel gardaLogo, pageTitle;
	private JPanel buttons, header, container;
	protected JButton addSuspect, editSuspect, deleteSuspect, viewSuspect, back;
	public SuspectMenu()
	{
		header = new JPanel();
		header.setBorder(new EmptyBorder(0,0,120,0));
		gardaLogo = new JLabel(new ImageIcon("Images/gardaLogo.png"));
		gardaLogo.setAlignmentY(LEFT_ALIGNMENT);
		pageTitle = new JLabel("Garda Case Tracking Database");
		pageTitle.setAlignmentY(LEFT_ALIGNMENT);
		pageTitle.setFont(new Font("Sans-Serif",0, 40));
		header.add(gardaLogo);
		header.add(pageTitle);
		
		buttons = new JPanel();
		buttons.setLayout(new GridLayout (0,1, 20,20));
		
		addSuspect = new JButton("Add a new suspect");
		addSuspect.setFont(new Font("Sans-Serif", 0, 25));
		
		editSuspect = new JButton("Update an existing suspect");
		editSuspect.setFont(new Font("Sans-Serif", 0, 25));
		
		deleteSuspect = new JButton("Delete a suspect");
		deleteSuspect.setFont(new Font("Sans-Serif", 0, 25));
		
		viewSuspect = new JButton("View an existing suspect");
		viewSuspect.setFont(new Font("Sans-Serif", 0, 25));
		
		back = new JButton("Return to Evidence Menu");
		back.setFont(new Font("Sans-Serif", 0, 25));
		
		buttons.add(addSuspect);
		buttons.add(editSuspect);
		buttons.add(deleteSuspect);
		buttons.add(viewSuspect);
		buttons.add(back);
		
		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(new EmptyBorder(20,20,20,20));
		
		container.add(header, BorderLayout.NORTH);
		container.add(buttons, BorderLayout.SOUTH);
		
		add(container);	
		
	}

}