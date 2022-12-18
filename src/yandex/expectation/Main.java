package yandex.expectation;

import java.io.*;

public class Main {
    static double getExpectation(double[] array, double n)
    {
    // вероятность взять книгу
        double prb = (1 / n);

        // подсчет ожидания
        double sum = 0;
        for (int i = 0; i < n; i++)
            sum += Math.abs(array[i]) * prb;

        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        String[] times = reader.readLine().split(" ");
        if (n != times.length) throw new IOException("Введены неверные значения");

        int count = 0;
        int total = 0;
        double[] array = new double[n];
        for (int i = 0; i<n; i++) {
            array[i] = Double.parseDouble(times[i]);
            if (array[i] >= 0) {
                count++;
                total = Math.max(total,count);
            } else {
                count = 0;
            }
        }

        double scale = Math.pow(10, 9);
        double result = count < n ? getExpectation(array,n)*(count+1) : getExpectation(array,n)*n;
        result = Math.ceil(result * scale) / scale;
        writer.write(String.valueOf(result));

        reader.close();
        writer.close();
    }

}
