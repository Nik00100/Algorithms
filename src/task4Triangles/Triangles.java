package task4Triangles;

import java.util.ArrayList;
import java.util.List;

public class Triangles {

    // метод проверки треугольника на соответствие критерию (нет пересечений даже по вершинам с другими треугольниками)
    private boolean correctTriangle (List<Main.Triangle> path, int first, int second, int third) {
        boolean flag = true;
        for (Main.Triangle triangle : path) {
            boolean f = triangle.first != first && triangle.first != second && triangle.first != third;
            boolean s = triangle.second != first && triangle.second != second && triangle.second != third;
            boolean t = triangle.third != first && triangle.third != second && triangle.third != third;
            flag = flag && f && s && t;
        }
        return flag;
    }


    // получить один набор треугольников
    List<List<Main.Triangle>> triangles = new ArrayList<>();
    public void MWT(int[] vertices, int i, int j, List<Main.Triangle> path) {
        // If the polygon has less than 3 vertices, triangulation is not possible
        if (j < i + 2) {
            triangles.add(path);
            return;
        }
        // consider all possible triangles `ikj` within the polygon
        for (int k = i + 1; k <= j - 1; k++) {
            if (correctTriangle(path,vertices[i],vertices[k],vertices[j])) {
                path.add(new Main.Triangle(vertices[i], vertices[k], vertices[j]));
                MWT(vertices, i + 1, k - 1, path);
                MWT(vertices, k + 1, j - 1, path);
            }
        }
        triangles.remove(triangles.size()-1);
    }


    // получить все возможные наборы треугольников и стартовый треугольник начинается в первой вершине
    public List<List<Main.Triangle>> getTriangles(int[] vertices) {
        List<List<Main.Triangle>> res = new ArrayList<>();
        for (int k=1; k<=(vertices.length-1)/2; k++) {
            for (int j=vertices.length-1; j>(vertices.length-1)/2; j--) {
                List<Main.Triangle> path = new ArrayList<>();
                dfs(vertices,0, k,j, path);
                if (!path.isEmpty()) res.add(path);
            }
        }
        return res;
    }

    public void dfs(int[] vertices, int i, int k, int j, List<Main.Triangle> path) {
        if (j<i+2)
            return;
        if (k<=i || k>=j || k > vertices.length/2 || j <= vertices.length/2)
            return;
        if (correctTriangle(path, vertices[i], vertices[k], vertices[j])) {
            path.add(new Main.Triangle(vertices[i], vertices[k], vertices[j]));
            dfs(vertices, i+1, i+2,k-1, path);
            dfs(vertices, k+1, k+2,j-1, path);
            dfs(vertices,j+1,j+2, vertices.length-1,path);
        }
    }



}
