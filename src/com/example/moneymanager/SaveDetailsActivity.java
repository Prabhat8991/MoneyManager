package com.example.moneymanager;

import java.util.List;

import android.app.Activity;
import android.content.Context;
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
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.sunrise.easymoneymanager.R;


public class SaveDetailsActivity extends Activity  {

	Spinner myContactSpinner,saveSpinnerName,saveSpinnerCategory;
	EditText amount,description;
	Button btnSaveAccount;
	SetupDatabaseAdapter sda;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_savedata);
		loadNames();
		sda = new SetupDatabaseAdapter(this);
		sda.Open();
		btnSaveAccount = (Button)findViewById(R.id.SaveAccDetails_Button);
		saveSpinnerName = (Spinner) findViewById(R.id.name_spinner);
		amount = (EditText)findViewById(R.id.Amount_EditText);
		description = (EditText)findViewById(R.id.Description_EditText);
		saveSpinnerCategory = (Spinner) findViewById(R.id.category_spinner);
		btnSaveAccount.setOnClickListener(new View.OnClickListener(){
			
			
			public void onClick(View v){	
			String AccName = String.valueOf(saveSpinnerName.getSelectedItem());
			String AmountStr= amount.getEditableText().toString();
			String Category = String.valueOf(saveSpinnerCategory.getSelectedItem());
			String desc = description.getEditableText().toString();
			Date date = new Date();
			String formattedDate = dateFormat.format(date);
			// check if any of the fields are vaccant
			Integer Amount;
			if(AmountStr.equals("")){
				
				Amount = 0;
			}
			else{
				Amount=Integer.parseInt(AmountStr);
			}
			
			if(Amount == 0)
			{
					Toast.makeText(getApplicationContext(), "Enter a valid amount", Toast.LENGTH_SHORT).show();
					return;
			}
			else if(desc.equals("")){
				
				Toast.makeText(getApplicationContext(), "Enter a description", Toast.LENGTH_SHORT).show();
				return;
				
			}
			
			else
			{
			    // Save the Data in Database
				String Cat;
				
				if(Category.equals("Lend")){
					Cat = "L";
				}
				else{
					Cat = "B";
				}
			    sda.insertEntry(formattedDate,AccName,null,Amount,Cat,desc);
			    Toast.makeText(getApplicationContext(), "data saved successfully", Toast.LENGTH_LONG).show();
			}
			
		
			
		}
	}
	);	
		
		
	}
	public void loadNames(){
		
		myContactSpinner = (Spinner) findViewById(R.id.name_spinner);
		SetupDatabaseAdapter db = new SetupDatabaseAdapter(getApplicationContext());
		List<String> names =  db.getNames();
		 ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
	                android.R.layout.simple_spinner_item, names);
	 
	        // Drop down layout style - list view with radio button
	        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        try{
	        	myContactSpinner.setAdapter(dataAdapter);
	        }
	        catch(Exception e){
	        	
	        	Toast.makeText(getApplicationContext(), "OOPs error occurred", Toast.LENGTH_LONG).show();
	        }
	}
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		sda.Close();
	}
	
}
