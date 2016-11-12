package com.example.moneymanager;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class SetupDatabaseAdapter  {
	
	static final String DB_NAME = "AccountsMM.db";
	static final int DB_VRSN = 5;
	//creating a new table accounts
	static final String DB_CREATE = "create table Accountbl (ID integer Primary Key Autoincrement, DATE text,NAME text , PHONE text, AMOUNT integer , CATEGORY text,REMARK text);";
	//variable to hold database instance
	public SQLiteDatabase db;
	//context in the database
	private final Context contextdb;
	//Database helper	
	private DatabaseHelper dbhelper;
	//constructor for SetupDatabaseAdapter
	public SetupDatabaseAdapter(Context _context){
		
		contextdb = _context;
		dbhelper = new DatabaseHelper(contextdb , DB_NAME , null , DB_VRSN);
		
	}
	
	public SetupDatabaseAdapter Open () throws SQLException{
		
		db = dbhelper.getWritableDatabase();
		return this;
		
	}

	public void Close(){
		
		db.close();
	}
	
	public SQLiteDatabase getDBinstance(){
		
		return db;
	}
	
	public void insertEntry(String Date,String Name,String Phone,int amount,String category,String remark)
	{
        ContentValues newValues = new ContentValues();
		// Assign values for each row.
        newValues.put("DATE", Date);
		newValues.put("NAME", Name);
		newValues.put("PHONE",Phone);
		newValues.put("AMOUNT",amount);
		newValues.put("CATEGORY",category);
		newValues.put("REMARK",remark);
		// Insert the row into your table
		try{
			db.insert("Accountbl", null, newValues);
		}
		catch(Exception E){
			E.printStackTrace();
		}
	}
	 public int checkName(String userName)
     {
         Cursor cursor=db.query("Accountbl", null, " NAME=?", new String[]{userName}, null, null, null);
         if(cursor.getCount()<1) // UserName Not Exist
         {
             cursor.close();
             return 1;
         }
         return 0;
        // cursor.moveToFirst();
        // String name= cursor.getString(cursor.getColumnIndex("Name"));
        // cursor.close();
        // return name;                
     }
	 public List<String> getNames(){
		 
		List<String> Names = dbhelper.getAllNames();
		 
		return Names;
	 }
	 public int getLendings(String Name){
		 
		 int Lendings = dbhelper.getLendAmount(Name);
		 return Lendings;
		 
	 }
	 public int getBorrowings(String Name){
		int Borrowings = dbhelper.getBorrowAmount(Name);
		return Borrowings;
		 
	 }
	 public List<String> getDates(String Name){
		 
			List<String> Names = dbhelper.getAllDates(Name);
			 
			return Names;
		 }
	 public String[] getDetails(String Name,String Date){
		 
		 String [] details = dbhelper.getCatAmtDescByNameDate(Name, Date);
		 return details;
		 
	 } 
	 public void settleAmount(String Date){
		 
			dbhelper.settleAmount(Date);
		 //dbhelper.delete("Accountbl","DATE = '"+Date+"'", null);
		 
			
		 }
	 
	 public void deleteAmount(String Date){
		 
			dbhelper.deleteAmount(Date);
		 //dbhelper.delete("Accountbl","DATE = '"+Date+"'", null);
		 
			
		 }


}
