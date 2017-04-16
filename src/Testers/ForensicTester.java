package Testers;
import TeamProjectApplication.Forensics;

public class ForensicTester {
	
	public static void main (String [] args)
	{
		Forensics f1 = new Forensics();
		
//		f1.setBiologicalEvidence(true);
//		f1.setPrintEvidence(true);
//		f1.setTrackEvidence(true);
//		f1.setDigitalEvidence(true);
//		f1.setToolMarkEvidence(true);
//		f1.setNarcoticEvidence(true);
//		f1.setFirearmEvidence(true);
//		f1.assignID();
//		f1.linkToCase(3);
//		f1.addToDatabase();
		
		f1.deleteFromDatabase("3");
	}
	
}
