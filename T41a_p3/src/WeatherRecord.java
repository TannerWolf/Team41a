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
import java.util.Comparator;

/**
 * The WeatherRecord class is the child class of Record to be used when merging weather data. Station and Date
 * store the station and date associated with each weather reading that this object stores.
 * l stores the weather readings, in the same order as the files from which they came are indexed.
 */
public class WeatherRecord extends Record{
    private int numFiles;
    private String stationID;
    private String date;
    private Double[] conditions;

	/**
	 * Constructs a new WeatherRecord by passing the parameter to the parent constructor
	 * and then calling the clear method()
	 */
    public WeatherRecord(int numFiles) {
		super(numFiles);
		// call separately because super not working correctly
		this.numFiles = numFiles;
		clear();
    }
	
	/**
	 * This comparator should first compare the stations associated with the given FileLines. If 
	 * they are the same, then the dates should be compared. 
	 */
    private class WeatherLineComparator implements Comparator<FileLine> {
		public int compare(FileLine l1, FileLine l2) {
			//compare the stations associated with the given FileLines
			String k1 = l1.getString().split(",")[0];
			String k2 = l2.getString().split(",")[0];
			//if stations are the same, split the rest by comma, compare the rest of data
			if(k1.compareTo(k2) == 0) {
				k1 = l1.getString().split(",")[1];
				k2 = l2.getString().split(",")[1];
			}
			return k1.compareTo(k2);
		}
	
		public boolean equals(Object o) {
			return this.equals(o);
		}
    }
    
	/**
	 * This method should simply create and return a new instance of the WeatherLineComparator
	 * class.
	 */
    public Comparator<FileLine> getComparator() {
		return new WeatherLineComparator();
    }
	
	/**
	 * This method should fill each entry in the data structure containing
	 * the readings with Double.MIN_VALUE
	 */
    public void clear() {
    	//set the variable to null to clear
		stationID = null;
		date = null;
		conditions = new Double[numFiles];
		// fill each entry in the data structure containing
		 // the readings with Double.MIN_VALUE
		for(int i=0; i<conditions.length; i++) {
			conditions[i] = Double.MIN_VALUE;
		}
    }

	/**
	 * This method should parse the String associated with the given FileLine to get the station, date, and reading
	 * contained therein. Then, in the data structure holding each reading, the entry with index equal to the parameter 
	 * FileLine's index should be set to the value of the reading. Also, so that
	 * this method will handle merging when this WeatherRecord is empty, the station and date associated with this
	 * WeatherRecord should be set to the station and date values which were similarly parsed.
	 */
    public void join(FileLine li) {
    	//check whether the input fileline is null
    	if (li == null) {
    		System.out.println("Error: null line given to join");
    	}
    	//Extract ID and date from input fileline
    	stationID = li.getString().split(",")[0];
    	date = li.getString().split(",")[1];
    	//merge the specific part
    	String temp = li.getString().split(",")[2];
        int conditionNum = li.getFileIterator().getIndex();//the entry with index equal to the parameter 
   	 // FileLine's index should be set to the value of the reading
        conditions[conditionNum] = Double.parseDouble(temp);
    }
	
	/**
	 * See the assignment description and example runs for the exact output format.
	 */
    public String toString() {
    	String out = stationID + "," + date + ",";
    	for(int i=0; i<conditions.length; i++) {
    		Double d = conditions[i];
    		//if the number in the record is equal to the minimum
    		//set to "-"
    		if (d == Double.MIN_VALUE) {
    			out += "-";
    		}
    		else {
    			out += d;
    		}
    		//Comma-separated list of measurements
    		if (i < conditions.length-1) {
    			out += ",";
    		}
    	}
		return out;
    }
}
