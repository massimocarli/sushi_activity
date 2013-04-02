package it.apogeo.sushi.activity.todoapp;

import it.apogeo.sushi.activity.todoapp.content.DB;
import it.apogeo.sushi.activity.todoapp.content.ToDoItemAdapter;
import it.apogeo.sushi.activity.todoapp.model.ToDoItem;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

/**
 * This class describes a List with the given ToDoList. 
 *
 * @author Massimo Carli - 21/gen/2013
 *
 */
public class ToDoListActivity extends ListActivity {
	
	/*
	 * The Adapter to show the list of ToDos
	 */
	private ToDoItemAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_to_do_list);
		// We set the adapter
		mAdapter = new ToDoItemAdapter(this);
		setListAdapter(mAdapter);
		// We register the ListView for the long click
		registerForContextMenu(getListView());
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		// We simply go to the selected item to show the detail. We'll pass only the
		// position of the item to show
		Intent showDetail = new Intent(this,ToDoDetailActivity.class);
		showDetail.putExtra(ToDoDetailActivity.TODO_POS_EXTRA, position);
		startActivity(showDetail);
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_to_do_list, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// We have to start the Activity to create the new ToDo
		Intent editIntent = new Intent(this, ToDoEditActivity.class);
		startActivity(editIntent);		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.activity_to_do_list_context, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo infos = (AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.edit_todo:
			// We show the Edit Activity
			Intent editIntent = new Intent(this, ToDoEditActivity.class);
			editIntent.putExtra(ToDoEditActivity.TODO_POS_EXTRA, infos.position);
			startActivity(editIntent);
			break;
		case R.id.done_todo:
			// We just need to update the model
			ToDoItem selectedItem = DB.getInstance().get(infos.position);
			selectedItem.setCompleted(true);
			mAdapter.notifyDataSetChanged();
			break;
		case R.id.delete_todo:
			DB.getInstance().removeItem(infos.position);
			mAdapter.notifyDataSetChanged();
			break;			
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}
	
}
