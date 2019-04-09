package example;

public class TestText {

    int k, a, b, c;
    TriangleType triangleType;

    TestText(int k, int a, int b, int c) {
        this.k = k;
        this.a = a;
        this.b = b;
        this.c = c;

        triangleType = Triangle.classify(a, b, c);
    }


    private String generateTestScenario() {
        return "package example;\n" +
                "\n" +
                "import static example.TriangleType.EQUILATERAL;\n" +
                "import static example.TriangleType.INVALID;\n" +
                "import static example.TriangleType.ISOSCELES;\n" +
                "import static example.TriangleType.SCALENE;\n" +
                "import static org.junit.Assert.assertEquals;\n" +
                "\n" +
                "import org.junit.Test;\n" +
                "\n" +
                "public class TriangleTest" + k + " {\n" +
                "\n" +
                "    @Test\n" +
                "    public void test" + 1 + "() {\n" +
                "        final TriangleType type = Triangle.classify(" + a + "," + b + "," + c + ");\n" +
                "        assertEquals(" + triangleType + ", type);\n" +
                "    }\n" +
                "}";
    }

    public String execute() {
        return generateTestScenario();
    }
}
