package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class AddVehicleForm extends JPanel implements ActionListener{
	
	//instance variables and components to build the GUI
	private JTextField reg, type, make, model, colour, desc, caseIn;
	private JLabel regLabel, typeLabel, makeLabel, modelLabel, colourLabel, descLabel, caseNumberLabel;
	protected JButton submit, cancel;
	private JPanel container, form, buttons;
	//instance of vehicle class for data manipulation
	Vehicle vehicleIn = new Vehicle();
	
	//main class for AddVehicleForm that builds the Panel
	public AddVehicleForm()
	{
		//sets the layout to border layout as the default layout for a jpanel is flow.
		this.setLayout(new BorderLayout());
		
		/* Creates the form panel that will hold all the labels and text fields for the form.
		 * It is given a grid layout so that each row will be a different section of the form e.g 
		 * what case it is a part of then on the next line the ID number of the vehicle file etc.*/
		form = new JPanel();
		form.setLayout(new GridLayout(0,2,5,5));
		form.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		/* this is the label and text field that is used to link the vehicle file to the case, this has to be
		 * done manually as new vehicle files could be added for older cases as well as newer and there is no
		 * way to predetermine what case the file is for. */
		caseNumberLabel = new JLabel("Vehicle in Case: ");
		caseIn = new JTextField();
		form.add(caseNumberLabel);
		form.add(caseIn);
		
		//this is the label and text field for the vehicle registration
		regLabel = new JLabel("Vehicle Registration: ");
		reg = new JTextField();
		form.add(regLabel);
		form.add(reg);
		
		//this is the label and text field for the vehicle type e.g. van, SuV, Saloon
		typeLabel = new JLabel("Vehicle Type: ");
		type = new JTextField();
		form.add(typeLabel);
		form.add(type);
		
		//this is the label and text field for the vehicle Make e.g. ford, toyota, hyundai
		makeLabel = new JLabel("Vehicle Make: ");
		make = new JTextField();
		form.add(makeLabel);
		form.add(make);
		
		//this is the label and text field for the vehicle model e.g. avensis, corolla, tucson
		modelLabel = new JLabel("Vehicle Model: ");
		model = new JTextField();
		form.add(modelLabel);
		form.add(model);
		
		//this is the label and text field for the vehicle colour
		colourLabel = new JLabel("Vehicle Colour: ");
		colour = new JTextField();
		form.add(colourLabel);
		form.add(colour);
		
		//this is the label and text field for the vehicle description e.g. broken tail light, modified body kit
		descLabel = new JLabel("Vehicle Description: ");
		desc = new JTextField();
		form.add(descLabel);
		form.add(desc);
		
		/* these are the buttons to cancel and sumbit and the panel that holds them
		 * Submit uses the inner class listener to perform a function
		 * and the cancel button is used by the window handler class to
		 * return to the previous menu*/		
		buttons = new JPanel();
		submit = new JButton("Submit");
		submit.addActionListener(this);
		cancel = new JButton("Cancel");
		buttons.add(submit);
		buttons.add(cancel);
		
		//this is the container that holds both the button and form panel.
		container = new JPanel();
		container.setLayout(new GridLayout(0,1));
		container.setBorder(new EmptyBorder(200, 0, 0, 0));
		container.add(form);
		container.add(buttons);
		
		//adds the container to the main panel.
		add(container, BorderLayout.CENTER);
	}
		
	//this is the action event that happens upon pressing the submit button
	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		/*creates a string of all the information entered to be used in the confirmation panel
		 * This is done to allow the user and overview of the information entered so they can review and
		 * see if the whole thing is correct*/
		String suspectInformation = "Vehicle Reg: " + reg.getText() + "\nType: " + type.getText() + "\nMake: " + make.getText()
		+ "\nmodel: " + model.getText() + "\ncolour: " + colour.getText() + "\ndesc: " + desc.getText()
		+ "\nCase: " + caseIn.getText();
		
		/* this is the confirmation dialog box that prints the above string in a popup box that gives the user a chance to confirm or cancel
		 * the adding of the new vehicle */
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to add the follwoing Information?\n" + suspectInformation);
		
		//if the user confirms they want to add the information the following code is executed
		if(confirmation == 0)
		{
			//All the information is retrieved from the text fields and inserted into the vehicle class using the set methods
			vehicleIn.setReg(reg.getText());
			vehicleIn.setType(type.getText());
			vehicleIn.setMake(make.getText());
			vehicleIn.setModel(model.getText());
			vehicleIn.setColour(colour.getText());
			vehicleIn.setDescription(desc.getText());
			
			/*a link is made to the case specified by the user, this is used to pull the information
			 *into the case overview*/
			 int returnCase = vehicleIn.linkToCase(Integer.valueOf(caseIn.getText()));	
			
			 if(returnCase == 0)
			 {
				 JOptionPane.showMessageDialog(null,"Case Not Found, Please Enter a case ID and try again", "Case Not Found",  JOptionPane.ERROR_MESSAGE);
			 }
			 
			 else
			 {
				//calls the method to add the data to the database.
					vehicleIn.addToDatabase();
					
					//calls the clearfields method to reset the fields to default for the next entry
					clearfields();
					JOptionPane.showMessageDialog(null, "Vehicle Entered Sucessfully", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
			 }
			
		}
	}
	
	//method for clearing all information from fields after succesfull add or cancellation of add
	public void clearfields()
	{
		//sets all the fields to blank
		reg.setText(null);
		type.setText(null);
		make.setText(null);
		model.setText(null);
		colour.setText(null);
		desc.setText(null);
		caseIn.setText(null);
	
	}

}
