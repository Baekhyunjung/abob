import java.util.*;
import java.io.*;
public class main {

	public static void main(String[] args) {
		// Variable
		Scanner scan = new Scanner(System.in);
		int a_time = 0; //���� �ð�
		int[] c_r_time = new int[5];
		int[] r_d_time = new int[5];
		int[] m_time = new int[5]; //�̵��ϴµ� �� �ɸ��� �ð�
		int cur_index = 0;
		int des_index = 0;
		String cur_loc = null; // ���� ��ġ
		String des_loc = null; // ���� ��ġ
		String store_loc = null; // ���� ��ġ
		String temp;
		floydwarshall floyd = new floydwarshall();
		locationToIndex lti = new locationToIndex();
		DB jdbc = new DB();
		
		System.out.println("���� �ð� �Է�: ");
		a_time=scan.nextInt();
		temp = scan.nextLine();
		
		System.out.println("���� ��ġ �Է�: ");
		cur_loc = scan.nextLine();
		
		System.out.println("������ �Է�: ");
		des_loc = scan.nextLine();
		
		temp = lti.Convert(cur_loc, des_loc);		
		cur_index = Integer.parseInt(temp.substring(0, temp.indexOf(",")));
		des_index = Integer.parseInt(temp.substring(temp.indexOf(",")+1));
		//System.out.println(cur_index+"     "+des_index);
		
		for(int i=0;i<5;i++) {// <�Ĵ�> 0:��õ��, 1:����Ÿ��(�н�), 2:����Ÿ��(�н�)
									//, 3:��������(�н�), 4:��������(�н�)
		c_r_time[i] = floyd.distance(cur_index, i*2+1);
		r_d_time[i] = floyd.distance( i*2+1, des_index);
		m_time[i] = c_r_time[i] + r_d_time[i];
		if(jdbc.DB(a_time, m_time[i],i*2+1))
		System.out.println("<�ּ��̵��ð�>\n"+"����~�Ĵ� : "+ c_r_time[i]+"\n�Ĵ�~������: "+r_d_time[i]);
		}
		

								
	}


}


