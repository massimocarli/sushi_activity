package it.apogeo.sushi.activity.todoapp.content;

import it.apogeo.sushi.activity.todoapp.model.ToDoItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This object encapsulate the information about the model with all the ToDoItem
 * we need to manage
 *  
 * @author Massimo Carli - 21/gen/2013
 *
 */
public final class DB {

	/*
	 * Here we store our data base 
	 */
	private List<ToDoItem> DATA  = new ArrayList<ToDoItem>();
	
	/*
	 * The singleton instance
	 */
	private static DB mInstance;
	
	/*
	 * Private constructor
	 */
	private DB(){
	}
	
	/**
	 * Static Factory Method that returns the unique instance of the DB.
	 * @return The single instance of our DB
	 */
	public static DB getInstance() {
		if (mInstance == null){
			mInstance = new DB();
		}
		return mInstance;
	} 
	
	/**
	 * This add a new item to the DB
	 * @param newItem The new item to add
	 */
	public void addItem(ToDoItem newItem) {
		// We add the item to the List 
		DATA.add(newItem);
	}
	
	/**
	 * Remove the data given the position
	 * @param position The position of the item to remove
	 */
	public void removeItem(int position) {
		DATA.remove(position);
	}
	
	/**
	 * Return the ToDoItem at the given position
	 * @param position The position of the item
	 * @return The ToDoItem if present or null
	 */
	public ToDoItem get(int position){
		if (position < DATA.size()){
			return DATA.get(position);
		} else {
			return null;
		}
	}
	
	/**
	 * @return The Iterator for the DB item
	 */
	public Iterator<ToDoItem> iterator() {
		return DATA.iterator();
	}
	
	/**
	 * @return The number of elements in the DB
	 */
	public int getCount() {
		return DATA.size();
	}
	
}
