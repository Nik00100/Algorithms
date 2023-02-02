package training.algo1_0.task1.conditioner;

/*В офисе, где работает программист Петр, установили кондиционер нового типа. Этот кондиционер отличается особой простотой
в управлении. У кондиционера есть всего лишь два управляемых параметра: желаемая температура и режим работы.

Кондиционер может работать в следующих четырех режимах:

«freeze» — охлаждение. В этом режиме кондиционер может только уменьшать температуру.
Если температура в комнате и так не больше желаемой, то он выключается.

«heat» — нагрев. В этом режиме кондиционер может только увеличивать температуру.
Если температура в комнате и так не меньше желаемой, то он выключается.

«auto» — автоматический режим. В этом режиме кондиционер может как увеличивать, так и уменьшать температуру в комнате до желаемой.

«fan» — вентиляция. В этом режиме кондиционер осуществляет только вентиляцию воздуха и не изменяет температуру в комнате.

Кондиционер достаточно мощный, поэтому при настройке на правильный режим работы он за час доводит температуру в комнате до желаемой.

Требуется написать программу, которая по заданной температуре в комнате troom, установленным на кондиционере желаемой температуре
tcond и режиму работы определяет температуру, которая установится в комнате через час.

Формат ввода
Первая строка входного файла содержит два целых числа troom, и tcond, разделенных ровно одним пробелом
(–50 ≤ troom ≤ 50, –50 ≤ tcond ≤ 50).

Вторая строка содержит одно слово, записанное строчными буквами латинского алфавита — режим работы кондиционера.

Формат вывода
Выходной файл должен содержать одно целое число — температуру, которая установится в комнате через час.

Пример 1
Ввод	Вывод
10 20
heat
20
Пример 2
Ввод	Вывод
10 20
freeze
10*/


import java.io.*;

public class Main {

    enum Mode {
        FREEZE("freeze"),
        HEAT("heat"),
        AUTO("auto"),
        FAN("fan");

        String mode;
        Mode(String mode) {
            this.mode = mode;
        }

        public String getMode() {
            return mode;
        }
    }



    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = reader.readLine().split(" ");

        int tRoom = Integer.parseInt(s[0]);
        int tCond = Integer.parseInt(s[1]);
        boolean needToHeat = tRoom < tCond;

        int res = tRoom;

        String mode = reader.readLine();

        if (mode.equals(Mode.FREEZE.getMode())) {
            if (needToHeat) res = tRoom;
            else res = tCond;
        }
        if (mode.equals(Mode.HEAT.getMode())) {
            if (needToHeat) res = tCond;
            else res = tRoom;
        }
        if (mode.equals(Mode.AUTO.getMode())) res = tCond;
        if (mode.equals(Mode.FAN.getMode())) res = tRoom;

        System.out.println(res);

        reader.close();
    }
}
