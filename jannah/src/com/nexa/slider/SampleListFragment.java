package com.nexa.slider;


import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nexa.jannah.R;
import com.nexa.jannah.RootActivity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SampleListFragment extends ListFragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.menu_list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		SampleAdapter adapter = new SampleAdapter(getActivity());
		//for (int i = 0; i < 5; i++) {
		//adapter.add(new SampleItem("", R.drawable.search,1));
		adapter.add(new SampleItem("SUITES", R.drawable.suites,0));
		adapter.add(new SampleItem("KARIM", R.drawable.karim,0));
		adapter.add(new SampleItem("NEWS", R.drawable.news,0));
		adapter.add(new SampleItem("DESTINATION", R.drawable.destination,0));
		adapter.add(new SampleItem("OFFERS", R.drawable.offers,0));
		adapter.add(new SampleItem("EXPERIENCE", R.drawable.experience,0));
		adapter.add(new SampleItem("GALLERY", R.drawable.gallery,0));
		adapter.add(new SampleItem("BOOKING", R.drawable.booking,0));
		
		//}
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {
		//SlidingMenu sm = getSlidingMenu();
		Fragment newContent = null;
		switch (position) {
		case 0:

			//Intent in = new Intent(getActivity().getApplicationContext(), NexaMainPage.class);
			//startActivity(in);
			switchFragment(0);

			//Toast.makeText(, text, duration)
			break;
	

		case 1:
			//Toast.makeText(getActivity().getApplicationContext(), "Coming Soon...", Toast.LENGTH_SHORT).show();
			switchFragment(1);
			break;

		case 2:
			Toast.makeText(getActivity().getApplicationContext(), "Coming Soon...", Toast.LENGTH_SHORT).show();
			switchFragment(2);
			break;
		case 3:
			Toast.makeText(getActivity().getApplicationContext(), "Coming Soon...", Toast.LENGTH_SHORT).show();
			switchFragment(3);
			break;
		case 4:
			Toast.makeText(getActivity().getApplicationContext(), "Coming Soon...", Toast.LENGTH_SHORT).show();
			switchFragment(4);
			break;
		case 5:
			Toast.makeText(getActivity().getApplicationContext(), "Coming Soon...", Toast.LENGTH_SHORT).show();
			switchFragment(5);
			break;
		case 6:
			Toast.makeText(getActivity().getApplicationContext(), "Coming Soon...", Toast.LENGTH_SHORT).show();
			switchFragment(6);
			break;
		case 7:
			Toast.makeText(getActivity().getApplicationContext(), "Coming Soon...", Toast.LENGTH_SHORT).show();
			switchFragment(7);
			break;

		}
	}
	// the meat of switching the above fragment
	private void switchFragment(int i) {
		if (getActivity() == null)
			return;

		if (getActivity() instanceof RootActivity) {
			RootActivity fca = (RootActivity) getActivity();
			fca.switchContent(i);
		}



	}
	private class SampleItem {

		public String tag;
		public int iconRes;
		public int imageFlag;

		public SampleItem(String tag, int iconRes,int imageFlag) {
			this.tag = tag; 
			this.iconRes = iconRes;
			this.imageFlag=imageFlag;
		}
	}

	public class SampleAdapter extends ArrayAdapter<SampleItem> {

		public SampleAdapter(Context context) {
			super(context, 0);
		}

		@SuppressLint("NewApi")
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_row, null);
			}
			ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
			ImageView header = (ImageView) convertView.findViewById(R.id.header);
			LinearLayout li=(LinearLayout)convertView.findViewById(R.id.slideLayout);
			icon.setImageResource(getItem(position).iconRes);

			if(getItem(position).imageFlag==1){
				header.setVisibility(View.VISIBLE);
				//Drawable background=getResources().getDrawable(R.drawable.search);  
				//li.setBackground(background);
				//li.setBackgroundColor(Color.parseColor("#ffff00"));
			}
			else{
				header.setVisibility(View.GONE);
				icon.setVisibility(View.VISIBLE);
			}
			TextView title = (TextView) convertView.findViewById(R.id.row_title);
			title.setText(getItem(position).tag);

			return convertView;
		}

	}
}
