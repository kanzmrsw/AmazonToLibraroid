package com.kanzmrsw.example.amazontolibraroid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {
	String url = "";
	String isbn = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intentReceive = getIntent();

		if (intentReceive == null) {
			finish();
		}

		url = intentReceive.getDataString();

		Pattern pat = null;
		Matcher mat;

		pat = Pattern.compile("\\d{9}[\\d|X]");
		mat = pat.matcher(url);
		if (mat.find()) {
			isbn = mat.group(0);
		} else {
			finish();
		}

		Intent intSend = new Intent();
		intSend.setAction(Intent.ACTION_SEARCH);
		intSend.putExtra("query", isbn);
		intSend.setPackage("yanzm.products.libraroid");
		startActivity(intSend);
	}
}
