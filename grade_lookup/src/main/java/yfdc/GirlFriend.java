package yfdc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
public final class GirlFriend<T> {
	private boolean fcalled = false;
	public android.widget.ArrayAdapter<T> getAdapter() {
		if (!fcalled) {
			android.util.Log.e("WARNING", "using an empty ArrayAdapter.");
		}
		return this.mAdapter;
	}
	private android.widget.ArrayAdapter<T> mAdapter;
	private java.util.List<T> mlist;
	public GirlFriend<T> setList(@NotNull java.util.List<T> list) {
		java.util.List<T> from;
		try {
			from = java.util.Objects.requireNonNull(list);
		} catch (NullPointerException ex) {
			if (!fcalled) fcalled = true;
			return this;
		}
		if (this.mlist == null) {
			this.mlist = new java.util.ArrayList<T>();
		} else {
			try {
				this.mlist.clear();
			} catch (Throwable ex) {
				this.mlist = new java.util.ArrayList<T>();
			}
		}
		for (T item : from) {
			java.util.Collections.addAll(this.mlist, item);
		}
		this.mAdapter.notifyDataSetChanged();
		if (!fcalled) fcalled = true;
		return this;
	}
	private GirlFriend(@NotNull final android.content.Context cc) {
		super();
		this.mlist = new java.util.ArrayList<T>();
		this.mAdapter = new android.widget.ArrayAdapter<T>(cc, android.R.layout.simple_spinner_dropdown_item, this.mlist);
		this.mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
	}
	@Nullable public static <T> GirlFriend<T> getInstance(@NotNull final android.content.Context ccs) {
		GirlFriend<T> gf = null;
		try {
			gf = (new GirlFriend<T>(ccs));
		} catch (Throwable ex) {
			GsonFactory.logstacktrace(GsonFactory.stacktrace(ex));
		}
		return gf;
	}
}
