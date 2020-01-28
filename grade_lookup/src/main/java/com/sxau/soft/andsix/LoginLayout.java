package com.sxau.soft.andsix;
import android.annotation.TargetApi;
import android.content.Context;
import org.jetbrains.annotations.Nullable;
import android.util.AttributeSet;
import com.google.android.material.textfield.TextInputLayout;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
public final class LoginLayout extends android.widget.LinearLayout {
	public android.content.Context getMyContext() {
		return this.mContext;
	}
	private android.content.Context mContext = null;
	private TextInputLayout usrname_hint = null;
	private TextInputLayout password_hint = null;
	private TextInputLayout capt_hint = null;
	private EditText username_ipu = null;
	private EditText passwd_ipu = null;
	private EditText capt_ipu = null;
	private Button btn_login = null;
	private ImageView captcha = null;
	public LoginLayout(Context context) {
		super(context);
		this.mContext = context;
		android.util.Log.d("YFDC", "LoginLayout(context=" + String.valueOf(context) + ") constructor called.");
	}
	public LoginLayout(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		android.util.Log.d("YFDC", "LoginLayout(context=" + String.valueOf(context) + ",attrs=" + String.valueOf(attrs) + ") constructor called.");
	}
	public LoginLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.mContext = context;
		StringBuilder sb = new StringBuilder();
		sb = sb.append("LoginLayout(context=").append(String.valueOf(context)).append(",");
		sb = sb.append("attrs=").append((attrs == null) ? ("<null>") : (attrs.toString())).append(",");
		sb = sb.append("defStyleAttr=0x").append(Integer.toHexString(defStyleAttr)).append(") constructor called");
		final String msg = (sb.toString());
		android.util.Log.d("YFDC", msg);
	}
	@TargetApi(value = 21) public LoginLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		this.mContext = context;
		StringBuilder sb = new StringBuilder();
		sb = sb.append("LoginLayout(context=").append(String.valueOf(context)).append(",");
		sb = sb.append("attrs=").append((attrs == null) ? ("<null>") : (attrs.toString())).append(",");
		sb = sb.append("defStyleAttr=0x").append(Integer.toHexString(defStyleAttr)).append(",");
		sb = sb.append("defStyleRes=0x").append(Integer.toHexString(defStyleRes));
		sb = sb.append(") constructor called");
		final String msg = (sb.toString());
		android.util.Log.d("YFDC", msg);
	}
	public Button getLoginButton() {
		return (this.btn_login);
	}
	@Override protected void onFinishInflate() {
		super.onFinishInflate();
		this.usrname_hint = (TextInputLayout) findViewById(R.id.username_hint);
		this.password_hint = (TextInputLayout) findViewById(R.id.passwd_hint);
		this.capt_hint = (TextInputLayout) findViewById(R.id.capt_hint);
		this.username_ipu = (EditText) findViewById(R.id.username_ipu);
		this.passwd_ipu = (EditText) findViewById(R.id.passwd_ipu);
		this.capt_ipu = (EditText) findViewById(R.id.capt_ipu);
		this.btn_login = (Button) findViewById(R.id.btn_login);
		this.captcha = (ImageView) findViewById(R.id.img_capt_vw);
		MyTextShow a1 = new MyTextShow(usrname_hint, username_ipu);
		MyTextShow a2 = new MyTextShow(password_hint, passwd_ipu);
		MyTextShow a3 = new MyTextShow(capt_hint, capt_ipu);
		a1.init();
		a2.init();
		a3.init();
		android.util.Log.d("YFDC", "onFinishInflate() function called.");
	}
	public TextInputLayout getUserHint() {
		return this.usrname_hint;
	}
	public EditText getUserInput() {
		return this.username_ipu;
	}
	public TextInputLayout getPasswordHint() {
		return this.password_hint;
	}
	public EditText getPasswordInput() {
		return this.passwd_ipu;
	}
	public TextInputLayout getCaptchaHint() {
		return this.capt_hint;
	}
	public EditText getCaptchaInput() {
		return this.capt_ipu;
	}
	public ImageView getCaptchaView() {
		return this.captcha;
	}
	public android.content.res.Resources getLoginResources() {
		return this.mContext.getResources();
	}
}
