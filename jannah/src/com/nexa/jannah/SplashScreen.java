package com.nexa.jannah;


import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.Toast;

public class SplashScreen extends Activity {



	// Splash screen timer
	private static int SPLASH_TIME_OUT = 3000;
	private boolean mIsBackButtonPressed;



	SharedPreferences appPreferences;
	boolean isAppInstalled = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.splashscreen);
		
		
		
		//if(isNetworkAvilable()){
			openapp();
		//}
		//else{
		//	networkDialog();
		//}
	}

	private void networkDialog(){

		AlertDialog.Builder alertDialog = new AlertDialog.Builder(SplashScreen.this);

		// Setting Dialog Title
		alertDialog.setTitle("Network Information...");

		// Setting Dialog Message
		alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");

		// Setting Icon to Dialog
		alertDialog.setIcon(R.drawable.ic_launcher);

		alertDialog.setCancelable(false);
		alertDialog.setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int which) {

				// Write your code here to invoke YES event
				if(isNetworkAvilable())
					openapp();
				else
					networkDialog();
			}
		});

		alertDialog.setNegativeButton("QUIT", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		});

		alertDialog.show();
	}
	/*Another Activity open method*/
	private void openapp() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

				//finish();

				if (!mIsBackButtonPressed) {
				
					Intent intent = new Intent(SplashScreen.this, RootActivity.class);
					intent.putExtra("tab","1" );
					intent.putExtra("fra","1");
					SplashScreen.this.startActivity(intent);
					
				}

			}
		}, SPLASH_TIME_OUT);
	}


	private boolean isNetworkAvilable(){
		System.out.println("OK Net");
		ConnectivityManager cm=(ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo info=cm.getActiveNetworkInfo();
		return(info!=null);
	}


}


