package com.kedzie.vbox.api;

public interface IPerformanceMetric extends IRemoteObject {
	public String getName();
	public String getDescription();
	public Integer getMinimumValue();
	public Integer getMaximumValue();
	public String getUnit();
	public String getObject();
}
