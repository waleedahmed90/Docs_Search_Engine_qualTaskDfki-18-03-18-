package org.serene.tester;

import java.util.*;
import java.util.Map.Entry;

/**
 * Main Class. Runs the application
 * 
 * @author Johannes Lahann
 */
public class Main {

	/**
	 * 
	 * @param map
	 * @return Map Sorted in Descending order by Values
	 */

	static <K, V extends Comparable<? super V>> List<Entry<K, V>> entriesSortedByValues(
			Map<K, V> map) {

		List<Entry<K, V>> sortedEntries = new ArrayList<Entry<K, V>>(
				map.entrySet());

		Collections.sort(sortedEntries, new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Entry<K, V> e1, Entry<K, V> e2) {
				return e2.getValue().compareTo(e1.getValue());
			}
		});

		return sortedEntries;
	}

	/**
	 * 
	 * @param arg
	 * @return content of the query after the Query Key and a colon
	 * @throws IllegalArgumentException
	 */
	public static String QuerySplit(String arg) throws IllegalArgumentException {

		ArgsFormator temp = new ArgsFormator();

		// calls the Helper class's method to get the Query Content
		temp.setContent(Helper.getSearchQueryContent(arg));
		return temp.getContent();
	}

	/**
	 * 
	 * @param arg
	 * @return Returns the document(Key, Content)
	 * @throws IllegalArgumentException
	 */
	public static ArgsFormator ArgsSplit(String arg)
			throws IllegalArgumentException {
		ArgsFormator temp = new ArgsFormator();

		// Calls the Helper class to obtain the Document Key
		temp.setKey(Helper.getDokumentKey(arg));

		// Calls the Helper class to obtain the Document Content
		temp.setContent(Helper.getDokumentContent(arg));
		return temp;
	}

	// TODO implement this method to run the application with the aforementioned
	// arguments.

	// Arguments are accepted as Command Line Arguments. Every Argument has to
	// be enclosed in "" and separated by a White Space

	// Sample Arguments:

	// 1// ["s: what is wrong" "d1: what what is wrong wrong wrong"
	// "d2: What what not is wrong wrong" "d3: what what is not wrong"]
	// 1// ["s: what is not wrong" "d1: what what is wrong wrong wrong"
	// "d2: What what not is wrong wrong" "d3: what what is not wrong"]

	public static void main(String[] args) throws IllegalArgumentException {

		// If There is only one argument or no argument. The Code works with at
		// least two arguments. (One Query and One Document)
		if (args.length <= 1) {
			throw new IllegalArgumentException(
					"\nPlease Enter correct Number of Arguments.\nQuery Followed by Document File Names");
		}

		// Array to store the Documents along with their keys
		ArgsFormator[] Arguments = new ArgsFormator[args.length - 1];

		// getting query content
		String s = QuerySplit(args[0]);

		// Getting the documents content
		for (int i = 0; i < Arguments.length; i++) {
			Arguments[i] = ArgsSplit(args[i + 1]);
		}

		// Creating an instance of the search engine to trigger and get the
		// Rankings
		SearchEngine search1 = new SearchEngine(Arguments);

		// Structure to store the combined Rank score returned
		Map<String, Double> combScore = new TreeMap<String, Double>();

		// calls in search with query and saves the combined score
		combScore = search1.search(s);

		System.out.println("\n\nCOMBINED SCORE WITH DOCUMENTS RANKED IN DESCENDING ORDER:");
		System.out.println(entriesSortedByValues(combScore));

	}

}
