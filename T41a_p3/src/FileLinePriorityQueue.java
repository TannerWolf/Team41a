import java.util.Comparator;

/**
 * An implementation of the MinPriorityQueueADT interface. This implementation stores FileLine objects.
 * See MinPriorityQueueADT.java for a description of each method. 
 *
 */
public class FileLinePriorityQueue implements MinPriorityQueueADT<FileLine> {
    // TODO
	//
    private Comparator<FileLine> cmp;
    // Max size of structure
    private int maxSize;
    // Array for queue structure

    /**
     * Initialize global variables
     * 
     * @param initialSize
     * @param cmp
     */
    public FileLinePriorityQueue(int initialSize, Comparator<FileLine> cmp) {
		this.cmp = cmp;
		maxSize = initialSize;
		
		// TODO
    }

    /**
     * Removes the minimum element from the Priority Queue, and returns it.
     *
     * @return the minimum element in the queue, according to the compareTo()
     * method of FileLine.
     * @throws PriorityQueueEmptyException if the priority queue has no elements
     * in it
     */
    public FileLine removeMin() throws PriorityQueueEmptyException {
		// TODO

		return null;
    }


    /**
     * Inserts a FileLine into the queue, making sure to keep the shape and
     * order properties intact.
     *
     * @param fl the FileLine to insert
     * @throws PriorityQueueFullException if the priority queue is full.
     */
    public void insert(FileLine fl) throws PriorityQueueFullException {
		// TODO
    }

    public boolean isEmpty() {
		return maxSize <= 0;
    }
}
