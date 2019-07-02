 public class c0 {
static Vector<String> txtdat;
static Set <String> pendvar;
String c0_v5 = "#c", c0_v6 = "#p", c0_v4, c0_v7 = "#m", c0_v8 = "#v";
;
static Map <String, String> blmp,c0_v2,reschar;
static Map <String, Map <String,String>> c0_v0;
static Stack <String> c0_v1;
int c0_v9=0, c0_v10=0, c0_v11=0, c0_v12=0,c0_v13=0, nfree;
static Commonizer2 c0_v3;
public static void c0_m0(String c0_m0_p0[]){
c0_v3 = new Commonizer2();
boolean c0_m0_v0 =true;
int c0_m0_v1;
FileInputStream c0_m0_v2;
Structuring c0_m0_v4 = new Structuring();
if (c0_m0_p0.length!=2 && c0_m0_p0.length!=3){
c0_m0_v4.printinfo();
}else {
txtdat = new Vector<String>();
String c0_m0_v1="", c0_m0_v2[]= {"$INV","$INV"};
try {
c0_m0_v2 = new FileInputStream(c0_m0_p0[0]);
BufferedInputStream c0_m0_v0 = new BufferedInputStream(c0_m0_v2);
c0_m0_v2 = c0_m0_v4.inpfilename(c0_m0_p0[0]);
byte c0_m0_v1[] = new byte[8192];
while ((c0_m0_v1 = c0_m0_v0.read(c0_m0_v1))!=-1)c0_m0_v1+=new String(c0_m0_v1, 0, c0_m0_v1, "UTF-8");
c0_m0_v0.close();
}catch(Exception e){
c0_m0_v0 = false;
System.out.println("The specified input filename is not found. Please check the file path.");
}c0_v1 = new Stack<>();
c0_v2 = new HashMap<>();
c0_v0 = new HashMap<>();
reschar = new HashMap<>();
blmp = new HashMap<>();
pendvar = new HashSet<>();
System.out.println("Processing code.........");
c0_m0_v1 = str2ret(c0_m0_v1);
if (c0_m0_p0[1].equals("CON") && c0_m0_v0){
System.out.println("-------Code starts here------");
System.out.print(c0_m0_v1);
System.out.println("\n-------Code Ends here--------");
}else if (c0_m0_p0[1].equals("FILE") && c0_m0_v0){
if (c0_m0_p0.length==2){
c0_m0_v2[0] = c0_m0_v0[0].substring(0, c0_m0_v0[0].length()-5);
c0_m0_v0[0] = c0_m0_v0[1]+c0_m0_v0[0]+"_nrml.java";
}else if (c0_m0_p0.length==3){
c0_m0_v2[0] = c0_m0_v2[0].substring(0, c0_m0_v2[0].length()-5);
c0_m0_v2[0] = c0_m0_p0[2]+c0_m0_v2[0]+"_nrml.java";
}try {
FileOutputStream c0_m0_v0 = new FileOutputStream(c0_m0_v2[0]);
BufferedOutputStream c0_m0_v1 = new BufferedOutputStream(c0_m0_v0);
System.out.println("Writing output to file.........");
for (int c0_m0_v2=0;
c0_m0_v2<c0_m0_v1.length();
c0_m0_v2++){
c0_m0_v1.write(c0_m0_v1.charAt(c0_m0_v2));
}System.out.println("Code successfully normalized.\nOutput File: "+c0_m0_v2[0]);
c0_m0_v1.close();
}catch(Exception e){
e.printStackTrace();
System.out.println("Cannot create the output file in the directory where the source file is located.\nPlease check your permissions or add your own directory as a third argument");
}}}}public static String c0_m1(String c0_m21_p0){
txtdat = new Vector<String>();
c0_m1_v0 = new Stack<>();
c0_v2 = new HashMap<>();
c0_v0 = new HashMap<>();
blmp = new HashMap<>();
Structuring c0_m1_v0 = new Structuring();
try {
return c0_m1_v0.single_opers(c0_m1_p0);
}catch(Exception e){
System.out.println("NORMALIZATION FAILED!\nThe program failed to normalize the give code. This may be due to an erroneous code. Please make sure your source code is in Java and can be compiled.");
System.out.println("If that fails, there may be an issue with the BETA version of this software your are using.\nPlease report the issue to the developer on the program's github page");
System.exit(1);
return "ERR";
}}public String c0_m2(String c0_m18_p0, String c0_m18_p1){
int c0_m2_v4,c0_m2_v11,c0_m2_v7,c0_m2_v2,c0_m2_v1,k,c0_m2_v14=10,c0_m2_v0,c0_m2_v3;
String c0_m2_v10,c0_m2_v6,c0_m2_v13 = "$IGNORE",c0_m2_v5,c0_m2_v12=c0_m2_p0,c0_m2_v9[],c0_m2_v8;
c0_m2_v14--;
do{
c0_m2_v4 = c0_m2_v0.length();
c0_m2_v7 = c0_m2_v0.indexOf('{
');
if (c0_m2_v7!=-1){
c0_m2_v11=c0_m2_v7;
if (c0_m2_v11>0&&c0_m2_v0.charAt(c0_m2_v11-1)==32)c0_m2_v11-=2;
else c0_m2_v11--;
if (c0_m2_v11>-1&&c0_m2_v0.charAt(c0_m2_v11)==')'){
while (c0_m2_v11>-1&&c0_m2_v0.charAt(c0_m2_v11)!='(')c0_m2_v11--;
c0_m2_v11--;
while (c0_m2_v11>-1&&!this.stoppoints(c0_m2_v0.charAt(c0_m2_v11)))c0_m2_v11--;
c0_m2_v0 = c0_m2_v0.substring(c0_m2_v11+1, c0_m2_v7+1);
System.out.println("bla blAa");
}else if (c0_m2_v11>-1){
while (c0_m2_v11>-1&&!this.stoppoints(c0_m2_v0.charAt(c0_m2_v11)))c0_m2_v11--;
c0_m2_v9 = this.classnormal(c0_m2_v0.substring(c0_m2_v11+1, c0_m2_v7+1), c0_m2_v0);
c0_m2_v0 = c0_m2_v9[1];
if (!c0_m2_v9[0].equals("$INV")) {
c0_m2_v0 = AdvSubstring.replaceFirst(c0_m2_v0, c0_m2_v9[0], c0_m2_v9[1]);
c0_m2_p1="";
continue;
}}c0_m2_v0 = c0_m2_v0.substring(c0_m2_v7+1,c0_m2_v4);
c0_m2_v2 = c0_m2_v0.indexOf('{
');
c0_m2_v1 = c0_m2_v0.indexOf('}');
if (c0_m2_v1!=-1&&(c0_m2_v2==-1||c0_m2_v2>c0_m2_v1)){
c0_m2_v5 = c0_m2_v0.substring(0, c0_m2_v1);
c0_m2_v0 = "{
"+c0_m2_v5+"}";
c0_m2_v6 = tmpresolve(c0_m2_v0);
c0_m2_v6 = Commonizer2.equalsuniv(c0_m2_v6, c0_m2_p1, c0_m2_v0);
c0_m2_v13 = "$IGNORE"+String.valueOf(c0_v11)+"$";
c0_v11++;
c0_v1.push(c0_m2_v13);
blmp.put(c0_m2_v13, c0_m2_v6);
c0_m2_v0 = c0_m2_v0.replace(c0_m2_v0, c0_m2_v13) ;
}else if (c0_m2_v1==-1)break;
else{
c0_m2_v11 = c0_m2_v2;
if (c0_m2_v11>0&&c0_m2_v0.charAt(c0_m2_v11-1)==32)c0_m2_v11-=2;
else c0_m2_v11--;
if (c0_m2_v11>-1&&c0_m2_v0.charAt(c0_m2_v11)==')'){
c0_m2_v0 = c0_m2_v11;
while (c0_m2_v11>-1&&c0_m2_v0.charAt(c0_m2_v11)!='(')c0_m2_v11--;
c0_m2_v3 = c0_m2_v11;
c0_m2_v11--;
while (c0_m2_v11>-1&&!this.stoppoints(c0_m2_v0.charAt(c0_m2_v11)))c0_m2_v11--;
c0_m2_v6 = c0_m2_v0.substring(c0_m2_v11+1, c0_m2_v2+1);
c0_m2_v9 = this.metnormal(c0_m2_v6, c0_m2_v0);
c0_m2_v0 = c0_m2_v9[2];
if (!c0_m2_v9[0].equals("$INV")){
c0_m2_v6 = c0_m2_v1.substring(c0_m2_v3, c0_m2_v0+1);
c0_m2_v8 = this.parnormal(c0_m2_v6, 0, c0_m2_p1 ,c0_m2_v0[2]);
c0_m2_v0 = AdvSubstring.replaceFirst(c0_m2_v0, c0_m2_v0[1], c0_m2_v0[2]);
c0_m2_v0 = c0_m2_v2.replace(c0_m2_v6, c0_m2_v8);
}}else if (c0_m2_v11>-1){
while (c0_m2_v11>-1&&!this.stoppoints(c0_m2_v0.charAt(c0_m2_v11)))c0_m2_v11--;
c0_m2_v9 = this.classnormal(c0_m2_v0.substring(c0_m2_v11+1, c0_m2_v2+1), c0_m2_p1);
c0_m2_p1 = c0_m2_v0[1];
if (!c0_m2_v0[0].equals("$INV"))c0_m2_v0 = AdvSubstring.replaceFirst(c0_m2_v0, c0_m2_v0[0], c0_m2_v0[1]);
}c0_m2_v0 = c0_m2_v0.substring(c0_m2_v2, c0_m2_v1+1);
c0_m2_v0 = c0_m2_v0.replace(c0_m2_v0,this.structures(c0_m2_v0, c0_m2_v1));
c0_m2_v1 = "";
}}}while(c0_m2_v7!=-1);
return c0_m2_v12;
}protected static String c0_m3(String c0_m21_p0){
int c0_m3_v3,c0_m3_v5,c0_m3_v1;
char c0_m3_v4;
String c0_m3_v0,c0_m3_v2;
do{
c0_m3_v0="";
c0_m3_v3 = c0_m3_v6.indexOf("$IGNORE");
c0_m3_v1 = c0_m3_v6.length();
for (c0_m3_v5=c0_m3_v1+7;
c0_m3_v5<c0_m3_v0;
c0_m3_v5++){
c0_m3_v4 = c0_m3_v6.charAt(c0_m3_v5);
if (c0_m3_v4>47&&c0_m3_v4<58)c0_m3_v0+=String.valueOf(c0_m3_v6.charAt(c0_m3_v5));
elsebreak;
}c0_m3_v0 = "$IGNORE"+c0_m3_v0+"$";
if (blmp.get(c0_m3_v0)!=null){
c0_m3_v2 = c0_m3_v0.get(c0_m3_v0);
c0_m3_v6 = c0_m3_v6.replace(c0_m3_v0, c0_m3_v2);
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
c0_m5_v1 = c0_m5_v0.replace(val, key);
}return c0_m5_v1;
}protected String c0_m6(String c0_m21_p0){
String c0_m6_v0, c0_m6_v1;
for (Map.Entry<String, String> tmp : reschar.entrySet()){
c0_m6_v0 = tmp.getKey();
c0_m6_v1 = tmp.getValue();
c0_m6_v2 = c0_m6_v2.replace(c0_m6_v0, c0_m6_v0);
}return c0_m6_v2;
}protected String[] c0_m7(String c0_m21_p0){
int c0_m7_v0 = c0_m7_v2.length(),c0_m7_v1;
String c0_m7_v4[] = new String[2];
c0_m7_v4[0]="";
for (c0_m7_v1=c0_m7_v0-1;
c0_m7_v1>-1;
c0_m7_v1--){
if(c0_m7_v2.charAt(c0_m7_v1)!='/'&&c0_m7_v2.charAt(c0_m7_v1)!='\\')c0_m7_v4[0] = String.valueOf(c0_m7_v2.charAt(c0_m7_v1))+c0_m7_v4[0];
else break;
}c0_m7_v4[1] = c0_m7_v2.substring(0, c0_m7_v1+1);
return c0_m7_v4;
}protected String c0_m8(String c0_m8_p0){
c0_v4 = this.textrepo(c0_m8_p0);
c0_v4 = this.rmcmt(c0_v4);
c0_v4 = this.removenlines(c0_v4);
c0_v4 = this.rmextrasp(c0_v4);
c0_v4 = this.trimmer(c0_v4);
c0_v4 = this.represvchar(c0_v4);
String c0_m8_v0 = this.structures(c0_v4,"");
c0_m8_v0 = c0_m3(c0_m8_v0);
c0_m8_v0 = this.removehash(c0_m8_v0);
c0_m8_v0 = this.redoresvchar(c0_m8_v0);
c0_m8_v0 = this.redotext(c0_m8_v0);
c0_m8_v0 = this.addnewline(c0_m8_v0);
this.cleanup();
return c0_m8_v0;
}protected void c0_m9(){
c0_v0.clear();
blmp.clear();
c0_v2.clear();
reschar.clear();
}protected String c0_m10(String c0_m21_p0){
inp = inp.replaceAll("\n", "");
return inp;
} protected String c0_m11(String c0_m21_p0){
int c0_m11_v0,c0_m11_v2;
do {
c0_m11_v0 = c0_m11_v0.indexOf("//");
if (c0_m11_v0!=-1) {
c0_m11_v2 = c0_m11_v1.indexOf("\n",c0_m11_v0+2);
c0_m11_v1 = c0_m11_v1.replace(c0_m11_v1.substring(c0_m11_v0, c0_m11_v2),"");
}}while (c0_m11_v0!=-1);
do {
c0_m11_v0 = c0_m11_v0.indexOf("/*");
if (c0_m11_v0!=-1){
c0_m11_v2 = c0_m11_v0.indexOf("*/",c0_m11_v0+2);
c0_m11_v0 = c0_m11_v0.replace(c0_m11_v0.substring(c0_m11_v0, c0_m11_v1+2), "");
}}while (c0_m11_v0!=-1);
return c0_m11_v1;
}protected String c0_m12(String c0_m21_p0){
int c0_m12_v0, c0_m12_v3, esc;
String c0_m12_v1,c0_m12_v4;
char c0_m12_v5 = 34;
do {
c0_m12_v0 = c0_m12_v0.indexOf(c0_m12_v5);
if (c0_m12_v0!=-1){
c0_m12_v1 = "$TEX$T";
c0_m12_v3 = c0_m12_v0.indexOf(c0_m12_v5,c0_m12_v0+1);
c0_m12_v1+=String.valueOf(nfree);
c0_m12_v4 = c0_m12_v0.substring(c0_m12_v0,c0_m12_v3+1);
txtdat.add(c0_m12_v4);
nfree++;
c0_m12_v0 = c0_m12_v0.replace(c0_m12_v4, c0_m12_v1);
}}while(c0_m12_v0!=-1);
return c0_m12_v2;
}protected String c0_m13(String c0_m21_p0){
String c0_m13_v9 = "$TEX$T",c0_m13_v2, c0_m13_v0, c0_m13_v6 = c0_m13_v5, c0_m13_v3;
int c0_m13_v1, c0_m13_v4, c0_m13_v7, c0_m13_v8;
do {
c0_m13_v1 = c0_m13_v0.length();
c0_m13_v2 = "";
c0_m13_v4 = c0_m13_v0.indexOf(c0_m13_v9);
if (c0_m13_v4!=-1){
c0_m13_v8 = c0_m13_v0 + 6;
while (c0_m13_v8<c0_m13_v1&&(c0_m13_v0.charAt(c0_m13_v8)>47&&c0_m13_v0.charAt(c0_m13_v8)<58)){
c0_m13_v2+=String.valueOf(c0_m13_v0.charAt(c0_m13_v8));
c0_m13_v8++;
}c0_m13_v7 = Integer.valueOf(c0_m13_v2);
c0_m13_v0 = c0_m13_v1.get(c0_m13_v7);
c0_m13_v3 = c0_m13_v0;
c0_m13_v0 = AdvSubstring.replaceFirst(c0_m13_v0,c0_m13_v9+c0_m13_v2, c0_m13_v0);
c0_m13_v6 = c0_m13_v6.replace(c0_m13_v3, c0_m13_v0);
c0_m13_v0 = c0_m13_v0.substring(c0_m13_v0+2,c0_m13_v0.length());
}}while (c0_m13_v4!=-1);
return c0_m13_v6;
}protected String c0_m14(String c0_m21_p0){
String c0_m14_v4[]= {"package", "import"};
int c0_m14_v5 = c0_m14_v3.length(), c0_m14_v1, i, c0_m14_v2;
String c0_m14_v0;
for (i=0;
i<2;
i++){
do {
c0_m14_v1 = c0_m14_v1.indexOf(c0_m14_v4[c0_m14_v0]);
if (c0_m14_v1!=-1){
for (c0_m14_v2=c0_m14_v1;
c0_m14_v2<c0_m14_v0;
c0_m14_v2++)if (c0_m14_v1.charAt(c0_m14_v2)==';
')break;
c0_m14_v0 = c0_m14_v1.substring(c0_m14_v1, c0_m14_v2+1);
c0_m14_v1 = c0_m14_v1.replace(c0_m14_v0, "");
}}while (c0_m14_v1!=-1);
}return c0_m14_v3;
}protected String c0_m15(String c0_m21_p0){
int c0_m15_v1 = c0_m15_v0.length(),i;
if (c0_m15_v0.charAt(0)==9)c0_m15_v0 = this.rmchar(c0_m15_v0, 0);
for (i=1;
i<c0_m15_v1;
){
if (c0_m15_v0.charAt(i)==32&&c0_m15_v0.charAt(i-1)==32){
c0_m15_v0 = this.rmchar(c0_m15_v0, c0_m15_v0);
c0_m15_v1--;
}else if (c0_m15_v0.charAt(i)==9){
c0_m15_v0 = this.rmchar(c0_m15_v0, i);
c0_m15_v1--;
}else i++;
}return c0_m15_v0;
}protected String c0_m16(String c0_m21_p0){
int c0_m16_v3 = c0_m16_v2.length(),c0_m16_v1;
String c0_m16_v0="";
for (c0_m16_v1=0;
c0_m16_v1<c0_m16_v3;
c0_m16_v1++){
c0_m16_v0+=String.valueOf(c0_m16_v2.charAt(c0_m16_v1));
if (c0_m16_v2.charAt(c0_m16_v1)==';
')c0_m16_v0+="\n";
else if (c0_m16_v1>1&&c0_m16_v2.charAt(c0_m16_v1)=='{'&&!(c0_m16_v2.charAt(c0_m16_v1-1)=='='||c0_m16_v2.charAt(c0_m16_v1-2)=='='))c0_m16_v0+="\n";
}return c0_m16_v0;
}protected String[] c0_m17(String c0_m18_p0, String c0_m18_p1){
String c0_m17_v8=inp,c0_m17_v0,c0_m17_v1,c0_m17_v7,c0_m17_v9[]= {"$INV","$INV"};
boolean c0_m17_v2 =false;
int len, c0_m17_v4,c0_m17_v5,c0_m17_v6;
c0_m17_v4 = c0_m17_v8.indexOf('{
');
len = c0_m17_v8.length();
c0_m17_v6=c0_m17_v4;
if (c0_m17_v4!=-1){
c0_m17_v4--;
if (c0_m17_v4>-1&&c0_m17_v8.charAt(c0_m17_v4)==32)c0_m17_v4--;
c0_m17_v0 = "";
c0_m17_v7="";
while (c0_m17_v4>-1&&Character.isJavaIdentifierPart(c0_m17_v8.charAt(c0_m17_v4))){
c0_m17_v0 = Character.toString(c0_m17_v8.charAt(c0_m17_v4))+c0_m17_v0;
c0_m17_v4--;
if (c0_m17_v4>-1&&c0_m17_v8.charAt(c0_m17_v4)==''){
c0_m17_v2 = true;
c0_m17_v4--;
break;
}}c0_m17_v4--;
while (c0_m17_v4>-1&&this.isValidKeywordChar(c0_m17_v8.charAt(c0_m17_v4))){
c0_m17_v7 = c0_m17_v8.charAt(c0_m17_v4)+c0_m17_v7;
c0_m17_v4--;
}if (c0_m17_v7.equals("class")||c0_m17_v0.equals("interface")||c0_m17_v0.equals("enum")){
if (!c0_m17_v0){
c0_m17_v1 = this.c0_v5+String.valueOf(c0_v9);
if (c0_m17_v2.equals(""))c0_m17_v2 = c0_m17_v1;
else c0_m17_v1 = c0_m17_v1 + "_" + c0_m17_v1;
c0_m17_v9[0]=c0_m17_v0;
c0_v2.put(c0_m17_v0, c0_m17_v1);
c0_v9++;
}else {
c0_m17_v0 = "#"+c0_m17_v0;
if (c0_m17_v1.equals(""))c0_m17_v1 = c0_m17_v0;
else c0_m17_v0 = c0_m17_v0 + "_" + c0_m17_v0;
}}}c0_m17_v9[1] = c0_m17_v3;
return c0_m17_v9;
}protected String[] c0_m18(String c0_m18_p0, String c0_m18_p1){
String c0_m18_v8[] = {"$INV","$INV", "$INV"};
String c0_m18_v9=inp,c0_m18_v0,c0_m18_v1;
boolean c0_m18_v2 =false, c0_m18_v7 = false;
int c0_m18_v3, c0_m18_v5,i,c0_m18_v6;
c0_m18_v2 = false;
c0_m18_v5 = c0_m18_v9.indexOf('(');
c0_m18_v3 = c0_m18_v9.length();
c0_m18_v6=c0_m18_v5;
if (c0_m18_v5!=-1){
c0_m18_v5--;
if (c0_m18_v5>-1&&c0_m18_v9.charAt(c0_m18_v5)==32)c0_m18_v5--;
c0_m18_v0 = "";
while (c0_m18_v5>-1&&Character.isJavaIdentifierPart(c0_m18_v9.charAt(c0_m18_v5))){
c0_m18_v0 = Character.toString(c0_m18_v9.charAt(c0_m18_v5))+c0_m18_v0;
c0_m18_v5--;
if (c0_m18_v5>-1&&c0_m18_v9.charAt(c0_m18_v5)==''){
c0_m18_v2 = true;
c0_m18_v5--;
break;
}}if (c0_m18_v5<0||c0_m18_v9.charAt(c0_m18_v5)!=32)c0_m18_v7=true;
if (!c0_m18_v2&&!c0_m18_v7&&!KeywordCheck.chk(c0_m18_v0)){
c0_m18_v1 = this.c0_v7+String.valueOf(c0_v12);
c0_m18_v4 = c0_m18_v4 + "_" + c0_m18_v1;
c0_v12++;
c0_m18_v8[0] = c0_m18_v1;
c0_m18_v8[1] = c0_m18_v0;
}else if (c0_m18_v2){
c0_m18_v0 = "#"+c0_m18_v0;
if (c0_m18_v1.equals(""))c0_m18_v1 = c0_m18_v0;
else c0_m18_v0 = c0_m18_v0 + "_" + c0_m18_v0;
}}c0_m18_v8[2] = c0_m18_v4;
return c0_m18_v8;
}protected String c0_m19(String c0_m19_p0, int c0_m19_p1, String c0_m19_p2, String c0_m19_p3){
Map <String, String> c0_m19_v2 = new HashMap<>();
int sze,c0_m19_v3=0,i,c0_m19_v4=0;
for (i=c0_m19_p1+1;
i<c0_m19_p0.length();
i++)if (c0_m19_p0.charAt(i)==')'){
c0_m19_v3 = i;
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
tmp = c0_m19_v2 + "_" + c0_m19_v0;
c0_m19_v4++;
c0_m19_v2.put(c0_m19_v8.get(sze-1), c0_m19_v1);
c0_m19_p0 = AdvSubstring.replace(c0_m19_p0, c0_m19_v8.get(sze-1), c0_m19_v1);
c0_m19_v8.clear();
}if (!c0_m19_v2.isEmpty()) c0_v0.put(c0_m19_p3, c0_m19_v2);
return c0_m19_v1;
}protected String c0_m20(String c0_m21_p0){
int c0_m20_v0 = c0_m20_v4.length(),c0_m20_v2,c0_m20_v3;
String c0_m20_v1;
do{
c0_m20_v2 = c0_m20_v4.indexOf('[');
if (c0_m20_v2!=-1){
c0_m20_v3 = c0_m20_v2+1;
while (c0_m20_v4.charAt(c0_m20_v3)!=']')c0_m20_v3++;
c0_m20_v1 = c0_m20_v4.substring(c0_m20_v2, c0_m20_v3+1);
c0_m20_v4 = c0_m20_v4.replace(c0_m20_v1, "");
}}while (c0_m20_v2!=-1);
return c0_m20_v4;
}protected String c0_m21(String c0_m21_p0){
int c0_m21_v3 = c0_m21_v2.length(),c0_m21_v1;
String c0_m21_v0="";
for (c0_m21_v1=0;
c0_m21_v1<c0_m21_v3;
c0_m21_v1++){
if (c0_m21_v2.charAt(c0_m21_v1)!='')c0_m21_v0+=String.valueOf(c0_m21_v2.charAt(c0_m21_v1));
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
Iterator<String> c0_m26_v1 = c0_m26_v0.iterator();
while (c0_m26_v1.hasNext())System.out.println(c0_m26_v1.next());
}}