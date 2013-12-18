package com.nexa.tapadapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.nexa.jannah.CheckAvailable;

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

public class SuitesListAdapter extends BaseAdapter {

	private ArrayList listData;
	private HashMap hmOfferHead;

	private LayoutInflater layoutInflater;
	private Context mContext;
	
	View v;

	public SuitesListAdapter(Context context, ArrayList listData,HashMap hmOfferHead) {
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
		final ViewHolder holder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.suiteslist, null);
			holder = new ViewHolder();
			holder.headlineView = (TextView) convertView.findViewById(R.id.txt_suitesDetials);
			
			holder.imgRoom = (ImageView) convertView.findViewById(R.id.suitesSnap);
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
		
		
		
	
		
		
		holder.headlineView.setText(listData.get(position).toString());
		holder.imgRoom.setBackgroundResource(Integer.parseInt(hmRate.get(position).toString()));
		
		
		
		holder.btnDetails=(ImageButton)convertView.findViewById(R.id.btn_suitesDiscover);
		holder.btnCheck=(ImageButton)convertView.findViewById(R.id.btn_suitesCheck);
		holder.offerHead=(TextView)convertView.findViewById(R.id.suiteshead);
		
		holder.offerHead.setText(hmOfferHead.get(position)+"");
		
		v = layoutInflater.inflate(R.layout.tab_suites, null);
		
	
		holder.btnDetails.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(mContext, "add"+holder.offerHead.getText(), Toast.LENGTH_SHORT).show();
					Intent intent=new Intent(mContext,SuitesDiscover.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.putExtra("caption", holder.offerHead.getText());
					intent.putExtra("details", holder.headlineView.getText());
					mContext.startActivity(intent);
			}
		});
		
		holder.btnCheck.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(mContext, "add"+holder.offerHead.getText(), Toast.LENGTH_SHORT).show();
					Intent intent=new Intent(mContext,CheckAvailable.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					mContext.startActivity(intent);
			}
		});
		
		
		
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
		ImageButton btnCheck;
	}
}

