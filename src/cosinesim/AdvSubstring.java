/*KMP Algorithm code has been used from GeeksForGeeks.org*/
package cosinesim;

public class AdvSubstring {
	static int trlen;
	static String inpo;
	static String torepc;
	static int found;
	public static String replace(String inp, String ori, String torep)
	{
		inp = " "+inp+" ";
		trlen = ori.length();
		inpo = inp;
		torepc = torep;
		AdvSubstring adv = new AdvSubstring();
		adv.KMPSearch(ori, inp, false, false, false);
		inpo = inpo.substring(1, inpo.length()-1);
		return inpo;
		
	}
	public static String replaceFirst(String inp, String ori, String torep)
	{
		inp = " "+inp+" ";
		trlen = ori.length();
		inpo = inp;
		torepc = torep;
		AdvSubstring adv = new AdvSubstring();
		adv.KMPSearch(ori, inp, true, false, false);
		inpo = inpo.substring(1, inpo.length()-1);
		return inpo;
	}
	public static String nidreplaceFirst(String inp, String ori, String torep)
	{
		trlen = ori.length();
		inpo = inp;
		torepc = torep;
		AdvSubstring adv = new AdvSubstring();
		adv.KMPSearch(ori, inp, true, false, true);
		return inpo;
	}
	void KMPSearch(String pat, String txt, boolean trip, boolean trip2, boolean nid) //Used from GeeksForGeeks.org
    { 
		String temp2,temp1;
        int M = pat.length(),beg,end; 
        int N = txt.length(); 
  
        // create lps[] that will hold the longest 
        // prefix suffix values for pattern 
        int lps[] = new int[M]; 
        int j = 0; // index for pat[] 
  
        // Preprocess the pattern (calculate lps[] 
        // array) 
        computeLPSArray(pat, M, lps); 
  
        int i = 0; // index for txt[] 
        while (i < N) { 
            if (pat.charAt(j) == txt.charAt(i)) { 
                j++; 
                i++; 
            } 
            if (j == M) { 
                beg = i-j;
                end = beg+trlen;
                if (!nid&&this.check(txt, beg, end))
                {
                	temp1 = Character.toString(txt.charAt(beg-1))+pat+Character.toString(txt.charAt(end));
                	temp2 = Character.toString(txt.charAt(beg-1))+torepc+Character.toString(txt.charAt(end));
                	if (!trip2&&!trip) 
                		inpo = inpo.replace(temp1, temp2);
                	else if (trip&&!trip2)
                	{
                		inpo = this.replacebychar(inpo, beg, end);
                		break;
                	}
                	else if (trip2) 
                	{
                		found=beg;
                		break;
                	}
                }
                else if (nid)
                {
                	inpo = this.replacebychar(inpo, beg, end);
                	break;
                }
                j = lps[j - 1]; 
            } 

            else if (i < N && pat.charAt(j) != txt.charAt(i)) { 
                // Do not match lps[0..lps[j-1]] characters, 
                // they will match anyway 
                if (j != 0) 
                    j = lps[j - 1]; 
                else
                    i = i + 1; 
            } 
        } 
    } 
  
    void computeLPSArray(String pat, int M, int lps[]) //Used from GeeksFromGeeks
    { 
        // length of the previous longest prefix suffix 
        int len = 0; 
        int i = 1; 
        lps[0] = 0; // lps[0] is always 0 
  
        // the loop calculates lps[i] for i = 1 to M-1 
        while (i < M) { 
            if (pat.charAt(i) == pat.charAt(len)) { 
                len++; 
                lps[i] = len; 
                i++; 
            } 
            else // (pat[i] != pat[len]) 
            { 
                // This is tricky. Consider the example. 
                // AAACAAAA and i = 7. The idea is similar 
                // to search step. 
                if (len != 0) { 
                    len = lps[len - 1]; 
  
                    // Also, note that we do not increment 
                    // i here 
                } 
                else // if (len == 0) 
                { 
                    lps[i] = len; 
                    i++; 
                } 
            } 
        } 
    } 
    protected static int find(String inp, String ori)
    {
    	found = -1;
    	inp = " "+inp+" ";
		trlen = ori.length();
		inpo = inp;;
		AdvSubstring adv = new AdvSubstring();
		adv.KMPSearch(ori, inp, true, true, false);
		return found;
    }
    protected boolean check(String txt, int beg, int end)
    {
    	boolean trip =false;
    	if (!Character.isJavaIdentifierPart(txt.charAt(beg-1))&&!Character.isJavaIdentifierPart(txt.charAt(end)))
    	{
    		if (!(txt.charAt(beg-1)=='.')) //&&txt.charAt(end)=='.')) this change is temporary
    			trip = true;
    	}
    	return trip;
    }	
    protected String replacebychar(String inp, int st, int en)
    {
     	int len = inp.length();
    	String p1 = inp.substring(0, st);
    	String p2 = inp.substring(en, len);
    	inp = p1+torepc+p2;
    	return inp;
    }
}
