package com.nexa.jannah;



import java.util.HashMap;
import java.util.List;
import java.util.Vector;



import com.nexa.slider.BaseActivity;


import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
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
public class RootActivity extends BaseActivity implements
TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {


	private LayoutInflater layoutInflater;
	ProgressBar progress;
	private int fra,tab;

	public RootActivity() {
		super(R.id.row_title);
		// TODO Auto-generated constructor stub
	}
	public void switchContent(final int i) {
		Handler h = new Handler();
		h.postDelayed(new Runnable() {
			public void run() {
				getSlidingMenu().showContent();
				if(i==0){
					mTabHost.setCurrentTab(0);
					mViewPager.setCurrentItem(0);
				}
				if(i==1){
					mTabHost.setCurrentTab(3);
					mViewPager.setCurrentItem(3);
				}
			}
		}, 50);
	}	

	private static TabHost mTabHost;
	private ViewPager mViewPager;
	private HashMap<String, TabInfo> mapTabInfo = new HashMap<String, RootActivity.TabInfo>();
	private PagerAdapter mPagerAdapter;

	/**
	 * 
	 * @author mwho Maintains extrinsic info of a tab's construct
	 */
	private class TabInfo {
		private String tag;
		private Class<?> clss;
		private Bundle args;
		private Fragment fragment;

		TabInfo(String tag, Class<?> clazz, Bundle args) {
			this.tag = tag;
			this.clss = clazz;
			this.args = args;
		}

	}

	/**
	 * A simple factory that returns dummy views to the Tabhost
	 * 
	 * @author mwho
	 */
	class TabFactory implements TabContentFactory {

		private final Context mContext;

		/**
		 * @param context
		 */
		public TabFactory(Context context) {
			mContext = context;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see android.widget.TabHost.TabContentFactory#createTabContent(java.lang.String)
		 */
		public View createTabContent(String tag) {
			View v = new View(mContext);
			v.setMinimumWidth(0);
			v.setMinimumHeight(0);
			return v;
		}

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.rootpage);
		Drawable d=getResources().getDrawable(R.drawable.top_menu);  
		getActionBar().setBackgroundDrawable(d);
		
		
		progress=(ProgressBar)findViewById(R.id.progressBar);
		progress.setIndeterminate(true);
		progress.setVisibility(View.VISIBLE);

		final ActionBar ab = getActionBar();
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

				mTabHost.setCurrentTab(1);
				mViewPager.setCurrentItem(1);
			}
		});

	
		ImageButton book=(ImageButton)findViewById(R.id.book);
		book.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(RootActivity.this,WebViewtemp.class);
				startActivity(intent);
			}
		});
		ImageButton mapp=(ImageButton)findViewById(R.id.map);
		mapp.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), "ddd", Toast.LENGTH_SHORT).show();
				Intent intent=new Intent(RootActivity.this,JannaMap.class);
				startActivity(intent);
			}
		});

		//		// Initialise the TabHost
		this.initialiseTabHost(savedInstanceState);
				if (savedInstanceState != null) {
					mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab")); // set
		//																				// the
		//																				// tab
		//																				// as
		//																				// per
		//																				// the
		//																				// saved
		//																				// state
				}
		//		// Intialise ViewPager
		this.intialiseViewPager();
		//		
		//		



	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onSaveInstanceState(android.os.Bundle)
	 */
	protected void onSaveInstanceState(Bundle outState) {
		outState.putString("tab", mTabHost.getCurrentTabTag()); // save the tab
		// selected
		super.onSaveInstanceState(outState);
	}

	/**
	 * Initialise ViewPager
	 */
	private void intialiseViewPager() {

		List<Fragment> fragments = new Vector<Fragment>();

		fragments.add(Fragment.instantiate(this, TabSuites.class.getName()));
		fragments.add(Fragment.instantiate(this, TabOffer.class.getName()));
		fragments.add(Fragment.instantiate(this, TabKarim.class.getName()));
		fragments.add(Fragment.instantiate(this, TabKarim.class.getName()));
		fragments.add(Fragment.instantiate(this, TabBook.class.getName()));

		this.mPagerAdapter = new PagerAdapter(
				super.getSupportFragmentManager(), fragments);
		//
		this.mViewPager = (ViewPager) super.findViewById(R.id.viewpager);
		this.mViewPager.setAdapter(this.mPagerAdapter);
		this.mViewPager.setOnPageChangeListener(this);

		mTabHost.setCurrentTab(1);
		mViewPager.setCurrentItem(1);
		

	}

	/**
	 * Initialise the Tab Host
	 */
	private void initialiseTabHost(Bundle args) {
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);



		mTabHost.setup();


		Resources res = getResources(); // Resource object to get Drawables



		mTabHost.getTabWidget().setStripEnabled(true);

		mTabHost.getTabWidget().setRightStripDrawable(R.drawable.line);
		mTabHost.getTabWidget().setLeftStripDrawable(R.drawable.line);


		TabInfo tabInfo = null;


		RootActivity.AddTab(this, this.mTabHost,
				this.mTabHost.newTabSpec("Tab1").setIndicator("SUITES"),
				(tabInfo = new TabInfo("Tab1", TabSuites.class, args)));



		this.mapTabInfo.put(tabInfo.tag, tabInfo);
		RootActivity.AddTab(this, this.mTabHost,
				this.mTabHost.newTabSpec("Tab2").setIndicator("OFFER"),
				(tabInfo = new TabInfo("Tab2", TabOffer.class, args)));


		this.mapTabInfo.put(tabInfo.tag, tabInfo);
		RootActivity.AddTab(this, this.mTabHost,
				RootActivity.mTabHost.newTabSpec("Tab3").setIndicator(""),
				(tabInfo = new TabInfo("Tab3", TabKarim.class, args)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);

		RootActivity.AddTab(this, this.mTabHost,
				RootActivity.mTabHost.newTabSpec("Tab4").setIndicator("KARIM"),
				(tabInfo = new TabInfo("Tab4", TabKarim.class, args)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);

		RootActivity.AddTab(this, this.mTabHost,
				RootActivity.mTabHost.newTabSpec("Ta5").setIndicator("BOOK"),
				(tabInfo = new TabInfo("Tab5", TabBook.class, args)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);








		TabWidget tw = (TabWidget)mTabHost.findViewById(android.R.id.tabs);
		View tabView = tw.getChildTabViewAt(0);
		TextView tv = (TextView)tabView.findViewById(android.R.id.title);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.tab_font));

		tv.setTextColor(Color.rgb(51, 43, 21));
		//tv.setBackgroundResource(R.drawable.txt_suites);



		View tabView1 = tw.getChildTabViewAt(1);
		TextView suites = (TextView)tabView1.findViewById(android.R.id.title);
		suites.setTextColor(Color.rgb(51, 43, 21));
		suites.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.tab_font));
		//suites.setBackgroundResource(R.drawable.offer);


		View tabView2 = tw.getChildTabViewAt(2);
		tabView2.setBackgroundResource(R.drawable.jaa);

		View tabView3 = tw.getChildTabViewAt(3);
		TextView karim = (TextView)tabView3.findViewById(android.R.id.title);
		karim.setTextColor(Color.rgb(51, 43, 21));
		//karim.setBackgroundResource(R.drawable.txt_karim);
		karim.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.tab_font));
		View tabView4 = tw.getChildTabViewAt(4);
		TextView book = (TextView)tabView4.findViewById(android.R.id.title);
		book.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.tab_font));
		book.setTextColor(Color.rgb(51, 43, 21));



		mTabHost.getTabWidget().getChildAt(0).getLayoutParams().height = 100;
		mTabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.rgb(204, 169, 55));
		mTabHost.getTabWidget().getChildAt(1).getLayoutParams().height = 100;

		mTabHost.getTabWidget().getChildAt(2).getLayoutParams().height = 50;
		mTabHost.getTabWidget().getChildAt(3).getLayoutParams().height = 100;
		mTabHost.getTabWidget().getChildAt(4).getLayoutParams().height = 100;

		mTabHost.getTabWidget().getChildAt(0).getLayoutParams().width = 10;
		//		mTabHost.getTabWidget().getChildAt(1).getLayoutParams().width = 0;
		//		mTabHost.getTabWidget().getChildAt(2).getLayoutParams().width = 20;
		//		mTabHost.getTabWidget().getChildAt(3).getLayoutParams().width = 0;
		//		mTabHost.getTabWidget().getChildAt(4).getLayoutParams().width = 0;

		//mTabHost.getTabWidget().getChildAt(5).getLayoutParams().width = 160;


		mTabHost.getTabWidget().getChildAt(1)
		.setBackgroundResource(R.drawable.tabselector);
		mTabHost.setOnTabChangedListener(this);
	}

	/**
	 * Add Tab content to the Tabhost
	 * 
	 * @param activity
	 * @param tabHost
	 * @param tabSpec
	 * @param clss
	 * @param args
	 */
	private static void AddTab(RootActivity activity, TabHost tabHost,
			TabHost.TabSpec tabSpec, TabInfo tabInfo) {
		// Attach a Tab view factory to the spec
		tabSpec.setContent(activity.new TabFactory(activity));

		tabHost.addTab(tabSpec);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see android.widget.TabHost.OnTabChangeListener#onTabChanged(java.lang.String)
	 */
	public void onTabChanged(String tag) {
		// TabInfo newTab = this.mapTabInfo.get(tag);
		progress.setVisibility(View.VISIBLE);
		int pos = this.mTabHost.getCurrentTab();
		if(pos!=2){
			mTabHost.getTabWidget().getChildAt(pos)
			.setBackgroundResource(R.drawable.tabselector);
			this.mViewPager.setCurrentItem(pos);
			
		}
		progress.setVisibility(View.GONE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.view.ViewPager.OnPageChangeListener#onPageScrolled
	 * (int, float, int)
	 */
	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.view.ViewPager.OnPageChangeListener#onPageSelected
	 * (int)
	 */
	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		//	if(position==2)
		//	position=3;

		this.mTabHost.setCurrentTab(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.view.ViewPager.OnPageChangeListener#
	 * onPageScrollStateChanged(int)
	 */
	@Override
	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub

	}
}
