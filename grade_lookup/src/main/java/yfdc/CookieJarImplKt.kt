package yfdc
public final object CookieJarImplKt : okhttp3.CookieJar {
	@JvmStatic @JvmName(name = "getInstance") fun getInstance(): CookieJarImplKt {
		return this
	}
	private final val cookieStore: HashMap<String, List<okhttp3.Cookie>> = HashMap<String, List<okhttp3.Cookie>>();
	init {
		val uu: String = (CookieJarImplKt::class.java).toString()
		android.util.Log.d("ROM", "condition:$uu")
		clearCache()
	}
	@JvmName(name = "clearCache") @JvmStatic fun clearCache() {
		cookieStore.clear()
	}
	override fun loadForRequest(url: okhttp3.HttpUrl): List<okhttp3.Cookie> {
		val kop: List<okhttp3.Cookie>? = (cookieStore[url.host])
		return kop ?: (ArrayList<okhttp3.Cookie>())
	}
	override fun saveFromResponse(url: okhttp3.HttpUrl, cookies: List<okhttp3.Cookie>) {
		cookieStore[url.host] = cookies
	}
	override fun toString(): String {
		var gh1: String = super.toString()
		val obj = com.google.gson.JsonObject()
		return try {
			gh1 = yfdc.GsonFactory.makeNewGson().toJson(cookieStore, (Map::class.java as java.lang.reflect.Type))
			val vag1 = com.google.gson.JsonParser().parse(gh1).asJsonObject
			obj.add("cookiestore", vag1)
			obj.toString()
		} catch (i_ignore: Throwable) {
			gh1
		}
	}
	override fun equals(other: Any?): Boolean {
		if (other is CookieJarImplKt) {
			android.util.Log.d("yfdc", "ygh:$other")
		}
		//the only instance can not call this method
		return false
	}
	override fun hashCode(): Int {
		return 0
	}
}
