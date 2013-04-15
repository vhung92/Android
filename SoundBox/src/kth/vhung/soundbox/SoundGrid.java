package kth.vhung.soundbox;


import java.lang.reflect.Field;
import java.util.ArrayList;

import android.widget.ToggleButton;

public class SoundGrid {
	private int RowSize = 5;
	private int ColumnSize = 7;
	private GridElement[][] Grid;	

	public SoundGrid() {
		Grid = new GridElement[RowSize][ColumnSize];
	}

	public GridElement getIndex(int row, int column) {
		return Grid[row][column];
	}

	public Matrix getSize() {
		return new Matrix(RowSize, ColumnSize);
	}
	
	public int getSoundID(int row, int column){
		return Grid[row][column].getSoundID();
	}
	
	public int getButtonID(int row, int column){
		return Grid[row][column].getButtonID();
	}
	
	public boolean getPlayable(int row, int column){
		return Grid[row][column].getPlay();
	}
	
	public void setPlayable(int row, int column, boolean play) {
		Grid[row][column].setPlay(play);
	}
	
	public void addElements(ToggleButton[][] buttons) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Field[] fields = R.raw.class.getFields();
		for (Field f : fields)
			try {
				list.add(f.getInt(null));
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			}
		int counter = 0;
		for(int i = 0; i < RowSize-1; i++) {
			for(int j = 0; j < ColumnSize-1; j++) {
				int ButtonID = buttons[i][j].getId();
				Grid[i][j] = new GridElement(i, j, list.get(counter), ButtonID);
				counter++;
			}
		}
	}
}


/*
private int[] soundId = {R.raw.sound_01,R.raw.sound_02,R.raw.sound_03, R.raw.sound_04,R.raw.sound_05,R.raw.sound_06,R.raw.sound_07,
R.raw.sound_08,R.raw.sound_09,R.raw.sound_10,R.raw.sound_11,R.raw.sound_12,R.raw.sound_13,R.raw.sound_14,
R.raw.sound_15,R.raw.sound_16,R.raw.sound_17,R.raw.sound_18,R.raw.sound_19,R.raw.sound_20,R.raw.sound_21,
R.raw.sound_22,R.raw.sound_23,R.raw.sound_24,R.raw.sound_25,R.raw.sound_26,R.raw.sound_27,R.raw.sound_28,
R.raw.sound_29,R.raw.sound_30,R.raw.sound_31,R.raw.sound_32,R.raw.sound_33,R.raw.sound_34,R.raw.sound_35,
R.raw.sound_36,R.raw.sound_37,R.raw.sound_38,R.raw.sound_39,R.raw.sound_40,R.raw.sound_41,R.raw.sound_42,
R.raw.sound_43,R.raw.sound_44,R.raw.sound_45,R.raw.sound_46,R.raw.sound_47,R.raw.sound_48,R.raw.sound_49};
*/