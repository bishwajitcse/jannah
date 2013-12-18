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
import com.nexa.tapadapter.BookListAdapter;
import com.nexa.tapadapter.SuitesListAdapter;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;



/**
 * @author jitesh
 *
 */
public class TabBook extends Fragment {
	/** (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
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
			inputStream = assetManager.open("booking.xml");
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
			image_details.add(xml.getValue(e,"name"));
			hmOfferHead.put(i,xml.getValue(e,"head"));

		}



		//image_details.add("Paschim Villa\nA Their Default Model");
		//image_details.add("Karlon Bagh\nMany Desktop publish");

		//ListView lv1 = (ListView)inflater.inflate(R.id.offer_list,container,false);
		//lv1.setAdapter(new OfferListAdapter(getActivity().getApplicationContext(), image_details));
		LinearLayout l= (LinearLayout)inflater.inflate(R.layout.tab_book, container, false);;


		ListView la=(ListView)l.findViewById(R.id.book_list);
		la.setAdapter(new BookListAdapter(getActivity().getApplicationContext(), image_details,hmOfferHead));


	

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