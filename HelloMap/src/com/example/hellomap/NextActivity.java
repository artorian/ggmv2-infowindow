package com.example.hellomap;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class NextActivity extends Activity {

	final int RQS_GooglePlayServices = 1;
	private GoogleMap mMap;
	static final LatLng CDG = new LatLng(13.70302 , 100.543646);
	static final LatLng PANJA = new LatLng(13.696039,100.537638);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_next);

		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		mMap.setMyLocationEnabled(true);
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CDG, 17));
//		
//		 Marker melbourne = mMap.addMarker(new MarkerOptions().position(
//		 MELBOURNE).title("Melbourne").snippet("This is Melbourne City"));
//		 melbourne.showInfoWindow();

		// mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		// mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		// mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

		mMap.getUiSettings().setZoomControlsEnabled(true);
		mMap.getUiSettings().setCompassEnabled(true);
		mMap.getUiSettings().setMyLocationButtonEnabled(true);

		mMap.getUiSettings().setRotateGesturesEnabled(true);
		mMap.getUiSettings().setScrollGesturesEnabled(true);
		mMap.getUiSettings().setTiltGesturesEnabled(true);
		mMap.getUiSettings().setZoomGesturesEnabled(true);
		// or mMap.getUiSettings().setAllGesturesEnabled(true);

		mMap.setTrafficEnabled(true);

		mMap.setInfoWindowAdapter(new MyInfoWindowAdapter());
				
		Marker cdgMarker = mMap.addMarker(new MarkerOptions().position(CDG)
				.snippet(CDG.toString()));
		cdgMarker.setTitle("CDG");
		
		Marker panjaMarker = mMap.addMarker(new MarkerOptions().position(PANJA)
				.snippet(PANJA.toString()));
		panjaMarker.setTitle("PANJATANI");

	}

	@Override
	protected void onResume() {
		super.onResume();

		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(getApplicationContext());

		if (resultCode == ConnectionResult.SUCCESS) {
			Toast.makeText(getApplicationContext(),
					"isGooglePlayServicesAvailable SUCCESS", Toast.LENGTH_LONG)
					.show();
		} else {
			GooglePlayServicesUtil.getErrorDialog(resultCode, this,
					RQS_GooglePlayServices);
		}
	}

	class MyInfoWindowAdapter implements InfoWindowAdapter {

		private final View myContentsView;

		MyInfoWindowAdapter() {
			myContentsView = getLayoutInflater().inflate(
					R.layout.custom_info_windows, null);
		}

		@Override
		public View getInfoContents(Marker marker) {

			TextView tvTitle = ((TextView) myContentsView
					.findViewById(R.id.title));
			tvTitle.setText(marker.getTitle());
			
			TextView tvSnippet = ((TextView) myContentsView
					.findViewById(R.id.snippet));
			tvSnippet.setText(marker.getSnippet());
			
			ImageView imgView = (ImageView) myContentsView
					.findViewById(R.id.images);
			if (tvTitle.getText().toString().equals("CDG")) {
				imgView.setBackgroundResource(R.drawable.cdg);
			} else {
				imgView.setBackgroundResource(R.drawable.panja);
			}
			

			return myContentsView;
		}

		@Override
		public View getInfoWindow(Marker marker) {
			// TODO Auto-generated method stub
			return null;
		}

	}

}
