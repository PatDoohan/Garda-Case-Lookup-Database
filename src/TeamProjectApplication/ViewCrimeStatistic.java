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

public class ViewCrimeStatistic extends JFrame{

	private JTable statistic;
	private JScrollPane statisticTable;
	private JButton close;
	private JPanel container, buttons;
	private JLabel caseLabel;
	private Statement st;
	private String sql;
	private ResultSet rs;
	
	public ViewCrimeStatistic(String month, String year)
	{
		
		statistic = new JTable();
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
			


			sql = "Select crime.crimeCode As 'Crime Code', crime.crimeName AS 'Crime Name', count(crime.crimeCode) AS 'Number Of Crimes' from crime, committedcrime, activecase where crime."
					+ "crimeCode = committedcrime.CrimeCode and committedcrime.CaseID = activecase.caseID and "
					+ "activecase.Date like '%"+year+"-"+month+"%' Group by crime.crimeCode;";
			
			//executes the above sql statement
			rs = st.executeQuery(sql);
			
			statistic.setModel(DbUtils.resultSetToTableModel(rs));
			statistic.setFont(new Font("Sans-Serif", 0, 14));
			
			//closes the connection
			con.close();
		}
		//catches errors thrown by database
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		
		caseLabel = new JLabel("Viewing Statistics for " + year + "/" + month);
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
	
		statisticTable = new JScrollPane(statistic);
		container.add(caseLabel);
		container.add(statisticTable);
		container.add(buttons);
		
		add(container);
	}
}
