package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Написать программу для подсчета наиболее встречающихся слов в неком тексте произведения Алиса в стране чудес.
 * Файл с текстом прилагается.
 * При выводе резульатов привести первые 10 наиболее встречающихся слова (длинной более 3 букв) с указанием их количества.
 * Пример вывода:
 * <p>
 * алиса: 406
 * сказала: 126
 * было: 105
 * сказал: 100
 * если: 87
 * только: 87
 * очень: 71
 * когда: 64
 * король: 61
 * подумала: 61
 * <p>
 * Подсчет слов не должен учитывать регистр и знаки препинания.
 * Рекомендуется использовать Stream API для решения этого задания
 */
public class App {
    public static void main(String[] args) {

        List<String> fairyTale = new ArrayList<>();
        Object[] arr;

        //очищення тексту від зайвих знаків і приведення до нижнього регістру
        try {
            arr = Files.lines(Path.of("alice.txt"))
                    .map(String::toLowerCase)
                    .map(s -> Arrays.toString(s.split("[^A-Za-zА-Яа-я]")))
                    .map(s -> s.replace("[", ""))
                    .map(s -> s.replace("]", ""))
                    .toArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //прибирання зайвих пробільних символів і відбір слів більше 3 букв
        for (Object s : arr) {
            String str = s.toString();
            String[] spl = str.split(".\\s");
            for (String sp : spl)
                if (sp.length() > 3) {
                    fairyTale.add(sp);
                }
        }

        List<Result> result = new ArrayList<>();
        //підрахунок кількості слів у тексті
        while (fairyTale.size() > 0) {
            String desired = fairyTale.get(0);
            long a = fairyTale.stream()
                    .filter(desired::equals)
                    .count();
            result.add(new Result(a, desired));
            for (int i = 0; i < a; i++) {
                fairyTale.remove(desired);
            }
        }

        Collections.sort(result);
        for (int i = 0; i < 10; i++) {
            System.out.println(result.get(i));
        }
    }
}