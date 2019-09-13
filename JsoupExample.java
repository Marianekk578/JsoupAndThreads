package APp.otomoto;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JsoupExample {


    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 4; i++) {
            executorService.submit(() -> showSkarpetki());
        }
        executorService.shutdown();

        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }


    public static void showSkarpetki() {

        Document doc;
        String url = "https://www.soxinthebox.pl/";

        try {
            doc = Jsoup.connect(url).get();
            Elements img = doc.getElementsByTag("img");
            for (Element element : img) {
                String src = element.absUrl("src");
                if (src.contains("skarpetki"))
                    System.out.println(src);
                else
                    System.out.println("there are no skarpetki in here :(");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
