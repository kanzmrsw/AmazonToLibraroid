package com.kanzmrsw.product.searchbooksinlibraroid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

public class MainActivity extends Activity {
	String url = "";
	String isbn = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		url = getIntent().getDataString();

		Pattern pat = null;
		Matcher mat;

		pat = Pattern.compile("(97(8|9))?\\d{9}(\\d|X)");
		mat = pat.matcher(url);
		if (mat.find()) {
			isbn = mat.group(0);
		}

		if (!TextUtils.isEmpty(isbn)) {
			Intent intSend = new Intent();
			intSend.setAction(Intent.ACTION_SEARCH);
			intSend.putExtra("query", isbn);
			intSend.setPackage("yanzm.products.libraroid");
			startActivity(intSend);
		} else {
			Toast.makeText(getApplicationContext(), "ISBN is empty or null",
					Toast.LENGTH_SHORT).show();
		}
		finish();
	}
}
