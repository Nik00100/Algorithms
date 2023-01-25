package ya_int.alchemistry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class AnotherVariant {
    static Map<Integer,int[]> recipes = new HashMap<>();
    static List<Recipe> recipeList = new ArrayList<>();

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

        @Override
        public String toString() {
            return "Recipe{number=" + number +
                    ", ingredients=" + ingredients.entrySet().stream()
                    .map(e->"["+e.getKey()+","+e.getValue()+"]").collect(Collectors.toList()) + '}';
        }
    }

    public AnotherVariant() {
        recipes.put(1,new int[] {1,0});
        recipes.put(2,new int[] {0,1});
    }

    public static void createRecipeMap (Recipe recipe, int num) {
        if (num == recipeList.size()) return;
        if (recipes.containsKey(recipe.number)) return;
        for (Recipe r : recipeList) {

        }

        /*for (int ing : recipe) {
            if (!recipes.containsKey(ing)) return;
            int[] ingredients = recipes.get(ing);
            int[] r = recipes.getOrDefault(number,new int[] {0,0});
            r[0] += ingredients[0];
            r[1] += ingredients[1];
            recipes.put(number,r);
        }*/
    }

    public static int responseForRequest (int aVal, int bVal, int number) {
        if (!recipes.containsKey(number)) return 0;
        int[] ingredients = recipes.get(number);
        if (ingredients[0] <= aVal && ingredients[1] <= bVal) return 1;
        return 0;
    }


    public static void main(String[] args) throws IOException {
        AnotherVariant main = new AnotherVariant();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        for (int i=3; i<=n; i++) {
            String[] s = reader.readLine().split(" ");
            int ingSize = Integer.parseInt(s[0]);
            int[] recipe = new int[ingSize];
            for (int j=0; j<ingSize; j++) {
                recipe[j] = Integer.parseInt(s[j+1]);
            }
            recipeList.add(new Recipe(i,recipe));
        }

        System.out.println(recipeList);

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
