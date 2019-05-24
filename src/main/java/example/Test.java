package example;


import org.apache.maven.shared.invoker.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.Collections;
import java.util.Random;

public class Test {

    public static void main(String[] args) throws IOException {
        for (int N = 0; N < 3; N++) {
            for (int i = 0; i < 5; i++) {
                TestText testText = new TestText(i, getRandomNumberInts(0, 20), getRandomNumberInts(0, 20), getRandomNumberInts(0, 20));
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

            File root = find("/home/dogukanku/IdeaProjects/dogusmbomt/target/pit-reports/", "index.html");
            Document doc = Jsoup.parse(root, "UTF-8");
            System.out.println("Line Coverage : " + calcTestClassesByLineCoverage(doc));
            System.out.println("Mutation Coverage : " + calcTestClassesByMutationCoverage(doc));
        }
    }

    private static File find(String path, String fName) {
        File f = new File(path);
        if (fName.equalsIgnoreCase(f.getName())) return f;
        if (f.isDirectory()) {
            for (String aChild : f.list()) {
                File ff = find(path + File.separator + aChild, fName);
                if (ff != null) return ff;
            }
        }
        return null;
    }

    private static double calcTestClassesByLineCoverage(Document doc) {
        String[] r = Jsoup.parse(doc.select("tbody")
                .first().after("td").toString()).select("div").first().getElementsByClass("coverage_legend").get(0).childNodes().get(0).toString()
                .replaceAll(" ", "")
                .replaceAll("\n", "").split("/");
        return Double.parseDouble(r[0]) / Double.parseDouble(r[1]);
    }

    private static double calcTestClassesByMutationCoverage(Document doc) {
        String[] r = Jsoup.parse(doc.select("tbody")
                .first().after("td").toString()).select("div").last().getElementsByClass("coverage_legend").get(0).childNodes().get(0).toString()
                .replaceAll(" ", "")
                .replaceAll("\n", "").split("/");
        return Double.parseDouble(r[0]) / Double.parseDouble(r[1]);
    }

    public static int getRandomNumberInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, (max + 1)).findFirst().getAsInt();
    }
}
