/*
* @author Victor Hung
*/

package kth.vhung.soundbox;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.ToggleButton;

public class LoopActivity extends Activity {
	private int BPM = 60;
	private SparseArray<Matrix> indexId;
	private SoundGrid Grid;
	private SoundManager Manager;
	private ToggleButton[][] Buttons;
	private Handler handler = new Handler();
	private int counter = 0;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loop);
		indexId = new SparseArray<Matrix>();
		Manager = new SoundManager(this);
		Buttons = new ToggleButton[5][7];
		for(int i = 0; i < 4; i++){
			Buttons[i] = new ToggleButton[7];
		}
		Grid = new SoundGrid();
		createButtons();
		Grid.addElements(Buttons);
		initSM();
		
		
		// Make sure we're running on Honeycomb or higher to use ActionBar APIs
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		handler.postDelayed(runnable, 5000);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onResume() {
		super.onResume();
		handler.postDelayed(runnable, 5000);
	}
	@Override
	public void onPause() {
		super.onPause();
		handler.removeCallbacks(runnable);
	}
	
	public void initSM() {
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 6; j++) {
				Matrix m = new Matrix(i,j);
				Manager.addSound(m, Grid.getSoundID(i,j));
				indexId.put(Grid.getButtonID(i,j), m);
			}
		}
	}

	public void createButtons() {
		TableLayout mTable = (TableLayout) findViewById(R.id.table);
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, 1.0f);
		params.setMargins(2, 2, 2, 2);
		int id = 0;
		for (int i = 0; i < 5; i++) {
			TableRow tr = new TableRow(this);
			tr.setId(i);
			tr.setLayoutParams(params);
			for (int j = 0; j < 7; j++) {
				ToggleButton tb = new ToggleButton(this);
				tb.setText("");
				tb.setTextOff("");
				tb.setTextOn("");
				tb.setBackgroundResource(R.drawable.boxoff);
				tb.setLayoutParams(params);
				tb.setId(1000 + id);
				id+=5;
				tb.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						ToggleButton tb = (ToggleButton) v;
						if (tb.isChecked()) {
							tb.setBackgroundResource(R.drawable.boxon);
							Matrix m = indexId.get(tb.getId());
							Grid.setPlayable(m.row, m.column, true);
						} else {
							tb.setBackgroundResource(R.drawable.boxoff);
							Matrix m = indexId.get(tb.getId());
							Grid.setPlayable(m.row, m.column, false);
						}
					}
				});
				tr.addView(tb);
				Buttons[i][j] = tb;
			}
			mTable.addView(tr, new TableLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
	}

	public void loopCounter() {
		for(int i = 0; i < 5; i++){
			boolean playable = Grid.getPlayable(i, counter);
			ToggleButton ib = (ToggleButton) findViewById(Grid.getButtonID(i, counter));
			if(!playable) {
				ib.setBackgroundResource(R.drawable.boxcount);
			}
		}
	}

	public void resetImages() {
	}

	public void playSounds() {
		for(int i = 0; i < 5; i++){
			boolean playable = Grid.getPlayable(i, counter);
			if(playable) {
				Matrix m = new Matrix(i,counter);
				Manager.playSound(m);
			}
		}
	}
	private Runnable runnable = new Runnable() {
		@Override
		public void run() {
			if (counter > 6) {
				counter = 0;
			}
			playSounds();
			counter++;
			handler.postDelayed(this, 60000 / BPM);
		}
	};
}

/*
 * 	public void initSeekBar() {
		SeekBar = (SeekBar) findViewById(R.id.BPMbar);
		SeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				BPM = progress + 60;
				TextView tv = (TextView) findViewById(R.id.BPMtext);
				tv.setText("BPM: " + BPM);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});
	}
 * 
 */

