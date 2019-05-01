package javaBrain2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javaBrain2.NeuronInfo.Switch;

public class UpDownGrade {
	/**
	 * y=1/(2-x) up
	 * y=2-1/x down (up limit=1, down limit=0(minus ni nattara 0 wo kagen tosuru))
	 * up
	 * (0<x<=1/2/(1+a))			y=x/(1-2ax)
	 * (1/2/(1+a)<x<=1/2)		y=1-x/(2ax-1)
	 * (1/2<x<1)				y=1+(x-1)/(-2ax+2a+1)
	 * down
	 * (0<x<=1/2)				y=x/(2ax+1)
	 * (1/2<x<(2a+1)/2/(a+1))	y=(x-1)/(2ax-2a+1)
	 * ((2a+1)/2/(a+1)<x<=1)	y=1+(x-1)/(2ax-2a+1)kotirani henkou suru
	 *
	 * shuturyoku no tuyosa wo kagen surunomi nyuuryoku no hannnouno tuyosa ha kaenai
	 * henkou file ->desttimeamo No .txt (DelayAmount.java) neuroinfo No xml
	 * sankou file ->count No .txt (CountRecord.java CountAnalize.java)
	 * input mo output mo count ga huetara up sase hettara down saseru
	 */
	final private int countupper=32;
	final private int countdowner=1;
	protected double swtilt=3;//katamuki
	protected double upperbord=1/2/(1+swtilt);
	protected double downerbord=(2*swtilt+1)/2/(swtilt+1);
	public void switchchange(int No,CountRecord cr) {
		NeuronInfo ni=new NeuronInfo(No);
		ni.read(No);
		int swborder=cr.sw.entcborder;
		int upper=countupper*swborder/1000;
		int downer=countdowner*swborder/1000;
		for (int i=0;i<cr.sw.toNo.length;i++) {
			List<Switch> sw=new ArrayList<NeuronInfo.Switch>();
			if(cr.sw.count[i]>=upper) {
				if(ni.SwChanged()==false) {
					ni.swinport();
					sw=ni.getSwList();
				}
				ni.swamountChange(cr.sw.toNo[i], cr.sw.delay[i], up(sw.get(i).amount));
			}
			if(cr.sw.count[i]<=downer) {
				if(ni.SwChanged()==false) {
					ni.swinport();
					sw=ni.getSwList();
				}
				ni.swamountChange(cr.sw.toNo[i], cr.sw.delay[i], down(sw.get(i).amount));
			}
		}
		ni.export();
		try {
			ni.write();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		ni.updatefiles();
	}
	public double up(double amount) {
		double ans=0;
//		if(upperbord==0) {
//			upperbord=1/2/(1+swtilt);
//		}
		if((amount>=1)||(amount<=0)) {
			System.out.println("updown up error");
		}
		if(amount<=upperbord) {
			ans=amount/(1-2*swtilt*amount);
		}else if((amount>upperbord)&&(amount<=0.5)) {
			ans=1-amount/(2*swtilt*amount+1);
		}else if(amount>0.5) {
			ans=1+(amount-1)/(2*swtilt-2*swtilt*amount+1);
		}

		return ans;
	}
	public double down(double amount) {
		double ans;
		if((amount>=1)||(amount<=0)) {
			System.out.println("updown down error");
			return 0;
		}
		if(amount>=downerbord) {
			ans=1+(amount-1)/(2*swtilt*amount-2*swtilt+1);
		}else if(amount<1/2) {
			ans=amount/(2*swtilt*amount+1);
		}else {
			ans=(amount-1)/(2*swtilt*amount-2*swtilt+1);
		}
		return ans;
	}

}
