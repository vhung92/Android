package kth.vhung.soundbox;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;


public class SoundManager {
	private SoundPool mSoundPool;
	private HashMap<Matrix, Integer> mSoundPoolMap;
	private AudioManager mAudioManager;
	private Context mContext;
	
	public SoundManager(Context theContext) {
		mContext = theContext;
		mSoundPool = new SoundPool(21, AudioManager.STREAM_MUSIC, 0);
		mSoundPoolMap = new HashMap<Matrix, Integer>();
		mAudioManager = (AudioManager) mContext
				.getSystemService(Context.AUDIO_SERVICE);
	}

	public void addSound(Matrix m, int soundID) {
		//mSoundPoolMap.put(index, mSoundPool.load(mContext, soundID, 1));
		mSoundPoolMap.put(m, mSoundPool.load(mContext, soundID, 1));
		
	}

	public void playSound(Matrix m) {
		float streamVolume = mAudioManager
				.getStreamVolume(AudioManager.STREAM_RING);
		streamVolume = streamVolume
				/ mAudioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
		
		mSoundPool.play((Integer) mSoundPoolMap.get(m), streamVolume,
				streamVolume, 1, 0, 1f);
	}

	public void playLoopedSound(Matrix m) {
		float streamVolume = mAudioManager
				.getStreamVolume(AudioManager.STREAM_RING);
		streamVolume = streamVolume
				/ mAudioManager.getStreamMaxVolume(AudioManager.STREAM_RING);

		mSoundPool.play((Integer) mSoundPoolMap.get(m), streamVolume,
				streamVolume, 1, -1, 1f);
	}
}