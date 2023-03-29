package ya_int_0322.perfect_done;

/*К Новому-преновому году работники Тындекса построили N ледяных скульптур, i-я скульптура состоит из ai  килограмм льда.
Но они не посоветовались с Кузей! А ведь Кузя знает, что идеальная скульптура состоит из ровно X  килограмм льда,
не больше и не меньше.
Новый-преновый год уже совсем скоро, до него осталось всего T  минут. За одну минуту Кузя может выбрать одну скульптуру
и добавить или удалить ровно 1  килограмм льда из неё.
Вас, как отличника художественной школы, Кузя просит найти максимальное количество идеальных скульптур в момент наступления праздника.

Формат ввода
В первой строке вводятся три целых числа N,X,T(1≤N≤2⋅10^5;0≤X≤10^9;0≤T≤3⋅10^14) — количество скульптур,
идеальное количество льда в скульптуре и оставшееся количество минут до наступления праздника.
Во второй строке вводятся через пробел N целых чисел ai(1≤ai≤109) — количество килограмм льда в i-й скульптуре.

Формат вывода
В первой строке выведите целое число K(0≤K≤N) — максимально возможное количество идеальных скульптур в момент наступления праздника.
Во второй строке выведите через пробел K различных целых чисел bi(1≤bi≤N) — номера скульптур, которые
будут идеальными в момент наступления Нового-пренового года. Скульптуры нумеруются с 1 в порядке ввода.
Если оптимальных ответов несколько, то выведите любой из оптимальных.

Пример 1
Ввод
3 5 2
5 10 6
Вывод
2
1 3
Пример 2
Ввод
5 19 32
36 10 72 4 50
Вывод
2
2 4
Пример 3
Ввод
4 25 10
1 10 42 9
Вывод
0

Примечания
Пояснение к первому тестовому примеру.
До нового года остаётся2 минуты, а идеальная скульптура должна содержать ровно 5 килограмм льда.

Первая скульптура идеальна сразу, поэтому Кузя не тратит времени на её исправление.
Кузя может сделать идеальной третью скульптуру за |6−5|=1 минуту. После этого у него в запасе останется 2−1=1 минута.
Кузя не сможет сделать идеальной вторую скульптуру, так как на её исправление необходимо |10−5|=5 минут.

Пояснение ко второму тестовому примеру.
До нового года остаётся 32 минуты, а идеальная скульптура должна содержать ровно 19 килограмм льда.
Рассмотрим, сколько требуется времени на «идеализацию» фигур:

|19−36|=17 минут;
|19−10|=9 минут;
|19−72|=53 минуты;
|19−4|=15 минут;
|19−50|=31 минута.
Итого получаются три возможных сценария с двумя идеальными фигурами:

Первая и вторая за 17+9=26 минут;
Первая и четвертая за 17+15=32 минуты - обратите внимание, что в данном сценарии Кузя потратит полностью время, оставшееся до события;
Вторая и четвертая за 9+15=24 минуты.
Хотя Кузя может сделать идеальной пятую фигуру, но на неё одну потребуется почти всё время (31 из 32 минут),
поэтому Кузя не рассматривает такие сценарии.

Пояснение ко третьему тестовому примеру.
До нового года остаётся 10 минут, а идеальная скульптура должна содержать ровно 25 килограмм льда.
Кузя не успеет сделать ни одну из фигур идеальной, так как на каждую из них требуется больше, чем 10 минут:

|1−25|=24>10;
|10−25|=15>10;
|42−25|=17>10;
|9−25|=14>10
.*/

import java.util.*;

public class Backtracking {
    static class Sculpture implements  Comparable<Sculpture>{
        int timeToBecomePerfect;
        int index;

        public Sculpture(int timeToBecomePerfect, int index) {
            this.timeToBecomePerfect = timeToBecomePerfect;
            this.index = index;
        }

        @Override
        public int compareTo(Sculpture o) {
            return this.timeToBecomePerfect == o.timeToBecomePerfect ? this.index - o.index
                    : this.timeToBecomePerfect - o.timeToBecomePerfect;
        }
    }
    public static List<List<Integer>> findSubsets(Sculpture[] sculptures, long target) {
        Arrays.sort(sculptures); // Sort the array in ascending order
        List<List<Integer>> subsets = new ArrayList<>();
        backtrack(sculptures, target, 0, new ArrayList<>(), 0, subsets);
        return subsets;
    }

    private static void backtrack(Sculpture[] sculptures, long target, int index, List<Integer> subset, long sum, List<List<Integer>> subsets) {
        if (sum > target || index == sculptures.length) {
            return;
        }
        for (int i = index; i < sculptures.length; i++) {
            Sculpture sculpture = sculptures[i];
            int time = sculpture.timeToBecomePerfect;
            int ind = sculpture.index;
            subset.add(ind);
            backtrack(sculptures, target, i+1, subset, sum + time, subsets);
            subset.remove(subset.size() - 1);
        }

        if (sum <= target) {
            subsets.add(new ArrayList<>(subset)); // Add the current subset to the list of valid subsets
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();
        long t = scanner.nextLong();
        Sculpture[] a = new Sculpture[n];
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            a[i] = new Sculpture(Math.abs(num - x), i + 1);
        }

        List<List<Integer>> answer = findSubsets(a, t);
        int maxNumbers = answer.stream().mapToInt(List::size).max().getAsInt();
        for (List<Integer> list : answer) {
            if (list.size() == maxNumbers) {
                System.out.println(maxNumbers);
                StringBuilder sb = new StringBuilder();
                for (int num : list) {
                    sb.append(num).append(" ");
                }
                System.out.println(sb.toString().trim());
                break;
            }
        }

        scanner.close();
    }
}
