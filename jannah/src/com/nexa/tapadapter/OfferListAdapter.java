package com.nexa.tapadapter;


import java.util.ArrayList;
import java.util.HashMap;

import com.nexa.jannah.R;
import com.nexa.jannah.WebViewtemp;

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

public class OfferListAdapter extends BaseAdapter {

	private ArrayList listData;
	private HashMap hmOfferHead;

	private LayoutInflater layoutInflater;
	private Context mContext;

	
	
	int[] images = {R.drawable.jaanha_1,R.drawable.jaanha_2,R.drawable.jaanha_3 }; 
	
	public OfferListAdapter(Context context, ArrayList listData,HashMap hmOfferHead) {
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
			convertView = layoutInflater.inflate(R.layout.offerlist, null);
			holder = new ViewHolder();
			holder.headlineView = (TextView) convertView.findViewById(R.id.txt_OfferDetails);
			
			holder.imgRoom = (ImageView) convertView.findViewById(R.id.roomSnap);
						convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		
		HashMap hmRate=new HashMap();
	
		
//		hmRate.put(0, R.drawable.jaanha_1);
//		hmRate.put(1, R.drawable.jaanha_2);
//		hmRate.put(2, R.drawable.jaanha_3);

		
		
	
		
		
		holder.headlineView.setText(listData.get(position).toString());
		if(position==0)
		holder.imgRoom.setImageResource(R.drawable.jaanha_1);
		if(position==1)
			holder.imgRoom.setImageResource(R.drawable.jaanha_2);
		if(position==2)
			holder.imgRoom.setImageResource(R.drawable.jaanha_3);
	
		
		
		holder.btnDetails=(ImageButton)convertView.findViewById(R.id.imageButton1);
		holder.offerHead=(TextView)convertView.findViewById(R.id.tab2head);
		
		holder.offerHead.setText(hmOfferHead.get(position)+"");
		
		holder.btnDetails.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
				//	Toast.makeText(mContext, "Comming Soon...", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(mContext, WebViewtemp.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);;
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
	}

}