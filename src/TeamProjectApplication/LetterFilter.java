package TeamProjectApplication;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class LetterFilter extends DocumentFilter {

	/**
	 * Overwritten Replace Method From DocumentFilter Class that replaces any 
	 * used when a field needs letters and numbers in a textfield, helps prevent SQL injections.
	 */
    @Override
    public void replace(FilterBypass filter, int offset, int length, String text, AttributeSet attributeSet) throws BadLocationException 
    {
    	if(text != null)
    	{
	    	//For loop large strings being entered at once e.g copy pasted, loop iterates through the entire string starting from the last position and de-incrementing
	        for (int i = text.length(); i > 0; i--) 
	        {
	        	//gets the character at the last spot of the string
	            char c = text.charAt(i-1);
	            
	            /*
	             * checks if the character is a number, if it is it calls the DocumentFilter replace method to replace it in the textfield.
	             * Where offset is the location in the original string, length is how much to delete and string.valueof(c) will be the replaces string
	             */
	            if (Character.isDigit(c) || c == ' ') 
	            {
	                super.replace(filter, offset, length, String.valueOf(c), attributeSet);
	            } 
	        }
    	}
    	
    	else
    	{
    		super.replace(filter, offset, length, String.valueOf(""), attributeSet);
    	}
    }

    @Override
    public void remove(FilterBypass filter, int offset, int length) throws BadLocationException {
        super.remove(filter, offset, length);
    }

    @Override
    public void insertString(FilterBypass filter, int offset, String string, AttributeSet attributeSet) throws BadLocationException {
        super.insertString(filter, offset, string, attributeSet);

    }
}
