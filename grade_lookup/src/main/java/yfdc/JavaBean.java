package yfdc;
import org.jetbrains.annotations.NotNull;
import com.google.gson.annotations.Expose;
public interface JavaBean extends java.io.Serializable {
	@Expose(serialize = false, deserialize = false)
	public static final long serialVersionUID = 0L;
	public static String getJavaBeanString(@NotNull final JavaBean object) {
		com.google.gson.JsonObject obj = new com.google.gson.JsonObject();
		Class<? extends JavaBean> cls = object.getClass();
		String jsonstr = "{\"object\":\"<error>\"}";
		String rtn = null;
		try {
			jsonstr = GsonFactory.makeNewGson().toJson(object, cls);
			final com.google.gson.JsonObject asd = (new com.google.gson.JsonParser()).parse(jsonstr).getAsJsonObject();
			obj.add(cls.getName(), asd);
			rtn = obj.toString();
		} catch (Throwable tx) {
			tx.printStackTrace(System.out);
			rtn = jsonstr;
		}
		return rtn;
	}
}
