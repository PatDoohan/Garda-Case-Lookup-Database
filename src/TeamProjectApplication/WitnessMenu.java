package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class WitnessMenu extends JPanel {

	private JLabel gardaLogo, pageTitle, menulbl;
	private JPanel buttons, header, container;
	protected JButton addWitness, editWitness, deleteWitness, viewWitness, back;
	public WitnessMenu()
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

		menulbl = new JLabel("Witness Menu", SwingConstants.CENTER);
		menulbl.setFont(new Font("Sans-Serif",0, 40));
		buttons.add(menulbl);
		
		addWitness = new JButton("Add Witness");
		addWitness.setFont(new Font("Sans-Serif", 0, 25));
		
		editWitness = new JButton("Update Witness");
		editWitness.setFont(new Font("Sans-Serif", 0, 25));
		
		deleteWitness = new JButton("Delete Witness");
		deleteWitness.setFont(new Font("Sans-Serif", 0, 25));
		
		viewWitness = new JButton("View Witness");
		viewWitness.setFont(new Font("Sans-Serif", 0, 25));
		
		back = new JButton("Return to Evidence Menu");
		back.setFont(new Font("Sans-Serif", 0, 25));
		
		buttons.add(addWitness);
		buttons.add(editWitness);
		buttons.add(deleteWitness);
		buttons.add(viewWitness);
		buttons.add(back);
		
		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(new EmptyBorder(20,20,20,20));
		
		container.add(header, BorderLayout.NORTH);
		container.add(buttons, BorderLayout.SOUTH);
		
		add(container);	
		
	}

}