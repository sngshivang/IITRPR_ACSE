/* The input code must be compiled and ensured that it is syntactically correct before this program
 * can be used. DO NOT USE IT ON INCORRECT SYNTAX as this will lead to unpredictable and possibly
 * erroneous output. This program does not have any tolerance to errors. 
 */
package cosinesim;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class Commonizer2 {
	static Vector<String> txtdat;
	String hash = "#c";
	String mhsh = "#m";
	String vrhsh = "#v";
	int cls=0;
	int mcnt=0,vrcnt=0;
	static int nfree;
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
		Commonizer2 cm = new Commonizer2();
		prog = cm.operations(prog);
		System.out.print(prog);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	private String operations(String prog)
	{
		prog = this.textrepo(prog);
		prog = this.rmcmt(prog);
		prog = this.removenlines(prog);
		prog = this.rmextrasp(prog);
		prog = this.trimmer(prog);
		prog = this.classmod(prog);
		prog = this.methmod(prog);
		prog = this.renprimvar(prog);
		return prog;
	}
	private String removenlines(String inp)
	{
		inp = inp.replaceAll("\n", "");
		return inp;
				
	} 
	private String rmcmt(String inp)
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
	private String textrepo(String inp)
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
	private int startid(String inp, int ind)
	{
		int len = inp.length();
		while (ind<len&&inp.charAt(ind)==32)
			ind++;
		return ind;
	}
	private int endid(String inp, int ind)
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
	private String classmod(String inp)
	{
		int len,i,ind, brcnt=0, end;
		String org = inp;
		while (true) {
		len = org.length();
		ind = org.indexOf("class");
		if (ind!=-1) {
		int strt = this.startid(org, ind+5);
		end = this.endid(org, strt);
		//System.out.println(org.charAt(strt));
		String idfr = org.substring(strt, end);
		//System.out.println(idfr);
		String nnme = this.hash + String.valueOf(cls);
		cls++;
		for (i=0;i<end;i++)
		{
			if (org.charAt(i)=='{')
				brcnt++;
			else if (org.charAt(i)=='}')
				brcnt--;
		}
		inp = inp.replace(idfr,nnme);
		org = org.substring(end, len-end);
		}
		else 
			break;
		
		}
		return inp;
	}
	private String mtname(String inp, int pos)
	{
		int i;
		boolean idfn2=true, trip = false;
		int spval = 0;
		String nme = "", typ="";
		String types[] = {"void", "boolean", "int", "String", "float", "double", "Void", "Boolean", "Integer", "Long"
				, "BigInteger", "short", "char", "boolean", "long"};
		while (pos>-1&&this.stoppoints(inp.charAt(pos))!=true)
		{
			if (inp.charAt(pos)==32) {
				if (spval == 1)
					idfn2 = false;
				else if (spval ==2)
					break;
			}
			else if(idfn2) {
				spval = 1;
				if (this.isValidChar(inp.charAt(pos)))
				nme = inp.charAt(pos)+nme;
				
			}
			else {
				spval = 2;
				typ = inp.charAt(pos)+typ;
			}
			pos--;
		}
		for (i=0;i<11;i++)
			if (typ.equals(types[i]))
			{
				trip=true;
				break;
			}
		if (trip)
			return nme;
		else 
			return "#INV";
		
	}
	private String methmod(String inp)
	{
		int len,i,ind, brcnt=0; String nnme,idf;
		String org = inp;
		while (true) {
			len = org.length();
			ind = org.indexOf('(');
			if (ind!=-1) {
			idf = this.mtname(org, --ind);
			if (!idf.equals("#INV")) {
			nnme = this.mhsh + String.valueOf(mcnt);
			inp = inp.replace(idf, nnme);
			mcnt++;
			}
			/*for (i=0;i<end;i++)
			{
				if (org.charAt(i)=='{')
					brcnt++;
				else if (org.charAt(i)=='}')
					brcnt--;
			}*/
			org = org.substring(ind+2, len-(ind+2));
			}
			else 
				break;
			
			}
			return inp;
	}
	private String trimmer(String inp)
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
	private String rmextrasp(String inp)
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
	private String rmchar(String inp, int pos)
	{
		int len = inp.length();
		String out = inp.substring(0, pos) + inp.substring(pos+1, len);
		return out;
	}
	private String renprimvar(String inp)
	{
		String subs,out,org=inp;
		int len, ind,i,j,k;
		String dattyp[]= {"int", "byte", "long", "double", "float", "short", "char", "boolean", "String"};
		for (i=0;i<9;i++)
		{
				do {
					ind = org.indexOf(dattyp[i]);
					len = org.length();
					if (ind!=-1)
					{
						ind+=dattyp[i].length();
						if (org.charAt(ind+1)!='#') {
							for (j=ind;j<len;j++)
								if (org.charAt(j)==';')
									break;
							//System.out.println(org.substring(ind, j+1));
							inp = this.renprimvarENG(inp, org.substring(ind,j+1));
						}
						else 
							j = ind;
						org = org.substring(j+1, len-j-1);
						
					}
				}while (ind!=-1);
		}
		return inp;
	}
	private String renprimvarENG(String inp, String sstr)
	{
		boolean trip =false;
		if (sstr.charAt(0)==32)
			trip=true;
		sstr = sstr.replace(" ", "");
		String subs, out;
		int i=0,coma,end = sstr.length();
		if (sstr.charAt(0)=='[')
			{i+=2;
			trip = true;
			}
		coma = i;
		if (trip) {
			//System.out.println(sstr);
		for (;i<end;i++)
		{
			if (sstr.charAt(i)=='['||sstr.charAt(i)==']')
				{
				subs = sstr.substring(coma, i);
				out = this.vrhsh+String.valueOf(vrcnt);
				vrcnt++;
				//System.out.println(subs+" "+out);
				inp = inp.replace(subs, out);
				coma=i+2;
				i++;
				}
			else if (sstr.charAt(i)==',')
			{
				subs = sstr.substring(coma, i);
				out = this.vrhsh+String.valueOf(vrcnt);
				vrcnt++;
				inp = inp.replace(subs, out);
				coma=i+1;
			}
			else if (sstr.charAt(i)=='=')
			{
				if (coma<i) {
				subs = sstr.substring(coma, i);
				out = this.vrhsh+String.valueOf(vrcnt);
				vrcnt++;
				inp = inp.replace(subs, out);
				}
				if (sstr.charAt(i+1)=='{')
					while (sstr.charAt(i)!='}')
						i++;
				while (i<end)
				{
					if (sstr.charAt(i)==','||sstr.charAt(i)==';')
						{
						coma = i+1;
						break;
						}
					i++;
				}
						
			}
			if (sstr.charAt(i)==';')
			{
				if (coma<i) {
				subs = sstr.substring(coma, i);
				out = this.vrhsh+String.valueOf(vrcnt);
				vrcnt++;
				inp = inp.replace(subs, out);
				}
				break;
			}
				
		}
		}
		return inp;
		
	}
	private String renamecompvar(String inp)
	{
		return inp;
	}
	private boolean isValidChar(char inp)
	{
		boolean trip = false;
		if ((inp>64&&inp<91)||(inp>96&&inp<123)||(inp>47&&inp<58)||inp=='$'||inp=='_')
			trip=true;
		return trip;
	}
	private boolean stoppoints(char inp)
	{
		boolean trip = false;
		if (inp=='{'||inp=='}'||inp=='('||inp==')'||inp==';')
			trip = true;
		return trip;
	}

}
