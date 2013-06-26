package com.example.school4foolspleasework;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class CustomDialogClass extends Dialog implements
android.view.View.OnClickListener {

	public Activity c;
	public Dialog d;
	public Button yes, no;
	public Editable userName;
	public Editable password;

	public CustomDialogClass(Activity a) {
		super(a);
		// TODO Auto-generated constructor stub
		this.c = a;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.custom_dialog);
		yes = (Button) findViewById(R.id.button1);
		yes.setOnClickListener(this);

	}
	
	public Editable getUser() {
		return userName;
	}
	
	public Editable getPass() {
		return password;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			EditText user;
			EditText pass;
			while (userName.equals("") || password.equals("")) {
				user = (EditText) findViewById(R.id.btn_yes);
				pass = (EditText) findViewById(R.id.btn_no);
				userName = user.getText();
				password = pass.getText();
			}
			break;
		default:
			break;
		}
		dismiss();
	}
}