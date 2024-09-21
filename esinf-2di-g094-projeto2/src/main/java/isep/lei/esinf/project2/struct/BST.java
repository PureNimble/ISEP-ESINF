package isep.lei.esinf.project2.struct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import isep.lei.esinf.project2.struct.interfaces.BSTInterface;

public class BST<E extends Comparable<E>> implements BSTInterface<E> {

    /**
     * This class represents a node of a binary search tree.
     * It contains an element stored at this node, a reference to the left child (if
     * any),
     * and a reference to the right child (if any).
     *
     * @param <E> the type of element stored in the node
     */
    public static class Node<E> {
        private E element; // an element stored at this node
        private Node<E> left; // a reference to the left child (if any)
        private Node<E> right; // a reference to the right child (if any)

        /**
         * Represents a node in a binary search tree.
         *
         * @param <E> the type of element stored in the node
         */
        public Node(E e, Node<E> leftChild, Node<E> rightChild) {
            element = e;
            left = leftChild;
            right = rightChild;
        }

        /**
         * Returns the element of type E stored in the node.
         *
         * @return the element of type E stored in the node
         */
        public E getElement() {
            return this.element;
        }

        /**
         * Returns the left child node of this node.
         *
         * @return the left child node of this node
         */
        public Node<E> getLeft() {
            return left;
        }

        /**
         * Returns the right child node of this node.
         *
         * @return the right child node of this node
         */
        public Node<E> getRight() {
            return right;
        }

        /**
         * Sets the element of the node.
         * 
         * @param e the new element to be set
         */
        public void setElement(E e) {
            element = e;
        }

        /**
         * Sets the left child of the current node.
         * 
         * @param leftChild the node to be set as the left child
         */
        public void setLeft(Node<E> leftChild) {
            left = leftChild;
        }

        /**
         * Sets the right child of the node.
         * 
         * @param rightChild the node to be set as the right child
         */
        public void setRight(Node<E> rightChild) {
            right = rightChild;
        }
    }

    protected Node<E> root = null;

    /**
     * Binary Search Tree implementation.
     * 
     * @param <E> Generic type parameter for the elements in the tree.
     */
    public BST() {
        root = null;
    }

    /**
     * Returns the root node of the binary search tree.
     *
     * @return the root node of the binary search tree.
     */
    protected Node<E> root() {
        return root;
    }

    /**
     * Checks if the binary search tree is empty.
     *
     * @return true if the binary search tree is empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Finds a node with the given element in the binary search tree rooted at the
     * given node.
     * 
     * @param node    the root node of the binary search tree to search
     * @param element the element to search for
     * @return the node containing the element, or null if the element is not found
     */
    protected Node<E> find(Node<E> node, E element) {
        if (node == null)
            return null;
        if (element.compareTo(node.getElement()) == 0)
            return node;
        if (element.compareTo(node.getElement()) < 0)
            return find(node.getLeft(), element);
        else
            return find(node.getRight(), element);
    }

    /**
     * Inserts an element in the binary search tree.
     *
     * @param element the element to be inserted
     */
    public void insert(E element) {
        root = insert(element, root());
    }

    /**
     * Inserts an element in the binary search tree.
     * If the element already exists, it updates the node's element.
     * 
     * @param element the element to be inserted
     * @param node    the root node of the tree or subtree where the element will be
     *                inserted
     * @return the root node of the updated tree or subtree
     */
    private Node<E> insert(E element, Node<E> node) {
        if (node == null) {
            return new Node<>(element, null, null);
        }
        if (element.compareTo(node.getElement()) == 0) {
            node.setElement(element);
        } else if (element.compareTo(node.getElement()) < 0) {
            node.setLeft(insert(element, node.getLeft()));
        } else {
            node.setRight(insert(element, node.getRight()));
        }
        return node;
    }

    /**
     * Removes the specified element from this BST if it is present.
     *
     * @param element the element to be removed from this BST, if present
     */
    public void remove(E element) {
        root = remove(element, root());
    }

