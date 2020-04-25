package org.serene.tester;

import java.util.*;

/**
 * The SearchEngine combines the results of all rankers to calculate a final
 * score for each document and sort the results by the score in descending
 * order, given a specific search query.
 * 
 * @author Johannes Lahann
 */
public class SearchEngine {
	BaseRanker[] baseRankers = new BaseRanker[2];
	double luceneRankerWeight = 0.7;
	double randomRankerWeight = 0.3;
	private String query;

	/**
	 * Initializes the SearchEngine
	 * 
	 * @param documents
	 */
	public SearchEngine(ArgsFormator[] documents) {

		// Sending Documents to Random Ranker
		initRandomRanker(documents);

		// Sending Documents to LuceneRanker
		initLuceneRanker(documents);
	}

	/**
	 * Initializes the RandomRanker
	 * 
	 * @param documents
	 */
	private void initRandomRanker(ArgsFormator[] documents) {
		baseRankers[0] = new RandomRanker(documents);
	}

	/**
	 * Initializes the LuceneRanker
	 * 
	 * @param documents
	 */
	// TODO implement the initialization of your Lucene based ranker.

	private void initLuceneRanker(ArgsFormator[] documents) {
		baseRankers[1] = new LuceneRanker(documents);
	}

	/**
	 * Triggers a search
	 * 
	 * @param searchquery
	 * @return
	 */
	public Map<String, Double> search(String searchquery) {
		this.query = searchquery;
		Map<String, Double>[] rankings = new Map[2];

		int i = 0;

		for (BaseRanker b : baseRankers) {
			// calls calculate rankings on both rankers with respect to the
			// query
			rankings[i++] = b.calcRankings(query);
		}
		System.out.println("---------RESPECTIVE RANK RESULTS----------");
		System.out.println("\n\nRANDOM RANKER SCORE:");
		System.out.println(rankings[0]);

		System.out.println("\n\nLUCENE RANKER SCORE:");
		System.out.println(rankings[1]);

		// returns the combined ranking score returned by the combinedScores
		// method
		return combinedScores(rankings);
	}

	/**
	 * Combines the scores of various rankers. Weights them with the defined
	 * weights: luceneRankerWeight, randomRankerWeight. Normalizes the scores
	 * between 0 and 1. Sort the documents by score in descending order.
	 * 
	 * @param rankings
	 * @return
	 */
	// TODO implement the method that combines all the scores.
	private SortedMap<String, Double> combinedScores(
			Map<String, Double>[] rankings) {
		SortedMap<String, Double> combScore = new TreeMap<String, Double>();

		// to store temporarily the ranked results from the Random ranker
		Map<String, Double> results_Random = new TreeMap<>();

		// to store temporarily the ranked results from the Lucene ranker
		Map<String, Double> results_Lucene = new TreeMap<>();

		results_Random = rankings[0];
		results_Lucene = rankings[1];

		// results_Random.
		Set setOfKeys = results_Random.keySet();
		Iterator iterator = setOfKeys.iterator();

		while (iterator.hasNext()) {

			String key = (String) iterator.next();
			Double value_Random = (Double) results_Random.get(key);
			Double value_Lucene = (Double) results_Lucene.get(key);

			// Applying the defined weights
			Double newWeight = (value_Random * this.randomRankerWeight)
					+ (value_Lucene * this.luceneRankerWeight);
			combScore.put(key, newWeight);
		}

		return combScore;
	}

}
