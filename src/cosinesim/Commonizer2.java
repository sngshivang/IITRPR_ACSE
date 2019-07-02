/* The input code must be compiled and ensured that it is syntactically correct before this program
 * can be used. DO NOT USE IT ON INCORRECT SYNTAX as this will lead to unpredictable and possibly
 * erroneous output. This program does not have any tolerance to errors. 
 */
package cosinesim;

import java.util.*;

public class Commonizer2 {
	Map <String, String> lcrep;
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
	protected static String equalsuniv(String inp, String pref, String flstr)
	{
		Map <String, String> locmp = new HashMap<>();
	    if (Structuring.mptv.get(pref)!=null)
			locmp = Structuring.mptv.get(pref);
		String vrhsh = pref+"_"+"#v";
		String org=inp,part,out,ky,lval;
		Commonizer2 cm = new Commonizer2();
		char temp;
		boolean trip,skip,trip2;
		inp = cm.checkset(inp, flstr, pref);
		int len, ind,i=0,j;
			do {
				skip =false;trip=false; trip2= false;
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
						if (ind>-1&&org.charAt(ind)==32)
							ind--;
						if (ind>-1&&Commonizer2.stoppoints(org.charAt(ind)))
							trip2=true;
						if (!trip&&!part.equals(""))
						{
							if (trip2)
							{
								Structuring.pendvar.add(part);
							}
							else {
							out = vrhsh+String.valueOf(cm.vrcnt);
							cm.vrcnt++;
							inp = AdvSubstring.replace(inp, part, out);
							}
						}
					}
					org = org.substring(j+1, len);
					//System.out.println(ind);
				}while (ind!=-1);
			for (Map.Entry<String,String> entry : locmp.entrySet())
			{
				ky = entry.getKey();
				lval = entry.getValue(); 
				//System.out.println(ky+" "+lval);
				inp = AdvSubstring.replace(inp, ky, lval);
			}
			//locmp.clear();
		return inp;
	}
	protected String checkset(String inp, String flstr, String pref)
	{
		this.locresolve(flstr);
		Iterator<String> it = Structuring.pendvar.iterator();
		Vector<String> todel = new Vector<>();
		String var, vrhsh = pref+"_"+"#v", out, rcv, rcp;
		int pos,str,i;
		while (it.hasNext())
		{
			var = it.next();
			//System.out.println(var);
			while ((pos=AdvSubstring.find(flstr, var))!=-1)
			{
				//System.out.println(var);
				str = pos-1;
				pos-=2;
				if (pos>-1&&flstr.charAt(pos)==32)
					pos--;
				if (pos>-1&&!stoppoints(flstr.charAt(pos)))
				{
					out = vrhsh+String.valueOf(this.vrcnt);
					this.vrcnt++;
					rcv = this.refill(flstr, var ,out);
					//System.out.println(rcv);
					rcp = AdvSubstring.replace(rcv, var, out);
					flstr = AdvSubstring.replace(flstr, var, out);
					//System.out.println(rcp);
					inp = inp.replace(rcv, rcp);
					todel.add(var);
					break;
				}
				else 
				{
					//System.out.println("exe");
					flstr = flstr.substring(str+1, flstr.length());
				}
			}
			
		}
		for (i=0;i<todel.size();i++)
			Structuring.pendvar.remove(todel.get(i));
		return inp;
	}
	protected void locresolve(String inp)
	{
		lcrep = new HashMap<>();
		//System.out.println(inp);
		int ind,i,len;char ch;String num,fnd;
		do
		{
			num="";
			ind = inp.indexOf("$IGNORE");
			len = inp.length();
			for (i=ind+7;i<len;i++)
			{
				ch = inp.charAt(i);
				if (ch>47&&ch<58)
					num+=String.valueOf(inp.charAt(i));
				else
					break;
			}
			num = "$IGNORE"+num+"$";
			if (Structuring.blmp.get(num)!=null)
			{
				fnd = Structuring.blmp.get(num);
				lcrep.put(num, fnd);
				inp = inp.replace(num, fnd);
			}
			
		}while (ind!=-1);
	}
	protected String refill(String inp, String var, String out)
	{
		int ind,i,len;char ch;String num,fnd, tmp;
		do
		{
			num="";
			ind = inp.indexOf("$IGNORE");
			len = inp.length();
			for (i=ind+7;i<len;i++)
			{
				ch = inp.charAt(i);
				if (ch>47&&ch<58)
					num+=String.valueOf(inp.charAt(i));
				else
					break;
			}
			num = "$IGNORE"+num+"$";
			if (this.lcrep.get(num)!=null)
			{
				fnd = this.lcrep.get(num);
				inp = inp.replace(num, fnd);
				tmp = lcrep.get(num);
				tmp = AdvSubstring.replace(tmp, var, out);
				lcrep.put(num, tmp);
			}
			
		}while (ind!=-1);
		return inp;
	}
	protected static boolean stoppoints(char inp)
	{
		boolean trip = false;
		if (inp=='{'||inp=='}'||inp=='('||inp==')'||inp==';'||inp=='$')
			trip = true;
		return trip;
	}
}
