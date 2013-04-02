package it.apogeo.sushi.activity.stateactivity;

import android.app.Activity;
import android.os.Bundle;

public class EditableActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editable);
	}

	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		//super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		//super.onRestoreInstanceState(savedInstanceState);
	}


}
