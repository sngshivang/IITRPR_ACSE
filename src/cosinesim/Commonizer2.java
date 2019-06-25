/* The input code must be compiled and ensured that it is syntactically correct before this program
 * can be used. DO NOT USE IT ON INCORRECT SYNTAX as this will lead to unpredictable and possibly
 * erroneous output. This program does not have any tolerance to errors. 
 */
package cosinesim;

import java.util.*;

public class Commonizer2 {
	static Vector<String> txtdat;
	String hash = "#c";
	String mhsh = "#m";
	String upref="";
	int cls=0;
	int mcnt=0,vrcnt=0;
	static int nfree;

	protected int startid(String inp, int ind)
	{
		int len = inp.length();
		while (ind<len&&inp.charAt(ind)==32)
			ind++;
		return ind;
	}
	protected int endid(String inp, int ind)
	{
		int len = inp.length(),asc;
		while (ind<len)
		{
			asc = inp.charAt(ind);
			if (asc<len&&((asc>64&&asc<91)||(asc>96&&asc<123)||(asc>47&&asc<58)||asc==95||asc==36))
			{
				ind++;
			}
			else 
				break;
		}
		return ind;
	}
	protected static String equalsuniv(String inp, String pref)
	{
		//System.out.println(inp);
		String vrhsh = pref+"_"+"#v";
		String org=inp,part,out;
		Commonizer2 cm = new Commonizer2();
		char temp;
		boolean trip =false,skip;
		int len, ind,i=0,j;
			do {
				skip =false;
					ind = org.indexOf('=');
					len = org.length();j=ind;
					if (ind!=-1)
					{
						if (org.charAt(ind+1)=='=')
						{
							j++;
							skip = true;
						}
						else if (ind!=0)
						{
							temp = org.charAt(ind-1);
							if (temp=='+'||temp=='-'||temp=='*'||temp=='/'||temp=='!'||temp=='<'
									||temp=='>')
								skip = true;
						}
						else if (ind==0)
							skip = true;
					}
					if (!skip&&ind!=-1)
					{
						ind--;
						if (ind>-1&&org.charAt(ind)==32)
							ind--;
						if (ind>-1&&org.charAt(ind)==']')
							{
							if (ind>-1&&org.charAt(ind-1)==32)
								ind-=3;
							else
								ind-=2;
							}
						if (ind>-1&&org.charAt(ind)==32)
							ind--;
						part = "";
						while (ind>-1&&Character.isJavaIdentifierPart(org.charAt(ind)))
						{
							part = Character.toString(org.charAt(ind))+part;
							ind--;
							if (ind>-1&&org.charAt(ind)=='#')
							trip = true;
						}
						if (!trip&&!part.equals(""))
						{
							out = vrhsh+String.valueOf(cm.vrcnt);
							cm.vrcnt++;
							inp = AdvSubstring.replace(inp, part, out);
						}
					}
					org = org.substring(j+1, len);
					//System.out.println(ind);
				}while (ind!=-1);
		return inp;
	}	
}
