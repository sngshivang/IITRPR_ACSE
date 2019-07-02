 public class c0 {
static Vector<String> c0_v6;
static Set <String> c0_v0;
String c0_v8 = "#c", c0_v9 = "#p", c0_v2, c0_v10 = "#m", c0_v11 = "#v";
;
static Map <String, String> c0_v3,c0_v5,c0_v1;
static Map <String, Map <String,String>> c0_v4;
static Stack <String> st;
int c0_v12=0, c0_v13=0, c0_v14=0, c0_v15=0,c0_v16=0, nfree;
static Commonizer2 c0_v7;
public static void c0_m0(String c0_m0_p0[]){
c0_v7 = new Commonizer2();
boolean c0_m0_v0 =true;
int c0_m0_v1;
FileInputStream c0_m0_v2;
Structuring c0_m0_v4 = new Structuring();
if (args.length!=2 && args.length!=3){
str.printinfo();
}else {
c0_v6 = new Vector<String>();
String c0_m0_v1="", c0_m0_v2[]= {"$INV","$INV"};
try {
c0_m0_v2 = new FileInputStream(c0_m0_p0[0]);
BufferedInputStream c0_m0_v0 = new BufferedInputStream(c0_m0_v2);
c0_m0_v2 = str.inpfilename(c0_m0_p0[0]);
byte c0_m0_v1[] = new byte[8192];
while ((c0_m0_v1 = bfs.read(c0_m0_v1))!=-1)c0_m0_v1+=new String(c0_m0_v1, 0, c0_m0_v1, "UTF-8");
bfs.close();
}catch(Exception e){
c0_m0_v0 = false;
System.out.println("The specified input filename is not found. Please check the file path.");
}st = new Stack<>();
c0_v5 = new HashMap<>();
c0_v4 = new HashMap<>();
c0_v1 = new HashMap<>();
c0_v3 = new HashMap<>();
c0_v0 = new HashSet<>();
System.out.println("Processing code.........");
c0_m0_v1 = str2ret(c0_m0_v1);
if (c0_m0_p0[1].equals("CON") && c0_m0_v0){
System.out.println("-------Code starts here------");
System.out.print(c0_m0_v1);
System.out.println("\n-------Code Ends here--------");
}else if (c0_m0_p0[1].equals("FILE") && c0_m0_v0){
if (args.length==2){
c0_m0_v2[0] = c0_m0_v0[0].substring(0, c0_m0_v0[0].length()-5);
c0_m0_v0[0] = c0_m0_v0[1]+c0_m0_v0[0]+"_nrml.java";
}else if (args.length==3){
c0_m0_v2[0] = c0_m0_v2[0].substring(0, c0_m0_v2[0].length()-5);
c0_m0_v2[0] = c0_m0_p0[2]+c0_m0_v2[0]+"_nrml.java";
}try {
FileOutputStream c0_m0_v0 = new FileOutputStream(c0_m0_v2[0]);
BufferedOutputStream c0_m0_v1 = new BufferedOutputStream(c0_m0_v0);
System.out.println("Writing output to file.........");
for (int c0_m0_v2=0;
c0_m0_v2<prog.length();
c0_m0_v2++){
bfis.write(prog.charAt(c0_m0_v2));
}System.out.println("Code successfully normalized.\nOutput File: "+c0_m0_v2[0]);
bfis.close();
}catch(Exception e){
e.printStackTrace();
System.out.println("Cannot create the output file in the directory where the source file is located.\nPlease check your permissions or add your own directory as a third argument");
}}}}public static String c0_m1(String c0_m1_p0){
c0_v6 = new Vector<String>();
c0_m1_v1 = new Stack<>();
c0_v5 = new HashMap<>();
c0_v4 = new HashMap<>();
c0_v3 = new HashMap<>();
Structuring c0_m1_v0 = new Structuring();
try {
return st.single_opers(c0_m1_p0);
}catch(Exception e){
e.printStackTrace();
System.out.println("NORMALIZATION FAILED!\nThe program failed to normalize the give code. This may be due to an erroneous code. Please make sure your source code is in Java and can be compiled.");
System.out.println("If that fails, there may be an issue with the BETA version of this software your are using.\nPlease report the issue to the developer on the program's github page");
System.exit(1);
return "ERR";
}}public String c0_m2(String c0_m2_p0, String c0_m2_p1){
int c0_m2_v4,c0_m2_v12,c0_m2_v7,c0_m2_v2,c0_m2_v1,k,c0_m2_v14=10,c0_m2_v0,c0_m2_v3;
String c0_m2_v10,c0_m2_v6,c0_m2_v13 = "$IGNORE",c0_m2_v5,c0_m2_v11=c0_m2_p0,c0_m2_v9[],c0_m2_v8;
c0_m2_v14--;
do{
c0_m2_v4 = inpc.length();
c0_m2_v7 = inpc.indexOf('{
');
if (c0_m2_v7!=-1){
c0_m2_v12=c0_m2_v7;
if (c0_m2_v12>0&&inpc.charAt(c0_m2_v12-1)==32)c0_m2_v12-=2;
else c0_m2_v0--;
if (c0_m2_v0>-1&&inpc.charAt(c0_m2_v0)==')'){
while (c0_m2_v0>-1&&inpc.charAt(c0_m2_v0)!='(')c0_m2_v0--;
c0_m2_v0--;
while (c0_m2_v0>-1&&!this.stoppoints(inpc.charAt(c0_m2_v0)))c0_m2_v0--;
c0_m2_v10 = inpc.substring(c0_m2_v0+1, c0_m2_v7+1);
System.out.println("bla blAa");
}else if (c0_m2_v0>-1){
while (c0_m2_v0>-1&&!this.stoppoints(inpc.charAt(c0_m2_v0)))c0_m2_v0--;
c0_m2_v9 = this.classnormal(inpc.substring(c0_m2_v0+1, c0_m2_v7+1), c0_m2_v0);
c0_m2_v0 = c0_m2_v9[1];
if (!c0_m2_v9[0].equals("$INV")) {
c0_m2_v11 = AdvSubstring.replaceFirst(c0_m2_v11, c0_m2_v9[0], c0_m2_v9[1]);
c0_m2_p1="";
continue;
}}c0_m2_v10 = inpc.substring(c0_m2_v7+1,c0_m2_v1);
c0_m2_v2 = sstr.indexOf('{
');
c0_m2_v1 = sstr.indexOf('}');
if (c0_m2_v1!=-1&&(c0_m2_v2==-1||c0_m2_v2>c0_m2_v1)){
c0_m2_v5 = sstr.substring(0, c0_m2_v1);
c0_m2_v10 = "{
"+c0_m2_v5+"}";
c0_m2_v6 = tmpresolve(c0_m2_v10);
c0_m2_v6 = Commonizer2.equalsuniv(c0_m2_v6, c0_m2_p1, c0_m2_v0);
c0_m2_v13 = "$IGNORE"+String.valueOf(c0_v14)+"$";
c0_v14++;
st.push(c0_m2_v13);
blmp.put(c0_m2_v13, c0_m2_v6);
c0_m2_v11 = inpc.replace(c0_m2_v0, c0_m2_v13) ;
}else if (c0_m2_v1==-1)break;
else{
c0_m2_v0 = c0_m2_v2;
if (c0_m2_v0>0&&sstr.charAt(c0_m2_v0-1)==32)c0_m2_v0-=2;
else c0_m2_v0--;
if (c0_m2_v0>-1&&sstr.charAt(c0_m2_v0)==')'){
c0_m2_v0 = c0_m2_v0;
while (c0_m2_v0>-1&&sstr.charAt(c0_m2_v0)!='(')c0_m2_v0--;
c0_m2_v3 = c0_m2_v0;
c0_m2_v0--;
while (c0_m2_v0>-1&&!this.stoppoints(sstr.charAt(c0_m2_v0)))c0_m2_v0--;
c0_m2_v6 = sstr.substring(c0_m2_v0+1, c0_m2_v2+1);
c0_m2_v9 = this.metnormal(c0_m2_v6, c0_m2_v0);
c0_m2_v0 = c0_m2_v9[2];
if (!c0_m2_v9[0].equals("$INV")){
c0_m2_v6 = sstr.substring(c0_m2_v3, c0_m2_v0+1);
c0_m2_v8 = this.parnormal(c0_m2_v6, 0, c0_m2_p1);
c0_m2_v11 = AdvSubstring.replaceFirst(c0_m2_v11, c0_m2_v0[1], c0_m2_v0[2]);
c0_m2_v11 = AdvSubstring.nidreplaceFirst(c0_m2_v11, c0_m2_v1, c0_m2_v8);
}}else if (c0_m2_v0>-1){
while (c0_m2_v0>-1&&!this.stoppoints(sstr.charAt(c0_m2_v0)))c0_m2_v0--;
c0_m2_v9 = this.classnormal(sstr.substring(c0_m2_v0+1, c0_m2_v2+1), c0_m2_p1);
c0_m2_p1 = c0_m2_v0[1];
if (!c0_m2_v0[0].equals("$INV"))c0_m2_v11 = AdvSubstring.replaceFirst(c0_m2_v11, c0_m2_v0[0], c0_m2_v0[1]);
}c0_m2_v10 = sstr.substring(c0_m2_v2, c0_m2_v1+1);
c0_m2_v11 = inpc.replace(c0_m2_v10,this.structures(c0_m2_v10, c0_m2_v0));
c0_m2_v0 = "";
}}}while(c0_m2_v7!=-1);
return c0_m2_v11;
}protected static String c0_m3(String c0_m3_p0){
int c0_m3_v3,c0_m3_v5,c0_m3_v1;
char c0_m3_v4;
String c0_m3_v0,c0_m3_v2;
do{
c0_m3_v0="";
c0_m3_v3 = inp.indexOf("$IGNORE");
c0_m3_v1 = inp.length();
for (c0_m3_v5=c0_m3_v1+7;
c0_m3_v5<c0_m3_v0;
c0_m3_v5++){
c0_m3_v4 = inp.charAt(c0_m3_v5);
if (c0_m3_v4>47&&c0_m3_v4<58)c0_m3_v0+=String.valueOf(inp.charAt(c0_m3_v5));
elsebreak;
}c0_m3_v0 = "$IGNORE"+c0_m3_v0+"$";
if (blmp.get(c0_m3_v0)!=null){
c0_m3_v2 = blmp.get(c0_m3_v0);
c0_m3_p0 = inp.replace(c0_m3_v0, c0_m3_v2);
}}while (c0_m3_v3!=-1);
return c0_m3_v6;
}protected void c0_m4(){
System.out.println("------Java Source Code Normalizer------");
System.out.println("Missing Arguments: Program expects at least two arguments");
System.out.println("Usage: JavaNormalizer.jar [Source File] [Output type] [Destination File (o)])");
System.out.println("\nOptions\n");
System.out.println("Source: Valid input filepath");
System.out.println("Output type : CON/FILE\n\tCON prints output on the console. Omit third argument when this option is used");
System.out.println("\tFILE prints output to the specified destination file.\n\tThrid argument if skipped will create the output file in the same directory as the source file.");
System.out.println("Destination File: The destination file path when second argument is FILE(Optional)");
}protected String c0_m5(String c0_m5_p0){
String c0_m5_v0, c0_m5_v1;
reschar.put("$RESV0$", "'{
'");
reschar.put("$RESV1$", "'}'");
reschar.put("$RESV2$", "'('");
reschar.put("$RESV3$", "')'");
reschar.put("$RESV4$", "';
'");
for (Map.Entry<String, String> tmp : reschar.entrySet()){
c0_m5_v0 = tmp.getKey();
c0_m5_v1 = tmp.getValue();
c0_m5_p0 = inp.replace(c0_m5_v1, c0_m5_v0);
}return c0_m5_v2;
}protected String c0_m6(String c0_m6_p0){
String c0_m6_v0, c0_m6_v1;
for (Map.Entry<String, String> tmp : reschar.entrySet()){
c0_m6_v0 = tmp.getKey();
c0_m6_v1 = tmp.getValue();
c0_m6_p0 = inp.replace(c0_m6_v0, c0_m6_v0);
}return c0_m6_v2;
}protected String[] c0_m7(String c0_m7_p0){
int c0_m7_v0 = inp.length(),c0_m7_v1;
String c0_m7_v3[] = new String[2];
c0_m7_v3[0]="";
for (c0_m7_v1=c0_m7_v0-1;
c0_m7_v1>-1;
c0_m7_v1--){
if(inp.charAt(c0_m7_v1)!='/'&&inp.charAt(c0_m7_v1)!='\\')c0_m7_v3[0] = String.valueOf(inp.charAt(c0_m7_v1))+c0_m7_v3[0];
else break;
}c0_m7_v3[1] = inp.substring(0, c0_m7_v1+1);
return c0_m7_v3;
}protected String c0_m8(String c0_m8_p0){
c0_v2 = this.textrepo(c0_m8_p0);
c0_v2 = this.rmcmt(c0_v2);
c0_v2 = this.removenlines(c0_v2);
c0_v2 = this.rmextrasp(c0_v2);
c0_v2 = this.trimmer(c0_v2);
c0_v2 = this.represvchar(c0_v2);
String c0_m8_v0 = this.structures(c0_v2,"");
c0_m8_v0 = tmpresolve(c0_m8_v0);
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
}protected String c0_m10(String c0_m10_p0){
c0_m10_p0 = inp.replaceAll("\n", "");
return c0_m10_p0;
} protected String c0_m11(String c0_m11_p0){
int c0_m11_v0,c0_m11_v2;
do {
c0_m11_v0 = inp.indexOf("//");
if (c0_m11_v0!=-1) {
c0_m11_v2 = inp.indexOf("\n",c0_m11_v0+2);
c0_m11_p0 = inp.replace(inp.substring(c0_m11_v0, c0_m11_v2),"");
}}while (c0_m11_v0!=-1);
do {
c0_m11_v0 = inp.indexOf("/*");
if (c0_m11_v0!=-1){
c0_m11_v2 = inp.indexOf("*/",c0_m11_v0+2);
c0_m11_p0 = inp.replace(inp.substring(c0_m11_v0, c0_m11_v1+2), "");
}}while (c0_m11_v0!=-1);
return c0_m11_v1;
}protected String c0_m12(String c0_m12_p0){
int c0_m12_v0, c0_m12_v3, esc;
String c0_m12_v1,c0_m12_v4;
char c0_m12_v5 = 34;
do {
c0_m12_v0 = inp.indexOf(c0_m12_v5);
if (c0_m12_v0!=-1){
c0_m12_v1 = "$TEX$T";
c0_m12_v3 = inp.indexOf(c0_m12_v5,c0_m12_v0+1);
c0_m12_v1+=String.valueOf(nfree);
c0_m12_v4 = inp.substring(c0_m12_v0,c0_m12_v1+1);
txtdat.add(c0_m12_v4);
nfree++;
c0_m12_p0 = inp.replace(c0_m12_v4, c0_m12_v1);
}}while(c0_m12_v0!=-1);
return c0_m12_v2;
}protected String c0_m13(String c0_m13_p0){
String c0_m13_v10 = "$TEX$T",c0_m13_v3, c0_m13_v0, c0_m13_v7 = c0_m13_v6, c0_m13_v4;
int c0_m13_v1, c0_m13_v5, c0_m13_v8, c0_m13_v9,c0_m13_v2;
do {
c0_m13_v1 = inp.length();
c0_m13_v3 = "";
c0_m13_v5 = inp.indexOf(c0_m13_v10);
if (c0_m13_v5!=-1){
c0_m13_v9 = c0_m13_v0 + 6;
while (c0_m13_v9<c0_m13_v1&&(inp.charAt(c0_m13_v9)>47&&inp.charAt(c0_m13_v9)<58)){
c0_m13_v3+=String.valueOf(inp.charAt(c0_m13_v9));
c0_m13_v9++;
}c0_m13_v8 = Integer.valueOf(c0_m13_v3);
c0_m13_v0 = txtdat.get(c0_m13_v8);
c0_m13_v2 = out.length();
c0_m13_v4 = c0_m13_p0;
c0_m13_p0 = AdvSubstring.nidreplaceFirst(c0_m13_p0,c0_m13_v10+c0_m13_v3, c0_m13_v0);
c0_m13_v7 = cp.replace(c0_m13_v4, c0_m13_p0);
c0_m13_p0 = inp.substring(c0_m13_v0+c0_m13_v2,inp.length());
}}while (c0_m13_v5!=-1);
return c0_m13_v7;
}protected String c0_m14(String c0_m14_p0){
String c0_m14_v4[]= {"package", "import"};
int c0_m14_v5 = inp.length(), c0_m14_v1, i, c0_m14_v2;
String c0_m14_v0;
for (i=0;
i<2;
i++){
do {
c0_m14_v1 = inp.indexOf(c0_m14_v4[c0_m14_v0]);
if (c0_m14_v1!=-1){
for (c0_m14_v2=c0_m14_v1;
c0_m14_v2<c0_m14_v0;
c0_m14_v2++)if (inp.charAt(c0_m14_v2)==';
')break;
c0_m14_v0 = inp.substring(c0_m14_v1, c0_m14_v2+1);
c0_m14_p0 = inp.replace(c0_m14_v0, "");
}}while (c0_m14_v1!=-1);
}return c0_m14_v3;
}protected String c0_m15(String c0_m15_p0){
int c0_m15_v1 = inp.length(),i;
if (inp.charAt(0)==9)c0_m15_p0 = this.rmchar(c0_m15_p0, 0);
for (i=1;
i<c0_m15_v1;
){
if (inp.charAt(i)==32&&inp.charAt(i-1)==32){
c0_m15_p0 = this.rmchar(c0_m15_p0, c0_m15_v0);
c0_m15_v1--;
}else if (inp.charAt(i)==9){
c0_m15_p0 = this.rmchar(c0_m15_p0, i);
c0_m15_v1--;
}else i++;
}return c0_m15_v0;
}protected String c0_m16(String c0_m16_p0){
int c0_m16_v2 = inp.length(),c0_m16_v1;
String c0_m16_v0="";
for (c0_m16_v1=0;
c0_m16_v1<c0_m16_v2;
c0_m16_v1++){
c0_m16_v0+=String.valueOf(inp.charAt(c0_m16_v1));
if (inp.charAt(c0_m16_v1)==';
')c0_m16_v0+="\n";
else if (c0_m16_v1>1&&inp.charAt(c0_m16_v1)=='{'&&!(inp.charAt(c0_m16_v1-1)=='='||inp.charAt(c0_m16_v1-2)=='='))c0_m16_v0+="\n";
}return c0_m16_v0;
}protected String[] c0_m17(String c0_m17_p0, String c0_m17_p1){
String c0_m17_v9=c0_m17_v7,c0_m17_v0,c0_m17_v1,c0_m17_v8,c0_m17_v10[]= {"$INV","$INV"};
boolean c0_m17_v2 =false;
int len, c0_m17_v4,c0_m17_v5,c0_m17_v6;
c0_m17_v4 = org.indexOf('{
');
len = org.length();
c0_m17_v6=c0_m17_v4;
if (c0_m17_v4!=-1){
c0_m17_v4--;
if (c0_m17_v4>-1&&org.charAt(c0_m17_v4)==32)c0_m17_v4--;
c0_m17_v0 = "";
c0_m17_v8="";
while (c0_m17_v4>-1&&Character.isJavaIdentifierPart(org.charAt(c0_m17_v4))){
c0_m17_v0 = Character.toString(org.charAt(c0_m17_v4))+c0_m17_v0;
c0_m17_v4--;
if (c0_m17_v4>-1&&org.charAt(c0_m17_v4)==''){
c0_m17_v2 = true;
c0_m17_v4--;
break;
}}c0_m17_v4--;
while (c0_m17_v4>-1&&this.isValidKeywordChar(org.charAt(c0_m17_v4))){
c0_m17_v8 = org.charAt(c0_m17_v4)+c0_m17_v8;
c0_m17_v4--;
}if (keywrd.equals("class")||keywrd.equals("interface")||keywrd.equals("enum")){
if (!c0_m17_v0){
c0_m17_v1 = this.hash+String.valueOf(c0_v12);
if (pref.equals(""))c0_m17_v2 = c0_m17_v1;
else c0_m17_v1 = c0_m17_v1 + "_" + c0_m17_v1;
c0_m17_v10[0]=c0_m17_v0;
clmp.put(c0_m17_v0, c0_m17_v1);
c0_v12++;
}else {
c0_m17_v0 = "#"+c0_m17_v0;
if (pref.equals(""))c0_m17_v1 = c0_m17_v0;
else c0_m17_v0 = c0_m17_v0 + "_" + c0_m17_v0;
}}}c0_m17_v10[1] = c0_m17_v3;
return c0_m17_v10;
}protected String[] c0_m18(String c0_m18_p0, String c0_m18_p1){
String c0_m18_v8[] = {"$INV","$INV", "$INV"};
String c0_m18_v9=c0_m18_p0,c0_m18_v0,c0_m18_v1;
boolean c0_m18_v2 =false, c0_m18_v7 = false;
int c0_m18_v3, c0_m18_v5,i,c0_m18_v6;
c0_m18_v2 = false;
c0_m18_v5 = org.indexOf('(');
c0_m18_v3 = org.length();
c0_m18_v6=c0_m18_v5;
if (c0_m18_v5!=-1){
c0_m18_v5--;
if (c0_m18_v5>-1&&org.charAt(c0_m18_v5)==32)c0_m18_v5--;
c0_m18_v0 = "";
while (c0_m18_v5>-1&&Character.isJavaIdentifierPart(org.charAt(c0_m18_v5))){
c0_m18_v0 = Character.toString(org.charAt(c0_m18_v5))+c0_m18_v0;
c0_m18_v5--;
if (c0_m18_v5>-1&&org.charAt(c0_m18_v5)==''){
c0_m18_v2 = true;
c0_m18_v5--;
break;
}}if (c0_m18_v5<0||org.charAt(c0_m18_v5)!=32)c0_m18_v7=true;
if (!c0_m18_v2&&!c0_m18_v7&&!KeywordCheck.chk(c0_m18_v0)){
c0_m18_v1 = this.mhsh+String.valueOf(c0_v15);
c0_m18_p1 = c0_m18_p1 + "_" + c0_m18_v1;
c0_v15++;
c0_m18_v8[0] = c0_m18_v1;
c0_m18_v8[1] = c0_m18_v0;
}else if (c0_m18_v2){
c0_m18_v0 = "#"+c0_m18_v0;
if (pref.equals(""))c0_m18_v1 = c0_m18_v0;
else c0_m18_v0 = c0_m18_v0 + "_" + c0_m18_v0;
}}c0_m18_v8[2] = c0_m18_v4;
return c0_m18_v8;
}protected String c0_m19(String c0_m19_p0, int c0_m19_p1, String c0_m19_p2){
Map <String, String> c0_m19_v8 = new HashMap<>();
int c0_m19_v2,c0_m19_v6=0,i,c0_m19_v10=0;
for (i=c0_m19_p1+1;
i<inp.length();
i++)if (inp.charAt(i)==')'){
c0_m19_v6 = i;
break;
}String c0_m19_v7,c0_m19_v1, c0_m19_v11 = inp.substring(c0_m19_p1+1,c0_m19_v6), c0_m19_v0;
c0_m19_v11 = this.parpreproc(c0_m19_v11);
StringTokenizer c0_m19_v3 = new StringTokenizer(c0_m19_v11),c0_m19_v5;
Vector <String> c0_m19_v13 = new Vector<>(),c0_m19_v14 = new Vector<>();
while (st.hasMoreTokens())vt.add(st.nextToken(","));
Iterator<String> c0_m19_v15 = vt.iterator();
while (it.hasNext()){
c0_m19_v7 = (String)it.next();
c0_m19_v5 = new StringTokenizer(c0_m19_v7);
while (st2.hasMoreTokens()){
vt2.add(st2.nextToken());
}c0_m19_v2 = vt2.size();
c0_m19_v1 = this.phsh+String.valueOf(c0_m19_v10);
c0_m19_v0 = c0_m19_p2 + "_" + c0_m19_v0;
c0_m19_v10++;
mp1.put(vt2.get(c0_m19_v2-1), c0_m19_v1);
c0_m19_p0 = AdvSubstring.replace(c0_m19_p0, vt2.get(c0_m19_v2-1), c0_m19_v1);
vt2.clear();
}if (!mp1.isEmpty()) mptv.put(c0_m19_p2, c0_m19_v8);
return c0_m19_v4;
}protected String c0_m20(String c0_m20_p0){
int c0_m20_v0 = inp.length(),c0_m20_v2,c0_m20_v3;
String c0_m20_v1;
do{
c0_m20_v2 = inp.indexOf('[');
if (c0_m20_v2!=-1){
c0_m20_v3 = c0_m20_v2+1;
while (inp.charAt(c0_m20_v3)!=']')c0_m20_v3++;
c0_m20_v1 = inp.substring(c0_m20_v2, c0_m20_v3+1);
c0_m20_p0 = inp.replace(c0_m20_v1, "");
}}while (c0_m20_v2!=-1);
return c0_m20_v4;
}protected String c0_m21(String c0_m21_p0){
int c0_m21_v2 = inp.length(),c0_m21_v1;
String c0_m21_v0="";
for (c0_m21_v1=0;
c0_m21_v1<c0_m21_v2;
c0_m21_v1++){
if (inp.charAt(c0_m21_v1)!='')c0_m21_v0+=String.valueOf(inp.charAt(c0_m21_v1));
}return c0_m21_v0;
}protected boolean c0_m22(char c0_m22_p0){
boolean c0_m22_v0 = false;
if (c0_m22_p0=='{'||c0_m22_p0=='}'||c0_m22_p0=='('||c0_m22_p0==')'||c0_m22_p0==';
'||c0_m22_p0=='$')c0_m22_v0 = true;
return c0_m22_v0;
}protected String c0_m23(String c0_m23_p0, int c0_m23_p1){
int c0_m23_v0 = inp.length();
String c0_m23_v1 = inp.substring(0, c0_m23_p1) + inp.substring(c0_m23_p1+1, c0_m23_v0);
return c0_m23_v1;
}protected boolean c0_m24(char c0_m24_p0){
boolean c0_m24_v0 = false;
if ((c0_m24_p0>64&&c0_m24_p0<91)||(c0_m24_p0>96&&c0_m24_p0<123)||(c0_m24_p0>47&&c0_m24_p0<58)||c0_m24_p0=='$'||c0_m24_p0=='_')c0_m24_v0=true;
return c0_m24_v0;
}protected boolean c0_m25(char c0_m25_p0){
boolean c0_m25_v0 = false;
if ((c0_m25_p0>64&&c0_m25_p0<91)||(c0_m25_p0>96&&c0_m25_p0<123))c0_m25_v0=true;
return c0_m25_v0;
}protected void c0_m26(){
Iterator<String> c0_m26_v0 = pendvar.iterator();
while (it.hasNext())System.out.println(it.next());
}}