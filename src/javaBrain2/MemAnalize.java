package javaBrain2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import javaBrain2.Brain0controll.Think.ShthinkMem;
import javaBrain2.DelayAmount.DAReturn;





public class MemAnalize {
	public MemDataList list;
	/**
	 * MemAnalize.list.time.get(time).unit.get(No).amount wo kasan site
	 * CountAnalize ni single to switch wo count delay to amount wo
	 * kaette kurunara kaesu method
	 * @param No
	 * @param amount
	 * @param time
	 * @param border
	 * @param startNo
	 * @param delay
	 * @return
	 */
	public DAReturn add(int No,double amount,int time,double border,int startNo,int delay) {
		this.tiNocheck(No,time);
		this.list.time.get(time).unit.get(No).amount+=amount;
		if((this.list.time.get(time).unit.get(No).repressed=true) ||
				(this.list.time.get(time).unit.get(No).overflowed=true)){
			return null;
		}else if(this.list.time.get(time).unit.get(No).amount<border){
			if(this.list.time.get(time).unit.get(No).swstart==null) {
				this.list.time.get(time).unit.get(No).swstart=new ArrayList<Integer>();
				this.list.time.get(time).unit.get(No).swtime=new ArrayList<Integer>();
			}else {
				this.list.time.get(time).unit.get(No).swstart.add(startNo);//kokomade toutatu sita unit de border ika de count dekinai bunwo hojisuru
				this.list.time.get(time).unit.get(No).swtime.add(delay);
			}
			return null;
		}else if(this.list.time.get(time).unit.get(No).amount>=border) {
			if(this.list.time.get(time).unit.get(No).trued==false) {
				this.list.time.get(time).unit.get(No).trued=true;
				CommonVal.contAnalize.add(No, startNo, time, delay);
				if(this.list.time.get(time).unit.get(No).swstart!=null) {
					for(int i=0;i<this.list.time.get(time).unit.get(No).swstart.size();i++) {
						CommonVal.contAnalize.add(No,this.list.time.get(time).unit.get(No).swstart.get(i),
								time,this.list.time.get(time).unit.get(No).swtime.get(i));//border wo mitasu maeni toutatu sita unit wo count ni tuika
					}
				}
				return new DelayAmount(No).conversion(time);
			}if(this.list.time.get(time).unit.get(No).amount>=1){
				if(this.list.time.get(time).unit.get(No).ofcount==0) {
					this.list.time.get(time).unit.get(No).ofcount=1;
					this.list.time.get(time+100).unit.get(No).overflowed=true;
					CommonVal.contAnalize.add(No, startNo, time, delay);
					return null;
				}if(((this.list.time.get(time).unit.get(No).ofcount+1)^2)>=
						this.list.time.get(time).unit.get(No).amount){
					if(this.list.time.get(time).unit.get(No).trued==true) {
						this.list.time.get(time).unit.get(No).ofcount+=1;//overflow sita baai
						for(int i=2;i<this.list.time.get(time).unit.get(No).ofcount;i++) {
							this.list.time.get(time+i*100).unit.get(No).overflowed=true;
						}
						CommonVal.contAnalize.add(No, startNo, time, delay);
						return null;
					}
				}
			}
		}else{
			CommonVal.contAnalize.add(No, startNo, time, delay);
			return null;
		}
		return null;
	}
	private void tiNocheck(int No,int time) {
		if(this.list.time.containsKey(time)==false) {
			this.list.time.put(time,new TimeToUnitMem());
		}if(this.list.time.get(time).unit.containsKey(No)==false) {
			this.list.time.get(time).unit.put(No,new UnitMem());
			this.list.time.get(time).unit.get(No).No=No;
		}
	}
	/**
	 * ??
	 * @param shmem
	 */
	public void input(ShthinkMem shmem) {
		for(int i=0;i<shmem.list.destlist.size();i++) {
			int time=shmem.list.timelist.get(i);
			int No=shmem.list.nowlist.get(i);
			if(this.list.time.get(time)==null) {
				this.list.time.put(time,new TimeToUnitMem());
				this.list.time.get(time).time=time;
				this.list.time.get(time).unit=new HashMap<Integer,UnitMem>();
			}
			if(this.list.time.get(time).unit.get(No)==null) {
				this.list.time.get(time).unit.put(No,new UnitMem());
				unitmemconstruct(this.list.time.get(time).unit.get(No),No);
			}
			/*
			 * singou no tuyosa wo kakeru dousa ni taiou sita kansuu wo tukuru
			 */
			double amvalue=1*0.3;
			BigDecimal ambd=new BigDecimal(amvalue);
			double amount=ambd.setScale(3,BigDecimal.ROUND_DOWN).doubleValue();
			BigDecimal enbd=new BigDecimal(1);
			double entireamount=enbd.setScale(3,BigDecimal.ROUND_DOWN).doubleValue();
			unitmemadd(this.list.time.get(time).unit.get(No),amount,entireamount);

		}
	}
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		MemAnalize ana=new MemAnalize(0);
	}
	public MemAnalize(int starttime) {
		this.list=new MemDataList();
		this.list.startpointtime=starttime;
	}
	public void construct() {
		if(this.list.time==null) {
			this.list.time=new HashMap<Integer,TimeToUnitMem>();
		}
	}
	private void unitmemconstruct(UnitMem mem,int No) {
		mem.No=No;
		mem.entireamount=0;
		mem.amount=0;
	}
	private void unitmemadd(UnitMem mem,double amount,double entireamount) {
		mem.amount+=amount;
		mem.entireamount+=entireamount;
	}
	public class MemDataList{
		public int startpointtime;
		public HashMap<Integer,TimeToUnitMem> time;//key is time ->TimeToUnitMem
	}
	public class TimeToUnitMem{
		public int time;
		public HashMap<Integer,UnitMem> unit;//key is unit No. ->UnitMem
	}
	public class UnitMem{
		public int No;
		public double amount=0;
		public double entireamount=0;
		public boolean overflowed=false;
		public int ofcount=0;
		public boolean repressed=false;
		public boolean trued=false;
		public ArrayList<Integer> swstart;
		public ArrayList<Integer> swtime;
	}
}
