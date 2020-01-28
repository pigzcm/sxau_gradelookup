package yfdc.javabean;
import com.google.gson.annotations.Expose;
import org.jetbrains.annotations.NotNull;
public abstract class AbstractBean extends java.lang.Object implements yfdc.JavaBean {
	@Expose(serialize = false, deserialize = false) private final transient yfdc.JavaBean bean;
	@Expose(serialize = false, deserialize = false) transient private static final long serialVersionUID = yfdc.JavaBean.serialVersionUID;
	public AbstractBean() {
		super();
		this.bean = this;
	}
	@NotNull public String toString() {
		return yfdc.JavaBean.getJavaBeanString(this.bean);
	}
}
