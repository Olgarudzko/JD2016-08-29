package by.it.sukora.JD1_09.nested;

public class Main {
    public static void main(String[ ] args) {
        // вызов статического метода
        ParentForNested.SubClassNested.downAll();
        // создание объекта статического класса
        ParentForNested.SubClassNested subClassNested = new ParentForNested.SubClassNested();
        // вызов обычного метода
        subClassNested.goOne();
    }
}


