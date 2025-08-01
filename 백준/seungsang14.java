import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //원래 쓰려고 햇는데, 입력이 10개 단위로 줄바꿈이 있어서 사용하지 않았다. 시간 복잡도는 충분했었다
		Scanner sc = new Scanner(System.in);
		//StringTokenizer st = new StringTokenizer(br.readLine());
		int T = sc.nextInt();
		
		
		for(int test=0;test<T;test++) {
			StringBuilder sb = new StringBuilder();
			int m = sc.nextInt();
			PriorityQueue<Integer> bigQueue = new PriorityQueue<>(); // 중간값보다 큰 수들 모아놓을 큐
			PriorityQueue<Integer> smallQueue = new PriorityQueue<>(Collections.reverseOrder()); // 중간값보다 작은 수들 모아놓을 큐
			
			int count = 0;
			
			for(int i=0;i<m;i++) {
				int n = sc.nextInt();
				
				if(smallQueue.isEmpty()) { // 첫번째 수
					smallQueue.add(n);
				} else if(bigQueue.isEmpty()) { // 두번째 수
					if(n<smallQueue.peek()) { // 두번째 수가 첫번째 들어왔던 수보다 작은경우
						bigQueue.add(smallQueue.poll());
						smallQueue.add(n);
					} else {
						bigQueue.add(n);
					}
					
				} else if(bigQueue.peek() <= n) {
					bigQueue.add(n);
				} else { // 스몰큐에서 가장 큰값보다 작거나, 그것보다는 큰데 빅큐의 가장 작은 값보단 작은경우
					smallQueue.add(n);
				}
				
				
				if(bigQueue.size() > smallQueue.size()) { // 언제나 스몰큐가 빅큐보다 한개 많거나 같은 숫자여야 함 => 그래야 스몰큐의 가장 큰 값을 뽑았을 때 중간 값 나옴
					smallQueue.add(bigQueue.poll());
				} else if(smallQueue.size() > bigQueue.size() + 1) { // 스몰큐와 빅큐의 개수 차이가 2개 이상일때
					bigQueue.add(smallQueue.poll());
				}
				
				if(i%2==0) { // 홀수번째일때, 값 입력
					sb.append(smallQueue.peek()).append(" ");
					count++;
					if(count%10==0) {
						sb.append("\n");
					}
				}
			}
			
			
			
			System.out.println(count);
			System.out.println(sb);
		}
		
		
		
		
		
		
	}
}



