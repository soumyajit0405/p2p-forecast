package com.energytrade.app.dao;

import org.springframework.data.jpa.repository.Modifying;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.energytrade.app.model.AllElectricityBoard;
import com.energytrade.app.model.AllForecast;
import com.energytrade.app.model.AllOtp;
import com.energytrade.app.model.AllState;
import com.energytrade.app.model.AllTimeslot;
import com.energytrade.app.model.AllUser;
import com.energytrade.app.model.DeviceRepository;
import com.energytrade.app.model.StateBoardMapping;
import com.energytrade.app.model.TimeslotRates;
import com.energytrade.app.model.UserRolesPl;
import com.energytrade.app.model.UserTypePl;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AllForecastRepository extends JpaRepository<AllForecast, Long>
{
    
	
	  @Query("Select a from AllTimeslot a ") 
	  List<AllTimeslot> getAllTimeSlots( );
	  
	  @Query("Select a from AllUser a where a.userId=?1 ") 
	  AllUser getUserById( int userId);
	  
	  @Query("Select a from AllTimeslot a where a.timeSlotName=?1") 
	  AllTimeslot getAllTimeSlots( String timeSlotName);
	  
	  @Query("Select a from TimeslotRates a where a.allTimeSlot.timeSlotId=?1") 
	  TimeslotRates getTimeSlotrate( int timeSlotId);
	  
	/*
	 * @Query("Select count(a) from AllForecast a where a.allUser.userId=?1 and a.forecastDate=?2"
	 * ) int getForeCastByUser( int userId,Date date);
	 */
//	  @Modifying
//	  @Query("update AllForecast a set a.evPower=?1,a.generatorPower=?2,a.solarPower=?3,a.userLoad=?4 where a.allUser.userId=?5 and a.forecastDate=?6 and a.timeslot.timeSlotId=?7") 
//	  void updateForeCast(BigDecimal power,BigDecimal genPower,BigDecimal solarPower,BigDecimal load, int userId, Date date, int slotId);
//	  
	  @Query("Select COALESCE(max(a.forecastId),0) from AllForecast a ") 
	  int getForeCastCount( );
	  
	/*
	 * @Query("Select count(a) from AllForecast a where a.allUser.userId=?1 and a.forecastDate=?2  and a.timeslot.timeSlotId=?3"
	 * ) int getForeCastByUser( int userId,Date date, int slotId);
	 */
	  @Query("Select a from AllForecast a where a.allUser.userId=?1 and a.forecastDate >=?2 and a.forecastDate<=?3 ") 
	  List<AllForecast> getForeCastDetailsByUser( int userId, Date fromDate, Date ToDate);
	  
	  @Query("Select a from AllForecast a where a.allUser.userId=?1 order by startTime") 
	  List<AllForecast> getForeCastDetailsByUser( int userId);
	  
	  @Query("select a.userDeviceId,b.deviceTypeId,b.deviceTypeName from UserDevice a , DevicePl b where a.allUser.userId = ?1 " + 
	  		"and a.devicePl.deviceTypeId = b.deviceTypeId")
	  List<Object[]> getUserDevices( int userId);
	  
	  @Query("Select a from DeviceRepository a where a.usn =?1") 
	  DeviceRepository getDeviceUrl(String usn );
	  
	  @Query("Select a from AllUser a where a.uniqueServiceNumber=?1")
	    AllUser getUserIdByUSN(String usn );
	  
	  @Modifying
	  @Query("delete from AllForecast a  where a.forecastDate=?1 and a.allUser.userId=?2")
	    void deleteForecast(Date date, int userId );
	  
	  @Modifying
	  @Query("update AllForecast a  set a.evPower =?1 where a.forecastDate=?2 and a.allUser.userId=?3 and  a.allTimeSlot.timeSlotId =?4")
	    void updateEv(BigDecimal power, Date date, int userId , int timeslotId);
	  
	  @Modifying
	  @Query("update AllForecast a  set a.userLoad =?1 where a.forecastDate=?2 and a.allUser.userId=?3 and  a.allTimeSlot.timeSlotId =?4")
	    void updateLoad(BigDecimal power,Date date, int userId , int timeslotId);
	  
	  @Modifying
	  @Query("update AllForecast a  set a.solarPower =?1 where a.forecastDate=?2 and a.allUser.userId=?3 and  a.allTimeSlot.timeSlotId =?4")
	    void updateSolar(BigDecimal power,Date date, int userId , int timeslotId);
	  
	  @Modifying
	  @Query("update AllForecast a  set a.generatorPower =?1 where a.forecastDate=?2 and a.allUser.userId=?3 and  a.allTimeSlot.timeSlotId =?4")
	    void updateExGen(BigDecimal power,Date date, int userId , int timeslotId);
	  
	  
	  
	  @Query("Select a from AllForecast a where a.allUser.userId=:userId and a.forecastDate =:date and a.allTimeSlot.timeSlotId =:timeslotId") 
	  AllForecast getForeCast( @Param("userId") int userId, @Param("date") Date date,@Param("timeslotId") int timeslotId);
        
}