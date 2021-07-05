

import java.io.*;
import java.util.*;

class buckets {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("buckets.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("buckets.out")));
        StringTokenizer st;

        int bR = 0;
        int bC = 0;
        int lR = 0;
        int lC = 0;
        int rR = 0;
        int rC = 0;

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            String bob = st.nextToken();
            for (int j = 0; j < 10; j++) {
                if(bob.charAt(j) == 'B'){
                    bR = i;
                    bC = j;
                }
                if(bob.charAt(j) == 'R'){
                    rR = i;
                    rC = j;
                }
                if(bob.charAt(j) == 'L'){
                    lR = i;
                    lC = j;
                }
            }
        }
        

        if(bR == lR && bR == rR){
            if((rC < lC && rC < bC) || (rC > lC && rC > bC)){
                pw.println(Math.abs(lC - bC) - 1);
            } else {
                pw.println(Math.abs(lC - bC) + 1);
            }
        } else if(bC == lC && bC == rC){
            if((rR < lR && rR < bR) || (rR > lR && rR > bR)){
                pw.println(Math.abs(lR - bR) - 1);
            } else {
                pw.println(Math.abs(lR - bR) + 1);
            }
        } else {
            pw.println(Math.abs(lC - bC) + Math.abs(lR - bR) - 1);
        }

        pw.close();
    }
}
