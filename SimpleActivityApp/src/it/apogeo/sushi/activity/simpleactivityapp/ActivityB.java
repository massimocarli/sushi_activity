package it.apogeo.sushi.activity.simpleactivityapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class ActivityB extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_b);
	}

	/**
	 * Invoked when *our* back button is pressed.
	 * @param backButton The Button pressed
	 */
	public void goBack(View backButton){
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_a, menu);
		return true;
	}

}
