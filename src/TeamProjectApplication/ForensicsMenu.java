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

public class ForensicsMenu extends JPanel{

	private JLabel gardaLogo, pageTitle, menulbl;
	private JPanel buttons, header, container;
	protected JButton addForensics, editForensics, deleteForensics, viewForensics, back;
	public ForensicsMenu()
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

		menulbl = new JLabel("Forensics Menu", SwingConstants.CENTER);
		menulbl.setFont(new Font("Sans-Serif",0, 40));
		buttons.add(menulbl);
		
		addForensics = new JButton("Add Forensics Report");
		addForensics.setFont(new Font("Sans-Serif", 0, 25));
		
		editForensics = new JButton("Update Forensics Report");
		editForensics.setFont(new Font("Sans-Serif", 0, 25));
		
		deleteForensics = new JButton("Delete Report");
		deleteForensics.setFont(new Font("Sans-Serif", 0, 25));
		
		viewForensics = new JButton("View Report");
		viewForensics.setFont(new Font("Sans-Serif", 0, 25));
		
		back = new JButton("Return to Evidence Menu");
		back.setFont(new Font("Sans-Serif", 0, 25));
		
		buttons.add(addForensics);
		buttons.add(editForensics);
		buttons.add(deleteForensics);
		buttons.add(viewForensics);
		buttons.add(back);
		
		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(new EmptyBorder(20,20,20,20));
		
		container.add(header, BorderLayout.NORTH);
		container.add(buttons, BorderLayout.SOUTH);
		
		add(container);	
		
	}

}
