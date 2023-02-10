package ya_training.algo1_0.task7.construction;

/*Над ареной огромного спортивного комплекса Независимого Главного Университета (НГУ) решили построить перекрытие.
Перекрытие будет построено по клеевой технологии и состоять из склеенных друг с другом блоков. Блок представляет собой
легкий прямоугольный параллелепипед. Два блока можно склеить, если они соприкасаются перекрывающимися частями боковых
граней ненулевой площади.
НГУ представил план комплекса, имеющий вид прямоугольника размером W на L. При этом один из углов прямоугольника находится в
начале системы координат, а другой имеет координаты (W, L). Стены комплекса параллельны осям координат.
Подрядчики известили НГУ, что они готовы к определенному сроку изготовить блоки и установить их. Для каждого блока фиксировано
место его возможного монтажа, совпадающее по размерам с этим блоком. Места выбраны так, что ребра блоков параллельны осям координат.
Места монтажа блоков не пересекаются.
По техническим условиям перекрытие должно состоять из такого набора склеенных блоков, который содержит сплошной горизонтальный
слой ненулевой толщины. Торопясь ввести комплекс в эксплуатацию, НГУ решил построить перекрытие из минимально возможного числа блоков.

Требуется написать программу, которая позволяет выбрать минимальное число блоков, которые, будучи установленными на указанных
подрядчиками местах, образуют перекрытие, либо определить, что этого сделать невозможно. Высота, на которой образуется перекрытие,
не имеет значения.

Формат ввода
В первой строке входного файла указаны три целых числа: N — количество возможных блоков (1 ≤ N ≤ 10^5) и размеры комплекса
W и L (1 ≤ W, L ≤ 10^4). Каждая из последующих N строк описывает место монтажа одного блока, определяемое координатами
противоположных углов: (x1, y1, z1) и (x2, y2, z2), при этом 0 ≤ x1 < x2 ≤ W, 0 ≤ y1 < y2 ≤ L, 0 ≤ z1 < z2 ≤ 10^9.
Все числа во входном файле целые и разделяются пробелами или переводами строк.

Гарантируется, что места установки блоков не пересекаются друг с другом.

Формат вывода
Первая строка выходного файла должна содержать либо слово «YES», если перекрытие возможно построить, иначе — слово «NO».
В первом случае вторая строка выходного файла должна содержать минимальное число блоков, образующих перекрытие,
а последующие строки — номера этих блоков, в соответствии с порядком, в котором они перечислены во входном файле.

Если возможно несколько минимальных наборов блоков, выведите любой из них.

Пример 1
Ввод
1 10 10
0 0 0 10 10 10
Вывод
YES
1
1
Пример 2
Ввод
2 10 10
0 0 0 10 5 5
0 5 5 10 10 10
Вывод
NO*/

import java.io.*;
import java.util.*;

public class Main {
    enum Type {
        END(-1),
        BEGIN(2000_000_000);
        private int num;
        Type(int num) {
            this.num = num;
        }
        public int getNum() {
            return num;
        }
    }

    static class Event {
        int z;
        int area;
        Type type;
        int id;
        public Event(int z, Type type, int area, int id) {
            this.z = z;
            this.type = type;
            this.area = area;
            this.id = id;
        }
        @Override
        public String toString() {return "{" + "z=" + z + ", type=" + type + "area=" + area + ", id=" + id + '}';}
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input8.txt")));
        String[] nwl = reader.readLine().split(" ");

        int n = Integer.parseInt(nwl[0]);
        int w = Integer.parseInt(nwl[1]);
        int l = Integer.parseInt(nwl[2]);

        int totalArea = w * l;
        List<Event> events = new ArrayList<>();
        for (int i = 1; i<=n; i++) {
            String[] coord = reader.readLine().split(" ");

            int x1 = Integer.parseInt(coord[0]);
            int y1 = Integer.parseInt(coord[1]);
            int z1 = Integer.parseInt(coord[2]);
            int x2 = Integer.parseInt(coord[3]);
            int y2 = Integer.parseInt(coord[4]);
            int z2 = Integer.parseInt(coord[5]);

            int area = Math.abs(x2 - x1) * Math.abs(y2 - y1);
            // порядок именно такой, чтобы исключить нулевой слой
            events.add(new Event(z1, Type.BEGIN, area, i));
            events.add(new Event(z2, Type.END, area, i));
        }

        Collections.sort(events, (a,b) -> a.z == b.z
                ? (a.type.getNum() == b.type.getNum() ? a.area - b.area : a.type.getNum() - b.type.getNum())
                : a.z - b.z);

        int minUsed = n + 1; // минимальное кол-во использованных блоков
        int nowUsed = 0; // текущее кол-во использованных блоков
        int nowArea = 0; // площадь

        for (Event event : events) {
            if (event.type.equals(Type.BEGIN)) {
                nowUsed++;
                nowArea += event.area;
                if (nowArea == totalArea && nowUsed < minUsed) {
                    minUsed = nowUsed;
                }
            } else {
                nowUsed--;
                nowArea -= event.area;
            }
        }

        if (minUsed == n + 1) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            System.out.println(minUsed);
            // поиск номеров нужных блоков (делаем второй проход, чтобы не появлялась квадратичная сложность)
            // за первый проход найдено минимальное количество блоков
            Set<Integer> usedBlocks = new HashSet<>();
            for (Event event : events) {
                if (event.type.equals(Type.BEGIN)) {
                    nowUsed++;
                    usedBlocks.add(event.id);
                    nowArea += event.area;
                    if (nowArea == totalArea && nowUsed == minUsed) {
                        break; // во множестве минимальное количество блоков покрывающих площадь
                    }
                } else {
                    nowUsed--;
                    usedBlocks.remove(event.id);
                    nowArea -= event.area;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int num : usedBlocks) {
                sb.append(num).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb.toString());
        }

        reader.close();
    }
}
