import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();
        int C = sc.nextInt();
        char[][] grid = new char[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                grid[i][j] = sc.next().charAt(0);
            }
        }
        // Please write your code here.
        char color;
        int cnt = 0;
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                color = grid[0][0];
                if(grid[i][j] != color) {
                    color = grid[i][j];
                    for (int k = i+1; k < R; k++) {
                        for (int l = j+1; l < C; l++) {
                            color = grid[i][j];
                            if(grid[k][l] != color) {
                                color = grid[k][l];
                                for (int m = k+1; m < R; m++) {
                                    for (int n = l+1; n < C; n++) {
                                        color = grid[k][l];
                                        if(grid[m][n] != color && m == R-1 && n == C-1) {
                                            cnt++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

        System.out.println(cnt);
    }
}