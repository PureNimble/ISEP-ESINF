package isep.lei.esinf.project2.struct;

public class AVL<E extends Comparable<E>> extends BST<E> {

    /**
     * Calculates the balance factor of a given node.
     * The balance factor is defined as the difference between the height of the right subtree and the height of the left subtree.
     *
     * @param node the node to calculate the balance factor for
     * @return the balance factor of the given node
     */
    private int balanceFactor(Node<E> node) {
        return (height(node.getRight()) - height(node.getLeft()));
    }

    /**
     * Performs a right rotation on the given node in an AVL tree.
     * @param node the node to perform the right rotation on
     * @return the new root node after the rotation
     */
    private Node<E> rightRotation(Node<E> node) {
        Node<E> left = node.getLeft();
        node.setLeft(left.getRight());
        left.setRight(node);
        return left;
    }

    /**
     * Performs a left rotation on the given node in the AVL tree.
     * @param node the node to perform the left rotation on
     * @return the new root of the subtree after the rotation
     */
    private Node<E> leftRotation(Node<E> node) {
        Node<E> right = node.getRight();
        node.setRight(right.getLeft());
        right.setLeft(node);
        return right;
    }

    /**
     * Performs two rotations on a given node to balance the AVL tree.
     * If the balance factor of the node is less than 0, a left rotation is performed on the left child of the node,
     * followed by a right rotation on the node itself.
     * If the balance factor of the node is greater than or equal to 0, a right rotation is performed on the right child of the node,
     * followed by a left rotation on the node itself.
     * @param node the node to perform the rotations on
     * @return the node after the rotations have been performed
     */
    private Node<E> twoRotations(Node<E> node) {
        if (balanceFactor(node) < 0) {
            node.setLeft(leftRotation(node.getLeft()));
            node = rightRotation(node);
        } else {
            node.setRight(rightRotation(node.getRight()));
            node = leftRotation(node);
        }
        return node;
    }

    /**
     * Balances a node in the AVL tree.
     * @param node the node to be balanced
     * @return the balanced node
     */
    private Node<E> balanceNode(Node<E> node) {
        int BF = balanceFactor(node);
        if (BF > 1 && node.getRight() != null) {
            if (balanceFactor(node.getRight()) < 0) {
                return twoRotations(node);
            } else {
                return leftRotation(node);
            }
        } else if (BF < -1 && node.getLeft() != null) {
            if (balanceFactor(node.getLeft()) > 0) {
                return twoRotations(node);
            } else {
                return rightRotation(node);
            }
        }
        return node;
    }

    /**
     * Inserts an element in the AVL tree.
     *
     * @param element the element to be inserted
     */
    @Override
    public void insert(E element) {
        root = insert(element, root);
    }

    /**
     * Inserts an element in the AVL tree.
     * If the element already exists, updates its value.
     * @param element the element to be inserted
     * @param node the root node of the AVL tree
     * @return the root node of the AVL tree after insertion
     */
    private Node<E> insert(E element, Node<E> node) {
        if (node == null) {
            return new Node<>(element, null, null);
        }
        if (node.getElement() == element) {
            node.setElement(element);
        }
        if (node.getElement().compareTo(element) > 0) {
            node.setLeft(insert(element, node.getLeft()));
            node = balanceNode(node);
        } else {
            node.setRight(insert(element, node.getRight()));
            node = balanceNode(node);
        }
        return node;
    }

    /**
     * Removes the specified element from the AVL tree.
     *
     * @param element the element to be removed from the tree
     */
    @Override
    public void remove(E element) {
        root = remove(element, root());
    }

    /**
     * Removes the node with the specified element from the AVL tree rooted at the given node.
     * If the element is not found, returns null.
     * 
     * @param element the element to be removed
     * @param node the root of the AVL tree
     * @return the root of the AVL tree after the removal
     */
    private Node<E> remove(E element, BST.Node<E> node) {
        if (node == null)
            return null;
        if (node.getElement() == element) {
            if (node.getLeft() == null && node.getRight() == null)
                return null;
            if (node.getLeft() == null) {
                node = node.getRight();
                return node;
            }
            if (node.getRight() == null) {
                node = node.getLeft();
                return node;
            }
            E smallElem = smallestElement(node.getRight());
            node.setElement(smallElem);
            node.setRight(remove(smallElem, node.getRight()));
            node = balanceNode(node);
        } else {
            if (node.getElement().compareTo(element) > 0) {
                node.setLeft(remove(element, node.getLeft()));
            } else {
                node.setRight(remove(element, node.getRight()));
            }
            node = balanceNode(node);
        }
        return node;
    }

    /**
     * Compares this AVL with another AVL for equality.
     * 
     * @param otherObj the other AVL to be compared with this AVL.
     * @return true if the two AVLs are equal, false otherwise.
     */
    public boolean equals(AVL<E> otherObj) {

        if (this == otherObj)
            return true;

        if (otherObj == null || this.getClass() != otherObj.getClass())
            return false;

        AVL<E> second = otherObj;
        return equals(root, second.root);
    }

    /**
     * Compares two AVL trees for equality.
     * Two AVL trees are equal if they have the same structure and the same elements.
     *
     * @param root1 the root of the first AVL tree to be compared
     * @param root2 the root of the second AVL tree to be compared
     * @return true if the two AVL trees are equal, false otherwise
     */
    public boolean equals(Node<E> root1, Node<E> root2) {
        if (root1 == null && root2 == null)
            return true;
        else if (root1 != null && root2 != null) {
            if (root1.getElement().compareTo(root2.getElement()) == 0) {
                return equals(root1.getLeft(), root2.getLeft())
                        && equals(root1.getRight(), root2.getRight());
            } else
                return false;
        } else
            return false;
    }

    /**
     * Returns the AVL tree.
     *
     * @return the AVL tree.
     */
    public AVL<E> getAVL() {
        return this;
    }

    /**
     * Finds the node containing the specified element.
     *
     * @param element the element to be found
     * @return the element found or null if the element is not in the tree
     */
    public E find(E element) {
        Node<E> node = find(root, element);

        return node == null ? null : node.getElement();
    }

    /**
     * Returns true if this AVL tree contains the specified element.
     *
     * @param element the element to be searched for
     * @return true if this AVL tree contains the specified element
     */
    public boolean contains(E element) {
        return find(element) != null;
    }

}
