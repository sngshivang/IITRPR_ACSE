/*File is bundled in cosinesim. Created by Shivang for IITRPR/ACSE
 * 
 */
package cosinesim;

public class KeywordCheck {
	private static String words[] = {"abstract", "continue", "for", "new", "switch",
		"assert", "default", "goto", "package", "synchronized",
		"boolean", "do", "if", "private", "this",
		"break", "double", "implements", "protected", "throw",
		"byte", "else", "import", "public", "throws",
		"case", "enum", "instanceof", "return", "transient",
		"catch", "extends", "int", "short", "try",
		"char", "final", "interface", "static", "void",
		"class", "finally", "long", "strictfp", "volatile",
		"const", "float", "native", "super", "while"};
	
	/* There are total 50 keywords that cannot be used as an identifier and has been reserved by the 
	 * compiler. Some of the words are reserved for future use as of 13-06-2019(IND-FORMAT).
	 */
	@SuppressWarnings("unused")
	public static boolean chk(String tochk)
	{
		boolean trip = false;
		for (int i=0;i<50;i++)
		{
			if (tochk.equals(words[i]))
				trip = true;
		}
		return trip;
	}
	

}
