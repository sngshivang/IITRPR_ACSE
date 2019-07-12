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
	
	protected String parpreproc(String inp)
	{
		int len = inp.length();
		System.out.println("$TEX$T");
		return inp;
	}
	protected String removehash(String inp, Map <String, String> mp)
	{
		int len = inp.length();
		System.out.println("$TEX$T");
		String out = "hello";
		for (int i=0;i<10;i++)
		{
			System.out.println(i);
		}
		try {
			System.out.println("Hello");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return out;
	}
}