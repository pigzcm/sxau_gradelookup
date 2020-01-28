package yfdc;
import com.sxau.soft.andsix.R;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
public final class GradeAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<GradeAdapter.YFViewHolder> {
	static final class YFViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
		android.widget.TextView c_no, c_nm, smst, grad;
		YFViewHolder(@NotNull final android.view.View view) {
			super(view);
			this.c_no = (android.widget.TextView) view.<android.widget.TextView>findViewById(R.id.课程号);
			this.c_nm = (android.widget.TextView) view.<android.widget.TextView>findViewById(R.id.课程名);
			this.smst = (android.widget.TextView) view.<android.widget.TextView>findViewById(R.id.学期);
			this.grad = (android.widget.TextView) view.<android.widget.TextView>findViewById(R.id.成绩);
		}
	}
	private static String objstr(@Nullable final Object obj) {
		if (obj == null) {
			return "<null>";
		} else {
			return obj.toString();
		}
	}
	public synchronized static GradeAdapter getAdapter(@NotNull java.util.List<java.util.Map<String, Object>> list_of_map) {
		GradeAdapter adapter = (new GradeAdapter());
		for (java.util.Map<String, Object> map : list_of_map) {
			java.util.Collections.addAll(adapter.gradelist, map);
		}
		return adapter;
	}
	private java.util.List<java.util.Map<String, Object>> gradelist;
	private GradeAdapter() {
		super();
		this.gradelist = (new java.util.ArrayList<java.util.Map<String, Object>>());
	}
	@NotNull @Override public YFViewHolder onCreateViewHolder(@NotNull final android.view.ViewGroup parent, int viewType) {
		android.view.View vue;
		try {
			vue = android.view.LayoutInflater.from(parent.getContext()).inflate(R.layout.grade_list_item, parent, false);
		} catch (Throwable ex) {
			vue = new android.view.View((android.content.Context) null);
			yfdc.GsonFactory.logstacktrace(yfdc.GsonFactory.stacktrace(ex));
		}
		return (new YFViewHolder(vue));
	}
	@Override public void onBindViewHolder(@NotNull final YFViewHolder holder, final int position) {
		java.util.Map<String, Object> objm = this.gradelist.get(position);
		if (objm != null) {
			String a = "", b = a, c = a, d = a;
			try {
				a = objstr(objm.get("course_num"));
				b = objstr(objm.get("course_name"));
				c = objstr(objm.get("grade"));
				d = objstr(objm.get("semester"));
			} catch (Throwable ignore){}
			try {
				java.util.Objects.requireNonNull(holder.c_no).setText(a);
				java.util.Objects.requireNonNull(holder.c_nm).setText(b);
				java.util.Objects.requireNonNull(holder.grad).setText(c);
				java.util.Objects.requireNonNull(holder.smst).setText(d);
			} catch (Throwable ex) {
				yfdc.GsonFactory.logstacktrace(yfdc.GsonFactory.stacktrace(ex));
			}
		}
	}
	/**
	 * @return 返回一个整数, 表示列表项个数
	 * @author 张乘铭
	 * 获取列表项个数
	 */
	@Override public int getItemCount() {
		return this.gradelist.size();
	}
}
