package it.apogeo.sushi.activity.actionsender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class ActivityA extends Activity {
	
	/**
	 * Our custom action
	 */
	public static final String ACTION_CUSTOM = "it.apogeo.sushi.activity.actionsender.action.ACTION_CUSTOM";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a);
	}
	
	/**
	 * Invoked when the sendButton is pressed
	 * @param sendButton The button pressed to send
	 */
	public void sendAction(View sendButton){
		Intent intent = new Intent(ACTION_CUSTOM);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_a, menu);
		return true;
	}

}
