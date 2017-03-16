package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VehicleMenu extends JPanel{

		private JLabel gardaLogo, pageTitle;
		private JPanel buttons, header, container;
		protected JButton addVehicle, editVehicle, deleteVehicle, viewVehicle, back;
		public VehicleMenu()
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
			
			addVehicle = new JButton("Add a new Vehicle");
			addVehicle.setFont(new Font("Sans-Serif", 0, 25));
			
			editVehicle = new JButton("Update an existing Vehicle");
			editVehicle.setFont(new Font("Sans-Serif", 0, 25));
			
			deleteVehicle = new JButton("Delete a Vehicle");
			deleteVehicle.setFont(new Font("Sans-Serif", 0, 25));
			
			viewVehicle = new JButton("View an existing Vehicle");
			viewVehicle.setFont(new Font("Sans-Serif", 0, 25));
			
			back = new JButton("Return to Evidence Menu");
			back.setFont(new Font("Sans-Serif", 0, 25));
			
			buttons.add(addVehicle);
			buttons.add(editVehicle);
			buttons.add(deleteVehicle);
			buttons.add(viewVehicle);
			buttons.add(back);
			
			container = new JPanel();
			container.setLayout(new BorderLayout());
			container.setBorder(new EmptyBorder(20,20,20,20));
			
			container.add(header, BorderLayout.NORTH);
			container.add(buttons, BorderLayout.SOUTH);
			
			add(container);	
			
		}

	}
	