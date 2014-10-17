package com.mattallen.notificationtoastexample;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Manager class for the NotificationToastViews
 *
 * @author Matt Allen
 * @project Blood
 */
public class NotificationToastManager implements OnNotificationToastEnd
{
	/**
	 * If a notification is being showed, add to the queue instead of displaying instantly
	 */
	private boolean showingNotification = false;

	/**
	 * The queue of notifications to be displayed. This will be called in order once the previous
	 * NotificationToast has been displayed
	 */
	private LinkedList<NotificationToast> mToastQueue;

	private static NotificationToastManager mInstance;

	public static NotificationToastManager getInstance()
	{
		if (mInstance == null)
		{
			synchronized (NotificationToastManager.class)
			{
				if (mInstance == null)
				{
					mInstance = new NotificationToastManager();
				}
			}
		}

		return mInstance;
	}

	public void addNotificationToast(NotificationToast toast)
	{
		synchronized (NotificationToastManager.class)
		{
			if (showingNotification)
			{
				getToastQueue().add(toast);
			}
			else
			{
				showNotificationToast(toast);
			}
		}
	}

	/**
	 * Lazy instantiation of the queue
	 * @return The queue of current notifications
	 */
	private LinkedList<NotificationToast> getToastQueue()
	{
		synchronized (NotificationToastManager.class)
		{
			if (mToastQueue == null)
			{
				mToastQueue = new LinkedList<NotificationToast>();
			}

			return mToastQueue;
		}
	}

	private void showNotificationToast(NotificationToast toast)
	{
		synchronized (NotificationToastManager.class)
		{
			showingNotification = true;

			try
			{
				NotificationToastView view = (NotificationToastView) LayoutInflater.from(toast.getContext())
					.inflate(R.layout.notification_toast_view, (ViewGroup)((Activity)toast.getContext()).getWindow().getDecorView(), false);

				view.setDuration(toast.getDuration());
				view.setCallback(this);

				((TextView)view.findViewById(R.id.title)).setText(toast.getTitle());
				((TextView)view.findViewById(R.id.subtitle)).setText(toast.getMessage());
				((ImageView)view.findViewById(R.id.image)).setImageBitmap(toast.getImage());
				view.findViewById(R.id.background).setBackgroundColor(toast.getColor());

				ViewGroup window = (ViewGroup)((Activity)toast.getContext()).getWindow().getDecorView();
				window.addView(view);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onNotificationToastEnd()
	{
		synchronized (NotificationToastManager.class)
		{
			showingNotification = false;
			if (getToastQueue().size() > 0)
			{
				NotificationToast toast;
				do
				{
					toast = getToastQueue().removeFirst();
				}
				while (toast == null);

				showNotificationToast(toast);
			}
		}
	}
}
