package it.apogeo.sushi.activity.todoapp.content;

import it.apogeo.sushi.activity.todoapp.R;
import it.apogeo.sushi.activity.todoapp.model.ToDoItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Implementation of an Adapter to access our DB informations
 * 
 * @author Massimo Carli - 21/gen/2013
 *
 */
public class ToDoItemAdapter extends BaseAdapter{
	
	/*
	 * The DateFormat to format dates
	 */
	private static final DateFormat SDF = new SimpleDateFormat("EE d MM yyyy", Locale.getDefault());
	
	/*
	 * The Reference to the Db
	 */
	private DB mDb;
	
	/*
	 * The Context
	 */
	private Context mContext;
	
	/**
	 * Default constructor
	 */
	public ToDoItemAdapter(Context context) {
		// We simply get the reference to the Db
		this.mDb = DB.getInstance();
		this.mContext = context;
	}

	@Override
	public int getCount() {
		return mDb.getCount();
	}

	@Override
	public Object getItem(int position) {
		return mDb.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Here we have to build the View for the given elements.
		Holder holder = null;
		if (convertView == null) {
			// We get the Inflater
			LayoutInflater inflater = LayoutInflater.from(mContext);
			// We inflate the layout for the row
			convertView = inflater.inflate(R.layout.layout_todo_list_item, null);
			// We get the references to the Views
			holder = new Holder();
			holder.mIdTextView = (TextView) convertView.findViewById(R.id.todo_id);
			holder.mTitleTextView = (TextView) convertView.findViewById(R.id.todo_title);
			holder.mDateTextView = (TextView) convertView.findViewById(R.id.todo_date);
			holder.mCompletedCheckBox = (CheckBox) convertView.findViewById(R.id.todo_completed_checkbox);
			// We set the Holder as a tag for the holder
			convertView.setTag(holder);
		}else{
			holder = (Holder) convertView.getTag();
		}
		// Here we have to insert the values into the TextViews
		ToDoItem currentItem = mDb.get(position);
		holder.mIdTextView.setText(String.valueOf(currentItem.getId()));
		holder.mTitleTextView.setText(currentItem.getTitle());
		holder.mCompletedCheckBox.setChecked(currentItem.isCompleted());
		// We format the date
		holder.mDateTextView.setText(SDF.format(currentItem.getDate()));
		// We return the created View
		return convertView;
	}
	
	
	/*
	 * We use this class to encapsulate the references to the Views into the 
	 * layout for the list item
	 */
	private static class Holder {
		TextView mIdTextView;
		TextView mTitleTextView;
		TextView mDateTextView;
		CheckBox mCompletedCheckBox;
	}

}
