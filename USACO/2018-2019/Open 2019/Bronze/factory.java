import java.io.*;
import java.util.*;

class factory {
    
    public static boolean [][] nodes;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("factory.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("factory.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        
        
        int[][] array = new int[n - 1][2];
        nodes = new boolean[n + 1][n + 1];
        
        for(int i = 1; i <= n; i++){
            nodes[i][i] = true;
        }
        
        for(int i = 0; i < n - 1; i++){
            st= new StringTokenizer(br.readLine());
            for(int j = 1; j >= 0; j--){
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i = 1; i <= n; i++){
            checkValues(array, i, i);
        }
        
        boolean allTrue = false;
        
        for(int i = 1; i < nodes.length; i++){
            allTrue = true;
            for(int j = 1; j < nodes.length; j++){
                if(!nodes[i][j]) allTrue = false;
            }
            if(allTrue){
                pw.println(i);
                break;
            }
        }
        
        if(!allTrue) pw.println(-1);
        
        pw.close();
    }
    
    public static void checkValues(int[][] array, int i, int startingValue){
        for(int j = 0; j < array.length; j++){
            if(array[j][0] == i){
                nodes[i][array[j][1]] = true;
                nodes[startingValue][array[j][1]] = true;
                checkValues(array, array[j][1], startingValue);
            }
        }
    }
    
}
