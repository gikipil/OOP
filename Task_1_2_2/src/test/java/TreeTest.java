import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.example.Bfs;
import org.example.Dfs;
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
        List<Tree<String>> child1 = object.getChildren();
        List<Tree<String>> arr1 = new ArrayList<>();
        arr1.add(object2);
        Assertions.assertEquals(child1, arr1);
        List<Tree<String>> child2 = object2.getChildren();
        List<Tree<String>> arr2 = new ArrayList<>();
        arr2.add(object3);
        Assertions.assertEquals(child2, arr2);
        List<Tree<String>> child3 = object3.getChildren();
        List<Tree<String>> arr3 = new ArrayList<>();
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
        List<Tree<String>> child1 = object.getChildren();
        List<Tree<String>> arr1 = new ArrayList<>();
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



        List<String> ans = new ArrayList<>();
        for (Tree<String> i : object) {
            ans.add(i.getData());
        }
        List<String> arr = new ArrayList<>();
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

        List<String> ans = new ArrayList<>();
        for (Tree<String> i : object) {
            ans.add(i.getData());
        }
        List<String> arr = new ArrayList<>();
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
        List<Tree<String>> child = object.getChildren();
        Stream<Tree<String>> stream = child.stream();
        List<String> ans = new ArrayList<>();
        stream.filter(x -> x.getData().contains("B")).forEach(x -> ans.add(x.getData()));
        List<String> arr = new ArrayList<>();
        arr.add("AB");
        arr.add("BB");
        arr.add("BA");
        Assertions.assertEquals(ans, arr);
    }

    /**
     * iter remove.
     */

    @Test
    void iterDfsRemoveTest() {
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


        Dfs<String> j = (Dfs<String>) object.iterator();
        while (j.hasNext()) {
            String t = j.next().getData();
            if (t.equals("9")) {
                j.remove();
            }
        }

        Tree<String> ans = new Tree<>("1");
        Tree<String> ans2 = ans.addChild("2");
        ans2.addChild("4");
        Tree<String> ans3 = ans.addChild("3");
        ans3.addChild("8");
        Tree<String> rans = ans3.addChild("9");
        Tree<String> ans5 = ans2.addChild("5");
        rans.deleteChild();
        ans5.addChild("10");
        Tree<String> ans6 = ans2.addChild("6");
        ans6.addChild("11");
        ans2.addChild("7");

        List<String> arr1 = new ArrayList<>();
        for (Tree<String> i : object) {
            arr1.add(i.getData());
        }
        List<String> arr2 = new ArrayList<>();
        for (Tree<String> i : object) {
            arr2.add(i.getData());
        }



        Assertions.assertEquals(arr1, arr2);
    }

    /**
     * iter remove.
     */

    @Test
    void iterBfsRemoveTest() {
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

        Bfs<String> j = (Bfs<String>) object.iterator();
        while (j.hasNext()) {
            String t = j.next().getData();
            if (t.equals("9")) {
                j.remove();
            }
        }

        Tree<String> ans = new Tree<>("1");
        Tree<String> ans2 = ans.addChild("2");
        ans2.addChild("4");
        Tree<String> ans3 = ans.addChild("3");
        ans3.addChild("8");
        Tree<String> rans = ans3.addChild("9");
        Tree<String> ans5 = ans2.addChild("5");
        rans.deleteChild();
        ans5.addChild("10");
        Tree<String> ans6 = ans2.addChild("6");
        ans6.addChild("11");
        ans2.addChild("7");

        List<String> arr1 = new ArrayList<>();
        for (Tree<String> i : object) {
            arr1.add(i.getData());
        }
        List<String> arr2 = new ArrayList<>();
        for (Tree<String> i : object) {
            arr2.add(i.getData());
        }



        Assertions.assertEquals(arr1, arr2);
    }
}
