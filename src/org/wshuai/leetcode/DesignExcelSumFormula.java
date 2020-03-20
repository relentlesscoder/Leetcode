package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/20/2019.
 * #0631 https://leetcode.com/problems/design-excel-sum-formula/
 */
public class DesignExcelSumFormula {
	private Cell[][] table;

	public DesignExcelSumFormula(int H, char W) {
		table = new Cell[H + 1][W - 'A' + 1];
	}

	public void set(int r, char c, int v) {
		int col = c - 'A';
		if(table[r][col] == null){
			table[r][col] = new Cell(v);
		}else{
			table[r][col].setValue(v);
		}
	}

	public int get(int r, char c) {
		int col = c - 'A';
		if(table[r][col] == null){
			return 0;
		}
		return table[r][col].getValue();
	}

	public int sum(int r, char c, String[] strs) {
		int col = c - 'A';
		if(table[r][col] == null){
			table[r][col] = new Cell(strs);
		}else{
			table[r][col].setFormular(strs);
		}
		return table[r][col].getValue();
	}

	private class Cell{

		private int val = 0;

		// record all involved cells and their count (may overlap)
		private Map<Cell, Integer> formular = new HashMap<>();

		public Cell(int val){
			setValue(val);
		}

		public Cell(String[] formularStrs){
			setFormular(formularStrs);
		}

		public void setValue(int val){
			formular.clear();
			this.val = val;
		}

		public void setFormular(String[] formularStrs){
			formular.clear();
			for(String str : formularStrs){
				if(str.indexOf(":") < 0){
					int[] pos = getPos(str);
					addFormularCell(pos[0], pos[1]);
				}else{
					String[] pos = str.split(":");
					int[] startPos = getPos(pos[0]);
					int[] endPos = getPos(pos[1]);
					for(int i = startPos[0]; i <= endPos[0]; i++){
						for(int j = startPos[1]; j <= endPos[1]; j++){
							// add each cell involved to the map
							addFormularCell(i, j);
						}
					}
				}
			}
		}

		private int[] getPos(String str){
			int[] pos = new int[2];
			pos[0] = Integer.parseInt(str.substring(1));
			pos[1] = str.charAt(0) - 'A';
			return pos;
		}

		private void addFormularCell(int r, int c){
			if(table[r][c] == null){
				table[r][c] = new Cell(0);
			}
			Cell rangeCell = table[r][c];
			formular.put(rangeCell, formular.getOrDefault(rangeCell, 0) + 1);
		}

		private int getValue(){
			if(this.formular.isEmpty()){
				return this.val;
			}
			int sum = 0;
			for(Cell cell : formular.keySet()){
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
