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
		clear();
    }
	
	/**
	 * This comparator should first compare the stations associated with the given FileLines. If 
	 * they are the same, then the dates should be compared. 
	 */
    private class WeatherLineComparator implements Comparator<FileLine> {
		public int compare(FileLine l1, FileLine l2) {
			String k1 = l1.getString().split(",")[0];
			String k2 = l2.getString().split(",")[0];
			if(k1.compareTo(k2) == 0) {
				k1 = l1.getString().split(",")[1];
				k2 = l2.getString().split(",")[1];
				return k1.compareTo(k2);
			}
			else {
				return k1.compareTo(k2);
			}
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
		stationID = null;
		date = null;
		conditions = new Double[numFiles];
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
    	System.out.println("in weather join");
    	if (li == null) {
    		System.out.println("Error: null line given to join");
    	}
    	stationID = li.getString().split(",")[0];
    	date = li.getString().split(",")[1];
    	String temp = li.getString().split(",")[2];
        int conditionNum = li.getFileIterator().getIndex();
        conditions[conditionNum] = Double.parseDouble(temp);
    }
	
	/**
	 * See the assignment description and example runs for the exact output format.
	 */
    public String toString() {
    	String out = stationID + "," + date + ",";
    	for(int i=0; i<conditions.length; i++) {
    		out += conditions[i] + ",";
    	}
		return out;
    }
}

