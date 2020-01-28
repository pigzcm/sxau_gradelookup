package com.sxau.soft.andsix;
public final class AboutDialog implements android.content.DialogInterface.OnClickListener {
	@Override public void onClick(android.content.DialogInterface dialog, int which) {
		aboutdlg.dismiss();
	}
	private android.app.AlertDialog.Builder bd;
	private android.app.AlertDialog aboutdlg;
	private MainActivity activity;

	public MainActivity getActivity() {
		return this.activity;
	}
	AboutDialog(MainActivity _activity) {
		this.activity = _activity;
		this.bd = (new android.app.AlertDialog.Builder((android.content.Context) this.activity));
	}
	void init() {
		bd.setNegativeButton("关闭", this);
		bd.setTitle("关于");
		bd.setMessage("成绩查询(手机版)\r\n本程序用于辅助期末查询工作, 使屏幕分辨率便于用户阅读。\r\n本程序承诺不会收集任何敏感信息。");
		aboutdlg = bd.create();
		aboutdlg.show();
	}
}
