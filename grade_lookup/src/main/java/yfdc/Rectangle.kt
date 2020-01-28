package yfdc
public final class Rectangle private constructor(sfs: Builder) : YfKtShape {
	@get:JvmName("width") var width: Double = sfs.width
	@get:JvmName("height") var height: Double = sfs.height
	override fun getArea(): Double = width * height
	override fun getParameter(): Double = ((width + height) * 2.0)
	class Builder @JvmOverloads constructor(
			@set:JvmName("setWidth") @get:JvmName("getWidth") var width: Double = 0.0,
			@set:JvmName("setHeight") @get:JvmName("getHeight") var height: Double = 0.0
	) {
		fun width(width: Double): Builder = apply {
			this.width = width
		}
		fun height(height: Double): Builder = apply {
			this.height = height
		}
		fun build(): Rectangle = Rectangle(this)
	}
	override fun toString(): String {
		val obj1 = org.json.JSONObject()
		val obj2 = org.json.JSONObject()
		return try {
			obj1.put("width", this.width)
			obj1.put("height", this.height)
			obj1.put("parameter", this.getParameter())
			obj1.put("area", this.getArea())
			obj2.put("rectangle", obj1)
			obj2.toString()
		} catch (_: Throwable) {
			super.toString()
		}
	}
}
