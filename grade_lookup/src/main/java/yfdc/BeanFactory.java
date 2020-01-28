package yfdc;
import androidx.annotation.Keep;
import com.sxau.soft.andsix.BuildConfig;
import yfdc.javabean.GradeListResultBean;
import yfdc.javabean.grade.PassedGrade;
import yfdc.javabean.grade.UnpassedGrade;
import org.jetbrains.annotations.NotNull;
public final class BeanFactory extends yfdc.Factory.FactImpl {
	@Keep private BeanFactory() throws RuntimeException {
		RuntimeException ex = (new RuntimeException("BeanFactory constructor could not be called."));
		android.util.Log.e("ERR", ex.toString());
		throw ex;
	}
	public static GradeListResultBean getHistoryBean(@NotNull final String json) {
		GradeListResultBean res;
		final String csc = json.substring(1, json.length() - 1);
		try {
			res = GsonFactory.makeNewGson().fromJson(csc, GradeListResultBean.class);
		} catch (Throwable h) {
			res = null;
			h.printStackTrace(System.out);
		}
		if (res != null && BuildConfig.DEBUG) {
			android.util.Log.d("YFDC", String.valueOf((Object) res));
		}
		return res;
	}
	public static PassedGrade getPassedBean(@NotNull final String json) {
		PassedGrade res;
		try {
			res = GsonFactory.makeNewGson().fromJson(json, PassedGrade.class);
		} catch (Throwable h) {
			res = null;
			h.printStackTrace(System.out);
		}
		if (res != null && BuildConfig.DEBUG) {
			android.util.Log.d("YFDC", String.valueOf((Object) res));
		}
		return res;
	}

	public static UnpassedGrade getUnpassedBean(@NotNull final String json) {
		final String csc = (new StringBuilder(1024)).append("{\"def\":").append(json).append("}").toString();
		UnpassedGrade res;
		try {
			res = GsonFactory.makeNewGson().fromJson(csc, UnpassedGrade.class);
		} catch (Throwable h) {
			res = null;
			h.printStackTrace(System.out);
		}
		if (res != null && BuildConfig.DEBUG) {
			android.util.Log.d("YFDC", String.valueOf((Object) res));
		}
		return res;
	}
}
