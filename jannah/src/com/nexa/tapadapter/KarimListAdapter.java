package com.nexa.tapadapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.nexa.jannah.R;
import com.nexa.jannah.SuitesDiscover;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class KarimListAdapter extends BaseAdapter {

	private ArrayList listData;
	private HashMap hmOfferHead;

	private LayoutInflater layoutInflater;
	private Context mContext;
	
	View v;

	public KarimListAdapter(Context context, ArrayList listData,HashMap hmOfferHead) {
		mContext=context;
		this.listData = listData;
		this.hmOfferHead=hmOfferHead;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position,  View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.karimfeaturesist, null);
			holder = new ViewHolder();
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		
		HashMap hmRate=new HashMap();
	
		
		hmRate.put(0, R.drawable.suites1);
		hmRate.put(1, R.drawable.suites2);
		hmRate.put(2, R.drawable.suites3);
		hmRate.put(3, R.drawable.suites4);

		hmRate.put(4, R.drawable.jaanha_1);
		hmRate.put(5, R.drawable.jaanha_1);
		

		holder.offerHead=(TextView)convertView.findViewById(R.id.suiteshead);
		
		holder.offerHead.setText(listData.get(position)+"");

		return convertView;
	}
	

	static class ViewHolder {
		TextView headlineView;
		TextView offerHead;
		TextView reportedDateView;
		ImageView imgRate;
		ImageView imgRoom;
		ImageButton btnDetails;
		ImageButton btnBook;
	}
}

