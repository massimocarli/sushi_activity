package it.apogeo.sushi.activity.resultactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityB extends Activity {
	
	/**
	 * The id that identify the response  requested value 
	 */
	public static final String RESULT_EXTRA_KEY = "it.apogeo.sushi.activity.resultactivity.RESULT_EXTRA_KEY";
	
	/**
	 * The custom value for a custom return response value
	 */
	public static final int CUSTOM_RETURN_KEY = RESULT_FIRST_USER + 1;
	
	/*
	 * The EditText for input text
	 */
	private EditText mEditText;
	
	/*
	 * True if in edit mode
	 */
	private boolean mPickMode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_b);
		// Get the reference to the output
		mEditText = (EditText) findViewById(R.id.string_edittext);
		// We check if we're started in edit or view mode
		mPickMode = Intent.ACTION_PICK.equals(getIntent().getAction());
	}
	
	
	/**
	 * Invoked to send data back
	 * @param button The button pressed
	 */
	public void sendData(View button){
		if (mPickMode) {
			// We create the return Intent
			Intent returnIntent = new Intent();
			if (!TextUtils.isEmpty(mEditText.getText())) {
				returnIntent.putExtra(RESULT_EXTRA_KEY, mEditText.getText().toString());
			}
			// We return it
			setResult(RESULT_OK, returnIntent);
			finish();
		} else {
			Toast toast = Toast.makeText(this, R.string.pick_mode_message, Toast.LENGTH_SHORT);
			toast.show();
		}
	}

	/**
	 * Invoked to send custom data back
	 * @param button The button pressed
	 */	
	public void sendCustom(View button){
		if (mPickMode) {
			Intent returnIntent = new Intent();
			// We return a custom valye
			setResult(CUSTOM_RETURN_KEY, returnIntent);	
			finish();
		} else {
			Toast toast = Toast.makeText(this, R.string.pick_mode_message, Toast.LENGTH_SHORT);
			toast.show();
		}
	}	

}
