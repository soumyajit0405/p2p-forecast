package com.energytrade.app.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the all_forecasts database table.
 * 
 */
@Entity
@Table(name="all_forecast")
@NamedQuery(name="AllForecast.findAll", query="SELECT a FROM AllForecast a")
public class AllForecast implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="forecast_id")
	private int forecastId;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	@Column(name="power")
	private double power;
	
	@Column(name="price_per_unit")
	private double pricePerUnit;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="forecast_date")
	private Date forecastDate;

	private byte softdeleteflag;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="sync_ts")
	private Date syncTs;

	@Column(name="updated_by")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_ts")
	private Date updatedTs;
	
	
	@Column(name="start_time")
	private Timestamp startTime;
	
	
	@Column(name="end_time")
	private Timestamp endTime;

	//bi-directional many-to-one association to AllUser
	@ManyToOne
	@JoinColumn(name="user_id")
	private AllUser allUser;
	
	//bi-directional many-to-one association to AllUser
		@ManyToOne
		@JoinColumn(name="timeslot_id")
		private AllTimeslot allTimeSlot;

	
	public AllTimeslot getAllTimeSlot() {
			return allTimeSlot;
		}

		public void setAllTimeSlot(AllTimeslot allTimeSlot) {
			this.allTimeSlot = allTimeSlot;
		}

	@Column(name="ev_power")
	private BigDecimal evPower;
	
	@Column(name="user_load")
	private BigDecimal userLoad;
	
	public BigDecimal getEvPower() {
		return evPower;
	}

	public void setEvPower(BigDecimal evPower) {
		this.evPower = evPower;
	}

	public BigDecimal getUserLoad() {
		return userLoad;
	}

	public void setUserLoad(BigDecimal userLoad) {
		this.userLoad = userLoad;
	}

	public BigDecimal getSolarPower() {
		return solarPower;
	}

	public void setSolarPower(BigDecimal solarPower) {
		this.solarPower = solarPower;
	}

	public BigDecimal getGeneratorPower() {
		return generatorPower;
	}

	public void setGeneratorPower(BigDecimal generatorPower) {
		this.generatorPower = generatorPower;
	}

	@Column(name="solar_power")
	private BigDecimal solarPower;


	@Column(name="generator_power")
	private BigDecimal generatorPower;


	public AllForecast() {
	}

	public int getForecastId() {
		return this.forecastId;
	}

	public void setForecastId(int forecastId) {
		this.forecastId = forecastId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public Date getForecastDate() {
		return forecastDate;
	}

	public void setForecastDate(Date forecastDate) {
		this.forecastDate = forecastDate;
	}

	public byte getSoftdeleteflag() {
		return softdeleteflag;
	}

	public void setSoftdeleteflag(byte softdeleteflag) {
		this.softdeleteflag = softdeleteflag;
	}

	public Date getSyncTs() {
		return syncTs;
	}

	public void setSyncTs(Date syncTs) {
		this.syncTs = syncTs;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTs() {
		return updatedTs;
	}

	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public AllUser getAllUser() {
		return allUser;
	}

	public void setAllUser(AllUser allUser) {
		this.allUser = allUser;
	}

}