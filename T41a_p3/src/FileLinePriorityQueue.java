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

    public FileLinePriorityQueue(int initialSize, Comparator<FileLine> cmp) {
		this.cmp = cmp;
		maxSize = initialSize;
		a = new FileLine[maxSize];
		numItems = 0;
		
    }

    public FileLine removeMin() throws PriorityQueueEmptyException {
    	if (numItems == 0)//??not sure whether checking condition is right
    		throw new PriorityQueueEmptyException();
    	
		FileLine temp = a[1];//save the minimum 
		a[1] = a[numItems];//replace the first element in the array to the last one
		numItems--;
		int i = 1;
		int n = 0;
		while (n <= (int)(Math.log(numItems)/Math.log(2))){//do while the number of swape is not the
			//same as height of the tree
			int child = i*2;
			int compare = cmp.compare(a[child+1], a[child]);//compare left child and right
			if (compare < 0 ){
				child += 1;//pick the right while right is smaller
			a[i] = a[child];
			i = child;	
			}
			else if(compare > 0){
				a[i] = a[child];
				i = child;
			}
			n++;
		}
			
		
		return temp;
    }

    public void insert(FileLine fl) throws PriorityQueueFullException {
		if (numItems == maxSize)
			throw new PriorityQueueFullException();//Throw exception if the priority queue
		//is full
	    a[numItems] = fl;//save FileLine to the array
		boolean done = false;//decide whether the FileLine are saved in decremental order
		int child = numItems;
		if (child == 1) {
			done = true;
		}
		while (!done){
			int parent = child/2;
			
			int compare = cmp.compare(a[parent], a[child]);
			if (parent == 0)
				done = true;
			//Check to see whether parents are smaller than child, if not swap the order
			else if (compare < 0)
				done = true;
			else if (compare > 0){
				FileLine tmp = a[parent];
				a[parent] = a[child];
				a[child] = tmp;
				done = true;

			}
		}
		numItems++;
    }

    public boolean isEmpty() {
		return numItems <= 0;
    }
}
