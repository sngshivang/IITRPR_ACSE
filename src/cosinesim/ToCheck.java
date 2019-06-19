package cosinesim;

import java.util.StringTokenizer;

public class ToCheck {
	public static void main(String args[])
	{
		String a = "int a= 2;";
		StringTokenizer tk = new StringTokenizer(a);
		while(tk.hasMoreTokens())
		{
			System.out.println(tk.nextToken());
		}
	}
}
