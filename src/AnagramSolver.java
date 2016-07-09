import java.io.*;
import java.util.*;

public class AnagramSolver {
	private List<String> dictionary;
	private List<String> anagrams = new ArrayList<String>();
	//private HashMap<String, LetterInventory> relevant = new HashMap<String, LetterInventory>();
	private List<String> relevant = new ArrayList<String>();
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the string to be anagrammed:");
		String anagram = reader.nextLine();
		System.out.println("Enter the file name of the dictionary to use:");
		String filename = reader.nextLine();
		System.out.println("Enter the max number of words in the anagram (0 for no max):");
		int m = reader.nextInt();
		reader.close();
		
		String filePath = new File("").getAbsolutePath().concat("/src/" + filename);
		Scanner in = new Scanner(new FileInputStream(filePath));
		List<String> words = new ArrayList<String>();
		while (in.hasNext()){
			words.add(in.next());
		}
		in.close();
		AnagramSolver a = new AnagramSolver(words);
		a.print(anagram, m);
	}
	
	public AnagramSolver(List<String> list){
		dictionary = list;
	}
	
	public void print(String s, int max){
		LetterInventory string = new LetterInventory(s);
		relevant.clear();
		anagrams.clear();
		
		for (String word : dictionary){
			if (string.subtract(new LetterInventory(word)) != null){
				relevant.add(word);
			}
		}
		
		printIter(string, max, "");
		
		for (String a : anagrams){
			System.out.println(a);
		}
	}
	
	public void printIter(LetterInventory string, int max, String anagram){
		
		if (string.isEmpty()){
			anagrams.add(anagram);
		} else if (max == 0){
			for (String s : relevant){
				if (string.subtract(new LetterInventory(s)) != null){
					string = string.subtract(new LetterInventory(s));
					String temp = new String(anagram);
					anagram += s + " ";
					
					printIter(string, 0, anagram);
					
					string = string.add(new LetterInventory(s));
					anagram = temp;
				}
			}
		} else if (max == 1){
			for (String s : relevant){
				if (string.subtract(new LetterInventory(s)) != null && string.subtract(new LetterInventory(s)).isEmpty()){
					anagram += s + " ";
					anagrams.add(anagram);
				}
			}
		} else {
			for (String s : relevant){
				if (string.subtract(new LetterInventory(s)) != null){
					string = string.subtract(new LetterInventory(s));
					String temp = new String(anagram);
					anagram += s + " ";
					
					printIter(string, max - 1, anagram);
					
					string = string.add(new LetterInventory(s));
					anagram = temp;
				}
			}
		}
	}
}
