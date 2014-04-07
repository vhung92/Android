/*
* @author Victor Hung
*/

package kth.vhung.soundbox;

public class GridElement {
	private int Row;
	private int Column;
	private int ButtonID;
	private int SoundID;
	private boolean play;

	
	public GridElement(int row, int column, int soundID, int buttonID){
		this.Row = row;
		this.Column = column;
		this.ButtonID = buttonID;
		this.SoundID = soundID;
		this.play = false;
	}
	public int getRow(){
		return Row;
	}
	public int getColumn(){
		return Column;
	}
	public int getButtonID(){
		return ButtonID;
	}
	public int getSoundID(){
		return SoundID;
	}
	public void setPlay(boolean play) {
		this.play = play;
	}
	public boolean getPlay(){
		return play;
	}
}
