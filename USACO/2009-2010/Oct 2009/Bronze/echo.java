import java.io.*;
import java.util.*;

public class echo {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String moo1 = st.nextToken();
        st = new StringTokenizer(br.readLine());
        String moo2 = st.nextToken();
        
        int res = 0;
        
        for(int i = 1; i <= moo1.length(); i++){
            String prefix = moo1.substring(0, i);
            if(moo2.substring(moo2.length() - 1 - prefix.length(), moo2.length()).equals(prefix)){
                res++;
            }
        }
        
        

        System.out.println(res);
    }
}
