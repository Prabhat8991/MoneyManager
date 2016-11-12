package com.example.moneymanager;


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemSelectedListener;
//import android.widget.ArrayAdapter;
import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import com.sunrise.easymoneymanager.R;
import android.os.*;


public class ListDateActivity extends Activity{
	
	TextView Date,Date2,Amount,Desc,settle;
	Button btnViewDetailSummary,btnSettleAmount,btnDeleteAmount;
	int  uniqueid = 0;
	SetupDatabaseAdapter db;
	String[] details;
	String dates;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_linearlist);
		String Name = getIntent().getExtras().getString("Name");
		LinearLayout layout=(LinearLayout)findViewById(R.id.linearScroll_list);
		//LayoutInflater inflater= (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//View view=inflater.inflate(R.layout.activity_panel,null);
		db = new SetupDatabaseAdapter(this);
		db.Open();
		List<String> Dates =  db.getDates(Name);
		for(String listDates :Dates)
		{
			
			LayoutInflater inflater= (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view=inflater.inflate(R.layout.activity_panel,null);
			//Date = (TextView)view.findViewById(R.id.date_tv);
			//Date.setText(listDates);
			//Date.setId(uniqueid);
			btnViewDetailSummary = (Button)view.findViewById(R.id.date_button);
			btnSettleAmount = (Button)view.findViewById(R.id.settle_button);
			btnDeleteAmount = (Button)view.findViewById(R.id.delete_button);
			Amount = (TextView)view.findViewById(R.id.amount_tv);
			Desc = (TextView)view.findViewById(R.id.desc_tv);
			settle = (TextView)view.findViewById(R.id.settle_tv);
			btnViewDetailSummary.setText(listDates);
			settle.setId(uniqueid);
			btnDeleteAmount.setId(uniqueid+1);
			btnSettleAmount.setId(uniqueid+2);
			btnSettleAmount.setTag(listDates);
			btnDeleteAmount.setTag(listDates);
			dates = listDates ;
			details = db.getDetails(Name,listDates);
			Amount.setText(details[1]);
			if(details[0].equals("L")){
				
				Amount.setTextColor(Color.GREEN);
				
			}
			else{
				Amount.setTextColor(Color.RED);
				
			}
			if(details[0].equals("S")){
				
				
				settle.setTextColor(Color.YELLOW);
				settle.setText("settled");
			}
			else{
				settle.setTextColor(Color.BLUE);
				settle.setText("unsettled");
			}
			Desc.setText(details[2]);
			
			//final int  findid = uniqueid;
			btnSettleAmount.setOnClickListener(new View.OnClickListener(){
			
				public void onClick(View settle){
					
					Button settle2 ;
					int id = settle.getId();
					settle2 = (Button)settle.findViewById(id);
					db.settleAmount((String)settle2.getTag());
					Toast.makeText(getApplicationContext(), "Amount Settled", Toast.LENGTH_LONG).show();
					settle2.setText("settled");
					/*TextView settle3;					
					int id = settle.getId();
					settle2 = (TextView)settle.findViewById(uniqueid);
					settle2.setText("settled");
					settle2.setTextColor(Color.YELLOW);
					*/
					
					/*Button Date2;
					int id = v.getId();
					Date2 = (Button)v.findViewById(id);
					String Date3 = Date2.getText().toString();
					String Name = getIntent().getExtras().getString("Name");
				    Intent intent=new Intent(getApplicationContext(),DetailsActivity.class);
				    intent.putExtra("Name", Name);
				    intent.putExtra("Date",Date3);
				    startActivity(intent);*/
					
				}
				
			}
		);	
			
			btnDeleteAmount.setOnClickListener(new View.OnClickListener(){
				
				public void onClick(View v){
					
					Button Date2;
					int id = v.getId();
					Date2 = (Button)v.findViewById(id);
					//String Date3 = Date2.getText().toString();

					
					db.deleteAmount((String)Date2.getTag());
					Toast.makeText(getApplicationContext(), "Amount Deleted", Toast.LENGTH_LONG).show();
					//settle.setText("settled");
					//settle.setTextColor(Color.YELLOW);
					
					    Intent intent = getIntent();
					    overridePendingTransition(0, 0);
					    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
					    finish();

					    overridePendingTransition(0, 0);
					    startActivity(intent);
					
					
					
					
					/*Button Date2;
					int id = v.getId();
					Date2 = (Button)v.findViewById(id);
					String Date3 = Date2.getText().toString();
					String Name = getIntent().getExtras().getString("Name");
				    Intent intent=new Intent(getApplicationContext(),DetailsActivity.class);
				    intent.putExtra("Name", Name);
				    intent.putExtra("Date",Date3);
				    startActivity(intent);*/
					
				}
				
			}
		);	
			
			
			layout.addView(view);
			uniqueid++;
		}
		

		
		
	}
	
	/* public void CallDetailsActivity(View V){
	        // Create an Intent, which will be use for TestActivity and Start the Activity
	        //Below //TestActivity.Class is the activity Class, which we will start from here.
		    String Name = getIntent().getExtras().getString("Name");
		    Date2 = (TextView)findViewById(uniqueid);
		    String Date3 = Date2.getText().toString();
	        Intent intent=new Intent(getApplicationContext(),DetailsActivity.class);
	        intent.putExtra("Name", Name);
		    intent.putExtra("Date",Date3);
	        startActivity(intent);
	    }
	    */
	 protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();

			db.Close();
		}

}
