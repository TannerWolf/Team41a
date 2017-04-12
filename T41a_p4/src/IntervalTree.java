import java.util.List;

public class IntervalTree<T extends Comparable<T>> implements IntervalTreeADT<T> {
	
	private IntervalNode<T> root = null;
		

	@Override
	public IntervalNode<T> getRoot() {
		root = this.getRoot();
		return root;
	}
    public IntervalNode<T> search(IntervalNode<T> root, T left, T right){
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
	@Override
	public void insert(IntervalADT<T> interval)
					throws IllegalArgumentException {
		if (interval == null)
			throw new IllegalArgumentException();
		if (root == null) {
			root = new IntervalNode<T>(interval);
		}
				
	}

	@Override
	public void delete(IntervalADT<T> interval)
					throws IntervalNotFoundException, IllegalArgumentException {
		// TODO Auto-generated method stub

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
