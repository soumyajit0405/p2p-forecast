package com.energytrade.app.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.energytrade.app.dto.AllForecastDto;
import com.energytrade.app.model.AllForecast;
import com.energytrade.app.model.AllTimeslot;
import com.energytrade.app.model.AllUser;
import com.energytrade.app.model.DeviceRepository;
import com.energytrade.app.model.TimeslotRates;
import com.energytrade.app.util.CommonUtility;
import com.energytrade.app.util.CustomMessages;
import com.energytrade.app.util.HttpConnectorHelper;
import com.energytrade.app.util.ValidationUtil;

@Transactional
@Repository
public class DBUtil extends AbstractBaseDao
{
	
 
public void updateForecastData(AllForecastRepository allforecastrepo,String meterType, BigDecimal power, int userId, Date today, int timeSlotId ) {
	
	if ((meterType).equalsIgnoreCase("PV")) {
		allforecastrepo.updateSolar(power ,today, userId,timeSlotId);
	} else if ((meterType).equalsIgnoreCase("ExternalGenerator")) {
		allforecastrepo.updateExGen(power ,today, userId, timeSlotId);
		//System.out.println("");
	} else if ((meterType).equalsIgnoreCase("Battery")) {
		allforecastrepo.updateEv(power ,today, userId, timeSlotId);
		System.out.println("");
	} else if ((meterType).equalsIgnoreCase("EV")) {
		allforecastrepo.updateEv(power ,today, userId, timeSlotId);
		System.out.println("");
	} else if ((meterType).equalsIgnoreCase("Loadmeter")) {
		allforecastrepo.updateLoad(power ,today, userId, timeSlotId);
		System.out.println("");
	}
}
}

