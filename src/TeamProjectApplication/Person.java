package TeamProjectApplication;

public class Person {

		protected String name, address, ppsNumber;
		
		public Person(String ppsNumber, String name, String address)
		{
			this.name = name;
			this.address = address;
			this.ppsNumber = ppsNumber;
		}

		/**
		 * getter method for pps number
		 * @return
		 */
		public String getPPS()
		{
			return this.ppsNumber;
		}
		
		/**
		 * getter method for name
		 * @return
		 */
		public String getName()
		{
			return this.name;
		}
		
		/**
		 * getter method for address
		 * @return
		 */
		public String getAddress()
		{
			return this.address;
		}
		
		/**
		 * Setter for PPS number, in the case that the PPS number is not known until later
		 * @param suspectPPS
		 */
		public void setPPS(String PPS)
		{
			this.ppsNumber = PPS;
		}
		
		/**
		 * Setter for the name, in the case that the PPS number is not known until later
		 * @param suspectName
		 */
		public void setName(String name)
		{
			this.name = name;
		}
		
		/**
		 * Setter for the address
		 * @param suspectAddress
		 */
		public void setAddress(String address)
		{
			this.address = address;
		}
}
