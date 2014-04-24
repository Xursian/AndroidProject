package com.example.finalandroidapp;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

//************************************************
//Wanted to do a friends list type thing here, That lets you browse all "users" in the Database and select them to add to your listview
// then you can search what you have common with those users, But i have not enough time :( /sadface!!!!
//***********************************************

public class FriendsActivity extends Activity {
	
	//public String userNames[] = {"test1","test2","test3"};

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


		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.friends, menu);
		return true;
	}

}
