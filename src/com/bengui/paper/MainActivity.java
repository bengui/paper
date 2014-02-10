package com.bengui.paper;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.Toast;
import com.bengui.paper.models.Note;
import com.bengui.papper.R;

public class MainActivity extends Activity {

    private Note note;
    private EditText editText;
	private GestureDetectorCompat mDetector;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = ((EditText)findViewById(R.id.note_text));
        //Set the custom font
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/Memoria vestri.ttf");
        editText.setTypeface(myTypeface);
        
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
        editText.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return mDetector.onTouchEvent(event);
			}
		});
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	
    	note = new Note(MainActivity.this);
    	
    	String noteText = note.getNoteText();
    	
    	if(noteText!=null){
    		editText.setText(noteText);
    	}else{
    		editText.setText("");
    	}
    	
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	String noteText = editText.getText().toString();
    	note.saveText(noteText);
    }
    
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
    	
    	@Override
    	public boolean onDoubleTap(MotionEvent e) {
    		editText.setText("");
    		note.saveText("");
    		String message = getResources().getString(R.string.toast_clean);
        	Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    		return super.onDoubleTap(e);
    	}
        
    }
    
}
