package it.apogeo.sushi.activity.simpleactivityapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityA extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a);
		// We get the reference of the Button in the UI
		Button nextButton = (Button) findViewById(R.id.next_button);
		nextButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Here we go to the next Activity
				Intent esplicitIntent = new Intent(ActivityA.this, ActivityB.class);
				startActivity(esplicitIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_a, menu);
		return true;
	}

}
