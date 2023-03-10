package ya_training.algo3_0.task5_01_03.divA.task5;

/*35. Кружки в Маховниках
Ограничение времени	2 секунды
Ограничение памяти	256Mb
Ввод	стандартный ввод или input.txt
Вывод	стандартный вывод или output.txt
Маленький Петя очень любит компьютеры и хочет научиться программировать. В небольшом городке Маховники, где он живёт, работает
сеть кружков по программированию самой разной тематики. Когда Петя пошёл записываться, он увидел большой список, состоящий из
N кружков. Петя хочет быть всесторонне развитой личностью, поэтому он собрался отучиться во всех этих кружках. Но когда он
собрался записаться на все занятия сразу, обнаружилось, что не всё так просто. Во-первых, в один момент времени разрешается
учиться только в одном из этих N кружков. Во-вторых, некоторые преподаватели выдвигают входные требования к знаниям учеников,
заключающиеся в знании курсов каких-то других кружков!

Петя хочет стать великим программистом, поэтому подобные мелочи его не останавливают. Действительно, ему достаточно всего-лишь
составить правильный порядок посещения кружков, чтобы удовлетворить всем входным требованиям — это совсем простая задача, доступная
даже совсем неопытному программисту.

Перед тем как сесть составлять порядок посещения кружков, Петя внимательно перечитал условия обучения и обнаружил ещё один важный
пункт. Оказывается, для привлечения школьников, во всех кружках действует система поощрения учеников конфетами. Это означает,
что по окончании очередного кружка ученику выдают несколько коробок конфет, всё больше и больше с каждым пройденным кружком.
С другой стороны, в каждом кружке количество конфет в коробке своё, зависящее от сложности курса. Более конкретно — за прохождение
i-го по счёту кружка, если этот кружок идёт в общем списке под номером j, ученику выдают аж [N^(i-1)] × j конфет — такие щедрые
люди программисты.

Петя решил совместить полезное с приятным — теперь он хочет выбрать такой порядок посещения кружков, чтобы при этом получить
как можно больше конфет, однако эта задача ему уже не под силу. Помогите будущему великому человеку отыскать такой порядок.

Формат ввода
В первой строке входного файла содержится целое число (N<=100000) — количество кружков в Маховниках.

В последующих N строках идут описания входных требований кружков, в порядке их следования в общем списке.
В i-ой строке сначала записано целое число (ki<=N-1) — количество кружков, в которых нужно отучиться перед записью в i-й кружок,
а потом ki номеров этих кружков.

Сумма ki не превосходит 200000.

Гарантируется, что возможно посетить все эти кружки в некотором порядке, не нарушая условия посещения.

Формат вывода
Выведите N номеров, разделённых пробелами — порядок, в котором Пете надо посещать кружки, чтобы съесть как можно больше конфет.

Пример
Ввод	Вывод
6
1 2
0
1 2
3 1 2 5
1 2
4 1 3 4 5
2 1 3 5 4 6
Примечания
Пояснение к примеру. Посещая кружки в указанном порядке, Петя получит
[6^0] × 2 + [6^1] × 1 + [6^2] × 3 + [6^3] × 5 + [6^4] × 4 + [6^5] × 6 = 2 + 6 + 108 + 1080 + 5184 + 46656 = 53036 конфет.*/

/*Анализ алгоритма

Пусть (p0, p1, p2, …, pn-1) – порядок, в котором Петя будет посещать кружки. Тогда следует максимизировать значение
n0 * p0 + n1 * p1 + n2 * p2 + … + nn-1 * pn-1

Поскольку n в задаче фиксировано, то указанное значение будет максимальным когда последовательность (pn-1, … p2, p1, p0)
будет лексикографически наибольшей. Построим обратный граф. Найдем для него лексикографически наибольшую топологическую сортировку
и выведем ее в обратном порядке. Количество съеденных конфет для построенной последовательности будет максимальным.

Пример
Граф из условия задачи имеет вид:

    ___________
    |          V
    |   4  ->  5 ___
    |   ^           \
    | /    \         V
    2  ->   1   ->   6
      \_____         ^
            V        |
            3 -------
Для обратного графа лексикографически наибольшей топологической сортировкой будет (6, 4, 5, 3, 1, 2). Порядок посещения
кружков будет обратный: (2, 1, 3, 5, 4, 6).*/


import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<List<Integer>> graph = new ArrayList<>();
        List<Integer> InDegree = new ArrayList<>();
        List<Integer> Order = new ArrayList<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            InDegree.add(0);
        }

        for (int i = 1; i <= n; i++) {
            int a = scanner.nextInt();
            for (int j = 0; j < a; j++) {
                int to = scanner.nextInt();
                // Перед записью в i-ый кружок необходимо посетить кружок номер to.
                // В исходном графе имеется ориентированнное ребро (to, i). Однако мы строим обратный граф.
                // Следовательно для каждого ребра (i, to) увеличим InDegree[to] на 1.
                graph.get(i).add(to);
                InDegree.set(to, InDegree.get(to) + 1);
            }
        }

        // Все вершины, входящие степени которых равны нулю, заносим во множество maxHeap.
        for (int i = 1; i < InDegree.size(); i++) {
            if (InDegree.get(i) == 0) {
                maxHeap.offer(i);
            }
        }

        while (!maxHeap.isEmpty()) {
            // Извлекаем вершину v из очереди с наибольшим номером. Заносим ее в топологический порядок Order.
            int v = maxHeap.poll();
            Order.add(v);
            // Удаляем из графа ребра (v, to). Для каждого такого ребра уменьшаем входящую степень вершины to.
            // Если степень вершины to станет нулевой, то заносим ее во множество maxHeap.
            for (int i = 0; i < graph.get(v).size(); i++) {
                int to = graph.get(v).get(i);
                InDegree.set(to, InDegree.get(to) - 1);
                if (InDegree.get(to) == 0) {
                    maxHeap.offer(to);
                }
            }
        }

        // Массив Order содержит наибольшую топологическую сортировку. Выводим ее в обратном порядке.
        for (int i = Order.size() - 1; i >= 0; i--) {
            System.out.print(Order.get(i) + " ");
        }
        System.out.println();
    }

}
