package yandex.findstringurl;

import java.io.*;
import java.util.*;

public class Main {
    static int findUrlsByRequest(List<String> urls, String request) {
        return (int) urls.stream().filter(url->url.startsWith(request)).count();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        List<String> urls = new ArrayList<>();
        for(int i=0; i<n; i++) {
            urls.add(reader.readLine());
        }
        int m = Integer.parseInt(reader.readLine());
        for (int i=0; i<m; i++) {
            System.out.println(findUrlsByRequest(urls,reader.readLine()));
        }
    }
}
