package com.nexa.jannah;



import com.nexa.slider.BaseActivity;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class WebViewtemp extends BaseActivity {

	public WebViewtemp() {
		super(R.string.title_activity_web_viewtemp);
		// TODO Auto-generated constructor stub
	}

	WebView webView;
	private ProgressBar progressbar = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Drawable titleBar=getResources().getDrawable(R.drawable.top_menu);  
		getActionBar().setBackgroundDrawable(titleBar);
		setContentView(R.layout.activity_web_viewtemp);
		
		
		//this.setProgressBarVisibility(true);
		
		final android.app.ActionBar ab = getActionBar();
		ab.setDisplayShowHomeEnabled(true);
		ab.setDisplayShowTitleEnabled(false);     
		final LayoutInflater inflater = (LayoutInflater)getSystemService("layout_inflater");
		View view = inflater.inflate(R.layout.actionbar,null); 
		ab.setCustomView(view);
		ab.setDisplayShowCustomEnabled(true);

		
		
		ImageButton home=(ImageButton)findViewById(R.id.home);
		home.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
					finish();
			}
		});

	
		ImageButton book=(ImageButton)findViewById(R.id.book);
		book.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(WebViewtemp.this,WebViewtemp.class);
				startActivity(intent);
			}
		});
		ImageButton mapp=(ImageButton)findViewById(R.id.map);
		mapp.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), "ddd", Toast.LENGTH_SHORT).show();
				Intent intent=new Intent(WebViewtemp.this,JannaMap.class);
				startActivity(intent);
			}
		});
		
		
		progressbar = (ProgressBar) findViewById(R.id.progressBar);
		progressbar.setIndeterminate(true);
		progressbar.setVisibility(View.VISIBLE);
		
		webView = (WebView) findViewById(R.id.webView);
		webView.setWebViewClient(new myWebClient());
		webView.getSettings().setJavaScriptEnabled(true);

		webView.getSettings().setBuiltInZoomControls(true);
		
	
		webView.loadUrl("http://www.jannah.ae");
		
		//if(webView.getBackground()!=null){
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

				progressbar.setVisibility(View.GONE);

			}
		}, 10000);
			
		//}
	
	}

	public class myWebClient extends WebViewClient
	{
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			// TODO Auto-generated method stub
			super.onPageStarted(view, url, favicon);
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub

			view.loadUrl(url);
			
			return true;

		}
	}

	// To handle "Back" key press event for WebView to go back to previous screen.
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}


}
