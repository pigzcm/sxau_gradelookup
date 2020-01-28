package yfdc;
import androidx.annotation.Keep;
public final class Test {
	@Keep private Test() throws Exception {
		super();
		throw (new Exception());
	}
	public static void fun1(int a, int b, int c) {
		try {
			sleep1();
			android.util.Log.d("ppp", native1());
			Circle crl = (new Circle.Builder()).radius(c).build();
			Circle cr1 = (new Circle.Builder(b)).build();
			Rectangle rcl = (new Rectangle.Builder()).width(a).height(b).build();
			Rectangle rc1 = (new Rectangle.Builder(5)).height(7).build();
			Rectangle.Builder xs = null;
			android.util.Log.d("YFDC:crl", crl.toString());
			android.util.Log.d("YFDC:cr1", cr1.toString());
			android.util.Log.d("YFDC:rcl", rcl.toString());
			android.util.Log.d("YFDC:rc1", rc1.toString());
			xs = (new Rectangle.Builder());
			xs.setHeight(0);
			xs.setWidth(0);
			xs.width(3).width(4).height(a).height(c);
			final Rectangle gho = xs.build();
			android.util.Log.d("YFDC:gho", gho.toString());
			YfKtShape.DefImpl impl = (new YfKtShape.DefImpl("zc" + b, a));
			YfKtShape.DefImpl imp1 = (new YfKtShape.DefImpl("zc" + c, null));
			android.util.Log.d("YFDC:imp1", "" + imp1.yfgetstr() + "," + imp1.yfgetfield());
			android.util.Log.d("YFDC:impl", "" + impl.yfgetstr() + "," + impl.yfgetfield());
		} catch (Throwable s) {
			GsonFactory.logstacktrace(GsonFactory.stacktrace(s));
		}
	}
	public static native void sleep1();
	public static native String native1();
}
