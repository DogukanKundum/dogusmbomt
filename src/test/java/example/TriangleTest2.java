package example;

import static example.TriangleType.EQUILATERAL;
import static example.TriangleType.INVALID;
import static example.TriangleType.ISOSCELES;
import static example.TriangleType.SCALENE;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TriangleTest2 {

    @Test
    public void test1() {
        final TriangleType type = Triangle.classify(5,2,-4);
        assertEquals(INVALID, type);
    }
}