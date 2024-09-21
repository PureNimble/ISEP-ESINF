package isep.lei.esinf.project2.struct.interfaces;
import java.util.List;

public interface KDTreeInterface<E> {
    public boolean isEmpty();
    public void insert(E element, double x, double y);
    public void insertByMedian(List<E> elements, List<Double> xCoords, List<Double> yCoords);
    public void remove(double x, double y);

    public int size();
    public int height();
    public int countAllNodes();

    public E exactFind(double x, double y);
    public Iterable<E> rangeSearch(double minPointX, double minPointY, double maxPointX, double macPointY);
    public E findNearestNeighbor(double x, double y);
    public List<E> findKNearestNeighbors(double x, double y, int k);

    public Iterable<E> inOrder();
    public Iterable<E> preOrder();
    public Iterable<E> posOrder();
}
