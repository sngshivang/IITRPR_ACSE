/*There was some problem with the AdvancedSubstring algo. Please check
Parameters where array may be used. Requires attention.
Text replacement algorithm must rectify the issues with escape sequences
WARNING- Development still under BETA- Output might be unintended or completely off the mark. */ 
package cosinesim;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Vector;
import java.util.HashMap;
import java.util.HashSet;

public class Structuring {
	static Vector<String> txtdat;
	static Set <String> pendvar;
	String hash = "#c", phsh = "#p", univ, mhsh = "#m", vrhsh = "#v";;
	static Map <String, String> blmp,clmp,reschar;
	static Map <String, Map <String,String>> mptv;
	static Stack <String> st;
	int cls=0, pcnt=0, icnt=0, mcnt=0,vrcnt=0, nfree;
	static Commonizer2 cm;
	public static void main(String args[])
	{
		cm = new Commonizer2();
		boolean trip =true;int len;
		FileInputStream fl;
		Structuring str = new Structuring();
		if (args.length!=2 && args.length!=3)
		{
			str.printinfo();
		}
		else {
		txtdat = new Vector<String>();
		String prog="", pathspl[]= {"$INV","$INV"};
		try {
		fl = new FileInputStream(args[0]);
		BufferedInputStream bfs = new BufferedInputStream(fl);
		pathspl = str.inpfilename(args[0]);
		byte bt[] = new byte[8192];
		while ((len = bfs.read(bt))!=-1)
		prog+=new String(bt, 0, len, "UTF-8");
		bfs.close();
		}
		catch(Exception e)
		{
			trip = false;
			System.out.println("The specified input filename is not found. Please check the file path.");
		}
		
		st = new Stack<>();
		clmp = new HashMap<>();
		mptv = new HashMap<>();
		reschar = new HashMap<>();
		blmp = new HashMap<>();
		pendvar = new HashSet<>();
		System.out.println("Processing code.........");
		prog = str2ret(prog);
		if (args[1].equals("CON") && trip)
		{
		System.out.println("-------Code starts here------");
		System.out.print(prog);
		System.out.println("\n-------Code Ends here--------");
		}
		
		else if (args[1].equals("FILE") && trip)
		{
			if (args.length==2){
				pathspl[0] = pathspl[0].substring(0, pathspl[0].length()-5);
				pathspl[0] = pathspl[1]+pathspl[0]+"_nrml.java";
			}
			else if (args.length==3)
				{
				pathspl[0] = pathspl[0].substring(0, pathspl[0].length()-5);
				pathspl[0] = args[2]+pathspl[0]+"_nrml.java";
				}
			try {
				FileOutputStream fl1 = new FileOutputStream(pathspl[0]);
				BufferedOutputStream bfis = new BufferedOutputStream(fl1);
				System.out.println("Writing output to file.........");
				for (int i=0;i<prog.length();i++)
					{
					bfis.write(prog.charAt(i));
					}
				System.out.println("Code successfully normalized.\nOutput File: "+pathspl[0]);
				bfis.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Cannot create the output file in the directory where the source file is located.\nPlease check your permissions or add your own directory as a third argument");
			}
		}
		
		}
		
	}
	public static String str2ret(String inp)
	{
		txtdat = new Vector<String>();
		st = new Stack<>();
		clmp = new HashMap<>();
		mptv = new HashMap<>();
		blmp = new HashMap<>();
		Structuring st = new Structuring();
		try {
			return st.single_opers(inp);
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("NORMALIZATION FAILED!\nThe program failed to normalize the give code. This may be due to an erroneous code. Please make sure your source code is in Java and can be compiled.");
			System.out.println("If that fails, there may be an issue with the BETA version of this software your are using.\nPlease report the issue to the developer on the program's github page");
			System.exit(1);
			return "ERR";
		}
	}
	public String structures(String inp, String pref)
	{
		int len,i,ind,ind1,ind2,k,cnt=10,bc,bo;
		String sstr,sstr2,inv = "$IGNORE",block,inpc=inp,temp[],sstr3;
		cnt--;
		do
		{
			//System.out.println(inpc);
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
					temp = this.classnormal(inpc.substring(i+1, ind+1), pref);
					pref = temp[1];
					//System.out.println(inpc);
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
					sstr2 = tmpresolve(sstr);
					sstr2 = Commonizer2.equalsuniv(sstr2, pref, sstr);
					//System.out.println(sstr2);
					inv = "$IGNORE"+String.valueOf(icnt)+"$";
					icnt++;
					st.push(inv);
					blmp.put(inv, sstr2);
					inpc = inpc.replace(sstr, inv) ;
					//System.out.println(inpc);
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
						bc = i;
					while (i>-1&&sstr.charAt(i)!='(')
						i--;
					bo = i;
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
						sstr2 = sstr.substring(bo, bc+1);
						sstr3 = this.parnormal(sstr2, 0, pref);
						inpc = AdvSubstring.replaceFirst(inpc, temp[1], temp[2]);
						inpc = AdvSubstring.nidreplaceFirst(inpc, sstr2, sstr3);
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
	protected static String tmpresolve(String inp)
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
			num = "$IGNORE"+num+"$";
			if (blmp.get(num)!=null)
			{
				fnd = blmp.get(num);
				inp = inp.replace(num, fnd);
			}
			
		}while (ind!=-1);
		return inp;
	}
	protected void printinfo()
	{
		System.out.println("------Java Source Code Normalizer------");
		System.out.println("Missing Arguments: Program expects at least two arguments");
		System.out.println("Usage: JavaNormalizer.jar [Source File] [Output type] [Destination File (o)])");
		System.out.println("\nOptions\n");
		System.out.println("Source: Valid input filepath");
		System.out.println("Output type : CON/FILE\n\tCON prints output on the console. Omit third argument when this option is used");
		System.out.println("\tFILE prints output to the specified destination file.\n\tThrid argument if skipped will create the output file in the same directory as the source file.");
		System.out.println("Destination File: The destination file path when second argument is FILE(Optional)");
	}
	protected String represvchar(String inp)
	{
		String key, val;
		reschar.put("$RESV0$", "'{'");
		reschar.put("$RESV1$", "'}'");
		reschar.put("$RESV2$", "'('");
		reschar.put("$RESV3$", "')'");
		reschar.put("$RESV4$", "';'");
		for (Map.Entry<String, String> tmp : reschar.entrySet())
		{
			key = tmp.getKey();
			val = tmp.getValue();
			inp = inp.replace(val, key);
		}
		return inp;
	}
	protected String redoresvchar(String inp)
	{
		String key, val;
		for (Map.Entry<String, String> tmp : reschar.entrySet())
		{
			key = tmp.getKey();
			val = tmp.getValue();
			inp = inp.replace(key, val);
		}
		return inp;
	}
	protected String[] inpfilename(String inp)
	{
		int len = inp.length(),i;
		String out[] = new String[2];
		out[0]="";
		for (i=len-1;i>-1;i--)
		{
			if(inp.charAt(i)!='/'&&inp.charAt(i)!='\\')
					out[0] = String.valueOf(inp.charAt(i))+out[0];
			else 
				break;
					
		}
		out[1] = inp.substring(0, i+1);
		return out;
	}
	protected String single_opers(String prog)
	{
		univ = this.textrepo(prog);
		univ = this.rmcmt(univ);
		univ = this.removenlines(univ);
		univ = this.rmextrasp(univ);
		univ = this.trimmer(univ);
		univ = this.represvchar(univ);
		String test = this.structures(univ,"");
		/*for (String key: blmp.keySet()) {
			System.out.println("key : " + key);
			System.out.println("value : " + blmp.get(key));
		}*/
		test = tmpresolve(test);
		test = this.removehash(test);
		test = this.redoresvchar(test);
		test = this.redotext(test);
		test = this.addnewline(test);
		//this.printout();
		this.cleanup();
		return test;
	}
	protected void cleanup()
	{
		mptv.clear();
		blmp.clear();
		clmp.clear();
		reschar.clear();
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
		String deft,phr;
		char apos = 34;
		do {
			ind = inp.indexOf(apos);
			if (ind!=-1)
			{
				deft = "$TEX$T";
				eind = inp.indexOf(apos,ind+1);
				deft+=String.valueOf(nfree);
				phr = inp.substring(ind,eind+1);
				txtdat.add(phr);
				nfree++;
				inp = inp.replace(phr, deft);
				
			}
		}while(ind!=-1);
		return inp;
	}
	protected String redotext(String inp)
	{
		String pat = "$TEX$T",tmp, out, cp = inp, inp1;
		int  len, ind, ky, eind,rlen;
		do {
			len = inp.length();
			tmp = "";
			ind = inp.indexOf(pat);
			if (ind!=-1)
			{
				eind  = ind + 6;
				while (eind<len&&(inp.charAt(eind)>47&&inp.charAt(eind)<58))
				{
					tmp+=String.valueOf(inp.charAt(eind));
					eind++;
				}
				//System.out.println(tmp);
				ky = Integer.valueOf(tmp);
				out = txtdat.get(ky);
				rlen = out.length();
				inp1 = inp;
				inp = AdvSubstring.nidreplaceFirst(inp,pat+tmp, out);
				cp = cp.replace(inp1, inp);
				inp = inp.substring(ind+rlen,inp.length());
				
			}
		}while (ind!=-1);
		return cp;
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
	protected String addnewline(String inp)
	{
		int len = inp.length(),i;
		String out="";
		for (i=0;i<len;i++)
		{
			out+=String.valueOf(inp.charAt(i));
			if (inp.charAt(i)==';')
				out+="\n";
			else if (i>1&&inp.charAt(i)=='{'&&!(inp.charAt(i-1)=='='||inp.charAt(i-2)=='='))
				out+="\n";
		}
		return out;
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
							if (ind>-1&&org.charAt(ind)=='#')
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
							if (ind>-1&&org.charAt(ind)=='#')
								{
								trip = true;
								ind--;
								break;
								}
						}
						if (ind<0||org.charAt(ind)!=32)
							trip2=true;
						if (!trip&&!trip2&&!KeywordCheck.chk(part))
						{
							out = this.mhsh+String.valueOf(mcnt);
								//metmp.put(pref, true);
								pref = pref + "_" + out;
								//this.parnormal(org, j, pref);
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
	protected String parnormal(String inp, int l, String pref)
	{
		//System.out.println(pref);
		Map <String, String> mp1 = new HashMap<>();
		int sze,r=0,i,pcnt=0;
		for (i=l+1;i<inp.length();i++)
			if (inp.charAt(i)==')')
			{
				r = i;
				break;
			}
		String itok,out, org = inp.substring(l+1,r), tmp;
		org = this.parpreproc(org);
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
			out = this.phsh+String.valueOf(pcnt);
			tmp = pref + "_" + out;
			pcnt++;
			mp1.put(vt2.get(sze-1), tmp);
			inp = AdvSubstring.replace(inp, vt2.get(sze-1), tmp);
			vt2.clear();
		}
		if (!mp1.isEmpty()) 
		mptv.put(pref, mp1);
		//System.out.println(mptv);
		return inp;
	}
	protected String parpreproc(String inp)
	{
		int len = inp.length(),ind,i;String tmp;
		do
		{
			ind = inp.indexOf('[');
			if (ind!=-1)
			{
				i = ind+1;
				while (inp.charAt(i)!=']')
					i++;
				tmp = inp.substring(ind, i+1);
				inp = inp.replace(tmp, "");
			}
		}while (ind!=-1);
		return inp;
	}
	protected String removehash(String inp)
	{
		int len = inp.length(),i;
		String out="";
		for (i=0;i<len;i++)
		{
			if (inp.charAt(i)!='#')
				out+=String.valueOf(inp.charAt(i));
		}
		return out;
	}
	protected boolean stoppoints(char inp)
	{
		boolean trip = false;
		if (inp=='{'||inp=='}'||inp=='('||inp==')'||inp==';'||inp=='$')
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
	protected void printout()
	{
		Iterator<String> it = pendvar.iterator();
		while (it.hasNext())
			System.out.println(it.next());
	}
}
