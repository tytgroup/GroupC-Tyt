package com.groupc.tyt.activity;


import com.groupc.tyt.R;
import com.groupc.tyt.util.HttpUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.TypefaceSpan;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class SplashActivity2 extends Activity {
	
	MyHandler myHandler;//��Ϣ�����Handler����
	String jsonstring;//����װ���������ص�json���������ݵ���һ��activity
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		Resources r = getResources();
		Drawable myDrawable = r.getDrawable(R.drawable.top_back);
		actionBar.setBackgroundDrawable(myDrawable);
		SpannableString spannableString = new SpannableString("������");
		spannableString.setSpan(new TypefaceSpan("monospace"), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		spannableString.setSpan(new AbsoluteSizeSpan(24, true), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		getActionBar().setTitle(spannableString);
	    actionBar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.splash);	
		  myHandler = new MyHandler();//��Ϣ�������
		  MyThread m = new MyThread();//json�������ض���
	       new Thread(m).start();//��ʼ���أ�UI�����������progress
	}

	
    private String GetSource() throws Exception{
	return HttpUtil.getRequest(HttpUtil.BaseUrl);
    }
    
    /**
     * �ڲ�Handler��
     * ������Ϣ,������Ϣ ,��Handler���뵱ǰ���߳�һ������
     * */

@SuppressLint("HandlerLeak")
class MyHandler extends Handler {
         @Override
         public void handleMessage(Message msg) {
             super.handleMessage(msg);
             Bundle b = msg.getData();
             String status = b.getString("status");
             if(status=="ok"){//�����ȷ��ȡ����
			 Intent intent=new Intent(SplashActivity2.this,Published_Activity.class);
			 intent.putExtra("jsonstring", jsonstring);//���ݵ�MainActivityҳ��
			 startActivity(intent);
			 SplashActivity2.this.finish();
             }
         }
     }
/*
 * ���ڲ��߳�����������json
 * ������������̷߳�����Ϣ
 * */
    class MyThread implements Runnable {
        public void run() {    
                try {
                	Thread.sleep(1000);
                	jsonstring= GetSource();
				} catch (Exception e) {
					e.printStackTrace();
				}
                Message msg = new Message();
                Bundle b = new Bundle();// �������
                b.putString("status","ok");
                msg.setData(b);
                SplashActivity2.this.myHandler.sendMessage(msg); // ��Handler������Ϣ,����UI
        }
    }
}
