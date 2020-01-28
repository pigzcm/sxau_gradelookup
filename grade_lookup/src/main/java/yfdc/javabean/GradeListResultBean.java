package yfdc.javabean;
import org.jetbrains.annotations.NotNull;
import java.util.List;
public final class GradeListResultBean implements yfdc.JavaBean {
	@com.google.gson.annotations.Expose(serialize = false, deserialize = false) transient private static final long serialVersionUID = yfdc.JavaBean.serialVersionUID;

	private List<ChengjiListBean> cjList;
	private String xqwtg;
	private String xqzms;
	private String xqtgms;
	private String xqzxs;
	private String xqzxf;
	private String zxf;
	private String zxs;
	private String yqzxf;
	private String yqxf;
	private String yxxf;
	private String tgms;
	private String wtgms;
	private String zms;
	private String cjlx;
	private String cjbh;
	private String fajhwkcms;
	private String kznzms;
	private String fajhnkcms;
	private String kzxdms;
	private String kzwtgms;
	private String kztgms;
	private String fajhzxf;
	private String fajhzxs;
	private String fajhzms;
	private Object famc;
	private String zxjxjhh;
	public GradeListResultBean() {
		super();
	}
	public List<ChengjiListBean> getCjList() {
		return cjList;
	}
	public void setCjList(List<ChengjiListBean> _cjList) {
		this.cjList = _cjList;
	}
	public String getXqwtg() {
		return xqwtg;
	}	public void setXqwtg(String xqwtg) {
		this.xqwtg = xqwtg;
	}
	public String getXqzms() {
		return xqzms;
	}
	public void setXqzms(String xqzms) {
		this.xqzms = xqzms;
	}
	public String getXqtgms() {
		return xqtgms;
	}
	public void setXqtgms(String xqtgms) {
		this.xqtgms = xqtgms;
	}
	public String getXqzxs() {
		return xqzxs;
	}
	public void setXqzxs(String xqzxs) {
		this.xqzxs = xqzxs;
	}
	public String getXqzxf() {
		return xqzxf;
	}
	public void setXqzxf(String xqzxf) {
		this.xqzxf = xqzxf;
	}
	public String getZxf() {
		return zxf;
	}
	public void setZxf(String _zxf) {
		this.zxf = _zxf;
	}
	public String getZxs() {
		return zxs;
	}
	public void setZxs(String zxs) {
		this.zxs = zxs;
	}
	public String getYqzxf() {
		return yqzxf;
	}
	public void setYqzxf(String yqzxf) {
		this.yqzxf = yqzxf;
	}
	public String getYxxf() {
		return yxxf;
	}
	public void setYxxf(String yxxf) {
		this.yxxf = yxxf;
	}
	public String getTgms() {
		return tgms;
	}
	public void setTgms(String tgms) {
		this.tgms = tgms;
	}
	public String getWtgms() {
		return wtgms;
	}
	public void setWtgms(String wtgms) {
		this.wtgms = wtgms;
	}
	public String getZms() {
		return zms;
	}
	public void setZms(String zms) {
		this.zms = zms;
	}
	public String getCjlx() {
		return cjlx;
	}
	public void setCjlx(String cjlx) {
		this.cjlx = cjlx;
	}
	public String getCjbh() {
		return cjbh;
	}
	public void setCjbh(String cjbh) {
		this.cjbh = cjbh;
	}
	public String getFajhwkcms() {
		return fajhwkcms;
	}
	public void setFajhwkcms(String fajhwkcms) {
		this.fajhwkcms = fajhwkcms;
	}
	public String getKznzms() {
		return kznzms;
	}
	public void setKznzms(String kznzms) {
		this.kznzms = kznzms;
	}
	public String getFajhnkcms() {
		return fajhnkcms;
	}
	public void setFajhnkcms(String fajhnkcms) {
		this.fajhnkcms = fajhnkcms;
	}
	public String getKzxdms() {
		return kzxdms;
	}
	public void setKzxdms(String kzxdms) {
		this.kzxdms = kzxdms;
	}
	public String getKzwtgms() {
		return kzwtgms;
	}
	public void setKzwtgms(String kzwtgms) {
		this.kzwtgms = kzwtgms;
	}
	public String getKztgms() {
		return kztgms;
	}
	public void setKztgms(String kztgms) {
		this.kztgms = kztgms;
	}
	public String getFajhzxf() {
		return fajhzxf;
	}
	public void setFajhzxf(String fajhzxf) {
		this.fajhzxf = fajhzxf;
	}
	public String getFajhzxs() {
		return fajhzxs;
	}
	public void setFajhzxs(String fajhzxs) {
		this.fajhzxs = fajhzxs;
	}
	public String getFajhzms() {
		return fajhzms;
	}
	public void setFajhzms(String fajhzms) {
		this.fajhzms = fajhzms;
	}
	public Object getFamc() {
		return famc;
	}
	public void setFamc(Object _famc) {
		this.famc = _famc;
	}
	public String getZxjxjhh() {
		return zxjxjhh;
	}
	public void setZxjxjhh(String zxjxjhh) {
		this.zxjxjhh = zxjxjhh;
	}
	public String getYqxf() {
		return yqxf;
	}
	public void setYqxf(String _yqxf) {
		this.yqxf = _yqxf;
	}
	@NotNull @Override public String toString() {
		android.util.Log.d("abc",super.toString());
		return yfdc.JavaBean.getJavaBeanString(this);
	}
}
