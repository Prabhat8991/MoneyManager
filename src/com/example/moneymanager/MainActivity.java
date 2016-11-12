package com.example.moneymanager;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Intent;
import android.view.View;
import com.sunrise.easymoneymanager.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	 public void callSetupAcc(View V){
	        // Create an Intent, which will be use for TestActivity and Start the Activity
	        //Below //TestActivity.Class is the activity Class, which we will start from here.
	        Intent intent=new Intent(getApplicationContext(),SetupActivity.class);
	        startActivity(intent);
	    }

	 public void callSaveDetailsAcc(View V){
	        // Create an Intent, which will be use for TestActivity and Start the Activity
	        //Below //TestActivity.Class is the activity Class, which we will start from here.
	        Intent intent=new Intent(getApplicationContext(),SaveDetailsActivity.class);
	        startActivity(intent);
	    }
	 public void callViewSummary(View V){
	        // Create an Intent, which will be use for TestActivity and Start the Activity
	        //Below //TestActivity.Class is the activity Class, which we will start from here.
	        Intent intent=new Intent(getApplicationContext(),ViewSummaryActivity.class);
	        startActivity(intent);
	    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

}
