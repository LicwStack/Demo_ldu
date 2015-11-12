package com.li.ldu;

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
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener {

	private Button btnReg;
	private EditText etUsername;
	private EditText etPassword;
	private EditText etComfirmPsd;
	private EditText etPhone;

	private String username = null;
	private String password = null;
	private String comfirmPsd = null;
	private String phone = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		etUsername = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);
		etComfirmPsd = (EditText) findViewById(R.id.et_comfirm_psd);
		etPhone = (EditText) findViewById(R.id.et_phone);

		btnReg = (Button) findViewById(R.id.bt_reg);
		btnReg.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_reg:
			username = etUsername.getText().toString();
			password = etPassword.getText().toString();
			comfirmPsd = etComfirmPsd.getText().toString();
			phone = etPhone.getText().toString();
			if ( !Util.isNetworkConnected(this) ) {
				toast("网络未连接… ");
			} else if (username.equals("") || password.equals("")
					|| comfirmPsd.equals("") || phone.equals("")) {
				toast("请将注册信息填写完整… ");
			} else if (!comfirmPsd.equals(password)) {
				toast("两次密码输入不一致… ");
			} else if(!Util.isPhoneNumberValid(phone)) {
				toast("请输入正确的手机号码… ");
			}else {
				// 开始提交注册信息
				User bu = new User();
				bu.setUsername(username);
				bu.setPassword(password);
				bu.setPhone(phone);
				bu.signUp(this, new SaveListener() {
					@Override
					public void onFailure(int arg0, String arg1) {
						toast("被人捷足先登了, 换个名字吧！");
					}

					public void onSuccess() {
						toast("注册成功！");
						Intent backLogin = new Intent(RegisterActivity.this,
								LoginActivity.class);
						startActivity(backLogin);
						RegisterActivity.this.finish();
					}
				});
			}
			break;
		default:
			break;
		}
	}

	public void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}

}
