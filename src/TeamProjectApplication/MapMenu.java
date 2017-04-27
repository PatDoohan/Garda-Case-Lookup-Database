package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

public class MapMenu extends JPanel{
		
	//button for navigating back to the menu
	protected JButton back;
	protected String googleMaps = "https://www.google.ie/maps/place/";
	private Browser browser;
	
	public MapMenu()
	{
		//sets the layout to borderlayout as the default layout is flow layout
		this.setLayout(new BorderLayout());
		
		//creates the browser in java that connects directly to google maps to allow the gardaí to search addresses and eircodes
		browser = new Browser();
	    BrowserView view = new BrowserView(browser);
	    browser.setSize(1000, 900);
	    //browser.loadURL("https://www.google.ie/maps/place/Carrick,+Co.+Donegal,+F92+Y654");
	    
	    //creates the button for returning back to the main menu
	    back = new JButton("Return to Main Menu");
		back.setFont(new Font("Sans-Serif", 0, 25));
		back.setPreferredSize(new Dimension(180, 30));
	    
		//adds the map browser and back button to the main panel
	    add(view, BorderLayout.CENTER);
	    add(back, BorderLayout.SOUTH);
	}

	//method for searching locations
	public void searchMap()
	{
		//pops up a dialog box to get the address/eircode from the user
		String enteredAddress = JOptionPane.showInputDialog("Please enter the address or eircode of the area you wish to view");
		
		//concatenates the location to the google maps string
		if(enteredAddress == null)
		{
			googleMaps = "https://www.google.ie/maps";
		}
		
		else
		{
			googleMaps = googleMaps + enteredAddress;
		}
		
		//loads an instance of google maps with the entered address
		browser.loadURL(googleMaps);
		
		//resets the default google maps string for the next address entered
		googleMaps = "https://www.google.ie/maps/place/";
	}
}
