package javaBrain2;

import java.util.ArrayList;
import java.util.HashMap;

public class CountAnalize {
	public HashMap<Integer,Time> time;//Integer->time,Time.NoCount<Integer->No,Integer->count>
	public int addcount=0;
	public class Time{
		public HashMap<Integer,Integer> NoCount;
		public HashMap<Switch,Integer> switchCount;
	}
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}
	public CountAnalize() {
		this.time=new HashMap<Integer,Time>();
	}
	public class Switch{
		int start;
		int end;
		int delay;
		public Switch(int start,int end,int delay) {
			this.start=start;
			this.end=end;
			this.delay=delay;
		}
	}
	/**
	 * switch no count wo hitotu huyasu addcount ga 1000 ijou nara flush wo okonau
	 * @param No
	 * @param switchst
	 * @param time
	 * @param delay
	 */
	public void add(int No,int switchst,int time,int delay) {
		if(this.time.containsKey(time)==false) {
			this.time.put(time,new Time());
			this.time.get(time).NoCount=new HashMap<Integer,Integer>();
			this.time.get(time).switchCount=new HashMap<Switch,Integer>();
		}
		if(this.time.get(time).NoCount.containsKey(No)==false) {
			this.time.get(time).NoCount.put(No,1);
		}else {
			this.time.get(time).NoCount.replace(No, this.time.get(time).NoCount.get(No)+1);
		}
		Switch sw=new Switch(switchst,No,delay);
		if(this.time.get(time).switchCount.containsKey(sw)) {
			this.time.get(time).switchCount.put(sw,1);
		}else {
			this.time.get(time).switchCount.replace(sw,this.time.get(time).switchCount.get(sw)+1);
		}
		this.addcount++;
		if(this.addcount>=1000) {
			//flush suru method wo tukuru
			flush(1000);
			addcount=0;
		}
	}
	/**
	 * time->Map<Integer(time 200,500etc..),Time>->NoCount->Map<Integer(No),Integer(count)>
	 * 											 ->switchCount->Map<Switch,Integer(count)>
	 * time gotono count wo No gotoni ikkatu kasan de matomeru
	 *
	 * @param entirecount
	 */
	 public void flush(int entirecount) {
		HashMap<Integer,Integer> tmpNomap=new HashMap<Integer,Integer>();
		HashMap<Switch,Integer> tmpswmap=new HashMap<Switch,Integer>();
		for(int time: this.time.keySet()) {
			for(int No: this.time.get(time).NoCount.keySet()) {
				if(tmpNomap.containsKey(No)) {
					tmpNomap.replace(No,tmpNomap.get(No)+this.time.get(time).NoCount.get(No));
				}else {
					tmpNomap.put(No,this.time.get(time).NoCount.get(No));
				}
			}
			this.time.get(time).NoCount.clear();
			for(Switch sw: this.time.get(time).switchCount.keySet()) {
				if(tmpswmap.containsKey(sw)) {
					tmpswmap.replace(sw, tmpswmap.get(sw)+this.time.get(time).switchCount.get(sw));
				}else {
					tmpswmap.put(sw,this.time.get(time).switchCount.get(sw));
				}
			}
			this.time.get(time).switchCount.clear();
			this.time.remove(time);
		}
		ToCountRec rec=new ToCountRec();
		for(int No:tmpNomap.keySet()) {
			if(rec.No.contains(No)) {
				int recno=0;
				for(int i=0;i<rec.No.size();i++){
					if(rec.No.get(i)==No) {
						recno=i;
						break;
					}
				}
				rec.count.set(recno,rec.count.get(recno)+tmpNomap.get(No));
			}else {
				rec.No.add(No);
				rec.count.add(tmpNomap.get(No));
				rec.sw.add(new SubSwitch());
			}
		}
		for(Switch sw:tmpswmap.keySet()) {
			int swcount=tmpswmap.get(sw);
			int No=sw.start;
			if(rec.No.contains(No)) {
				int swno=0;
				for(int i=0;i<rec.No.size();i++) {
					if(rec.No.get(i)==No) {
						swno=i;
						break;
					}
				}
				if(rec.sw.get(swno).toNo.contains(sw.end)) {
					int swde=0;
					for(int i=0;i<rec.sw.get(swno).toNo.size();i++) {
						if(rec.sw.get(swno).toNo.contains(sw.end)) {
							if(rec.sw.get(swno).delay.get(i)!=sw.delay) {
								continue;
							}
							swde=i;
							break;
						}
					}
					rec.sw.get(swno).count.set(swde, rec.sw.get(swno).count.get(swde)+swcount);
				}else {
					rec.sw.get(swno).toNo.add(sw.end);
					rec.sw.get(swno).delay.add(sw.delay);
					rec.sw.get(swno).count.add(swcount);
				}


			}else {
				rec.No.add(No);
				rec.count.add(0);
				rec.sw.add(new SubSwitch());
				rec.sw.get(rec.sw.size()-1).toNo.add(sw.end);
				rec.sw.get(rec.sw.size()-1).count.add(swcount);
				rec.sw.get(rec.sw.size()-1).delay.add(sw.delay);
				rec.sw.get(rec.sw.size()-1).entirecount=entirecount;

			}

		}
		for(int i=0;i<rec.No.size();i++) {
			int No=rec.No.get(i);
			CountRecord.Switch sw1=toSwitch(rec.sw.get(i));
			CountRecord conrec=new CountRecord(No);
			conrec.read();//tuika
			conrec.add(rec.count.get(i),sw1,entirecount);
			conrec.entcountcheck();
			conrec.write();
		}


	}
	public class ToCountRec{
		public ArrayList<Integer> No=new ArrayList<Integer>();
		public ArrayList<Integer> count=new ArrayList<Integer>();
		public ArrayList<SubSwitch> sw=new ArrayList<SubSwitch>();
	}
	public class SubSwitch{
		public ArrayList<Integer> toNo=new ArrayList<Integer>();
		public ArrayList<Integer> count=new ArrayList<Integer>();
		public ArrayList<Integer> delay=new ArrayList<Integer>();
		public ArrayList<Integer> toNoorder=new ArrayList<Integer>();
		public int entirecount;
	}
	public CountRecord.Switch toSwitch(SubSwitch rec){
		int size=rec.toNo.size();
		CountRecord.Switch ret=new CountRecord.Switch(size);
		ret.toNo=rec.toNo.toArray(new Integer[size]);
		ret.toNoorder=rec.toNoorder.toArray(new Integer[size]);
		ret.delay=rec.delay.toArray(new Integer[size]);
		ret.count=rec.count.toArray(new Integer[size]);
		ret.entirecount=rec.entirecount;
		return ret;
	}
	/**
	 * quicksort key->narabekaeru hensuu d->junban no teigi left->0 right->d.length-1
	 * @param key
	 * @param d
	 * @param left
	 * @param right
	 */
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
