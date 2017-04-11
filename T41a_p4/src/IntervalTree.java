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
	private int size = 0;					// not sure if we need these but they might be helpful - T
	
	@Override
	public IntervalNode<T> getRoot() {
		return this.root;
	}

	@Override
	public void insert(IntervalADT<T> interval)
					throws IllegalArgumentException {
		// TODO Recursively insert
		if (interval == null) {
			throw new IllegalArgumentException();
		}
		if (size == 0) {
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
		// TODO Auto-generated method stub
		return size;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void printStats() {
		System.out.println("-----------------------------------------");
		System.out.println("Height: " + getHeight());
    	System.out.println("Size: " + getSize());
		System.out.println("-----------------------------------------");
	}
}
