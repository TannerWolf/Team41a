
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
	public void insert(IntervalADT<T> interval) throws IllegalArgumentException {
		if (interval == null)
			throw new IllegalArgumentException();

		// Calls helper method
		root = insert(root, interval);
		// TODO call recalculateMaxEnd here??
	}

	private IntervalNode<T> insert(IntervalNode<T> node, IntervalADT<T> interval) {
		// If the tree is empty, create a new node
		if (node == null) {
			node = new IntervalNode<T>(interval);
			return node;
		}

		// If interval is smaller, add to left sub tree
		if (interval.compareTo(node.getInterval()) < 0) {
			node.setLeftNode(insert(node.getLeftNode(), interval));
		}

		// If interval is larger, add to right sub tree
		else if ((interval.compareTo(node.getInterval()) > 0)) {
			node.setRightNode(insert(node.getRightNode(), interval));
		}
		else {
			throw new IllegalArgumentException();
		}

		// Update max end
		node.setMaxEnd(recalculateMaxEnd(node));

		// Return the unchanged node pointer
		return node;
	}

	@Override
	public void delete(IntervalADT<T> interval) throws IntervalNotFoundException, IllegalArgumentException {
		if (interval == null)
			throw new IllegalArgumentException();
		if (contains(interval) == false)
			throw new IntervalNotFoundException(
					interval.getLabel() + " [" + interval.getStart() + ", " + interval.getEnd() + "]");
		// Calls helper method
		deleteHelper(root, interval);
		// TODO call recalculateMaxEnd here?
	}

	@Override
	public IntervalNode<T> deleteHelper(IntervalNode<T> node, IntervalADT<T> interval) {
		// TODO Insert comments
		if (interval.compareTo(node.getInterval()) == 0) {
			if (node.getLeftNode() == null && node.getRightNode() == null) {
				return null;
			}
			if (node.getLeftNode() == null) {
				return node.getRightNode();
			}
			if (node.getRightNode() == null) {
				return node.getLeftNode();
			}
		}

		else if (interval.compareTo(node.getInterval()) < 0) {
			node.setLeftNode(deleteHelper(node.getLeftNode(), interval));
		}

		else
			node.setRightNode(deleteHelper(node.getRightNode(), interval));
		
		recalculateMaxEnd(node);
		return node;
	}

	/**
	 * Check if the max end has changed and update it
	 * 
	 * @param node
	 * @return maximum maxEnd of self and children
	 */
	private T recalculateMaxEnd(IntervalNode<T> node) {
		T thisMax = node.getInterval().getEnd();
		
		// check if the children are both null: if so, skip and return
		if (node.getLeftNode() == null && node.getRightNode() == null) {
			return thisMax;
		}
		
		T tempL = null;
		T tempR = null;

		// get the maxEnd of either child
		if (node.getLeftNode() != null) {
			tempL = node.getLeftNode().getMaxEnd();
		}
		if (node.getRightNode() != null) { // && leftNode != null
			tempR = node.getRightNode().getMaxEnd();
		}

		// Get the maximum of the two maxEnds
		T maxChildEnd = null;
		if (tempR != null) {
			maxChildEnd = tempR;
			if (tempL != null)
				if (tempR.compareTo(tempL) < 0) {
					maxChildEnd = tempL;
				}
				else {
					maxChildEnd = tempR;
			}
		}
		else { //if (tempL != null)
			maxChildEnd = tempL;
		}
		
		// Compare thisMax to maximum maxEnd of the children
		if (maxChildEnd.compareTo(thisMax) > 0 ) {
			thisMax = maxChildEnd;
		}

		return thisMax;
	}

	@Override
	public List<IntervalADT<T>> findOverlapping(IntervalADT<T> interval) {
		// TODO MAY NEED SOME HELP
		// return findOverlappingHelper(root, interval, *list?*);
		return null; // TEMPORARY
	}

	// private void findOverlappingHelper(IntervalNode<T> node,
	// IntervalADT<T> interval, List<IntervalADT<T>> result){
	// // TODO MAY NEED SOME HELP
	// return;
	// }

	@Override
	public List<IntervalADT<T>> searchPoint(T point) {
		if (point == null)
			throw new IllegalArgumentException();

		// TODO MAY NEED SOME HELP
		if (point.compareTo(root.getInterval().getStart()) == 0) {

		}

		if (point.compareTo(root.getInterval().getStart()) < 0) {

		}

		if (point.compareTo(root.getInterval().getStart()) > 0) {

		}

		List<IntervalADT<T>> list = new LinkedList<IntervalADT<T>>();

		return list;
	}

	@Override
	public int getSize() {
		// Calls helper method
		return getSizeHelper(root);
	}

	/**
	 * Helper method to find size of tree
	 * 
	 * @param node
	 * @return number of nodes in the tree
	 */
	private int getSizeHelper(IntervalNode<T> node) {
		if (node == null)
			return (0);
		else {
			return (getSizeHelper(node.getLeftNode()) + 1 + getSizeHelper(node.getRightNode()));
		}
	}

	@Override
	public int getHeight() {
		// Calls helper method
		return getHeightHelper(root);
	}

	/**
	 * Helper method to find height of tree
	 * 
	 * @param node
	 * @return max height of tree
	 */
	private int getHeightHelper(IntervalNode<T> node) {
		int leftHeight = 0;
		int rightHeight = 0;

		// Recursion to find height
		if (node.getLeftNode() != null)
			leftHeight = getHeightHelper(node.getLeftNode());
		if (node.getRightNode() != null)
			rightHeight = getHeightHelper(node.getRightNode());

		// Returns max: if one is null, height remains 1
		if (leftHeight > rightHeight) {
			return leftHeight + 1;
		} else {
			return rightHeight + 1;
		}
	}

	@Override
	public boolean contains(IntervalADT<T> interval) {
		// Calls helper method
		return containsHelper(root, interval);
	}

	private boolean containsHelper(IntervalNode<T> node, IntervalADT<T> interval) {
		if (node == null) {
			return false;
		}
		// if interval equals current node's interval, returns true
		if (interval.compareTo(node.getInterval()) == 0) {
			return true;
		}
		// interval < this node's interval; look in left subtree
		if (interval.compareTo(node.getInterval()) < 0) {
			return containsHelper(node.getLeftNode(), interval);
		}
		// interval > this node's interval; look in left subtree
		else {
			return containsHelper(node.getRightNode(), interval);
		}
	}

	@Override
	public void printStats() {
		System.out.println("-----------------------------------------");
		System.out.println("Height: " + getHeight());
		System.out.println("Size: " + getSize());
		System.out.println("-----------------------------------------");
	}
}