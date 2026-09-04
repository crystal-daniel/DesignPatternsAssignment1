package assignment01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MSCSCyberChecker implements Checker {
	Set<String> electives = new TreeSet<>();

	public MSCSCyberChecker() {
		List<String> lines = Collections.emptyList();
		try {
			lines = Files.readAllLines(
					Paths.get("./elecsCyber.txt"),
					StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String str : lines) {
			str = str.trim();
			if (str.length() > 0) electives.add(str);
		}
	}

	@Override
	public boolean satisfies(List<String> taken) {
		boolean result = true;
		if (!taken.contains("CS558")) {
			result = false;
			System.out.println("The Cybersecurity track list is missing CS 558.");
		}
		if (!taken.contains("CS559")) {
			result = false;
			System.out.println("The Cybersecurity track list is missing CS 559.");
		}
		Set<String> valid = new TreeSet<>();
		for (String str : taken) {
			if (electives.contains(str)) valid.add(str);
		}
		// CS536, CS537, and CS580E are overlapping/renumbered versions of the same
		// course, so only one of them may count toward the elective total.
		String[] equivalent = { "CS536", "CS537", "CS580E" };
		boolean keptOne = false;
		for (String code : equivalent) {
			if (valid.contains(code)) {
				if (keptOne) {
					valid.remove(code);
				} else {
					keptOne = true;
				}
			}
		}
		// Using a Set is very important because some students have to repeat the
		// same class a second time in order to improve their grade. Using a set
		// ensures it is only counted once.
		if (valid.size() < 2) {
			result = false;
			System.out.println("The Cybersecurity track list is missing enough Cybersecurity electives. "
					+ "Two Cybersecurity track electives are needed.");
		}
		return result;
	}
}
