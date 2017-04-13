import java.util.List;

public class IntervalTree<T extends Comparable<T>> implements IntervalTreeADT<T> {
	
	private IntervalNode<T> root = null;
		

	@Override
	public IntervalNode<T> getRoot() {
		root = this.getRoot();
		return root;
	}
    /**public IntervalNode<T> search(IntervalNode<T> root, T left, T right){
    	//Check to see whether is reaching the end
    	//Do we need more check?????
        if (root == null)
        	return root;
        // Compare to find whether is greater than root's start
        //If 'start' are same, compare the 'end' values.
        if (root.getInterval().getStart().compareTo(left) != 0){
        	 if (root.getInterval().getStart().compareTo(left) > 0)
                 return search( root.getLeftNode(), left, right);
     		return search(root.getRightNode(), left, right);
        }
        //The situation that start are the same
        if (root.getInterval().getEnd().compareTo(right) > 0){
        	return search( root.getLeftNode(), left, right);
        }
		return search(root.getRightNode(), left, right);
            
    
    }
    **/
	@Override
	public void insert(IntervalADT<T> interval)
					throws IllegalArgumentException {
		if (interval == null)
			throw new IllegalArgumentException();
		
		insert(root, interval);
		
		
	}
	
	public IntervalNode<T> insert(IntervalNode<T> T, IntervalADT<T> interval){
		//If the tree is empty,create a new one
		if (root == null) {
			root = new IntervalNode<T>(interval);
			return root;
		}//If interval's start is different and is smaller, add to left sub tree
		//recursively trace 
		if (interval.getStart().compareTo(root.getInterval().getStart()) < 0){
			root.setLeftNode(insert(root.getLeftNode(), interval));
			
		}
		//Insert it into T's right subtree if it's start is 
		//different and larger than that of T
		if ((interval.getStart().compareTo(T.getInterval().getStart()) > 0)){
			root.setRightNode(insert(root.getLeftNode(), interval));	
		}
		
		//If interval's start is equal to that of T, than compare its end
		else if (interval.getEnd().compareTo(T.getInterval().getEnd()) < 0){
			root.setLeftNode(insert(root.getLeftNode(), interval));
		}
		//Insert it into T's right subtree if it's start is 
		//different and larger than that of T
		else if ((interval.getEnd().compareTo(T.getInterval().getEnd()) > 0)){
			root.setRightNode(insert(root.getLeftNode(), interval));
		}
		//return the unchanged node pointer
		return root;
		
	}

	@Override
	public void delete(IntervalADT<T> interval)
					throws IntervalNotFoundException, IllegalArgumentException {
		if (interval == null) 
			throw new IllegalArgumentException();
		//not sure
		if (!interval.overlaps(interval))
			throw new IntervalNotFoundException(interval.toString());
			
		deleteHelper(root,interval);

	}

	@Override
	public IntervalNode<T> deleteHelper(IntervalNode<T> node,
					IntervalADT<T> interval)
					throws IntervalNotFoundException, IllegalArgumentException {
		// TODO Auto-generated method stub
	}

	@Override
	public List<IntervalADT<T>> findOverlapping(
					IntervalADT<T> interval) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<IntervalADT<T>> searchPoint(T point) {
		// TODO Auto-generated method stub
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean contains(IntervalADT<T> interval) {
		// TODO Auto-generated method stub
	}

	@Override
	public void printStats() {
		// TODO Auto-generated method stub

	}

}
