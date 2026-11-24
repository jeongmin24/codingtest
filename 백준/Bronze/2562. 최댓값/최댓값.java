import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int max = Integer.MIN_VALUE;
        int index = 1;

        for (int i = 1; i <= 9; i++) {
            int cur = Integer.parseInt(br.readLine());

            if(cur > max) {
                max = cur;
                index = i;
            }
        }

        System.out.println(max);
        System.out.println(index);

    }
}