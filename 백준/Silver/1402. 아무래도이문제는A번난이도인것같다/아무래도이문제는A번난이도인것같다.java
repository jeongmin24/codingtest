import java.io.*;
import java.util.*;

public class Main {

    public static boolean getStatus(int A, int B) {

        return true;
    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for( int t=1; t<=T; t++ ) {

            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            boolean status = getStatus(A, B);
            System.out.println(status?"yes":-1);

        }

    }
}