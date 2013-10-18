package cn.eoe.app.widget;

import cn.eoe.app.R;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TitleBar extends FrameLayout {
	
	TextView mTitle = null;
	
	public TitleBar(Context context, AttributeSet set)
	{
		super(context,set);
		LayoutInflater inflat = LayoutInflater.from(context);
		
		View target = inflat.inflate(R.layout.details_title, null);
		
		
		
		ImageView bar = (ImageView) target.findViewById(R.id.details_imageview_gohome);
		
		TextView title = (TextView) target.findViewById(R.id.details_imageview_head);
		
		
		final Context innContext = context;
		
		bar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(innContext != null && innContext instanceof Activity)
				{
					
					Activity targetActivity = (Activity) innContext;
					
					if(!targetActivity.isFinishing())
					{
						targetActivity.finish();
					}
				}
			}
		});
		
		this.addView(target);
	}

	public TitleBar(Context context) {
		super(context, null);
						
	
	}
	
	Handler uiHandler = new Handler(Looper.getMainLooper());
	
	public void setTitle(String title)
	{
		if(mTitle != null && title != null)
		{
			if(Thread.currentThread() == Looper.getMainLooper().getThread())
			{
				mTitle.setText(title);
			}
			else
			{
				final String innTitle = title;
				
				uiHandler.post(new Runnable() {
					
					@Override
					public void run() {
						mTitle.setText(innTitle);
					}
				});
			}
		}
	}

}
