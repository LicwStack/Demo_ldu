package com.li.ldu;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;

import com.example.li.R;
import com.li.ldu.model.User;
import com.li.ldu.util.Util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {
	
	private Button btnLogin;
	private TextView tvReg;
	private EditText etUsername;
	private EditText etPassword;

	private String username;
	private String password;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化 Bmob SDK
		Bmob.initialize(this, "6665d013c8384dfe2d9805d9ecfdf3df");
		setContentView(R.layout.activity_login);
		
		btnLogin = (Button) findViewById(R.id.btn_login);
		tvReg = (TextView) findViewById(R.id.tv_register);
		etUsername = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);
		
		btnLogin.setOnClickListener(this);
		tvReg.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			username = etUsername.getText().toString();
            password = etPassword.getText().toString();
            if ( !Util.isNetworkConnected(this) ){
                toast("网络未连接…");
            } 
            else if (username.equals("") || password.equals("")) {
                toast("请输入学号号和密码…");
                break;
            } 
            else {
                User bu2 = new User();
                bu2.setUsername(username);
                bu2.setPassword(password);
                bu2.login(this, new SaveListener() {

					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO Auto-generated method stub
						toast("网络不通, 无法登录…");
					}

					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						toast("欢迎登录…");
						Intent toHome = new Intent(LoginActivity.this,
								TabActivity.class);
						startActivity(toHome);
						finish();						
					}

                });
            }
            break;            
		case R.id.tv_register:
			Intent toReg = new Intent(LoginActivity.this, RegisterActivity.class);
			startActivity(toReg);
			break;			
		default:
			break;
		}
	}
	
	public void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}
}
