package assignment01;

import java.util.List;

public interface Checker {
	boolean satisfies(List<String> taken);
	Checker NullAI = (_) -> true; // if this does not compile, your are not
	Checker NullCyber = (_) -> true; // using Java 22 or later
	Checker NullMSCS = (_) -> false; // use (x) -> true; instead 
}
