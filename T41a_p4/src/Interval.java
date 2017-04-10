
public class Interval<T extends Comparable<T>> implements IntervalADT<T> {

    // TODO declare any needed data members
	private T start;
	private T end;
	private String label;

    public Interval(T start, T end, String label) {
        // TODO check if end < start??
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
        // TODO Auto-generated method stub
    	
    	
    	
    	return true;
    }

    @Override
    public boolean contains(T point) {
        // check if the point is greater than or equal to the start and less than or equal to the end
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
