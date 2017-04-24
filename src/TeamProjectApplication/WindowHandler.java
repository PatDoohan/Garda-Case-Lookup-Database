package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class WindowHandler extends JFrame implements ActionListener{
	
	//Variables, Panels and Classes created to be used in the Windowhandler
	private MainMenu mainMenu;
	private CaseMenu caseMenu;
	private CaseDisplay caseDisplay;
	private JPanel Container;
	private CardLayout cLayout;
	private EvidenceMenu evidenceMenu;
	private SuspectMenu suspectMenu;
	private WitnessMenu witnessMenu;
	private VehicleMenu	vehicleMenu;
	private ForensicsMenu forensicsMenu;
	private MapMenu mapMenu;
	private CreateCaseForm caseForm;
	private EditCaseForm editCase;
	private AddSuspectForm addSuspect;
	private AddVehicleForm addVehicle;
	private EditSuspect editSuspect;
	private EditVehicle editVehicle;
	private AddWitnessForm addWitness;
	private EditWitness editWitness;
	private AddForensicsForm addForensic;
	private EditForensics editForensic;
	private CrimeMenu crimeMenu;
	private GardaMenu gardaMenu;
	private AddCrimeForm addCrime;
	private EditCrime editCrime;
	private AddGardaForm addGarda;
	private EditGarda editGarda;
	private AssignGarda assignGarda;
	private CrimeStatisticsMenu crimestats;

	public WindowHandler(boolean validation)
	{
		//this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("gardaLogo.png")));
		//creates container to hold different windows.
		Container = new JPanel();
		//creates a card layout that can handle panel swapping
		cLayout = new CardLayout(0,0);
		//setting the layout in the container
		Container.setLayout(cLayout);
		

		//creates instance of main menu using GardaMenu panel
		mainMenu = new MainMenu(validation);
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
		//validation check to see if additional menus crime and garda should be dis
		if(validation == true)
		{
			mainMenu.crimeMenu.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//makes the evidenceMenu visible and the main menu invisible.
					crimeMenu.setVisible(true);
					mainMenu.setVisible(false);
				}
				
			});
			

			mainMenu.gardaMenu.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//makes the evidenceMenu visible and the main menu invisible.
					gardaMenu.setVisible(true);
					mainMenu.setVisible(false);
				}
				
			});
		}
	
		mainMenu.mapLookup.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				mapMenu.searchMap();
				//makes the evidenceMenu visible and the main menu invisible.
				mapMenu.setVisible(true);
				mainMenu.setVisible(false);
			}
			
		});
				
		mainMenu.CrimeStatistics.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				crimestats.setVisible(true);
				mainMenu.setVisible(false);
			}
			
		});
		//sets listener for logout to the outer class listener that handles returning to previous menus
		mainMenu.Logout.addActionListener(this);
		//adds the main menu to the container card layout.
		Container.add(mainMenu);
		
		//creates instance of CaseMenu panel
		caseMenu = new CaseMenu(validation);
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
		
		if(validation == true)
		{
			caseMenu.updateCase.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{	
					//sets the caseMenu to Invisible and the caseDisplay to visible.
					caseMenu.setVisible(false);
					editCase.setVisible(true);
				}
				
			});	
		}
		
		caseMenu.addCrime.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{	
					LinkCrimesToCase frame = new LinkCrimesToCase();
				    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    frame.setTitle("Add Crime To Case");
				    frame.setSize(900, 300);
				    frame.setLocationRelativeTo(null);
				    frame.setVisible(true);
				}
				
			});	

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
		
		suspectMenu = new SuspectMenu(validation);
		suspectMenu.back.addActionListener(this);
		Container.add(suspectMenu);
		
		suspectMenu.addSuspect.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//hides the evidence menu and makes the Forensics menu visible.
				suspectMenu.setVisible(false);
				addSuspect.setVisible(true);
			}
					
		});	
		
		if(validation == true)
		{
			suspectMenu.editSuspect.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//hides the evidence menu and makes the Forensics menu visible.
					suspectMenu.setVisible(false);
					editSuspect.setVisible(true);
				}
						
			});
		}
			
		suspectMenu.viewSuspect.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				viewSuspect frame = new viewSuspect(Integer.valueOf(JOptionPane.showInputDialog(null, "Please enter a case ID to view suspects", "input Required", JOptionPane.INFORMATION_MESSAGE)));
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    frame.setTitle("Suspect Viewer");
			    frame.setSize(900, 350);
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);
			}
					
		});	
		
		witnessMenu = new WitnessMenu(validation);
		witnessMenu.back.addActionListener(this);
		witnessMenu.setVisible(false);
		witnessMenu.addWitness.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//hides the evidence menu and makes the Forensics menu visible.
				witnessMenu.setVisible(false);
				addWitness.setVisible(true);
			}
					
		});	
		
		if(validation == true)
		{
			witnessMenu.editWitness.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//hides the evidence menu and makes the Forensics menu visible.
					witnessMenu.setVisible(false);
					editWitness.setVisible(true);
				}
						
			});	
		}
	
		witnessMenu.viewWitness.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ViewWitness frame = new ViewWitness(Integer.valueOf(JOptionPane.showInputDialog(null, "Please enter a case ID to view Witnesses", "input Required", JOptionPane.INFORMATION_MESSAGE)));
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    frame.setTitle("Suspect Viewer");
			    frame.setSize(900, 350);
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);
			}
					
		});

		Container.add(witnessMenu);
		
		vehicleMenu = new VehicleMenu(validation);
		vehicleMenu.back.addActionListener(this);
		vehicleMenu.setVisible(false);
		vehicleMenu.addVehicle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//hides the evidence menu and makes the Forensics menu visible.
				vehicleMenu.setVisible(false);
				addVehicle.setVisible(true);
			}
					
		});	
		
		if(validation == true)
		{
			vehicleMenu.editVehicle.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//hides the evidence menu and makes the Forensics menu visible.
					vehicleMenu.setVisible(false);
					editVehicle.setVisible(true);
				}
						
			});	
		}

		vehicleMenu.viewVehicle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ViewVehicle frame = new ViewVehicle(Integer.valueOf(JOptionPane.showInputDialog(null, "Please enter a case ID to view Vehicles", "input Required", JOptionPane.INFORMATION_MESSAGE)));
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    frame.setTitle("Vehicle Viewer");
			    frame.setSize(900, 400);
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);
			}
						
		});	
		Container.add(vehicleMenu);
		
		
		forensicsMenu = new ForensicsMenu(validation);
		forensicsMenu.back.addActionListener(this);
		forensicsMenu.setVisible(false);
		Container.add(forensicsMenu);
		
		forensicsMenu.addForensics.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//hides the evidence menu and makes the Forensics menu visible.
				forensicsMenu.setVisible(false);
				addForensic.setVisible(true);
			}
					
		});	
		
		if(validation == true)
		{
			forensicsMenu.editForensics.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					//hides the evidence menu and makes the Forensics menu visible.
					forensicsMenu.setVisible(false);
					editForensic.setVisible(true);
				}
						
			});	
		}
		
		forensicsMenu.viewForensics.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ViewForensics frame = new ViewForensics(Integer.valueOf(JOptionPane.showInputDialog(null, "Please enter a case ID to view a Forensics File", "input Required", JOptionPane.INFORMATION_MESSAGE)));
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    frame.setTitle("Forensics Viewer");
			    frame.setSize(900, 350);
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);
			}		
		});		
		
		crimeMenu = new CrimeMenu();
		crimeMenu.back.addActionListener(this);
		crimeMenu.setVisible(false);
		Container.add(crimeMenu);
		
		crimeMenu.addCrime.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//hides the crime menu and makes the add crime form visible.
				crimeMenu.setVisible(false);
				addCrime.setVisible(true);
			}
					
		});	
		
		crimeMenu.editCrime.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//hides the crime menu and makes the edit crime form visible.
				crimeMenu.setVisible(false);
				editCrime.setVisible(true);
			}
					
		});	
		
		gardaMenu = new GardaMenu();
		gardaMenu.back.addActionListener(this);
		gardaMenu.setVisible(false);
		Container.add(gardaMenu);
		
		gardaMenu.addGarda.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//hides the crime menu and makes the edit crime form visible.
				gardaMenu.setVisible(false);
				addGarda.setVisible(true);
			}
					
		});
		
		gardaMenu.editGarda.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//hides the crime menu and makes the edit crime form visible.
				gardaMenu.setVisible(false);
				editGarda.setVisible(true);
			}
					
		});
		
		gardaMenu.assignGarda.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//hides the crime menu and makes the edit crime form visible.
				gardaMenu.setVisible(false);
				assignGarda.setVisible(true);
			}
					
		});
		
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
		
		addSuspect = new AddSuspectForm();
		addSuspect.cancel.addActionListener(this);
		addSuspect.setVisible(false);
		Container.add(addSuspect);
		
		editSuspect = new EditSuspect();
		editSuspect.cancel.addActionListener(this);
		editSuspect.setVisible(false);
		Container.add(editSuspect);
		
		addVehicle = new AddVehicleForm();
		addVehicle.cancel.addActionListener(this);
		addVehicle.setVisible(false);
		Container.add(addVehicle);
		
		editVehicle = new EditVehicle();
		editVehicle.cancel.addActionListener(this);
		editVehicle.setVisible(false);
		Container.add(editVehicle);
		
		addWitness = new AddWitnessForm();
		addWitness.cancel.addActionListener(this);
		addWitness.setVisible(false);
		Container.add(addWitness);
		
		editWitness = new EditWitness();
		editWitness.cancel.addActionListener(this);
		editWitness.setVisible(false);
		Container.add(editWitness);
		
		addForensic = new AddForensicsForm();
		addForensic.cancel.addActionListener(this);
		addForensic.setVisible(false);
		Container.add(addForensic);
		
		editForensic = new EditForensics();
		editForensic.cancel.addActionListener(this);
		editForensic.setVisible(false);
		Container.add(editForensic);
		
		addCrime = new AddCrimeForm();
		addCrime.cancel.addActionListener(this);
		addCrime.setVisible(false);
		Container.add(addCrime);
		
		editCrime = new EditCrime();
		editCrime.cancel.addActionListener(this);
		editCrime.setVisible(false);
		Container.add(editCrime);
		
		addGarda = new AddGardaForm();
		addGarda.cancel.addActionListener(this);
		addGarda.setVisible(false);
		Container.add(addGarda);
		
		editGarda = new EditGarda();
		editGarda.cancel.addActionListener(this);
		editGarda.setVisible(false);
		Container.add(editGarda);
		
		assignGarda = new AssignGarda();
		assignGarda.cancel.addActionListener(this);
		assignGarda.setVisible(false);
		Container.add(assignGarda);
		
		crimestats = new CrimeStatisticsMenu();
		crimestats.back.addActionListener(this);
		crimestats.setVisible(false);
		Container.add(crimestats);
		
		add(Container, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{

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
			dispose();
			Login frame = new Login();
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setTitle("Garda Case Tracking Database");
		    frame.setSize(1200,1000);
		    frame.setLocationRelativeTo(null);
		    frame.setVisible(true);
			
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
			mainMenu.setVisible(true);
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
		
		else if(addSuspect.isVisible())
		{
			int confirmation = JOptionPane.showConfirmDialog(null, "All Information will be cleared, are you sure you want to quit? \n");
			if(confirmation == 0)
			{
				addSuspect.clearfields();
				addSuspect.setVisible(false);
				suspectMenu.setVisible(true);
			}
		}
		
		else if(editSuspect.isVisible())
		{
			int confirmation = JOptionPane.showConfirmDialog(null, "All Information will be cleared, are you sure you want to quit? \n");
			if(confirmation == 0)
			{
				editSuspect.clearfields();
				editSuspect.setVisible(false);
				suspectMenu.setVisible(true);
			}
		}
		
		else if(addVehicle.isVisible())
		{
			int confirmation = JOptionPane.showConfirmDialog(null, "All Information will be cleared, are you sure you want to quit? \n");
			if(confirmation == 0)
			{
				addVehicle.clearfields();
				addVehicle.setVisible(false);
				vehicleMenu.setVisible(true);
			}
		}
		
		else if(editVehicle.isVisible())
		{
			int confirmation = JOptionPane.showConfirmDialog(null, "All Information will be cleared, are you sure you want to quit? \n");
			if(confirmation == 0)
			{
				editVehicle.clearfields();
				editVehicle.setVisible(false);
				vehicleMenu.setVisible(true);
			}
		}
		
		else if(editWitness.isVisible())
		{
			int confirmation = JOptionPane.showConfirmDialog(null, "All Information will be cleared, are you sure you want to quit? \n");
			if(confirmation == 0)
			{
				editWitness.clearfields();
				editWitness.setVisible(false);
				witnessMenu.setVisible(true);
			}
		}
		
		else if(addWitness.isVisible())
		{
			int confirmation = JOptionPane.showConfirmDialog(null, "All Information will be cleared, are you sure you want to quit? \n");
			if(confirmation == 0)
			{
				addWitness.clearfields();
				addWitness.setVisible(false);
				witnessMenu.setVisible(true);
			}
		}
		
		else if(addForensic.isVisible())
		{
			int confirmation = JOptionPane.showConfirmDialog(null, "All Information will be cleared, are you sure you want to quit? \n");
			if(confirmation == 0)
			{
				addForensic.clearfields();
				addForensic.setVisible(false);
				forensicsMenu.setVisible(true);
			}
		}
		
		else if(editForensic.isVisible())
		{
			int confirmation = JOptionPane.showConfirmDialog(null, "All Information will be cleared, are you sure you want to quit? \n");
			if(confirmation == 0)
			{
				editForensic.clearfields();
				editForensic.setVisible(false);
				forensicsMenu.setVisible(true);
			}
		}
		
		else if(crimeMenu.isVisible())
		{
			crimeMenu.setVisible(false);
			mainMenu.setVisible(true);
		}
		
		else if(gardaMenu.isVisible())
		{
			gardaMenu.setVisible(false);
			mainMenu.setVisible(true);
		}
		
		else if(addCrime.isVisible())
		{
			int confirmation = JOptionPane.showConfirmDialog(null, "All Information will be cleared, are you sure you want to quit? \n");
			if(confirmation == 0)
			{
				addCrime.clearfields();
				addCrime.setVisible(false);
				crimeMenu.setVisible(true);
			}
		}
		
		else if(editCrime.isVisible())
		{
			int confirmation = JOptionPane.showConfirmDialog(null, "All Information will be cleared, are you sure you want to quit? \n");
			if(confirmation == 0)
			{
				editCrime.clearfields();
				editCrime.setVisible(false);
				crimeMenu.setVisible(true);
			}
		}
		
		else if(addGarda.isVisible())
		{
			int confirmation = JOptionPane.showConfirmDialog(null, "All Information will be cleared, are you sure you want to quit? \n");
			if(confirmation == 0)
			{
				editGarda.clearfields();
				addGarda.setVisible(false);
				gardaMenu.setVisible(true);
			}
		}
		
		else if(editGarda.isVisible())
		{
			int confirmation = JOptionPane.showConfirmDialog(null, "All Information will be cleared, are you sure you want to quit? \n");
			if(confirmation == 0)
			{
				editGarda.clearfields();
				editGarda.setVisible(false);
				gardaMenu.setVisible(true);
			}
		}
		
		else if(assignGarda.isVisible())
		{
			assignGarda.setVisible(false);
			gardaMenu.setVisible(true);
		}
		
		else if(crimestats.isVisible())
		{
			crimestats.setVisible(false);
			mainMenu.setVisible(true);
			
		}
	}
}
