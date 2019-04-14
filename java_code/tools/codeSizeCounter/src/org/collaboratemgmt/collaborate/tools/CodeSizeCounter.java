package org.collaboratemgmt.collaborate.tools;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.List;

//license: MIT
//website: https://github.com/CollaboRateMgmt/CollaboRate

public class CodeSizeCounter {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("First argument: directory, second argument: file extension e.g. 'java'");
			return;
		}
		// System.out.println("hello world");
		countSizeInDir(args[0], ("." + args[1]).toLowerCase());
		System.out.println("Total code size: " + totalCount);
	}

	static int totalCount = 0;

	private static void countSizeInDir(String dirPath, String ext) {
		System.out.println("dirPath='" + dirPath + "', ext='" + ext + "'...");
		File dir = new File(dirPath);
		if (!dir.isDirectory()) {
			throw new RuntimeException("not a dir!!!");
		}
		File[] files = dir.listFiles();
		if (files == null) {
			throw new RuntimeException("unexpected");
		}
		for (File child : files) {
			// Do something with child
			if (child.getAbsolutePath().toLowerCase().endsWith(ext)) {
				countCodeSizeOfFile(child);
			} else if (child.isDirectory()) {
				countSizeInDir(child.getAbsolutePath(), ext);
			} else {
				System.out.println("'" + child + "' is neither a file nor a directory so ignoring....");
			}
		}
	}

	/*
	 * private static Set<Character> bracketTypeOfChars = new HashSet<>();
	 * 
	 * static { Set<Character> b = bracketTypeOfChars; b.addAll(Arrays.asList('{',
	 * '}', '[', ']', '(', ')', '<', '>')); }
	 */

	private static void countCodeSizeOfFile(File f) {
		List<String> lines = null;
		int fileCodeSize = 0;
		try {
			lines = Files.readAllLines(Paths.get(f.getAbsolutePath()), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String line : lines) {
			final CharacterIterator it = new StringCharacterIterator(line);
			boolean hasPreviousAlphaNumericCharacters = false;
			for (char c = it.first(); c != CharacterIterator.DONE; c = it.next()) {
				if (Character.isWhitespace(c)) {
					hasPreviousAlphaNumericCharacters = false;
				} // else if (bracketTypeOfChars.contains(c)) {
				else if (!(Character.isDigit(c) || Character.isLetter(c))) {
					hasPreviousAlphaNumericCharacters = false;
					// Any type of punctuation character (non-alphanumeric, non-whitespace character
					// increments the code size)
					fileCodeSize++;
				} else {
					if (!hasPreviousAlphaNumericCharacters) {
						fileCodeSize++;
						hasPreviousAlphaNumericCharacters = true;
					}
				}
			}
		}
		System.out.println("Code size of file '" + f.getAbsolutePath() + "' is " + fileCodeSize);
		totalCount += fileCodeSize;
	}
}
