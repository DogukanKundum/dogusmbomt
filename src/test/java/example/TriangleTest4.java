package example;

import static example.TriangleType.EQUILATERAL;
import static example.TriangleType.INVALID;
import static example.TriangleType.ISOSCELES;
import static example.TriangleType.SCALENE;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TriangleTest4 {

    @Test
    public void test1() {
        final TriangleType type = Triangle.classify(-2,9,10);
        assertEquals(INVALID, type);
    }
}