package model.bst;

/**
 * Interface for a Set
 */
public interface Set<E> {

    /**
     * @param element : element to be added
     * @return boolean : elemented successfully added -> true
     *                   else -> false
     * @modifies this
     * @effect adds element if element does not already exist
     */
    public boolean add(E element);

    /**
     * @param element : element to be removed
     * @return boolean : elemented successfully removed -> true
     *                   else -> false
     * @modifies this
     * @effect removes element if element exists
     */
    public boolean remove(E element);

    /**
     * @param element : element to be checked for containment
     * @return boolean : contains -> true
     *                   else -> false
     */
    public boolean contains(E element);

    /**
     * @return size of the current state of the set
     */
    public int size();
}