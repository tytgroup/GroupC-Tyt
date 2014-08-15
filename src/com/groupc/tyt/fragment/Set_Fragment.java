package com.groupc.tyt.fragment;

import java.util.ArrayList;
import java.util.List;

import com.groupc.tyt.R;
import com.groupc.tyt.activity.Applied_Activity;
import com.groupc.tyt.activity.SplashActivity;
import com.groupc.tyt.activity.SplashActivity2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Set_Fragment extends Fragment {
	private ListView listView;
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.me,container,false);
		listView = (ListView) v.findViewById(R.id.listviewme);
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
		listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {
                    switch(position){
                    case 0:
                            Intent localIntent=new Intent(getActivity(),SplashActivity.class);
                            startActivity(localIntent);
                    case 1:
                    	Intent localIntent2=new Intent(getActivity(),SplashActivity2.class);
                        startActivity(localIntent2);
                    case 2:
                    	Toast.makeText(getActivity(), "����������3", Toast.LENGTH_SHORT).show();
                    case 3:
                    	Toast.makeText(getActivity(), "����������4", Toast.LENGTH_SHORT).show();
                    case 4:
                    	Toast.makeText(getActivity(), "����������5", Toast.LENGTH_SHORT).show();
                    }
                    
            }
    });
}
}