package it.apogeo.sushi.activity.todoapp;

import it.apogeo.sushi.activity.todoapp.content.DB;
import it.apogeo.sushi.activity.todoapp.model.ToDoItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * This class describes the Activity we use to show the detail of a ToDo.
 * 
 * @author Massimo Carli - 21/gen/2013
 * 
 */
public class ToDoDetailActivity extends Activity {
	
	/*
	 * The Tag for the log
	 */
	private static final String TAG_LOG = ToDoDetailActivity.class.getName();
	
	/**
	 * The Extra name for the detail to show.
	 */
	public static final String TODO_POS_EXTRA  ="it.apogeo.sushi.activity.todoapp.TODO_POS_EXTRA";
	
	/*
	 * The DateFormat to format dates
	 */
	private static final DateFormat SDF = new SimpleDateFormat("EE d MM yyyy", Locale.getDefault());	
	
	/*
	 * The TextView for completed state
	 */
	private TextView mCompletedTextView;
	
	/*
	 * The item selected
	 */
	private ToDoItem mSelectedItem;
	
	/*
	 * The selected position
	 */
	private int mPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_detail);
		// We get the position of the element to show
		mPosition = getIntent().getIntExtra(TODO_POS_EXTRA, -1);
		if (mPosition < 0) {
			Log.w(TAG_LOG, "No position for the element to show!");
			finish();
		}
		// We get the reference to the UI elements
		TextView idTextView = (TextView) findViewById(R.id.todo_id);
		TextView titleTextView = (TextView) findViewById(R.id.todo_title);
		TextView dateTextView = (TextView) findViewById(R.id.todo_date);
		TextView descrTextView = (TextView) findViewById(R.id.todo_description);
		// We show data
		mSelectedItem = DB.getInstance().get(mPosition);
		idTextView.setText(String.valueOf(mSelectedItem.getId()));
		titleTextView.setText(mSelectedItem.getTitle());
		descrTextView.setText(mSelectedItem.getDescription());
		// We format the date
		dateTextView.setText(SDF.format(mSelectedItem.getDate()));
		// manage completed state
		mCompletedTextView = (TextView) findViewById(R.id.completed_textview);
		if (mSelectedItem.isCompleted()) {
			mCompletedTextView.setText(getResources().getString(R.string.completed_label));
		} else {
			mCompletedTextView.setText(getResources().getString(R.string.not_completed_label));
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_to_do_detail, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.done_todo:
			// We just need to update the model
			mSelectedItem.setCompleted(true);
			mCompletedTextView.setText(getResources().getString(R.string.completed_label));		
			break;
		case R.id.delete_todo:
			DB.getInstance().removeItem(mPosition);
			finish();
			break;			
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}	

}
