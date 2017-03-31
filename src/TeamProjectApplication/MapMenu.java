package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

public class MapMenu extends JPanel{
	
	
	JButton back;
	public MapMenu()
	{
		this.setLayout(new BorderLayout());
		Browser browser = new Browser();
	    BrowserView view = new BrowserView(browser);
	    browser.loadURL("http://maps.google.ie");
	    back = new JButton("Return to Case Menu");
		back.setFont(new Font("Sans-Serif", 0, 25));
		back.setPreferredSize(new Dimension(180, 30));
	    
	    add(view, BorderLayout.CENTER);
	    add(back, BorderLayout.SOUTH);
	}

}
