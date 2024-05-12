/**
 * Class creates an AVL tree.
 * @author Dennis Yiaile
 * @param <E> - generic type.
 */
public class AVLTree<E extends Comparable<E>> extends LinkedBinaryTree<E> {

    /** Initialize a new tree of type LinkedBinaryTree. */
    public LinkedBinaryTree<E> tree;

    // Constructors.
    /** Create a new empty tree. */
    public AVLTree() {
        tree = new LinkedBinaryTree<>();
    }

    /**
     * Create a new tree rooted at the new data.
     * @param data - new data 
     */
    public AVLTree(E data) {
        tree = new LinkedBinaryTree<>(data);
    }

    /**
     * Get the root of the tree.
     * @return Node<E> - root of the tree.
     */
    public Node<E> getRoot() {
        return tree.root;
    }

    
    /**
     * Get the root element of the current tree.
     * @return E root element of the current tree
     */
    @Override
    public E getRootElement() {
        if (getRoot() == null) {
            return null;
        } else {
            return getRoot().element;
        }
    }
    
    /**
     * Get the total height of the tree.
     * @return int - height of the root.
     */
    public int height() {
        if (getRoot() == null) {
            return -1;
        }
        return getRoot().getHeight() - 1;
    }

    /**
     * Determines if the tree is empty.
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return getRoot() == null;
        
    }

    /** Determines the number of elements in the tree.
     * @return int - the number of elements
    */
    public int size() {
        return tree.size();
    }

    /**
     * Insert element into the tree.
     * @param element - item to be inserted
     */
    @Override
    public void insert(E element) {
        tree.insert(element);
    }

    /**
     * Determines if the tree contains a specific element.
     * @param element - the element to search
     * @return a boolean if the item was found
     */
    @Override
    public boolean contains(E element) {
        if (element == null) {
            return false;
        }
        return containsElement(getRoot(), element);
    }

    /**
     * First checks if element is in the tree.
     * then removes it from the tree if found.
     * @param element - element to be removed
     * @return - true if removed false otherwise.
     */
    @Override
    public boolean remove(E element) {
        Boolean removed = tree.remove(element);
        return removed;
    }

    /**
     * Return a string representation of the tree for all the traversal methods.
     * @return String - all traversal methods of a tree
     */
    @Override
    public String toString() {
        return tree.toString();
    }

    /**
     * Returns the element for which compareTo evaluates to 0.
     * @return E - element if compareTo is 0 else null.
     */
    @Override
    public E get(E element) {
        return tree.get(element);
    }
}