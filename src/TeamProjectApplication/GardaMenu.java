package TeamProjectApplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GardaMenu extends JPanel implements ActionListener{
	
	protected JButton caseLookup, EvidenceMenu, suspectLookup, vehicleLookup, mapLookup, CrimeStatistics;
	protected JLabel gardaLogo, pageTitle;
	protected JPanel buttons, header, container;
	
	
	public GardaMenu()
	{
		header = new JPanel();
		header.setBorder(new EmptyBorder(0,0,80,0));
		gardaLogo = new JLabel(new ImageIcon("Images/gardaLogo.png"));
		gardaLogo.setAlignmentY(LEFT_ALIGNMENT);
		pageTitle = new JLabel("Garda Case Tracking Database");
		pageTitle.setAlignmentY(LEFT_ALIGNMENT);
		pageTitle.setFont(new Font("Sans-Serif",0, 40));
		header.add(gardaLogo);
		header.add(pageTitle);
		
		buttons = new JPanel();
		buttons.setLayout(new GridLayout (1,4, 20, 20));
		
		caseLookup = new JButton("Case Menu");
		caseLookup.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(caseLookup);
		
		EvidenceMenu = new JButton("Evidence Menu");
		EvidenceMenu.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(EvidenceMenu);
		
		mapLookup = new JButton("Map Menu");
		mapLookup.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(mapLookup);
		
		CrimeStatistics = new JButton("Crime Statistics");
		CrimeStatistics.setFont(new Font("Sans-Serif", 0, 25));
		buttons.add(CrimeStatistics);
		
		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(new EmptyBorder(20,20,20,20));
		
		container.add(header, BorderLayout.NORTH);
		container.add(buttons, BorderLayout.CENTER);
		
		add(container);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
