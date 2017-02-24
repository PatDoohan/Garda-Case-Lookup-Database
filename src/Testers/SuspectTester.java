package Testers;


import TeamProjectApplication.Suspect;

public class SuspectTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Suspect c1 = new Suspect("Unkown", "Unkown", "Unkown", "6ft, Brown hair, broken nose, thin", "Unkown");
				
		
		System.out.println("Suspect ID: " + c1.getSuspectID());
		System.out.println("Suspect PPS: " + c1.getPPS());
		System.out.println("Suspect Name: " + c1.getName());
		System.out.println("Suspect address: " + c1.getAddress());
		System.out.println("Suspect Description: " + c1.getSuspectDescription());
		System.out.println("Prior Convictions: " + c1.getSuspectPriors());
		System.out.println("Status: " + c1.getSuspectStatus());
	
		Suspect c2 = new Suspect();
		
		c2.setPPS("1234567A");
		c2.setName("joe Bloggs");
		c2.setAddress("Ballyraine");
		c2.setSuspectDescription("Caucasian Male, Grey Hair, 60+, partial balding, walking stick");
		c2.setSuspectPriors("Domestic Abuse");
		c2.setSuspectStatus();
		
	
		System.out.println("\nSuspect ID: " + c2.getSuspectID());
		System.out.println("Suspect PPS: " + c2.getPPS());
		System.out.println("Suspect Name: " + c2.getName());
		System.out.println("Suspect address: " + c2.getAddress());
		System.out.println("Suspect Description: " + c2.getSuspectDescription());
		System.out.println("Prior Convictions: " + c2.getSuspectPriors());
		System.out.println("Status: " + c2.getSuspectStatus());

	}

}
