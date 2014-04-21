package com.example.finalandroidapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class TestActivity extends Activity {
	
	TextView txtGames, txtMovies, txtSongs, txtActivities;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		
		//grab the current user
		ParseUser currentUser = ParseUser.getCurrentUser();
		//convert user to string
		String strUser = currentUser.getUsername().toString();
		
		TextView txtUser = (TextView)findViewById(R.id.textView1);
		txtUser.setText(strUser);
		
		final TextView txtErr = (TextView)findViewById(R.id.txtErrs);
		
		//get those details feilds
		txtGames = (TextView)findViewById(R.id.txtGames);
		txtMovies = (TextView)findViewById(R.id.txtMovies);
		txtSongs = (TextView)findViewById(R.id.txtSongs);
		txtActivities = (TextView)findViewById(R.id.txtActivities);

		
		//we want to try to load details of the users current stuff if it exists
		ParseQuery<ParseObject> qry = ParseQuery.getQuery("Details"); //table name
		qry.whereEqualTo("owner", ParseUser.getCurrentUser()).orderByAscending("updatedAt"); //column owner and most recent
		qry.getFirstInBackground(new GetCallback<ParseObject>() {

			@Override
			public void done(ParseObject arg0, ParseException arg1) {
				//if good then populate fields
				if (arg0 != null) {
					txtMovies.setText(arg0.getString("movies"));
					txtGames.setText(arg0.getString("games"));
					txtSongs.setText(arg0.getString("songs"));
					txtActivities.setText(arg0.getString("activities"));
				}
			}
			
		});
		
		
		Button btnSaveDetails = (Button)findViewById(R.id.btnSaveDetails);
		btnSaveDetails.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//check for blanks
				if (txtGames.getText().equals("") || txtMovies.getText().equals("")
						|| txtSongs.getText().equals("") || txtActivities.getText().equals("")) {
					
					Toast.makeText(TestActivity.this, "Please fill out the favorites!", Toast.LENGTH_SHORT).show();
					
				} else {
					//create a new row
					ParseObject post = new ParseObject("Details");
					post.put("games", txtGames.getText().toString());
					post.put("movies", txtMovies.getText().toString());
					post.put("songs", txtSongs.getText().toString());
					post.put("activities", txtActivities.getText().toString());
					//create the relationship
					post.put("owner", ParseUser.getCurrentUser());								
					
					//save
					post.saveInBackground(new SaveCallback() {
						
						@Override
						public void done(ParseException arg0) {
							//check for sucess
							if (arg0 == null) {
								//good
								Toast.makeText(TestActivity.this, "Details Saved Sucssfully", Toast.LENGTH_SHORT).show();
							} else {
								//bad							
								Toast.makeText(TestActivity.this, "Error Saving, try again!", Toast.LENGTH_SHORT).show();
							}
						}
					});
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.test, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_logout:
				//logout
				ParseUser.logOut();
			    finish();
				break;
			case R.id.action_search:
				Toast.makeText(this, "Search coming soon", Toast.LENGTH_SHORT).show();
				break;
			case R.id.action_settings:
				Toast.makeText(this, "Settings coming soon", Toast.LENGTH_SHORT).show();
				break;
			default:
				
		}
		return super.onOptionsItemSelected(item);
	}

}
