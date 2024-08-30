import java.io.*;
import java.util.*;

/**
 * 
 * N*N 크기의 체스판에 N개의 퀸을 두는 방법
 * 	수평 : 해당 열에 퀸을 배치하면 다음 퀸은 해당 열에 배치할 수 없음
 * 	수직 : 배치한 퀸과 행이 같으면 다음 퀸은 해당 칸에 배치할 수 없음
 * 	대각선 : |대각선 칸의 열 - 퀸의 열| == |대각선 칸의 행 - 퀸의 행|
 */

public class ALGO_Backtracking_NQueen {
	static int[][] board; // 체스판
	static int N; // 체스판의 크기
	static int count = 0; // 경우의 수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // N*N 크기의 체스판에 N개의 퀸을 두자
		
		board = new int[N][N];
		
		nQueen(0);
		System.out.println(count);
		
	} // end of main
	
	/**
	 * 현재 행의 상태공간 트리 탐색
	 * @param depth : 현재 행, 상태공간 트리의 깊이
	 */
	static void nQueen(int depth) {
		// 모든 행에 대하여 퀸의 배치를 마쳤을 경우
		if(depth == N) {
			count++; 
		}
		
		for(int i = 0; i < N; i++) {
			if(isPromising(depth, i)) { // 퀸을 놓을 위치가 유망한지(Promising) 판단 
				board[depth][i] = 1; // 해당 위치에 퀸을 배치
				nQueen(depth + 1); // 다음 행의 상태 공간 트리 탐색
				board[depth][i] = 0; // 탐색 종료 후 퀸 제거
			}
		}
	}
	
	/**
	 * 퀸을 놓을 위치가 유망한지(Promising) 판단
	 * @param row : 현재 행
	 * @param col : 현재 열
	 * @return
	 */
	static boolean isPromising(int row, int col) {
		// 현재 퀸을 놓을 위치의 열 확인
		for(int i = 0; i < row; i++) {
			if(board[i][col] == 1) { // 모든 행을 돌면서 같은 열에 퀸이 있다면 false
				return false;
			}
		}
		
		// 현재 퀸을 놓을 위치의 왼쪽 위 대각선 확인, 아래쪽은 재귀호출에 의해 Depth가 깊어지면서 탐색됨
		int i = row; // 현재 행과 열로 초기화
		int j = col;
		while(i >= 0 && j >= 0) { // 점점 왼쪽으로 가다가 인덱스 범위를 벗어나면 종료
			if(board[i][j] == 1) {
				return false;
			}
			i--;
			j--;
		}
		
		// 현재 퀸을 놓을 위치의 오른쪽 위 대각선 확인, 아래쪽은 재귀호출에 의해 Depth가 깊어지면서 탐색됨
		i = row;
		j = col;
		while(i >= 0 && j < N) { // i는 점점 위로 가고, j는 점점 오른쪽으로 감
			if(board[i][j] == 1) {
				return false;
			}
			i--;
			j++;
		}
		
		return true;
	}
	
} // end of class
