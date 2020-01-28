package com.sxau.soft.andsix;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.sxau.soft.andsix.thread.GradeGetThread;
import com.sxau.soft.andsix.thread.LogoutThread;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import android.view.View;
import android.widget.*;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import yfdc.GirlFriend;
import yfdc.GradeAdapter;
import yfdc.javabean.ChengjiListBean;
import yfdc.javabean.GradeListResultBean;
import yfdc.javabean.grade.PassedGrade;
import yfdc.javabean.grade.UnpassedGrade;
import android.view.KeyEvent;
import android.Manifest;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
public class MainActivity extends androidx.appcompat.app.AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener, ActivityCompat.OnRequestPermissionsResultCallback {
	private static int errcount = 0;
	@Override public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 16) {
			if (permissions.length == grantResults.length) {
				for (int i = 0; i < permissions.length; i++) {
					if (ContextCompat.checkSelfPermission(this, permissions[i]) == grantResults[i]) {
						int ex = grantResults[i];
						if (ex != PackageManager.PERMISSION_GRANTED) {
							this.requirePermissions();
							errcount++;
							if (errcount == 3) {
								this.finish();
							}
						} else {
							errcount--;
							if (errcount < 0) errcount = 0;
						}
					}
				}
			}
		}
	}
	private static class yfdx extends Thread {
		private final MainActivity act;
		private yfdx(MainActivity d) {
			super((Runnable) null, "yfdc");
			act = d;
		}
		@Override public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				android.os.Message msg = new android.os.Message();
				msg.what = 0xed7b;
				msg.obj = Thread.currentThread().getName();
				act.getHandler().sendMessage(msg);
				try {
					Thread.sleep(10000, 200);
				} catch (Throwable ex){
					break;
				}
			}
		}
	}
	private void requirePermissions() {
		java.util.ArrayList<String> list = new java.util.ArrayList<String>();
		list.clear();
		if (android.os.Build.VERSION.SDK_INT >= 23) {
			for (String i : MainActivity.permissions) {
				if (ContextCompat.checkSelfPermission(this, i) != PackageManager.PERMISSION_GRANTED) {
					list.add(i);
				}
			}
			if (list.size() >= 1) {
				String[] stri = new String[list.size()];
				try {
					for (int s = 0; s < list.size(); s++) {
						stri[s] = list.get(s);
					}
				} catch (Throwable h) {
					stri = (String[]) list.toArray();
				}
				assert stri != null;
				ActivityCompat.requestPermissions(this, stri, 16);
			}
		}
	}
	private static final String[] permissions = {Manifest.permission.READ_PHONE_STATE};
	private GradeListResultBean historybean = null;
	private PassedGrade passedbean = null;
	private UnpassedGrade unpassedbean = null;
	private java.util.Map<String, Object> resmap = null;
	private boolean sucgetdata = false;
	private DrawerLayout drawerLayout = null;
	private LinearLayout notlogin = null, suclogin = null;
	private TextView namestr;
	private static boolean login = false;
	private FloatingActionButton fab = null;
	private java.util.HashSet<String> semester_set = new java.util.HashSet<String>();
	private java.util.HashSet<String> getHistorySet() {
		if (sucgetdata && historybean != null) {
			semester_set.clear();
			java.util.List<ChengjiListBean> ass = historybean.getCjList();
			for (int i = 0; i < ass.size(); i++) {
				ChengjiListBean item = ass.get(i);
				final String str = MainActivity.semester(item);
				if (!semester_set.contains(str)) {
					semester_set.add(str);
				}
			}
		}
		return semester_set;
	}
	private static java.util.ArrayList<String> semesterlist = null;
	private static java.util.ArrayList<String> getSemesterList(@NotNull final java.util.Set<String> set) {
		if (MainActivity.semesterlist == null) {
			MainActivity.semesterlist = new java.util.ArrayList<String>(10);
		} else {
			try {
				MainActivity.semesterlist.clear();
			} catch (Throwable ex) {
				MainActivity.semesterlist = new java.util.ArrayList<String>();
			}
		}
		MainActivity.semesterlist.addAll(set);
		return MainActivity.semesterlist;
	}
	@Override public boolean onKeyDown(int keyCode, KeyEvent event) {
		boolean b = ((keyCode == KeyEvent.KEYCODE_BACK) || (keyCode == KeyEvent.KEYCODE_ESCAPE));
		if (b && super.onKeyDown(keyCode, event)) {
			(new ExitDialog(this)).init().show();
			return true;
		}
		return (b);
	}
	private java.util.HashSet<String> getPassedSet() {
		if (sucgetdata && passedbean != null) {
			semester_set.clear();
			java.util.List<GradeListResultBean> ass = passedbean.getList();
			for (int i = 0; i < ass.size(); i++) {
				GradeListResultBean var1 = ass.get(i);
				java.util.List<ChengjiListBean> var2 = var1.getCjList();
				for (int t = 0; t < var2.size(); t++) {
					ChengjiListBean item = var2.get(t);
					final String str = MainActivity.semester(item);
					if (!semester_set.contains(str)) {
						semester_set.add(str);
					}
				}
			}
		}
		return semester_set;
	}
	private java.util.HashSet<String> getUnpassedSet() {
		if (sucgetdata && unpassedbean != null) {
			semester_set.clear();
			java.util.List<GradeListResultBean> var1 = unpassedbean.getList();
			for (int a = 0; a < var1.size(); a++) {
				GradeListResultBean var2 = var1.get(a);
				List<ChengjiListBean> var3 = var2.getCjList();
				for (int b = 0; b < var3.size(); b++) {
					ChengjiListBean item = var3.get(b);
					final String str = MainActivity.semester(item);
					if (!semester_set.contains(str)) {
						semester_set.add(str);
					}
				}
			}
		}
		return semester_set;
	}
	private static String semester(ChengjiListBean item) {
		return (new StringBuilder()).append(item.getAcademicYearCode()).append((char) 0x0020).append(item.getTermName()).toString();
	}
	private static List<Map<String, Object>> list_of_map;
	static {
		list_of_map = new java.util.ArrayList<Map<String, Object>>();
	}
	private static List<Map<String, Object>> histrylist(@NotNull final GradeListResultBean bean, @NotNull final String semester) {
		java.util.List<java.util.Map<String, Object>> output = list_of_map;
		list_of_map.clear();
		List<ChengjiListBean> gre = bean.getCjList();
		List<ChengjiListBean> gra = new ArrayList<ChengjiListBean>();
		for (int i = 0; i < gre.size(); i++) {
			if (MainActivity.semester(gre.get(i)).equals(semester)) {
				gra.add(gre.get(i));
			}
		}
		updateResult(output, gra, false, "history");
		return output;
	}
	private static List<Map<String, Object>> passedlist(@NotNull final PassedGrade bean, @NotNull final String semester) {
		java.util.List<java.util.Map<String, Object>> as = list_of_map;
		as.clear();
		List<GradeListResultBean> cj = bean.getList();
		for (int a = 0; a < cj.size(); a++) {
			List<ChengjiListBean> s = cj.get(a).getCjList();
			List<ChengjiListBean> s2 = new ArrayList<ChengjiListBean>();
			for (int b = 0; b < s.size(); b++) {
				if (MainActivity.semester(s.get(b)).equals(semester)) {
					s2.add(s.get(b));
				}
			}
			updateResult(as, s2, false, "passed");
		}
		return as;
	}
	private static List<Map<String, Object>> unpassedlist(@NotNull final UnpassedGrade bean, @NotNull final String semester) {
		java.util.List<java.util.Map<String, Object>> output = list_of_map;
		output.clear();
		List<GradeListResultBean> unp = bean.getList();
		for (int a = 0; a < unp.size(); a++) {
			GradeListResultBean gradelist = unp.get(a);
			String str = gradelist.getCjlx();
			if (str == null) str = "";
			List<ChengjiListBean> cjj = gradelist.getCjList();
			List<ChengjiListBean> cjj2 = new ArrayList<ChengjiListBean>();
			cjj2.clear();
			for (int b = 0; b < cjj.size(); b++) {
				if (MainActivity.semester(cjj.get(b)).equals(semester)) {
					cjj2.add(cjj.get(b));
				}
			}
			updateResult(output, cjj2, true, str);
		}
		return output;
	}
	private static void updateResult(final java.util.List<java.util.Map<String, Object>> output, final java.util.List<ChengjiListBean> input, final boolean unpass, final String type) {
		for (int i = 0; i < input.size(); i++) {
			java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
			map.clear();
			map.put("course_num", input.get(i).getId().getCourseNumber());
			String cname = input.get(i).getCourseName();
			if (unpass && type != null) cname = "[" + type + "]" + cname;
			map.put("course_name", cname);
			String var1 = input.get(i).getCj();
			String var2 = input.get(i).getCourseScore();
			if (var1 == null || var1.length() == 0) {
				var1 = var2;
			} else {
				var1 = (var1.trim().equalsIgnoreCase(var2.trim())) ? var1 : var2;
			}
			final String getGrade = var1;
			String grade = String.format(Locale.US, "%.3f", Float.parseFloat(getGrade));
			map.put("grade", grade);
			final String sth = MainActivity.semester(input.get(i));
			map.put("semester", sth);
			output.add(map);
		}
	}
	public static final String URL_HISTORYCODE = "/student/integratedQuery/scoreQuery/schemeScores/callback";
	public static final String URL_NOT_PASSED = "/student/integratedQuery/scoreQuery/unpassedScores/callback";
	public static final String URL_PASSED = "/student/integratedQuery/scoreQuery/allPassingScores/callback";
	private static final class MyHandler extends android.os.Handler {
		private final MainActivity activity;
		private MyHandler(MainActivity _act) {
			super();
			final java.lang.ref.WeakReference<MainActivity> reference = new java.lang.ref.WeakReference<MainActivity>(_act);
			this.activity = reference.get();
		}
		@SuppressWarnings(value = {"unchecked"})
		@Override public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
				case 0: {//登陆成功
					String str = "";
					MainActivity.login = true;
					activity.loginsuccess();
					if (msg.obj instanceof java.util.Map) {
						str = (String) ((java.util.Map<String, Object>) msg.obj).get("name");
					}
					Snackbar.make(activity.findViewById(R.id.yfdc), "欢迎您, " + str, Snackbar.LENGTH_LONG).show();
					activity.namestr.setText(str);
					(new GradeGetThread(this.activity)).start();
				}
				break;
				case 1: {//获取数据成功
					try {
						activity.sucgetdata = true;
						activity.resmap = (java.util.Map<String, Object>) msg.obj;
						activity.passedbean = (PassedGrade) activity.resmap.get("passed");
						activity.unpassedbean = (UnpassedGrade) activity.resmap.get("not_passed");
						activity.historybean = (GradeListResultBean) activity.resmap.get("history");
					} catch (Throwable ex) {
						activity.sucgetdata = false;
						ex.printStackTrace();
						android.util.Log.e("YFDC", ex.toString(), ex);
					}
				}
				break;
				case 2: {//获取数据失败
					activity.sucgetdata = false;
					activity.notlogin.setVisibility(View.VISIBLE);
					activity.suclogin.setVisibility(View.GONE);
					TextView tv = (TextView) activity.findViewById(R.id.tv_result);
					if (tv != null) {
						tv.setText("获取成绩列表失败");
					}
					(new GradeGetThread(this.activity)).start();
				}
				break;
				default:
					break;
			}
		}
	}
	private GirlFriend<String> girlfriend = null;
	private void showlist(int which) {
		final String semester = listcheme.getSelectedItem().toString();
		RecyclerView lw = this.<RecyclerView>findViewById(R.id.list_grade_view);
		lw.setLayoutManager(linearLayoutManager);
		lw.setVisibility(View.VISIBLE);
		lw.setAdapter(listAdapter(which,semester));
	}
	private LinearLayoutManager linearLayoutManager;
	private void showhistory() {
		sucl();
		girlfriend.setList(getSemesterList(getHistorySet()));
	}
	private void showpass() {
		sucl();
		girlfriend.setList(getSemesterList(getPassedSet()));
	}
	private void showunpass() {
		if (no_unpass()) return;
		girlfriend.setList(getSemesterList(getUnpassedSet()));
	}
	private yfdc.GradeAdapter listAdapter(int which, String semester) {
		List<Map<String, Object>> list = null;
		if (which == 1) {
			list = MainActivity.histrylist(historybean, semester);
			return GradeAdapter.getAdapter(list);
		} else if (which == 2) {
			list = MainActivity.passedlist(passedbean, semester);
			return GradeAdapter.getAdapter(list);
		} else if (which == 3) {
			list = MainActivity.unpassedlist(unpassedbean, semester);
			return GradeAdapter.getAdapter(list);
		} else {
			list = new ArrayList<Map<String, Object>>();
			list.clear();
			HashMap<String, Object> obj = new HashMap<String, Object>();
			obj.put("course_num", getResources().getString(R.string.课程号示例));
			obj.put("course_name", getResources().getString(R.string.课程名示例));
			obj.put("semester", getResources().getString(R.string.学期示例));
			obj.put("grade", getResources().getString(R.string.成绩示例));
			list.add(obj);
			return GradeAdapter.getAdapter(list);
		}
	}
	private void logout() {
		AlertDialog.Builder ac = (new AlertDialog.Builder(this));
		ac.setTitle("系统提示");
		ac.setMessage("确定要退出登录吗?");
		ac.setPositiveButton("确定", (_d, _w) -> {
			logout2();
			Snackbar abs = Snackbar.make(findViewById(R.id.yfdc), "已退出登录", Snackbar.LENGTH_INDEFINITE);
			abs.show();
			(new android.os.Handler()).postDelayed(() -> {
				abs.dismiss();
			}, 2000L);
		});
		ac.setNegativeButton("取消", null);
		(ac.create()).show();
	}

	protected void logout2() {
		(new LogoutThread()).start();
		if (namestr != null) {
			namestr.setText(R.string.str_not_login2);
		}
		TextView tv = (TextView) findViewById(R.id.tv_result);
		if (tv != null) {
			tv.setText(R.string.str_not_login);
		}
		MainActivity.login = false;
		notlogin.setVisibility(View.VISIBLE);
		suclogin.setVisibility(View.GONE);
		yfdc.CookieJarImplKt.clearCache();
		dialog.refresh();
		fab.show();
		fab.setEnabled(true);
		fab.setFocusable(true);
	}

	private void sucl() {
		suclogin.setVisibility(View.VISIBLE);
		notlogin.setVisibility(View.GONE);
	}

	private boolean no_unpass() {
		if (sucgetdata && unpassedbean.getList().get(0).getCjList().size() == 0 && unpassedbean.getList().get(1).getCjList().size() == 0) {
			suclogin.setVisibility(View.GONE);
			notlogin.setVisibility(View.VISIBLE);
			TextView tv = (TextView) findViewById(R.id.tv_result);
			if (tv != null) {
				tv.setText(R.string.no_unpass_str);
			}
			return true;
		} else {
			android.util.Log.d("YFDC", unpassedbean.toString());
			sucl();
			return false;
		}
	}

	private void loginsuccess() {
		fab.hide();
		fab.setEnabled(false);
		View v = findViewById(R.id.yfdc);
		TextView tv = (TextView) findViewById(R.id.tv_result);
		if (tv != null) {
			tv.setText("请在左侧菜单选择成绩类型");
		}
		Toast t = Toast.makeText(this, (CharSequence) null, Toast.LENGTH_LONG);
		t.setText("请在左侧菜单选择成绩类型");
		t.show();
		(new android.os.Handler()).postDelayed(() -> {
			DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
			drawer.openDrawer(androidx.core.view.GravityCompat.START);
		}, 2000L);
	}

	private final android.os.Handler handler = new MyHandler(this);

	public final android.os.Handler getHandler() {
		return this.handler;
	}

	private LoginDialog dialog = null;
	private int listwhich = 0;

	@Override
	public boolean onNavigationItemSelected(@NotNull final android.view.MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.menu_about) {
			(new AboutDialog(this)).init();
		} else if (id == R.id.menu_exit) {
			(new ExitDialog(this)).init().show();
		}
		if (MainActivity.login && this.sucgetdata) {
			notlogin.setVisibility(View.GONE);
			suclogin.setVisibility(View.VISIBLE);
			RecyclerView lv = this.<RecyclerView>findViewById(R.id.list_grade_view);
			lv.setVisibility(View.GONE);
			switch (id) {
				case (R.id.history_score): {
					showhistory();
					listwhich = 1;
				}
				break;
				case (R.id.passed_score): {
					showpass();
					listwhich = 2;
				}
				break;
				case (R.id.unpassed_score): {
					showunpass();
					listwhich = 3;
				}
				break;
				default:
					break;
			}
		} else {
			notlogin.setVisibility(View.VISIBLE);
			suclogin.setVisibility(View.GONE);
		}
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(androidx.core.view.GravityCompat.START);
		return true;
	}

	public static final String CON_URL = "http://xsjwxt.sxau.edu.cn:";
	private static NetPort nowport = NetPort.port7873;

	public static String getPort() {
		return nowport.getPort();
	}

	public static void changePort() {
		if (nowport == NetPort.port7872) {
			nowport = NetPort.port7873;
		} else if (nowport == NetPort.port7873) {
			nowport = NetPort.port7872;
		}
	}

	private static final okhttp3.OkHttpClient client = (new okhttp3.OkHttpClient.Builder()).cookieJar(yfdc.CookieJarImplKt.getInstance()).build();

	@NotNull
	public static okhttp3.OkHttpClient getClient() {
		return MainActivity.client;
	}

	public MainActivity() {
		super();
		android.util.Log.d("YFDC", "Main activity constructor called.");
	}

	private Spinner listcheme;

	private void adjust_screen() {
		if (getRequestedOrientation() != android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
			setRequestedOrientation(android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
	}

	@Override
	protected void onStart() {
		this.adjust_screen();
		super.onStart();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		this.adjust_screen();
	}

	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		this.adjust_screen();
		super.onCreate(savedInstanceState);
		final StrictMode.ThreadPolicy policy = (new StrictMode.ThreadPolicy.Builder()).permitAll().build();
		StrictMode.setThreadPolicy(policy);
		this.setContentView(R.layout.activity_login);
		this.linearLayoutManager = new LinearLayoutManager(this);
		this.girlfriend = GirlFriend.<String>getInstance(this);
		notlogin = (LinearLayout) findViewById(R.id.notlogin);
		suclogin = (LinearLayout) findViewById(R.id.login_success);
		dialog = new LoginDialog(this);
		androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
		this.setSupportActionBar(toolbar);
		this.fab = (FloatingActionButton) findViewById(R.id.fab);
		this.fab.setOnClickListener((v) -> {
			if (!dialog.isLogin()) {
				dialog.show();
			} else {
				fab.setEnabled(false);
				fab.hide();
			}
		});
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		androidx.appcompat.app.ActionBarDrawerToggle toggle = new androidx.appcompat.app.ActionBarDrawerToggle(
				this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawerLayout.addDrawerListener(toggle);
		toggle.syncState();
		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
		View h = navigationView.getHeaderView(0);
		namestr = (TextView) h.findViewById(R.id.name_txt);
		if (namestr != null) namestr.setOnClickListener((v) -> {
			drawerLayout.closeDrawer(androidx.core.view.GravityCompat.START);
			if (MainActivity.login) {
				logout();
			} else {
				dialog.show();
			}
		});
		listcheme = (Spinner) this.<Spinner>findViewById(R.id.semester_spinner);
		if (listcheme != null) listcheme.setAdapter(this.girlfriend.getAdapter());
		Button button = (Button) this.<Button>findViewById(R.id.btn_lookup);
		button.setOnClickListener((v) -> {
			if (MainActivity.login && MainActivity.this.sucgetdata) {
				try {
					showlist(MainActivity.this.listwhich);
				} catch (Throwable ex) {
					Snackbar.make(findViewById(R.id.yfdc), "请选择学期呢<_>", Snackbar.LENGTH_LONG).show();
				}
			}
		});
		requirePermissions();
		try {
			(new yfdx(this)).start();
			Intent intent = new Intent(MainActivity.this, Testserv.class);
			startService(intent);
		} catch (Throwable ex) {
			ex.printStackTrace(System.out);
		}
	}

	@NotNull
	@Override
	public String toString() {
		return yfdc.CookieJarImplKt.INSTANCE.toString();
	}

	@Override
	protected void onDestroy() {
		drawerLayout = null;
		dialog = null;
		notlogin = null;
		suclogin = null;
		fab = null;
		listcheme = null;
		this.girlfriend = null;
		super.onDestroy();
		System.gc();
		System.exit(0);
		throw (new RuntimeException("Application exited successfully"));
	}
}
