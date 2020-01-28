package com.sxau.soft.andsix;
import android.content.DialogInterface;
import com.sxau.soft.andsix.thread.LogoutThread;
public final class ExitDialog implements DialogInterface.OnClickListener {
	private final MainActivity activity;
	private final android.app.AlertDialog.Builder builder;
	private android.app.AlertDialog exitDialog = null;
	@Override public void onClick(DialogInterface dialog, int which) {
		if (which == DialogInterface.BUTTON_POSITIVE) {
			(new LogoutThread()).start();
			activity.finish();
			dialog.dismiss();
		} else if (which == DialogInterface.BUTTON_NEGATIVE) {
			dialog.dismiss();
		} else {
			dialog.dismiss();
		}
	}
	ExitDialog(MainActivity _activity) {
		this.activity = _activity;
		this.builder = (new android.app.AlertDialog.Builder((android.content.Context) this.activity));
	}
	ExitDialog init() {
		this.builder.setTitle("系统提示");
		this.builder.setMessage("确定要退出吗?");
		this.builder.setPositiveButton("确定", this);
		this.builder.setNegativeButton("取消", this);
		this.exitDialog = builder.create();
		return this;
	}
	void show() {
		if (this.exitDialog != null) this.exitDialog.show();
	}
}
