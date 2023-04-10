package ya_int_0322.kosmopolitizm;

/*Кузя был очень удивлён, когда на десятилетие выпуска не приехала большая часть его одноклассников.
«Чему тут удивляться, разъехались по миру», — сказал ему один из пришедших одноклассников.
Кузя решил, что раз одноклассники не приехали на выпускной, то Кузя сам привезёт выпускной им. Правда для этого Кузе
необходимо узнать, в какие страны они переехали.
Кузя знает, что каждая страна разрешает переезд на основе двух характеристик:
- Минимальный доход, необходимый для проживания;
- Обязательное наличие высшего образования.

Также Кузя знает, что некоторые страны разрешают переезд непосредственным детям своих граждан без необходимости удовлетворять
описанным выше характеристикам. Про каждого из уехавших одноклассников Кузя выяснил следующую информацию (мы сами не понимаем, где он всё это узнал):
- Величина дохода;
- Наличие высшего образования;
- Гражданство родителей.

Теперь Кузя просит вас, как лучшего игрока в GeoGuesser среди выпускников, выяснить страну, в которую переехал каждый из
непришедших на выпускной одноклассников.

Формат ввода
Первая строка содержит одно целое число N(1≤N≤2⋅10^5) — количество стран, рассматриваемых для переезда.
Вторая строка содержит N целых чисел через пробел ai(0≤ai≤10^9) — минимальный доход, необходимый для переезда в i-ю страну.
Третья строка содержит N целых чисел через пробел bi(0≤bi≤1) — bi равно 1, если для переезда в i-ю страну обязательно
наличие высшего образования.
Четвертая строка содержит N целых чисел через пробел ci(0≤ci≤1) — ci равно 1, если непосредственные дети граждан i-й страны
могут переехать в i-ю страну,
не удовлетворяя условиям на доход и высшее образование. Страны нумеруются с 1 по N в порядке ввода.
Пятая строка содержит одно целое число Q(1≤Q≤2⋅10^5) — количество одноклассников, не пришедших на выпускной.
Шестая строка содержит Q целых чисел через пробел xj(0≤xj≤10^9) — доход j-го одноклассника.
Седьмая строка содержит Q целых чисел через пробел yj(0≤yj≤1) — yj равно 1, если у j-го одноклассника есть высшее образование.
Восьмая строка содержит Q целых чисел через пробел zj(0≤zj≤N) — гражданство родителей j-го одноклассника,
заданное номером страны или 0, если их гражданство неизвестно. Одноклассники нумеруются с 1 по Q в порядке ввода.

Формат вывода
В единственной строке выведите Q целых чисел через пробел tj(0≤tj≤N) — номер страны, которую выбрал для переезда j-й одноклассник.
Каждый одноклассник может выбрать только страну, в которую он имеет право переехать (согласно условиям).
В случае, если одноклассник может выбрать несколько стран для переезда, он выберет страну с наименьшим номером в списке
(Кузя любезно отсортировал страны в порядке убывания привлекательности для переезда).
Если одноклассник не мог переехать ни в одну из описанных стран, выведите 0.

Пример
Ввод
2
10 9
1 0
0 1
5
0 0 11 10 9
0 1 0 1 1
2 1 0 0 0

Вывод
2 0 2 1 2

Примечания
Пояснение к тестовому примеру.
В примере две страны и пять одноклассников.

Первая страна имеет ограничение по зарплате 10 и требует наличия высшего образования. Также в данной стране не разрешён переезд
по воссоединению с семьёй.
Вторая страна имеет ограничение по зарплате 9 и не требует наличия высшего образования. Также данная страна разрешает переезд
по воссоединению с семьёй.
Первый одноклассник не имеет работы и высшего образования, но его родители живут в стране 2, куда можно переехать по воссоединению с семьёй.
Второй одноклассник имеет высшее образование, но пока не нашел работы с доходом. Кроме того, его родители живут в стране
1, которая не дает права на воссоединение с семьей. Поэтому бедняга вообще не смог никуда уехать и просто решил не идти на выпускной.
Третий одноклассник удовлетворяет условиям для обеих стран по зарплате (11≥10 и 11≥9), но не озаботился получить диплом,
поэтому смог переехать только во вторую, менее привлекательную страну.
Четвертый одноклассник удовлетворяет условиям для обеих стран (достаточная зарплата 10≥10 и 10≥9, а также высшее образование).
Из двух стран он выбрал первую, как более привлекательную (то есть имеющую меньший номер во вводе).
Пятый одноклассник имеет высшее образование, но его зарплаты не хватает на переезд в первую страну (9<10),
поэтому он переезжает во вторую страну, в которую его зарплаты достаточно (9≥9).
*/

