import java.util.ArrayList;
import java.util.stream.Stream;
import org.example.Tree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * testing class.
 */

public class TreeTest {

    /**
     * testing addChild.
     */

    @Test
    void addChildTest() {
        Tree<String> object = new Tree<>("1");
        Tree<String> object2 = object.addChild("2");
        Tree<String> object3 = object2.addChild("3");
        String ans = object.getData() + object2.getData() + object3.getData();
        Assertions.assertEquals(ans, "123");
        ArrayList<Tree<String>> child1 = object.getChildren();
        ArrayList<Tree<String>> arr1 = new ArrayList<>();
        arr1.add(object2);
        Assertions.assertEquals(child1, arr1);
        ArrayList<Tree<String>> child2 = object2.getChildren();
        ArrayList<Tree<String>> arr2 = new ArrayList<>();
        arr2.add(object3);
        Assertions.assertEquals(child2, arr2);
        ArrayList<Tree<String>> child3 = object3.getChildren();
        ArrayList<Tree<String>> arr3 = new ArrayList<>();
        Assertions.assertEquals(child3, arr3);

    }

    /**
     * testing deleteChild.
     */

    @Test
    void deleteChildTest() {
        Tree<String> object = new Tree<>("1");
        Tree<String> object2 = object.addChild("2");
        Tree<String> object3 = object2.addChild("3");
        String del = object2.deleteChild();
        Assertions.assertEquals(del, "2");
        ArrayList<Tree<String>> child1 = object.getChildren();
        ArrayList<Tree<String>> arr1 = new ArrayList<>();
        arr1.add(object3);
        Assertions.assertEquals(child1, arr1);
    }

    /**
     * testing iteration DFS.
     */

    @Test
    void dfsTest() {
        Tree<String> object = new Tree<>("1");
        Tree<String> object2 = object.addChild("2");
        object2.addChild("4");
        Tree<String> object3 = object.addChild("3");
        object3.addChild("8");
        object3.addChild("9");
        Tree<String> object5 = object2.addChild("5");
        object5.addChild("10");
        Tree<String> object6 = object2.addChild("6");
        object6.addChild("11");
        object2.addChild("7");



        ArrayList<String> ans = new ArrayList<>();
        for (Tree<String> i : object) {
            ans.add(i.getData());
        }
        ArrayList<String> arr = new ArrayList<>();
        arr.add("1");
        arr.add("3");
        arr.add("9");
        arr.add("8");
        arr.add("2");
        arr.add("7");
        arr.add("6");
        arr.add("11");
        arr.add("5");
        arr.add("10");
        arr.add("4");

        Assertions.assertEquals(ans, arr);

    }

    /**
     * Testing iteration BFS.
     */

    @Test
    void bfsTest() {
        Tree<String> object = new Tree<>("1");
        Tree<String> object2 = object.addChild("2");
        object2.addChild("4");
        Tree<String> object3 = object.addChild("3");
        object3.addChild("8");
        object3.addChild("9");
        Tree<String> object5 = object2.addChild("5");
        object5.addChild("10");
        Tree<String> object6 = object2.addChild("6");
        object6.addChild("11");
        object2.addChild("7");





        object.setTypeIteration(Tree.IteratorTree.BFS);

        ArrayList<String> ans = new ArrayList<>();
        for (Tree<String> i : object) {
            ans.add(i.getData());
        }
        ArrayList<String> arr = new ArrayList<>();
        arr.add("1");
        arr.add("2");
        arr.add("3");
        arr.add("4");
        arr.add("5");
        arr.add("6");
        arr.add("7");
        arr.add("8");
        arr.add("9");
        arr.add("10");
        arr.add("11");

        Assertions.assertEquals(ans, arr);

    }

    /**
     * Stream API.
     */

    @Test
    void streamTest() {
        Tree<String> object = new Tree<>("A");
        object.addChild("AA");
        object.addChild("AB");
        object.addChild("BB");
        object.addChild("BA");
        ArrayList<Tree<String>> child = object.getChildren();
        Stream<Tree<String>> stream = child.stream();
        ArrayList<String> ans = new ArrayList<>();
        stream.filter(x -> x.getData().contains("B")).forEach(x -> ans.add(x.getData()));
        ArrayList<String> arr = new ArrayList<>();
        arr.add("AB");
        arr.add("BB");
        arr.add("BA");
        Assertions.assertEquals(ans, arr);
    }
}
