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


public class DetailsActivity extends Activity {
	
	TextView name,Amount,Desc,Dates,Status;
	Button settle;
	SetupDatabaseAdapter db = new SetupDatabaseAdapter(this);
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		db.Open();
		settle = (Button)findViewById(R.id.settle_button);
		String Name = getIntent().getExtras().getString("Name");
		String Date = getIntent().getExtras().getString("Date");
		String[] details = db.getDetails(Name, Date);
		name = (TextView)findViewById(R.id.Name_tv);
		name.setText(Name);
		Dates = (TextView)findViewById(R.id.Date_tv);
		Dates.setText(Date);
		Amount=(TextView)findViewById(R.id.Amount_tv);
		Amount.setText(details[1]);
		Desc=(TextView)findViewById(R.id.Desc_tv);
		Desc.setText(details[2]);
		Status=(TextView)findViewById(R.id.Status_tv);
		Status.setText(details[0]);
		
	}
	public void CallSettleAmount(View V) {
		Dates = (TextView)findViewById(R.id.Date_tv);
		String Date = (String)Dates.getText();
		db.settleAmount(Date);
		Toast.makeText(getApplicationContext(), "Amount Settled", Toast.LENGTH_LONG).show();
		//db.Close();
		Intent intent=new Intent(getApplicationContext(),ViewSummaryActivity.class);
        startActivity(intent);
		
	}
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		db.Close();
	}
}
