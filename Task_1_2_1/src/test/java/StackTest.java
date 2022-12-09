import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import org.example.Stack;
import org.junit.jupiter.api.Test;

/**
 * Testing class.
 */

public class StackTest {

    /**
     * Testing basic functions.
     */

    @Test
    void functionTest() {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(2);
        stack.push(4);
        stack.push(7);
        stack.push(1);
        stack.push(4);
        stack.push(2);
        stack.pushStack(new Integer []{4, 6, 3, 1, 98});
        stack.pushStack(new Integer []{323, 11, 123, 444});
        stack.pop();
        stack.pop();
        stack.pop();
        stack.popStack(1);
        stack.popStack(3);
        assertArrayEquals(stack.show(), new Integer[]{2, 4, 7, 1, 4, 2, 4, 6});
        assertEquals(stack.count(),8);
    }

    /**
     * Testing an empty stack.
     */

    @Test
    void emptyTest() {
        Stack<Integer> stack = new Stack<Integer>();
        stack.pop();
        stack.pop();
        stack.push(3);
        stack.pop();
        assertArrayEquals(stack.show(), new Object[0]);
    }

    /**
     * Testing multiple stacks.
     */

    @Test
    void multipleTest() {
        Stack<Integer> stack1 = new Stack<Integer>();
        stack1.push(1);
        stack1.push(67);
        Stack<String> stack2 = new Stack<String>();
        stack2.push("dsa");
        Stack<Boolean> stack3 = new Stack<Boolean>();
        stack3.push(true);
        stack2.push("tea");
        stack1.push(54);
        stack3.push(false);
        stack1.push(3);
        stack2.push("car");
        stack2.push("bed");
        stack3.push(false);
        assertArrayEquals(stack1.show(), new Integer [] {1, 67, 54, 3});
        assertArrayEquals(stack2.show(), new String [] {"dsa", "tea", "car", "bed"});
        assertArrayEquals(stack3.show(), new Boolean[] {true, false, false});
    }
    /**
     * Testing random stacks.
     */

    @Test
    void randomTest() {
        Random generator = new Random();
        int len = generator.nextInt(1000);
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> ans = new Stack<Integer>();
        for (int i = 0; i < len; i++) {
            Integer val = generator.nextInt();
            stack.push(val);
            ans.push(val);
        }
        len = generator.nextInt(1000);
        for (int i = 0; i < len; i++) {
            stack.pop();
            ans.pop();
        }
        assertArrayEquals(stack.show(), ans.show());
    }
}
