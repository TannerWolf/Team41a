/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          p3
// FILE:             FileLinePriorityQueue
//
// TEAM:    Team 41a
// Authors: 
// Author1: (Jiayue Lai, jlai28@wisc.edu, jlai, Lec 002)
// Author2: (William Mustari, willmustari@gmail.com, mustari, Lec 002) 
// Author3: (Tanner Wolf, tmwolf2@wisc.edu, tmwolf2, Lec 002) 
///////////////////////////////////////////////////////////////////////////////
import java.util.Comparator;

/**
 * An implementation of the MinPriorityQueueADT interface. This implementation stores FileLine objects.
 * See MinPriorityQueueADT.java for a description of each method. 
 *
 */
public class FileLinePriorityQueue implements MinPriorityQueueADT<FileLine> {
    private Comparator<FileLine> cmp;
    private int maxSize;
    private int numItems;
    FileLine a[];//an array based heap of FileLine
/**
 * Construct for initializing the FileLinePriorityQueue class
 * @param initialSize: the size of array based fileline
 * @param cmp: comparator used to compare big/small of fileline
 */
    public FileLinePriorityQueue(int initialSize, Comparator<FileLine> cmp) {
		this.cmp = cmp;
		maxSize = initialSize;
		a = new FileLine[maxSize + 1];
		numItems = 0;
		
    }
/**
 * Remove the minimum of FileLine array through using the minHeap and 
 * compare the left and right child of the element in the array. Throw the
 * exception when the array is empty
 */
    public FileLine removeMin() throws PriorityQueueEmptyException {

    	if (numItems == 0)
    		throw new PriorityQueueEmptyException();
    	
		FileLine min = a[1];//save the minimum 
		a[1] = a[numItems];//replace the first element in the array to the last one
		a[numItems] = null;
		numItems--;
		
		// Reorder to maintain structure
		int parent = 1;
		// 
		while (parent < numItems){
			int child = parent*2;
			if (child < numItems && a[child+1] != null) {
				int compare = cmp.compare(a[child], a[child +1]);//compare left child and right
				if (compare > 0 ){
					child += 1;//pick the right while right is smaller
				}
			}
			// Check if (smaller) child is smaller than parent
			if (a[parent] != null && a[child] != null) {
				int compare = cmp.compare(a[parent], a[child]);
				// if child is smaller, swap
				if (compare > 0) {
					FileLine temp = a[parent];
					a[parent] = a[child];
					a[child] = temp;
					parent = child;
				}
				// if parent larger than max child, done swapping
				else {
					return min;
				}
			}
			else {
				return min;
			}
		}
		return min;
    }
/**
 * Insert the FileLine in to the heap in minimum-based order. Throw
 * Exception when the array is full or when the input FileLine is
 * null
 *  */
    public void insert(FileLine fl) throws PriorityQueueFullException {
    	//Throw exception if the priority queue is full
    	if (numItems == maxSize) {
			throw new PriorityQueueFullException();
    	}
    	if (fl == null) {
    		throw new IllegalArgumentException();
    	}
		numItems++;
	    a[numItems] = fl;//save FileLine to the array
		boolean done = false;//decide whether the heap is in order
		int child = numItems;
		while (!done){
			int parent = child/2;
			int compare = -1;
			// If parent > 0, compare parent and child
			if (parent > 0) {
				compare = cmp.compare(a[parent], a[child]);
			}
			// If parent <= 0, or a[parent] < a[child], done with swapping
			if (compare < 0)
				done = true;
			else if (compare > 0){
				FileLine tmp = a[parent];
				a[parent] = a[child];
				a[child] = tmp;
				child = parent;
			}
			else { // If the lines have the same key
				done = true;
			}
		}
    }
/**
 * Check to see whether the array is empty
 */
    public boolean isEmpty() {
		return numItems <= 0;
    }
}