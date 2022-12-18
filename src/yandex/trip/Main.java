package yandex.trip;

import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int getDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }



    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        List<Point> points = new ArrayList<>();
        for (int i=0; i<n; i++) {
            String[] xy = reader.readLine().split(" ");
            points.add(new Point(Integer.parseInt(xy[0]),Integer.parseInt(xy[1])));
        }

        reader.close();
        writer.close();
    }
}
