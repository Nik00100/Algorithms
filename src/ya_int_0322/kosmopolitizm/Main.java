package ya_int_0322.kosmopolitizm;

import java.util.*;

public class Main {
    static class Country {
        int id;
        int minIncome;
        boolean requiresEducation;
        boolean allowsChildren;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Country[] countries = new Country[n];
        for (int i = 0; i < n; i++) {
            countries[i] = new Country();
            countries[i].id = i + 1;
            countries[i].minIncome = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            countries[i].requiresEducation = scanner.nextInt() == 1;
        }
        for (int i = 0; i < n; i++) {
            countries[i].allowsChildren = scanner.nextInt() == 1;
        }
        Arrays.sort(countries, (a, b) -> {
            if (a.minIncome != b.minIncome) {
                return Integer.compare(b.minIncome, a.minIncome); // sort by minIncome in descending order
            } else if (a.requiresEducation != b.requiresEducation) {
                return Boolean.compare(b.requiresEducation, a.requiresEducation); // sort by requiresEducation in descending order
            } else {
                return Boolean.compare(b.allowsChildren, a.allowsChildren); // sort by allowsChildren in descending order
            }
        });

        int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            int income = scanner.nextInt();
            boolean hasEducation = scanner.nextInt() == 1;
            int parentCitizenship = scanner.nextInt();
            int left = 0;
            int right = n - 1;
            int index = -1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                Country country = countries[mid];
                if (income >= country.minIncome &&
                        (!country.requiresEducation || hasEducation) &&
                        (country.allowsChildren || parentCitizenship == country.id)) {
                    index = mid;
                    right = mid - 1; // look for a better match to the left
                } else {
                    left = mid + 1; // the current country is not a match, look to the right
                }
            }
            if (index == -1) {
                System.out.print(0 + " ");
            } else {
                System.out.print(countries[index].id + " ");
            }
        }


        scanner.close();
    }
}
