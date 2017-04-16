/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          p4
// FILE:             Interval
//
// TEAM:    Team 41a
// Authors: 
// Author1: (Jiayue Lai, jlai28@wisc.edu, jlai, Lec 002)
// Author2: (William Mustari, willmustari@gmail.com, mustari, Lec 002) 
// Author3: (Tanner Wolf, tmwolf2@wisc.edu, tmwolf2, Lec 002) 
///////////////////////////////////////////////////////////////////////////////


public class Interval<T extends Comparable<T>> implements IntervalADT<T> {

	private T start;	// start of the interval
	private T end;		// interval end
	private String label;	// label

    public Interval(T start, T end, String label) {
        // check if end is less than start
    	if (end.compareTo(start) < 0) {
    		throw new IllegalArgumentException();
    	}
    	
    	this.start = start;
    	this.end = end;
    	this.label = label;
    }

    @Override
    public T getStart() {
    	return start;
    }

    @Override
    public T getEnd() {
    	return end;
    }

    @Override
    public String getLabel() {
    	return label;
    }

    @Override
    public boolean overlaps(IntervalADT<T> other) {
        // check if null, otherwise compare the starts and ends to see if they overlap
    	if (other == null) {
    		throw new IllegalArgumentException();
    	}
    	if (end.compareTo(other.getStart()) < 0) {
    		return false;
    	}
    	if (start.compareTo(other.getEnd()) > 0) {
    		return false;
    	}
    	return true;
    }

    @Override
    public boolean contains(T point) {
        // check if the point is greater than or equal to the start 
    	// and less than or equal to the end
    	return (start.compareTo(point) <= 0 && point.compareTo(end) <= 0 );
    }

    @Override
    public int compareTo(IntervalADT<T> other) {
        // check if start times are the same
    	if (start.compareTo(other.getStart()) == 0) {
    		// if they are, return the comparison of the ends
    		return end.compareTo(other.getEnd());
    	}
    	// if starts not the same, return comparison of starts
    	return start.compareTo(other.getStart());
    }

    @Override
	public String toString() {
    	// return String form in form of: label [start, end]
    	return label + " [" + start.toString() + ", " + end.toString() + "]";
    }   
}