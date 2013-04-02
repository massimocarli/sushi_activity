package it.apogeo.sushi.activity.stateactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StatefulActivity extends Activity {
	
	/*
	 * The key we use to save the state
	 */
	private static final String COUNTER_KEY = "it.apogeo.sushi.activity.stateactivity.COUNTER_KEY";
	
	private int mCounter;
	
	private TextView mOutput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a);
		mOutput = (TextView) findViewById(R.id.output);
		// We test the presence of the information about the counter
		if (savedInstanceState !=null ) {
			mCounter = savedInstanceState.getInt(COUNTER_KEY, 0);
		}
		showCounter();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// We save the state of the counter in the given Bundle
		outState.putInt(COUNTER_KEY, mCounter);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		// Second option for restore
	}

	/**
	 * Invoked when we press the addButton
	 */
	public void add(View nextButton) {
		mCounter++;
		showCounter();
	}
	
	private void showCounter(){
		mOutput.setText(String.valueOf(mCounter));
	}

}
