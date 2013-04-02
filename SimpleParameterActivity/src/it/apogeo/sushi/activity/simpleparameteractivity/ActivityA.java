package it.apogeo.sushi.activity.simpleparameteractivity;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityA extends Activity {

	/*
	 * Reference to the EditText for text
	 */
	private EditText mText;
	
	/*
	 * Reference to the EditText for number
	 */
	private EditText mNumber;
	
	/*
	 * Reference to the DatePicker for number
	 */
	private DatePicker mDatePicker;	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_a);
		// We get the reference to the UI elements
		mText = (EditText) findViewById(R.id.string_edittext);
		mNumber = (EditText) findViewById(R.id.number_editext);
		mDatePicker = (DatePicker) findViewById(R.id.date_datepicker);
	}
	
	/**
	 * This is invoked when the next button in pressed
	 * @param sendButton The button to send data
	 */
	public void sendData(View sendButton) {
		// We get the values from the form
		Editable text = mText.getEditableText();
		if (TextUtils.isEmpty(text)) { 
			showMandatoryToast("text");
			return;
		}
		// We get the values for the number
		Editable number = mNumber.getEditableText();
		if (TextUtils.isEmpty(text)) { 
			showMandatoryToast("number");
			return;
		}
		// We check that it's a number (it should be because the android:inputType
		long numberAsLong = 0;
		try{
			numberAsLong = Long.parseLong(number.toString());
		}catch(NumberFormatException nfe){
			showNotNumberToast(number.toString());			
		}
		// We get the Data
		int year = mDatePicker.getYear();
		int month = mDatePicker.getMonth();
		int day = mDatePicker.getDayOfMonth();
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		Date date = cal.getTime();
		// Here we're ok.We create the Intent
		Intent esplicitIntent = new Intent(ActivityA.this, ActivityB.class);
		// We insert the data as Extra
		esplicitIntent.putExtra(ActivityB.TEXT_EXTRA_NAME, text.toString());
		esplicitIntent.putExtra(ActivityB.NUMBER_EXTRA_NAME, numberAsLong);
		esplicitIntent.putExtra(ActivityB.DATE_EXTRA_NAME, date);
		startActivity(esplicitIntent);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_a, menu);
		return true;
	}
	
	/*
	 * Utility method that shows a short Toast
	 */
	private void showMandatoryToast(String mandatoryField) {
		String message = getResources().getString(R.string.mandatory_warning, mandatoryField);
		Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
		toast.show();
	}
	
	/*
	 * Utility method that shows a short Toast for a not number message
	 */
	private void showNotNumberToast(String numberValue) {
		String message = getResources().getString(R.string.not_number_warning, numberValue);
		Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
		toast.show();
	}	

}
