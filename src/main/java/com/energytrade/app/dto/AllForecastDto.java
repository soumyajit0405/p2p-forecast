package com.energytrade.app.dto;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AllForecastDto {

	public int getForecastId() {
		return forecastId;
	}

	public void setForecastId(int forecastId) {
		this.forecastId = forecastId;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private int forecastId;

	public List<HashMap<String, String>> getListOfUserDevices() {
		return listOfUserDevices;
	}

	public void setListOfUserDevices(List<HashMap<String, String>> listOfUserDevices) {
		this.listOfUserDevices = listOfUserDevices;
	}

	private double power;
	
	private double pricePerUnit;

	private Date forecastDate;
	
	private Timestamp startTime;
	
	private Timestamp endTime;

	private int userId;
	
	private String userName;
	
	private List<HashMap<String,String>> listOfUserDevices;
	
	private double solarPower;
	
	private double evpower;
	
	private double generatorPower;
	
	private double userLoad;

	public double getSolarPower() {
		return solarPower;
	}

	public void setSolarPower(double solarPower) {
		this.solarPower = solarPower;
	}

	public double getEvpower() {
		return evpower;
	}

	public void setEvpower(double evpower) {
		this.evpower = evpower;
	}

	public double getGeneratorPower() {
		return generatorPower;
	}

	public void setGeneratorPower(double generatorPower) {
		this.generatorPower = generatorPower;
	}

	public double getUserLoad() {
		return userLoad;
	}

	public void setUserLoad(double userLoad) {
		this.userLoad = userLoad;
	}

}