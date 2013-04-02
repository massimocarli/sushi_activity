package it.apogeo.sushi.activity.activitylifecycle;

import android.os.Bundle;
import android.view.View;

public class ActivityB extends ActivityA {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_b);
	}

	/**
	 * @return The name of the Activity class
	 */
	protected String whoAmI() {
		return "ACTIVITY_B";
	}

	/**
	 * Invoked when we press the backButton
	 * 
	 * @param backButton The back Button
	 */
	public void goBack(View backButton) {
		finish();
	}

}