import java.util.*;
import java.util.stream.Collectors;

public class BinSearch {
    private static int MAX_REQUIRED_INCOME = 1000_000_000;

    static class Country {
        int id, income, education, children;

        public Country(int id, int income, int education, int children) {
            this.id = id;
            this.income = income;
            this.education = education;
            this.children = children;
        }

        public int getId() {
            return id;
        }

        public int getIncome() {
            return income;
        }

        public int getEducation() {
            return education;
        }

        public int getChildren() {
            return children;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] income = new int[n];
        int[] education = new int[n];
        int[] children = new int[n];
        for (int i = 0; i < n; i++) {
            income[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            education[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            children[i] = scanner.nextInt();
        }

        Country[] countries = new Country[n];
        for (int i = 0; i < n; i++) {
            countries[i] = new Country(i + 1, income[i], education[i], children[i]);
        }

        Map<Integer, Integer> mapIncomeIdNotNeedHigherEd = new HashMap<>();
        List<Country> countriesSortedByIncomeIdNotNeedHigherEd = Arrays.stream(countries)
                .filter(country -> country.getEducation() == 0)
                .sorted(Comparator.comparing(Country::getIncome).thenComparing(Country::getId))
                .collect(Collectors.toList());
        fillMinIdForEachIncome(countriesSortedByIncomeIdNotNeedHigherEd, mapIncomeIdNotNeedHigherEd);

        Map<Integer, Integer> mapIncomeId = new HashMap<>();
        List<Country> countriesSortedByIncomeId = Arrays.stream(countries)
                .sorted(Comparator.comparing(Country::getIncome).thenComparing(Country::getId))
                .collect(Collectors.toList());
        fillMinIdForEachIncome(countriesSortedByIncomeId, mapIncomeId);

        int q = scanner.nextInt();
        int[] x = new int[q];
        int[] y = new int[q];
        int[] z = new int[q];
        int[] result = new int[q];

        for (int i = 0; i < q; i++) {
            x[i] = scanner.nextInt();
        }
        for (int i = 0; i < q; i++) {
            y[i] = scanner.nextInt();
        }
        for (int i = 0; i < q; i++) {
            z[i] = scanner.nextInt();
        }

        for (int i = 0; i < q; i++) {
            int xIncome = x[i];
            int yEducation = y[i];
            int zParentCountry = z[i] - 1;

            int parentCountryId = Integer.MAX_VALUE;
            if (zParentCountry > 0 && countries[zParentCountry].children == 1) {
                parentCountryId= countries[zParentCountry].id;
            }

            int countryId = Integer.MAX_VALUE;
            int countryIncome;
            if (yEducation == 0) {
                countryIncome = binSearch(countriesSortedByIncomeIdNotNeedHigherEd, xIncome);
                countryId = mapIncomeIdNotNeedHigherEd.getOrDefault(countryIncome, Integer.MAX_VALUE);
            } else {
                countryIncome = binSearch(countriesSortedByIncomeId, xIncome);
                countryId = mapIncomeId.getOrDefault(countryIncome, Integer.MAX_VALUE);
            }

            //System.out.println(rightBorder);

            countryId = Math.min(countryId, parentCountryId);

            result[i] = countryId == Integer.MAX_VALUE ? 0 : countryId;
        }

        for (int i = 0; i < q; i++) {
            System.out.println(result[i]);
        }
    }

    static int binSearch (List<Country> countriesSortedByIncomeId, int xIncome) {
        int l = 0;
        int r = countriesSortedByIncomeId.size() - 1;
        while (l < r) {
           int m = (r + l) / 2;
           if (xIncome <= countriesSortedByIncomeId.get(m).getIncome()) {
               r = m;
           } else {
               l = m + 1;
           }
        }
        return countriesSortedByIncomeId.get(l).getIncome() <= xIncome
                ? countriesSortedByIncomeId.get(l).getIncome()
                : Integer.MAX_VALUE;
    }

    static void fillMinIdForEachIncome(List<Country> countriesSortedByIncomeId, Map<Integer, Integer> mapForIncomeId) {
        int prevCountryIncome = Integer.MAX_VALUE;
        for (Country country : countriesSortedByIncomeId) {
            if (prevCountryIncome != country.getIncome()) {
                int prevCountryId = mapForIncomeId.getOrDefault(prevCountryIncome, Integer.MAX_VALUE);
                int id = Math.min(prevCountryId, country.getId());
                mapForIncomeId.put(country.getIncome(), id);
            }
        }
    }
}
