import java.io.*;
import java.util.*;

class mixmilk {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("mixmilk.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mixmilk.out")));
        
        int[] amounts = new int[3];
        int[] capacities = new int[3];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        capacities[0] = Integer.parseInt(st.nextToken());
        amounts[0] = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        capacities[1] = Integer.parseInt(st.nextToken());
        amounts[1] = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        capacities[2] = Integer.parseInt(st.nextToken());
        amounts[2] = Integer.parseInt(st.nextToken());
        
        int pouringfrom = 0;
        int pouringinto = 1;
        
        for(int i = 0; i < 100; i++){
            
            if(amounts[pouringfrom] + amounts[pouringinto] < capacities[pouringinto]){
                amounts[pouringinto] += amounts[pouringfrom];
                amounts[pouringfrom] = 0;
            } else {
                int temp = capacities[pouringinto] - amounts[pouringinto];
                amounts[pouringinto] += temp;
                amounts[pouringfrom] -= temp;
            }
                
            if(pouringfrom == 2) pouringfrom = 0;
            else pouringfrom++;
            
            if(pouringinto == 2) pouringinto = 0;
            else pouringinto++;
        }
	
        pw.println(amounts[0]);
        pw.println(amounts[1]);
        pw.println(amounts[2]);
        pw.close();
    }
}
