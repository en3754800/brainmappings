package javaBrain2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
/*
 * file keisiki ha
 * count+No+.txt
 * 23/2000---1000(entirecount)---2000(entcborder);34/1;45/20;55(0)/3/200;55(1)/5/400
 */
public class CountRecord {
	public int No;
	public int Nocount;
	public int entirecount;
	public Switch sw;
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}
	public CountRecord(int No) {
		this.No=No;
	}
	public void read() {
		File rf=new File(CommonFunc.getPath( "count",No,".txt"));
		String costr="";
		try {
			BufferedReader br=new BufferedReader(new FileReader(rf));
			String str =br.readLine();
			while(str!=null) {
				costr=costr+str;
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
		String[] tmp1=costr.split("---");
		String[] tmpNo=tmp1[0].split("/");
		this.Nocount=Integer.parseInt(tmpNo[0]);
		this.entirecount=Integer.parseInt(tmpNo[1]);
		String[] tmp2=tmp1[2].split(";");
		this.sw=new Switch(tmp2.length-1);
		this.sw.entcborder=Integer.parseInt(tmp2[0]);
		this.sw.entirecount=Integer.parseInt(tmp1[1]);
		for(int i=1;i<tmp2.length;i++) {
			String[] tmpsw=tmp2[i].split("/");
			if(Pattern.matches("(",tmpsw[0])) {
				String order=Pattern.compile("(\\d)").matcher(tmpsw[0]).group(1);
				order.replace("(", "");
				order.replace(")","");
				this.sw.toNo[i]=Integer.parseInt(tmpsw[0].split("(")[0]);
				this.sw.toNoorder[i]=Integer.parseInt(order);
				this.sw.delay[i]=Integer.parseInt(tmpsw[2]);
			}else {
				this.sw.toNo[i]=Integer.parseInt(tmpsw[0]);
			}
			this.sw.count[i]=Integer.parseInt(tmpsw[1]);
		}
	}
	/**
	 * entirecount ga tokutei no atai ijou nara shuturyokuno resize shori wo okonau
	 * sw count wo check site switchchange wo kidou file wo henkou sita ato owaru
	 * time kijun ni henkou yotei
	 */
	public void entcountcheck() {
		/*
		 * count no genkai wo yomikomu
		 * readentirecountNo();
		 * readentirecountSw();
		 */
//		if(this.Nocount>=this.sw.) {
//
	//	}
		if(sw.entirecount>=sw.entcborder) {
			UpDownGrade ud=new UpDownGrade();
			ud.switchchange(No,this);
			sw.entirecount=0;
			for(int i=0;i<sw.toNo.length;i++) {
				sw.count[i]=0;
			}
		}
		//konoato No gawa no check method wo kakitai
	}
	public void write(){
		StringBuffer sb=new StringBuffer();
		sb.append(Nocount);
		sb.append("/");
		sb.append(entirecount);
		sb.append("---");
		sb.append(sw.entirecount);
		sb.append("---");
		sb.append(sw.entcborder);
		sb.append(";");
		for(int i=0;i<sw.toNo.length;i++) {
			if(sw.toNoorder[i]!=0) {
				sb.append(sw.toNo[i]);
				sb.append("(");
				sb.append(sw.toNoorder[i]);
				sb.append(")/");
				sb.append(sw.count[i]);
				sb.append("/");
				sb.append(sw.delay[i]);
				sb.append(";");
			}else {
				sb.append(sw.toNo[i]);
				sb.append("/");
				sb.append(sw.count[i]);
				sb.append(";");
			}

		}
		sb.deleteCharAt(sb.lastIndexOf(";"));
		try {
			File file=new File(CommonFunc.getPath("count",No,".txt"));
			FileWriter filewrite=new FileWriter(file);
			filewrite.write(sb.toString());
			filewrite.close();
		}catch(IOException e) {
			System.out.println(e);
		}
	}
	/**
	 * Nocount wo No no count ni plus suru
	 * Switch keisiki no count list kara Switch bubun no count wo plus suru
	 * switch ha hairetu ni natteite No ga atarasii mono ga deru tabini
	 * youso wo huyasite irenaosu
	 * @param Nocount
	 * @param nomap
	 * @param entirecount
	 */
	@SuppressWarnings("unlikely-arg-type")
	public void add(int Nocount,Switch nomap,int entirecount) {
		this.Nocount+=Nocount;
		this.entirecount+=entirecount;
		this.sw.entirecount+=entirecount;
		for(int i=0;i<nomap.toNo.length;i++) {
			List<Integer> ar=Arrays.asList(this.sw.toNo);
			if(ar.contains(nomap.toNo[i])) {
				if(nomap.toNoorder[i]!=0) {
					int j=ar.indexOf(nomap.toNo[i]);
					this.sw.count[j]+=nomap.count[i];
				}else {
					toNos: for(int k=0;k<this.sw.toNo.length;k++) {
						if(this.sw.toNo[k]==nomap.toNo[i]) {
							if(this.sw.toNoorder[k]==nomap.toNoorder[i]) {
								this.sw.count[k]+=nomap.count[i];
								break toNos;
							}
						}
					}

				}
			}else {
				int newcount=this.sw.toNo.length;
				Switch presw=this.sw;
				this.sw=null;
				this.sw=new Switch(newcount+1);
				ArrayList<Integer> tmplist=new ArrayList<Integer>(Arrays.asList(presw.toNo));
				tmplist.add(nomap.toNo[i]);
				this.sw.toNo=tmplist.toArray(new Integer[newcount+1]);
				tmplist.clear();
				tmplist=new ArrayList<Integer>(Arrays.asList(presw.count));
				tmplist.add(nomap.count[i]);
				this.sw.count=tmplist.toArray(new Integer[newcount+1]);
				tmplist.clear();
				tmplist=new ArrayList<Integer>(Arrays.asList(presw.delay));
				tmplist.add(nomap.delay[i]);
				this.sw.delay=tmplist.toArray(new Integer[newcount+1]);
				tmplist.clear();
				tmplist=new ArrayList<Integer>(Arrays.asList(presw.toNoorder));
				tmplist.add(nomap.toNoorder[i]);
				this.sw.toNoorder=tmplist.toArray(new Integer[newcount+1]);
				tmplist.clear();
			}
		}

	}
	public static class Switch{
		Integer[] toNo;
		Integer[] count;
		int entirecount=0;
		int entcborder;
		Integer[] toNoorder;
		Integer[] delay;
		public Switch(int swcount) {
			this.toNo=new Integer[swcount];
			this.count=new Integer[swcount];
			this.toNoorder=new Integer[swcount];
			this.delay=new Integer[swcount];
		}
	}
}
