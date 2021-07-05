import java.io.*;
import java.util.*;

class traffic {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("traffic.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("traffic.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        
        int[][] segs = new int[n][3];
        
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            if(str.equals("on")){
                segs[i][0] = 1;
            } else if (str.equals("none")){
                segs[i][0] = 0;
            } else if(str.equals("off")){
                segs[i][0] = -1;
            }
            
            segs[i][1] = Integer.parseInt(st.nextToken());
            segs[i][2] = Integer.parseInt(st.nextToken());
        }
       
        int none = firstNone(segs);
        int min1 = segs[none][1];
        int max1 = segs[none][2];

        
        for(int i = none + 1; i < n; i++){
            if(segs[i][0] == 1){
                min1 += segs[i][1];
                max1 += segs[i][2];
            } else if(segs[i][0] == 0){
                min1 = Math.max(min1, segs[i][1]);
                max1 = Math.min(max1, segs[i][2]);
            } else if(segs[i][0] == -1){
                min1 -= segs[i][2];
                max1 -= segs[i][1];
            }
        }
        
        
        int min2 = min1;
        int max2 = max1;
        
        for(int i = segs.length - 1; i >= 0; i--){
            if(segs[i][0] == 1){
                min2 -= segs[i][2];
                max2 -= segs[i][1];
            } else if(segs[i][0] == 0){
                min2 = Math.max(min2, segs[i][1]);
                max2 = Math.min(max2, segs[i][2]);
            } else if(segs[i][0] == -1){
                min2 += segs[i][1];
                max2 += segs[i][2];
            }
        } 
        
        pw.println(min2 + " " + max2);
        pw.println(min1 + " " + max1);
        
        pw.close();
    }
    
    public static int firstNone(int[][] array){
        for(int i= 0; i < array.length; i++){
            if(array[i][0] == 0) return i;
        }
        return 0;
    }
}
