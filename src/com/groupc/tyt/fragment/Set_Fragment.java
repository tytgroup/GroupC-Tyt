package com.groupc.tyt.fragment;

import java.util.ArrayList;
import java.util.List;

import com.groupc.tyt.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Set_Fragment extends Fragment {
	private ListView listView;
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.me,container,false);
		listView = (ListView) v.findViewById(R.id.listView1);
		init();
		return v;
	}
	
	
	private void init() {
		List<String> items = new ArrayList<String>();
		items.add("������Ľ���");
		items.add("�ѷ����Ľ���");
		items.add("ʮ�������");
		items.add("�������");
		items.add("���и��õĽ��飨�����ͻ���)");
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, items);
		listView.setAdapter(adapter);
}
}