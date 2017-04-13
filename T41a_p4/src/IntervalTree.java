/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          p4
// FILE:             IntervalTree
//
// TEAM:    Team 41a
// Authors: 
// Author1: (Jiayue Lai, jlai28@wisc.edu, jlai, Lec 002)
// Author2: (William Mustari, willmustari@gmail.com, mustari, Lec 002) 
// Author3: (Tanner Wolf, tmwolf2@wisc.edu, tmwolf2, Lec 002) 
///////////////////////////////////////////////////////////////////////////////

import java.util.LinkedList;
import java.util.List;

public class IntervalTree<T extends Comparable<T>> implements IntervalTreeADT<T> {
	
	private IntervalNode<T> root = null;

	@Override
	public IntervalNode<T> getRoot() {
		return this.root;
	}

	@Override
	public void insert(IntervalADT<T> interval)
					throws IllegalArgumentException {
		if (interval == null)
			throw new IllegalArgumentException();
		
		insert(root, interval);
	}
	
	private IntervalNode<T> insert(IntervalNode<T> T, IntervalADT<T> interval){
		//If the tree is empty,create a new one
		if (root == null) {
			root = new IntervalNode<T>(interval);
			return root;
		}
		
		//If interval's start is different and is smaller, add to left sub tree
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
		// TODO Auto-generated method stub

	}

	@Override
	public IntervalNode<T> deleteHelper(IntervalNode<T> node,
					IntervalADT<T> interval)
					throws IntervalNotFoundException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return node;
	}

	@Override
	public List<IntervalADT<T>> findOverlapping(
					IntervalADT<T> interval) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IntervalADT<T>> searchPoint(T point) {
		// TODO Auto-generated method stub
		List<IntervalADT<T>> list = new LinkedList<IntervalADT<T>>();
		// Iterate through tree
		
		// Check if interval contains point
		return list;
	}

	@Override
	public int getSize() {
		//Calls helper method
		return getSize(root);
	}
	
	/**
	 * Helper method to find size of tree
	 * 
	 * @param node
	 * @return number of nodes in the tree
	 */
	private int getSize(IntervalNode<T> node) {
		if (node == null) return(0); 
		else { 
			return(getSize(node.getLeftNode()) + 1 + 
					getSize(node.getRightNode()));
	  } 
	}

	@Override
	public int getHeight() {
		//Calls helper method
		return getHeight(root);
	}
	
	/**
	 * Helper method to find height of tree
	 * 
	 * @param node
	 * @return max height of tree
	 */
	private int getHeight(IntervalNode<T> node) {
		int leftH = 1;
		int rightH = 1;
		
		//If root node is null, height is zero.
        if (node == null)
            return 0;
	
		// Recursion to find height
		if (node.getLeftNode() != null)
			leftH = 1 + getHeight(node.getLeftNode());
		if (node.getRightNode() == null)
			rightH = 1 + getHeight(node.getRightNode());
		
		// return max: if one is null, height remains 1
		if (leftH > rightH) {
			return leftH;
		}
		return rightH;
	}

	@Override
	public boolean contains(IntervalADT<T> interval) {
		//Calls helper method
		return contains();
	}
	
	private boolean contains() {
		//FINISH HELPER METHOD
		return false;
	}
	
//  public IntervalNode<T> search(IntervalNode<T> root, T left, T right){
//	//Check to see whether is reaching the end
//	//Do we need more check?????
//    if (root == null)
//    	return root;
//    // Compare to find whether is greater than root's start
//    //If 'start' are same, compare the 'end' values.
//    if (root.getInterval().getStart().compareTo(left) != 0){
//    	 if (root.getInterval().getStart().compareTo(left) > 0)
//             return search( root.getLeftNode(), left, right);
// 		return search(root.getRightNode(), left, right);
//    }
//    //The situation that start are the same
//    if (root.getInterval().getEnd().compareTo(right) > 0){
//    	return search( root.getLeftNode(), left, right);
//    }
//	return search(root.getRightNode(), left, right);
//}

	@Override
	public void printStats() {
		System.out.println("-----------------------------------------");
		System.out.println("Height: " + getHeight());
    	System.out.println("Size: " + getSize());
		System.out.println("-----------------------------------------");
	}
}