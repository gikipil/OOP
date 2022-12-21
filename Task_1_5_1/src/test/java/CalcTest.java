import static org.example.Calc.calculate;

import org.example.Calc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Calculate test.
 */
public class CalcTest {

    /**
     * Test with one number.
     */

    @Test
    void one_numberTest() {
        String input = "5";
        Assertions.assertEquals(5, calculate(input));
    }

    /**
     * Test negative value with sqrt and log.
     * Test division by zero.
     */

    @Test
    void exceptionTest() {
        String input = "log -1";
        Assertions.assertThrows(ArithmeticException.class, () -> Calc.calculate(input));
        String input2 = "/ 5 0";
        Assertions.assertThrows(ArithmeticException.class, () -> Calc.calculate(input2));
        String input3 = "sqrt 0";
        Assertions.assertThrows(ArithmeticException.class, () -> Calc.calculate(input3));

    }

    /**
     * Default test for all function.
     */

    @Test
    void combineTest() {
        String input1 = "+ + 1 5 + 2 21";
        Assertions.assertEquals(29, calculate(input1));
        String input2 = "- - 5 4 + 1 2";
        Assertions.assertEquals(-2, calculate(input2));
        String input3 = "* + 2 2 * 4 13";
        Assertions.assertEquals(208, calculate(input3));
        String input4 = "/ / 0 1 / 2 4";
        Assertions.assertEquals(0, calculate(input4));
        String input5 = "pow / 1 3 * 0.4 0.233";
        Assertions.assertEquals(0.9026768382556047, calculate(input5));
        String input6 = "sqrt * 55 pow 2 20";
        Assertions.assertEquals(7594.187250785959, calculate(input6));
        String input7 = "sin sin 45";
        Assertions.assertEquals(0.7518764093672391, calculate(input7));
        String input8 = "cos / 22 7";
        Assertions.assertEquals(-0.999999200533553, calculate(input8));
        String input9 = "log log 5";
        Assertions.assertEquals(0.47588499532711054, calculate(input9));
        String input99 = "* pow log + 1 - 3 1 / 10 5 + sin - 56 23.433 cos sqrt 5";
        Assertions.assertEquals(0.35717124057073574, calculate(input99));
    }
}
