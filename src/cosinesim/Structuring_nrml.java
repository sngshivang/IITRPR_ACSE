 public class c0 {
static Vector<String> txtdat;
static Set <String> c0_v0;
String c0_v5 = "#c", c0_v6 = "#p", univ, c0_v7 = "#m", c0_v8 = "#v";
;
static Map <String, String> blmp,clmp,reschar;
static Map <String, Map <String,String>> mptv;
static Stack <String> st;
int c0_v9=0, c0_v10=0, c0_v11=0, c0_v12=0,c0_v13=0, nfree;
static Commonizer2 cm;
public static void c0_m0(String c0_m0_p0[]){
cm = new Commonizer2();
boolean c0_m0_v0 =true;
int len;
FileInputStream fl;
Structuring c0_m0_v4 = new Structuring();
if (c0_m0_p0.length!=2 && c0_m0_p0.length!=3){
c0_m0_v4.printinfo();
}else {
txtdat = new Vector<String>();
String c0_m0_v1="", c0_m0_v2[]= {"$INV","$INV"};
try {
fl = new FileInputStream(c0_m0_p0[0]);
BufferedInputStream c0_m0_v0 = new BufferedInputStream(fl);
c0_m0_v2 = c0_m0_v4.inpfilename(c0_m0_p0[0]);
byte c0_m0_v1[] = new byte[8192];
while ((len = c0_m0_v0.read(c0_m0_v1))!=-1)c0_m0_v1+=new String(c0_m0_v1, 0, len, "UTF-8");
c0_m0_v0.close();
}catch(Exception e){
c0_m0_v0 = false;
System.out.println("The specified input filename is not found. Please check the file path.");
}st = new Stack<>();
clmp = new HashMap<>();
mptv = new HashMap<>();
reschar = new HashMap<>();
blmp = new HashMap<>();
c0_v0 = new HashSet<>();
c0_m0_v1 = str2ret(c0_m0_v1);
if (c0_m0_p0[1].equals("CON") && c0_m0_v0)System.out.print(c0_m0_v1);
else if (c0_m0_p0[1].equals("FILE") && c0_m0_v0){
if (c0_m0_p0.length==2){
c0_m0_v2[0] = c0_m0_v0[0].substring(0, c0_m0_v0[0].length()-5);
c0_m0_v0[0] = c0_m0_v0[1]+c0_m0_v0[0]+"_nrml.java";
}else if (c0_m0_p0.length==3){
c0_m0_v2[0] = c0_m0_v2[0].substring(0, c0_m0_v2[0].length()-5);
c0_m0_v2[0] = c0_m0_p0[2]+c0_m0_v2[0]+"_nrml.java";
}try {
FileOutputStream c0_m0_v0 = new FileOutputStream(c0_m0_v2[0]);
BufferedOutputStream c0_m0_v1 = new BufferedOutputStream(c0_m0_v0);
for (int c0_m0_v2=0;
c0_m0_v2<c0_m0_v1.length();
c0_m0_v2++){
c0_m0_v1.write(c0_m0_v1.charAt(c0_m0_v2));
}c0_m0_v1.close();
}catch(Exception e){
e.printStackTrace();
System.out.println("Cannot create the output file in the directory where the source file is located.\nPlease check your permissions or add your own directory as a third argument");
}}}}public static String c0_m1(String c0_m21_p0){
txtdat = new Vector<String>();
c0_m1_v0 = new Stack<>();
clmp = new HashMap<>();
mptv = new HashMap<>();
blmp = new HashMap<>();
Structuring c0_m1_v0 = new Structuring();
try {
return c0_m1_v0.single_opers(c0_m1_p0);
}catch(Exception e){
e.printStackTrace();
System.out.println("NORMALIZATION FAILED!\nThe program failed to normalize the give code. This may be due to an erroneous code. Please make sure your source code is in Java and can be compiled.");
System.out.println("If that fails, there may be an issue with the BETA version of this software your are using.\nPlease report the issue to the developer on the program's github page");
return "ERR";
}}public String c0_m2(String c0_m18_p0, String c0_m18_p1){
int len,i,ind,ind1,ind2,k,c0_m2_v12=10,c0_m2_v0,bo;
String sstr,sstr2,c0_m2_v13 = "$IGNORE",block,c0_m2_v14=c0_m2_p0,temp[],sstr3;
c0_m2_v12--;
do{
len = c0_m2_v0.length();
ind = c0_m2_v0.indexOf('{
');
if (ind!=-1){
i=ind;
if (i>0&&c0_m2_v0.charAt(i-1)==32)i-=2;
else i--;
if (i>-1&&c0_m2_v0.charAt(i)==')'){
while (i>-1&&c0_m2_v0.charAt(i)!='(')i--;
i--;
while (i>-1&&!this.stoppoints(c0_m2_v0.charAt(i)))i--;
sstr = c0_m2_v0.substring(i+1, ind+1);
System.out.println("bla blAa");
}else if (i>-1){
while (i>-1&&!this.stoppoints(c0_m2_v0.charAt(i)))i--;
temp = this.classnormal(c0_m2_v0.substring(i+1, ind+1), c0_m2_v0);
c0_m2_v0 = temp[1];
if (!temp[0].equals("$INV")) {
c0_m2_v0 = AdvSubstring.replaceFirst(c0_m2_v0, temp[0], temp[1]);
c0_m2_p1="";
continue;
}}sstr = c0_m2_v0.substring(ind+1,len);
ind1 = sstr.indexOf('{
');
ind2 = sstr.indexOf('}');
if (ind2!=-1&&(ind1==-1||ind1>ind2)){
block = sstr.substring(0, ind2);
sstr = "{
"+block+"}";
sstr2 = tmpresolve(sstr);
sstr2 = Commonizer2.equalsuniv(sstr2, c0_m2_p1, sstr);
c0_m2_v13 = "$IGNORE"+String.valueOf(c0_v11)+"$";
c0_v11++;
st.push(c0_m2_v13);
blmp.put(c0_m2_v13, sstr2);
c0_m2_v0 = c0_m2_v0.replace(sstr, c0_m2_v13) ;
}else if (ind2==-1)break;
else{
i = ind1;
if (i>0&&sstr.charAt(i-1)==32)i-=2;
else i--;
if (i>-1&&sstr.charAt(i)==')'){
c0_m2_v0 = i;
while (i>-1&&c0_m2_v0.charAt(i)!='(')i--;
bo = i;
i--;
while (i>-1&&!this.stoppoints(c0_m2_v0.charAt(i)))i--;
sstr2 = c0_m2_v0.substring(i+1, ind1+1);
temp = this.metnormal(sstr2, c0_m2_p1);
c0_m2_p1 = temp[2];
if (!temp[0].equals("$INV")){
sstr2 = c0_m2_v0.substring(bo, c0_m2_v0+1);
sstr3 = this.parnormal(sstr2, 0, c0_m2_p1 ,c0_m2_v0[2]);
c0_m2_v0 = AdvSubstring.replaceFirst(c0_m2_v0, c0_m2_v0[1], c0_m2_v0[2]);
c0_m2_v0 = c0_m2_v1.replace(sstr2, sstr3);
}}else if (i>-1){
while (i>-1&&!this.stoppoints(sstr.charAt(i)))i--;
temp = this.classnormal(sstr.substring(i+1, ind1+1), c0_m2_p1);
c0_m2_p1 = c0_m2_v0[1];
if (!c0_m2_v0[0].equals("$INV"))c0_m2_v0 = AdvSubstring.replaceFirst(c0_m2_v0, c0_m2_v0[0], c0_m2_v0[1]);
}sstr = sstr.substring(ind1, ind2+1);
c0_m2_v0 = c0_m2_v0.replace(sstr,this.structures(sstr, c0_m2_p1));
c0_m2_p1 = "";
}}}while(ind!=-1);
return c0_m2_v14;
}protected static String c0_m3(String c0_m21_p0){
int ind,i,len;
char ch;
String c0_m3_v0,fnd;
do{
c0_m3_v0="";
ind = inp.indexOf("$IGNORE");
len = inp.length();
for (i=c0_m3_v0+7;
i<len;
i++){
ch = inp.charAt(i);
if (ch>47&&ch<58)c0_m3_v0+=String.valueOf(inp.charAt(i));
elsebreak;
}c0_m3_v0 = "$IGNORE"+c0_m3_v0+"$";
if (blmp.get(c0_m3_v0)!=null){
fnd = c0_m3_v0.get(c0_m3_v0);
inp = inp.replace(c0_m3_v0, fnd);
}}while (ind!=-1);
return inp;
}protected void c0_m4(){
System.out.println("------Java Source Code Normalizer------");
System.out.println("Missing Arguments: Program expects at least two arguments");
System.out.println("Usage: JavaNormalizer.jar [Source File] [Output type] [Destination File (o)])");
System.out.println("\nOptions\n");
System.out.println("Source: Valid input filepath");
System.out.println("Output type : CON/FILE\n\tCON prints output on the console. Omit third argument when this option is used");
System.out.println("\tFILE prints output to the specified destination file. Requires third argument");
System.out.println("Destination File: The destination file path when second argument is FILE");
}protected String c0_m5(String c0_m21_p0){
String key, val;
reschar.put("$RESV0$", "'{
'");
reschar.put("$RESV1$", "'}'");
reschar.put("$RESV2$", "'('");
reschar.put("$RESV3$", "')'");
reschar.put("$RESV4$", "';
'");
for (Map.Entry<String, String> tmp : c0_m5_v0.entrySet()){
key = tmp.getKey();
val = tmp.getValue();
inp = c0_m5_v0.replace(val, key);
}return inp;
}protected String c0_m6(String c0_m21_p0){
String c0_m6_v0, val;
for (Map.Entry<String, String> tmp : reschar.entrySet()){
c0_m6_v0 = tmp.getKey();
val = tmp.getValue();
inp = inp.replace(c0_m6_v0, c0_m6_v0);
}return inp;
}protected String[] c0_m7(String c0_m21_p0){
int c0_m7_v0 = inp.length(),i;
String c0_m7_v2[] = new String[2];
c0_m7_v2[0]="";
for (i=c0_m7_v0-1;
i>-1;
i--){
if(inp.charAt(i)!='/'&&c0_m7_v0.charAt(i)!='\\')c0_m7_v2[0] = String.valueOf(c0_m7_v0.charAt(i))+c0_m7_v2[0];
else break;
}c0_m7_v2[1] = inp.substring(0, i+1);
return c0_m7_v2;
}protected String c0_m8(String c0_m8_p0){
univ = this.textrepo(c0_m8_p0);
univ = this.rmcmt(univ);
univ = this.removenlines(univ);
univ = this.rmextrasp(univ);
univ = this.trimmer(univ);
univ = this.represvchar(univ);
String c0_m8_v0 = this.structures(univ,"");
c0_m8_v0 = c0_m3(c0_m8_v0);
c0_m8_v0 = this.removehash(c0_m8_v0);
c0_m8_v0 = this.redoresvchar(c0_m8_v0);
c0_m8_v0 = this.redotext(c0_m8_v0);
c0_m8_v0 = this.addnewline(c0_m8_v0);
this.cleanup();
return c0_m8_v0;
}protected void c0_m9(){
mptv.clear();
blmp.clear();
clmp.clear();
reschar.clear();
}protected String c0_m10(String c0_m21_p0){
inp = inp.replaceAll("\n", "");
return inp;
} protected String c0_m11(String c0_m21_p0){
int c0_m11_v0,eind;
do {
c0_m11_v0 = c0_m11_v0.indexOf("//");
if (c0_m11_v0!=-1) {
eind = c0_m11_v0.indexOf("\n",c0_m11_v0+2);
c0_m11_v0 = c0_m11_v0.replace(c0_m11_v0.substring(c0_m11_v0, eind),"");
}}while (c0_m11_v0!=-1);
do {
c0_m11_v0 = c0_m11_v0.indexOf("/*");
if (c0_m11_v0!=-1){
eind = c0_m11_v0.indexOf("*/",c0_m11_v0+2);
c0_m11_v0 = c0_m11_v0.replace(c0_m11_v0.substring(c0_m11_v0, c0_m11_v0+2), "");
}}while (c0_m11_v0!=-1);
return inp;
}protected String c0_m12(String c0_m21_p0){
int c0_m12_v0, eind, esc;
String deft,phr;
char c0_m12_v5 = 34;
do {
c0_m12_v0 = c0_m12_v0.indexOf(c0_m12_v5);
if (c0_m12_v0!=-1){
deft = "$TEX$T";
eind = c0_m12_v0.indexOf(c0_m12_v5,c0_m12_v0+1);
deft+=String.valueOf(nfree);
phr = c0_m12_v0.substring(c0_m12_v0,eind+1);
txtdat.add(phr);
nfree++;
c0_m12_v0 = c0_m12_v0.replace(phr, deft);
}}while(c0_m12_v0!=-1);
return inp;
}protected String c0_m13(String c0_m21_p0){
String c0_m13_v9 = "$TEX$T",tmp, c0_m13_v0, c0_m13_v10 = inp, inp1;
int len, ind, ky, eind;
do {
len = inp.length();
tmp = "";
ind = c0_m13_v0.indexOf(c0_m13_v9);
if (ind!=-1){
eind = ind + 6;
while (eind<len&&(c0_m13_v0.charAt(eind)>47&&c0_m13_v0.charAt(eind)<58)){
tmp+=String.valueOf(c0_m13_v0.charAt(eind));
eind++;
}ky = Integer.valueOf(tmp);
c0_m13_v0 = c0_m13_v0.get(ky);
inp1 = c0_m13_v0;
c0_m13_v0 = AdvSubstring.replaceFirst(c0_m13_v0,c0_m13_v9+tmp, c0_m13_v0);
c0_m13_v10 = c0_m13_v10.replace(inp1, c0_m13_v0);
c0_m13_v0 = c0_m13_v0.substring(ind+2,c0_m13_v0.length());
}}while (ind!=-1);
return c0_m13_v10;
}protected String c0_m14(String c0_m21_p0){
String c0_m14_v4[]= {"package", "import"};
int c0_m14_v5 = inp.length(), ind, i, j;
String c0_m14_v0;
for (i=0;
i<2;
i++){
do {
ind = c0_m14_v0.indexOf(c0_m14_v4[i]);
if (ind!=-1){
for (j=ind;
j<c0_m14_v0;
j++)if (c0_m14_v0.charAt(j)==';
')break;
c0_m14_v0 = c0_m14_v0.substring(ind, j+1);
c0_m14_v0 = c0_m14_v0.replace(c0_m14_v0, "");
}}while (ind!=-1);
}return inp;
}protected String c0_m15(String c0_m21_p0){
int c0_m15_v1 = c0_m15_v0.length(),i;
if (c0_m15_v0.charAt(0)==9)c0_m15_v0 = this.rmchar(c0_m15_v0, 0);
for (i=1;
i<c0_m15_v1;
){
if (c0_m15_v0.charAt(i)==32&&c0_m15_v0.charAt(i-1)==32){
c0_m15_v0 = this.rmchar(c0_m15_v0, i);
c0_m15_v1--;
}else if (c0_m15_v0.charAt(i)==9){
c0_m15_v0 = this.rmchar(c0_m15_v0, i);
c0_m15_v1--;
}else i++;
}return c0_m15_v0;
}protected String c0_m16(String c0_m21_p0){
int c0_m16_v2 = inp.length(),i;
String c0_m16_v0="";
for (i=0;
i<c0_m16_v2;
i++){
c0_m16_v0+=String.valueOf(inp.charAt(i));
if (inp.charAt(i)==';
')c0_m16_v0+="\n";
else if (i>1&&inp.charAt(i)=='{'&&!(inp.charAt(i-1)=='='||inp.charAt(i-2)=='='))c0_m16_v0+="\n";
}return c0_m16_v0;
}protected String[] c0_m17(String c0_m18_p0, String c0_m18_p1){
String c0_m17_v7=inp,c0_m17_v0,out,keywrd,c0_m17_v8[]= {"$INV","$INV"};
boolean c0_m17_v9 =false;
int len, ind,i,j;
ind = c0_m17_v7.indexOf('{
');
len = c0_m17_v7.length();
j=ind;
if (ind!=-1){
ind--;
if (ind>-1&&c0_m17_v7.charAt(ind)==32)ind--;
c0_m17_v0 = "";
keywrd="";
while (ind>-1&&Character.isJavaIdentifierPart(c0_m17_v7.charAt(ind))){
c0_m17_v0 = Character.toString(c0_m17_v7.charAt(ind))+c0_m17_v0;
ind--;
if (ind>-1&&c0_m17_v7.charAt(ind)==''){
c0_m17_v9 = true;
ind--;
break;
}}ind--;
while (ind>-1&&this.isValidKeywordChar(c0_m17_v7.charAt(ind))){
keywrd = c0_m17_v7.charAt(ind)+keywrd;
ind--;
}if (keywrd.equals("class")||c0_m17_v0.equals("interface")||c0_m17_v0.equals("enum")){
if (!c0_m17_v0){
out = this.c0_v5+String.valueOf(c0_v9);
if (c0_m17_v2.equals(""))c0_m17_v2 = out;
else c0_m17_v2 = c0_m17_v2 + "_" + out;
c0_m17_v8[0]=c0_m17_v0;
clmp.put(c0_m17_v0, c0_m17_v2);
c0_v9++;
}else {
c0_m17_v0 = "#"+c0_m17_v0;
if (c0_m17_v1.equals(""))c0_m17_v1 = c0_m17_v0;
else c0_m17_v0 = c0_m17_v0 + "_" + c0_m17_v0;
}}}c0_m17_v8[1] = pref;
return c0_m17_v8;
}protected String[] c0_m18(String c0_m18_p0, String c0_m18_p1){
String c0_m18_v8[] = {"$INV","$INV", "$INV"};
String c0_m18_v9=inp,c0_m18_v0,out;
boolean c0_m18_v10 =false, c0_m18_v11 = false;
int len, ind,i,j;
c0_m18_v10 = false;
ind = c0_m18_v9.indexOf('(');
len = c0_m18_v9.length();
j=ind;
if (ind!=-1){
ind--;
if (ind>-1&&c0_m18_v9.charAt(ind)==32)ind--;
c0_m18_v0 = "";
while (ind>-1&&Character.isJavaIdentifierPart(c0_m18_v9.charAt(ind))){
c0_m18_v0 = Character.toString(c0_m18_v9.charAt(ind))+c0_m18_v0;
ind--;
if (ind>-1&&c0_m18_v9.charAt(ind)==''){
c0_m18_v10 = true;
ind--;
break;
}}if (ind<0||c0_m18_v9.charAt(ind)!=32)c0_m18_v11=true;
if (!c0_m18_v10&&!c0_m18_v11&&!KeywordCheck.chk(c0_m18_v0)){
out = this.c0_v7+String.valueOf(c0_v12);
pref = pref + "_" + out;
c0_v12++;
c0_m18_v8[0] = out;
c0_m18_v8[1] = c0_m18_v0;
}else if (c0_m18_v10){
c0_m18_v0 = "#"+c0_m18_v0;
if (c0_m18_v1.equals(""))c0_m18_v1 = c0_m18_v0;
else c0_m18_v0 = c0_m18_v0 + "_" + c0_m18_v0;
}}c0_m18_v8[2] = pref;
return c0_m18_v8;
}protected String c0_m19(String c0_m19_p0, int c0_m19_p1, String c0_m19_p2, String c0_m19_p3){
Map <String, String> c0_m19_v2 = new HashMap<>();
int sze,c0_m19_v3=0,i,c0_m19_v4=0;
for (i=c0_m19_p1+1;
i<c0_m19_p0.length();
i++)if (c0_m19_p0.charAt(i)==')'){
c0_m19_v3 = c0_m19_v0;
break;
}String itok,c0_m19_v0, c0_m19_v5 = c0_m19_p0.substring(c0_m19_p1+1,c0_m19_v3), tmp;
c0_m19_v5 = this.parpreproc(c0_m19_v5);
StringTokenizer c0_m19_v6 = new StringTokenizer(c0_m19_v5),st2;
Vector <String> c0_m19_v7 = new Vector<>(),c0_m19_v8 = new Vector<>();
while (c0_m19_v6.hasMoreTokens())c0_m19_v7.add(c0_m19_v6.nextToken(","));
Iterator<String> c0_m19_v9 = c0_m19_v7.iterator();
while (c0_m19_v9.hasNext()){
itok = (String)c0_m19_v9.next();
st2 = new StringTokenizer(itok);
while (st2.hasMoreTokens()){
c0_m19_v8.add(st2.nextToken());
}sze = c0_m19_v8.size();
c0_m19_v0 = this.c0_v6+String.valueOf(c0_m19_v4);
tmp = c0_m19_p2 + "_" + c0_m19_v0;
c0_m19_v4++;
c0_m19_v2.put(c0_m19_v8.get(sze-1), tmp);
c0_m19_p0 = AdvSubstring.replace(c0_m19_p0, c0_m19_v8.get(sze-1), tmp);
c0_m19_v8.clear();
}if (!c0_m19_v2.isEmpty()) mptv.put(c0_m19_p3, c0_m19_v2);
return c0_m19_v1;
}protected String c0_m20(String c0_m21_p0){
int c0_m20_v0 = inp.length(),ind,i;
String tmp;
do{
ind = c0_m20_v0.indexOf('[');
if (ind!=-1){
i = ind+1;
while (c0_m20_v0.charAt(i)!=']')i++;
tmp = c0_m20_v0.substring(ind, i+1);
c0_m20_v0 = c0_m20_v0.replace(tmp, "");
}}while (ind!=-1);
return inp;
}protected String c0_m21(String c0_m21_p0){
int c0_m21_v1 = inp.length(),i;
String c0_m21_v0="";
for (i=0;
i<c0_m21_v1;
i++){
if (inp.charAt(i)!='')c0_m21_v0+=String.valueOf(inp.charAt(i));
}return c0_m21_v0;
}protected boolean c0_m22(char c0_m25_p0){
boolean c0_m22_v0 = false;
if (c0_m22_p0=='{'||c0_m22_p0=='}'||c0_m22_p0=='('||c0_m22_p0==')'||c0_m22_p0==';
'||c0_m22_p0=='$')c0_m22_v0 = true;
return c0_m22_v0;
}protected String c0_m23(String c0_m23_p0, int c0_m23_p1){
int c0_m23_v0 = c0_m23_p0.length();
String c0_m23_v1 = c0_m23_p0.substring(0, c0_m23_p1) + c0_m23_p0.substring(c0_m23_p1+1, c0_m23_v0);
return c0_m23_v1;
}protected boolean c0_m24(char c0_m25_p0){
boolean c0_m24_v0 = false;
if ((inp>64&&inp<91)||(inp>96&&inp<123)||(inp>47&&inp<58)||inp=='$'||inp=='_')c0_m24_v0=true;
return c0_m24_v0;
}protected boolean c0_m25(char c0_m25_p0){
boolean c0_m25_v0 = false;
if ((inp>64&&inp<91)||(inp>96&&inp<123))c0_m25_v0=true;
return c0_m25_v0;
}protected void c0_m26(){
System.out.println(mptv);
}}