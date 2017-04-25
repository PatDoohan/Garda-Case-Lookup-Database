package Testers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class passwordGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String password = "password";
		
		MessageDigest m;
		try 
		{
			m = MessageDigest.getInstance("SHA-512");
			m.update(password.getBytes(),0,password.length());
			password = new BigInteger(1, m.digest()).toString(16);
		} 
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}
		
		System.out.print(password);
	}

}
