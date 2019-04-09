package example;


import org.apache.maven.shared.invoker.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Random;

public class Test {

    public static void main(String[] args) throws IOException {


        for (int i = 0; i < 5; i++) {
            TestText testText = new TestText(i, getRandomNumberInts(-10, 10), getRandomNumberInts(-10, 10), getRandomNumberInts(-10, 10));
            BufferedWriter writer = new BufferedWriter(new FileWriter("/home/dogukanku/IdeaProjects/dogusmbomt/src/test/java/example/TriangleTest" + i + ".java"));
            writer.write(testText.execute());
            writer.close();
        }

        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File("/home/dogukanku/IdeaProjects/dogusmbomt/pom.xml"));
        request.setDebug(false);
        request.setGoals(Collections.singletonList("clean verify"));
        Invoker invoker = new DefaultInvoker();
        try {
            invoker.setMavenHome(new File("/opt/maven"));
            invoker.execute(request);
        } catch (MavenInvocationException e) {
            e.printStackTrace();
        }
    }

    public static int getRandomNumberInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, (max + 1)).findFirst().getAsInt();
    }
}
