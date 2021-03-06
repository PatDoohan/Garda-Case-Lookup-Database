package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.AbstractDocument;

public class EditVehicle extends JPanel implements ActionListener{
	
	//instance variables and components to build the GUI
	private JFormattedTextField reg, type, make, model, colour, desc;
	private JLabel regLabel, typeLabel, makeLabel, modelLabel, colourLabel, descLabel;
	protected JButton submit, cancel, caseSubmit, caseClear;
	private JPanel container, form, buttons, caseSelection;
	//instance of vehicle class for data manipulation
	Vehicle vehicleIn = new Vehicle();
	
	//main class for EditVehicle that builds the Panel
	public EditVehicle()
	{
		//sets the layout to border layout as the default layout for a jpanel is flow.
		this.setLayout(new BorderLayout());
		
		/* Creates the form panel that will hold all the labels and text fields for the form.
		 * It is given a grid layout so that each row will be a different section of the form e.g 
		 * what case it is a part of then on the next line the ID number of the vehicle file etc.*/
		form = new JPanel();
		form.setLayout(new GridLayout(0,2,5,5));
		form.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		/* Creates the panel that will hold the panels and buttons for the suspect file selection
		 * the layout is set to allow all the items in the panel to exist on the same row */
		caseSelection = new JPanel();
		caseSelection.setLayout(new GridLayout(0,4,5,5));
		caseSelection.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		//creates the panel, text field and buttons for the file selection panel.
		regLabel = new JLabel("vehicle regestration number To Update: ");
		reg = new JFormattedTextField();
		((AbstractDocument)reg.getDocument()).setDocumentFilter(new SpecialCharacterFilter());
		caseSubmit = new JButton("Submit");
		caseClear = new JButton("Clear");
		
		//adds functionality to the submit button
		caseSubmit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//sets the suspectID field to uneditable to stop it being accidently changed while in use.
				reg.setEditable(false);
				
				
				
				//sets the textfields to the appropriate values retrieved from the database.
				int returnVehicle = vehicleIn.getVehicle(reg.getText());
				
				if(returnVehicle == 0)
				{
					JOptionPane.showMessageDialog(null,"Vehicle Not Found, Please Enter a valid vehicle regestration", "Vehicle Not Found",  JOptionPane.ERROR_MESSAGE);
					reg.setEditable(true);
				}
				
				else
				{
					type.setText(vehicleIn.getType());
					make.setText(vehicleIn.getMake());
					model.setText(vehicleIn.getModel());
					colour.setText(vehicleIn.getColour());
					desc.setText(vehicleIn.getDescription());
				}
				
			}	
		});
		
		//functionality for the clear button that clears all the fields and sets them to defaults
		caseClear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//calls the clearfields method to clear all the information
				clearfields();
			}
		});
		
		//adds the label, text field and buttons to the caseSelection panel
		caseSelection.add(regLabel);
		caseSelection.add(reg);
		caseSelection.add(caseSubmit);
		caseSelection.add(caseClear);
		
		//this is the label and text field for the vehicle type e.g. van, SuV, Saloon
		typeLabel = new JLabel("Vehicle Type: ");
		type = new JFormattedTextField();
		((AbstractDocument)type.getDocument()).setDocumentFilter(new NumberFilter());
		form.add(typeLabel);
		form.add(type);
		
		//this is the label and text field for the vehicle Make e.g. ford, toyota, hyundai
		makeLabel = new JLabel("Vehicle Make: ");
		make = new JFormattedTextField();
		((AbstractDocument)make.getDocument()).setDocumentFilter(new NumberFilter());
		form.add(makeLabel);
		form.add(make);
		
		//this is the label and text field for the vehicle model e.g. avensis, corolla, tucson
		modelLabel = new JLabel("Vehicle Model: ");
		model = new JFormattedTextField();
		((AbstractDocument)model.getDocument()).setDocumentFilter(new SpecialCharacterFilter());
		form.add(modelLabel);
		form.add(model);
		
		//this is the label and text field for the vehicle colour
		colourLabel = new JLabel("Vehicle Colour: ");
		colour = new JFormattedTextField();
		((AbstractDocument)colour.getDocument()).setDocumentFilter(new NumberFilter());
		form.add(colourLabel);
		form.add(colour);
		
		//this is the label and text field for the vehicle description e.g. broken tail light, modified body kit
		descLabel = new JLabel("Vehicle Description: ");
		desc = new JFormattedTextField();
		((AbstractDocument)desc.getDocument()).setDocumentFilter(new SpecialCharacterFilter());
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
		
		//adds the container and case selection to the panel
		add(caseSelection,BorderLayout.NORTH);
		add(container, BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		/*creates a string of all the information entered to be used in the confirmation panel
		 * This is done to allow the user and overview of the information entered so they can review and
		 * see if the whole thing is correct*/
		String suspectInformation = "Vehicle Reg: " + reg.getText() + "\nType: " + type.getText() + "\nMake: " + make.getText()
		+ "\nmodel: " + model.getText() + "\ncolour: " + colour.getText() + "\ndesc: " + desc.getText();
		
		/* this is the confirmation dialog box that prints the above string in a popup box that gives the user a chance to confirm or cancel
		 * the adding of the new case */
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
			
			//calls the method to add the updated data to the database.
			vehicleIn.updateVehicle();
			
			//calls the clearfields method to reset the fields to default for the next entry
			clearfields();
			JOptionPane.showMessageDialog(null, "Vehicle Sucessfully Updated", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	//method for clearing all information from fields after successful add or cancellation of add
	public void clearfields()
	{
		//sets all the fields to blank and sets the reg field to editable again
		reg.setEditable(true);
		reg.setText(null);
		type.setText(null);
		make.setText(null);
		model.setText(null);
		colour.setText(null);
		desc.setText(null);
	
	}
}
