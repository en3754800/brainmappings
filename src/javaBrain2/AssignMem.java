/**
 *
 */
package javaBrain2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author okada.yousuke
 *
 */
public class AssignMem {


/**
 * assign file no keisiki
 * 23,24,25,26-34/78,79,80,81-24/
 *
 */
	static int[] exist=new int[10000];

	public void construct() {
		File assfileexs=new File("assignment.txt");
		if(assfileexs.exists()) {
			Arrays.fill(exist, 0);
			String str="";
			try {
				BufferedReader fileread=new BufferedReader(new FileReader(assfileexs));
				String tmp =fileread.readLine();
				while(tmp!=null) {
					str=str+tmp;
					tmp=fileread.readLine();
				}
				fileread.close();
			}catch(FileNotFoundException e) {
				System.out.println(e);
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			String[] list1=str.split("/");
			for (String tmplist1: list1) {
				String[] list2=tmplist1.split("-");
				String[] existstrlist=list2[0].split(",");
				for(String existstr: existstrlist) {
					exist[Integer.parseInt(existstr)]=1;
				}
			}
		}else {
			try {
				File assfile=new File("assignment.txt");
				FileWriter filewrite=new FileWriter(assfile);
				filewrite.write(0);/** 0 wo ikkai kinyuu site owaru **/
				filewrite.close();
			}catch(IOException e) {
				System.out.println(e);
			}
		}
		Arrays.fill(exist, 0);

	}
	/**
	 *atarasiku assignment ni wariateru
	 * @param length
	 */

	public static int[] newass(int length) {

		int assst=0;
		boolean decided=false;
		while(decided==false) {
			int start=(int)(Math.random()*10000);/** assignmem no nagasa **/
			int count=0;
			for (int i=start;i<start+length;i++) {
				if (exist[i]==0) {/** kokonotameni zanteitekini exist wo 0 ni construct suru **/
					count++;
				}
			}
			if (count==length){
				decided=true;
				assst=start;
			}
		}
		assnewwrite(assst,assst+length);
		int[] ret= {assst,assst+length};
		return ret;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}
	/**
	 * atarasiku assignment.txt ni tuika suru
	 * itibann sita no gyou
	 * @param start
	 * @param end
	 */

	public static void assnewwrite(int start,int end) {
		File fn=new File("assignment.txt");
		try {
			BufferedReader rb =new BufferedReader(new FileReader(fn));
			PrintWriter wb =new PrintWriter(new FileWriter(fn));
			String line;
			while((line=rb.readLine())!=null) {
				wb.println(line);
			}
			rb.close();
			wb.print(start);
			exist[start]=1;
			if(start<end) {
				for(int i=start+1;i<end;i++) {
					wb.print(","+i);
					exist[i]=1;
				}
			}
			wb.print("-50");
			for (int i=0;i<end-start;i++) {
				wb.print(",50");
			}
			wb.print("/");
			wb.close();

		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


	}
	/**
	 *  1 zutu herasite 10 ika no mempry wo sakujo suru
	 */
	public static void decrementanddelete() {
		File fn=new File("assignment.txt");
		String instr=new String();
		try {
			BufferedReader br=new BufferedReader(new FileReader(fn));
			String str =br.readLine();
			while(str!=null) {
				instr=instr+str;
				str=br.readLine();
			}///null_ni_nattara_yomikomi_owaru_saigono_str_ha_kanarazu_null

			br.close();

		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

		}
		instr.replaceAll("\n", "");
		String[] spl=instr.split("/");
		String[] result=new String[1000];
		int count=0;
		for (int i=0;i<spl.length;i++) {
			String[] tmp=spl[i].split("-");
			int tmpint=Integer.valueOf(tmp[1])-1;
			if(tmpint>=10) {
				result[count]=tmp[0]+"-"+tmpint;
				count++;
			}else {
				String[] sten=tmp[0].split(",");
				for (int j=0;j<sten.length;j++) {
					exist[Integer.parseInt(sten[j])]=0;
				}
			}
		}
		String resstr="";
		for(int i=0;i<(int)(count/10);i++) {
			for(int j=0;j<10;j++) {
				resstr=resstr+spl[10*i+j]+"/";
			}
			resstr=resstr+"\n";
		}
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(fn));
			bw.write(resstr);
			bw.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}


	}
	/**
	 * 10 tanni gotoni kaigyou suru ookii junnni narabekaeru
	 * (notini jissou surukamo? onaji memory wo matomeru)
	 */

