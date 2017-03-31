package Testers;

import TeamProjectApplication.Vehicle;

public class VehicleTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Vehicle v1 = new Vehicle();
		
		v1.setReg("Test");
		v1.setType("Type");
		v1.setMake("Make");
		v1.setModel("Model");
		v1.setColour("colour");
		v1.setDescription("desc");
		v1.linkToCase(2);
		v1.addToDatabase();
		//v1.addToDatabase();
		//v1.deleteFromDatabase("171-DL-1918");
	}

}
