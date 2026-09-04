package assignment01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MSCSAIChecker implements Checker {
	Set<String> electives = new TreeSet<>();

	public MSCSAIChecker() {
		List<String> lines = Collections.emptyList();
		try {
			lines = Files.readAllLines(
					Paths.get("./elecsAI.txt"),
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
		if (!taken.contains("CS536")) {
			result = false;
			System.out.println("The AI track list is missing CS 536.");
		}
		if (!taken.contains("CS565")) {
			result = false;
			System.out.println("The AI track list is missing CS 565.");
		}
		Set<String> valid = new TreeSet<>();
		for (String str : taken) {
			if (electives.contains(str)) valid.add(str);
		}
		// Using a Set is very important because some students have to repeat the
		// same class a second time in order to improve their grade. Using a set
		// ensures it is only counted once.
		if (valid.size() < 2) {
			result = false;
			System.out.println("The AI track list is missing enough AI electives. "
					+ "Two AI track electives are needed.");
		}
		return result;
	}
}