	public static void sort() {
		File fn=new File("assignment.txt");
		String instr=new String();
		try {
			BufferedReader br=new BufferedReader(new FileReader(fn));
			String str =br.readLine();
			while(str!=null) {
				instr=instr+str;
				str=br.readLine();
			}///null_ni_nattara_yomikomi_owaru_saigono_str_ha_kanarazu_null

			br.close();

		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

		}
		instr.replaceAll("\n", "");
		String[] spl=instr.split("/");
		String[] result=new String[spl.length];
		int count=0;
		int[] amount=new int[spl.length];
		Arrays.fill(amount, -1);
		for (int i=0;i<spl.length;i++) {
			String[] tmp=spl[i].split("-");
			amount[i]=Integer.parseInt(tmp[1]);
			result[i]=spl[i];
		}
		quick_sort(result,amount,0,amount.length-1);
		String resstr="";
		for(int i=0;i<(int)(count/10);i++) {
			for(int j=0;j<10;j++) {
				resstr=resstr+spl[10*i+j]+"/";
			}
			resstr=resstr+"\n";
		}
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(fn));
			bw.write(resstr);
			bw.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * tukatta memory no yuusenndo wo 5 ageru(100 limit)
	 */

	public static void usedincrement(int[] usedlist) {

	}

	/**
	 * (yoku tukau monowo erannde yuusenndo wo ageru)
	 * locatopn (23,34) amount(2,4)
	 * ittisita basho no amount wo sitei suu dake ageru
	 *
	 * henkou! assignment.txt 24,25,26,27-20,15,18,30
	 */
	public static void chooseincrement(int[] location,int[] amount) {
		File fn=new File("assignment.txt");
		String instr=new String();
		int linenumber=0;

		try {
			LineNumberReader lr=new LineNumberReader(new FileReader(fn));
			PrintWriter pw=new PrintWriter(fn);
			String str="";
			while((str =lr.readLine())!=null) {
				linenumber=lr.getLineNumber();
				str.replace("\r\n", "");
				String[] strarr=str.split("/");
				for(String str3:strarr) {
					String[] set=str3.split("-");
					String[] loc=set[0].split(",");
					String[] amo=set[1].split(",");
					for(int i=0;i<loc.length;i++) {
						int locnum=Integer.parseInt(loc[i]);
						for(int j=0;j<location.length;j++) {
							if(location[j]==locnum) {
								int tmp=Integer.parseInt(amo[i])+amount[j];
								amo[i]=String.valueOf(tmp);
							}
						}
					}
					StringBuilder sb=new StringBuilder();
					sb.append(set[0]);
					sb.append("-");
					for(String amostr:amo) {
						sb.append(amostr);
						sb.append(",");
					}
					sb.delete(sb.length(), sb.length());
					pw.println(new String(sb));
				}
			}
			lr.close();
			pw.close();

		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();

		}

	}


