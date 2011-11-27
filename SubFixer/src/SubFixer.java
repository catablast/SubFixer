/**
 * Created: 2011
 * By Fevzi Konduk 
 */

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SubFixer {
    @SuppressWarnings("unused")
	private static void printAllChars() {
    	for (int i = 29; i < 1000; i++){
			System.out.print ((char)i);
		}
		System.out.println("\n\rFINAL");
		System.exit(0);
    }
    
    private static void printConversionTable(HashMap<Character, Character> hashMapCharChar) {
		//Print Conversion table
		System.out.println("Printing Conversion Table\n\'From\' -> \'To\'");
		
		Set<Entry<Character, Character>> entries = hashMapCharChar.entrySet();
	    Iterator<Entry<Character, Character>> it = entries.iterator();
	    while (it.hasNext()) {
	      Map.Entry entry = (Map.Entry) it.next();
	      System.out.println( "[" + (int)((Character)entry.getKey()).charValue() + "]\'" + entry.getKey() + "\' -> \'"  + entry.getValue() + "\'" );
	    }
    }
    
    private static void populateMapWithConversionTable(HashMap<Character, Character> hashMapCharChar){
    	hashMapCharChar.put((char)199, 'C');
    	hashMapCharChar.put((char)231, 'c');
    	hashMapCharChar.put((char)240, 'g');
    	hashMapCharChar.put((char)253, 'i');
    	hashMapCharChar.put((char)221, 'I');
    	hashMapCharChar.put((char)222, 'S');
    	hashMapCharChar.put((char)254, 's');
    	hashMapCharChar.put((char)220, 'U');
    	hashMapCharChar.put((char)252, 'u');
    	hashMapCharChar.put((char)214, 'O');
    	hashMapCharChar.put((char)246, 'o');
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Boolean debug = false; 
		//debug = true;
		//printAllChars();
		HashMap <Character,Character> characterMap = new HashMap<Character, Character>();
		//Init HashMap with conversionTable
		populateMapWithConversionTable(characterMap);
		
		if (debug){
			//Print Conversion Table with characters and codes
			printConversionTable(characterMap);
			//System.exit(1);
		}
		
				
		if (debug){
			;
			System.out.println("PRINT: " + args[0]);
		}

		try{ 
			FileInputStream fstream;

			if(args.length == 0) {
			  System.out.println("The syntax of the command is incorrect.\n" + 
					  			 "Try `java SubFixer filename.ext' .");
			  //No Argument for filename, terminating application. 
			  System.exit(0);
			  fstream = new FileInputStream("subtile.srt");
			} else {
		      //Open the file that is the first command line parameter
			  fstream = new FileInputStream(args[0]);
			}
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String strLine;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
				if (debug){
					// Print the content on the console
					System.out.println ("BEFORE: " + strLine);
				}
				
				char[] charArr = strLine.toCharArray();
				
			    for (int i = 0; i < charArr.length; i++){
			    	if (debug){
			    		System.out.println (charArr[i] + "[" + i + "]: " + (int)charArr[i]);
			    	}
			    	//Check if char should be replaced
			    	if(characterMap.containsKey(charArr[i])){
			    		//Converting the actual char codes!
			    		charArr[i] = characterMap.get(charArr[i]);
			    	}
			    }
			    
			    if (debug){
			    	System.out.print ("AFTER: " );
			    }
			    //Printing Out Replaced string
			    System.out.println(new String(charArr));
			}
			//Close the input stream
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			//System.err.println("Error: " + e.getMessage());
		}
	}
}
