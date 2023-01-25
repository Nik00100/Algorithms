package ya_int.alchemistry;

import java.io.*;
import java.util.*;

public class Main {
    static Map<Integer,int[]> recipes = new HashMap<>();

    public Main() {
        recipes.put(1,new int[] {1,0});
        recipes.put(2,new int[] {0,1});
    }

    public static void addRecipeIfPossible(int[] recipe, int number) {
        for (int ing : recipe) {
            if (!recipes.containsKey(ing)) return;
            int[] ingredients = recipes.get(ing);
            int[] r = recipes.getOrDefault(number,new int[] {0,0});
            r[0] += ingredients[0];
            r[1] += ingredients[1];
            recipes.put(number,r);
        }
    }

    public static int responseForRequest (int aVal, int bVal, int number) {
        if (!recipes.containsKey(number)) return 0;
        int[] ingredients = recipes.get(number);
        if (ingredients[0] <= aVal && ingredients[1] <= bVal) return 1;
        return 0;
    }


    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        for (int i=3; i<=n; i++) {
            String[] s = reader.readLine().split(" ");
            int ingSize = Integer.parseInt(s[0]);
            int[] recipe = new int[ingSize];
            for (int j=0; j<ingSize; j++) {
                recipe[j] = Integer.parseInt(s[j+1]);
            }
            addRecipeIfPossible(recipe,i);
        }

        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(reader.readLine());
        for (int i=0; i<m; i++) {
            String[] s = reader.readLine().split(" ");
            int aVal = Integer.parseInt(s[0]);
            int bVal = Integer.parseInt(s[1]);
            int number = Integer.parseInt(s[2]);
            sb.append(responseForRequest(aVal,bVal,number));
        }
        System.out.println(sb);
        reader.close();
    }
}
