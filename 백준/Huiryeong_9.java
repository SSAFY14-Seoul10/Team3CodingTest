
import java.util.Scanner;

public class Huiryeong_9 {

    static int answer = 0;
    static int[] arr;
    static int n;

    public static void backTrack(int x, int y) {
        if (x == 0) {
            arr[x] = y;
            for(int i = 0; i < n; ++i) {
                backTrack(x+1, i);
            }
        } else {
            for (int i = 0; i < x; ++i) {
                if (arr[i] == y || arr[i] == y - (x-i) || arr[i] == y + (x - i)) {return;}
            }

            if (x == n-1) {answer += 1; return;}
            
            arr[x] = y;
            for (int i = 0; i < n; i++) {
                backTrack(x+1, i);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            backTrack(0,i);
        }

        System.out.println(answer);
        sc.close();
    }
}
