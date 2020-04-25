package org.serene.tester;

import java.util.*;

/**
 * Sample of a Ranker Implementation: The random Ranker. Assigns Random Ranks to
 * all the documents. Value between 0 and 1
 * 
 * @author Johannes Lahann
 */
public class RandomRanker implements BaseRanker {
	private final ArgsFormator[] documents;

	public RandomRanker(ArgsFormator[] documents) {
		super();
		this.documents = documents;
	}

	@Override
	public Map<String, Double> calcRankings(String query) {
		Map<String, Double> results = new TreeMap<>();
		Random r = new Random();
		for (int i = 0; i < documents.length; i++) {
			String id = documents[i].getKey();

			results.put(
					id,
					(double) ((double) (r.nextInt(100) + (double) (1.0)) / (double) (100.0)));
		}
		return results;

	}
}
