package com.example.moneymanager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
//import android.content.Intent;
//import android.view.View;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.sunrise.easymoneymanager.R;

public class SetupActivity extends Activity {
	
	EditText editTextUserName,editTextPhone;
	Button btnCreateAccount;
	SetupDatabaseAdapter sda;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acc);
		
		sda = new SetupDatabaseAdapter(this);
		sda = sda.Open();
		//Toast.makeText(getApplicationContext(), "we are here", Toast.LENGTH_LONG).show();
				// Get Refferences of Views
		editTextUserName=(EditText)findViewById(R.id.editTextName);
		editTextPhone=(EditText)findViewById(R.id.editTextPhone);
		btnCreateAccount = (Button)findViewById(R.id.createButton);
		
		btnCreateAccount.setOnClickListener(new View.OnClickListener(){
		
			
		public void onClick(View v){	
		String userName = editTextUserName.getEditableText().toString();
		String phone = editTextPhone.getEditableText().toString();
		// check if any of the fields are vaccant
		Date date = new Date();
		String formattedDate = dateFormat.format(date);
		
		if(userName.equals("") || phone.equals(""))
		{
				Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_SHORT).show();
				return;
		}
		// check if both password matches
		
		else
		{
		    // Save the Data in Database
			int namestatus = sda.checkName(userName);
			if(namestatus == 0){
				Toast.makeText(getApplicationContext(), "Account already exists", Toast.LENGTH_LONG).show();
				return;
			}
		    sda.insertEntry(formattedDate,userName,phone,0,null,null);
		    Toast.makeText(getApplicationContext(), "Account Successfully Created date:"+formattedDate, Toast.LENGTH_LONG).show();
		}
		
	
		
	}
}
);	
}	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		sda.Close();
	}
	 
			
}
