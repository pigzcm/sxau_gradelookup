package yfdc;
import androidx.annotation.Keep;
import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;
public final class GsonFactory extends yfdc.Factory.FactImpl {
	private static final Gson gson = (new Gson());
	@Keep private GsonFactory() throws RuntimeException {
		RuntimeException exception = new RuntimeException("GsonFactory constructor should not be called.");
		android.util.Log.e("ERR", exception.toString());
		throw exception;
	}
	public static com.google.gson.Gson makeNewGson() {
		return gson;
	}
	public synchronized static String stacktrace(@NotNull final Throwable exception) {
		final java.io.StringWriter stw = new java.io.StringWriter();
		final java.io.PrintWriter ptw = new java.io.PrintWriter(stw, true);
		exception.printStackTrace(ptw);
		final String rtn = stw.toString();
		try {
			stw.flush();
			ptw.flush();
			stw.close();
			ptw.close();
		} catch (Throwable h) {
			h.printStackTrace(System.out);
		}
		return rtn;
	}
	public static void logstacktrace(String trace) {
		if (trace == null || trace.length() == 0) return;
		String[] list = trace.split("[\r\n]{1,2}");
		for (final String s : list) {
			android.util.Log.d("YFException", s);
		}
	}
}
