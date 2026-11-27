import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] people = new int[N];
        int max = 0;
        int period = 1;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        int sum1 = 0;
        for(int j=0; j<X; j++) {
            sum1 += people[j];
        }
        max = sum1;

        for(int i=1; i<=N-X; i++) {
            sum1 = sum1 - people[i-1] + people[i+X-1];

            if(sum1 > max) {
                max = sum1;
                period = 1;
            } else if(sum1 == max) {
                period++;
            }
        }

        if(max == 0) {
            System.out.println("SAD");
        }else {
            System.out.printf("%d\n%d", max, period);
        }
    }
}