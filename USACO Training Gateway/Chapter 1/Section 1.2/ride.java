import java.io.*;
import java.util.*;

public class ride {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(/*new InputStreamReader(System.in)*/ new FileReader(new File("ride.in")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String comet = st.nextToken();
        st = new StringTokenizer(br.readLine());
        String group = st.nextToken();
        
        
        long cometSum = 0;
        for(int i = 0; i < comet.length(); i++) {
        	cometSum *= comet.charAt(i) - 'A' + 1;
        }
        
        long groupSum = 0;
        for(int i = 0; i < group.length(); i++) {
        	groupSum *= group.charAt(i) - 'A' + 1;
        }

        if((cometSum % 47) == (groupSum % 47)) {
        	System.out.println("GO");
        } else {
        	System.out.println("STAY");
        }
    }
}
