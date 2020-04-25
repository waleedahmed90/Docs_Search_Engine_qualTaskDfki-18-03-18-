package org.serene.tester;

import java.util.Map;

/**
 * Interface of a basic ranker.
 * 
 * @author Johannes Lahann
 * 
 */
public interface BaseRanker {
	/**
	 * Has to be implemented by a specific Ranker. It calculates the scores for
	 * each document given a specific search query.
	 * 
	 * @param query
	 *            : Search Query
	 * @return a Map of Documentkeys -> RankerScores
	 */
	Map<String, Double> calcRankings(String query);

}
