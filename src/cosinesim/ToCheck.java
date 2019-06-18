package cosinesim;

public class ToCheck {
	public static void main(String args[])
	{
		String inp = "hello my name is shivang but is a different shivang";
		String pat = "shivang";
		String sak = "sakshi";
		String out = AdvSubstring.replace(inp, pat, sak);
		System.out.println(out);
	}
}
