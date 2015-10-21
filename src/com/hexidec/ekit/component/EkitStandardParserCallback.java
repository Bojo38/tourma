package com.hexidec.component;

import javax.swing.text.html.HTMLEditorKit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
  * Custom ParserCallback class by Janko Jochimsen
  */

public class EkitStandardParserCallback extends HTMLEditorKit.ParserCallback
{
	HashMap<String, Integer> theErrors;
	boolean debug = false;
	
	public EkitStandardParserCallback()
	{
		theErrors = new HashMap<String, Integer>();
	}

	/**
	 * Process all Errors and take node
	 */
	public void handleError(String errorMsg, int pos)
	{
		Integer value = theErrors.get(errorMsg);
		if(value == null)
		{
			Integer one = new Integer(1);
			theErrors.put(errorMsg, one);
		}
		else
		{
			Integer incvalue = value + 1;
			theErrors.put(errorMsg, incvalue);
		}
		if(debug)
		{
			System.out.println("ParserCallback " + errorMsg + ", POS: " + pos);
		}
	}

	/**
	 * Get the Errors for futher investigation
	 */
	public HashMap<String, Integer> getTheErrors()
	{
		return theErrors;
	}

	/**
	 * Service Method to output the Errors. Might be used as a Template for
	 * futher methods
	 */
	public void reportCB()
	{
		Set<String> theErrorTypes = theErrors.keySet();
		if(theErrorTypes.size() < 1)
		{
			System.out.println("No Errors parsing file ");
		}
		else
		{
			Iterator<String> iter = theErrorTypes.iterator();
			while(iter.hasNext())
			{
				String error = iter.next();
				Integer count = theErrors.get(error);
				System.out.println("Error [" + error + "] happend " + count + " times");
			}
		}
	}
}
