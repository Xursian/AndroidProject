package com.example.finalandroidapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends Activity {
	
	Button btnLogin, btnSignup;
	String strUserName, strPassword;
	EditText txtUsername, txtPassword;
	TextView txtErr;
	CheckBox chkbxSaveLogInInfo;
	
	protected String mAction;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout); //login layout
		
		//init parse
		Parse.initialize(this, "zl19Tr1mCFzHQPaHFGT4Ewlj8lAMGWY1egfpsUzL", "uS5c6yGsyWBOmfAhe5eqycmrLEHNhDgkNd4cy27K");
		
		//grab the locations
		txtUsername = (EditText)findViewById(R.id.txtUsername);
		txtPassword = (EditText)findViewById(R.id.txtPassword);
		btnLogin = (Button)findViewById(R.id.btnLogin);
		btnSignup = (Button)findViewById(R.id.btnRegister);
		txtErr = (TextView)findViewById(R.id.txtError);
		chkbxSaveLogInInfo = (CheckBox)findViewById(R.id.chxbxRememberMe);
		
		//depending which option was selected from main activity, we want to hide the other option
		Bundle bundle = getIntent().getExtras();
		mAction = bundle.getString("type");
		
		//hide a button if not needed
		if (mAction.equals("login")) {
			btnSignup.setVisibility(View.GONE);
			
			//we want to try to load saved data (if the checkbox is clicked)		
			try {
				Log.d("TEST","Attempt Load SP");
				SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE); //SP name is loginInfo
				//get value of the checkbox
				chkbxSaveLogInInfo.setChecked(sp.getBoolean("saveLoginInfo", false));
				//now if checkbox is true we want to load the name and pass,
				Log.d("TEST","Value of CHkBx=" + String.valueOf(chkbxSaveLogInInfo.isChecked()));
				if (chkbxSaveLogInInfo.isChecked()) {
					txtUsername.setText(sp.getString("userName", ""));
					txtPassword.setText(sp.getString("userPass", ""));
					Log.d("TEST","Loaded SP");
				}
			} catch (Exception ex){
				chkbxSaveLogInInfo.setChecked(false);
				Log.d("TEST","ERROR LOADING SP");
			}
		} else {
			btnLogin.setVisibility(View.GONE);
			//the register doesn't need to load details its a new account
		}
		
		//signup button
		btnSignup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//grab the entered fields
				strUserName = txtUsername.getText().toString();
				strPassword = txtPassword.getText().toString();
				
				//check for saving of their info for next time
				try {
					Log.d("TEST","Attempt Save SP");
					//set up SharedPref
					SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
					//get editor object to save data
					SharedPreferences.Editor editor = sp.edit();
					//save if they want to keep their info or not
					Log.d("TEST","Value of save CHkBx=" + String.valueOf(chkbxSaveLogInInfo.isChecked()));
					if (chkbxSaveLogInInfo.isChecked()) {
						//keep all their info
						editor.putString("userName", strUserName);
						editor.putString("userPass", strPassword);
						editor.putBoolean("saveLoginInfo", chkbxSaveLogInInfo.isChecked());
						Log.d("TEST","Save checked sucessful");
					} else {
						//save that they don't want to and overwrite the files to blank
						editor.putString("userName", "");
						editor.putString("userPass", "");
						editor.putBoolean("saveLoginInfo", chkbxSaveLogInInfo.isChecked());
						Log.d("TEST","Save unchecked sucessful");
					}
					//commit the save
					editor.commit();
				} catch (Exception ex) {
					Log.d("TEST","ERROR SAVING FAILED");
				}
				
				//check for quick validation
				if (strUserName.equals("") || strPassword.equals("")) {
					txtErr.setText("Please Enter A Username And Password!");					
				} else {
					//lets create their profile
					ParseUser user = new ParseUser();
					
					user.setUsername(strUserName);
					user.setPassword(strPassword);
					
					user.signUpInBackground(new SignUpCallback() {
						
						@Override
						public void done(ParseException arg0) {
							// check if it exceeds
							if (arg0 == null) {
								//sucess - go to app
								startActivity(new Intent(LoginActivity.this, TestActivity.class));
								finish();
							} else {
								//fail
								txtErr.setText("Sorry creation failed, Try again!");
							}
						}
					});
				}
			}
		});
		
		//here we do a login with parse
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//grab the values
				strUserName = txtUsername.getText().toString();
				strPassword = txtPassword.getText().toString();
				
				//check for saving of their info for next time
				try {
					//set up SharedPref
					SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
					//get editor object to save data
					SharedPreferences.Editor editor = sp.edit();
					//save if they want to keep their info or not
					if (chkbxSaveLogInInfo.isChecked()) {
						//keep all their info
						editor.putString("userName", strUserName);
						editor.putString("userPass", strPassword);
						editor.putBoolean("saveLoginInfo", chkbxSaveLogInInfo.isChecked());
					} else {
						//save that they don't want to and overwrite the files to blank
						editor.putString("userName", "");
						editor.putString("userPass", "");
						editor.putBoolean("saveLoginInfo", chkbxSaveLogInInfo.isChecked());
					}
					editor.commit();
				} catch (Exception ex) {
					
				}
				
				//login using parser
				ParseUser.logInInBackground(strUserName, strPassword, new LogInCallback() {
					
					@Override
					public void done(ParseUser arg0, ParseException arg1) {
						// check for success
						if (arg0 != null) {
							//arg0 is user, if authenticated go to the app
							Intent intent = new Intent(LoginActivity.this, TestActivity.class);
							startActivity(intent);
							finish();
						} else {
							//failed
							txtErr.setText("Login Failed: Try again!");
						}
					}
				});
				
			}
		});
		
		
	}
}
