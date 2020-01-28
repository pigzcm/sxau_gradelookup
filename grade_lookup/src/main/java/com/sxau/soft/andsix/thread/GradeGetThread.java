package com.sxau.soft.andsix.thread;
import com.sxau.soft.andsix.MainActivity;
import yfdc.BeanFactory;
import android.os.Message;
public final class GradeGetThread extends Thread {
	private MainActivity activity = null;
	public GradeGetThread(MainActivity _activity) {
		super((Runnable) null, "GradeGetThread");
		this.activity = _activity;
	}
	private static String getString(final int which) {
		StringBuilder sb = (new StringBuilder()).append(MainActivity.CON_URL).append(MainActivity.getPort());
		if (which == 0) {
			sb = sb.append(MainActivity.URL_HISTORYCODE);
			return (sb.toString());
		} else if (which == 1) {
			sb = sb.append(MainActivity.URL_PASSED);
			return (sb.toString());
		} else if (which == 2) {
			sb = sb.append(MainActivity.URL_NOT_PASSED);
			return (sb.toString());
		} else {
			return "";
		}
	}
	@Override public void run() {
		java.util.HashMap<String, Object> maps = new java.util.HashMap<String, Object>();
		maps.clear();
		final okhttp3.Request gradeHistoryRequest = (new okhttp3.Request.Builder()).url(getString(0)).build();
		final okhttp3.Request gradePassedRequest = (new okhttp3.Request.Builder()).url(getString(1)).build();
		final okhttp3.Request gradeUnpassedRequest = (new okhttp3.Request.Builder()).url(getString(2)).build();
		final okhttp3.OkHttpClient client = MainActivity.getClient();
		okhttp3.Response gradeHistoryResponse = null,
				gradePassedResponse = null,
				gradeUnpassedResponse = null;
		okhttp3.ResponseBody gradeHistoryResponseBody = null,
				gradePassedResponseBody = null,
				gradeUnpassedResponseBody = null;
		try {
			gradeHistoryResponse = client.newCall(gradeHistoryRequest).execute();//连接到历年成绩
			gradePassedResponse = client.newCall(gradePassedRequest).execute();//全部及格成绩
			gradeUnpassedResponse = client.newCall(gradeUnpassedRequest).execute();//不及格成绩
			//以下3行获取response_body
			gradeHistoryResponseBody = gradeHistoryResponse.body();
			gradePassedResponseBody = gradePassedResponse.body();
			gradeUnpassedResponseBody = gradeUnpassedResponse.body();
			if ((gradeHistoryResponseBody != null) && (gradePassedResponseBody != null) && (gradeUnpassedResponseBody != null)) {
				String a, b, c;
				a = gradeHistoryResponseBody.string();
				b = gradePassedResponseBody.string();
				c = gradeUnpassedResponseBody.string();
				try {
					gradeHistoryResponse.close();
					gradePassedResponse.close();
					gradeUnpassedResponse.close();
				} catch (Throwable ignore){}
				maps.put("history_string", a);
				maps.put("passed_string", b);
				maps.put("unpassed_string", c);
				maps.put("history", BeanFactory.getHistoryBean(a));
				maps.put("passed", BeanFactory.getPassedBean(b));
				maps.put("not_passed", BeanFactory.getUnpassedBean(c));
				Message message = new Message();
				message.what = 1;
				message.obj = maps;
				this.activity.getHandler().sendMessage(message);
			} else {
				throw (new NullPointerException("empty response body"));
			}
		} catch (Throwable hh) {
			maps.clear();
			final String ase = yfdc.GsonFactory.stacktrace(hh);
			yfdc.GsonFactory.logstacktrace(ase);
			Message message = new Message();
			message.what = 2;
			message.obj = hh;
			this.activity.getHandler().sendMessage(message);
		}
	}
}
