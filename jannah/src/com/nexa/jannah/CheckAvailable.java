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



import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.DatePicker;
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
public class CheckAvailable extends BaseActivity 
{


	private int fra,tab;
	private DatePicker dpResult;
	TextView txtCheck;
	TextView txtCheckout;
	static final int DATE_DIALOG_ID = 999;
	private int year;
	private int month;
	private int day;
	int i;

	public CheckAvailable() {
		super(R.id.row_title);
		// TODO Auto-generated constructor stub
	}


	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		Drawable titleBar=getResources().getDrawable(R.drawable.top_menu);  
		getActionBar().setBackgroundDrawable(titleBar);

		setContentView(R.layout.activity_check_aviable);
		
		dpResult = (DatePicker) findViewById(R.id.datePicker1);
		dpResult.setVisibility(View.GONE);



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
				Intent intent=new Intent(CheckAvailable.this,WebViewtemp.class);
				startActivity(intent);
			}
		});
		ImageButton mapp=(ImageButton)findViewById(R.id.map);
		mapp.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), "ddd", Toast.LENGTH_SHORT).show();
				Intent intent=new Intent(CheckAvailable.this,JannaMap.class);
				startActivity(intent);
			}
		});


		ImageButton btnback=(ImageButton)findViewById(R.id.btn_backcheck);

		btnback.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(CheckAvailable.this,RootActivity.class);
				intent.putExtra("tab","1" );
				intent.putExtra("fra","1");
				CheckAvailable.this.startActivity(intent);

			}
		});

		txtCheck=(TextView)findViewById(R.id.txtCheck);
		txtCheck.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		
				i=0;
				showDialog(DATE_DIALOG_ID);
			}
		});
		
		txtCheckout=(TextView)findViewById(R.id.txtCheckout);
		txtCheckout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				showDialog(DATE_DIALOG_ID);
				i=1;
		
			}
		});
		
		
		ImageButton btnCheck=(ImageButton)findViewById(R.id.imageButton1);
		btnCheck.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(CheckAvailable.this,WebViewtemp.class);
				
				CheckAvailable.this.startActivity(intent);
				
		
			}
		});
		

	}
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			// set date picker as current date
			return new DatePickerDialog(this, datePickerListener, 2013, 10,
					18);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set selected date into textview
			if(i==0)
			txtCheck.setText("Checked In Date "+new StringBuilder().append(month + 1)
						.append("-").append(day).append("-").append(year)
						.append(" "));
			if(i==1)
				txtCheckout.setText("Checked Out Date "+new StringBuilder().append(month + 1)
						.append("-").append(day).append("-").append(year)
						.append(" "));
			// set selected date into datepicker also
			dpResult.init(year, month, day, null);

		}
	};

}
