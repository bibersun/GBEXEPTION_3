import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
//        String input = "asasdda Иванов Сергей Александрович 16.10.1967 16.10.1967 Djkklklj f 12345678901 asdda";
        String input = "asasdda Иванов Сергей Александрович 16.10.1967 Djkklklj f 12345678901 asdda";
//        String input = "";
        //закомментировать строку выше и раскомментировать строки ниже если хотим вводить в консоли
//        Scanner console = new Scanner(System.in);
//        System.out.println("");
//        System.out.print("Введите данные: ");
//        String input = console.nextLine();
        Person person = new Person();
        try {
//            if (input.length() > 0) {
                person.getStringToPerson(input);
                //person.getFamilyName(true) - правильное имя файла, person.getFamilyName(false) - неправильное имя файла для
                // проверки реагирования на ошибку
                try (FileWriter fileWriter = new FileWriter(person.getFamilyName(true), true)) {
                    fileWriter.write(person.getAll());
                    System.out.println(person + "\nЗаписано/добавлено в файл с именем " + person.getFamilyName(true));
                } catch (IOException e) {
                    System.out.println("Ошибка ввода-вывода");
                    //к сожалению, не знаю - как писать стектрейс, поэтому взял со stackoverflow
                    System.out.println(Arrays.toString(Thread.currentThread().getStackTrace()).replace(",", "\n").trim());
                }
//            }
        } catch (InvalidInputExeption e) {
            System.out.println(e.getMessage());
        }


    }
}
