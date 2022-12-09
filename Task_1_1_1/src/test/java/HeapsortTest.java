import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;
import org.example.Heapsort;
import org.junit.jupiter.api.Test;



/**
 * Класс тестирования сортировки кучей.
 */
public class HeapsortTest {

    /**
     * Тестирование списка с повторяющимися элементами.
     */

    @Test
    void repeatedElementsArrayTest() {
        int[] arr = {56, 3, 4, 3, 1, 3};
        int[] ans = {1, 3, 3, 3, 4, 56};
        System.out.println("Создан массив: 56,3,4,3,1,3");
        assertArrayEquals(Heapsort.sort(arr), ans);
        System.out.println("Массив успешно отсортирован!");
    }

    /**
     * Тестирование пустого списка.
     */

    @Test
    void emptyArrayTest() {
        int[] arr = {};
        int[] ans = {};
        System.out.println("Создан пустой массив");
        assertArrayEquals(Heapsort.sort(arr), ans);
        System.out.println("Массив успешно отсортирован!");
    }

    /**
     * Тестирование случайного списка.
     */

    @Test
    void randomArrayTest() {
        Random generator = new Random();
        int len = generator.nextInt(1000);
        int [] arr = new int[len];
        int [] ans = new int[len];
        for (int i = 0; i < len; i++) {
            int val = generator.nextInt();
            arr[i] = val;
            ans[i] = val;
        }
        Arrays.sort(ans);
        System.out.println("Создан случайный массив");
        assertArrayEquals(Heapsort.sort(arr), ans);
        System.out.println("Массив успешно отсортирован!");
    }

    /**
     * Тестирование уже отсортированого списка.
     */

    @Test
    void sortedArrayTest() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] ans = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println("Создан массив: 1,2,3,4,5,6,7,8");
        assertArrayEquals(Heapsort.sort(arr), ans);
        System.out.println("Массив успешно отсортирован!");
    }

    /**
     * Тестировани списка с отрицательными значениями.
     */

    @Test
    void negativeArrayTest() {
        int [] arr = {0, -1, -2, -3, -4, -5};
        int [] ans = {-5, -4, -3, -2, -1, 0};
        System.out.println("Создан массив: 0,-1,-2,-3,-4,-5");
        assertArrayEquals(Heapsort.sort(arr), ans);
        System.out.println("Массив успешно отсортирован!");
    }
}
