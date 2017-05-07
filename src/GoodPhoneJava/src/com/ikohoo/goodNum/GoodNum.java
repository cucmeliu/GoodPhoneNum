package com.ikohoo.goodNum;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoodNum {

	private static List<Integer> goodList = Arrays.asList(1, 3, 5, 6, 7, 8, 11,
			13, 15, 16, 17, 18, 21, 23, 24, 25, 29, 31, 32, 33, 35, 37, 39, 41,
			45, 47, 48, 52, 57, 63, 65, 67, 68, 73, 81);
	private static List<PhoneExp> phoneExps = new ArrayList<PhoneExp>();

	private class PhoneExp {
		@Override
		public String toString() {
			return "PhoneExp [num=" + num + ", expl=" + expl + ", flag=" + flag
					+ "]";
		}

		private int num;
		private String expl;
		private String flag;

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

		public String getExpl() {
			return expl;
		}

		public void setExpl(String expl) {
			this.expl = expl;
		}

		public String getFlag() {
			return flag;
		}

		public void setFlag(String flag) {
			this.flag = flag;
		}
	}

	public static void main(String[] args) {
		List<Integer> rstList = new ArrayList<Integer>();
		GoodNum gn = new GoodNum();
		List<PhoneExp> peList = gn.loadData();

		for (int i = 1; i < 10000; i++) {
			if (gn.isGood(i)) {
				rstList.add(i);
				// System.out.println(i);
				System.out.println("num=" + i + ", calNum:" + gn.recalNum(i));
				System.out.println(
						", exp:"
						+ gn.getPhoneExp(gn.recalNum(i), peList).toString());

			}
		}
	}

	public PhoneExp getPhoneExp(int idx, List<PhoneExp> peList) {
		for (int i = 0; i < peList.size(); i++) {
			if (peList.get(i).getNum() == idx) {
				return peList.get(i);
			}
		}
		return null;

	}

	public List<PhoneExp> loadData() {
		List<PhoneExp> peList = new ArrayList<PhoneExp>();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("phoneExp.data"));
			String s = null;
			PhoneExp pe;// = new PhoneExp();
			String[] sss;

			while ((s = br.readLine()) != null) {
				sss = s.split(" ");
				pe = new PhoneExp();
				pe.setNum(Integer.parseInt(sss[0]));
				pe.setExpl(sss[1]);
				pe.setFlag(sss[2]);
				peList.add(pe);

				System.out.println(pe.toString());
			}
		} catch (FileNotFoundException e) {
			try {
				br.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return peList;

	}

	public int recalNum(int num) {
		int iPart = num / 80;
		float fPart = (float) ((num / 80.0) - iPart);
		return (int) (fPart * 80);
	}

	public boolean isGood(int num) {
		return goodList.contains(recalNum(num));
	}

}
