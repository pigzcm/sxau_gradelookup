package com.sxau.soft.andsix;
import android.os.Message;
import org.jetbrains.annotations.NotNull;
import com.google.android.material.snackbar.Snackbar;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.sxau.soft.andsix.thread.CaptchaGetThread;
import com.sxau.soft.andsix.thread.LoginThread;
import com.sxau.soft.andsix.thread.LogoutThread;
import yfdc.Jiami;
public final class LoginDialog extends android.app.Dialog implements android.view.View.OnClickListener {
	private boolean showing = false;
	public boolean isLogin() {
		return this.loggin;
	}
	public void setLoginStatus(boolean status) {
		this.loggin = status;
	}
	private void refreshCaptcha() {
		if (!gettingCaptcha) {
			mLayout.getCaptchaView().setImageResource(R.drawable.waiting);
			gettingCaptcha = true;
			(new CaptchaGetThread(this)).start();
		} else {
			android.widget.Toast toa = android.widget.Toast.makeText(this.context, (CharSequence) null, android.widget.Toast.LENGTH_SHORT);
			toa.setText("正在获取验证码呢, 别急");
			toa.show();
			toa = null;
		}
	}
	private boolean gettingCaptcha = false;
	private boolean loggin = false;
	private android.os.Handler handler = new MyHandler(this);
	public android.os.Handler getHandler() {
		return (this.handler);
	}
	public java.util.HashMap<String, Object> result = new java.util.HashMap<String, Object>();
	private LoginLayout mLayout = null;
	private void login() {
		if (!loggin) {
			String username = mLayout.getUserInput().getText().toString();
			if (username == null || username.length() == 0) {
				mLayout.getUserHint().setError("输入为空");
				getEditTextFocus(mLayout.getUserInput(), false);
				return;
			}
			if (username.length() != 11) {
				mLayout.getUserHint().setError("格式错误");
				getEditTextFocus(mLayout.getUserInput(), true);
				return;
			}
			String password = mLayout.getPasswordInput().getText().toString();
			if (password == null || password.length() == 0) {
				mLayout.getPasswordHint().setError("输入为空");
				getEditTextFocus(mLayout.getPasswordInput(), false);
				return;
			}
			String nepas = Jiami.MD5(password);
			if (!nepas.equals("<error>")) password = nepas;
			String captcha = mLayout.getCaptchaInput().getText().toString();
			if (captcha == null || captcha.length() == 0) {
				mLayout.getCaptchaHint().setError("输入为空");
				getEditTextFocus(mLayout.getCaptchaInput(), true);
				return;
			} else if (captcha.length() != 4) {
				mLayout.getCaptchaHint().setError("格式错误");
				getEditTextFocus(mLayout.getCaptchaInput(), false);
				return;
			}
			(new LoginThread(this, username, password, captcha)).start();
		} else {
			android.widget.Toast toa = android.widget.Toast.makeText(this.context, (CharSequence) null, android.widget.Toast.LENGTH_SHORT);
			toa.setText("已经登陆了");
			toa.show();
			this.dismiss();
		}
	}
	@Override public void onClick(android.view.View v) {
		if (v.getId() == R.id.btn_login) {
			login();
		} else if (v.getId() == R.id.img_capt_vw) {
			refreshCaptcha();
		}
		java.lang.StringBuilder sb = new java.lang.StringBuilder();
		sb = sb.append("function onClick(v=").append(java.lang.String.valueOf((Object) v)).append(")");
		sb = sb.append(" called.");
		final String message = (sb.toString());
		android.util.Log.d("YFDC", message);
	}
	private final android.content.Context context;
	public LoginDialog(@NotNull final android.content.Context _context) {
		super(_context);
		this.context = _context;
		if (result != null && result.size() >= 1) result.clear();
	}
	@Override public void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final android.view.LayoutInflater inflater = android.view.LayoutInflater.from(this.context);
		final LoginLayout mView = (LoginLayout) inflater.inflate(R.layout.dialog_login, (android.view.ViewGroup) null);
		this.setContentView(mView);
		mLayout = mView;
		this.setCanceledOnTouchOutside(false);
		this.setCancelable(false);
		mView.getCaptchaView().setOnClickListener(this);
		mView.getLoginButton().setOnClickListener(this);
		refreshCaptcha();
		getEditTextFocus(mView.getUserInput(), true);
	}
	@Override public boolean onKeyDown(int keycode, @NotNull android.view.KeyEvent event) {
		boolean b = ((keycode == 4) || (keycode == 111));
		if (b && super.onKeyDown(keycode, event)) {
			this.dismiss();
			return true;
		}
		return b;
	}
	private static final class MyHandler extends android.os.Handler {
		private static int count = 0;
		private final LoginDialog dialog;

		private MyHandler(@NotNull final LoginDialog _dialog) {
			super();
			final java.lang.ref.WeakReference<LoginDialog> reference = new java.lang.ref.WeakReference<LoginDialog>(_dialog);
			this.dialog = reference.get();
		}
		@Override public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0) {
				android.graphics.Bitmap resc = (android.graphics.Bitmap) msg.obj;
				dialog.mLayout.getCaptchaView().setImageBitmap(resc);
				dialog.gettingCaptcha = false;
				count = 0;
			} else if (msg.what == 1) {
				count++;
				if (count <= 2) {
					MainActivity.changePort();
					(new CaptchaGetThread(dialog)).start();
				} else {
					android.widget.Toast toa = android.widget.Toast.makeText(dialog.context, (CharSequence) null, android.widget.Toast.LENGTH_SHORT);
					toa.setText("网络连接失败");
					toa.show();
					dialog.mLayout.getCaptchaView().setImageResource(R.drawable.retry);
					dialog.gettingCaptcha = false;
				}
			} else {
				switch (msg.what) {
					case 2: {
						dialog.loginSuccess((String) msg.obj);
						dialog.dismiss();
					}
					break;
					case 3: {
						if ("badCaptcha".equals((String) msg.obj)) {
							dialog.mLayout.getCaptchaInput().setText("");
							dialog.mLayout.getCaptchaInput().clearFocus();
							dialog.hideipu(dialog.mLayout.getCaptchaInput());
							dialog.mLayout.getCaptchaView().clearFocus();
							dialog.mLayout.getCaptchaHint().setErrorEnabled(false);
							dialog.mLayout.getCaptchaHint().setError("验证码错误");
							Snackbar.make(dialog.mLayout, "验证码错误", Snackbar.LENGTH_SHORT).show();
							dialog.getEditTextFocus(dialog.mLayout.getCaptchaInput(), true);
							dialog.refreshCaptcha();
						} else if ("badCredentials".equals((String) msg.obj)) {
							dialog.mLayout.getUserHint().setErrorEnabled(true);
							dialog.mLayout.getPasswordHint().setError("密码错误");
							dialog.getEditTextFocus(dialog.mLayout.getPasswordInput(), false);
						} else if ("invalidSession".equals((String) msg.obj)) {
							Toast oo = Toast.makeText(dialog.context, (CharSequence) null, Toast.LENGTH_SHORT);
							oo.setText("会话超时");
							oo.show();
						}
					}
					break;
					case 4: {
						Toast oo = Toast.makeText(dialog.context, (CharSequence) null, Toast.LENGTH_SHORT);
						oo.setText("网络连接失败");
						oo.show();
						dialog.dismiss();
					}
					break;
					default: {
						dialog.dismiss();
					}
					break;
				}
			}
		}
	}
	private void loginSuccess(String name) {
		result.put("name", name);
		result.put("stu_num", this.mLayout.getUserInput().getText().toString());
		result.put("verify", Integer.valueOf(0));
		Message message = new Message();
		message.what = 0;
		message.obj = result;
		if (this.context instanceof MainActivity) {
			final MainActivity ddd = (MainActivity) this.context;
			ddd.getHandler().sendMessage(message);
		}
		loggin = true;
	}
	private void hideipu(android.widget.EditText va) {
		InputMethodManager imm = (InputMethodManager) this.context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE);
		if (imm != null) {
			imm.hideSoftInputFromWindow(va.getWindowToken(), 0);
		}
	}
	private void getEditTextFocus(android.widget.EditText editText, boolean isNeedDelay) {
		if (!isNeedDelay) {
			InputMethodManager imm = (InputMethodManager) context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE);
			if (imm != null) {
				editText.requestFocus();
				imm.showSoftInput(editText, 0);
			}
		} else {
			editText.postDelayed(() -> {
				editText.requestFocus();
				InputMethodManager imm = (InputMethodManager) context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE);
				if (imm != null) imm.showSoftInput(editText, 0);
			}, 400L);
		}
	}
	public void refresh() {
		(new LogoutThread()).start();
		this.loggin = false;
		this.mLayout.getUserInput().setText("");
		this.mLayout.getUserHint().setErrorEnabled(false);
		this.mLayout.getPasswordInput().setText("");
		this.mLayout.getPasswordHint().setErrorEnabled(false);
		this.mLayout.getCaptchaInput().setText("");
		this.mLayout.getCaptchaHint().setErrorEnabled(false);
		this.getEditTextFocus(this.mLayout.getUserInput(), true);
	}
	@Override public void show() {
		super.show();
		if (showing) {
			refreshCaptcha();
			return;
		}
		showing = true;
	}
}
