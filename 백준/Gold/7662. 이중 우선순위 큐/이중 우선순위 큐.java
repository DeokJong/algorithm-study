import static java.lang.Integer.parseInt;

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        int T = parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            solve();
        }
        System.out.println(sb);
    }
    
    private static void solve() throws Exception {
        int commandCount = parseInt(br.readLine());
        TreeMap<Integer, Integer> map = new TreeMap<>();
        
        for (int i = 0; i < commandCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char cmd = st.nextToken().charAt(0);
            int n = parseInt(st.nextToken());
            
            if (cmd == 'I') {
                map.put(n, map.getOrDefault(n, 0) + 1);
            } else {
                if (map.isEmpty()) continue;
                
                if (n == 1) {
                    int maxKey = map.lastKey();
                    int count = map.get(maxKey);
                    if (count == 1) {
                        map.remove(maxKey);
                    } else {
                        map.put(maxKey, count - 1);
                    }
                } else {
                    int minKey = map.firstKey();
                    int count = map.get(minKey);
                    if (count == 1) {
                        map.remove(minKey);
                    } else {
                        map.put(minKey, count - 1);
                    }
                }
            }
        }
        
        if (map.isEmpty()) {
            sb.append("EMPTY").append('\n');
        } else {
            sb.append(map.lastKey()).append(' ').append(map.firstKey()).append('\n');
        }
    }
}
