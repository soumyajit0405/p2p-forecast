package com.energytrade.app.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the user_devices database table.
 * 
 */
@Entity
@Table(name="user_devices")
@NamedQuery(name="UserDevice.findAll", query="SELECT u FROM UserDevice u")
public class UserDevice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_device_id")
	private int userDeviceId;

	@Column(name="active_status")
	private byte activeStatus;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Column(name="device_capacity")
	private BigDecimal deviceCapacity;
	
	@Column(name="forecast_enabled")
	private String forecastEnabled;
	
	@Column(name="data_acquisition_enabled")
	private String dataAcquistionEnabled;

	public String getForecastEnabled() {
		return forecastEnabled;
	}

	public void setForecastEnabled(String forecastEnabled) {
		this.forecastEnabled = forecastEnabled;
	}

	public String getDataAcquistionEnabled() {
		return dataAcquistionEnabled;
	}

	public void setDataAcquistionEnabled(String dataAcquistionEnabled) {
		this.dataAcquistionEnabled = dataAcquistionEnabled;
	}

	private byte softdeleteflag;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="sync_ts")
	private Date syncTs;

	@Column(name="updated_by")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_ts")
	private Date updatedTs;

	@ManyToOne
	@JoinColumn(name="user_id")
	private AllUser allUser;

	//bi-directional many-to-one association to DevicePl
	@ManyToOne
	@JoinColumn(name="device_type_id")
	private DevicePl devicePl;
	
	@Column(name="meter_id")
	private int meterId;
	
	@Column(name="device_name")
	private String deviceName;
	
	@Column(name="meter_type")
	private String meterType;
	
	@Column(name="meter_model_number")
	private String meterModelNumber;
	
	@Column(name="port_number")
	private String portNumber;

	public int getMeterId() {
		return meterId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getMeterType() {
		return meterType;
	}

	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}

	public String getMeterModelNumber() {
		return meterModelNumber;
	}

	public void setMeterModelNumber(String meterModelNumber) {
		this.meterModelNumber = meterModelNumber;
	}

	public String getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}

	public void setMeterId(int meterId) {
		this.meterId = meterId;
	}

	public UserDevice() {
	}

	public int getUserDeviceId() {
		return this.userDeviceId;
	}

	public void setUserDeviceId(int userDeviceId) {
		this.userDeviceId = userDeviceId;
	}

	public AllUser getAlLUser() {
		return allUser;
	}

	public void setAllUser(AllUser allUser) {
		this.allUser = allUser;
	}

	public byte getActiveStatus() {
		return this.activeStatus;
	}

	public void setActiveStatus(byte activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTs() {
		return this.createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	public BigDecimal getDeviceCapacity() {
		return this.deviceCapacity;
	}

	public void setDeviceCapacity(BigDecimal deviceCapacity) {
		this.deviceCapacity = deviceCapacity;
	}

	public byte getSoftdeleteflag() {
		return this.softdeleteflag;
	}

	public void setSoftdeleteflag(byte softdeleteflag) {
		this.softdeleteflag = softdeleteflag;
	}

	public Date getSyncTs() {
		return this.syncTs;
	}

	public void setSyncTs(Date syncTs) {
		this.syncTs = syncTs;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTs() {
		return this.updatedTs;
	}

	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}

	public DevicePl getDevicePl() {
		return this.devicePl;
	}

	public void setDevicePl(DevicePl devicePl) {
		this.devicePl = devicePl;
	}

}