package String.StringParenGenerate;

/*See also BacktrackParenGenerate in BFS_DFS_UnionFind another variant


        Approach: Initially, add () to the result set.
        Then for each item in result set, add the following 4 combos:
        1. () + item
        2. item + ()
        3. () inside each () in item
        4. )( inside each () in item*/

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
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
        System.out.println(generateParenthesis(4));
    }
}
