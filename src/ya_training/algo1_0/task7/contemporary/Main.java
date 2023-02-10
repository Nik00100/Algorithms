package ya_training.algo1_0.task7.contemporary;

/*Группа людей называется современниками, если был такой момент, когда они могли собраться все вместе и обсуждать какой-нибудь важный
вопрос. Для этого в тот момент, когда они собрались, каждому из них должно было уже исполниться 18 лет, но еще не исполниться 80 лет.
Вам дан список великих людей с датами их жизни. Выведите всевозможные максимальные множества современников. Множество современников
будем называть максимальным, если нет другого множества современников, которое включает в себя всех людей из первого множества.
Будем считать, что в день своего 18-летия человек уже может принимать участие в такого рода собраниях, а в день 80-летия, равно как и
в день своей смерти, — нет.

Формат ввода
Сначала на вход программы поступает число N — количество людей (1 ≤ N ≤ 10000). Далее в N строках вводится по шесть
чисел — первые три задают дату (день, месяц, год) рождения, следующие три — дату смерти (она всегда не ранее даты рождения).
День (в зависимости от месяца, а в феврале — еще и года) от 1 до 28, 29, 30 или 31, месяц — от 1 до 12, год — от 1 до 2005.

Формат вывода
Программа должна вывести все максимальные множества современников. Каждое множество должно быть записано на отдельной строке и
содержать номера людей (люди во входных данных нумеруются в порядке их задания, начиная с 1). Номера людей должны разделяться
пробелами.

Никакое множество не должно быть указано дважды.

Если нет ни одного непустого максимального множества, выведите одно число 0.

Гарантируется, что входные данные будут таковы, что размер выходных данных для правильного ответа не превысит 2 Мб.

Пример 1
Ввод
3
2 5 1988 13 11 2005
1 1 1 1 1 30
1 1 1910 1 1 1990
Вывод
2
3
Пример 2
Ввод
3
2 5 1968 13 11 2005
1 1 1 1 1 30
1 1 1910 1 1 1990
Вывод
2
1 3
Пример 3
Ввод
3
2 5 1988 13 11 2005
1 1 1 1 1 10
2 1 1910 1 1 1928
Вывод
0*/

        // пример как сравнивать даты
        /*int compareTo0(LocalDate otherDate) {
            int cmp = (year - otherDate.year);
            if (cmp == 0) {
                cmp = (month - otherDate.month);
                if (cmp == 0) {
                    cmp = (day - otherDate.day);
                }
            }
            return cmp;
        }*/

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    enum Type {
        START(2000_000_000),
        END(-1);

        private int num;

        Type(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }
    }

    static class Date {
        LocalDate date;
        Type type;
        int id;

        public Date (LocalDate date, Type type, int id) {
            this.date = date;
            this.type = type;
            this.id = id;
        }

        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("Y-M-d");
            return "{" + "date=" + date.format(formatter) + ", t=" + type + ", id=" + id +'}';
        }
    }

    static List<LocalDate> getChronologicalDates (String yearsOfLife) {
        List <LocalDate> dates = new ArrayList<>();
        String[] dmyBorn_dmyD = yearsOfLife.split(" ");

        int dBorn = Integer.parseInt(dmyBorn_dmyD[0]);
        int mBorn = Integer.parseInt(dmyBorn_dmyD[1]);
        int yBorn = Integer.parseInt(dmyBorn_dmyD[2]);

        int dD = Integer.parseInt(dmyBorn_dmyD[3]);
        int mD = Integer.parseInt(dmyBorn_dmyD[4]);
        int yD = Integer.parseInt(dmyBorn_dmyD[5]);

        LocalDate born = LocalDate.of(yBorn, mBorn, dBorn);
        LocalDate d = LocalDate.of(yD, mD, dD);

        LocalDate age18 = born.plusYears(18);
        LocalDate age80 = born.plusYears(80);

        dates.add(born);
        dates.add(age18);
        dates.add(age80);
        dates.add(d);

        return dates;
    }

    static List<LocalDate> getSortedDates (LocalDate age18, LocalDate age80, LocalDate d) {
        List <LocalDate> dates = new ArrayList<>();

        dates.add(age18);
        dates.add(age80);
        dates.add(d);
        Collections.sort(dates);

        if (dates.get(0).equals(d)) return new ArrayList<>();

        return dates;
    }

    // проверка на то, что множество checkedSet содержит элементы, которые не содержатся в FullSet
    static boolean isIntersects (Set<Integer> checkedSet, Set<Integer> FullSet) {
        boolean flag = false;
        int count = 0;
        for (Integer num : checkedSet) {
            if (FullSet.contains(num)) count++;
        }
        flag = count < checkedSet.size();
        return flag;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input8.txt")));

        int n = Integer.parseInt(reader.readLine());

        List<Date> allDates = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            String yearsOfLife = reader.readLine();
            // список дат born, age18, age80, d
            List<LocalDate> dates = getChronologicalDates(yearsOfLife);

            // отсортированный список дат age18, age80, d
            List<LocalDate> sortedDates = getSortedDates(dates.get(1), dates.get(2), dates.get(3));

            // если years of Life более 18 лет, то добавляем даты в потенциально возможные современники
            if (sortedDates.size() != 0) {
                allDates.add(new Date(sortedDates.get(0), Type.START, i));
                allDates.add(new Date(sortedDates.get(1), Type.END, i));
            }

        }

        // сортируем по датам и типу
        Collections.sort(allDates, (a, b) -> a.date.equals(b.date) ? a.type.getNum() - b.type.getNum() : a.date.compareTo(b.date));

        // для подсчета используются два множества (текущее и общее)
        // в текущее множество curr добавляются значения если тип даты START,
        // если тип END и если во множестве curr содержатся элементы, не содержащиеся в full, то выводим результат
        StringBuilder sb = new StringBuilder();
        List<String> answer = new ArrayList<>();
        Set<Integer> curr = new HashSet<>();
        Set<Integer> full = new HashSet<>();
        for (Date date : allDates) {
            if (date.type.equals(Type.START)) {
                curr.add(date.id);
            } else if (date.type.equals(Type.END)) {
                if (!full.contains(date.id) || isIntersects(curr, full)) {
                    for (Integer num : curr) {
                        sb.append(num).append(" ");
                        full.add(num);
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    answer.add(sb.toString());
                    sb.delete(0,sb.length());
                }
                curr.remove(date.id);
            }
        }


        if (answer.size() == 0) {
            System.out.println(0);
        } else {
            for (String str : answer) {
                System.out.println(str);
            }
        }

        reader.close();
    }
}
