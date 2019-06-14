package cosinesim;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class commonizer {
	
	
	int clcnt=0;
	static FileWriter fw; 
	public static void main(String args[])
	{
		File fl = new File("/home/shivang/Desktop/example.java");
		File out = new File("/home/shivang/Desktop/outputfl.java");
		try {
		fw = new FileWriter(out);
		BufferedReader br = new BufferedReader(new FileReader(fl));
		String ln,snd;
		int i,lp=0,rp=0;
		while ((ln = br.readLine())!=null)
		{
			int len = ln.length();
			for (i = 0; i < len; i++ )
			{
				if (ln.charAt(i)==';')
				{
					rp = i;
					snd = ln.substring(lp, rp);
					lp = i + 1;
				}
			}
		}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	private void inspector(String inp)
	{
		int len = inp.length();
		try {
	    if (KMPSearch("import",inp) == -1 && KMPSearch("package", inp) == -1)
	    {
	    	if (KMPSearch("class",inp)!= -1)
	    	{
	    		clcnt++;
	    		fw.write("class cl"+String.valueOf(clcnt));
	    		
	    	}
	    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private int KMPSearch(String pat, String txt) 
    { 
        int M = pat.length();
        int res = -1;
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
            	res = i - j;
                j = lps[j - 1];
                break;
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
        return res;
    } 
  
    void computeLPSArray(String pat, int M, int lps[]) 
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
