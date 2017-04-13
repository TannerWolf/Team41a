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
		
		//Calls helper method
		root = insert(root, interval);
	}
	
	private IntervalNode<T> insert(IntervalNode<T> node, IntervalADT<T> interval){
		//If the tree is empty, create a new node
		if (node == null) {
			node = new IntervalNode<T>(interval);
			return node;
		}
		
		//If interval is smaller, add to left sub tree
		else if (interval.compareTo(node.getInterval()) < 0){
			node.setLeftNode(insert(node.getLeftNode(), interval));
		}
		
		//If interval is larger, add to right sub tree
		else if ((interval.compareTo(node.getInterval()) > 0)){
			node.setRightNode(insert(node.getRightNode(), interval));	
		}
		
		//Return the unchanged node pointer
		return node;
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
		int leftHeight = 0;
		int rightHeight = 0;

		// Recursion to find height
		if (node.getLeftNode() != null)
			leftHeight = getHeight(node.getLeftNode());
		if (node.getRightNode() != null)
			rightHeight = getHeight(node.getRightNode());
		
		// Returns max: if one is null, height remains 1
		if (leftHeight > rightHeight) {
			return leftHeight+1;
		}
		else{
			return rightHeight+1;
		}
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