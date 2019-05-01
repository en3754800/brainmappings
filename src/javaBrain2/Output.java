package javaBrain2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * brain no shuturyoku wo suru type no unit number wo sitei suru
 * file keisiki
 * outputmap.txt
 * 300-320;356;367;400
 * @author okada.yousuke
 *
 */
public class Output {
	/**
	 * No->time->value
	 */
	public HashMap<Integer,HashMap<Integer,Double>> values=new HashMap<Integer,HashMap<Integer,Double>>();
	public HashMap<Integer,HashMap<Integer,Double>> formoutput;
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}
	/**
	 * kokono outputmap ni tuika suru
	 * No no key ga nakutemo kinisinai
	 */
	public void add(int No,int time,double amount) {
		if(this.values.containsKey(No)==false) {
			return;
		}else if(this.values.get(No).containsKey(time)==false) {
			this.values.get(No).put(time,amount);
		}else {
			this.values.get(No).replace(time, this.values.get(No).getOrDefault(time, 0.0)+amount);
		}
	}
	public void makemap() {
		File mf=new File("outputmap.txt");
		String mapstr="";
		try {
			BufferedReader br=new BufferedReader(new FileReader(mf));
			String str =br.readLine();
			while(str!=null) {
				mapstr=mapstr+str;
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
		String[] mapstr2=mapstr.split(";");
		for(int i=0;i<mapstr2.length;i++) {
			if(mapstr2[i].contains("-")) {
				String[] mapstr3=mapstr2[i].split("-");
				for(int j=Integer.parseInt(mapstr3[0]);j<=Integer.parseInt(mapstr3[1]);j++) {
					this.values.put(j,new HashMap<Integer,Double>());
				}
			}else {
				this.values.put(Integer.parseInt(mapstr2[i]),new HashMap<Integer,Double>());
			}
		}


	}
	public void cleartime() {
		for(int No:this.values.keySet()) {
			this.values.get(No).clear();
		}
	}
	public Output output() {
		formoutput=new HashMap<Integer,HashMap<Integer,Double>>();
		for(int No:values.keySet()) {
			for(int time:values.get(No).keySet()) {
				if(formoutput.containsKey(time)==false) {
					formoutput.put(time,new HashMap<Integer,Double>());
				}else if(formoutput.get(time).containsKey(No)) {
					formoutput.get(time).replace(No,formoutput.get(time).get(No)
							+values.get(No).get(time));
				}else {
					formoutput.get(time).put(No,values.get(No).get(time));
				}
			}
			values.get(No).clear();
		}
		return this;
	}

}
