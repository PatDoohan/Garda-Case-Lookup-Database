package TeamProjectApplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CaseMenu extends JPanel implements ActionListener{
	
	private JLabel gardaLogo, pageTitle;
	protected JButton createCase, updateCase, deleteCase, viewCase ,back;
	private JPanel buttons, header, container;
	private int caseID, crimeCode;
	private String date, time;
	
	public CaseMenu()
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
		
		createCase  = new JButton("Create a new case");
		createCase.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(createCase);
		
		updateCase = new JButton("Update an existing case");
		updateCase.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(updateCase);
		
		deleteCase = new JButton("Delete an existing case");
		deleteCase.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(deleteCase);
		
		viewCase = new JButton("View an Existing Case");
		viewCase.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(viewCase);
		
		back = new JButton("Return to main menu");
		back.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(back);
		
		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(new EmptyBorder(20,20,20,20));
		
		container.add(header, BorderLayout.NORTH);
		container.add(buttons, BorderLayout.SOUTH);
		
		add(container);		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
