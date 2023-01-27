package ya_int.alchemistry;

import java.io.*;
import java.util.*;

public class Solution_v2 {
    static Map<Integer,int[]> recipes = new HashMap<>();
    static Queue<Recipe> q = new LinkedList<>();

    static class Recipe {
        int number;
        Map<Integer,Integer> ingredients;
        int[] ing;


        public Recipe(int number, int[] recipe) {
            this.number = number;
            this.ingredients = new HashMap<>();
            for (int r : recipe) {
                ingredients.put(r,ingredients.getOrDefault(r,0) + 1);
            }
            ing = ingredients.keySet().stream().mapToInt(Integer::intValue).toArray();
        }

       /* @Override
        public String toString() {
            return "r {n=" + number +
                    ", i=" + ingredients.entrySet().stream()
                    .map(e->"["+e.getKey()+","+e.getValue()+"]").collect(Collectors.toList()) + '}';
        }*/
    }

    public static void createRecipeMap (int k) {
        recipes.put(1,new int[] {1,0});
        recipes.put(2,new int[] {0,1});
        while (!q.isEmpty() && k>0) {
            Recipe r = q.poll();
            // if couldn't add to map now try again until k attempts
            if (!canAdd(r)) {
                q.offer(r);
                k--;
                continue;
            }

            // if we can add to map - then do it
            int[] ing = r.ing;
            int number = r.number;
            for (int i : ing) {
                int j = r.ingredients.get(i); // quantity of this ingredient
                int[] ingrid = recipes.getOrDefault(number,new int[2]);
                ingrid = new int[] {recipes.get(i)[0]*j + ingrid[0], recipes.get(i)[1]*j + ingrid[1]};
                recipes.put(number,ingrid);
            }
        }
    }

    public static boolean canAdd (Recipe r) {
        int[] ing = r.ing;
        for (int i : ing) {
            if (!recipes.containsKey(i)) {
                return false;
            }
        }
        return true;
    }



    public static int responseForRequest (int aVal, int bVal, int number) {
        if (!recipes.containsKey(number)) return 0;
        int[] ingredients = recipes.get(number);
        if (ingredients[0] <= aVal && ingredients[1] <= bVal) return 1;
        return 0;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        for (int i=3; i<=n; i++) {
            String[] s = reader.readLine().split(" ");
            int ingSize = Integer.parseInt(s[0]);
            int[] recipe = new int[ingSize];
            for (int j=0; j<ingSize; j++) {
                recipe[j] = Integer.parseInt(s[j+1]);
            }
            Recipe r = new Recipe(i,recipe);
            q.offer(r);
        }


        createRecipeMap(2*n);
        /*System.out.println(recipes.entrySet().stream()
                .map(e->"["+e.getKey()+","+ Arrays.stream(e.getValue()).boxed().collect(Collectors.toList())+"]")
                .collect(Collectors.toList()));*/

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
