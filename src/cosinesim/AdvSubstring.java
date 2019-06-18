/*KMP Algorithm code has been used from GeeksForGeeks.org*/
package cosinesim;

public class AdvSubstring {
	static int trlen;
	static String inpo;
	static String torepc;
	public static String replace(String inp, String ori, String torep)
	{
		inp = " "+inp+" ";
		trlen = ori.length();
		inpo = inp;
		torepc = torep;
		AdvSubstring adv = new AdvSubstring();
		adv.KMPSearch(ori, inp);
		inpo = inpo.substring(1, inpo.length()-1);
		return inpo;
		
	}
	void KMPSearch(String pat, String txt) //Used from GeeksForGeeks.org
    { 
		String temp1,temp2;
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
                if (!Character.isJavaIdentifierPart(txt.charAt(beg-1))&&!Character.isJavaIdentifierPart(txt.charAt(end)))
                {
                	temp1 = Character.toString(txt.charAt(beg-1))+pat+Character.toString(txt.charAt(end));
                	temp2 = Character.toString(txt.charAt(beg-1))+torepc+Character.toString(txt.charAt(end));
                	inpo = inpo.replace(temp1, temp2);
                }
                j = lps[j - 1]; 
            } 
  
            // mismatch after j matches 
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

}