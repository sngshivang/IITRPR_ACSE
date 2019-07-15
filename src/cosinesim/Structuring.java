/*WARNING- Development still under BETA- Output might be unintended or completely off the mark.
 Version 0.1.1 */
package cosinesim;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Vector;
import java.util.HashMap;

public class Structuring {
	static Vector<String> txtdat;
	static Map <String, Set<String>> pendvar;
	String hash = "#c", phsh = "#p", univ, mhsh = "#m", vrhsh = "#v";
	static Map <String, String> blmp,reschar,inimp;
	static Map <String, Map <String,String>> mptv,mtmp,clmp;
	static Map <String, Vector<Integer>> vmp;
	static Stack <String> st;
	int pcnt=0, icnt=0, mcnt=0,vrcnt=0, nfree;
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
		vmp = new HashMap<>();
		reschar = new HashMap<>();
		blmp = new HashMap<>();
		inimp = new HashMap<>();
		mtmp = new HashMap<>();
		pendvar = new HashMap<>();
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
			//e.printStackTrace();
			System.out.println("NORMALIZATION FAILED!\nThe program failed to normalize the give code. This may be due to an erroneous code. Please make sure your source code is in Java and can be compiled.");
			System.out.println("If that fails, there may be an issue with the BETA version of this software your are using.\nPlease report the issue to the developer on the program's github page");
			System.exit(1);
			return "ERR";
		}
	}
	public String structures(String inp, String pref)
	{
		
		int len,i,ind,ind1,ind2,bc,bo;
		String sstr,sstr2,inv = "$IGNORE",block,inpc=inp,temp[],sstr3;
		do
		{
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
					}
				else if (i>-1)
					{
					while (i>-1&&!this.stoppoints(inpc.charAt(i)))
					i--;
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
				if (ind2!=-1&&(ind1==-1||ind1>ind2))
				{
					block = sstr.substring(0, ind2);
					sstr = "{"+block+"}";
					sstr2 = tmpresolve(sstr);
					sstr2 = Commonizer2.equalsuniv(sstr2, pref, sstr);
					inv = "$IGNORE"+String.valueOf(icnt)+"$";
					icnt++;
					st.push(inv);
					blmp.put(inv, sstr2);
					inpc = inpc.replace(sstr, inv) ;
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
					temp = this.metnormal(sstr2, pref);
					pref = temp[2];
					
					if (!temp[0].equals("$INV"))
					{
						sstr2 = sstr.substring(bo, bc+1);
						sstr3 = this.parnormal(sstr2, 0, 0, pref);
						inpc = AdvSubstring.replaceFirst(inpc, temp[1], temp[2]);
						inpc = AdvSubstring.nidreplaceFirst(inpc, sstr2, sstr3);
					}
						
					}
					else if (i>-1){
					while (i>-1&&!this.stoppoints(sstr.charAt(i)))
						i--;
					temp = this.classnormal(sstr.substring(i+1, ind1+1), pref);
					pref = temp[1];
					if (!temp[0].equals("$INV"))
						inpc = AdvSubstring.replaceFirst(inpc, temp[0], temp[1]);
					}
					sstr = sstr.substring(ind1, ind2+1);
					inpc = inpc.replace(sstr,this.structures(sstr, pref));
					pref = "";
				}
			}
		}while(ind!=-1);
		return inpc;
	}
	public String restructures(String inp, String pref)
	{
		int len,i,ind,ind1,ind2,k,com=0;
		String sstr,sstr2,inv = "$IGNORE",block,inpc=inp,temp[];
		do
		{
			len = inpc.length();
			ind = inpc.indexOf('{');
			if (ind!=-1)
			{
				i=ind;
				if (i>0&&inpc.charAt(i-1)==32)
					i-=2;
				else 
					i--;
				if (i>-1&&inpc.charAt(i)==')');
				else if (i>-1)
					{
					while (i>-1&&!this.stoppoints(inpc.charAt(i)))
					i--;
					temp = this.classnormal(inpc.substring(i+1, ind+1), pref);
					pref = temp[1];
					}
				sstr = inpc.substring(ind+1,len);
				ind1 = sstr.indexOf('{');
				ind2 = sstr.indexOf('}');
				if (ind2!=-1&&(ind1==-1||ind1>ind2))
				{
					block = sstr.substring(0, ind2);
					sstr = "{"+block+"}";
					sstr2 = tmpresolve(sstr);
					k = pref.length()-1;
					while (k>-1)
					{
						if (!Character.isDigit(pref.charAt(k)))
						{
							if (pref.charAt(k)=='c')
								com = 2;
							else if (pref.charAt(k)=='m')
								com = 1;
							break;
						}
						k--;
					}
					if (com==1||com==2)
					{
						sstr2 = this.classmet(sstr2, pref);
						
					if (com==2)
					{
						if (mtmp.get(pref)!=null)
						{
							sstr2 = this.renmet(sstr2, pref);
						}
					}
					}
					inv = "$IGNORE"+String.valueOf(icnt)+"$";
					icnt++;
					blmp.put(inv, sstr2);
					inpc = inpc.replace(sstr, inv) ;
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
					temp = this.metnormal(sstr2, pref);
					pref = temp[2];
					}
					else if (i>-1){
					while (i>-1&&!this.stoppoints(sstr.charAt(i)))
						i--;
					temp = this.classnormal(sstr.substring(i+1, ind1+1), pref);
					pref = temp[1];
					}
					sstr = sstr.substring(ind1, ind2+1);
					inpc = inpc.replace(sstr,this.restructures(sstr, pref));
					pref = "";
				}
			}
		}while(ind!=-1);
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
		System.out.println("\nExample:\t java -jar normalizer.jar /home/user/Desktop/test.java FILE /home/user/Downloads/");
		System.out.println("\nVersion 0.1.1 BETA");
		System.out.println("\nSource on https://github.com/sngshivang/IITRPR_ACSE. Follow for updates");
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
		test = tmpresolve(test);
		icnt = 0;
		test = this.restructures(test, "");
		test = tmpresolve(test);
		test = this.classmet(test, "null");
		test = this.removehash(test);
		test = this.redoresvchar(test);
		test = this.redotext(test);
		test = this.addnewline(test);
		this.cleanup();
		return test;
	}
	protected void cleanup()
	{
		mptv.clear();
		blmp.clear();
		clmp.clear();
		reschar.clear();
		vmp.clear();
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
		int ind, eind;
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
		String org=inp,part,out,keywrd,send[]= {"$INV","$INV"},cpref = pref;
		Map<String,String> cpt;
		if (clmp.get(pref)!=null)
			cpt = clmp.get(pref);
		else 
			cpt = new HashMap<>();
		if (cpref.equals(""))
			cpref = "null";
		Vector <Integer> cvt;
		boolean trip =false;
		int ind;
			//do {
					ind = org.indexOf('{');
					if (ind!=-1)
					{
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
								ind = inp.indexOf("#c0");
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
							if (vmp.get(cpref)==null) {
								cvt = new Vector<>();
								cvt.add(0);
								cvt.add(0);
							}
							else 
								cvt = vmp.get(pref);
							out = this.hash+String.valueOf(cvt.get(0));
							if (pref.equals(""))
								pref = out;
							else 
							pref = pref + "_" + out;
							send[0]=part;
							cpt.put(part, pref);
							clmp.put(cpref, cpt);
							cvt.set(0, cvt.get(0)+1);
							vmp.put(cpref, cvt);
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
						
					}
					send[1] = pref;
					return send;
	}
	protected String classmet(String sstr2, String pref)
	{
		Map <String,String> tmp;
		StringTokenizer st;
		String ky, val, trim, sstr3 = sstr2;
		int fnd,l,r;
		if (clmp.get(pref)!=null)
		{
			tmp = clmp.get(pref);
			for (Map.Entry<String, String> fmp:tmp.entrySet()) {
				ky = fmp.getKey();
				val = fmp.getValue();
				fnd = AdvSubstring.find(sstr3, ky);l= r=fnd;
				while (fnd!=-1) {
				while (l>-1&&!this.stoppoints(sstr3.charAt(l)))
					l--;
				while (r<sstr3.length()&&!this.stoppoints(sstr3.charAt(r)))
					r++;
				trim = sstr3.substring(l, r+1);
				st = new StringTokenizer(trim);
				trim = st.nextToken("=");
				st = new StringTokenizer(trim);
				while (st.hasMoreTokens())
					trim = st.nextToken();
				sstr3 = sstr3.substring(r, sstr3.length());
				fnd = AdvSubstring.find(sstr3, ky);l= r=fnd;
				inimp.put(trim, val);
				}
				sstr2 = AdvSubstring.replace(sstr2, ky, val);
				}
			
		}
		return sstr2;
	}
	protected String renmet(String inp, String pref)
	{
		StringTokenizer st;
		int ind,rt,len,l=-1,lr,tcp,pl;
		boolean trip;
		Map <String, String> tmp;
		String met="", cp=inp, toch, temp;
		do
		{
			trip =true;
			ind = inp.indexOf('(');
			len = inp.length();
			if (ind!=-1)
			{
				ind--;
				if (ind>-1&&inp.charAt(ind)==32)
					ind--;
				rt = ind+1;
				tcp = ind;
				while (ind>-1&&!this.stoppoints(inp.charAt(ind))&&inp.charAt(ind)!='.'&&inp.charAt(ind)!=32)
				{
					if (inp.charAt(ind)=='#')
						{trip = false;
						break;
						}
					ind--;
				}
				//tcp = ind;
				/*if (trip)
				{
					while (tcp>-1&&(inp.charAt(tcp)==32||inp.charAt(tcp)=='.'))
						tcp--;
					if (tcp>-1&&inp.charAt(tcp)==')')
						{while (tcp>-1&&inp.charAt(tcp)!='(')
							tcp--;
						}
					if (tcp>-1&&inp.charAt(tcp)==32)
						tcp--;
					l = tcp;
					while (l>-1&&(Character.isJavaIdentifierPart(inp.charAt(l))||inp.charAt(l)=='#'))
						l--;
						
				}*/
				while (tcp>-1&&!this.stoppoints(inp.charAt(tcp)))
					tcp--;
				if (ind>-1&&trip&&tcp>-1)
				{
					met = inp.substring(tcp+1, rt);
					st = new StringTokenizer(met);
					met=st.nextToken(".");
					st = new StringTokenizer(met);
					while (st.hasMoreTokens())
						met = st.nextToken();
					l = met.indexOf('(');
					lr = met.indexOf(')',l);
					if (l!=-1&&lr!=-1)
					met = met.replace(met.substring(l, lr+1), "");
					
					/*if (l>-1&&tcp>-1)
					met  = inp.substring(l+1, tcp+1);
					System.out.println(met);*/
					temp = inp.substring(ind+1, rt);
					if (met.equals("this"))
					{
						pl = pref.length()-1;
						while (pref.charAt(pl)!='c')
							pl--;
						pref=  pref.substring(0, pl+2);
						tmp = mtmp.get(pref);
						//System.out.println(pref+" "+temp);
							
					}
					else
					tmp = mtmp.get(inimp.get(met));
					if (tmp!=null) {
					toch = tmp.get(temp);
					if (toch!=null)
					{
						toch=inp.charAt(ind)+toch;
						temp = inp.charAt(ind)+temp;
						cp = AdvSubstring.nidreplaceFirst(cp,temp, toch);
					}
					}
					
				}
				inp = inp.substring(rt+1, len);
			}
			
		}while(ind!=-1);
		return cp;
	}
	protected String[] metnormal(String inp, String pref)
	{
		String send[] = {"$INV","$INV", "$INV"};
		Map <String,String> mpt;
		Vector <Integer> cvt;
		if (mtmp.get(pref)!=null)
			mpt = mtmp.get(pref);
		else 
			mpt = new HashMap<>();
		String org=inp,part,out,cpref = pref;
		boolean trip =false, trip2 = false;
		int ind;
				trip = false;
					ind = org.indexOf('(');
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
								ind = inp.indexOf("#c0");
								ind--;
								break;
								}
						}
						if (ind<0||org.charAt(ind)!=32)
							trip2=true;
						if (!trip&&!trip2&&!KeywordCheck.chk(part))
						{
							if (vmp.get(cpref)==null) {
								cvt = new Vector<>();
								cvt.add(0);
								cvt.add(0);
							}
							else 
								cvt = vmp.get(pref);
							out = this.mhsh+String.valueOf(cvt.get(1));
							pref = pref + "_" + out;
							mpt.put(part, pref);
							mtmp.put(cpref, mpt);
							cvt.set(1, cvt.get(1)+1);
							vmp.put(cpref, cvt);
							send[0] = out;
							send[1] = part;
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
	protected String parnormal(String inp, int l, int pcnt, String pref)
	{
		Map <String, String> mp1;
		if (mptv.get(pref)==null)
		mp1 = new HashMap<>();
		else
			mp1 = mptv.get(pref);
		int sze,r=0,i;
		for (i=l+1;i<inp.length();i++)
			if (inp.charAt(i)==')')
			{
				r = i;
				break;
			}
		String itok,out, org = inp.substring(l+1,r), tmp;
		org = this.parpreproc(org);
		StringTokenizer sti = new StringTokenizer(org),st ,st2;
		if (sti.hasMoreTokens())
			org = sti.nextToken(";");
		st = new StringTokenizer(org);
		Vector <String> vt = new Vector<>(),vt2 = new Vector<>();
		while (st.hasMoreTokens())
		vt.add(st.nextToken(","));
		Iterator<String> it = vt.iterator();
		while (it.hasNext())
		{
			itok = (String)it.next();
			st2 = new StringTokenizer(itok);
			if (st2.hasMoreTokens())
				itok = st2.nextToken(":");
			st2 = new StringTokenizer(itok);
			if (st2.hasMoreTokens())
				itok = st2.nextToken("=");
			st2 = new StringTokenizer(itok);
			while (st2.hasMoreTokens())
			{
				vt2.add(st2.nextToken());
			}
			sze = vt2.size();
			if (this.isValidKeyword(vt2.get(sze-1))) {
			out = this.phsh+String.valueOf(pcnt);
			tmp = pref + "_" + out;
			pcnt++;
			mp1.put(vt2.get(sze-1), tmp);
			inp = AdvSubstring.replace(inp, vt2.get(sze-1), tmp);
			}
			vt2.clear();
		}
		if (!mp1.isEmpty()) 
		mptv.put(pref, mp1);
		vrcnt = pcnt;
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
				while (i<len&&inp.charAt(i)!=']')
					i++;
				if (i<len) {
				tmp = inp.substring(ind, i+1);
				inp = inp.replace(tmp, "");
				}
				else
					break;
			}
		}while (ind!=-1);
		len = inp.length();
		do
		{
			ind = inp.indexOf('<');
			if (ind!=-1)
			{
				i = ind+1;
				while (i<len&&inp.charAt(i)!='>')
					i++;
				if (i<len) {
				tmp = inp.substring(ind, i+1);
				inp = inp.replace(tmp, "");
				}
				else 
					break;
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
	protected boolean isValidKeyword(String inp)
	{
		boolean trip =true;
		int len = inp.length();
		for (int i=0;i<len;i++)
		{
			if (!Character.isJavaIdentifierPart(inp.charAt(i))) {
				trip = false;
				break;
			}
		}
		return trip;
	}
}
