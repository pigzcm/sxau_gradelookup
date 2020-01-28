package yfdc
interface YfKtShape {
	fun getArea(): Double;
	fun getParameter(): Double;
	open class DefImpl @JvmOverloads public constructor(strf:String?=null,intef:Int?=null): java.io.Serializable ,YfKtShape{
		@get:JvmName("yfgetstr")
		@set:JvmName("yfsetstr")
		public var str: String? = strf
			get() = (field ?: "<null>")
			set(value) {
				val bbs: String = value ?: "<null>"
				field = bbs
			}
		@get:JvmName("yfgetfield")
		@set:JvmName("yfsetfield")
		var field:Int? = intef
		get() = field?:0
		set(value) {
			val rtn:Int = value?:0
			field = rtn
		}
		override fun getArea(): Double = (0).toDouble()
		override fun getParameter(): Double = (0).toDouble()
	}
}
