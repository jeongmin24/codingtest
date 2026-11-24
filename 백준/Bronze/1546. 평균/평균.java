import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Double[] arr = new Double[N];
        int max = Integer.MIN_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int score = Integer.parseInt(st.nextToken());
            arr[i] = (double) score;
            max = Math.max(score, max);
        }

        double newScore = 0;

        for (int i = 0; i < N; i++) {
            newScore += (arr[i] / max) * 100;
        }

        System.out.println(newScore/N);

    }
}