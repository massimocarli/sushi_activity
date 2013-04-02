package it.apogeo.sushi.activity.todoapp.model;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class describes the Model for the ToDo application. A ToDo is composed
 * of a:
 * 
 * - id - date - title - description
 * 
 * We created a
 * 
 * @author Massimo Carli - 21/gen/2013
 * 
 */
public class ToDoItem implements Parcelable {

	/*
	 * The value of the byte that tells the date is NOT present
	 */
	private static final byte DATE_NOT_PRESENT_VALUE = 0;

	/*
	 * The value of the byte that tells the date is present
	 */
	private static final byte DATE_PRESENT_VALUE = 1;
	
	/*
	 * The value of the byte that tells the ToDo is NOT complete
	 */
	private static final byte NOT_COMPLETED_VALUE = 0;

	/*
	 * The value of the byte that tells the ToDo is complete
	 */
	private static final byte COMPLETED_VALUE = 1;	

	/**
	 * The CREATOR responsable of the instance deserialization
	 */
	public static final Parcelable.Creator<ToDoItem> CREATOR = new Parcelable.Creator<ToDoItem>() {
		public ToDoItem createFromParcel(Parcel in) {
			return new ToDoItem(in);
		}

		public ToDoItem[] newArray(int size) {
			return new ToDoItem[size];
		}
	};

	/*
	 * The id of this ToDoItem
	 */
	private long mId;

	/*
	 * The creation time for the ToDoItem
	 */
	private Date mDate;

	/*
	 * The title of this ToDoItem
	 */
	private String mTitle;

	/*
	 * The decription of this ToDoItem
	 */
	private String mDescription;

	/*
	 * If true the ToDo is completed
	 */
	private boolean mCompleted;

	/**
	 * Default constructor
	 */
	public ToDoItem() {
	}

	/**
	 * Creates a ToDoItem from a Parcel
	 * 
	 * @param in
	 *            The Parcel to read from
	 */
	public ToDoItem(Parcel in) {
		// We have to restore the state from the Parcel
		this.mId = in.readLong();
		this.mTitle = in.readString();
		// We check for the date
		byte datePresenceByte = in.readByte();
		if (datePresenceByte == DATE_PRESENT_VALUE) {
			this.mDate = new Date(in.readLong());
		}
		this.mDescription = in.readString();
		this.mCompleted = in.readByte() == COMPLETED_VALUE;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(this.mId);
		dest.writeString(this.mTitle);
		// We write the byte to test if the date is present
		if(this.mDate != null){
			dest.writeByte(DATE_PRESENT_VALUE);
			dest.writeLong(this.mDate.getTime());
		} else {
			dest.writeByte(DATE_NOT_PRESENT_VALUE);
		}
		dest.writeString(this.mDescription);
		dest.writeByte(this.mCompleted ? COMPLETED_VALUE : NOT_COMPLETED_VALUE);
	}

	public long getId() {
		return mId;
	}

	public void setId(long id) {
		this.mId = id;
	}

	public Date getDate() {
		return mDate;
	}

	public void setDate(Date date) {
		this.mDate = date;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String description) {
		this.mDescription = description;
	}

	public boolean isCompleted() {
		return mCompleted;
	}

	public void setCompleted(boolean completed) {
		this.mCompleted = completed;
	}

}
