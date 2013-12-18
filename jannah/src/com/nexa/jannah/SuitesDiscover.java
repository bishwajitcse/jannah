package com.nexa.jannah;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.nexa.jannah.xml.XMLParser;
import com.nexa.slider.BaseActivity;
import com.nexa.tapadapter.KarimListAdapter;
import com.nexa.tapadapter.SuitesDiscoverListAdapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The <code>TabsViewPagerFragmentActivity</code> class implements the Fragment
 * activity that maintains a TabHost using a ViewPager.
 * 
 * @author jitesh
 */
public class SuitesDiscover extends BaseActivity 
 {


	private LayoutInflater layoutInflater;
	private int fra,tab;
	
	int[] images = {R.drawable.jaanha_1,R.drawable.jaanha_2,R.drawable.jaanha_3 }; 
	private int currentImage = 0;

	public SuitesDiscover() {
		super(R.id.row_title);
		// TODO Auto-generated constructor stub
	}


	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		Drawable titleBar=getResources().getDrawable(R.drawable.top_menu);  
		getActionBar().setBackgroundDrawable(titleBar);

		setContentView(R.layout.activity_suites_discover);
		
		ImageButton disbook=(ImageButton)findViewById(R.id.dis_book);
		disbook.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(SuitesDiscover.this,WebViewtemp.class);
				startActivity(intent);
			}
		});
		

		String caption=getIntent().getExtras().getString("caption");
		String details=getIntent().getExtras().getString("details");
		TextView txt=(TextView)findViewById(R.id.textView1);
		TextView txtdetails=(TextView)findViewById(R.id.textView2);
		txt.setText(caption);
		txtdetails.setText(details);


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
				Intent intent=new Intent(SuitesDiscover.this,WebViewtemp.class);
				startActivity(intent);
			}
		});
		ImageButton mapp=(ImageButton)findViewById(R.id.map);
		mapp.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), "ddd", Toast.LENGTH_SHORT).show();
				Intent intent=new Intent(SuitesDiscover.this,JannaMap.class);
				startActivity(intent);
			}
		});

		ImageButton suites=(ImageButton)findViewById(R.id.imageButton1);


		//
		//		this.initialiseTabHost(savedInstanceState);
		//		if (savedInstanceState != null) {
		//			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab")); // set
		//
		//		}

		ArrayList image_details = new ArrayList();



		AssetManager assetManager = getApplicationContext().getAssets();
		InputStream inputStream = null;
		try {
			inputStream = assetManager.open("karim.xml");
		} catch (IOException e) {
			Log.i("tag", e.getMessage());
		}

		String s = readTextFile(inputStream);

		XMLParser xml=new XMLParser();
		Document doc=xml.getDomElement(s);
		NodeList nl = doc.getElementsByTagName("item");

		HashMap hmOfferHead=new HashMap();




		for (int i =0; i<nl.getLength(); i++) {
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nl.item(i);
			// adding each child node to HashMap key => value
			image_details.add(xml.getValue(e,"head"));
			//	image_details.put(xml.getValue(e,"head"));

		}
		
		ProgressBar progress=(ProgressBar)findViewById(R.id.progressBar);
		progress.setIndeterminate(true);
		progress.setVisibility(View.VISIBLE);
		

		ListView la=(ListView)findViewById(R.id.list_discoverfeature);
		la.setAdapter(new SuitesDiscoverListAdapter(getApplicationContext(), image_details,hmOfferHead));
		
		progress.setVisibility(View.GONE);
		
		
		
		
		ImageButton pre=(ImageButton)findViewById(R.id.imageButton2);
		ImageButton next=(ImageButton)findViewById(R.id.imageView5);
		
		final ImageView image=(ImageView)findViewById(R.id.imageView4);

		pre.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				currentImage--;
				currentImage = (currentImage + images.length) % images.length;
				image.setBackgroundResource(images[currentImage]);
			}
		});

		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				currentImage++;
				currentImage = currentImage % images.length;

				image.setBackgroundResource(images[currentImage]);
			}
		});
		
		ImageButton btnback=(ImageButton)findViewById(R.id.imageButton1);

		btnback.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(SuitesDiscover.this,RootActivity.class);
				intent.putExtra("tab","1" );
				intent.putExtra("fra","1");
				SuitesDiscover.this.startActivity(intent);

			}
		});

	}
	private String readTextFile(InputStream inputStream) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		byte buf[] = new byte[1024];
		int len;
		try {
			while ((len = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, len);
			}
			outputStream.close();
			inputStream.close();
		} catch (IOException e) {

		}
		return outputStream.toString();
	}


}
