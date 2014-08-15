package com.groupc.tyt.util;
/*
 * abstract :����ͼƬ�첽������
 * author:��ΰ
 * */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;

public class AsyncImageLoader {
	private HashMap<String, SoftReference<Drawable>> imageCache;
	private BlockingQueue<Runnable> queue;
	private ThreadPoolExecutor executor;

	public AsyncImageLoader() {
		imageCache = new HashMap<String, SoftReference<Drawable>>();

		queue = new LinkedBlockingQueue<Runnable>();
		executor = new ThreadPoolExecutor(1, 50, 180, TimeUnit.SECONDS, queue);
	}

	public Drawable loadDrawable(final Context context, final String url,
			final ImageCallback imageCallback) {
		if (imageCache.containsKey(url)) {
			SoftReference<Drawable> softReference = imageCache.get(url);
			Drawable drawable = softReference.get();
			if (drawable != null) {
				return drawable;
			}
		}
		final Handler handler = new Handler() {
			public void handleMessage(Message message) {
				imageCallback.imageLoaded((Drawable) message.obj,
						url.substring(url.lastIndexOf("/") + 1));
			}
		};

		// ���̳߳���������ͼƬ������
		executor.execute(new Runnable() {
			
			public void run() {
				Drawable drawable = loadImageFromUrl(context, url);
				imageCache.put(url, new SoftReference<Drawable>(drawable));
				Message message = handler.obtainMessage(0, drawable);
				handler.sendMessage(message);
			}
		});

		return null;
	}

	// ����ͼƬ�����ص�����cacheĿ¼���棬��imagUrl��ͼƬ�ļ������档�����ͬ���ļ���cacheĿ¼�ʹӱ��ؼ���
	public static Drawable loadImageFromUrl(Context context, String imageUrl) {
		Drawable drawable = null;
		if (imageUrl == null)
			return null;
		String fileName = "";

		if (imageUrl != null && imageUrl.length() != 0) {
			fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
		}

		File file = new File(context.getCacheDir(), fileName);// �����ļ�
		if (!file.exists() && !file.isDirectory()) {
			try {
				FileOutputStream fos = new FileOutputStream(file);
				InputStream is = new URL(imageUrl).openStream();
				int data = is.read();
				while (data != -1) {
					fos.write(data);
					data = is.read();
				}
				fos.close();
				is.close();
				drawable = Drawable.createFromPath(file.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			drawable = Drawable.createFromPath(file.toString());
		}
		return drawable;
	}

	public interface ImageCallback {
		public void imageLoaded(Drawable imageDrawable, String image);
	}

}