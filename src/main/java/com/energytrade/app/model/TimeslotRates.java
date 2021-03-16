package com.energytrade.app.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the all_timeslots database table.
 * 
 */
@Entity
@Table(name="timeslot_rates")
@NamedQuery(name="TimeslotRates.findAll", query="SELECT a FROM TimeslotRates a")
public class TimeslotRates implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="timeslot_rate_id")
	private int timeSlotId;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	private byte softdeleteflag;
	
	private double ratePerUnit;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="sync_ts")
	private Date syncTs;

	//bi-directional many-to-one association to AllUser
	@ManyToOne
	@JoinColumn(name="timeslot_id")
	private AllTimeslot allTimeSlot;

	@Column(name="updated_by")
	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_ts")
	private Date updatedTs;

	public TimeslotRates() {
		// TODO Auto-generated constructor stub
	}
	
	public int getTimeSlotId() {
		return this.timeSlotId;
	}

	public void setTimeSlotId(int timeSlotId) {
		this.timeSlotId = timeSlotId;
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

		public double getRatePerUnit() {
		return ratePerUnit;
	}

	public void setRatePerUnit(double ratePerUnit) {
		this.ratePerUnit = ratePerUnit;
	}

	public AllTimeslot getAllTimeSlot() {
		return allTimeSlot;
	}

	public void setAllTimeSlot(AllTimeslot allTimeSlot) {
		this.allTimeSlot = allTimeSlot;
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

}