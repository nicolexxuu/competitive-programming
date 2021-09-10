import java.util.*;
import java.io.*;

class teleport {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("teleport.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("teleport.out")));
        StringTokenizer st = new StringTokenizer(br.readLine()); //reads one line

        //to read an int:
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        
        pw.println( Math.min( Math.abs(b - a), Math.min( Math.abs(a - x) + Math.abs(b - y), Math.abs(a - y) + Math.abs(b - x) ) ) );
        
        pw.close();
        
        
    }
}
