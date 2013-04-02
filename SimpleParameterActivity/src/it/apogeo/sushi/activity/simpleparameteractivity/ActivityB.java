package it.apogeo.sushi.activity.simpleparameteractivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ActivityB extends Activity {

	/*
	 * Extra name for text parameter
	 */
	public static final String TEXT_EXTRA_NAME = "it.apogeo.sushi.activity.simpleparameteractivity.extra.TEXT_EXTRA_NAME";

	/*
	 * Extra name for number parameter
	 */
	public static final String NUMBER_EXTRA_NAME = "it.apogeo.sushi.activity.simpleparameteractivity.extra.NUMBER_EXTRA_NAME";

	/*
	 * Extra name for date parameter
	 */
	public static final String DATE_EXTRA_NAME = "it.apogeo.sushi.activity.simpleparameteractivity.extra.DATE_EXTRA_NAME";
	
	/*
	 * The DateFormat to show a date
	 */
	private static final SimpleDateFormat SDF = new SimpleDateFormat("EEE dd MMM yyyy",Locale.getDefault());

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_b);
		// We get the value from the input Intent
		Intent input = getIntent();
		String text = input.getStringExtra(TEXT_EXTRA_NAME);
		long number = input.getLongExtra(NUMBER_EXTRA_NAME, -1);
		Date date = (Date) input.getSerializableExtra(DATE_EXTRA_NAME);
		// We show data in the layout
		TextView textOutput = (TextView) findViewById(R.id.string_text);
		textOutput.setText(text);
		TextView numberOutput = (TextView) findViewById(R.id.number_text);
		numberOutput.setText(String.valueOf(number));
		TextView dateTextView = (TextView) findViewById(R.id.date_text);
		dateTextView.setText(SDF.format(date));
	}

	/**
	 * Invoked when *our* back button is pressed.
	 * 
	 * @param backButton
	 *            The Button pressed
	 */
	public void goBack(View backButton) {
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_a, menu);
		return true;
	}

}
