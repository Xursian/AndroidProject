package com.example.finalandroidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button btnLogIn, btnRegister;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//grab our ids
		btnLogIn = (Button)findViewById(R.id.btnLogInMain);
		btnRegister = (Button)findViewById(R.id.btnRegisterMain);
		
		//set listeners up
		btnLogIn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//intent to login with parse
				Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
				loginIntent.putExtra("type", "login");
				startActivity(loginIntent);
			}
		});
		
		btnRegister.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//intent to login with parse
				Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
				loginIntent.putExtra("type", "register");
				startActivity(loginIntent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}
