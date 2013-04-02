package it.apogeo.sushi.activity.stateactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StatelessActivity extends Activity {
	
	private int mCounter;
	
	private TextView mOutput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a);
		mOutput = (TextView) findViewById(R.id.output);
		showCounter();
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
