package com.mattallen.notificationtoastexample;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;


public class MainActivity extends Activity implements OnClickListener
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.example1).setOnClickListener(this);
		findViewById(R.id.example2).setOnClickListener(this);
		findViewById(R.id.example3).setOnClickListener(this);
	}

	@Override public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.example1:
				NotificationToast.makeToast(this, "Example 1", "Title", BitmapFactory.decodeResource(getResources(), R.drawable.homer), Color.parseColor("#e74c3c"), NotificationToast.LENGTH_SHORT).show();
				break;

			case R.id.example2:
				NotificationToast.makeToast(this, "Example 2", "Title", null, Color.parseColor("#3498db"), NotificationToast.LENGTH_SHORT).show();
				break;

			case R.id.example3:
				NotificationToast.makeToast(this, "Example 3", "Title", null, Color.parseColor("#2ecc71"), NotificationToast.LENGTH_SHORT).show();
				break;
		}
	}
}
