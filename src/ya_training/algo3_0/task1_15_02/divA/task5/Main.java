package ya_training.algo3_0.task1_15_02.divA.task5;

/*15. Поврежденный XML

Формат XML является распространенным способом обмена данными между различными программами. Недавно программист Иванов написал
небольшую программу, которая сохраняет некоторую важную информацию в виде XML-строки.

XML-строка состоит из открывающих и закрывающих тегов.

Открывающий тег начинается с открывающей угловой скобки (<), за ней следует имя тега — непустая строка из строчных букв латинского
алфавита, а затем закрывающая угловая скобка (>). Примеры открывающих тегов: <a>, <dog>.

Закрывающий тег начинается с открывающей угловой скобки, за ней следует прямой слеш (/), затем имя тега — непустая строка из
строчных букв латинского алфавита, а затем закрывающая угловая скобка. Примеры закрывающихся тегов: </a>, </dog>.

XML-строка называется корректной, если она может быть получена по следующим правилам:

• Пустая строка является корректной XML-строкой.

• Если A и B — корректные XML-строки, то строка AB, получающаяся приписыванием строки B в конец строки A, также является корректной
XML-строкой.

• Если A — корректная XML-строка, то строка <X>A</X>, получающаяся приписыванием в начало A открывающегося тега,
а в конец — закрывающегося с таким же именем, также является корректной XML-строкой. Здесь X — любая непустая строка из строчных
букв латинского алфавита.

Например, представленные ниже строки:

<a></a>

<a><ab></ab><c></c></a>

<a></a><a></a><a></a>

являются корректными XML-строками, а такие строки как:

<a></b>

<a><b>

<a><b></a></b>

не являются корректными XML-строками.

Иванов отправил файл с сохраненной XML-строкой по электронной почте своему коллеге Петрову. Однако, к сожалению, файл повредился в
процессе пересылки: ровно один символ в строке заменился на некоторый другой символ.

Требуется написать программу, которая по строке, которую получил Петров, восстановит исходную XML-строку, которую отправлял Иванов.

Формат ввода
Входной файл содержит одну строку, которая заменой ровно одного символа может быть превращена в корректную XML-строку. Длина строки
лежит в пределах от 7 до 1000, включительно. Строка содержит только строчные буквы латинского алфавита и символы «<»
(ASCII код 60), «>»(ASCII код 62) и «/»(ASCII код 47). Строка во входном файле заканчивается переводом строки.

Формат вывода
Выходной файл должен содержать корректную XML-строку, которая может быть получена из строки во входном файле заменой ровно одного
 символа на другой. Если вариантов ответа несколько, можно вывести любой.

Пример 1
Ввод	Вывод
<a></b>
<b></b>
Пример 2
Ввод	Вывод
<a><aa>
<a></a>
Пример 3
Ввод	Вывод
<a><>a>
<a></a>
Пример 4
Ввод	Вывод
<a/</a>
<a></a>

Метод checkTags(String xml) проверяет, является ли входная строка корректным XML-документом. Он принимает входную строку xml
и возвращает true, если она является корректным XML-документом, и false в противном случае.
Корректный XML-документ должен начинаться с открывающего тега <tag> и заканчиваться соответствующим закрывающим тегом </tag>.
Внутри тегов могут содержаться другие теги и/или текстовые данные.

Метод changeSymbol(char j, char[] string, ArrayList<String> result) принимает символ j, массив символов string и
ArrayList строк result. Он изменяет каждый символ в string на символ j, по очереди, и проверяет, является ли новая строка
корректным XML-документом с помощью метода checkTags(). Если новая строка является корректным XML-документом,
она добавляется в result.

В main() подсчитывается количество открывающих и закрывающих угловых скобок (< и > соответственно), слэшей (/) и
символов латинского алфавита (a-z, A-Z). Затем он вызывает changeSymbol() для каждого символа <>/qwertyuiopasdfghjklzxcvbnm
(включая угловые скобки, слэши и символы латинского алфавита), который может быть заменен на другой символ, чтобы получить новый
корректный XML-документ. Первый элемент result выводится на экран */

import java.util.*;

public class Main {
    public static boolean checkTags(String xml) {
        if (xml.charAt(0) != '<' || xml.charAt(xml.length()-1) != '>') {
            return false;
        }
        ArrayList<String> opening_tag = new ArrayList<>();
        int i = 0;
        while (true) {
            if (i == xml.length()) {
                break;
            }
            if (xml.charAt(i) != '<') {
                return false;
            }
            i++;
            int closing_tag = 0;
            if (xml.charAt(i) == '/') {
                closing_tag  = 1;
                i++;
            }
            if (!(xml.charAt(i) >= 'a' && xml.charAt(i) <= 'z')) {
                return false;
            }
            String tag = String.valueOf(xml.charAt(i));
            i++;
            while (xml.charAt(i) >= 'a' && xml.charAt(i) <= 'z') {
                tag += xml.charAt(i);
                i++;
            }
            if (xml.charAt(i) != '>') {
                return false;
            }
            i++;
            if (closing_tag  == 0) {
                opening_tag.add(tag);
            } else {
                if (opening_tag.size() == 0) {
                    return false;
                }
                if (!opening_tag.get(opening_tag.size()-1).equals(tag)) {
                    return false;
                }
                opening_tag.remove(opening_tag.size()-1);
            }
        }
        return opening_tag.size() == 0;
    }

    public static void changeSymbol(char j, char[] string, ArrayList<String> result) {
        for (int i = 0; i < string.length; i++) {
            char[] temp = string.clone();
            temp[i] = j;
            if (checkTags(String.valueOf(temp))) {
                result.add(String.valueOf(temp));
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] string = scanner.nextLine().toCharArray();
        int opened_chevron = 0;
        int closed_chevron = 0;
        int slashes = 0;
        HashMap<Character, Integer> letters = new HashMap<>();
        ArrayList<String> result = new ArrayList<>();

        for (char i : string) {
            letters.put(i, letters.getOrDefault(i, 0) + 1);
            if (i == '<') {
                opened_chevron++;
            } else if (i == '>') {
                closed_chevron++;
            } else if (i == '/') {
                slashes++;
            }
        }

        for (char j : "<>/qwertyuiopasdfghjklzxcvbnm".toCharArray()) {
            if (j == '<' && opened_chevron % 2 != 0) {
                changeSymbol(j, string, result);
            } else if (j == '>' && closed_chevron % 2 != 0) {
                changeSymbol(j, string, result);
            } else if (j == '/' && closed_chevron / 2 != slashes) {
                changeSymbol(j, string, result);
            } else if (letters.containsKey(j) && letters.get(j) % 2 != 0) {
                changeSymbol(j, string, result);
            }
        }

        System.out.println(result.get(0));

    }
}