	public static void replace(LineNumberReader in,PrintWriter out,int linenumber,String s) throws IOException{
		String line;
		while((line=in.readLine())!=null) {
			if(in.getLineNumber()==linenumber) {
				out.println(s);
			}else {
				out.println(line);
			}
		}
	}
	/**
	 * ryouiki ga aru tokoni tuika 50 de tuika nakereba atarasii ryouiki wo settei
	 */
	public static void paramadd(int[] location,int[] length) {
		try {
			BufferedReader br=new BufferedReader(new FileReader("assignment.txt"));
			PrintWriter pw=new PrintWriter(new FileWriter("assignment.txt"));
			String line=new String();
			String[]newasslist=new String[1000];
			int[]newasslenlist=new int[1000];
			int newasscount=0;
			while((line=br.readLine())!=null) {
				String writeline="";
				String[] linearr=line.split("/");
				StringBuffer sb2=new StringBuffer();
				for(String line2:linearr) {
					String[] set=line2.split("-");
					Pattern pt=Pattern.compile("\n");
					Matcher match=pt.matcher(set[1]);
					boolean lnmatch =match.find();
					set[1]=match.replaceAll("");
					String[] loc=set[0].split(",");
					int loccount=0;
					boolean changed=false;
					inter: for(int location2:location) {
						if(location2==Integer.parseInt(loc[0])) {
							int start=location2;
							int end=Integer.parseInt(loc[loc.length-1]);
							int count=0;
							for(int i=0;i<length[loccount];i++) {
								if(exist[end+i]==0) {
									count++;
								}
							}
							if(count==length[loccount]) {
								for(int i=0;i<length[loccount];i++) {
									exist[end+i]=1;
								}
								StringBuffer sb=new StringBuffer();
								for(int i=start;i<start+loc.length+length[loccount];i++) {
									sb.append(i);
									sb.append(",");
								}
								sb.deleteCharAt(sb.lastIndexOf(","));
								sb.append("-");
								sb.append(set[1]);
								for(int i=0;i<length[loccount];i++) {
									sb.append(",50");
								}
								if(lnmatch==true) {
									sb.append("\n");
								}
								sb2.append(sb.toString());
								changed=true;
								break inter;
							}else {
								newasslist[newasscount]=line2;
								newasslenlist[newasscount]=length[loccount];
								break inter;
							}
						}else {

						}
						loccount++;
					}
					if(changed==false) {
						sb2.append(line2);
					}sb2.append("/");
				}

				writeline=sb2.toString();
				pw.write(writeline);
			};

			br.close();
			int count2=0;
			for (int i=0;i<newasslist.length;i++) {
				int[] newlist=newass(newasslist[i].length()+newasslenlist[i]);
				StringBuffer sb3=new StringBuffer();
				for(int newloc: newlist) {
					exist[newloc]=1;
					sb3.append(newloc);
					sb3.append(",");
				}
				sb3.deleteCharAt(sb3.lastIndexOf(","));
				sb3.append("-");
				String tmp=newasslist[i].split("-")[1];
				tmp=Pattern.compile("\n").matcher(tmp).replaceAll("");
				sb3.append(tmp);
				for(int j=0;j<newasslenlist[i];j++) {
					sb3.append(",50");
				}
				if(count2==10) {
					sb3.append("\n");
					count2=0;
				}
				sb3.append("/");
				count2++;
				pw.write(sb3.toString());
			}
			System.out.print(newasslenlist[0]);
			System.out.print(newasslist[0]);
			pw.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}

	}
	class Exist{
		public int[] add(int[] exist,int start,int end) {
			for (int i=start;i<end-start;i++) {
				exist[i]=1;
			}
			return exist;
		}
		public int[] delete(int[] exist,int start,int end) {
			for (int i=start;i<end-start;i++) {
				exist[i]=0;
			}
			return exist;
		}
	}
    static void quick_sort(Object[] key,int[] d, int left, int right) {
        if (left>=right) {
            return;
        }
        int p = d[(left+right)/2];
        int l = left, r = right, tmp;Object tmpkey;
        while(l<=r) {
            while(d[l] < p) { l++; }
            while(d[r] > p) { r--; }
            if (l<=r) {
                tmp = d[l]; d[l] = d[r]; d[r] = tmp;
                tmpkey=key[l];key[l]=key[r];key[r]=tmpkey;
                l++; r--;
            }
        }
        quick_sort(key,d, left, r);  // ピボットより左側をクイックソート
        quick_sort(key,d, l, right); // ピボットより右側をクイックソート
    }

}
