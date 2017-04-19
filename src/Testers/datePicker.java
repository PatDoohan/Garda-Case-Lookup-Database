package Testers;
import javax.swing.*;
import java.awt.*;

public class datePicker extends JFrame{
	
	String[] days31;
	String[] days30;
	String[] days28;
	String[] days29;
	String[] Months = {"January", "Febuary", "March", "April","May", "June", "July", "August", "September", "October", "November", "December"};
	
	
	public datePicker()
	{
		for(int i = 0; i < 31; i++)
		{
			days31[i] = String.valueOf(i+1);
		}
		
		for(int i = 0; i < 30; i++)
		{
			days30[i] = String.valueOf(i+1);
		}
		
		for(int i = 0; i < 28; i++)
		{
			days28[i] = String.valueOf(i+1);
		}
		
		for(int i = 0; i < 29; i++)
		{
			days29[i] = String.valueOf(i+1);
		}
	}
	
	

}
