package com.test.hsx;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	MediaPlayer m1;
	ImageView infoOperatingIV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		infoOperatingIV = (ImageView) findViewById(R.id.infoOperating);

		Button play = (Button) findViewById(R.id.play);
		Button stop = (Button) findViewById(R.id.stop);

		play.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				playMusic();
			}
		});

		stop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stopMusic();
			}
		});

	}

	private void playMusic() {
		m1 = MediaPlayer.create(this, R.raw.quiet);
		m1.start();

		Animation operatingAnim = AnimationUtils.loadAnimation(this, R.anim.tip);
		LinearInterpolator lin = new LinearInterpolator();
		operatingAnim.setInterpolator(lin);
		if (operatingAnim != null) {
			infoOperatingIV.startAnimation(operatingAnim);
		}

		m1.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.stop();
				infoOperatingIV.clearAnimation();
			}
		});
	}

	private void stopMusic() {
		m1.stop();
		infoOperatingIV.clearAnimation();
	}

}
