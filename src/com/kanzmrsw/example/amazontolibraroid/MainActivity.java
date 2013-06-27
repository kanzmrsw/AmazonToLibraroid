package com.kanzmrsw.example.amazontolibraroid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	String url = "";
	String isbn = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView tvUrl = (TextView) findViewById(R.id.tvUrl);
		TextView tvIsbn = (TextView) findViewById(R.id.tvIsbn);

		url = getIntent().getDataString();
		
		Pattern pat = null;
		Matcher mat;
		
		pat	= Pattern.compile("\\d{9}[\\d|X]");
		mat = pat.matcher(url);
		if (mat.find()) {
			isbn = mat.group(0);
		}

		tvUrl.setText(url);
		tvIsbn.setText(isbn);

		Button btnSend = (Button) findViewById(R.id.btnSend);
		btnSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View paramView) {
				Intent intSend = new Intent();
				intSend.setAction(Intent.ACTION_SEARCH);
				intSend.putExtra("query", isbn);
				intSend.setPackage("yanzm.products.libraroid");
				startActivity(intSend);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