    /**
     * Removes the node with the specified element from the tree rooted at the given
     * node.
     * If the element is not found, returns null.
     *
     * @param element the element to be removed
     * @param node    the root of the tree where the element will be removed
     * @return the root of the tree after the removal
     */
    private Node<E> remove(E element, Node<E> node) {

        if (node == null) {
            return null;
        }
        if (element.compareTo(node.getElement()) < 0) {
            node.setLeft(remove(element, node.getLeft()));
        } else if (element.compareTo(node.getElement()) > 0) {
            node.setRight(remove(element, node.getRight()));
        } else if (node.getLeft() != null && node.getRight() != null) {
            node.setElement(smallestElement(node.getRight()));
            node.setRight(remove(node.getElement(), node.getRight()));
        } else {
            node = (node.getLeft() != null) ? node.getLeft() : node.getRight();
        }
        return node;
    }

    /**
     * Returns the number of elements in the binary search tree.
     *
     * @return the number of elements in the binary search tree
     */
    public int size() {
        return size(root);
    }

    /**
     * Returns the number of nodes in the subtree rooted at the given node.
     *
     * @param node the root of the subtree
     * @return the number of nodes in the subtree rooted at the given node
     */
    private int size(Node<E> node) {
        if (node == null)
            return 0;
        return 1 + size(node.getLeft()) + size(node.getRight());
    }

    /**
     * Returns the height of the binary search tree.
     *
     * @return the height of the binary search tree
     */
    public int height() {
        return height(root);
    }

