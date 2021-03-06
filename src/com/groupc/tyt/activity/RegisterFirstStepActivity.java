package com.groupc.tyt.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.groupc.tyt.R;
import com.groupc.tyt.constant.User;

public class RegisterFirstStepActivity extends Activity{

	private String uno,name,psd,phone;
	private EditText stunum;
	private EditText usrname;
	private EditText usrpsw;
	private EditText usrpsw2;
	private EditText usrphone;
	private Button btn_nextstep;
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();

		SpannableString spannableString = new SpannableString("登录");
		getActionBar().setTitle(spannableString);
        actionBar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.register_firststep_activity); 
		stunum = (EditText)findViewById(R.id.stunum);
		usrname = (EditText)findViewById(R.id.usrname);
		usrpsw = (EditText)findViewById(R.id.usrpsw);
		usrpsw2 = (EditText)findViewById(R.id.usrpsw2);
		usrphone = (EditText)findViewById(R.id.usrphone);
		btn_nextstep= (Button)findViewById(R.id.btn_nextstep);
		
		btn_nextstep.setOnClickListener(new Button.OnClickListener(){//创建监听  
            public void onClick(View v) {    
            	uno=stunum.getText().toString();
            	name = usrname.getText().toString();
            	psd = usrpsw.getText().toString();
            	phone = usrphone.getText().toString();
            	if (registerIsSuccess()) {
            		User.name=name;
            		User.uno=uno;
            		User.passWord=psd;
            		User.phone=phone;
            		startActivity(new Intent(RegisterFirstStepActivity.this,RegisterSecondStepActivity.class));
            		finish();
            }    
            }
        });   
	}
	
	private boolean registerIsSuccess(){
		
    	//获取用户输入的信息
    	
    	String password1=usrpsw.getText().toString();
    	String password2=usrpsw2.getText().toString();
    	String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。  
        
    	if("".equals(uno)){
    		Toast.makeText(RegisterFirstStepActivity.this, "请输入学号!", Toast.LENGTH_SHORT).show();
    		return false;
    	}else if("".equals(name)){
        		//用户输入用户名为空
        		Toast.makeText(RegisterFirstStepActivity.this, "用户名不能为空!", Toast.LENGTH_SHORT).show();
        		return false;
    	}else if("".equals(password1)){
    		//密码不能为空
    		Toast.makeText(RegisterFirstStepActivity.this, "密码不能为空!", Toast.LENGTH_SHORT).show();
    		return false;
    	}else if(!password1.equals(password2)){
    		Toast.makeText(RegisterFirstStepActivity.this, "两次密码输入不一致！", Toast.LENGTH_SHORT).show();
    		return false;
    	}else if(!phone.matches(telRegex)){
    		Toast.makeText(RegisterFirstStepActivity.this, "请输入正确手机号!", Toast.LENGTH_SHORT).show();
    		return false;
    	}
    	return true;
    }
	
	public boolean onOptionsItemSelected(MenuItem item) {  
	    switch (item.getItemId()) {  
	        case android.R.id.home:  
	        	  finish();
	        default:  
	            return super.onOptionsItemSelected(item);  
	    }  
	}
	

}
