/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          p3
// FILE:             ThesaurusRecord
//
// TEAM:    Team 41a
// Authors: 
// Author1: (Jiayue Lai, jlai28@wisc.edu, jlai, Lec 002)
// Author2: (William Mustari, willmustari@gmail.com, mustari, Lec 002) 
// Author3: (Tanner Wolf, tmwolf2@wisc.edu, tmwolf2, Lec 002) 
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * The ThesaurusRecord class is the child class of Record to be used when merging thesaurus data.
 */

public class ThesaurusRecord extends Record{
	private String key;
	private ArrayList<String> list;

	/**
	 * Constructs a new ThesaurusRecord by passing the parameter to the parent constructor
	 * and then calling the clear method()
	 */
    public ThesaurusRecord(int numFiles) {
    	super(numFiles);
    	clear();
    }

    /**
	 * This Comparator should simply behave like the default (lexicographic) compareTo() method
	 * for Strings, applied to the portions of the FileLines' Strings up to the ":"
	 * The getComparator() method of the ThesaurusRecord class will simply return an
	 * instance of this class.
	 */
	private class ThesaurusLineComparator implements Comparator<FileLine> {
		public int compare(FileLine l1, FileLine l2) {
			String k1 = l1.getString().split(":")[0];
			String k2 = l2.getString().split(":")[0];
			return k1.compareTo(k2);
		}
		public boolean equals(Object o) {
			return this.equals(o);
		}
    }
    
	/**
	 * This method should simply create and return a new instance of the ThesaurusLineComparator class.
	 */
    public Comparator<FileLine> getComparator() {
		return new ThesaurusLineComparator();
    }
	
	/**
	 * This method should (1) set the word to null and (2) empty the list of synonyms.
	 */
    public void clear() {
    	// Set the key to null
    	key = null;
    	// Empty the list by creating a new one
    	list = new ArrayList<String>();
    }
	
	/**
	 * This method should parse the list of synonyms contained in the given FileLine and insert any
	 * which are not already found in this ThesaurusRecord's list of synonyms.
	 */
    public void join(FileLine w) {
    	if (w == null) {
    		System.out.println("Error: null string given to join");
    	}
    	key = w.getString().split(":")[0];
    	String[] syns = w.getString().split(":")[1].split(",");
    	// Go through each synonym string in input FileLine
    	for (int i = 0; i < syns.length; i++) {
    		// If the list doens't contain the synonym already, add it to the list of synonyms
    		if (!list.contains(syns[i])) {
    			list.add(syns[i]);
    		}
    	}
    }
	
	/**
	 * See the assignment description and example runs for the exact output format.
	 */
    public String toString() {
    	String out = key + ":";
    	Collections.sort(list);//writing the list in the sorted order
    	for (int i = 0; i < list.size(); i++) {
    		out += list.get(i);
    		//Comma-separated list of synonyms
    		if (i < list.size() - 1) {
    			out += ",";
    		}
    	}
		return out;
	}
    
}