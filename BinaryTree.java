
/**
 * Interface for a binary tree.
 *
 * @param <E> the type of elements stored in the binary tree, 
 * must extend Comparable
 */
public interface BinaryTree<E extends Comparable<E>> {

    /**
     * Returns the element for which compareTo(element) evaluates to 0.
     * @param element - element to get
     * @return the element for which compareTo(element) evaluates to 0.
     */
    E get(E element);


    /**
     * Retrieves the root element of the binary tree.
     *
     * @return the root element of the binary tree
     */
    E getRootElement();

    /**
     * Retrieves the number of elements in the binary tree.
     *
     * @return the number of elements in the binary tree
     */
    int size();

    /**
     * Checks if the binary tree is empty.
     *
     * @return true if the binary tree is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Checks if the binary tree contains a specific element.
     *
     * @param element the element to check for
     * @return true if the binary tree contains the 
     *       element, false otherwise
     */
    boolean contains(E element);

    /**
     * Inserts a new element into the binary tree.
     *
     * @param element the element to insert
     */
    void insert(E element);

    /**
     * Removes a specific element from the binary tree.
     *
     * @param element the element to remove
     * @return true if the element was successfully removed, 
     *        false otherwise
     */
    boolean remove(E element);

    /**
     * Returns a string representation of the binary tree in 
     * in-order traversal.
     *
     * @return a string representation of the binary tree 
     * in in-order traversal
     */
    String toStringInOrder();

    /**
     * Returns a string representation of the binary tree in 
     * pre-order traversal.
     *
     * @return a string representation of the binary tree 
     * in pre-order traversal
     */
    String toStringPreOrder();

    /**
     * Returns a string representation of the binary tree in 
     * post-order traversal.
     *
     * @return a string representation of the binary tree 
     * in post-order traversal
     */
    String toStringPostOrder();
}

