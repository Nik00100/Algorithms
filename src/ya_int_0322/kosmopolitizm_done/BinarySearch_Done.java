package ya_int_0322.kosmopolitizm_done;

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
поэтому он переезжает во вторую страну, в которую его зарплаты достаточно (9≥9).*/

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class BinarySearch_Done {
    private final static int MAX_VALUE = 2_000_000_000;
    static class Country {
        int minIncome, id;
        boolean  edRequired, childrenAllowed;
        public Country(int id, int minIncome, boolean edRequired, boolean childrenAllowed) {
            this.id = id;
            this.minIncome = minIncome;
            this.edRequired = edRequired;
            this.childrenAllowed = childrenAllowed;
        }
        public int getMinIncome() {return minIncome;}
        public int getId() {return id;}
    }

    static int binSearch (List<Country> countriesSortedByIncomeId, int xIncome) {
        if (countriesSortedByIncomeId.size() == 0) {return MAX_VALUE;}
        int l = 0;
        int r = countriesSortedByIncomeId.size() - 1;
        while (l < r) {
            int m = (r + l) / 2;
            if (xIncome <= countriesSortedByIncomeId.get(m).getMinIncome()) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return getIncome(countriesSortedByIncomeId, xIncome, r);
    }

    static int getIncome(List<Country> countriesSortedByIncomeId, int xIncome, int index) {
        if (index == 0) {
            if (countriesSortedByIncomeId.get(0).getMinIncome() <= xIncome) {
                return countriesSortedByIncomeId.get(0).getMinIncome();
            } else {
                return MAX_VALUE;
            }
        } else if (countriesSortedByIncomeId.get(index).getMinIncome() <= xIncome) {
            return countriesSortedByIncomeId.get(index).getMinIncome();
        } else {
            return countriesSortedByIncomeId.get(index - 1).getMinIncome();
        }
    }

    static void fillMinIdForEachIncome(List<Country> countriesSortedByIncomeId, Map<Integer, Integer> mapForIncomeId) {
        int prevCountryIncome = MAX_VALUE;
        for (Country country : countriesSortedByIncomeId) {
            if (!mapForIncomeId.containsKey(country.getMinIncome())) {
                int prevCountryId = mapForIncomeId.getOrDefault(prevCountryIncome, MAX_VALUE);
                int id = Math.min(prevCountryId, country.getId());
                mapForIncomeId.put(country.getMinIncome(), id);
                prevCountryIncome = country.getMinIncome();
            }
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(new File("input.txt")));
        int n = scanner.nextInt();
        int[] minIncome = new int[n];
        for (int i = 0; i < n; i++) {
            minIncome[i] = scanner.nextInt();
        }
        boolean[] higherEducationRequired = new boolean[n];
        for (int i = 0; i < n; i++) {
            higherEducationRequired[i] = scanner.nextInt() == 1;
        }
        boolean[] childrenOfCitizensAllowed = new boolean[n];
        for (int i = 0; i < n; i++) {
            childrenOfCitizensAllowed[i] = scanner.nextInt() == 1;
        }

        Country[] countries = new Country[n];
        for (int i = 0; i < n; i++) {
            countries[i] = new Country(i + 1, minIncome[i], higherEducationRequired[i], childrenOfCitizensAllowed[i]);
        }

        Map<Integer, Integer> mapIncomeIdNotNeedHigherEd = new HashMap<>();
        List<Country> countriesSortedByIncomeIdNotNeedHigherEd = Arrays.stream(countries)
                .filter(country -> !country.edRequired)
                .sorted(Comparator.comparing(Country::getMinIncome).thenComparing(Country::getId))
                .collect(Collectors.toList());
        fillMinIdForEachIncome(countriesSortedByIncomeIdNotNeedHigherEd, mapIncomeIdNotNeedHigherEd);

        Map<Integer, Integer> mapIncomeId = new HashMap<>();
        List<Country> countriesSortedByIncomeId = Arrays.stream(countries)
                .sorted(Comparator.comparing(Country::getMinIncome).thenComparing(Country::getId))
                .collect(Collectors.toList());
        fillMinIdForEachIncome(countriesSortedByIncomeId, mapIncomeId);

        int q = scanner.nextInt();
        int[] income = new int[q];
        for (int i = 0; i < q; i++) {
            income[i] = scanner.nextInt();
        }
        boolean[] hasHigherEducation = new boolean[q];
        for (int i = 0; i < q; i++) {
            hasHigherEducation[i] = scanner.nextInt() == 1;
        }
        int[] parentCitizenship = new int[q];
        for (int i = 0; i < q; i++) {
            parentCitizenship[i] = scanner.nextInt();
        }

        for (int i = 0; i < q; i++) {
            int xIncome = income[i];
            boolean yEducation = hasHigherEducation[i];
            int zParentCountry = parentCitizenship[i] - 1;

            int parentCountryId = MAX_VALUE;
            if (zParentCountry >= 0 && countries[zParentCountry].childrenAllowed) {
                parentCountryId= countries[zParentCountry].id;
            }

            int countryId = MAX_VALUE;
            int countryIncome;
            if (!yEducation) {
                countryIncome = binSearch(countriesSortedByIncomeIdNotNeedHigherEd, xIncome);
                countryId = mapIncomeIdNotNeedHigherEd.getOrDefault(countryIncome, MAX_VALUE);
            } else {
                countryIncome = binSearch(countriesSortedByIncomeId, xIncome);
                countryId = mapIncomeId.getOrDefault(countryIncome, MAX_VALUE);
            }

            countryId = Math.min(countryId, parentCountryId);

            int result = countryId == MAX_VALUE ? 0 : countryId;
            System.out.print(result + " ");
        }

        scanner.close();
    }
}