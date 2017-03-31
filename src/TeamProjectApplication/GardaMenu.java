package TeamProjectApplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GardaMenu extends JPanel implements ActionListener{
	
	protected JButton caseLookup, EvidenceMenu, suspectLookup, vehicleLookup, mapLookup, CrimeStatistics, Logout;
	protected JLabel gardaLogo, pageTitle, menulbl;
	protected JPanel buttons, header, container;
	
	
	public GardaMenu()
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
		buttons.setLayout(new GridLayout (0,1, 20, 20));
		
		menulbl = new JLabel("Main Menu", SwingConstants.CENTER);
		menulbl.setFont(new Font("Sans-Serif",0, 40));
		buttons.add(menulbl);
		
		caseLookup = new JButton("Case");
		caseLookup.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(caseLookup);
		
		EvidenceMenu = new JButton("Evidence");
		EvidenceMenu.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(EvidenceMenu);
		
		
		CrimeStatistics = new JButton("Crime Statistics");
		CrimeStatistics.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(CrimeStatistics);
		
		Logout = new JButton("Logout");
		Logout.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(Logout);
		
		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(new EmptyBorder(20,20,20,20));
		
		container.add(header, BorderLayout.NORTH);
		container.add(buttons, BorderLayout.SOUTH);
		
		
		add(container,BorderLayout.CENTER);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
