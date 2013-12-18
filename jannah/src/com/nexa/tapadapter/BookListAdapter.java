package com.nexa.tapadapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.nexa.jannah.CheckAvailable;
import com.nexa.jannah.R;
import com.nexa.jannah.SuitesDiscover;
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

public class BookListAdapter extends BaseAdapter {

	private ArrayList listData;
	private HashMap hmOfferHead;

	private LayoutInflater layoutInflater;
	private Context mContext;
	
	View v;

	public BookListAdapter(Context context, ArrayList listData,HashMap hmOfferHead) {
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
			convertView = layoutInflater.inflate(R.layout.booklist, null);
			holder = new ViewHolder();
			holder.headlineView = (TextView) convertView.findViewById(R.id.txt_suitesDetials);
			
			holder.imgRoom = (ImageView) convertView.findViewById(R.id.bookSnap);
						convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		
		HashMap hmImage=new HashMap();
	
		
		hmImage.put(0, R.drawable.book1);
		hmImage.put(1, R.drawable.book2);
		hmImage.put(2, R.drawable.book3);
		hmImage.put(3, R.drawable.book4);
		
	
		
		
	
		
		
		holder.headlineView.setText(listData.get(position).toString());
		holder.imgRoom.setBackgroundResource(Integer.parseInt(hmImage.get(position).toString()));
		
		
		
		holder.btnDetails=(ImageButton)convertView.findViewById(R.id.btn_bookDiscover);
		holder.btnBook=(ImageButton)convertView.findViewById(R.id.btn_bookCheck);
		holder.offerHead=(TextView)convertView.findViewById(R.id.suiteshead);
		
		holder.offerHead.setText(hmOfferHead.get(position)+"");
		
		v = layoutInflater.inflate(R.layout.tab_book, null);
		
	
		holder.btnDetails.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//	Intent intent=new Intent(mContext,SuitesDiscover.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					//mContext.startActivity(intent);
				Toast.makeText(mContext, "Comming Soon...", Toast.LENGTH_SHORT).show();
				
				
			}
		});
		
	       holder.btnBook.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(mContext, "add"+holder.offerHead.getText(), Toast.LENGTH_SHORT).show();
					Intent intent=new Intent(mContext,WebViewtemp.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					
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

