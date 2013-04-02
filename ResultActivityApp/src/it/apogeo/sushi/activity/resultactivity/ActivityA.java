package it.apogeo.sushi.activity.resultactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivityA extends Activity {
	
	/**
	 * The id that identify the request with startActivityForResult
	 */
	public static final int GET_DATA_REQUEST_ID = 1;
	
	/*
	 * The TextView for output
	 */
	private TextView mOutput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a);
		// Get the reference to the output
		mOutput = (TextView) findViewById(R.id.output_text);
	}
	
	/**
	 * Invoked when we press the button to get data
	 * @param getDataButton The button pressed 
	 */
	public void sendActionForData(View getDataButton){
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType("custom/mime");
		startActivityForResult(intent, GET_DATA_REQUEST_ID);
	}
	
	/**
	 * Invoked when we press the button to show data
	 * @param showDataButton The button pressed
	 */
	public void showData(View showDataButton){
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setType("custom/mime");
		startActivity(intent);
	}	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		String text = null;
		switch (resultCode) {
		case RESULT_OK:
			// We get the result intent
			text = data.getStringExtra(ActivityB.RESULT_EXTRA_KEY);
			mOutput.setText("GOT " + text);
			break;
		case RESULT_CANCELED:
			mOutput.setText("OPERATION CANCELLED");
			break;			
		default:
			text = data.getStringExtra(ActivityB.RESULT_EXTRA_KEY);
			mOutput.setText("OTHER VALUE: " + text);
			break;
		}
	}

}
