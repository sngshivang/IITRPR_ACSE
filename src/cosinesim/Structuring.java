//There was some problem with the AdvancedSubstring algo. Please check
//Parameters where array may be used. Requires attention.
package cosinesim;

import java.io.BufferedInputStream;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.FileInputStream;
import java.util.Vector;
import java.util.HashMap;

public class Structuring {
	static Vector<String> txtdat;
	String hash = "#c", phsh = "#p", univ;
	String mhsh = "#m";
	static Map <String, Boolean> metmp;
	static Map <String, String> blmp,clmp;
	String vrhsh = "#v";
	static Stack <String> st;
	int cls=0, pcnt=0, icnt=0;
	int mcnt=0,vrcnt=0;
	static int nfree;
	static Commonizer2 cm;
	public static void main(String args[])
	{
		int len;txtdat = new Vector<String>();
		nfree = 0;
		String prog="";
		try {
		FileInputStream fl = new FileInputStream("/home/shivang/Desktop/example.java");
		File out = new File("/home/shivang/Desktop/output.java");
		BufferedInputStream bfs = new BufferedInputStream(fl);
		byte bt[] = new byte[8192];
		while ((len = bfs.read(bt))!=-1)
		{
			prog+=new String(bt);
		}
		bfs.close();
		st = new Stack<>();
		cm = new Commonizer2();
		clmp = new HashMap<>();
		metmp = new HashMap<>();
		blmp = new HashMap<>();
		Structuring st = new Structuring();
		prog = st.single_opers(prog);
		System.out.print(prog);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public String structures(String inp, String pref)
	{
		int len,i,ind,ind1,ind2,k,cnt=10;
		String sstr,sstr2,inv = "$IGNORE",block,inpc=inp,temp[];
		cnt--;
		do
		{
			//System.out.println("PREF:"+ pref);
			len = inpc.length();
			ind = inpc.indexOf('{');
			if (ind!=-1)
			{
				i=ind;
				if (i>0&&inpc.charAt(i-1)==32)
					i-=2;
				else 
					i--;
				if (i>-1&&inpc.charAt(i)==')')
					{
					while (i>-1&&inpc.charAt(i)!='(')
						i--;
					i--;
					while (i>-1&&!this.stoppoints(inpc.charAt(i)))
						i--;
					sstr = inpc.substring(i+1, ind+1);
					System.out.println("bla blAa");
					//pref = this.metnormal(sstr, pref);
					}
				else if (i>-1)
					{
					while (i>-1&&!this.stoppoints(inpc.charAt(i)))
					i--;
					//System.out.println(pref);
					temp = this.classnormal(inpc.substring(i+1, ind+1), pref);
					pref = temp[1];
					if (!temp[0].equals("$INV")) {
						inpc = AdvSubstring.replaceFirst(inpc, temp[0], temp[1]);
						pref="";
						continue;
					}
					}
				sstr = inpc.substring(ind+1,len);
				ind1 = sstr.indexOf('{');
				ind2 = sstr.indexOf('}');
				//System.out.println(ind1+" "+ind2);
				if (ind2!=-1&&(ind1==-1||ind1>ind2))
				{
					//System.out.println("PREF:"+ pref);
					block = sstr.substring(0, ind2);
					sstr = "{"+block+"}";
					sstr2 = this.tmpresolve(sstr);
					sstr2 = Commonizer2.equalsuniv(sstr2, pref);
					//System.out.println(sstr2);
					inv = "$IGNORE"+String.valueOf(icnt);
					icnt++;
					st.push(inv);
					blmp.put(inv, sstr2);
					inpc = inpc.replace(sstr, inv) ;
					//System.out.println(inpc);
					//inpc = inpc.replace(sstr,inv);
					//System.out.println(inpc);
				}
				else if (ind2==-1)
					break;
				else
				{
					i = ind1;
					if (i>0&&sstr.charAt(i-1)==32)
						i-=2;
					else 
						i--;
					if (i>-1&&sstr.charAt(i)==')')
					{
					while (i>-1&&sstr.charAt(i)!='(')
						i--;
					i--;
					while (i>-1&&!this.stoppoints(sstr.charAt(i)))
						i--;
					sstr2 = sstr.substring(i+1, ind1+1);
					//System.out.println(sstr2);
					temp = this.metnormal(sstr2, pref);
					pref = temp[2];
					//System.out.println(temp[1]+" "+temp[2]);
					if (!temp[0].equals("$INV"))
					{
						inpc = AdvSubstring.replaceFirst(inpc, temp[1], temp[2]);
					}
					}
					else if (i>-1){
					while (i>-1&&!this.stoppoints(sstr.charAt(i)))
						i--;
					//System.out.println(sstr.substring(i+1, ind1+1));
					temp = this.classnormal(sstr.substring(i+1, ind1+1), pref);
					pref = temp[1];
					if (!temp[0].equals("$INV"))
						inpc = AdvSubstring.replaceFirst(inpc, temp[0], temp[1]);
					}
					sstr = sstr.substring(ind1, ind2+1);
					inpc = inpc.replace(sstr,this.structures(sstr, pref));
					//System.out.println(inpc);
					pref = "";
				}
			}
		}while(ind!=-1);
		//System.out.println("Exit");
		return inpc;
	}
	protected String tmpresolve(String inp)
	{
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
			num = "$IGNORE"+num;
			if (blmp.get(num)!=null)
			{
				fnd = blmp.get(num);
				inp = inp.replace(num, fnd);
			}
			
		}while (ind!=-1);
		return inp;
	}
	protected String single_opers(String prog)
	{
		univ = this.textrepo(prog);
		univ = this.rmcmt(univ);
		univ = this.removenlines(univ);
		univ = this.rmextrasp(univ);
		univ = this.trimmer(univ);
		String test = this.structures(univ,"");
		/*for (String key: blmp.keySet()) {
			System.out.println("key : " + key);
			System.out.println("value : " + blmp.get(key));
		}*/
		return univ;
	}
	protected String removenlines(String inp)
	{
		inp = inp.replaceAll("\n", "");
		return inp;
				
	} 
	protected String rmcmt(String inp)
	{
		int ind,eind;
		do {
			ind = inp.indexOf("//");
			if (ind!=-1) {
			eind = inp.indexOf("\n",ind+2);
			inp = inp.replace(inp.substring(ind, eind),"");
			}
		}while (ind!=-1);
		do {
			ind = inp.indexOf("/*");
			if (ind!=-1)
			{
				eind = inp.indexOf("*/",ind+2);
				inp = inp.replace(inp.substring(ind, eind+2), "");
			}
		}while (ind!=-1);
		return inp;
	}
	protected String textrepo(String inp)
	{
		int ind, eind, esc;
		String deft = "$TEX$T",phr;
		char apos = 34;
		do {
			ind = inp.indexOf(apos);
			if (ind!=-1)
			{
				eind = inp.indexOf(apos,ind+1);
				deft+=String.valueOf(nfree);
				phr = inp.substring(ind,eind+1);
				txtdat.add(phr);
				nfree++;
				inp = inp.replace(phr, deft);
				//esc = inp.indexOf("\"",ind);
				deft = "$TEX$T";
				
			}
		}while(ind!=-1);
		return inp;
	}
	protected String trimmer(String inp)
	{
		String kyd[]= {"package", "import"};
		int len = inp.length(), ind, i, j;
		String subs;
		for (i=0;i<2;i++)
		{
			do {
				ind = inp.indexOf(kyd[i]);
				if (ind!=-1)
				{
					for (j=ind;j<len;j++)
						if (inp.charAt(j)==';')
							break;			
					subs = inp.substring(ind, j+1);
					inp = inp.replace(subs, "");
					
				}
			}while (ind!=-1);
		}
	return inp;
	}
	protected String rmextrasp(String inp)
	{
		int len = inp.length(),i;
		if (inp.charAt(0)==9)
			inp = this.rmchar(inp, 0);
		for (i=1;i<len;)
		{
			if (inp.charAt(i)==32&&inp.charAt(i-1)==32)
				{
				inp = this.rmchar(inp, i);
				len--;
				}
			else if (inp.charAt(i)==9)
				{
				inp = this.rmchar(inp, i);
				len--;
				}
			else 
				i++;
		}
		return inp;
		
	}
	protected String[] classnormal(String inp, String pref)
	{
		//System.out.println(pref);
		String org=inp,part,out,keywrd,send[]= {"$INV","$INV"};
		boolean trip =false;
		int len, ind,i,j;
			//do {
					ind = org.indexOf('{');
					len = org.length();j=ind;
					if (ind!=-1)
					{
						//System.out.println(org.substring(0,ind));
						ind--;
						if (ind>-1&&org.charAt(ind)==32)
							ind--;
						part = "";keywrd="";
						while (ind>-1&&Character.isJavaIdentifierPart(org.charAt(ind)))
						{
							part = Character.toString(org.charAt(ind))+part;
							ind--;
							if (org.charAt(ind)=='#')
								{trip = true;
								ind--;
								break;
								}
						}
						ind--;
						while (ind>-1&&this.isValidKeywordChar(org.charAt(ind)))
						{
							keywrd = org.charAt(ind)+keywrd;
							ind--;
						}
						if (keywrd.equals("class")||keywrd.equals("interface")||keywrd.equals("enum"))
						{
						if (!trip)
						{
							out = this.hash+String.valueOf(cls);
							if (pref.equals(""))
								pref = out;
							else 
							pref = pref + "_" + out;
							send[0]=part;
							clmp.put(part, pref);
							cls++;
							//univ = AdvSubstring.replace(univ, part, pref);
						}
						else 
						{
							part = "#"+part;
							if (pref.equals(""))
								pref = part;
							else 
							pref = pref + "_" + part;
						}
						}
						//org = org.substring(j+1, len-j-1);
						
					}
					send[1] = pref;
					return send;
	}
	protected String[] metnormal(String inp, String pref)
	{
		String send[] = {"$INV","$INV", "$INV"};
		String org=inp,part,out;
		boolean trip =false, trip2 = false;
		int len, ind,i,j;
				trip = false;
					ind = org.indexOf('(');
					len = org.length();j=ind;
					if (ind!=-1)
					{
						ind--;
						if (ind>-1&&org.charAt(ind)==32)
							ind--;
						part = "";
						while (ind>-1&&Character.isJavaIdentifierPart(org.charAt(ind)))
						{
							part = Character.toString(org.charAt(ind))+part;
							ind--;
							if (org.charAt(ind)=='#')
								{
								trip = true;
								ind--;
								break;
								}
						}
						if (org.charAt(ind)!=32)
							trip2=true;
						if (!trip&&!trip2)
						{
							out = this.mhsh+String.valueOf(mcnt);
								this.parnormal(org, j);
								//metmp.put(pref, true);
								pref = pref + "_" + out;
								mcnt++;
								send[0] = out;
								send[1] = part;
								//univ = AdvSubstring.replace(univ, part, pref);
						}
						else if (trip)
						{
							part = "#"+part;
							if (pref.equals(""))
								pref = part;
							else 
							pref = pref + "_" + part;
						}
					}
					send[2] =  pref;
				return send;
	}
	protected void parnormal(String inp, int l)
	{
		int sze,r=0,i;
		for (i=l+1;i<inp.length();i++)
			if (inp.charAt(i)==')')
			{
				r = i;
				break;
			}
		String itok,out, org = inp.substring(l+1,r);
		StringTokenizer st = new StringTokenizer(org),st2;
		Vector <String> vt = new Vector<>(),vt2 = new Vector<>();
		while (st.hasMoreTokens())
		vt.add(st.nextToken(","));
		Iterator<String> it = vt.iterator();
		while (it.hasNext())
		{
			itok = (String)it.next();
			st2 = new StringTokenizer(itok);
			while (st2.hasMoreTokens())
			{
				vt2.add(st2.nextToken());
			}
			sze = vt2.size();
			//System.out.println(sze);
			if (sze==2)
			{
				out = this.phsh+String.valueOf(pcnt);
				pcnt++;
				univ = AdvSubstring.replace(univ, vt2.get(1), out);
			}
			vt2.clear();
		}
	}
	protected boolean stoppoints(char inp)
	{
		boolean trip = false;
		if (inp=='{'||inp=='}'||inp=='('||inp==')'||inp==';')
			trip = true;
		return trip;
	}
	protected String rmchar(String inp, int pos)
	{
		int len = inp.length();
		String out = inp.substring(0, pos) + inp.substring(pos+1, len);
		return out;
	}
	protected boolean isValidChar(char inp)
	{
		boolean trip = false;
		if ((inp>64&&inp<91)||(inp>96&&inp<123)||(inp>47&&inp<58)||inp=='$'||inp=='_')
			trip=true;
		return trip;
	}
	protected boolean isValidKeywordChar(char inp)
	{
		boolean trip = false;
		if ((inp>64&&inp<91)||(inp>96&&inp<123))
			trip=true;
		return trip;
	}
	protected void infotransfer()
	{
		
	}
}
