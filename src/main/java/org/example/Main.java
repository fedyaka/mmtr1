package org.example;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final String PATH_TO_DICTIONARY_LATIN = "target\\classes\\dictionaryLatin.txt";
    private static final String PATH_TO_DICTIONARY_NUMBER = "target\\classes\\dictionaryNumber.txt";

    private DictionaryLatin dictionaryLatin;
    private DictionaryNumber dictionaryNumber;

    {
        try {
            dictionaryLatin = new DictionaryLatin(PATH_TO_DICTIONARY_LATIN);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            dictionaryNumber = new DictionaryNumber(PATH_TO_DICTIONARY_NUMBER);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args)  {

        Main dictionaries = new Main();

        //Приветствие :)
        System.out.println(
                "----------------------------------------------\n" +
                "Здравствуйте! Я очень странное консольное приложение :)\n" +
                "Во мне находится 2 словаря:\n" +
                "1)Словарь только из латинских слов, но слово должно быть ровно 4 символа!\n" +
                "2)Словарь только из чисел, но число должно быть из 5-и цифр!\n" +
                "Поэтому пожалуйста соблюдайте эти скромные правила :)\n" +
                "----------------------------------------------"
        );


        Scanner scan = new Scanner(System.in);
        while (true){
            System.out.println(
                    "----------------------------------------------\n" +
                    "С чего хотите начать?\n" +
                    "1 - просмотр содержимого словарей\n" +
                    "2 - поиск слова по ключу\n" +
                    "3 - добавление пары слов\n" +
                    "4 - удаление слова по ключу\n" +
                    "Введите номер действия:"
            );
            int actionNumber = 0;
            try {
                actionNumber = scan.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Это не номер действия!");
                scan.next();
                continue;
            }

            switch (actionNumber){
                case 1:
                    dictionaries.printDictionaries();
                    break;
                case 2:
                    dictionaries.findByKey(dictionaries.choiceDictionary());
                    break;
                case 3:
                    dictionaries.putWord(dictionaries.choiceDictionary());
                    break;
                case 4:
                    dictionaries.deleteByKey(dictionaries.choiceDictionary());
                    break;
                default:
                    System.out.println("Это не номер действия!");
            }
        }
    }

    public void printDictionaries(){
        System.out.println("Содержимое первого словаря:");
        dictionaryLatin.printToConsole();
        System.out.println();

        System.out.println("Содержимое второго словаря:");
        dictionaryNumber.printToConsole();
        System.out.println();
    }

    //Возвращает словарь выбранный пользователем
    public Dictionary choiceDictionary() {
        Scanner scan = new Scanner(System.in);
        while (true){
            System.out.println(
                    "Какой из словарей вы хотите выбрать?\n" +
                            "1 - латинский словарь\n" +
                            "2 - числовой словарь\n" +
                            "Введите номер словаря:"
            );
            if (scan.hasNextInt()){
                int numberDictionary = scan.nextInt();

                if (numberDictionary == 1){
                    return dictionaryLatin;
                } else if (numberDictionary == 2){
                    return dictionaryNumber;
                } else {
                    System.out.println("Это не номер словаря!!!");
                    continue;
                }
            } else {
                System.out.println("Это не номер словаря!!!");
                scan.next();
                continue;
            }
        }
    }

    public void findByKey(Dictionary dictionary){
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите ключ слово:");
        String key = scan.nextLine();
        try {
            System.out.println("Вот оно: " + dictionary.findByKey(key));
            System.out.println();
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }

    public void putWord(Dictionary dictionary){
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите ключ слово:");
        String key = scan.nextLine();
        System.out.println("Введите значение слова:");
        String value = scan.nextLine();
        try {
            dictionary.putWord(key, value);
            System.out.println("Слово было добавлено! :)");
            System.out.println();
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteByKey(Dictionary dictionary){
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите ключ слово:");
        String key = scan.nextLine();
        try {
            dictionary.deleteByKey(key);
            System.out.println("Слово было удалено! :)");
            System.out.println();
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}