    /**
     * Returns the height of the subtree rooted at the given node.
     * 
     * @param node the root of the subtree
     * @return the height of the subtree rooted at the given node
     */
    protected int height(Node<E> node) {
        if (node == null)
            return -1;
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    /**
     * Returns the smallest element in the binary search tree.
     *
     * @return the smallest element in the binary search tree
     */
    public E smallestElement() {
        return smallestElement(root);
    }

    /**
     * Returns the smallest element in the binary search tree rooted at the given
     * node.
     *
     * @param node the root node of the binary search tree
     * @return the smallest element in the binary search tree
     * @param <E> the type of elements in the binary search tree
     */
    protected E smallestElement(Node<E> node) {
        if (node == null)
            return null;
        if (node.getLeft() == null)
            return node.getElement();
        return smallestElement(node.getLeft());
    }

    /**
     * Returns the greatest element in the binary search tree.
     *
     * @return the greatest element in the binary search tree.
     */
    public E greatestElement() {
        return greatestElement(root);
    }

    /**
     * Returns the greatest element in the binary search tree rooted at the given
     * node.
     *
     * @param node the root node of the binary search tree
     * @return the greatest element in the binary search tree, or null if the tree
     *         is empty
     */
    public E greatestElement(Node<E> node) {
        if (node == null)
            return null;
        if (node.getRight() == null)
            return node.getElement();
        return greatestElement(node.getRight());
    }

    /**
     * Returns an iterable collection of the elements in the BST in in-order
     * (left-to-right) traversal order.
     *
     * @return an iterable collection of the elements in the BST in in-order
     *         (left-to-right) traversal order.
     */
    public Iterable<E> inOrder() {
        List<E> snapshot = new ArrayList<>();
        if (root != null)
            inOrderSubtree(root, snapshot); // fill the snapshot recursively
        return snapshot;
    }

    /**
     * Traverses the subtree rooted at the given node in in-order fashion and adds
     * the elements to the given snapshot list.
     * 
     * @param node     the root of the subtree to be traversed
     * @param snapshot the list to which the elements of the subtree are added
     */
    private void inOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node == null)
            return;
        inOrderSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getElement());
        inOrderSubtree(node.getRight(), snapshot);
    }

    /**
     * Returns an iterable collection of elements of the tree, reported in
     * pre-order.
     *
     * @return an iterable collection of elements of the tree, reported in
     *         pre-order.
     */
    public Iterable<E> preOrder() {
        List<E> snapshot = new ArrayList<>();
        if (root != null)
            preOrderSubtree(root, snapshot); // fill the snapshot recursively
        return snapshot;
    }

    /**
     * Traverses the subtree rooted at the given node in pre-order and adds the
     * elements to the given list.
     * 
     * @param node     the root of the subtree to be traversed
     * @param snapshot the list to which the elements are added
     */
    private void preOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node == null)
            return;
        snapshot.add(node.getElement());
        preOrderSubtree(node.getLeft(), snapshot);
        preOrderSubtree(node.getRight(), snapshot);
    }

    /**
     * Returns an iterable collection of elements of the binary search tree in
     * post-order.
     * 
     * @return an iterable collection of elements of the binary search tree in
     *         post-order
     */
    public Iterable<E> posOrder() {
        List<E> snapshot = new ArrayList<>();
        if (root != null)
            posOrderSubtree(root, snapshot); // fill the snapshot recursively
        return snapshot;
    }

    /**
     * Traverses the subtree rooted at the given node in postorder and adds the
     * elements to the given list.
     * 
     * @param node     the root of the subtree to be traversed
     * @param snapshot the list to which the elements are added
     */
    private void posOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node == null)
            return;
        posOrderSubtree(node.getLeft(), snapshot);
        posOrderSubtree(node.getRight(), snapshot);
        snapshot.add(node.getElement());
    }

    /**
     * Returns a Map with the nodes of the BST grouped by their level.
     * The keys of the map represent the levels of the BST and the values are lists
     * of nodes that belong to that level.
     *
     * @return a Map with the nodes of the BST grouped by their level.
     */
    public Map<Integer, List<E>> nodesByLevel() {
        Map<Integer, List<E>> result = new HashMap<>();
        processBstByLevel(root, result, 0);
        return result;
    }

    /**
     * Processes the elements of the BST by level and stores them in a Map.
     * 
     * @param node   the root node of the BST
     * @param result the Map where the elements will be stored
     * @param level  the current level of the BST
     */
    private void processBstByLevel(Node<E> node, Map<Integer, List<E>> result, int level) {
        if (node == null)
            return;
        List<E> list = result.get(level);
        if (list == null) {
            list = new ArrayList<>();
            result.put(level, list);
        }
        list.add(node.getElement());
    }

    /**
     * Returns a string representation of the binary search tree.
     *
     * @return a string representation of the binary search tree.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRec(root, 0, sb);
        return sb.toString();
    }

    /**
     * Recursive method that returns a string representation of the binary search
     * tree.
     * 
     * @param root  the root node of the binary search tree
     * @param level the level of the current node in the binary search tree
     * @param sb    the StringBuilder object used to build the string representation
     *              of the binary search tree
     */
    private void toStringRec(Node<E> root, int level, StringBuilder sb) {
        if (root == null)
            return;
        toStringRec(root.getRight(), level + 1, sb);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++)
                sb.append("|\t");
            sb.append("|-------" + root.getElement() + "\n");
        } else
            sb.append(root.getElement() + "\n");
        toStringRec(root.getLeft(), level + 1, sb);
    }

    /**
     * Returns the root node of the binary search tree.
     *
     * @return the root node of the binary search tree
     */
    public Node<E> getRoot() {
        return root;
    }

    /**
     * Returns the number of nodes in the binary search tree.
     *
     * @return the number of nodes in the binary search tree.
     */
    public int countAllNodes() {
        return countAllNodes(root);
    }

    /**
     * Counts all nodes in the binary search tree rooted at the given node.
     *
     * @param node the root node of the binary search tree
     * @return the number of nodes in the binary search tree
     */
    public int countAllNodes(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + countAllNodes(node.getLeft()) + countAllNodes(node.getRight());
    }

    /**
     * Prints the elements of the AVL tree in order.
     * 
     * @param root the root node of the AVL tree
     */
    public void print() {
        printAVL(root);
    }

    /**
     * Prints the elements of the AVL tree in order.
     * 
     * @param node the root node of the AVL tree to be printed
     */
    public void printAVL(Node<E> node) {
        if (node != null) {
            printAVL(node.getLeft()); // Recursively print left subtree
            System.out.println(node.getElement()); // Print this node
            printAVL(node.getRight()); // Recursively print right subtree
        }
    }

}