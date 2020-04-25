package org.serene.tester;

import java.util.*;

/**
 * Some Helper Methods that you could or should implement.
 * 
 * @author Johannes Lahann
 */
public class Helper {

	/**
	 * 
	 * if there is no distinction between the Document Key and Content. (Key's
	 * perspective)
	 * 
	 * @param s
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static String getDokumentKey(String s)
			throws IllegalArgumentException {
		if (!s.contains(":")) {
			throw new IllegalArgumentException(
					"The DOCUMENT STRING does not contain the Unique document Key.\nPlease insert a colon : followed by the document key");
		} else {
			int index = s.indexOf(":");
			return s.substring(0, index);
		}

	}

	/**
	 * If there is no distinction between Document Content and Document Key.
	 * (Document's perspective)
	 * 
	 * @param s
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static String getDokumentContent(String s)
			throws IllegalArgumentException {
		if (!s.contains(":")) {
			throw new IllegalArgumentException(
					"The DOCUMENT STRING does not have a distinction between key and content");
		} else {
			int index = s.indexOf(":");
			return s.substring(index + 1);
		}
	}

	/**
	 * if there is no distinction between query content and key
	 * 
	 * @param s
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static String getSearchQueryContent(String s)
			throws IllegalArgumentException {
		if (!s.contains(":")) {
			throw new IllegalArgumentException(
					"The SEARCH QUERY does not have a distinction between key and content.\nPlease specify a query by an \'s\' followed by a colon");
		} else {
			int index = s.indexOf(":");
			return s.substring(index + 1);
		}

	}
}
