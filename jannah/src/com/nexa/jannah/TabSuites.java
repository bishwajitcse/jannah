package com.nexa.jannah;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.nexa.jannah.xml.XMLParser;
import com.nexa.tapadapter.OfferListAdapter;
import com.nexa.tapadapter.SuitesListAdapter;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import android.widget.Toast;



/**
 * @author jitesh
 *
 */
public class TabSuites extends Fragment {
	/** (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	
	ProgressBar progress;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			// We have different layouts, and in one of them this
			// fragment's containing frame doesn't exist.  The fragment
			// may still be created from its saved state, but there is
			// no reason to try to create its view hierarchy because it
			// won't be displayed.  Note this is not needed -- we could
			// just run the code below, where we would create and return
			// the view hierarchy; it would just never be used.
			return null;
		}



		ArrayList image_details = new ArrayList();



		AssetManager assetManager = getActivity().getApplicationContext().getAssets();
		InputStream inputStream = null;
		try {
			inputStream = assetManager.open("suites.xml");
		} catch (IOException e) {
			Log.i("tag", e.getMessage());
		}

//		String s = readTextFile(inputStream);
//
//		XMLParser xml=new XMLParser();
//		Document doc=xml.getDomElement(s);
//		NodeList nl = doc.getElementsByTagName("item");

		HashMap hmOfferHead=new HashMap();




//		for (int i =0; i<nl.getLength(); i++) {
//			// creating new HashMap
//			HashMap<String, String> map = new HashMap<String, String>();
//			Element e = (Element) nl.item(i);
//			// adding each child node to HashMap key => value
//			image_details.add(xml.getValue(e,"name"));
//			hmOfferHead.put(i,xml.getValue(e,"head"));
//
//		}
		image_details.add("The Deluxe Jannah Suites offer a harmony of elegant design and comfort spread over 56 square meters. Suites are designed in serene tones combining stylish contemporary décor with Arabic influences.");
		image_details.add("From 86 to 137 square meters, the spacious suites are complemented by outdoor living spaces with endless uninterrupted mangrove views, seen through floor to ceiling windows. The one bedroomed suites give you the choice to either have breakfast at your balcony table, or ");
		image_details.add("The two bedroomed Mangroves Suites are elegantly designed to give you the best in comfort and luxury among Abu Dhabi hotels. Our suites echo the majestic natural beauty of the famed mangroves and the views beyond.");
		image_details.add("The Jannah Royal Suite is our signature suite which comes with private driver and personal Karim, pairing the ultimate Jannah luxury with the majestic natural beauty of the mangroves.");


		hmOfferHead.put(0, "DELUXE JANNAH SUITES");
		hmOfferHead.put(1, "ONE BEDROOMED MANGROVES SUITE");
		hmOfferHead.put(2, "TWO BEDROOMED MANGROVES SUITE");
		hmOfferHead.put(3, "JANNAH ROYAL SUITE");

		//image_details.add("Paschim Villa\nA Their Default Model");
		//image_details.add("Karlon Bagh\nMany Desktop publish");

		//ListView lv1 = (ListView)inflater.inflate(R.id.offer_list,container,false);
		//lv1.setAdapter(new OfferListAdapter(getActivity().getApplicationContext(), image_details));
		RelativeLayout l= (RelativeLayout)inflater.inflate(R.layout.tab_suites, container, false);;

		progress=(ProgressBar)l.findViewById(R.id.progressBar);
		progress.setIndeterminate(true);
		progress.setVisibility(View.VISIBLE);
		ListView la=(ListView)l.findViewById(R.id.suites_list);
		la.setAdapter(new SuitesListAdapter(getActivity().getApplicationContext(), image_details,hmOfferHead));

		//progress.
		progress.setVisibility(View.GONE);
	

		return l;
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