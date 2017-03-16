package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class EvidenceMenu extends JPanel {
	
	private JLabel gardaLogo, pageTitle;
	private JPanel buttons, header, container;
	protected JButton witness, vehicle, forensics, suspect, back;
	
	public EvidenceMenu()
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
		
		suspect = new JButton("Suspect Menu");
		suspect.setFont(new Font("Sans-Serif", 0, 25));
		
		witness = new JButton("Witness Menu");
		witness.setFont(new Font("Sans-Serif", 0, 25));
		
		vehicle = new JButton("Vehicle Menu");
		vehicle.setFont(new Font("Sans-Serif", 0, 25));
		
		forensics = new JButton("Forensics Menu");
		forensics.setFont(new Font("Sans-Serif", 0, 25));
		
		back = new JButton("Return to Main Menu");
		back.setFont(new Font("Sans-Serif", 0, 25));
		
		buttons.add(suspect);
		buttons.add(witness);
		buttons.add(vehicle);
		buttons.add(forensics);
		buttons.add(back);
		
		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(new EmptyBorder(20,20,20,20));
		
		container.add(header, BorderLayout.NORTH);
		container.add(buttons, BorderLayout.SOUTH);
		
		add(container);	
		
	}

}
