package com.mattallen.notificationtoastexample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * A small view to be displayed at the top of the window and will pop down and swipe away after a
 * customizable amount of time or when the user swipes horizontally.
 *
 * @author Matt Allen
 * @project Blood
 */
public class NotificationToastView extends View
{
	private OnNotificationToastEnd mCallback;

	public NotificationToastView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}

	public void setCallback(OnNotificationToastEnd callback)
	{
		mCallback = callback;
	}
}
