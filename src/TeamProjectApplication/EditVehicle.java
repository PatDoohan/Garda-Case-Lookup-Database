package TeamProjectApplication;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class EditVehicle extends JPanel implements ActionListener{
	
	private JTextField reg, type, make, model, colour, desc;
	private JLabel regLabel, typeLabel, makeLabel, modelLabel, colourLabel, descLabel;
	protected JButton submit, cancel, caseSubmit, caseClear;
	private JPanel container, form, buttons, caseSelection;
	Vehicle vehicleIn = new Vehicle();
	
	public EditVehicle()
	{
		this.setLayout(new BorderLayout());
		form = new JPanel();
		caseSelection = new JPanel();
		caseSelection.setLayout(new GridLayout(0,4,5,5));
		caseSelection.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		form.setLayout(new GridLayout(0,2,5,5));
		form.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		regLabel = new JLabel("vehicle regestration number To Update: ");
		reg = new JTextField();
		caseSubmit = new JButton("Submit");
		caseClear = new JButton("Clear");
		caseSubmit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				reg.setEditable(false);
				vehicleIn.getVehicle(reg.getText());
				type.setText(vehicleIn.getType());
				make.setText(vehicleIn.getMake());
				model.setText(vehicleIn.getModel());
				colour.setText(vehicleIn.getColour());
				desc.setText(vehicleIn.getDescription());
			}
					
		});
		caseClear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				reg.setEditable(true);
				clearfields();
				
			}
		});
		caseSelection.add(regLabel);
		caseSelection.add(reg);
		caseSelection.add(caseSubmit);
		caseSelection.add(caseClear);
		
		typeLabel = new JLabel("Vehicle Type: ");
		type = new JTextField();
		form.add(typeLabel);
		form.add(type);
		
		makeLabel = new JLabel("Vehicle Make: ");
		make = new JTextField();
		form.add(makeLabel);
		form.add(make);
		
		modelLabel = new JLabel("Vehicle Model: ");
		model = new JTextField();
		form.add(modelLabel);
		form.add(model);
		
		colourLabel = new JLabel("Vehicle Colour: ");
		colour = new JTextField();
		form.add(colourLabel);
		form.add(colour);
		
		descLabel = new JLabel("Vehicle Description: ");
		desc = new JTextField();
		form.add(descLabel);
		form.add(desc);
		
		buttons = new JPanel();
		submit = new JButton("Submit");
		submit.addActionListener(this);
		cancel = new JButton("Cancel");
		buttons.add(submit);
		buttons.add(cancel);
		
		container = new JPanel();
		container.setLayout(new GridLayout(0,1));
		container.setBorder(new EmptyBorder(200, 0, 0, 0));
		container.add(form);
		container.add(buttons);
		
		add(caseSelection,BorderLayout.NORTH);
		add(container, BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String suspectInformation = "Vehicle Reg: " + reg.getText() + "\nType: " + type.getText() + "\nMake: " + make.getText()
		+ "\nmodel: " + model.getText() + "\ncolour: " + colour.getText() + "\ndesc: " + desc.getText();
		
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to add the follwoing Information?\n" + suspectInformation);
		if(confirmation == 0)
		{
			vehicleIn.setReg(reg.getText());
			vehicleIn.setType(type.getText());
			vehicleIn.setMake(make.getText());
			vehicleIn.setModel(model.getText());
			vehicleIn.setColour(colour.getText());
			vehicleIn.setDescription(desc.getText());	
			vehicleIn.updateVehicle();
			clearfields();
		}
	}

	public void clearfields()
	{
		reg.setEditable(true);
		reg.setText(null);
		type.setText(null);
		make.setText(null);
		model.setText(null);
		colour.setText(null);
		desc.setText(null);
	
	}
}
