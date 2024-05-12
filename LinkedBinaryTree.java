/**
 * Class that implements Binary Search Tree.
 * @author Dennis Yiaile
 * @param <E> - enable E types
 */
@SuppressWarnings("hiding")
public class LinkedBinaryTree<E extends Comparable<E>> 
    implements BinaryTree<E> {
    // instance variables
    /** Root name. */
    Node<E> root;

    // Constructors.
    /** Creates an empty constructor that initialize a new tree.
     */
    LinkedBinaryTree() {
        root = null;
    }

    /** Create a constructor that takes in the inputed data as the root.
     * @param data - element to be added to the tree
    */
    LinkedBinaryTree(E data) {
        root = new Node<>(data);
    }

    /** Create a private class of type Node.
     * @param <E> - Polling Data type
     */
    public class Node<E> {

        // instance variable for the nodes.
        /** Element of the tree. */
        public E element;
        /** Left part of the tree. */
        public Node<E> left;
        /** Right part of the tree. */
        public Node<E> right;
        /** Height of the tree. */
        public int height;
        /** Parent of a given node. */
        public Node<E> parent;

        // Constructors.
        /** Empty constructor creating an empty node */
        Node() {
            this.left = null;
            this.right = null;
            this.parent = null;
            this.height = -1;
        }

        /** 
         * Constructor creates a new node root at the inputed element. 
         * @param element - element to the root of the current tree
        */
        Node(E data) {
            this.element = data;
            this.left = null;
            this.right = null;
            this.parent = null;
            this.height = 1;
        }

        /**
         * Return the parent node for a given node.
         * @return - Generic Node for that parent 
         */
        public Node<E> getParent() {
            return parent;
        }

        /**
         * Get the height of a tree or substree.
         * @return - int height of a tree.
         */
        public int getHeight() {
            return height;
        }

        
        /**
         * Get the value of a given node.
         * @return - generic node value
         */
        public E getData() {
            return element;
        }

        /**
         * Get the left node of some root node.
         * @return - Generic left node.
         */
        public Node<E> getLeft() {
            return left;
        }

        /**
         * Get the right node of some root node.
         * @return - Generic right node. 
         */
        public Node<E> getRight() {
            return right;
        }

        /**
         * Print the node element followed by it's height in parenthesis.
         * @return String - representation of a node
         */
        @Override
        public String toString() {
            String stringRep = element.toString();
            String stringHeight = Integer.toString(height);
            return stringRep + "(" + stringHeight + ")";
        }

    }

    /** Determines the number of elements in the tree.
     * @return int - the number of elements
    */
    public int size() {
        return numberOfNodes(root);
    }

    /**
     * Get the total height of the tree.
     * @return int - height of the root.
     */
    public int height() {
        if (root == null) {
            return -1;
        }
        return root.getHeight() - 1;
    }

    /**
     * Returns the element for which compareTo evaluates to 0.
     * @param element - to search
     * @return E - element if compareTo is 0 else null.
     */
    public E get(E element) {
        return getElement(root, element);
    }

    /**
     * Recursive method that returns the element for which
     * compareTo evaluate sto 0.
     * @param tree - Contains nodes to search
     * @param element1 - item to search
     * @return E- element if compareTo is 0 else null.
     */
    private E getElement(Node<E> tree, E element1) {
        if (tree == null) {
            return null;
        } else {
            if (tree.element.compareTo(element1) == 0) {
                return tree.element;
            } else if (tree.element.compareTo(element1) > 0) {
                return getElement(tree.left, element1);
            } else {
                return getElement(tree.right, element1);
            }
        }
    }

    /**
     * Get the root element of the current tree.
     * @return E root element of the current tree
     */
    public E getRootElement() {
        if (root == null) {
            return null;
        } else {
            return root.element;
        }
    }
    
    /** Determines the number of nodes in the tree recursively.
     * @param tree - tree containing nodes
     * @return - numbe of nodes in the tree
     */
    private int numberOfNodes(Node<E> tree) {
        if (tree == null) {
            return 0;
        } else {
            return 1 + numberOfNodes(tree.left) + numberOfNodes(tree.right);
        }
    }
    
    /**
     * Determines if the tree is empty.
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
        
    }

    /** Inserts an element into the tree.
     * @param element - the element to insert
     */
    public void insert(E element) {
        Node<E> current = new Node<>(element);
        root = insertElement(root, root, current);
    }

    /**
     * Recursive method to insert a new element to the tree.
     * @param tree - tree to insert an element
     * @param newNode - new node to add to the tree
     * @param parent - parent of the tree/subtree.
     */
    private Node<E> insertElement(Node<E> tree, 
        Node<E> parent, Node<E> newNode) {
        if (tree == null) {
            newNode.parent = parent;
            return newNode;
        } else {
            if (tree.element.compareTo(newNode.element) == 0) {
                tree.element = newNode.element;
            } else if (tree.element.compareTo(newNode.element) > 0) {
                tree.left = insertElement(tree.left, tree, newNode);
            } else {
                tree.right = insertElement(tree.right, tree, newNode);
            }
        }
        modifyHeights(tree);
        return rebalance(tree);
    }

    /**
     * Determines if the tree contains a specific element.
     * @param element - the element to search
     * @return a boolean if the item was found
     */
    public boolean contains(E element) {
        if (element == null) {
            return false;
        }
        return containsElement(root, element);
    }

    /** 
     * Recursive method to check if node is found in a tree.
     * @param tree - tree to search
     * @param element1 - item to search
     * @return a boolean if the element was found
     */
    public boolean containsElement(Node<E> tree, E element1) {
        if (tree == null) {
            return false;
        } else {
            if (tree.element.compareTo(element1) == 0) {
                return true;
            } else if (tree.element.compareTo(element1) > 0) {
                return containsElement(tree.left, element1);
            } else {
                return containsElement(tree.right, element1);
            }
        }
    }

    /**
     * Check if the element can be removed from the tree.
     * @param element - element to remove
     * @return true if removed false otherwise
     */
    public boolean remove(E element) {
        if (!contains(element)) {
            return false;
        } else {
            root = findAndRemove(root, element);
            return true;
        }
    }

    /**
     * Private method that finds and remove the element to remove from the tree
     * @param tree - tree to remove and element from
     * @param element1 node to remove
     * @return Node<E> - return the tree after items have been removed
     */
    private Node<E> findAndRemove(Node<E> tree, E element1) {
        if (tree.element.compareTo(element1) == 0) {
            if (tree.left == null && tree.right == null) {
                tree = null;
            } else {
                E promoteElement = nodeToPromote(tree);
                LinkedBinaryTree<E> newTree = 
                    new LinkedBinaryTree<>(promoteElement);
                newTree.root.parent = tree.parent;
                tree = removeNode(tree, newTree, element1);
            }
        } else {
            if (tree.element.compareTo(element1) > 0) {
                tree.left = findAndRemove(tree.left, element1);
            } else {
                tree.right = findAndRemove(tree.right, element1);
            }
        }
        return tree;
    }

    /**
     * Get the element to replace the element to remove.
     * @param tree - tree to search and get the element to remove
     * @return E - node to replace the element to be removed with
     */
    private E nodeToPromote(Node<E> tree) {
        if (tree.right == null) {
            return maxOnTheReft(tree.left);
        } else if (tree.left == null) {
            return minOnTheRight(tree.right);
        } else {
            return minOnTheRight(tree.right);
        }
    }

    /**
     * Find the minimum node on the right.
     * @param subTree - current subtree to search
     * @return E - minimum node on the right. 
     */
    private E minOnTheRight(Node<E> subTree) {
        if (subTree.left == null) {
            return subTree.element;
        }
        return minOnTheRight(subTree.left);
    }

    /**
     * Find the maximum node on the left.
     * @param subTree - current subtree to search
     * @return E - maximum node on the left. 
     */
    private E maxOnTheReft(Node<E> subTree) {
        if (subTree.right == null) {
            return subTree.element;
        }
        return maxOnTheReft(subTree.right);
    }

    /**
     * Remove element from the tree.
     * @param tree - where to remove the element from
     * @param newTree - new tree rooted at new element replacing the one removed
     * @param toRemove - node to be removed
     * @return Node<E>- the tree after element have been removed
     */
    private Node<E> removeNode(Node<E> tree, 
        LinkedBinaryTree<E> newTree, E toRemove) {
        if (tree != null) {
            if (tree.element.compareTo(toRemove) != 0 &&
                tree.element.compareTo(newTree.root.element) != 0) {
                newTree.insert(tree.element);
            }
            removeNode(tree.left, newTree, toRemove);
            removeNode(tree.right, newTree, toRemove);
        }
        return newTree.root;
    }

    /**
     * Modify heights of every node in a tree.
     * @param tree - tree containing nodes
     * @return int - height of a tree/subtree
     */
    public int modifyHeights(Node<E> tree) {
        int left = tree.left == null ? 0 : tree.left.height;
        int right = tree.right == null ? 0 : tree.right.height;
        int currentHeight = 1 + Math.max(left, right);
        tree.height = currentHeight;
        return currentHeight;
    }

    /**
     * Create a string representation of the elements in the tree.
     * the string is based on an in-order traversal.
     * @return A string representation of the elements in the tree
     */
    public String toStringInOrder() {
        String in = toStringInOrder(root).trim();
        return in; 
    }

    /**
     * Recursive method that builds a string of elements
     * in a tree based on an in-order traversal.
     * @param tree - subtree to get elements from
     * @return - a string representation of the elements in the tree
     */
    private String toStringInOrder(Node<E> tree) {
        if (tree != null) {
            return toStringInOrder(tree.left) + 
            tree.toString() + " " + toStringInOrder(tree.right);
        } else {
            return "";
        }
    }

    /**
     * Create a string representation of the elements in the tree.
     * the string is based on an pre-order traversal.
     * @return A string representation of the elements in the tree
     */
    public String toStringPreOrder() {
        String pre = toStringPreOrder(root);
        return pre; 
    }

    /**
     * Recursive method that builds a string of elements
     * in a tree based on an pre-order traversal. 
     * @param tree - subtree to get elements from
     * @return - a string representation of the elements in the tree
     */
    private String toStringPreOrder(Node<E> tree) {
        if (tree != null) {
            return tree.toString() + " " + 
                toStringPreOrder(tree.left) + toStringPreOrder(tree.right);
        } else {
            return "";
        }
    }

    /**
     * Create a string representation of the elements in the tree.
     * the string is based on an post-order traversal.
     * @return A string representation of the elements in the tree
     */ 
    public String toStringPostOrder() {
        String post = toStringPostOrder(root);
        return post;
    }

    /**
     * Recursive method that builds a string of elements
     * in a tree based on an post-order traversal. 
     * @param tree - subtree to get elements from
     * @return - a string representation of the elements in the tree
     */
    private String toStringPostOrder(Node<E> tree) {
        if (tree != null) {
            return toStringPostOrder(tree.left) + 
            toStringPostOrder(tree.right) + tree.toString() + " ";
        } else {
            return "";
        }
    }

    /**
     * Return a string representation of the tree for all the traversal methods.
     * @return String - all traversal methods of a tree
     */
    @Override
    public String toString() {
        return toStringInOrder();
    }

    /**
     * Rotate right.
     * @param n - Tree to rotate
     * @return - Node<E> root of the tree or subtree rotated
     */
    public Node<E> rotateRight(Node<E> n) {
        Node<E> prevRight = n.left.right;
        if (prevRight != null) {
            prevRight.parent = n;
        }
        if (n.parent == null) {
            root = n.left;
        } else if (n.parent.left != null && 
            n.parent.left.element.compareTo(n.element) == 0) {
            n.parent.left = n.left;
        } else {
            n.parent.right = n.left;
        }

        n.left.right = n;
        n.left.parent = n.parent;
        n.parent = n.left;
        n.left = prevRight;
        modifyHeights(n);
        modifyHeights(n.parent);
        return n.parent;
    }

    /**
     * Rotate the tree to the left.
     * @param n - Tree to rotate
     * @return - root of the tree/subtree to rotate
     */
    public Node<E> rotateLeft(Node<E> n) {
        Node<E> prevLeft = n.right.left;
        if (prevLeft != null) {
            prevLeft.parent = n;
        }
        if (n.parent == null) {
            root = n.right;
        } else if (n.parent.left != null && 
            n.parent.left.element.compareTo(n.element) == 0) {
            n.parent.left = n.right;
        } else {
            n.parent.right = n.right;
        }

        n.right.left = n;
        n.right.parent = n.parent;
        n.parent = n.right;
        n.right = prevLeft;
        modifyHeights(n);
        modifyHeights(n.parent);
        return n.parent;
    }

    /**
     * Rotate the tree from left then right.
     * @param n - tree/subtree to rotate
     * @return - Node<E> rotated at that root.
     */
    public Node<E> rotateLeftRight(Node<E> n) {
        n.left = rotateLeft(n.left);
        return rotateRight(n);
    }

    /**
     * Rotate tree to the right then left.
     * @param n - tree/substree to rotate
     * @return - root where that tree is rotated.
     */
    public Node<E> rotateRightLeft(Node<E> n) {
        n.right = rotateRight(n.right);
        return rotateLeft(n);
    }

    /**
     * Helper method rebalances the tree.
     * @param myTree - tree to balance
     * @return - Node<E> if balance null otherwise
     */
    public Node<E> rebalance(Node<E> myTree) {
        int balancingFactor = getBalanceFactor(myTree);
        if (balancingFactor > 1) {
            if (getBalanceFactor(myTree.right) < 0) {
                myTree = rotateRightLeft(myTree);
            } else {
                myTree = rotateLeft(myTree);
            }
        } else if (balancingFactor < -1) {
            if (getBalanceFactor(myTree.left) > 0) {
                myTree = rotateLeftRight(myTree);
            } else {
                myTree = rotateRight(myTree);
            }
        }
        return myTree;
    }

    /**
     * Get the tree/subtree balance factor.
     * @param tree - tree to get balance factor
     * @return
     */
    private int getBalanceFactor(Node<E> tree) {
        int left = tree.left == null ? 0 : tree.left.height;
        int right = tree.right == null ? 0 : tree.right.height;
        return right - left;
    }
}
