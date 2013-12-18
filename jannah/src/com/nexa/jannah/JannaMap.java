package com.nexa.jannah;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.Toast;



import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.nexa.jannah.R;

@SuppressLint("NewApi")
public class JannaMap extends Activity  {
	
	private LatLng LOCATION_SURRREY= new LatLng(24.446133825424848, 54.43875789642334);

	private GoogleMap map;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.googlemap);
		
		final ActionBar ab = getActionBar();
		ab.setDisplayShowHomeEnabled(true);
		ab.setDisplayShowTitleEnabled(false);     
		final LayoutInflater inflater = (LayoutInflater)getSystemService("layout_inflater");
		View view = inflater.inflate(R.layout.top_backlayout,null); 
		ab.setCustomView(view);
		ab.setDisplayShowCustomEnabled(true);

		ab.setDisplayShowHomeEnabled(false);
		//getActionBar().setIcon(R.drawable.jannha);
	
		Drawable d=getResources().getDrawable(R.drawable.inner_head);  
		getActionBar().setBackgroundDrawable(d);
		
		
		ImageButton btnback=(ImageButton)findViewById(R.id.btn_topback);
		
		btnback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		map  = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

		Marker melbourne = map.addMarker(new MarkerOptions().position(LOCATION_SURRREY).draggable(true)
				.snippet("HELLO").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));


		LatLng position = melbourne.getPosition(); //
		//map.addMarker(new MarkerOptions().position(LOCATION_SURRREY).title("Find me here!"));

		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(LOCATION_SURRREY, 16);
		map.animateCamera(update);


		map.setOnMarkerDragListener(new OnMarkerDragListener() {

			public void onMarkerDragStart(Marker marker) {
				// TODO Auto-generated method stub
				// Here your code
				Toast.makeText(JannaMap.this, "Dragging Start",
						Toast.LENGTH_SHORT).show();
			}

			public void onMarkerDragEnd(Marker marker) {
				// TODO Auto-generated method stub
				LatLng position = marker.getPosition(); //
				Toast.makeText(
						JannaMap.this,
				                "Lat " + position.latitude + " "
				                        + "\nLong " + position.longitude,
				                Toast.LENGTH_LONG).show();
			}

			public void onMarkerDrag(Marker marker) {
				// TODO Auto-generated method stub
			
				System.out.println("Draagging");
			}
		});



	}




}
