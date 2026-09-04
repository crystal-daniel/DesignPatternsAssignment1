package assignment01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MSCSChecker implements Checker {
	Set<String> electives = new TreeSet<>();
	
	public MSCSChecker() {
		 List<String> lines = Collections.emptyList();	        
	      	try {
	            lines = Files.readAllLines(
	                Paths.get("./elecs.txt"),
	                StandardCharsets.UTF_8);
	        } catch(IOException e) {
	            e.printStackTrace();
	        }
	      for(String str : lines) {
	    	  str = str.trim();
	    	  if(str.length() > 0) electives.add(str);
	      }
	}
	
	@Override
	public boolean satisfies(List<String> taken) {
		boolean result = true;
		if(!taken.contains("CS551")) {
			result = false;
			System.out.println("The list is missing CS 551.");
		}
		if(!taken.contains("CS571")) {
			result = false;
			System.out.println("The list is missing CS 571.");
		}
		if(!taken.contains("CS575")) {
			result = false;
			System.out.println("The list is missing CS 575.");
		}
		if(!(taken.contains("CS520") || taken.contains("CS528") || taken.contains("CS550"))) {
			result = false;
			System.out.println("The list is missing all of CS 520, CS 528, or CS 550, at least one is needed.");
		}
		if(!(taken.contains("CS595") || taken.contains("CS599"))) {
			result = false;
			System.out.println("The list is missing a Termination Project or a Masters Thesis.");
		}
		Set<String> valid = new TreeSet<>();
		int count597 = 0;
		for(String str : taken) {
			if(electives.contains(str)) valid.add(str);
			if(str.equals("CS597") && count597 < 2) count597++;
		}
		// Using a Set is very important because some students have to repeat the 
		// same class a second time in order to improve their grade. Using a set
		// ensures it is only counted once.
		if((taken.contains("CS595") && valid.size() + count597 < 7) || 
				(taken.contains("CS599") && valid.size() + count597 < 6)) {
			result = false;
			System.out.println("You appear not to have enough electives. "
					+ "At most 2 independent studies can be used.\n"
					+ "If you have permission to take a class from outside the MSCS, "
					+ "a manual check will be needed.");			
		}
		return result;
	}

}
