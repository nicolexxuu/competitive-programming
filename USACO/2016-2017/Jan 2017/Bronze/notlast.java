import java.io.*;
import java.util.*;

class notlast2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("notlast.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("notlast.out")));

        int[] cows = new int[7];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int amount = Integer.parseInt(st.nextToken());
            if (name.equals("Bessie")) {
                cows[0] += amount;
            } else if (name.equals("Elsie")) {
                cows[1] += amount;
            } else if (name.equals("Daisy")) {
                cows[2] += amount;
            } else if (name.equals("Gertie")) {
                cows[3] += amount;
            } else if (name.equals("Annabelle")) {
                cows[4] += amount;
            } else if (name.equals("Maggie")) {
                cows[5] += amount;
            } else if (name.equals("Henrietta")) {
                cows[6] += amount;
            }
        }

        int min = cows[0];
        for(int i = 0; i < cows.length; i++){
            if(cows[i] < min) min = cows[i];
        }
        
        int secondMin = Integer.MAX_VALUE;
        int secondMinIndex = 0;
        boolean duplicates = false;
        for(int i = 0; i < cows.length; i++){
            if(cows[i] != min){
                if(cows[i] == secondMin) duplicates = true;
                if(cows[i] > min && cows[i] < secondMin) {
                    secondMin = cows[i];
                    secondMinIndex = i;
                }
            }
            if(duplicates) break;
        }
        
        if(duplicates || secondMin == Integer.MAX_VALUE)pw.println("Tie");
        else{
            if(secondMinIndex == 0) pw.println("Bessie");
            else if(secondMinIndex == 1) pw.println("Elsie");
            else if(secondMinIndex == 2) pw.println("Daisy");
            else if(secondMinIndex == 3) pw.println("Gertie");
            else if(secondMinIndex == 4) pw.println("Annabelle");
            else if(secondMinIndex == 5) pw.println("Maggie");
            else if(secondMinIndex == 6) pw.println("Henrietta");
        }
        pw.close();
    }
}
