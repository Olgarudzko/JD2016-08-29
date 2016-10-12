package by.it.sukora.jd02_05.HomeWork;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Runner implements Messages {

    public static void main(String[] args) {
        if (args.length == 2) {
            Locale locale = new Locale(args[0], args[1]);
            LanguageManager.setLocale(locale);
        }
        System.out.println(LanguageManager.getString(MESSAGE_WELCOME));
        System.out.println(LanguageManager.getString(MESSAGE_USERNAME));
        System.out.println(LanguageManager.getString(MESSAGE_QUESTION));

        Date date = new Date(System.currentTimeMillis());
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, LanguageManager.getLocale());
        String strDate = df.format(date);
        System.out.println(strDate);

        Scanner scanner = new Scanner(System.in);
        String line;

//        while ((line = scanner.nextLine()) != null) {
//            switch (line){
//                case "ru":
//                    Locale locale = new Locale(args[0], args[1]);
//                    LanguageManager.setLocale(locale);
//                    break;
//            if (line.toLowerCase().equals("exit"))
//                System.exit(0);
//        }
//    }

    }
}
