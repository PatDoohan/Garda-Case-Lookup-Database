package TeamProjectApplication;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;

public class ViewVehicle extends JFrame{
	
	private JTable vehicle;
	private JScrollPane vehicleTable;
	private int caseID;
	private JButton close;
	private JPanel container, buttons;
	private JLabel caseLabel;
	private Statement st;
	private String sql;
	private ResultSet rs;
	
	public ViewVehicle(int caseNumber)
	{
		caseID = caseNumber;
		vehicle = new JTable();
		container = new JPanel();
		container.setLayout(new GridLayout(0,1));
		container.setBorder(new EmptyBorder(20,20,20,20));
		
		try
		{
			//Load the JDBC driver, Initialize a driver to open a communications channel with the database.
			Class.forName("com.mysql.jdbc.Driver");
			//connection for MYSQL workbench.
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			//Create Statement object	
			st = con.createStatement();
			
			sql = "SELECT reg, type, make, model, colour, description from vehicle, caseevidence "
			+ "where caseevidence.caseID = " + caseID + " and caseevidence.EvidenceID = vehicle.EvidenceID;";
			//executes the above sql statement
			rs = st.executeQuery(sql);
			
			if(!rs.next())
			{
				JOptionPane.showMessageDialog(null, "No vehicles to display for this case", "No vehicles on file for case", JOptionPane.ERROR_MESSAGE);
			}
			rs.previous();
			vehicle.setModel(DbUtils.resultSetToTableModel(rs));
			vehicle.getColumnModel().getColumn(5).setPreferredWidth(200);
			vehicle.setFont(new Font("Sans-Serif", 0, 14));
			
			//closes the connection
			con.close();
		}
		//catches errors thrown by database
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		
		caseLabel = new JLabel("Viewing vehicles for case number: " + caseID);
		caseLabel.setFont(new Font("Sans-Serif", 0, 25));
	
		close = new JButton("Close");
		close.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}	
		});
		//close.setPreferredSize(new Dimension(100, 100));
		close.setSize(new Dimension(100, 100));
		
		buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		buttons.add(close);
	
		vehicleTable = new JScrollPane(vehicle);
		container.add(caseLabel);
		container.add(vehicleTable);
		container.add(buttons);
		
		add(container);
		
	
	}
}
