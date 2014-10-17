package com.mattallen.notificationtoastexample;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Encapsulates all of the information needed to present the view on the window of the given context
 *
 * @author Matt Allen
 * @project Blood
 */
public class NotificationToast
{
	public static final long LENGTH_SHORT = 1500;
	public static final long LENGTH_LONG = 3500;

	private Context mContext;
	private String mMessage, mTitle;
	private Bitmap mImage;
	private int mColor;
	private long mDuration;

	public NotificationToast(Context context, String message, String title, Bitmap image, int color, long duration)
	{
		this.mContext = context;
		this.mMessage = message;
		this.mTitle = title;
		this.mImage = image;
		this.mColor = color;
		this.mDuration = duration;
	}

	public static NotificationToast makeToast(Context context, String message, String title, Bitmap image, int color, long duration)
	{
		return new NotificationToast(context, message, title, image, color, duration);
	}

	public Context getContext()
	{
		return mContext;
	}

	public String getMessage()
	{
		return mMessage;
	}

	public String getTitle()
	{
		return mTitle;
	}

	public Bitmap getImage()
	{
		return mImage;
	}

	public int getColor()
	{
		return mColor;
	}

	public long getDuration()
	{
		return mDuration;
	}

	public void show()
	{
		NotificationToastManager.getInstance().addNotificationToast(this);
	}
}
