package ya_int_0322.kosmopolitizm_done;

import java.io.*;

/**
 * Полный перебор всех возможных вариантов
 * */

public class BruteForce {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int[] minIncome = new int[n];
        String[] s = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            minIncome[i] = Integer.parseInt(s[i]);
        }

        boolean[] higherEducationRequired = new boolean[n];
        s = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            higherEducationRequired[i] = Integer.parseInt(s[i]) == 1;
        }

        boolean[] childrenOfCitizensAllowed = new boolean[n];
        s = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            childrenOfCitizensAllowed[i] = Integer.parseInt(s[i]) == 1;
        }

        int q = Integer.parseInt(reader.readLine());
        s = reader.readLine().split(" ");
        int[] income = new int[q];
        for (int i = 0; i < q; i++) {
            income[i] = Integer.parseInt(s[i]);
        }

        boolean[] hasHigherEducation = new boolean[q];
        s = reader.readLine().split(" ");
        for (int i = 0; i < q; i++) {
            hasHigherEducation[i] = Integer.parseInt(s[i]) == 1;
        }

        int[] parentCitizenship = new int[q];
        s = reader.readLine().split(" ");
        for (int i = 0; i < q; i++) {
            parentCitizenship[i] = Integer.parseInt(s[i]);
        }

        for (int i = 0; i < q; i++) {
            int countryChosen = 0;
            for (int j = 0; j < n; j++) {
                if ((income[i] >= minIncome[j] && (!higherEducationRequired[j] || hasHigherEducation[i]))
                        || (parentCitizenship[i] == j + 1 && childrenOfCitizensAllowed[j])) {
                    countryChosen = j + 1;
                    break;
                }
            }
            System.out.print(countryChosen + " ");
        }

        reader.close();
    }
}