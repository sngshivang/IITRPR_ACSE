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
		//System.out.println(inp);
		Set <String> pvt = new HashSet<>();
		Map <String, String> locmp = new HashMap<>();
	    if (Structuring.mptv.get(pref)!=null)
			locmp = Structuring.mptv.get(pref);
		String vrhsh = pref+"_"+"#v";
		String org,part,out,ky,lval,sstr,sstr2;
		Commonizer2 cm = new Commonizer2();
		inp = cm.checkset_rev(inp, flstr, pref);
		char temp;
		boolean trip,skip,trip2;
		org = inp;
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
						//System.out.println(part);
						if (!trip&&!part.equals(""))
						{
							if (trip2)
							{
								//System.out.println(part);
								pvt.add(part);
							}
							else {
							out = vrhsh+String.valueOf(cm.vrcnt);
							cm.vrcnt++;
							sstr = org.substring(ind+1, len);
							sstr2 = AdvSubstring.replace(sstr, part, out);
							//System.out.println(sstr+"\n");
							inp = AdvSubstring.nidreplaceFirst(inp, sstr, sstr2);
							//inp = inp.replace(sstr, sstr2);
							//System.out.println(inp);
							//System.out.println(AdvSubstring.find(inp, sstr));
							org = AdvSubstring.nidreplaceFirst(org, sstr, sstr2);
							len = org.length();
							j = org.indexOf('=');
							}
						}
					}
					org = org.substring(j+1, len);
					//System.out.println(ind);
				}while (ind!=-1);
			//System.out.println(locmp);
			//inp = cm.splpar(inp, pref);
			for (Map.Entry<String,String> entry : locmp.entrySet())
			{
				ky = entry.getKey();
				lval = entry.getValue();
				//System.out.println(inp);
				//System.out.println(pref+" "+ky+" "+inp);
				inp = AdvSubstring.replace(inp, ky, lval);
				//System.out.println("new\n"+inp);
			}
			Structuring.pendvar.put(pref, pvt);
			inp = cm.checkset_rev(inp, flstr, pref);
			//locmp.clear();
		return inp;
	}
	protected String splpar(String inp, String pref)
	{
		int l,r;
		Structuring st = new Structuring();
		String cp = inp, proc,oproc;
		do
		{
			if ((l=inp.indexOf('('))!=-1)
			{
				r = l+1;
				if ((r=inp.indexOf(')',l))!=-1)
				{
					//System.out.println(inp);
					proc = inp.substring(l,r+1);
					st.phsh="#v";
					oproc = st.parnormal(proc, 0, vrcnt, pref);
					cp = AdvSubstring.nidreplaceFirst(cp, proc, oproc);
					vrcnt = st.vrcnt;
					//System.out.println(vrcnt);
				}
				inp = inp.substring(r+1,inp.length());
			}
		}while (l!=-1);
		return cp;
	}
	protected String checkset(String inp, String flstr, String pref)
	{
		//System.out.println(flstr);
		this.locresolve(flstr);
		Iterator<String> it;
		Set <String> tst;
		Vector<String> todel = new Vector<>();
		String var, vrhsh = pref+"_"+"#v", out, rcv, rcp, ky;
		int pos,str,i;
		for (Map.Entry<String, Set<String>> mp: Structuring.pendvar.entrySet())
		{
			ky = mp.getKey();
			//System.out.println(pref);
			//System.out.println(ky);
			if (pref.equals(ky.substring(0, pref.length()))) {
				 tst= mp.getValue();
				 it = tst.iterator();
					while (it.hasNext())
					{
						var = it.next();
						//System.out.println(ky);
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
			}
		}
		//for (i=0;i<todel.size();i++)
			//Structuring.pendvar.remove(todel.get(i));
		return inp;
	}
	protected String checkset_rev(String inp, String flstr, String pref)
	{
		//System.out.println(flstr);
		//this.locresolve(flstr);
		Iterator<String> it;
		Set <String> tst;
		Vector<String> todel = new Vector<>();
		String var, vrhsh = pref+"_"+"#v", out, ky;
		int pos,str,i;
		for (Map.Entry<String, Set<String>> mp: Structuring.pendvar.entrySet())
		{
			ky = mp.getKey();
			if (ky.length()>pref.length()-1) {
			if (pref.equals(ky.substring(0, pref.length()))) {
				 tst= mp.getValue();
				 it = tst.iterator();
					while (it.hasNext())
					{
						var = it.next();
						//System.out.println(ky);
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
								inp = AdvSubstring.replace(inp, var, out);
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
					tst.remove(todel.get(i));
					todel.clear();
					Structuring.pendvar.put(ky, tst);
			}
			}
		}
		//for (i=0;i<todel.size();i++)
			//Structuring.pendvar.remove(todel.get(i));
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
