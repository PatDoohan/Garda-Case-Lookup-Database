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
	
	private JTextField reg, type, make, model, colour, desc, caseIn;
	private JLabel regLabel, typeLabel, makeLabel, modelLabel, colourLabel, descLabel, caseNumberLabel;
	protected JButton submit, cancel;
	private JPanel container, form, buttons;
	Vehicle vehicleIn = new Vehicle();
	
	
	public AddVehicleForm()
	{
		this.setLayout(new BorderLayout());
		form = new JPanel();
		form.setLayout(new GridLayout(0,2,5,5));
		form.setBorder(new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5))));
		
		caseNumberLabel = new JLabel("Vehicle in Case: ");
		caseIn = new JTextField();
		form.add(caseNumberLabel);
		form.add(caseIn);
		
		regLabel = new JLabel("Vehicle Registration: ");
		reg = new JTextField();
		form.add(regLabel);
		form.add(reg);
		
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
		
		add(container, BorderLayout.CENTER);
	}
		
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//reg, type, make, model, colour, desc, caseIn;
		String suspectInformation = "Vehicle Reg: " + reg.getText() + "\nType: " + type.getText() + "\nMake: " + make.getText()
		+ "\nmodel: " + model.getText() + "\ncolour: " + colour.getText() + "\ndesc: " + desc.getText()
		+ "\nCase: " + caseIn.getText();
		
		int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to add the follwoing Information?\n" + suspectInformation);
		if(confirmation == 0)
		{
			vehicleIn.setReg(reg.getText());
			vehicleIn.setType(type.getText());
			vehicleIn.setMake(make.getText());
			vehicleIn.setModel(model.getText());
			vehicleIn.setColour(colour.getText());
			vehicleIn.setDescription(desc.getText());
			vehicleIn.linkToCase(Integer.valueOf(caseIn.getText()));	
			vehicleIn.addToDatabase();
			clearfields();
		}
	}
	
	public void clearfields()
	{
		reg.setText(null);
		type.setText(null);
		make.setText(null);
		model.setText(null);
		colour.setText(null);
		desc.setText(null);
		caseIn.setText(null);
	
	}

}
