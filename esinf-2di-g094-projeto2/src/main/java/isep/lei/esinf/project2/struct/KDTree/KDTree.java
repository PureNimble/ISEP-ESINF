package isep.lei.esinf.project2.struct.KDTree;

import isep.lei.esinf.project2.struct.KDTree.Neighbor.NeighborComparator;
import isep.lei.esinf.project2.struct.interfaces.KDTreeInterface;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class KDTree<E extends Comparable<E>> implements KDTreeInterface<E> {

    /**
     * Represents a node in a KDTree.
     * @param <E> the type of element stored in the node
     */
    protected static class KDNode<E> {
        protected Point2D.Double coords;
        protected E element;
        protected KDNode<E> left;
        protected KDNode<E> right;

        /**
         * Represents a node in a KDTree.
         * @param "E" the type of element stored in the node
         */
        public KDNode(E element, KDNode<E> leftChild, KDNode<E> rightChild, double x, double y) {
            this.coords = new Point2D.Double(x, y);
            this.element = element;
            this.left = leftChild;
            this.right = rightChild;
        }

        /**
         * Represents a node in a KDTree.
         * @param "E" the type of element stored in the node.
         */
        public KDNode(E element, double x, double y) {
            this.coords = new Point2D.Double(x, y);
            this.element = element;
            this.left = null;
            this.right = null;
        }

        /**
         * Represents a point in a two-dimensional coordinate system.
         */
        public Point2D.Double getCoords() {
            return coords;
        }

        /**
         * Returns the element of type E stored in the node.
         *
         * @return the element of type E stored in the node
         */
        public E getElement() {
            return element;
        }

        /**
         * Returns the left child node of this KDNode.
         *
         * @return the left child node of this KDNode
         */
        public KDNode<E> getLeft() {
            return left;
        }

        /**
         * Returns the right child node of this KDNode.
         *
         * @return the right child node of this KDNode
         */
        public KDNode<E> getRight() {
            return right;
        }

        /**
         * Sets the coordinates of the current node.
         * 
         * @param newCoords the new coordinates to be set
         */
        public void setCoords(Point2D.Double newCoords) {
            coords = newCoords;
        }

        /**
         * Sets the element of the node.
         * @param newElement the new element to be set
         */
        public void setElement(E newElement) {
            element = newElement;
        }

        /**
         * Sets the left child of this node.
         * 
         * @param leftChild the new left child of this node
         */
        public void setLeft(KDNode<E> leftChild) {
            left = leftChild;
        }

        /**
         * Sets the right child of the current node.
         * 
         * @param rightChild the right child to be set
         */
        public void setRight(KDNode<E> rightChild) {
            right = rightChild;
        }
    }

    protected KDNode<E> root = null;

    /**
     * Returns true if the KDTree is empty.
     *
     * @return true if the KDTree is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Comparator used to compare two KDNodes by their X coordinates.
     */
    private final Comparator<KDNode<E>> cmpX = new Comparator<KDNode<E>>() {
        @Override
        public int compare(KDNode<E> p1, KDNode<E> p2) {
            return Double.compare(p1.coords.getX(), p2.coords.getX());
        }
    };

    /**
     * Comparator for comparing KDNodes by their Y coordinate.
     */
    private final Comparator<KDNode<E>> cmpY = new Comparator<KDNode<E>>() {
        @Override
        public int compare(KDNode<E> p1, KDNode<E> p2) {
            return Double.compare(p1.coords.getY(), p2.coords.getY());
        }
    };

    /**
     * Inserts a new node with the given element and coordinates into the KDTree.
     * 
     * @param element the element to be inserted
     * @param x the x-coordinate of the element
     * @param y the y-coordinate of the element
     */
    @Override
    public void insert(E element, double x, double y) {
        KDNode<E> node = new KDNode<E>(element, x, y);
        root = insert(getRoot(), node, true);
    }

    /**
     * Inserts a new node in the KDTree.
     * 
     * @param currentNode the current node being compared
     * @param node the node to be inserted
     * @param divX a boolean indicating if the division should be made by X or Y coordinates
     * @return the root node of the KDTree
     */
    private KDNode<E> insert(KDNode<E> currentNode, KDNode<E> node, boolean divX) {

        if (currentNode == null) {
            return node;
        }

        if (node.getCoords().equals(currentNode.getCoords())) {
            return currentNode;
        }

        int cmpResult = (divX ? cmpX : cmpY).compare(node, currentNode);

        if (cmpResult == -1) {
            currentNode.setLeft(insert(currentNode.getLeft(), node, !divX));
        } else {
            currentNode.setRight(insert(currentNode.getRight(), node, !divX));
        }

        return currentNode;
    }

    /**
     * Removes a node from the KDTree with the given coordinates.
     * 
     * @param x the x-coordinate of the node to be removed
     * @param y the y-coordinate of the node to be removed
     */
    @Override
    public void remove(double x, double y) {
        Point2D.Double targetCoords = new Point2D.Double(x, y);
        root = remove(root, targetCoords, true);
    }

    /**
     * Removes a node with the given target coordinates from the KDTree.
     * 
     * @param currentNode the current node being evaluated
     * @param targetCoords the coordinates of the node to be removed
     * @param divX        a boolean indicating whether the division should be made by X or Y axis
     * @return the updated KDNode after removal
     */
    private KDNode<E> remove(KDNode<E> currentNode, Point2D.Double targetCoords, boolean divX) {
        if (currentNode == null) {
            return null;
        }

        Point2D.Double nodeCoords = currentNode.getCoords();

        if (nodeCoords.getX() == targetCoords.getX() && nodeCoords.getY() == targetCoords.getY()) {
            if (currentNode.getLeft() == null) {
                return currentNode.getRight();
            } else if (currentNode.getRight() == null) {
                return currentNode.getLeft();
            } else {
                KDNode<E> successor = findMinNode(currentNode.getRight(), divX);
                currentNode.setElement(successor.getElement());
                currentNode.setCoords(successor.getCoords());
                currentNode.setRight(remove(currentNode.getRight(), successor.getCoords(), !divX));
            }
        } else {
            int cmpXResult = Double.compare(targetCoords.getX(), nodeCoords.getX());
            int cmpYResult = Double.compare(targetCoords.getY(), nodeCoords.getY());

            if ((divX && cmpXResult == -1) || (!divX && cmpYResult == -1)) {
                currentNode.setLeft(remove(currentNode.getLeft(), targetCoords, !divX));
            } else {
                currentNode.setRight(remove(currentNode.getRight(), targetCoords, !divX));
            }
        }
        return currentNode;
    }

    /**
     * Finds the node with the minimum value in the tree rooted at the given node.
     * 
     * @param node the root node of the tree to search
     * @param divX a boolean indicating whether to divide by x or y coordinate
     * @return the node with the minimum value in the tree
     */
    private KDNode<E> findMinNode(KDNode<E> node, boolean divX) {
        if (node == null) {
            return null;
        }

        while (node.getLeft() != null) {
            node = node.getLeft();
            divX = !divX;
        }
        return node;
    }

    /**
     * Returns the number of elements in the KDTree.
     *
     * @return the number of elements in the KDTree.
     */
    @Override
    public int size() {
        return size(root);
    }

    /**
     * Returns the number of nodes in the subtree rooted at the specified node.
     *
     * @param node the root of the subtree
     * @return the number of nodes in the subtree rooted at the specified node
     */
    private int size(KDNode<E> node) {
        if (node == null)
            return 0;
        return 1 + size(node.getLeft()) + size(node.getRight());
    }

    /**
     * Returns the element of type E that matches the given coordinates.
     */
    @Override
    public E exactFind(double x, double y) {
        Point2D.Double targetCoords = new Point2D.Double(x, y);
        return exactFind(root, targetCoords, true);
    }

    /**
     * Type parameter for the elements stored in the KDTree.
     */
    private E exactFind(KDNode<E> currentNode, Point2D.Double targetCoords, boolean divX) {
        if (currentNode == null) {
            return null;
        }

        Point2D.Double currentCoords = currentNode.getCoords();

        if (targetCoords.equals(currentCoords)) {
            return currentNode.getElement();
        }

        int cmpResult = (divX ? cmpX : cmpY).compare(new KDNode<>(null, targetCoords.getX(), targetCoords.getY()),
                currentNode);

        if (cmpResult == -1) {
            return exactFind(currentNode.getLeft(), targetCoords, !divX);
        } else {
            return exactFind(currentNode.getRight(), targetCoords, !divX);
        }
    }

    /**
     * Returns a list of elements within the range defined by the minimum and maximum points.
     * 
     * @param minPointX the minimum x-coordinate of the range
     * @param minPointY the minimum y-coordinate of the range
     * @param maxPointX the maximum x-coordinate of the range
     * @param maxPointY the maximum y-coordinate of the range
     * @return a list of elements within the range defined by the minimum and maximum points
     */
    @Override
    public List<E> rangeSearch(double minPointX, double minPointY, double maxPointX, double maxPointY) {

        Point2D.Double minPointCoords = new Point2D.Double(minPointX, minPointY);
        Point2D.Double maxPointCoords = new Point2D.Double(maxPointX, maxPointY);

        List<E> result = new ArrayList<>();
        rangeSearch(root, result, true, minPointCoords, maxPointCoords);
        return result;
    }

    /**
     * Searches for all elements within a given range in the KDTree.
     * 
     * @param node the current node being evaluated
     * @param result the list of elements within the given range
     * @param divX a boolean indicating if the division is being made by the x-axis
     * @param minPoint the minimum point of the range
     * @param maxPoint the maximum point of the range
     */
    private void rangeSearch(KDNode<E> node, List<E> result, boolean divX, Point2D.Double minPoint,
            Point2D.Double maxPoint) {
        if (node == null) {
            return;
        }

        Point2D.Double coords = node.getCoords();

        if (isWithinRange(coords, minPoint, maxPoint)) {
            result.add(node.getElement());
        }

        if (divX) {
            if (coords.getX() >= minPoint.getX()) {
                rangeSearch(node.getLeft(), result, !divX, minPoint, maxPoint);
            }
            if (coords.getX() <= maxPoint.getX()) {
                rangeSearch(node.getRight(), result, !divX, minPoint, maxPoint);
            }
        } else {
            if (coords.getY() >= minPoint.getY()) {
                rangeSearch(node.getLeft(), result, !divX, minPoint, maxPoint);
            }
            if (coords.getY() <= maxPoint.getY()) {
                rangeSearch(node.getRight(), result, !divX, minPoint, maxPoint);
            }
        }
    }

    /**
     * Checks if a given point is within a range defined by a minimum and maximum point.
     * @param point the point to check
     * @param min the minimum point of the range
     * @param max the maximum point of the range
     * @return true if the point is within the range, false otherwise
     */
    private boolean isWithinRange(Point2D.Double point, Point2D.Double min, Point2D.Double max) {
        return point.getX() >= min.getX() && point.getX() <= max.getX()
                && point.getY() >= min.getY() && point.getY() <= max.getY();
    }

    /**
     * Returns the nearest neighbor element to the given coordinates.
     *
     * @param x the x-coordinate of the target point
     * @param y the y-coordinate of the target point
     * @return the nearest neighbor element to the given coordinates
     */
    @Override
    public E findNearestNeighbor(double x, double y) {
        if (root == null)
            return null;
        Point2D.Double targetCoords = new Point2D.Double(x, y);

        KDNode<E> closestNode = findNearestNeighbor(getRoot(), targetCoords, getRoot(), true);
        return closestNode.getElement();
    }

    /**
     * Finds the nearest neighbor to a given point in the KDTree.
     * 
     * @param node the current node being evaluated
     * @param targetCoords the coordinates of the target point
     * @param closestNode the closest node found so far
     * @param divX a boolean indicating whether to divide by x or y coordinate
     * @return the closest node to the target point
     */
    private KDNode<E> findNearestNeighbor(KDNode<E> node, Point2D.Double targetCoords, KDNode<E> closestNode,
            boolean divX) {
        if (node == null)
            return closestNode;

        double distanceSquared = Point2D.distanceSq(node.getCoords().getX(), node.getCoords().getY(),
                targetCoords.getX(), targetCoords.getY());
        double closestDistSquared = Point2D.distanceSq(closestNode.getCoords().getX(), closestNode.getCoords().getY(),
                targetCoords.getX(), targetCoords.getY());

        if (closestDistSquared > distanceSquared) {
            closestNode = node;
        }

        double delta = divX ? targetCoords.getX() - node.getCoords().getX()
                : targetCoords.getY() - node.getCoords().getY();
        double delta2 = delta * delta;

        KDNode<E> node1 = delta < 0 ? node.getLeft() : node.getRight();
        KDNode<E> node2 = delta < 0 ? node.getRight() : node.getLeft();

        closestNode = findNearestNeighbor(node1, targetCoords, closestNode, !divX);

        if (delta2 < closestDistSquared) {
            closestNode = findNearestNeighbor(node2, targetCoords, closestNode, !divX);
        }

        return closestNode;
    }

    /**
     * Finds the k nearest neighbors to a given point in the KDTree.
     *
     * @param x the x-coordinate of the target point
     * @param y the y-coordinate of the target point
     * @param k the number of neighbors to find
     * @return a list of the k nearest neighbors to the target point
     */
    @Override
    public List<E> findKNearestNeighbors(double x, double y, int k) {
        if (root == null)
            return Collections.emptyList();

        Point2D.Double targetCoords = new Point2D.Double(x, y);

        PriorityQueue<Neighbor<E>> nearestNeighbors = new PriorityQueue<>(k, new NeighborComparator<E>());

        findKNearestNeighbors(root, targetCoords, nearestNeighbors, k, true);

        List<E> kNearest = new ArrayList<>(k);
        for (Neighbor<E> neighbor : nearestNeighbors) {
            kNearest.add(neighbor.getNode().getElement());
        }

        return kNearest;
    }

    /**
     * Finds the k nearest neighbors of a given point in the KDTree.
     * 
     * @param node the current node being evaluated
     * @param targetCoords the coordinates of the target point
     * @param nearestNeighbors a priority queue containing the k nearest neighbors found so far
     * @param k the number of nearest neighbors to find
     * @param divX a boolean indicating whether to divide the space by the x-axis or y-axis
     */
    private void findKNearestNeighbors(KDNode<E> node, Point2D.Double targetCoords,
            PriorityQueue<Neighbor<E>> nearestNeighbors, int k, boolean divX) {
        if (node == null)
            return;

        double distance = Point2D.distanceSq(node.getCoords().getX(), node.getCoords().getY(), targetCoords.getX(),
                targetCoords.getY());

        if (nearestNeighbors.size() < k || distance < nearestNeighbors.peek().getDistance()) {
            nearestNeighbors.add(new Neighbor<E>(node, distance));

            if (nearestNeighbors.size() > k) {
                nearestNeighbors.poll();
            }
        }

        double delta = divX ? targetCoords.getX() - node.getCoords().getX()
                : targetCoords.getY() - node.getCoords().getY();

        KDNode<E> nearNode = delta < 0 ? node.getLeft() : node.getRight();
        KDNode<E> farNode = delta < 0 ? node.getRight() : node.getLeft();

        findKNearestNeighbors(nearNode, targetCoords, nearestNeighbors, k, !divX);

        if (nearestNeighbors.size() < k || delta * delta < nearestNeighbors.peek().getDistance()) {
            findKNearestNeighbors(farNode, targetCoords, nearestNeighbors, k, !divX);
        }
    }

    /**
     * Returns an iterable collection of the elements in the tree, in in-order traversal order.
     *
     * @return an iterable collection of the elements in the tree, in in-order traversal order.
     */
    @Override
    public Iterable<E> inOrder() {
        List<E> result = new ArrayList<>();
        inOrderTraversal(root, result);
        return result;
    }

    /**
     * Performs an in-order traversal of the KDTree starting from the given node and adds the elements to the result list.
     * 
     * @param node the starting node for the traversal
     * @param result the list to which the elements are added
     */
    private void inOrderTraversal(KDNode<E> node, List<E> result) {
        if (node == null) {
            return;
        }

        inOrderTraversal(node.getLeft(), result);
        result.add(node.getElement());
        inOrderTraversal(node.getRight(), result);
    }

    /**
     * Returns an iterable collection of the elements in pre-order traversal.
     *
     * @return an iterable collection of the elements in pre-order traversal
     */
    @Override
    public Iterable<E> preOrder() {
        List<E> result = new ArrayList<>();
        preOrderTraversal(root, result);
        return result;
    }

    /**
     * Performs a pre-order traversal of the KDTree starting from the given node and adds the elements to the result list.
     * 
     * @param node the starting node for the traversal
     * @param result the list to which the elements are added
     */
    private void preOrderTraversal(KDNode<E> node, List<E> result) {
        if (node == null) {
            return;
        }

        result.add(node.getElement());
        preOrderTraversal(node.getLeft(), result);
        preOrderTraversal(node.getRight(), result);
    }

    /**
     * Returns an iterable collection of the elements in post-order traversal.
     *
     * @return an iterable collection of the elements in post-order traversal
     */
    @Override
    public Iterable<E> posOrder() {
        List<E> result = new ArrayList<>();
        postOrderTraversal(root, result);
        return result;
    }

    /**
     * Performs a post-order traversal of the KDTree starting from the given node and adds the elements to the result list.
     * @param node the starting node for the traversal
     * @param result the list to which the elements are added
     */
    private void postOrderTraversal(KDNode<E> node, List<E> result) {
        if (node == null) {
            return;
        }

        postOrderTraversal(node.getLeft(), result);
        postOrderTraversal(node.getRight(), result);
        result.add(node.getElement());
    }

    /**
     * Inserts a list of elements into the KDTree by recursively splitting the list in half based on the median value of the x or y coordinates.
     * If there are multiple elements with the same coordinates, only the first one will be inserted.
     * 
     * @param elements the list of elements to be inserted
     * @param xCoords the list of x coordinates of the elements
     * @param yCoords the list of y coordinates of the elements
     */
    @Override
    public void insertByMedian(List<E> elements, List<Double> xCoords, List<Double> yCoords) {
        List<KDNode<E>> nodes = new ArrayList<>();
        Set<Point2D.Double> seenCoordinates = new HashSet<>(); // Maintain a set of seen coordinates

        for (int i = 0; i < elements.size(); i++) {
            Point2D.Double coord = new Point2D.Double(xCoords.get(i), yCoords.get(i));

            if (seenCoordinates.add(coord)) {
                KDNode<E> newNode = new KDNode<E>(elements.get(i), xCoords.get(i), yCoords.get(i));
                nodes.add(newNode);
            }
        }

        root = insertByMedian(nodes, true);
    }
    
    /**
     * Inserts a node in the KDTree by recursively dividing the nodes list by their median value.
     * @param nodes the list of nodes to insert the new node into
     * @param divX a boolean indicating whether to divide by the x-axis or y-axis
     * @return the inserted node
     */
    private KDNode<E> insertByMedian(List<KDNode<E>> nodes, boolean divX) {
        if (nodes.isEmpty()) {
            return null;
        }
    
        nodes.sort(divX ? cmpX : cmpY);
    
        int medianIndex = nodes.size() / 2;
        KDNode<E> medianNode = nodes.get(medianIndex);
    
        medianNode.setLeft(insertByMedian(nodes.subList(0, medianIndex), !divX));
        medianNode.setRight(insertByMedian(nodes.subList(medianIndex + 1, nodes.size()), !divX));
    
        return medianNode;
    }

    /**
     * Returns the height of the KDTree.
     *
     * @return the height of the KDTree.
     */
    @Override
    public int height() {
        return height(root);
    }

    /**
     * Calculates the height of the KDTree from the given node.
     * 
     * @param node the node from which to calculate the height
     * @return the height of the KDTree from the given node
     */
    private int height(KDNode<E> node) {
        if (node == null)
            return -1;
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    /**
     * Returns the total number of nodes in the KDTree.
     *
     * @return the total number of nodes in the KDTree.
     */
    @Override
    public int countAllNodes() {
        return countNodes(root);
    }

    /**
     * Counts the number of nodes in the subtree rooted at the given node.
     *
     * @param node the root of the subtree to count nodes from
     * @return the number of nodes in the subtree rooted at the given node
     */
    private int countNodes(KDNode<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
    }

    /**
     * Returns the root node of the KDTree.
     *
     * @return the root node of the KDTree.
     */
    public KDNode<E> getRoot() {
        return this.root;
    }

    /**
     * Returns the current KDTree.
     *
     * @return the current KDTree.
     */
    public KDTree<E> getKdTree() {
        return this;
    }
}