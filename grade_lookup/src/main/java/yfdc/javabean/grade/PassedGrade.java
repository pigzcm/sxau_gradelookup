package yfdc.javabean.grade;
import org.jetbrains.annotations.NotNull;
import com.google.gson.annotations.Expose;
import yfdc.javabean.AbstractBean;
public final class PassedGrade extends AbstractBean {
	@Expose(serialize = false, deserialize = false)
	transient private static final long serialVersionUID = yfdc.JavaBean.serialVersionUID;
	private java.util.List<yfdc.javabean.GradeListResultBean> lnList = null;
	private String state;
	private String zxjxjhh;
	public PassedGrade(){
		super();
	}
	public String getState() {
		return (this.state == null) ? "<null>" : this.state;
	}
	public java.util.List<yfdc.javabean.GradeListResultBean> getList() {
		return this.lnList;
	}
	@NotNull @Override public String toString() {
		android.util.Log.d("abc",super.toString());
		return yfdc.JavaBean.getJavaBeanString(this);
	}
}
