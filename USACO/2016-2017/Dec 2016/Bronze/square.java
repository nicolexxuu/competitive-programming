import java.io.*;
import java.util.*;

class square {

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("square.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("square.out")));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x1LL = Integer.parseInt(st.nextToken());
        int y1LL = Integer.parseInt(st.nextToken());
        int x1UR = Integer.parseInt(st.nextToken());
        int y1UR = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int x2LL = Integer.parseInt(st.nextToken());
        int y2LL = Integer.parseInt(st.nextToken());
        int x2UR = Integer.parseInt(st.nextToken());
        int y2UR = Integer.parseInt(st.nextToken());
        
        int result = 0;
        
        int length1 = Math.max(x1UR, x2UR) - Math.min(x1LL, x2LL);
        int length2 = Math.max(y1UR, y2UR) - Math.min(y1LL, y2LL);
        
        if(length1 >= length2) result = length1 * length1;
        else result = length2 * length2;
        
        
        pw.println(result);
        pw.close();
    }
}
