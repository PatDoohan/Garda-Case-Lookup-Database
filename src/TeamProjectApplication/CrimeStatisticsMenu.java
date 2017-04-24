package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.MaskFormatter;

public class CrimeStatisticsMenu extends JPanel{

	private JFormattedTextField year;
	private JLabel yearLabel, monthLabel;
	private JPanel yearpanel,buttons, months;
	private JButton january, febuary, march, april, may, june, july, august,september, october, november, december;
	protected JButton back;	
	
	public CrimeStatisticsMenu()
	{
		this.setLayout(new BorderLayout());

		yearpanel = new JPanel();
		yearpanel.setLayout(new GridLayout(0,2,5,5));
		yearpanel.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,700))));
		
		yearLabel = new JLabel("Enter Year to view crimes for that year: ");
		year = new JFormattedTextField();
		((AbstractDocument)year.getDocument()).setDocumentFilter(new LetterFilter());
		
		yearpanel.add(yearLabel);
		yearpanel.add(year);
	
		monthLabel = new JLabel("Select A Month for the above year:");
		monthLabel.setFont(new Font("Sans-Serif", 0, 20));
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
	
		january = new JButton("January");
		january.setPreferredSize(new Dimension(300, 60));
		january.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int yearEntered = Integer.valueOf(year.getText());
				if(yearEntered > currentYear )
				{
					JOptionPane.showMessageDialog(null, "Invalid Year Entered, Please Re-enter and try again" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else if(yearEntered == currentYear && currentMonth < 01)
				{
					JOptionPane.showMessageDialog(null, "Month has not occured this year, please select another Month" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else
				{
					ViewCrimeStatistic frame = new ViewCrimeStatistic("01",year.getText());
				    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    frame.setTitle("Suspect Viewer");
				    frame.setSize(900, 350);
				    frame.setLocationRelativeTo(null);
				    frame.setVisible(true);
				}
				
			}	
		});
		
		febuary = new JButton("Febuary");
		febuary.setPreferredSize(new Dimension(300, 60));
		febuary.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int yearEntered = Integer.valueOf(year.getText());
				if(yearEntered > currentYear )
				{
					JOptionPane.showMessageDialog(null, "Invalid Year Entered, Please Re-enter and try again" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else if(yearEntered == currentYear && currentMonth < 02)
				{
					JOptionPane.showMessageDialog(null, "Month has not occured this year, please select another Month" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else
				{
					ViewCrimeStatistic frame = new ViewCrimeStatistic("02",year.getText());
				    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    frame.setTitle("Suspect Viewer");
				    frame.setSize(900, 350);
				    frame.setLocationRelativeTo(null);
				    frame.setVisible(true);
				}
			}	
		});
		
		march = new JButton("March");
		march.setPreferredSize(new Dimension(300, 60));
		march.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int yearEntered = Integer.valueOf(year.getText());
				if(yearEntered > currentYear )
				{
					JOptionPane.showMessageDialog(null, "Invalid Year Entered, Please Re-enter and try again" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else if(yearEntered == currentYear && currentMonth < 03)
				{
					JOptionPane.showMessageDialog(null, "Month has not occured this year, please select another Month" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else
				{
					ViewCrimeStatistic frame = new ViewCrimeStatistic("03",year.getText());
				    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    frame.setTitle("Suspect Viewer");
				    frame.setSize(900, 350);
				    frame.setLocationRelativeTo(null);
				    frame.setVisible(true);
				}
			}	
		});
		
		april = new JButton("April");
		april.setPreferredSize(new Dimension(300, 60));
		april.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				int yearEntered = Integer.valueOf(year.getText());
				if(yearEntered > currentYear )
				{
					JOptionPane.showMessageDialog(null, "Invalid Year Entered, Please Re-enter and try again" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else if(yearEntered == currentYear && currentMonth < 04)
				{
					JOptionPane.showMessageDialog(null, "Month has not occured this year, please select another Month" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else
				{
					ViewCrimeStatistic frame = new ViewCrimeStatistic("04",year.getText());
				    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    frame.setTitle("Suspect Viewer");
				    frame.setSize(900, 350);
				    frame.setLocationRelativeTo(null);
				    frame.setVisible(true);
				}
			}	
		});
		
		may = new JButton("May");
		may.setPreferredSize(new Dimension(300, 60));
		may.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int yearEntered = Integer.valueOf(year.getText());
				if(yearEntered > currentYear )
				{
					JOptionPane.showMessageDialog(null, "Invalid Year Entered, Please Re-enter and try again" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else if(yearEntered == currentYear && currentMonth < 05)
				{
					JOptionPane.showMessageDialog(null, "Month has not occured this year, please select another Month" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else
				{
					ViewCrimeStatistic frame = new ViewCrimeStatistic("05",year.getText());
				    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    frame.setTitle("Suspect Viewer");
				    frame.setSize(900, 350);
				    frame.setLocationRelativeTo(null);
				    frame.setVisible(true);
				}
			}	
		});
		
		june = new JButton("June");
		june.setPreferredSize(new Dimension(300, 60));
		june.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int yearEntered = Integer.valueOf(year.getText());
				if(yearEntered > currentYear )
				{
					JOptionPane.showMessageDialog(null, "Invalid Year Entered, Please Re-enter and try again" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else if(yearEntered == currentYear && currentMonth < 06)
				{
					JOptionPane.showMessageDialog(null, "Month has not occured this year, please select another Month" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else
				{
					ViewCrimeStatistic frame = new ViewCrimeStatistic("06",year.getText());
				    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    frame.setTitle("Suspect Viewer");
				    frame.setSize(900, 350);
				    frame.setLocationRelativeTo(null);
				    frame.setVisible(true);
				}
			}	
		});
		
		july = new JButton("July");
		july.setPreferredSize(new Dimension(300, 60));
		july.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int yearEntered = Integer.valueOf(year.getText());
				if(yearEntered > currentYear )
				{
					JOptionPane.showMessageDialog(null, "Invalid Year Entered, Please Re-enter and try again" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else if(yearEntered == currentYear && currentMonth < 07)
				{
					JOptionPane.showMessageDialog(null, "Month has not occured this year, please select another Month" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else
				{
					ViewCrimeStatistic frame = new ViewCrimeStatistic("07",year.getText());
				    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    frame.setTitle("Suspect Viewer");
				    frame.setSize(900, 350);
				    frame.setLocationRelativeTo(null);
				    frame.setVisible(true);
				}
			}	
		});
		
		august = new JButton("August");
		august.setPreferredSize(new Dimension(300, 60));
		august.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int yearEntered = Integer.valueOf(year.getText());
				if(yearEntered > currentYear )
				{
					JOptionPane.showMessageDialog(null, "Invalid Year Entered, Please Re-enter and try again" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else if(yearEntered == currentYear && currentMonth < 8)
				{
					JOptionPane.showMessageDialog(null, "Month has not occured this year, please select another Month" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else
				{
					ViewCrimeStatistic frame = new ViewCrimeStatistic("08",year.getText());
				    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    frame.setTitle("Suspect Viewer");
				    frame.setSize(900, 350);
				    frame.setLocationRelativeTo(null);
				    frame.setVisible(true);
				}
			}	
		});
		
		september = new JButton("September");
		september.setPreferredSize(new Dimension(300, 60));
		september.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int yearEntered = Integer.valueOf(year.getText());
				if(yearEntered > currentYear )
				{
					JOptionPane.showMessageDialog(null, "Invalid Year Entered, Please Re-enter and try again" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else if(yearEntered == currentYear && currentMonth < 9)
				{
					JOptionPane.showMessageDialog(null, "Month has not occured this year, please select another Month" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else
				{
					ViewCrimeStatistic frame = new ViewCrimeStatistic("09",year.getText());
				    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    frame.setTitle("Suspect Viewer");
				    frame.setSize(900, 350);
				    frame.setLocationRelativeTo(null);
				    frame.setVisible(true);
				}
			}	
		});
		
		october = new JButton("October");
		october.setPreferredSize(new Dimension(300, 60));
		october.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int yearEntered = Integer.valueOf(year.getText());
				if(yearEntered > currentYear )
				{
					JOptionPane.showMessageDialog(null, "Invalid Year Entered, Please Re-enter and try again" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else if(yearEntered == currentYear && currentMonth < 10)
				{
					JOptionPane.showMessageDialog(null, "Month has not occured this year, please select another Month" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else
				{
					ViewCrimeStatistic frame = new ViewCrimeStatistic("10",year.getText());
				    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    frame.setTitle("Suspect Viewer");
				    frame.setSize(900, 350);
				    frame.setLocationRelativeTo(null);
				    frame.setVisible(true);
				}
			}	
		});
		
		november = new JButton("November");
		november.setPreferredSize(new Dimension(300, 60));
		november.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int yearEntered = Integer.valueOf(year.getText());
				if(yearEntered > currentYear )
				{
					JOptionPane.showMessageDialog(null, "Invalid Year Entered, Please Re-enter and try again" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else if(yearEntered == currentYear && currentMonth < 11)
				{
					JOptionPane.showMessageDialog(null, "Month has not occured this year, please select another Month" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else
				{
					ViewCrimeStatistic frame = new ViewCrimeStatistic("11",year.getText());
				    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    frame.setTitle("Suspect Viewer");
				    frame.setSize(900, 350);
				    frame.setLocationRelativeTo(null);
				    frame.setVisible(true);
				}
			}	
		});
		
		december = new JButton("December");
		december.setPreferredSize(new Dimension(300, 60));
		december.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int yearEntered = Integer.valueOf(year.getText());
				if(yearEntered > currentYear )
				{
					JOptionPane.showMessageDialog(null, "Invalid Year Entered, Please Re-enter and try again" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else if(yearEntered == currentYear && currentMonth < 12)
				{
					JOptionPane.showMessageDialog(null, "Month has not occured this year, please select another Month" , "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else
				{
					ViewCrimeStatistic frame = new ViewCrimeStatistic("12",year.getText());
				    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    frame.setTitle("Suspect Viewer");
				    frame.setSize(900, 350);
				    frame.setLocationRelativeTo(null);
				    frame.setVisible(true);
				}
			}	
		});
		
		months = new JPanel();
		months.setLayout(new FlowLayout());
		months.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,800))));
		
		months.add(monthLabel);
		months.add(january);
		months.add(febuary);
		months.add(march);
		months.add(april);
		months.add(may);
		months.add(june);
		months.add(july);
		months.add(august);
		months.add(september);
		months.add(october);
		months.add(november);
		months.add(december);
		
		back = new JButton("Return to main menu");
		buttons = new JPanel();
		buttons.add(back);
		
		add(yearpanel, BorderLayout.NORTH);
		add(months, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
		
	}
	
	protected MaskFormatter TextFormatter(String s) 
	{
	    MaskFormatter formatter = null;
	    try 
	    {
	        formatter = new MaskFormatter(s);
	    } 
	    catch (java.text.ParseException exc) 
	    {
	        System.err.println("formatter is bad: " + exc.getMessage());
	        System.exit(-1);
	    }
	    return formatter;
	}

}
