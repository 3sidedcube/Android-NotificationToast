package com.mattallen.notificationtoastexample;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewPropertyAnimator;
import android.widget.LinearLayout;

/**
 * A small view to be displayed at the top of the window and will pop down and swipe away after a
 * customizable amount of time or when the user swipes horizontally.
 *
 * @author Matt Allen
 * @project Blood
 */
public class NotificationToastView extends LinearLayout
{
	private OnNotificationToastEnd mCallback;
	private AnimatorListener mListener;
	private long mDuration;

	public NotificationToastView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public void setCallback(OnNotificationToastEnd callback)
	{
		mCallback = callback;
		final NotificationToastView view = this;

		mListener = new AnimatorListener()
		{

			@Override public void onAnimationStart(Animator animation){}
			@Override public void onAnimationEnd(Animator animation)
			{
				mCallback.onNotificationToastEnd();
			}

			@Override public void onAnimationCancel(Animator animation)
			{
				mCallback.onNotificationToastEnd();
			}
			@Override public void onAnimationRepeat(Animator animation){}
		};
	}

	public void setDuration(long duration)
	{
		mDuration = duration;
	}

	@Override
	public void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);

		// Set the animation for when the view has finished
		ViewPropertyAnimator animator = animate();
		animator.setStartDelay(mDuration);
		animator.setListener(mListener);
		animator.setDuration(500);
		animator.alpha(0.5f);
		animator.translationYBy(-getHeight());
		animator.start();
	}
}
