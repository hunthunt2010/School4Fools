package com.example.school4foolspleasework;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	public CustomDialogClass cdd;
	
//	 @Override
//	  public void onCreate(Bundle savedInstanceState) {
//	    super.onCreate(savedInstanceState);
//	   
//	    Parse.initialize(this, "fhk8Ez8Mlb5dGCRHv5XesnucSv5neqgaJ4OFjGyY", "GlnCAmeplX7Hr5ApENuhnqCYDmzVo5EM2gyUIbq7"); 
//		ParseAnalytics.trackAppOpened(getIntent());
//		ParseFacebookUtils.initialize("605999516090944");
//	    // start Facebook Login
//	    Session.openActiveSession(this, true, new Session.StatusCallback() {
//
//	      // callback when session changes state
//	      @Override
//	      public void call(Session session, SessionState state, Exception exception) {
//	        if (session.isOpened()) {
//
//	          // make request to the /me API
//	          Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {
//
//	            // callback after Graph API response with user object
//
//				@Override
//				public void onCompleted(GraphUser user, Response response) {
//					if (user != null) {
//		                TextView welcome = (TextView) findViewById(R.id.welcome2);
//		                welcome.setText("Hello " + user.getName() + "!");
//		              }
//				}
//	          });
//	        }
//	      }
//	    });
//	    setContentView(R.layout.activity_main);
//	  }
//
//	  @Override
//	  public void onActivityResult(int requestCode, int resultCode, Intent data) {
//	      super.onActivityResult(requestCode, resultCode, data);
//	      Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
//	  }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Parse.initialize(this, "fhk8Ez8Mlb5dGCRHv5XesnucSv5neqgaJ4OFjGyY", "GlnCAmeplX7Hr5ApENuhnqCYDmzVo5EM2gyUIbq7"); 
		ParseAnalytics.trackAppOpened(getIntent());
		ParseFacebookUtils.initialize("605999516090944");
		setContentView(R.layout.activity_main);
	}
	
	public void signUpAsGuest(View v) {
		final Intent intent = new Intent(this, MainScreenActivity.class);
		ParseUser current = ParseUser.getCurrentUser();
		if (current == null) {
			ParseUser user = new ParseUser();
			user.setUsername("Guest");
			user.setPassword("guestpassword");
			user.signUpInBackground(new SignUpCallback() {
				  public void done(ParseException e) {
				    if (e == null) {
				      Log.d("MyApp", "Yay signed in");
				      startActivity(intent);
				    } else {
				      Log.d("MyApp", "Whoops");
				    }
				  }
				});
		} else {
			startActivity(intent);
		}
	}
	
	public void signUp(View v) {
		Intent intent = new Intent(this, SignUpActivity.class);
		startActivity(intent);
	}
	
	public void logInWFacebook(View v) {
		ParseFacebookUtils.logIn(this, new LogInCallback() {
			@Override
			public void done(ParseUser user, ParseException err) {
				if (user == null) {
					Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
				} else if (user.isNew()) {
					Log.d("MyApp", "User signed up and logged in through Facebook!");
				} else {
					Log.d("MyApp", "User logged in through Facebook!");
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
	}

}
