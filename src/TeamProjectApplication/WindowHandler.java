package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class WindowHandler extends JFrame implements ActionListener{
	
	//Varibales, Panels and Classes created to be used in the Windowhandler
	private LoginScreen login;
	private GardaMenu mainMenu;
	private CaseMenu caseMenu;
	private CaseDisplay caseDisplay;
	private JPanel Container;
	private int caseToView;
	private CardLayout cLayout;
	private EvidenceMenu evidenceMenu;
	private SuspectMenu suspectMenu;
	private WitnessMenu witnessMenu;
	private VehicleMenu	vehicleMenu;
	private ForensicsMenu forensicsMenu;
	private MapMenu mapMenu;
	private CreateCaseForm caseForm;
	private EditCaseForm editCase;
	private JPanel center;
	private CaseDeleteForm deleteCase;
	
	
	
	public WindowHandler()
	{
		//this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("gardaLogo.png")));
		//creates container to hold different windows.
		Container = new JPanel();
		//creates a card layout that can handle panel swapping
		cLayout = new CardLayout(0,0);
		//setting the layout in the container
		Container.setLayout(cLayout);
		
		//creates the login screen from the class login screen that extends from jpanel
		login = new LoginScreen();
		//sets it to visible as it is the first screen required to be displayed
		login.setVisible(true);
		//creates a listener for the login button
		login.login.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//creates variables for the data entered by the user
				String enteredUsername = login.userName.getText();
				String enteredPassword = login.password.getText();
				//creates variables for hasing the password and the data retrieved from the database
				String hashedPassword = null, retrievedLogin = null, retrievedPassword=null;
				//tries to has the password entered by the user, catches noSuchAlgorithmException.
				try 
				{
					/*
					 * creates a message digest using sha 512 which encryptes the password
					 * entered by the user as the password in the database has already been
					 * encrypted, so the entered password must be encrypted to compare them.
					 */
					MessageDigest m;
					m = MessageDigest.getInstance("SHA-512");
					m.update(enteredPassword.getBytes(),0,enteredPassword.length());
					hashedPassword = new BigInteger(1, m.digest()).toString(16);
				}
				catch (NoSuchAlgorithmException e1) 
				{
					e1.printStackTrace();
				}

				//instance variales for use with the database.
				Statement st;
				ResultSet rs;
				
				try
				{
					//creates connection to database
					Class.forName("com.mysql.jdbc.Driver");
					//connects to mysql with username and password.
					java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
					//creates a statement
					st = con.createStatement();
					//creates a query and returns a result set from it based on the entered username.
					rs = st.executeQuery("SELECT * FROM garda.authentication WHERE LoginID = '" + enteredUsername + "';");
				
					//uses the result set to set the values to the retrieved variables
					while(rs.next())
					{
						retrievedLogin = rs.getString(1);
						retrievedPassword = rs.getString(2);
					}	
					//closes result set and connection.
					rs.close();
					con.close();
				}
				
				catch(Exception f)
				{
					System.out.println("Error: " + f.getMessage());
				}
			
				/*
				 * if the correct username and password is entered sets login to invisible and the main menu 
				 * to visible.
				*/
				if(retrievedLogin.equals(enteredUsername) && retrievedPassword.equals(hashedPassword))
				{
					mainMenu.setVisible(true);
					login.setVisible(false);
				}
				
				//if the wrong values are entered an error will be popped to screen
				else
				{
					/******************************************************************
					 * PLACE HOLDER VALUE, NEEDS TO BE CHANGED TO POP UP NOTIFICATION *
					 ******************************************************************/
					System.out.println("passwords do not match");
				}
			}
		});
		//adds login to CardLayout container.
		Container.add(login);
		

		//creates instance of main menu using GardaMenu panel
		mainMenu = new GardaMenu();
		//sets visible to false as this should not be displayed until user passes authentication
		mainMenu.setVisible(false);
		//adds action listener for caseLookup button from GardaMenu class
		mainMenu.caseLookup.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//makes the caseMenu class visible and the main menu invisible.
				caseMenu.setVisible(true);
				mainMenu.setVisible(false);
			}
			
		});
		//adds action listener for evidenceMenu button from GardaMenu class
		mainMenu.EvidenceMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//makes the evidenceMenu visible and the main menu invisible.
				evidenceMenu.setVisible(true);
				mainMenu.setVisible(false);
			}
			
		});
		
		
		//sets listener for logout to the outer class listener that handles returning to previous menus
		mainMenu.Logout.addActionListener(this);
		//adds the main menu to the container card layout.
		Container.add(mainMenu);
		
		//creates instance of CaseMenu panel
		caseMenu = new CaseMenu();
		//sets visible to false as this should not be visible until navigated to via the main menu
		caseMenu.setVisible(false);
		//sets listener for return to main menu to the outer class listener that handles returning to previous menus
		caseMenu.back.addActionListener(this);
		//adds the listner for the viewCase button inside the CaseMenu class.
		caseMenu.viewCase.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				//calls the get Int method which gets the value for which case to display from the user.
				caseDisplay.getInt(Integer.valueOf(JOptionPane.showInputDialog("Please enter the id of the case you want to view")));
				//sets the caseMenu to Invisible and the caseDisplay to visible.
				caseMenu.setVisible(false);
				caseDisplay.setVisible(true);
			}
			
		});
		
		caseMenu.createCase.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				//sets the caseMenu to Invisible and the caseDisplay to visible.
				caseMenu.setVisible(false);
				caseForm.setVisible(true);
			}
			
		});
		
		caseMenu.updateCase.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				//sets the caseMenu to Invisible and the caseDisplay to visible.
				caseMenu.setVisible(false);
				editCase.setVisible(true);
			}
			
		});
		
		caseMenu.mapLookup.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//makes the evidenceMenu visible and the main menu invisible.
				mapMenu.setVisible(true);
				caseMenu.setVisible(false);
			}
			
		});
		
		caseMenu.deleteCase.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				//sets the caseMenu to Invisible and the caseDisplay to visible.
				caseMenu.setVisible(false);
				deleteCase.setVisible(true);
			}
			
		});;
		//adds the caseMenu to the Container card layout.
		Container.add(caseMenu);
		//creates a new caseDisplay panel
		caseDisplay = new CaseDisplay();
		//sets visible to false as this should not be visible until navigated to via the caseMenu
		caseDisplay.setVisible(false);
		//sets listener for return to case Menu to the outer class listener that handles returning to previous menus
		caseDisplay.back.addActionListener(this);
		//adds the case display to the Container card Layout.
		Container.add(caseDisplay, BorderLayout.CENTER);
		
		//creates an instance of the evidence menu panel
		evidenceMenu = new EvidenceMenu();
		//sets visible to false as this should not be visible until navigated to via the MainMenu
		evidenceMenu.setVisible(false);
		//sets listener for return to case Menu to the outer class listener that handles returning to previous menus
		evidenceMenu.back.addActionListener(this);
		//adds the listner for the suspect button inside the CaseMenu class.
		evidenceMenu.suspect.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//hides the evidence menu and makes the suspect menu visible.
				evidenceMenu.setVisible(false);
				suspectMenu.setVisible(true);
			}
			
		});
		//adds the listner for the witness button inside the CaseMenu class.
		evidenceMenu.witness.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//hides the evidence menu and makes the Witness menu visible.
				evidenceMenu.setVisible(false);
				witnessMenu.setVisible(true);
			}
			
		});
		//adds the listner for the Vehicle button inside the CaseMenu class.
		evidenceMenu.vehicle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//hides the evidence menu and makes the Vehicle menu visible.
				evidenceMenu.setVisible(false);
				vehicleMenu.setVisible(true);
			}
			
		});
		//adds the listner for the Vehicle button inside the CaseMenu class.
		evidenceMenu.forensics.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//hides the evidence menu and makes the Forensics menu visible.
				evidenceMenu.setVisible(false);
				forensicsMenu.setVisible(true);
			}
					
		});		
		//adds the evidence menu to the container card layout.
		Container.add(evidenceMenu);
		
		suspectMenu = new SuspectMenu();
		suspectMenu.back.addActionListener(this);
		Container.add(suspectMenu);
		
		witnessMenu = new WitnessMenu();
		witnessMenu.back.addActionListener(this);
		witnessMenu.setVisible(false);
		Container.add(witnessMenu);
		
		vehicleMenu = new VehicleMenu();
		vehicleMenu.back.addActionListener(this);
		vehicleMenu.setVisible(false);
		Container.add(vehicleMenu);
		
		forensicsMenu = new ForensicsMenu();
		forensicsMenu.back.addActionListener(this);
		forensicsMenu.setVisible(false);
		Container.add(forensicsMenu);
		
		mapMenu = new MapMenu();
		mapMenu.back.addActionListener(this);
		mapMenu.setVisible(false);
		Container.add(mapMenu);
		
		caseForm = new CreateCaseForm();
		caseForm.cancel.addActionListener(this);
		caseForm.setVisible(false);
		Container.add(caseForm);
		
		editCase = new EditCaseForm();
		editCase.cancel.addActionListener(this);
		editCase.setVisible(false);
		Container.add(editCase);
		
		deleteCase = new CaseDeleteForm();
		deleteCase.cancel.addActionListener(this);
		deleteCase.setVisible(false);
		Container.add(deleteCase);
		
		add(Container, BorderLayout.CENTER);
	}
	
	public static void main (String [] args)
	{
		WindowHandler frame = new WindowHandler();
	    frame.getRootPane().setDefaultButton(LoginScreen.login);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setTitle("Garda Case Tracking Database");
	    frame.setSize(1200,1000);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(caseMenu.isVisible() == true)
		{
			caseMenu.setVisible(false);
			mainMenu.setVisible(true);
			
		}
		
		else if(caseDisplay.isVisible() == true)
		{
			caseDisplay.setVisible(false);
			caseMenu.setVisible(true);
		}
		
		else if(mainMenu.isVisible() == true)
		{
			mainMenu.setVisible(false);
			login.setVisible(true);
			login.userName.setText(null);
			login.password.setText(null);
		}
		
		else if(evidenceMenu.isVisible())
		{
			evidenceMenu.setVisible(false);
			mainMenu.setVisible(true);
		}
		
		else if(suspectMenu.isVisible())
		{
			suspectMenu.setVisible(false);
			evidenceMenu.setVisible(true);
		}
		
		else if(witnessMenu.isVisible())
		{
			witnessMenu.setVisible(false);
			evidenceMenu.setVisible(true);
		}
		
		else if(vehicleMenu.isVisible())
		{
			vehicleMenu.setVisible(false);
			evidenceMenu.setVisible(true);
		}
		else if(forensicsMenu.isVisible())
		{
			forensicsMenu.setVisible(false);
			evidenceMenu.setVisible(true);
		}
		else if(mapMenu.isVisible())
		{
			mapMenu.setVisible(false);
			caseMenu.setVisible(true);
		}
		else if(caseForm.isVisible())
		{
			int confirmation = JOptionPane.showConfirmDialog(null, "All Information will be cleared, are you sure you want to quit? \n");
			if(confirmation == 0)
			{
				caseForm.clearFields();
				caseForm.setVisible(false);
				caseMenu.setVisible(true);
			}
		}
		
		else if(editCase.isVisible())
		{
			int confirmation = JOptionPane.showConfirmDialog(null, "All Information will be cleared, are you sure you want to quit? \n");
			if(confirmation == 0)
			{
				editCase.clearFields();
				editCase.setVisible(false);
				caseMenu.setVisible(true);
			}
		}
		
		else if(deleteCase.isVisible())
		{
			deleteCase.clearFields();
			deleteCase.setVisible(false);
			caseMenu.setVisible(true);
		}
	}
}
