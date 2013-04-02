package it.apogeo.sushi.activity.activitylifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ActivityA extends Activity {
	
	/*
	 * The tag to use for the log
	 */
	private final static String TAG_LOG = "ACTIVITY_LIFECYCLE";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a);
		log("ON CREATE");
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		log("ON START");
	}

	@Override
	protected void onResume() {
		super.onResume();
		log("ON RESUME");
	}	
	
	@Override
	protected void onPause() {
		super.onPause();
		log("ON PAUSE");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		log("ON STOP");		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		log("ON DESTROY");		
	}	
	
	@Override
	protected void onRestart() {
		super.onRestart();
		log("ON RESTART");		
	}	
	
	
	/**
	 * @return The name of the Activity class
	 */
	protected String whoAmI(){
		return "ACTIVITY_A";
	}
	
	/**
	 * Utility method to show the log.
	 * @param message The message of the log.
	 */
	protected void log(String message){
		Log.d(TAG_LOG, whoAmI() + " -> " +message);
	}

	/**
	 * Invoked when we press the nextButton
	 * @param nextButton The nex Button
	 */
	public void goNext(View nextButton) {
		Intent intent = new Intent(this, ActivityB.class);
		startActivity(intent);
	}


}
