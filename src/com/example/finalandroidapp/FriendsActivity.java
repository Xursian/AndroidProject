package com.example.finalandroidapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

//************************************************
//Wanted to do a friends list type thing here, That lets you browse all "users" in the Database and select them to add to your listview
// then you can search what you have common with those users, But i have not enough time :( /sadface!!!!
//***********************************************

public class FriendsActivity extends Activity {
	
	//public String userNames[] = {"test1","test2","test3"};
	
	TextView txtUserName, txtDetails;
	Button btnSearchUser;

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

		txtUserName = (TextView)findViewById(R.id.txtFindFriendUserName);
		txtDetails = (TextView)findViewById(R.id.txtDisplayResults);
		
		btnSearchUser = (Button)findViewById(R.id.btnMatchFriendsCommon);
			btnSearchUser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//search the database for a match
				final String userNameToSearchFor = txtUserName.getText().toString();
				
			/*	ParseQuery<ParseObject> qry = ParseQuery.getQuery("User");
				qry.whereEqualTo("username", userNameToSearchFor);
				qry.getFirstInBackground(new GetCallback<ParseObject>() {

					@Override
					public void done(ParseObject arg0, ParseException arg1) {
						// TODO Auto-generated method stub
						if (arg0 != null) {
							txtDetails.setText("Found the user: " + userNameToSearchFor);
						} else {
							txtDetails.setText("No User Found for: " + userNameToSearchFor);
						}
						txtDetails.setText("\nMatching Table: User: for username: " + userNameToSearchFor);
					}
					
				});*/
				
			/*	ParseQuery<ParseObject> qry = ParseQuery.getQuery("User"); //table name
				qry.whereEqualTo("username", userNameToSearchFor);
				qry.getFirstInBackground(new GetCallback<ParseObject>() {  //and ONLY one row

					@Override
					public void done(ParseObject arg0, ParseException arg1) {
						//if good then populate fields
						if (arg0 != null) {
							txtDetails.setText("Users Object ID = " + arg0.getString("objectId"));
						} else {
							txtDetails.setText("No such user found");
						}
					}
				});	*/
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.friends, menu);
		return true;
	}

}
