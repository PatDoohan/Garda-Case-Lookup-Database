package TeamProjectApplication;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TimePicker extends JPanel{

	private String[] hour = new String[24];
	private String[] Defaulthour = new String[24];;
	private String[] minute = new String[60];;
	private String[] Defaultminute = new String[60];;
	private JComboBox hours, minutes;
	
	public TimePicker()
	{
		this.setLayout(new GridLayout(1,0));
		this.setBorder(new EmptyBorder(0,0 ,0 ,400));
		for(int i = 0; i < 24; i++)
		{
			if(i <10)
			{
				Defaulthour[i] = "0" + String.valueOf(i);
				hour[i] = "0" + String.valueOf(i);
			}
			
			else
			{
				Defaulthour[i] = String.valueOf(i);
				hour[i] = String.valueOf(i);
				
			}
			
		}
		
		for(int i = 0; i <60; i++)
		{
			if(i < 10)
			{
				Defaultminute[i] = "0" + String.valueOf(i);
				minute[i] = "0" + String.valueOf(i);
			}
			else
			{
				Defaultminute[i] = String.valueOf(i);
				minute[i] = String.valueOf(i);
			}
			
		}
		
		hours = new JComboBox(hour);
		hours.setPrototypeDisplayValue("24");
		add(hours);
		minutes = new JComboBox(minute);
		hours.setPrototypeDisplayValue("60");
		add(minutes);
	}
	
	public String getTime()
	{
		String getHours = String.valueOf(hours.getSelectedItem());
		String getMinutes = String.valueOf(minutes.getSelectedItem());
		
		return getHours + ":" + getMinutes;
	}
	
	public void resetTimer()
	{
		DefaultComboBoxModel hourmodel = new DefaultComboBoxModel(Defaulthour);
		hours.setModel(hourmodel);
		
		DefaultComboBoxModel minutemodel = new DefaultComboBoxModel(Defaultminute);
		minutes.setModel(minutemodel);
	}
	
	public void setTime(String hoursIn, String minutesIn)
	{
		hours.setSelectedItem(hoursIn);
		minutes.setSelectedItem(minutesIn);
	}
}
