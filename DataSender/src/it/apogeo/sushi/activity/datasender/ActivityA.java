package it.apogeo.sushi.activity.datasender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class ActivityA extends Activity {
	

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
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setType("custom/mime");
		startActivity(intent);
		/*
		Intent intent = new Intent(Intent.ACTION_VIEW);
		Uri uri = Uri.parse("my_schema://my_host/my_path");
		intent.setData(uri);
		startActivity(intent);
		*/		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_a, menu);
		return true;
	}

}
