package by.it.tsiamruk.jd01_14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by waldemar on 03/10/2016.
 */
public class TaskB {
    static void taskB() {
        //comment
        StringBuilder sb = new StringBuilder();
        int countWords = 0;
        int countPunct = 0;
        //path
        String path = System.getProperty("user.dir");
        path = path.concat("/src/by/it/tsiamruk/");
        String file = path + "jd01_14/data/test.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            Pattern words = Pattern.compile("[aA-zZ]+");
            Matcher wMatch = words.matcher(sb.toString());
            Pattern punctiation = Pattern.compile("[\\.,?!;:]");
            Matcher punctMatch = punctiation.matcher(sb.toString());
            while (wMatch.find()) {
                countWords++;
            }
            while (punctMatch.find()) {
                countPunct++;
            }
            System.out.printf("Кол-во слов: %d \nКол-во знаков препинания: %d\n", countWords, countPunct);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
