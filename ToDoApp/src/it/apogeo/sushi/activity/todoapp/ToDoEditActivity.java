package it.apogeo.sushi.activity.todoapp;

import it.apogeo.sushi.activity.todoapp.content.DB;
import it.apogeo.sushi.activity.todoapp.model.ToDoItem;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This class describes the Activity we use to show the detail of a ToDo.
 * 
 * @author Massimo Carli - 21/gen/2013
 * 
 */
public class ToDoEditActivity extends Activity {
	
	/**
	 * The Extra name for the detail to edit. If not present it's a create
	 */
	public static final String TODO_POS_EXTRA  ="it.apogeo.sushi.activity.todoapp.TODO_POS_EXTRA";
	
	/*
	 * If true we're in edit mode.
	 */
	private boolean mEditMode;
	
	private EditText mIdEditText;
	
	private EditText mTitleEditText;
	
	private DatePicker mDatePicker;
	
	private EditText mDescrEditText;
	
	private ToDoItem mTodoItem;
	
	private int mPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_detail);
		// We get the position of the element to show
		mPosition = getIntent().getIntExtra(TODO_POS_EXTRA, -1);
		mEditMode = mPosition >=0;
		// We get the reference to the UI elements
		mIdEditText = (EditText) findViewById(R.id.todo_id);
		mTitleEditText = (EditText) findViewById(R.id.todo_title);
		mDatePicker = (DatePicker) findViewById(R.id.todo_date);
		mDescrEditText = (EditText) findViewById(R.id.todo_description);
		if (mEditMode) {
			// We show data
			mTodoItem = DB.getInstance().get(mPosition);
			mIdEditText.setText(String.valueOf(mTodoItem.getId()));
			mTitleEditText.setText(mTodoItem.getTitle());
			mDescrEditText.setText(mTodoItem.getDescription());
			// We format the date
			Calendar cal = Calendar.getInstance();
			cal.setTime(mTodoItem.getDate());
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);
			mDatePicker.updateDate(year, month, day);			
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_to_do_edit, menu);
		return true;
	}	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.save_todo:
			// Here we have to save the new ToDo created
			ToDoItem itemToChange = null;
			if (mEditMode) {
				itemToChange = mTodoItem;
			} else {
				itemToChange = new ToDoItem();
			}
			if(checkValidity()){
				itemToChange.setId(Long.parseLong(mIdEditText.getText().toString()));
				itemToChange.setTitle(mTitleEditText.getText().toString());
				Editable descr = mDescrEditText.getText();
				if(TextUtils.isEmpty(descr)){
					itemToChange.setDescription(descr.toString());
				} else {
					itemToChange.setDescription("");
				}
				// The date
				int day = mDatePicker.getDayOfMonth();
				int month = mDatePicker.getMonth();
				int year = mDatePicker.getYear();
				Calendar cal = Calendar.getInstance();
				cal.set(year, month, day);
				itemToChange.setDate(cal.getTime());
				if (mEditMode) {
					// Nothing. We work on the reference in memory
				} else {
					DB.getInstance().addItem(itemToChange);	
				}
				
				finish();
			} 
			break;
		case R.id.delete_todo:
			// Here we have to update the state the new ToDo created
			DB.getInstance().removeItem(mPosition);
			finish();			
			break;			
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	/*
	 * Utility method that test validity
	 */
	private final boolean checkValidity() {
		// The id
		Editable idValue = mIdEditText.getText();
		if (TextUtils.isEmpty(idValue)){
			String message = getResources().getString(R.string.mandatory_message, "id");
			Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
			return false;
		}
		// The title
		Editable titleValue = mIdEditText.getText();
		if (TextUtils.isEmpty(titleValue)){
			String message = getResources().getString(R.string.mandatory_message, "Title");
			Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
	

}
