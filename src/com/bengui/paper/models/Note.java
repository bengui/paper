package com.bengui.paper.models;

import android.content.Context;
import android.content.SharedPreferences;

public class Note {
	
	private static final String NOTE_TEXT = "NOTE_TEXT";
	private Context context;
	
	public Note(Context context){
		this.context = context;
	}
	
	
	public void saveText(String noteText){
		if(noteText!=null){
			SharedPreferences prefs = context.getSharedPreferences("com.bengui.papper", Context.MODE_PRIVATE);
			prefs.edit().putString(NOTE_TEXT, noteText).commit();
		}
	}
	
	public String getNoteText(){
		SharedPreferences prefs = context.getSharedPreferences("com.bengui.papper", Context.MODE_PRIVATE);
		return prefs.getString(NOTE_TEXT, null);
	}
	
}
