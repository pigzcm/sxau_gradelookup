package yfdc
public final class Circle private constructor(rsd: Builder) : YfKtShape {
	@get:JvmName("radius") public var radius: Double = rsd.radius
	companion object {
		private const val MY_PI: Double = 3.14
	}
	override fun getArea(): Double = MY_PI * radius * radius
	override fun getParameter(): Double = 2.0 * MY_PI * radius
	class Builder @JvmOverloads constructor(
			@set:JvmName("setRadius") @get:JvmName("getRadius") var radius: Double = 0.0
	) {
		fun radius(radius: Double): Builder = apply {
			this.radius = radius
		}
		fun build(): Circle = Circle(this)
	}
	override fun toString(): String {
		val obj1 = org.json.JSONObject()
		val obj2 = org.json.JSONObject()
		return try {
			obj1.put("radius", this.radius)
			obj1.put("parameter", this.getParameter())
			obj1.put("area", this.getArea())
			obj2.put("circle", obj1)
			obj2.toString()
		} catch (_: Throwable) {
			super.toString()
		}
	}
}
