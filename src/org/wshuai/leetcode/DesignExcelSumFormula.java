package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/20/2019.
 * #631 https://leetcode.com/problems/design-excel-sum-formula/
 */
public class DesignExcelSumFormula {
	private ExcelCell[][] table;

	public DesignExcelSumFormula(int H, char W) {
		table = new ExcelCell[H + 1][W - 'A' + 1];
	}

	public void set(int r, char c, int v) {
		if(table[r][c - 'A'] == null){
			table[r][c - 'A'] = new ExcelCell(v);
		}else{
			table[r][c - 'A'].setValue(v);
		}
	}

	public int get(int r, char c) {
		if( table[r][c-'A'] == null){
			return 0;
		}
		else{
			return table[r][c-'A'].getValue();
		}
	}

	public int sum(int r, char c, String[] strs) {
		if (table[r][c-'A'] == null) {
			table[r][c-'A'] = new ExcelCell(strs);
		} else {
			table[r][c-'A'].setFormular(strs);
		}

		return table[r][c-'A'].getValue();
	}

	private class ExcelCell{
		int val = 0;
		Map<ExcelCell, Integer> formular = new HashMap<>();

		public ExcelCell(int val){
			setValue(val);
		}

		public ExcelCell(String[] formularStr){
			setFormular(formularStr);
		}

		public void setValue(int val){
			formular.clear();
			this.val = val;
		}

		public void setFormular(String[] formularStr){
			formular.clear();
			for(String str : formularStr){
				if(str.indexOf(":") < 0){
					int[] pos = getPos(str);
					addFormularCell(pos[0], pos[1]);
				}else{
					String[] pos = str.split(":");
					int[] startPos = getPos(pos[0]);
					int[] endPos = getPos(pos[1]);
					for(int i = startPos[0]; i <= endPos[0]; i++){
						for(int j = startPos[1]; j <= endPos[1]; j++){
							addFormularCell(i, j);
						}
					}
				}
			}
		}

		private int[] getPos(String str){
			int[] pos = new int[2];
			pos[1] = str.charAt(0) - 'A';
			pos[0] = Integer.parseInt(str.substring(1));
			return pos;
		}

		private void addFormularCell(int r, int c){
			if(table[r][c] == null){
				table[r][c] = new ExcelCell(0);
			}
			ExcelCell rangeCell = table[r][c];
			formular.put(rangeCell, formular.getOrDefault(rangeCell, 0) + 1);
		}

		private int getValue(){
			if(this.formular.isEmpty()){
				return this.val;
			}
			int sum = 0;
			for(ExcelCell cell : formular.keySet()){
				sum += cell.getValue() * formular.get(cell);
			}
			return sum;
		}
	}
}

/**
 * Your Excel object will be instantiated and called as such:
 * Excel obj = new Excel(H, W);
 * obj.set(r,c,v);
 * int param_2 = obj.get(r,c);
 * int param_3 = obj.sum(r,c,strs);
 */
