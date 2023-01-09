package KMP_BoyerMoore.KnuthMorrisPrattPalindrome;

/*You are given a string s. You can convert s to a palindrome by adding characters in front of it.
Return the shortest palindrome you can find by performing this transformation.

Example 1:
Input: s = "aacecaaa"
Output: "aaacecaaa"
Example 2:
Input: s = "abcd"
Output: "dcbabcd"

Изюминка КМП—это префикс-функция. Применяя префикс функцию для объединенной строки, решаем исходную задачу.

Пример:
Исходная строка, S      a	a	b	a	a	b	a	a	a	a	b
Индекс символа, i       0   1	2	3	4	5	6	7	8	9	10
Таблица поиска, M       0	1	0	1	2	3	4	5	2	2	3

Строки-префиксы - подстрока, начинающаяся с первого индекса строки.
Суффиксы - подстрока, последний символ которой в строке в позиции индекса символа.
Таблица поиска - массив длин префиксов/суффиксов (max подстрока суффиксов,
эквивалентная подстроке преффиксов). Для первого элемента М[0]=0, всегда.

Для того, чтобы формировать таблицу из предыдущих значений, надо проверить,
совпадает ли добавляемый в суффикс символ  со следующим символом префикса.

Пример для второго символа (S[1] = 'a', М[0]=0),
поскольку S[1] - это следующий символ суффикса, и он равен следующему символу преффикса
(ищем в позиции S[М[0]] = 'a'), то М[1]=M[0]+1.

Пример для восьмого символа (S[7] = 'a', М[6]=4),
cледующий символ префикса находится в позиции М[6]=4, символ префикса==символу суффикса, то М[7]=М[6]+1,

Если, символы преффикса и суффикса не совпадают, то ищем через индексы в таблице М.
Пример для девятого символа (S[8] = 'a', M[7]=5),
S[5]='b' -> не совпадает, ищем М[5]=3, ищем S[3]='a' -> совпало, значит М[8]=M[3]+1

И т.д. для всех значений в таблице
*/

public class Main {
    static String shortestPalindromeKMP(String s) { // O(n) complexity
        String combined_s = s + "$" + new StringBuilder(s).reverse().toString(); // основную строку добавляем к перевернутой
        int n = combined_s.length();
        int[] position = createLookUpTableKMP(combined_s);
        return new StringBuilder(s.substring(position[position.length-1])).reverse().toString()+s;
    }

    static int[] createLookUpTableKMP(String combined_s) {
        int n = combined_s.length();
        int[] lookUpTable = new int[n];
        // ищем, какой префикс-суффикс можно расширить
        for (int i=1; i<n; i++) {
            // длина предыдущего префикса-суффикса, возможно нулевая
            int j = lookUpTable[i-1];
            // если нельзя расширить, берем длину меньшего префикса-суффикса
            while (j>0 && combined_s.charAt(i) != combined_s.charAt(j)) {
                j = lookUpTable[j-1];
            }
            // если можно, расширяем
            if (combined_s.charAt(i) == combined_s.charAt(j)) {
                ++j;
            }
            lookUpTable[i] = j;
        }
        return lookUpTable;
    }

    static String shortestPalindromeBruteForce(String s) { // O(n*n) complexity
        final String t = new StringBuilder(s).reverse().toString();

        for (int i = 0; i < t.length(); ++i)
            if (s.startsWith(t.substring(i)))
                return t.substring(0, i) + s;

        return t + s;
    }

    public static void main(String[] args) {
        System.out.println(shortestPalindromeKMP("aacecaaa"));
        //System.out.println(shortestPalindromeBruteForce("abcd"));
        //System.out.println(Arrays.stream(createLookUpTableKMP("aaacecaa$aacecaaa")).boxed().toList());
    }
}
