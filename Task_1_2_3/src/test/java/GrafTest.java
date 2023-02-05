import java.util.ArrayList;
import java.util.List;
import org.example.AdjList;
import org.example.Graf;
import org.example.Sort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * GrafTest.
 */

public class GrafTest {

    /**
     * Testing remove vert.
     */

    @Test
    void removeVertTest() {
        Graf<Integer> obj1 = new Graf<>();
        obj1.addVert(1);
        obj1.addVert(2);
        obj1.addVert(3);
        obj1.addVert(4);
        obj1.addVert(5);
        obj1.removeVert(3);
        Graf<Integer> obj = new Graf<>();
        obj.addVert(1);
        obj.addVert(2);
        obj.addVert(4);
        obj.addVert(5);
        AdjList<Integer> ans1 = obj1.adjList();
        AdjList<Integer> ans2 = obj.adjList();
        Assertions.assertEquals(ans1.getData(), ans2.getData());
    }

    /**
     * Testing remove edge.
     */

    @Test
    void removeEdgeTest() {
        Graf<Integer> obj1 = new Graf<>();
        obj1.addVert(1);
        obj1.addVert(2);
        obj1.addVert(3);
        obj1.addEdge(1, 2, 3);
        obj1.addEdge(1, 3, 6);
        obj1.addEdge(2, 3, 1);
        obj1.removeEdge(1, 2);
        Graf<Integer> obj = new Graf<>();
        obj.addVert(1);
        obj.addVert(2);
        obj.addVert(3);
        obj.addEdge(1, 3, 6);
        obj.addEdge(2, 3, 1);
        var ans1 = obj1.adjMatrix();
        var ans2 = obj.adjMatrix();
        Assertions.assertEquals(ans1.getData(), ans2.getData()
        );
    }

    /**
     * Testing getWeightEdge.
     */

    @Test
    void getWeightEdgeTest() {
        Graf<Integer> obj1 = new Graf<>();
        obj1.addVert(1);
        obj1.addVert(2);
        obj1.addVert(3);
        obj1.addEdge(1, 2, 3);
        obj1.addEdge(1, 3, 6);
        Integer weight = obj1.getWeightEdge(1, 3);
        Assertions.assertEquals(weight, 6);
    }

    /**
     * Testing getWeightEdgeForIndex.
     */

    @Test
    void getWeightEdgeForIndexTest() {
        Graf<Integer> obj1 = new Graf<>();
        obj1.addVert(1);
        obj1.addVert(2);
        obj1.addVert(3);
        obj1.addEdge(1, 2, 3);
        obj1.addEdge(1, 3, 6);
        Integer weight = obj1.getWeightEdgeForIndex(0, 1);
        Assertions.assertEquals(weight, 3);
    }

    /**
     * Testing setWeightEdge.
     */

    @Test
    void setWeightEdgeTest() {
        Graf<Integer> obj1 = new Graf<>();
        obj1.addVert(1);
        obj1.addVert(2);
        obj1.addVert(3);
        obj1.addEdge(1, 2, 3);
        obj1.addEdge(1, 3, 6);
        obj1.setWeightEdge(1, 3, 9);
        Graf<Integer> obj = new Graf<>();
        obj.addVert(1);
        obj.addVert(2);
        obj.addVert(3);
        obj.addEdge(1, 2, 3);
        obj.addEdge(1, 3, 9);
        Assertions.assertEquals(obj1.adjMatrix().getData(), obj.adjMatrix().getData());
    }

    /**
     * Testing getAllVert.
     */

    @Test
    void getAllVertTest() {
        Graf<String> obj1 = new Graf<>();
        obj1.addVert("art");
        obj1.addVert("moon");
        obj1.addVert("roof");
        List<String> ans = new ArrayList<>();
        ans.add("roof");
        ans.add("art");
        ans.add("moon");
        List<String> obj = obj1.getAllVert();
        Assertions.assertEquals(obj, ans);
    }

    /**
     * Testing getEdges.
     */

    @Test
    void getEdgesTest() {
        Graf<String> obj1 = new Graf<>();
        obj1.addVert("art");
        obj1.addVert("moon");
        obj1.addVert("roof");
        obj1.addEdge("art", "moon", 7);
        obj1.addEdge("art", "roof", 23);
        obj1.addEdge("moon", "moon", 1);
        obj1.addEdge("roof", "art", 3);
        List<String> obj2 = obj1.getEdges("art");
        List<String> ans = new ArrayList<>();
        ans.add("moon");
        ans.add("roof");
        Assertions.assertEquals(obj2, ans);
        List<String> obj3 = obj1.getEdges("moon");
        List<String> ans2 = new ArrayList<>();
        ans2.add("moon");
        Assertions.assertEquals(obj3, ans2);
    }

    /**
     * Testing setVert.
     */

    @Test
    void setVertTest() {
        Graf<String> obj1 = new Graf<>();
        obj1.addVert("art");
        obj1.addVert("moon");
        obj1.addVert("roof");
        obj1.setVert("roof", "train");
        Graf<String> obj = new Graf<>();
        obj.addVert("art");
        obj.addVert("moon");
        obj.addVert("train");
        Assertions.assertEquals(obj.incMatrix().getData(), obj1.incMatrix().getData());
    }

    /**
     * Testing dijkstra.
     */

    @Test
    void deikstraTest() {
        Graf<Integer> obj = new Graf<>();
        obj.addVert(1);
        obj.addVert(2);
        obj.addVert(3);
        obj.addVert(4);
        obj.addVert(5);
        obj.addVert(6);
        obj.addEdge(1, 2, 7);
        obj.addEdge(1, 3, 9);
        obj.addEdge(1, 6, 14);
        obj.addEdge(2, 3, 10);
        obj.addEdge(2, 4, 15);
        obj.addEdge(3, 6, 2);
        obj.addEdge(3, 4, 11);
        obj.addEdge(4, 5, 6);
        obj.addEdge(5, 6, 9);
        var o = obj.adjList();
        var a = obj.adjMatrix();
        var b = obj.incMatrix();
        Sort<Integer> deik = new Sort<>();
        var ans1 = deik.deikstra(1, o);
        var ans2 = deik.deikstra(1, a);
        var ans3 = deik.deikstra(1, b);
        Assertions.assertArrayEquals(ans1, ans2);
        Assertions.assertArrayEquals(ans2, ans3);
    }

    /**
     * Testing sort.
     */

    @Test
    void sortTest() {
        Graf<Integer> obj = new Graf<>();
        obj.addVert(1);
        obj.addVert(2);
        obj.addVert(3);
        obj.addVert(4);
        obj.addVert(5);
        obj.addVert(6);
        obj.addEdge(1, 2, 1);
        obj.addEdge(1, 3, 21);
        obj.addEdge(1, 6, 14);
        obj.addEdge(2, 3, 32);
        obj.addEdge(2, 4, 15);
        obj.addEdge(3, 6, 33);
        obj.addEdge(3, 4, 11);
        obj.addEdge(4, 5, 8);
        obj.addEdge(5, 6, 9);
        List<Integer> act = new ArrayList<>();
        act.add(1);
        act.add(2);
        act.add(6);
        act.add(4);
        act.add(3);
        act.add(5);
        Sort<Integer> ans = new Sort<Integer>();
        Assertions.assertEquals(act, ans.sortVert(1, obj.adjMatrix()));
    }

}
