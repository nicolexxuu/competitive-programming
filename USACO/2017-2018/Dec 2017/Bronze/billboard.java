import java.io.*;
import java.util.*;

public class billboard2 {

    public static int overlapArea(int x1LL, int y1LL, int x1UR, int y1UR,
            int x2LL, int y2LL, int x2UR, int y2UR) {
        int xL = Math.max(x1LL, x2LL);
        int xU = Math.min(x1UR, x2UR);
        if (xL >= xU) {
            return 0;
        }

        int yL = Math.max(y1LL, y2LL);
        int yU = Math.min(y1UR, y2UR);
        if (yL >= yU) {
            return 0;
        }

        return (xU - xL) * (yU - yL);
    }

    public static int areaOfRectangle(int x1, int y1, int x2, int y2) {
        return (x2 - x1) * (y2 - y1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\javaprojects\\usaco\\billboard_bronze_dec17.zip\\1.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\javaprojects\\usaco\\billboard_bronze_dec17.zip\\1-test.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x3 = Integer.parseInt(st.nextToken());
        int y3 = Integer.parseInt(st.nextToken());
        int x4 = Integer.parseInt(st.nextToken());
        int y4 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x5 = Integer.parseInt(st.nextToken());
        int y5 = Integer.parseInt(st.nextToken());
        int x6 = Integer.parseInt(st.nextToken());
        int y6 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x7 = Integer.parseInt(st.nextToken());
        int y7 = Integer.parseInt(st.nextToken());
        int x8 = Integer.parseInt(st.nextToken());
        int y8 = Integer.parseInt(st.nextToken());

        int combinedArea = areaOfRectangle(x1, y1, x2, y2) - overlapArea(x1, y1, x2, y2, x5, y5, x6, y6);
        combinedArea += (areaOfRectangle(x3, y3, x4, y4) - overlapArea(x3, y3, x4, y4, x5, y5, x6, y6));
        pw.println(combinedArea);
        pw.close();
        
        System.out.println(combinedArea);
    }
}
