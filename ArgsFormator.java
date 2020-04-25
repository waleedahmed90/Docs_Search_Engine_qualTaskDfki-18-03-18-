package org.serene.tester;

/**
 * 
 * @author Waleed
 * A public class To Contain Data
 * Structure: [(String), (String>)]
 * Purpose: To hold the Document Keys and Document Content After argument Splitting
 */

import java.util.*;

public class ArgsFormator {
	private String key;
	private String content;

	public ArgsFormator() {
		this.key = "";
		this.content = "";
	}

	ArgsFormator(String c) {
		this.key = "";
		this.content = c;
	}

	ArgsFormator(String k, String c) {
		this.key = k;
		this.content = c;
	}

	// setter method for key
	public void setKey(String k) {
		this.key = k;
	}

	// setter method for content
	public void setContent(String c) {
		this.content = c;
	}

	// getter method for key
	public String getKey() {
		return this.key;
	}

	// getter method for content
	public String getContent() {
		return this.content;
	}
}
