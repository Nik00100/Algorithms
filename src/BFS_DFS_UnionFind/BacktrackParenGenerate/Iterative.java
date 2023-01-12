package BFS_DFS_UnionFind.BacktrackParenGenerate;

 /*     Approach: Initially, add () to the result set.
        Then for each item in result set, add the following 4 combos:
        1. () + item
        2. item + ()
        3. () inside each () in item
        4. )( inside each () in item */

import java.io.*;
import java.util.*;

class Iterative {
    public static List<String> generateParenthesis(int n) {
        HashSet<String> allCombos = new HashSet<>();
        for(int i=0;i<n;i++){
            //Initial situation
            if(allCombos.size()==0){
                allCombos.add("()");
            } else{
                HashSet<String> combos = new HashSet<>();
                for(String combo : allCombos){
                    //Add parenthesis to both sides
                    combos.add(combo+"()");
                    combos.add("()"+combo);
                    //Add parenthesis inside openings
                    for(int j=0;j<combo.length()-1;j++){
                        //Opening detected
                        if(combo.charAt(j)=='(' && combo.charAt(j+1)==')'){
                            combos.add(combo.substring(0,j+1)+"()"+combo.substring(j+1));
                            combos.add(combo.substring(0,j+1)+")("+combo.substring(j+1));
                        }
                    }
                }
                allCombos = combos;
            }
        }
        return new ArrayList<String>(allCombos);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int m = Integer.parseInt(reader.readLine());
        List<String> list = generateParenthesis(m);
        if (list != null)
            list.stream().forEach(str -> {
                try {
                    writer.write(str);
                    writer.newLine();
                } catch (IOException e) {}
            });
        else
            try {
                writer.newLine();
            } catch (IOException e) {}

        reader.close();
        writer.close();
    }
}