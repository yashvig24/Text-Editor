package model.bst;

import java.lang.Math;
import java.util.Stack;
import java.lang.IllegalArgumentException;

/**
 * This class represents a AVLTreeSet.
 * It is a self-balancing tree set to keep the height of the tree around log(n)
 * where n is the total number of nodes present
 * It balances itself after every insert
 */
public class AVL<E extends Comparable<E>> extends TreeSet<E> {
    /**
     * Constructs a instance of an AVL Tree
     */
    public AVL() {
        super();
    }

    /**
     * @param element : element to be added
     * @return boolean : elemented successfully added -> true
     *                   else -> false
     * @modifies this
     * @effect adds element if element does not already exist
     */
    @Override
    public boolean add(E element) {
        boolean added = super.add(element);
        balance();
        return added;
    }

    // private method to balance the tree after an addition or deletion of a node
    private void balance() {
        TreeNode<E> parentOfUnBalance = findNodeOffBalance();
        if(parentOfUnBalance == null && isOffBalance(this.root))
            this.root = balance(this.root);
        else {
            if(isOffBalance(parentOfUnBalance.left))
                parentOfUnBalance.left = balance(parentOfUnBalance.left);
            else 
                parentOfUnBalance.right = balance(parentOfUnBalance.right);
        }
    }

    // private method to balance a offblanace node 
    // @param offBalance : the node that is off-balance
    private TreeNode<E> balance(TreeNode<E> offBalance) {
        if(offBalance != null) {
            TreeNode<E> balanced = null;
            if(getHeight(offBalance.right) > getHeight(offBalance.left)) {
                if(getHeight(offBalance.right.right) < getHeight(offBalance.right.left))
                    offBalance.right = rightRotation(offBalance.right);
                balanced = leftRotation(offBalance);
            }
            else {
                if(getHeight(offBalance.left.left) < getHeight(offBalance.left.right)) {
                    offBalance.left = leftRotation(offBalance.left);
                }
                balanced = rightRotation(offBalance);
            }
            return balanced;
        }
        return null;
    }

    // performs the leftRotation algorithm on the node
    // @param node for the algorithm to be performed on
    private TreeNode<E> leftRotation(TreeNode<E> node) {
        TreeNode<E> rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;
        return rightChild;
    }

    // performs the rightRotation algorithm on the node
    // @param node for the algorithm to be performed on
    private TreeNode<E> rightRotation(TreeNode<E> offBalance) {
        TreeNode<E> leftChild = offBalance.left;
        offBalance.left = leftChild.right;
        leftChild.right = offBalance;
        return leftChild;
    }

    // private method to help find the node off Balance in the tree
    // @return node that is off Balance first from the leaf up
    private TreeNode<E> findNodeOffBalance() {
        Stack<TreeNode<E>> stack = new Stack<TreeNode<E>>();
        findNodeOffBalance(this.root, stack);
        if(stack.size() == 0)
            return null;
        return stack.pop();
    }

    private void findNodeOffBalance(TreeNode<E> node, Stack<TreeNode<E>> stack) {
        if(node != null) {
            if(hasChildOffBalance(node))
                stack.push(node);
            findNodeOffBalance(node.left, stack);
            findNodeOffBalance(node.right, stack);
        }
    }

    private boolean hasChildOffBalance(TreeNode<E> node) {
        return isOffBalance(node.left) || isOffBalance(node.right);
    }

    private boolean isOffBalance(TreeNode<E> node) {
        return Math.abs(getHeight(node.left) - getHeight(node.right)) > 1;
    }

    /**
     * @param element : element to be removed
     * @return boolean : elemented successfully removed -> true
     *                   else -> false
     * @modifies this
     * @effect removes element if element exists
     */
    @Override
    public boolean remove(E element) {
        boolean removed = super.remove(element);
        balance();
        return removed;
    }
}