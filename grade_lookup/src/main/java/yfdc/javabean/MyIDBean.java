package yfdc.javabean;
import org.jetbrains.annotations.NotNull;
import com.google.gson.annotations.Expose;
public final class MyIDBean implements yfdc.JavaBean {
	@Expose(serialize = false, deserialize = false) transient private static final long serialVersionUID = yfdc.JavaBean.serialVersionUID;

	private String executiveEducationPlanNumber;
	private String courseNumber;
	private String startTime;
	private String studentId;
	private String coureSequenceNumber;
	private String kch_zj;
	public MyIDBean() {
		super();
	}
	public String getExecutiveEducationPlanNumber() {
		return this.executiveEducationPlanNumber;
	}
	public void setExecutiveEducationPlanNumber(String _val) {
		this.executiveEducationPlanNumber = _val;
	}
	public String getCourseNumber() {
		return courseNumber;
	}
	public void setCourseNumber(String _courseNumber) {
		this.courseNumber = _courseNumber;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String _startTime) {
		this.startTime = _startTime;
	}
	public String getStudentId() {
		return this.studentId;
	}
	public void setStudentId(String _studentId) {
		this.studentId = _studentId;
	}
	public String getCoureSequenceNumber() {
		return coureSequenceNumber;
	}
	public void setCoureSequenceNumber(String coureSequenceNumber) {
		this.coureSequenceNumber = coureSequenceNumber;
	}
	public String getKch_zj() {
		return kch_zj;
	}
	public void setKch_zj(String _kch_zj) {
		this.kch_zj = _kch_zj;
	}
	@NotNull @Override public String toString() {
		android.util.Log.d("abc",super.toString());
		return yfdc.JavaBean.getJavaBeanString(this);
	}
}
