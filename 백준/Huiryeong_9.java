import java.util.Scanner;

public class Huiryeong_9 {
    static boolean[][] arr;
    static int answer;

    static void backTrack(int x, int y) {
        if (x == arr.length-1) {
            answer++;
            return;
        }

        for (int i = 0; i < x; i++) {
            if (arr[i][y]) {
                return;
            } else if (y-(x-i) >= 0 && y-(x-i) < arr.length && arr[i][y-(x-i)]) {
                return;
            } else if (y+(x-i) >= 0 && y+(x-i) < arr.length && arr[i][y+(x-i)]) {
                return;
            }
        }

        arr[x][y] = true;
        for (int i = 0; i < arr.length; i++) {
            backTrack(x+1, i);
        }
        arr[x][y] = false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        arr = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            backTrack(0,i);
        }

        System.out.println(answer);

        sc.close();
    }
}
