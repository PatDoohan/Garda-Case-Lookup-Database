package TeamProjectApplication;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Suspect extends Person {
	
	private int CRIMINALID = 1;
	private String suspectPPS, suspectName, suspectAddress, description, priorConvictions;
	private String[] status = {"Suspect", "Fugitive", "Arrested", "Processed"};
	private String currentStatus;
	private JComboBox statusBox = new JComboBox(status);
	
	
	public Suspect()
	{
		super("Unkown", "Unknown", "Unkown");
		this.description = "Unkown";
		this.priorConvictions = "Unkown";
		CRIMINALID++;
		currentStatus = (String) JOptionPane.showInputDialog(null, "What is the current status of the suspect?", "Suspect Status", JOptionPane.QUESTION_MESSAGE, null, status, status[0]);
	
	}
	
	public Suspect(String suspectPPS, String suspectName, String suspectAddress, String description, String priorConvictions)
	{
		super(suspectPPS, suspectName, suspectAddress);
		CRIMINALID++;	
		this.description = description;
		this.priorConvictions = priorConvictions;
		currentStatus = (String)JOptionPane.showInputDialog(null, "What is the current status of the suspect?", "Suspect Status", JOptionPane.QUESTION_MESSAGE, null, status, status[0]);
	}
	
	/**
	 * Setter for PPS number, in the case that the PPS number is unknown until later
	 * @param suspectPPS
	 */
	@Override
	public void setPPS(String suspectPPS)
	{
		this.ppsNumber = suspectPPS;
	}
	
	/**
	 * Setter for the name, in the case that the Name is unknown until later
	 * @param suspectName
	 */
	@Override
	public void setName(String suspectName)
	{
		this.name = suspectName;
	}
	
	/**
	 * Setter for the address, in the case that the Address is unknown until later
	 * @param suspectAddress
	 */
	@Override
	public void setAddress(String suspectAddress)
	{
		this.address = suspectAddress;
	}
	
	/**
	 * Setter for the Description, in the case that extra details need to be added
	 * @param suspectDescription
	 */
	public void setSuspectDescription(String suspectDescription)
	{
		this.description = suspectDescription;
	}
	
	/**
	 * Setter for the prior convictions, in the case that they are unknown until later
	 * @param priorConvictions
	 */
	public void setSuspectPriors(String priorConvictions)
	{
		this.priorConvictions = priorConvictions;
	}
	
	/**
	 * Setter for the status in case the status of the suspect has changed.
	 */
	public void setSuspectStatus()
	{
		this.currentStatus = (String)JOptionPane.showInputDialog(null, "What is the current status of the suspect?", "Suspect Status", JOptionPane.QUESTION_MESSAGE, null, status, status[0]);
	}
	
	/**
	 * getter for the criminal ID
	 * @return
	 */
	public int getSuspectID()
	{
		return this.CRIMINALID;
	}
	
	/**
	 * Getter for the pps Number, mostly used to confirm changes
	 */
	@Override
	public String getPPS()
	{
		return this.ppsNumber;
	}
	
	/**
	 * Getter for the name, mostly used to confirm changes
	 */
	@Override
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Getter for the address, mostly used to confirm changes
	 */
	@Override
	public String getAddress()
	{
		return this.address;
	}
	
	/**
	 * Getter for the address, mostly used to confirm changes and displaying description on it's own screen
	 * @return
	 */
	public String getSuspectDescription()
	{
		return this.description;
	}
	
	/**
	 * Getter for the address, mostly used to confirm changes
	 * @return
	 */
	public String getSuspectPriors()
	{
		return this.priorConvictions;
	}
	/**
	 * getter for status, used along with description for a display page
	 * @return
	 */
	public String getSuspectStatus()
	{
		return this.currentStatus;
	}
}
