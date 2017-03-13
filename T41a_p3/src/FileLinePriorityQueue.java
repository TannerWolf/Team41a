import java.util.Comparator;

/**
 * An implementation of the MinPriorityQueueADT interface. This implementation stores FileLine objects.
 * See MinPriorityQueueADT.java for a description of each method. 
 *
 */
public class FileLinePriorityQueue implements MinPriorityQueueADT<FileLine> {
    // TODO
    private Comparator<FileLine> cmp;
    private int maxSize;
    private int numItems;
    FileLine a[];

    public FileLinePriorityQueue(int initialSize, Comparator<FileLine> cmp) {
		this.cmp = cmp;
		maxSize = initialSize;
		a = new FileLine[maxSize];
		numItems = 0;
		
    }

    public FileLine removeMin() throws PriorityQueueEmptyException {
		boolean find = false;
		/**int height = (int)(Math.log(numItems)/Math.log(2));
		int j = 0;
		
		FileLine tmp;
		while (!find){
			 
		        for (int i = numItems - 2 * height + j; i <= numItems; j++) {
		        	int min = cmp.compare(a[i], a[i+1]);
		        	if (min > 0) {
		                
		        }
		            
		                
		            }
		}
*/
		return null;
    }

    public void insert(FileLine fl) throws PriorityQueueFullException {
		if (fl == null)
			throw new PriorityQueueFullException();
		else
			numItems++;
	    a[numItems] = fl;
		boolean done = false;
		int child =numItems;
		while (!done){
			int parent = child/2;
			
			int compare = cmp.compare(a[parent], a[child]);
			if (parent == 0)
				done = true;
			else if (compare == 0)
				done = true;
			else if (compare < 0){
				FileLine tmp = a[parent];
				a[parent] = a[child];
				a[child] = tmp;
				done = true;

			}
		}
    }

    public boolean isEmpty() {
		return numItems <= 0;
    }
}
