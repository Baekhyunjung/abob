import java.util.*;
import java.io.*;
public class main {

	public static void main(String[] args) {
		// Variable
		Scanner scan = new Scanner(System.in);
		int a_time = 0; //���� �ð�
		int t_time = 0; //�� �ɸ��� �ð�
		String cur_loc = null; // ���� ��ġ
		String des_loc = null; // ���� ��ġ
		String store_loc = null; // ���� ��ġ
		
		a_time=scan.nextInt();
		//
		//floyd warshall ��� �� db���� �ð� �Ѱܼ� ����ϴ� ��....
		// �ּ� ��� �߰�....
		
		DB jdbc = new DB();
		jdbc.DB(a_time);
	}
}
