package org.serene.tester;

import java.util.*;

/**
 * 
 * @author Waleed A public class To Contain Data Structure: [(String),
 *         (TreeMap<String, Double>)] Purpose: To hold the frequency list of
 *         tokens, Probability Lists of Tokens
 */
public class DocumentFreqMap {
	private String ID;
	private TreeMap<String, Double> ProbMap;

	public DocumentFreqMap(String id, TreeMap<String, Double> pm) {
		this.ID = id;
		this.ProbMap = pm;
	}

	public DocumentFreqMap() {
		this.ID = "";
		this.ProbMap = new TreeMap<String, Double>();
	}

	// getter method for ID
	public String getID() {
		return this.ID;
	}

	// setter method for ID
	public void setID(String id) {
		this.ID = id;
	}

	// getter method for ProbMap
	public TreeMap<String, Double> getProbMap() {
		return this.ProbMap;
	}

	// setter method for Frequency Map
	public void setFreqMap(TreeMap<String, Double> pm) {
		this.ProbMap = pm;
	}
}
