package com.example.teatrulcaracalean;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class IstoricTeatru extends MainActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_istoric);
		
		TextView tvi = (TextView)findViewById(R.id.tvIstoric);
		tvi.setMovementMethod(new ScrollingMovementMethod());
	}
	
}
