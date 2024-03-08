import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner; 
public class Loader {  
		// I tried to sort countries but I couldn't solve the error.
	public static Stack loadSortCountries(String path) {
		try {
				Stack countries = new Stack(200);
		    	File countries_file = new File(path);
		        Scanner Reader = new Scanner(countries_file);
		        Stack S_temp = new Stack(200);
		        
		        while (Reader.hasNextLine()) {
		        	String data = Reader.nextLine();
		        
		        	if(!countries.isEmpty()) {
		        		for(int x=0;x<countries.size();x++) {
		        			if(countries.peek().toString().compareToIgnoreCase(data)==1) {//Deðiþtir
		        				S_temp.push(countries.pop());		        				
		        			}
		        			else {
		        				break;
		        			}     		
		        			countries.push(data);
		        			while(!S_temp.isEmpty()) {
	        					countries.push(S_temp.pop());
	        				}
		        		}
		        	}
		        	else {
		        		countries.push(data);
		        	}
		
			      }
			      Reader.close();
			      return countries;
			      
			      
			    } catch (FileNotFoundException e) {
			      System.out.println("File not found.");
			      e.printStackTrace();
			      return null;
			    }
	}
	public static Stack loadCountries(String path) {
		try {
				Stack countries = new Stack(100);
		    	File countries_file = new File(path);
		        Scanner Reader = new Scanner(countries_file);
		        
		        while (Reader.hasNextLine()) {
		        	String data = Reader.nextLine();
		        	countries.push(data);
			      }
			      Reader.close();
			      return countries;
			    } catch (FileNotFoundException e) {
			      System.out.println("File not found.");
			      e.printStackTrace();
			      return null;
			    }
	}

	public static Stack loadHighScoreTable(String path) {
		try {
			File HStable_file = new File(path);
			Scanner Reader = new Scanner(HStable_file);
			Stack HStable= new Stack(20);
			
			while (Reader.hasNextLine()) {
	        	  String data = Reader.nextLine();
	        	  HStable.push(data.split(" ")[0]);
	        	  HStable.push(data.split(" ")[1]);
				}
			Reader.close();
			return HStable;
			}
			catch (FileNotFoundException e) {
			      System.out.println("File not found.");
			      e.printStackTrace();
			      return null;
			    }
	}

}
 
