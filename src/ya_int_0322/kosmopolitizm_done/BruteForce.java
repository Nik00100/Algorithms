package ya_int_0322.kosmopolitizm_done;

import java.util.Scanner;

public class BruteForce {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
    }
}