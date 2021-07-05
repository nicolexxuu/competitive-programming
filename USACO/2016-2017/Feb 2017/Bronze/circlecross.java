import java.io.*;
import java.util.*;

public class circlecross {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char[] paths = st.nextToken().toCharArray();
        int count = 0;
        for(char cow = 'A'; cow <= 'Z'; cow++){
            boolean start = false;
            boolean[] letters= new boolean[26];
            for(int i = 0; i < paths.length; i++){
                if(paths[i] == cow){
                    if(!start){
                        start = true;
                    } else {
                        break;
                    }
                } else {
                    if(start){
                        if(letters[paths[i] - 'A']){
                            count--;
                        } else {
                            letters[paths[i] - 'A'] = true;
                            count++;
                        }
                    }
                }
            }
        }
        System.out.println(count /2);
    }
}
