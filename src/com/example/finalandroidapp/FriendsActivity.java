package com.example.finalandroidapp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

//************************************************
//Wanted to do a friends list type thing here, That lets you browse all "users" in the Database and select them to add to your listview
// then you can search what you have common with those users, But i have not enough time :( /sadface!!!!
//***********************************************

public class FriendsActivity extends Activity {
	
	//public String userNames[] = {"test1","test2","test3"};
	//a test of comparing matchs
	public String test1 = "Rice, Bacon, Cheeze, toast";
	public String test2 = "bacon, lettus, hotdogs, rice";
	
	TextView txtUserName, txtDetails;
	Button btnSearchUser;
	String mstrFriendsId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends);
		
		//final ListView listview = (ListView)findViewById(R.id.listview);
		//setListAdapter(new ArrayAdapter<String>(FriendsActivity.this, R.layout.listview_layout, userNames));
		//final ArrayList<String> list = new ArrayList<String>();
		//for (int i=0; i < userNames.length; i++) {
		//	list.add(userNames[i]);
		//}
		//Parse.initialize(this, "zl19Tr1mCFzHQPaHFGT4Ewlj8lAMGWY1egfpsUzL", "uS5c6yGsyWBOmfAhe5eqycmrLEHNhDgkNd4cy27K");

		txtUserName = (TextView)findViewById(R.id.txtFindFriendUserName);
		txtDetails = (TextView)findViewById(R.id.txtDisplayResults);
		
		btnSearchUser = (Button)findViewById(R.id.btnMatchFriendsCommon);
		btnSearchUser.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//search the database for a match
			final String userNameToSearchFor = txtUserName.getText().toString();
			
			ParseQuery<ParseUser> query = ParseUser.getQuery();
			query.whereEqualTo("username", userNameToSearchFor);
			query.findInBackground(new FindCallback<ParseUser>() {
			  public void done(List<ParseUser> objects, ParseException e) {
			    if (e == null) {
			       for (ParseUser pu : objects) {
			    	   mstrFriendsId = pu.getObjectId();			    	   
			    	   Log.d("TEST","KEY = " + pu.getObjectId());
			       }
			    } else {
			    	txtDetails.setText("No Username Found for : " + userNameToSearchFor);
			    	 Log.d("TEST","FAILS");
			    }
			  }
			});
			
			//tests
			displayWhatsInCommon(test1, test2);
		}
		});
		
	}
	
	//based on comma dilimed info, a generic quick match of common enteries found
	public void displayWhatsInCommon (String firstPerson, String secondPerson) {
		Log.d("TEST","INSIDE PROC");
		//split the string into arrays, based on "," after remove spaces from the string // lower case to hit more matchs
		List<String> listOne = Arrays.asList(firstPerson.replace(" ", "").toLowerCase(Locale.ENGLISH).split(","));
		List<String> listTwo = Arrays.asList(secondPerson.replace(" ", "").toLowerCase(Locale.ENGLISH).split(","));
		String matches = "";
		Log.d("TEST","HAVE CONVERTED LISTS");
		
		//find the matches
		HashSet<String> map = new HashSet<String>();
		for (String one : listOne) {
			map.add(one);
		}
		Log.d("TEST","POPULATED MAP");
		for (String two : listTwo) {
			if (map.contains(two)) {
				matches += two + ", ";
			}
		}
		
		txtDetails.setText("The Matches Are: " + matches);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.friends, menu);
		return true;
	}

}
