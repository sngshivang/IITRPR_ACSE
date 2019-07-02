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

public class commonizer {
	static Vector<String> txtdat;
	static Set <String> pendvar;
	String hash = "#c", phsh = "#p", univ, mhsh = "#m", vrhsh = "#v";;
	static Map <String, String> adds,blmp,clmp,reschar;
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
		
		
		st = new Stack<>();
		clmp = new HashMap<>();
		mptv = new HashMap<>();
		reschar = new HashMap<>();
		blmp = new HashMap<>();
		pendvar = new HashSet<>();
		
		}
		
	}
}