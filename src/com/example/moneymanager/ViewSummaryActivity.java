package com.example.moneymanager;


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.TextView;

import com.sunrise.easymoneymanager.R;

public class ViewSummaryActivity extends Activity  {

	Spinner myStaticContactSpinner,myNameSpinner;
	Button btnViewAccountSummary;
	TextView staticlend,dynamiclend,staticborrow,dynamicborrow,statictotal,dynamictotal;
	SetupDatabaseAdapter sda;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewsummary);
		loadNames();
		staticlend = (TextView)findViewById(R.id.staticlend_textView);
		dynamiclend = (TextView)findViewById(R.id.dynamiclend_textView);
		staticborrow = (TextView)findViewById(R.id.staticborrow_textView);
		dynamicborrow = (TextView)findViewById(R.id.dynamicborrow_textView);
		statictotal = (TextView)findViewById(R.id.statictotal_textView);
		dynamictotal = (TextView)findViewById(R.id.dynamictotal_textView);
		myNameSpinner = (Spinner)findViewById(R.id.staticname_spinner);
		sda = new SetupDatabaseAdapter(this);
		sda.Open();
		//btnViewAccountSummary = (Button)findViewById(R.id.summary_button);
		myNameSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		//
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
			    
				staticlend.setText("Lend");
				String AccName = String.valueOf(myNameSpinner.getSelectedItem());
				int Lend = sda.getLendings(AccName);
				int Borrow = sda.getBorrowings(AccName);
				
				dynamiclend.setText(""+Lend);
				dynamiclend.setTextColor(Color.GREEN);
				
				staticborrow.setText("Borrow");
				
				dynamicborrow.setText(""+Borrow);
				dynamicborrow.setTextColor(Color.RED);
				
				statictotal.setText("Total");
				int total;
				if(Lend >= Borrow){
					
					total = Lend - Borrow;
					dynamictotal.setText("+"+total);
					dynamictotal.setTextColor(Color.GREEN);
					
				}
				else{
					total = Borrow - Lend;
					dynamictotal.setText("-"+total);
					dynamictotal.setTextColor(Color.RED);				
					
				}
				
			}
			public void onNothingSelected(AdapterView<?> parentView) {
				Toast.makeText(getApplicationContext(), "No Accounts available", Toast.LENGTH_LONG).show();
			}
			});
			
		}
		
		
		
	
	public void loadNames(){
		
		myStaticContactSpinner = (Spinner) findViewById(R.id.staticname_spinner);
		SetupDatabaseAdapter db = new SetupDatabaseAdapter(getApplicationContext());
		List<String> names =  db.getNames();
		 ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
	                android.R.layout.simple_spinner_item, names);
	 
	        // Drop down layout style - list view with radio button
	        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        try{
	        	myStaticContactSpinner.setAdapter(dataAdapter);
	        }
	        catch(Exception e){
	        	
	        	Toast.makeText(getApplicationContext(), "OOPs error occurred", Toast.LENGTH_LONG).show();
	        }
	}
	public void CallViewDates(View V){
        // Create an Intent, which will be use for TestActivity and Start the Activity
        //Below //TestActivity.Class is the activity Class, which we will start from here.
		myNameSpinner = (Spinner)findViewById(R.id.staticname_spinner);
		sda = new SetupDatabaseAdapter(this);
		sda.Open();
		String AccName = String.valueOf(myNameSpinner.getSelectedItem());
        Intent intent=new Intent(getApplicationContext(),ListDateActivity.class);
        intent.putExtra("Name",AccName);
        startActivity(intent);
    }
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		sda.Close();
	}
	
}

