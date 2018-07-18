package model.bst;


import java.lang.Math;
import java.lang.IllegalArgumentException;

/**
 * The TreeSet class creates a Set of any type
 * and arranges them in sorted order
 */
public class TreeSet<E extends Comparable<E>> implements Set<E> {

    protected TreeNode<E> root;
    protected int size;

    /**
     * Creates an instance of the TreeSet<>
     */
    public TreeSet() {
         this.size = 0;
    }

    /**
     * @param element : element to be added
     * @return boolean : elemented successfully added -> true
     *                   else -> false
     * @modifies this
     * @effect adds element if element does not already exist
     */
    public boolean add(E element) {
        // if tree is empty, initialize root
        if(this.root == null) {
            this.root = new TreeNode<E>(element);
            return true;
        }
        TreeNode<E> curr = this.root;
        // have to keep track of parent node
        while((curr.data.compareTo(element) > 0 && curr.right != null) || 
        (curr.data.compareTo(element) < 0 && curr.left != null)) {
            if(curr.data.compareTo(element) > 0)
                curr = curr.right;
            else if(curr.data.compareTo(element) < 0)
                curr = curr.left;
            else {
                // if it is the same as another element (compareTo function returns 0)
                // then do not add duplicate
                return false;
            }
        } 

        // add child to parent node depending on comparison
        if(curr.data.compareTo(element) > 0) {
            curr.right = new TreeNode<E>(element);
        }
        else if(curr.data.compareTo(element) < 0) {
            curr.left = new TreeNode<E>(element);
        }
        size++;
        return true;
    }

    /**
     * @param element : element to be removed
     * @return boolean : elemented successfully removed -> true
     *                   else -> false
     * @modifies this
     * @effect removes element if element exists
     */
    public boolean remove(E element) {
        // if element not contained in tree, return false
        if(!contains(element))
            return false;
        // handle case of deletion of root
        if(this.root.data.equals(element)) {
            if(this.root.isLeaf()) {
                this.root = null;
            }
            else if(!this.root.hasBothChildren()) {
                if(this.root.left != null) 
                    this.root = this.root.left;
                else
                    this.root = this.root.right;
            }
            else {
                removeBothChildNode(this.root);
            }
            return true;
        }
        // handle the case of deletion of any other node
        TreeNode<E> parent = getParentOf(element);
        TreeNode<E> elementNode = null;
        if(parent.left.data.equals(element)) 
            elementNode = parent.left;
        else    
            elementNode = parent.right;
        if(elementNode.isLeaf()) {
            removeLeaf(elementNode, parent);
        }
        else if(elementNode.hasBothChildren()) {
            removeBothChildNode(elementNode);
        }
        else {
            removeSingleChild(elementNode, parent);
        }
        return true;
    }

    // requires that element E is not data of root
    private TreeNode<E> getParentOf(E element) {
        TreeNode<E> parent = this.root;
        TreeNode<E> curr = this.root;
        while(curr.data.equals(element)) {
            parent = curr;
            if(curr.data.compareTo(element) > 0)
                curr = curr.left;
            else
                curr = curr.right;
        }
        return parent;
    }

    // private method responsible for deleting a node which is identified to be a leaf
    // (have no child)
    // @param element : element to be removed
    // @param parent: the parent of the element to be removed
    private void removeLeaf(TreeNode<E> element, TreeNode<E> parent) {
        if(parent.left.equals(element)) {
            parent.left = null;
        }
        else {
            parent.right = null;
        }
    }

    // private method responsible for deleting a node which is identified to have a 
    // single child
    // @param element : element to be removed
    // @param parent: the parent of the element to be removed
    private void removeSingleChild(TreeNode<E> elementNode, TreeNode<E> parent) {
        if(parent.left.equals(elementNode)) {
            if(parent.left.left != null)
                parent.left = parent.left.left;
            else
                parent.left = parent.left.right;
        }
        else {
            if(parent.right.left != null)
                parent.right = parent.right.left;
            else
                parent.right = parent.right.right;
        }
    }

    // private method responsible for deleting a node which is identified 
    // to have both children
    // @param element : element to be removed
    private void removeBothChildNode(TreeNode<E> elementNode) {
        TreeNode<E> rightChild = elementNode.right;
        TreeNode<E> leftmostChild = rightChild;
        TreeNode<E> parent = elementNode;
        while(leftmostChild.left != null) {
            parent = leftmostChild;
            leftmostChild = rightChild.left;
        }
        elementNode.data = leftmostChild.data;
        removeSingleChild(leftmostChild, parent);
    }

    // returns the height of the tree
    public int getHeight() {
        return getHeight(this.root);
    }

    // @param node : root of the tree
    // returns the height of the tree with node as root
    protected int getHeight(TreeNode<E> node) {
        if(node == null) {
            return 0;
        }
        else {
            return 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
    }

    /**
     * @return size of the current state of the set
     */
    public int size() {
        return size;
    }

    /**
     * @param element : element to be checked for containment
     * @return boolean : contains -> true
     *                   else -> false
     */
    public boolean contains(E element) {
        TreeNode<E> curr = this.root;
        while(curr != null) {
            if(curr.data.compareTo(element) > 0)
                curr = curr.right;
            else if(curr.data.compareTo(element) < 0)
                curr = curr.left;
            else
                return true;
        } 
        return false;
    }

    /**
     * This class represents a Node of the Tree in a TreeSet
     * @specfield data
     * @specfield children (left and right instances of Nodes)
     */
    protected class TreeNode<E> {
        E data;
        TreeNode<E> left;
        TreeNode<E> right;

        /**
         * Constructs and instance of TreeNode
         * @param data : the data in the node
         * @param left : the left child reference
         * @param right : the right child reference
         */
        TreeNode(E data, TreeNode<E> left, TreeNode<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        /**
         * Constructs an instance of TreeNode
         * @param data : the data in the node
         * sets left and right children to null
         */
        TreeNode(E data) {
            this(data, null, null);
        }

        /**
         * Constructs an instance of TreeNode
         * sets left and right children to null
         * sets data to null
         */
        TreeNode() {
            this(null, null, null);
        }

        /**
         * returns if this is a leafNode
         * returns true if both children are null
         * returns false otherwise
         */
        boolean isLeaf() {
            return this.left == null && this.right == null;
        }

        /**
         * @param other : Another object to be compared to
         * returns true if this and other object are the same
         * Are same if they have same data
         */
        public boolean equals(Object other) {
            if(!(other instanceof TreeNode)) {
                return false;
            } 
            TreeNode<E> o = (TreeNode<E>) other;
            return o.data.equals(this.data);
        }

        /**
         * returns true if neither of its children are null
         */
        boolean hasBothChildren() {
            return this.left != null && this.right != null;
        }
    }
}