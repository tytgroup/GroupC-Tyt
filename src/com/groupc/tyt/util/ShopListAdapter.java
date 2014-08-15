package com.groupc.tyt.util;
/*
 * describle:��List<shop>��listview��������
 * author:��ΰ
 * */
import java.util.List;

import com.groupc.tyt.R;
import com.groupc.tyt.util.AsyncImageLoader.ImageCallback;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ShopListAdapter extends BaseAdapter {
	private Context context;// ����������
	private List<Shop> listItems;// ���ݼ���
	private LayoutInflater listContainer;// ��ͼ����
	private ListView listView;
	private AsyncImageLoader asyncImageLoader;//�첽��������������
	static class ListItemView { // �Զ���ؼ�����
		public ImageView image;
		public TextView addr;
		public TextView name;
	}

	/**
	 * ʵ����Adapter
	 */
	public ShopListAdapter(Context context, List<Shop> ShopList,
			ListView ShopListview) {
		this.context = context;
		this.listContainer = LayoutInflater.from(context); // ������ͼ����������������
		this.listView = ShopListview;
		this.listItems = ShopList;
		asyncImageLoader = new AsyncImageLoader();
	}

	public int getCount() {
		return listItems.size();
	}

	public Shop getItem(int position) {
		return listItems.get(position);
	}
	
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		 return 0;
	}

	/**
	 * ListView Item����
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		ListItemView listItemView = null;

		if (convertView == null) {
			convertView = listContainer.inflate(
					R.layout.list_row, null);
			listItemView = new ListItemView();
			listItemView.image = (ImageView) convertView
					.findViewById(R.id.list_image);
			listItemView.name = (TextView) convertView
					.findViewById(R.id.gdname);
			listItemView.addr = (TextView) convertView
					.findViewById(R.id.gdprice);
			convertView.setTag(listItemView);
		} else {
			listItemView = (ListItemView) convertView.getTag();
		}
		Shop Shop = listItems.get(position);
		String name = Shop.getName();
		listItemView.name.setText(name);
		listItemView.addr.setText(Shop.getStreet_address());
		listItemView.image.setTag(Shop.getThumbnail());
		Drawable cachedImage = null;
		if (Shop.getThumbnail() != null) {
			cachedImage = asyncImageLoader.loadDrawable(context,Shop.getThumbnail(),
					new ImageCallback() {//���سɹ���ص�
						public void imageLoaded(Drawable imageDrawable,
								String image) {
							ImageView imageViewByTag = (ImageView) listView
									.findViewWithTag(image);
							if (imageViewByTag != null && imageDrawable != null) {
								imageViewByTag.setImageDrawable(imageDrawable);
							}
						}
					});
		}
		if (cachedImage == null) {
			listItemView.image.setImageResource(R.drawable.ic_launcher);
		} else {
			listItemView.image.setImageDrawable(cachedImage);
		}
		return convertView;
	}

	// ���¼�������
	public void refreshData(List<Shop> listItems) {
		this.listItems = listItems;
		notifyDataSetChanged();
	}
}
