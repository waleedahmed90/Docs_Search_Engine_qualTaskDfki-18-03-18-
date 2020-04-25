package org.serene.tester;

import java.util.*;

/**
 * 
 * @author Waleed Lucene Ranker Class. (Implements BaseRanker interface)
 *         Computes the Document Rankings depending upon the query. Algorithm
 *         Used: Maximum Likelihood Estimation (MLE)
 */
public class LuceneRanker implements BaseRanker {

	// Array to hold the document Keys and content
	private final ArgsFormator[] documents;

	// Array to hold the document Keys and unigram probabilities
	private final DocumentFreqMap[] docMapProbs;

	/**
	 * 
	 * @param Documents
	 *            (Keys,Content)
	 */
	public LuceneRanker(ArgsFormator[] arguments) {
		super();
		this.documents = arguments;
		this.docMapProbs = new DocumentFreqMap[documents.length];
		// calls the starter function to calculate unigram probabilities
		this.starter();
	}

	/**
	 * 
	 * @param Words
	 *            list with word frequencies.
	 * @return sum of frequencies (Total number of words in the document)
	 */
	public double sumOfValues(TreeMap<String, Double> list) {
		double sum = 0.0;
		for (Double d : list.values()) {
			sum += d;
		}
		return sum;
	}

	/**
	 * 
	 * @param freqList
	 * @return TreeMap<String, Double> the unigram smoothened probabilities and
	 *         compensatory probability value for unseen words
	 */
	public TreeMap<String, Double> calcSmoothProbabilties(
			TreeMap<String, Double> freqList) {
		// "###" denotes the unseen token with a count of 0

		freqList.put("###", 0.0);

		// iterate over the tree map and calculates the smoothened probabilities

		Set setOfKeys = freqList.keySet();
		Iterator iterator = setOfKeys.iterator();

		// V is no of distinct tokens
		Double V = (double) freqList.size();

		// calls the sumOfValues function to calculate total number of words
		Double N = (double) sumOfValues(freqList);

		TreeMap<String, Double> probList = new TreeMap<String, Double>();

		while (iterator.hasNext()) {

			String key = (String) iterator.next();
			Double value = (Double) freqList.get(key);

			// Additive smoothing for the missing values
			value = (value + 1) / (V + N);

			probList.put(key, value);
		}

		return probList;
	}

	/**
	 * 
	 * @param Document
	 *            Content
	 * @return Frequency count for word occurences
	 */
	// This method returns the frequency strings for the documents
	public TreeMap<String, Double> FrequencyStrings(String s) {

		// casting the entire document content to lowercase
		s = s.toLowerCase();

		// Tokenization with regular expression to remove the punctuations
		String[] tokens = s.split("[ \n\t\r,.;:!?(){}]");

		TreeMap<String, Double> freqList = new TreeMap<String, Double>();

		// counting word frequencies
		for (int i = 0; i < tokens.length; i++) {
			String key = tokens[i];

			if (freqList.get(key) == null) {
				// if the token does not exist already
				freqList.put(key, 1.0);
			} else {
				// if the token already exists. Count increments by 1
				double value = freqList.get(key).doubleValue();
				value++;
				freqList.put(key, value);
			}

		}
		// calls the function to calculate the probabilites based on the word
		// frequencies
		return calcSmoothProbabilties(freqList);

	}

	/**
	 * Purpose: Starter for the rest of LuceneRanker functions
	 */
	public void starter() {

		for (int i = 0; i < docMapProbs.length; i++) {

			// maintains the document IDs
			String ID = documents[i].getKey();
			TreeMap<String, Double> prob = FrequencyStrings(documents[i]
					.getContent());

			docMapProbs[i] = new DocumentFreqMap();
			docMapProbs[i].setID(ID);
			docMapProbs[i].setFreqMap(prob);
		}
	}

	@Override
	public Map<String, Double> calcRankings(String query) {

		// Pre-Processing for the search query
		// //casting the entire query content to lower case
		query = query.toLowerCase();

		// tokenizing the query words with regular expression to remove
		// punctuations
		String[] words = query.split("[ \n\t\r,.;:!?(){}]");

		Map<String, Double> results = new TreeMap<>();

		TreeMap<String, Double> prob = new TreeMap<String, Double>();

		for (int i = 0; i < docMapProbs.length; i++) {
			String id = docMapProbs[i].getID();

			prob = docMapProbs[i].getProbMap();

			// probability value for unseen tokens
			double unknown = prob.get("###").doubleValue();

			double MLE = 1.0;

			for (int j = 0; j < words.length; j++) {
				if (prob.get(words[j]) == null) {
					// if the word is not seen in the document
					MLE *= unknown;
				} else {

					// if the word is seen in the document
					double value = prob.get(words[j]).doubleValue();
					MLE *= value;
				}
			}
			results.put(id, MLE);

		}
		return results;

	}

}