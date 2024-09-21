package isep.lei.esinf.project2.struct.KDTree;

import java.util.Comparator;

public class Neighbor<E> {
    protected KDTree.KDNode<E> node;
    private double distance;

    public Neighbor(KDTree.KDNode<E> node, double distance) {
        this.node = node;
        this.distance = distance;
    }

    public KDTree.KDNode<E> getNode() {
        return node;
    }

    public double getDistance() {
        return distance;
    }

    public static class NeighborComparator<E> implements Comparator<Neighbor<E>> {
        @Override
        public int compare(Neighbor<E> neighbor1, Neighbor<E> neighbor2) {
            return Double.compare(neighbor1.getDistance(), neighbor2.getDistance());
        }
    }
}




