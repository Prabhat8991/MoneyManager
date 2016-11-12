package com.example.moneymanager;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import com.sunrise.easymoneymanager.R;



public class DatabaseHelper extends SQLiteOpenHelper {
	
	
	public DatabaseHelper(Context context, String name,CursorFactory factory, int version) 
    {
	           super(context, name, factory, version);
	}
	// Called when no database exists in disk and the helper class needs
	// to create a new one.
	@Override
	public void onCreate(SQLiteDatabase _db) 
	{
			_db.execSQL(SetupDatabaseAdapter.DB_CREATE);

	}
	
	@Override
	public void onUpgrade(SQLiteDatabase _db,int _oldvrsn, int _newvrsn ){
		 _db.execSQL("DROP TABLE IF EXISTS Accountbl" );
		  
	        // Create tables again
	        onCreate(_db);
	}
	
	public List<String> getAllNames(){
        List<String> labels = new ArrayList<String>();
         
        // Select All Query
        String selectQuery = "SELECT  distinct NAME FROM Accountbl";
      
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
      
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(cursor.getColumnIndex("NAME")));
            } while (cursor.moveToNext());
        }
         
        // closing connection
        cursor.close();
        db.close();
       
        // returning lables
        return labels;
    }	
	public int getLendAmount(String Name){
        int SumLend=0;
         
        // Select All Query
        String selectQuery = "SELECT  Sum(AMOUNT) as Total FROM Accountbl where Name = '"+Name+"' and Category = 'L'";
      
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
      
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	try{
                  SumLend=Integer.parseInt(cursor.getString(cursor.getColumnIndex("Total")));
            	}
            	catch(Exception e){
            		return 0;  	
            		
            	}
            } while (cursor.moveToNext());
        }
         
        // closing connection
        cursor.close();
        db.close();
       
        // returning lables
        return SumLend;
    }
	
	public int getBorrowAmount(String Name){
        int SumBorrow=0;
         
        // Select All Query
        String selectQuery = "SELECT  Sum(AMOUNT) as Total FROM Accountbl where Name = '"+Name+"' and Category = 'B'";
      
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
      
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                try{
            	 SumBorrow=Integer.parseInt(cursor.getString(cursor.getColumnIndex("Total")));
                }
                catch(Exception e){
                	
                	return 0;
                }
            } while (cursor.moveToNext());
        }
         
        // closing connection
        cursor.close();
        db.close();
       
        // returning lables
        return SumBorrow;
    }
	public String[] getCatAmtDescByNameDate(String Name,String Date){
        
         
        // Select All Query
		String [] details = new String[3];
        String selectQuery = "SELECT  AMOUNT,CATEGORY,REMARK FROM Accountbl where NAME = '"+Name+"' and DATE = '"+Date+"'";
      
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
      
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                details[0]=cursor.getString(cursor.getColumnIndex("CATEGORY"));
                details[1]=cursor.getString(cursor.getColumnIndex("AMOUNT"));
                details[2]=cursor.getString(cursor.getColumnIndex("REMARK"));
            } while (cursor.moveToNext());
        }
         
        // closing connection
        cursor.close();
        db.close();
       
        // returning lables
        return details;
    }	
	public List<String> getAllDates(String Name){
        List<String> labels = new ArrayList<String>();
         
        // Select All Query
        String selectQuery = "SELECT DATE FROM Accountbl where NAME = '"+Name+"' and CATEGORY in ('L','B','S') order by ID";
      
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
      
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(cursor.getColumnIndex("DATE")));
            } while (cursor.moveToNext());
        }
         
        // closing connection
        cursor.close();
        db.close();
       
        // returning lables
        return labels;
    }
	public void settleAmount(String Date){
        
         
        // Select All Query
        //String deleteQuery = "delete  FROM Accountbl where Date = '"+Date+"'";
      
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("CATEGORY", "S");
        //db.delete("Accountbl","DATE = '"+Date+"'", null);
        try{
        	db.update("Accountbl",cv, "DATE = '" + Date + "'", null);
        }
        catch(Exception e){
        	
        	e.printStackTrace();
        	
        }
      
        // looping through all rows and adding to list
                // closing connection
        
        db.close();
       
        // returning lables
        
    }
	/*
	public String getDescription(String Date){
        List<String> labels = new ArrayList<String>();
         
        // Select All Query
        String selectQuery = "SELECT DATE FROM Accountbl where NAME = '"+Name+"' and CATEGORY in ('L','B') order by ID";
      
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
      
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(cursor.getColumnIndex("DATE")));
            } while (cursor.moveToNext());
        }
         
        // closing connection
        cursor.close();
        db.close();
       
        // returning lables
        return labels;
    }
	*/
	public void deleteAmount(String Date){
        
        
        // Select All Query
        //String deleteQuery = "delete  FROM Accountbl where Date = '"+Date+"'";
      
        SQLiteDatabase db = this.getWritableDatabase();
        //ContentValues cv = new ContentValues();
        //cv.put("CATEGORY", "S");
        db.delete("Accountbl","DATE = '" + Date + "'", null);
        //db.update("Accountbl",cv, "DATE = '"+Date+"'", null);
        
      
        // looping through all rows and adding to list
                // closing connection
        
        db.close();
       
        // returning lables
        
    }

	
	
}
