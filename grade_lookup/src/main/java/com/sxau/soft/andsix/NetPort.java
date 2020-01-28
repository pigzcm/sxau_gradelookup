package com.sxau.soft.andsix;
public enum NetPort {
	port7872("7872", 7872), port7873("7873", 7873);
	public String port;
	public int index;
	private NetPort(final String _port,final int _index) {
		this.port = _port;
		this.index = _index;
	}
	public int getIndex() {
		return (this.index);
	}
	public String getPort() {
		return (this.port);
	}
}
