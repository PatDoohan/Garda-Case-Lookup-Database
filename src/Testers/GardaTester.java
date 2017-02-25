package Testers;

import TeamProjectApplication.Garda;

public class GardaTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Garda g1 = new Garda("dn102", "Gweedore", "John Doe", "Carrick","087-0000001", "2345678A");
		
		//g1.addToDatabase();
		
		g1.deleteFromDatabase("dn102");
	}

}
