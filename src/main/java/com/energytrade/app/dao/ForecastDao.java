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
public class ForecastDao extends AbstractBaseDao
{
	@Autowired
    AllForecastRepository allforecastrepo;
	
	@Autowired
    HttpConnectorHelper httpconnectorhelper;
	
    
	
    public HashMap<String,Object> addForeCast(HashMap<String,Object> listOfData, int userId, String authToken) {
    
    	ValidationUtil validationutil = new ValidationUtil();
    	CommonUtility cutility=new CommonUtility();
    	HashMap<String,Object> response=new HashMap<String, Object>();
        try {
        int tokenStatus = validationutil.checkAuthentication(authToken);
        if(tokenStatus == 0) {
        	response.put("message",CustomMessages.getCustomMessages("TM"));
     	   response.put("key","500");
     	   return response;
        }
        else if(tokenStatus == -1) {
        	response.put("message",CustomMessages.getCustomMessages("TE"));
     	   response.put("key","500");
     	   return response;
        }
        int requestFormatStatus = validationutil.checkRequestFormat(listOfData);
        if(requestFormatStatus == -1) {
        	response.put("message",CustomMessages.getCustomMessages("BRQ"));
      	   response.put("key","500");
      	   return response;
        }
        List<HashMap<String,Object>> formattedData=cutility.convertData(listOfData);
        ArrayList<HashMap<String,String>> listOfFC = (ArrayList<HashMap<String,String>>) listOfData.get("data"); 
        List<AllTimeslot> alltimeslots=allforecastrepo.getAllTimeSlots();
			/*
			 * System.out.println(alltimeslots); Date today=new Date(); DateFormat df= new
			 * SimpleDateFormat("yyyy-MM-dd"); //String todate= df.format(today); String
			 * todate= (String) listOfData.get("forecast_date"); today= df.parse(todate);
			 * int count= allforecastrepo.getForeCastByUser(userId,today);
			 */AllUser user = allforecastrepo.getUserById(userId);
        HashMap<String,AllTimeslot> slotMap= getListOfSlots(alltimeslots);
      int iResponse=  1;//updateForecastData(formattedData, slotMap, user);
        if(iResponse == -1) {
        	response.put("message",CustomMessages.getCustomMessages("ISE"));
      	   response.put("key","500");
      	   return response;
        }
      /* if(count == 0) {
    	  int inResponse= createForeCast(listOfFC,slotMap,user,today);
    	  if(inResponse == -1) {
    		  response.put("message",CustomMessages.getCustomMessages("ISE"));
        	   response.put("key","500");
        	   return response;
    	  }
    	
       }
       else {
    	   int inResponse= updateForeCast(listOfFC, slotMap, user, today);
    	   if(inResponse == -1) {
     		  response.put("message",CustomMessages.getCustomMessages("ISE"));
         	   response.put("key","500");
         	   return response;
       }
    	*/   
       //}
   	response.put("message",CustomMessages.getCustomMessages("SUC"));
	   response.put("key","200");
	
        
        }
        catch (Exception e) {
            System.out.println("Error in checkExistence" + e.getMessage());
            e.printStackTrace();
            response.put("message",CustomMessages.getCustomMessages("ISE"));
     	   response.put("key","500");
           
        }
        return response;
    }
    
public HashMap<String,Object> getForecastDetails(int userId) {
    	
    	HashMap<String,Object> response=new HashMap<String, Object>();
    	List<AllForecastDto> listOfForecastdto = new ArrayList<AllForecastDto>();
    	
        try {
        Date fromDate = new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        String fromDates = sdf.format(fromDate);
        Calendar c  = Calendar.getInstance();
        c.setTime(fromDate);
        c.add(Calendar.DAY_OF_MONTH, 7);
        String toDates = sdf.format(c.getTime());
        System.out.println(sdf.parse(fromDates));
        System.out.println(sdf.parse(toDates));
         List<AllForecast> allforecast=allforecastrepo.getForeCastDetailsByUser(userId,sdf.parse(fromDates), sdf.parse(toDates));
       // List<AllForecast> allforecast=allforecastrepo.getForeCastDetailsByUser(userId);
        for(int i=0;i<allforecast.size();i++) {
        	AllForecastDto allforecastdto = new AllForecastDto();
        	allforecastdto.setEndTime(allforecast.get(i).getEndTime());
        	allforecastdto.setStartTime(allforecast.get(i).getStartTime());
        	allforecastdto.setForecastDate(allforecast.get(i).getForecastDate());
        	allforecastdto.setForecastId(allforecast.get(i).getForecastId());
        	allforecastdto.setPower(allforecast.get(i).getPower());
        	allforecastdto.setPricePerUnit(allforecast.get(i).getAllTimeSlot().getTimeslotrates().get(0).getRatePerUnit());
        	allforecastdto.setUserId(allforecast.get(i).getAllUser().getUserId());
        	allforecastdto.setUserName(allforecast.get(i).getAllUser().getFullName());
        	if (allforecast.get(i).getSolarPower() != null) {
        	allforecastdto.setSolarPower(allforecast.get(i).getSolarPower().doubleValue());
        	}
        	if (allforecast.get(i).getEvPower() != null) {
        	allforecastdto.setEvpower(allforecast.get(i).getEvPower().doubleValue());
        	}
        	if (allforecast.get(i).getGeneratorPower() != null) {
        	allforecastdto.setGeneratorPower(allforecast.get(i).getGeneratorPower().doubleValue());
        	}
        	if (allforecast.get(i).getUserLoad() != null) {
        	allforecastdto.setUserLoad(allforecast.get(i).getUserLoad().doubleValue());
        	}
//        	List<HashMap<String,String>> listOfDevices = new ArrayList<HashMap<String,String>>();
//        	List<Object[]> listOfUserDevices = allforecastrepo.getUserDevices(allforecast.get(i).getAllUser().getUserId());
//        	for ( int j=0;j<listOfUserDevices.size();j++) {
//        		HashMap<String,String> userDeviceDetails = new HashMap<String, String>();
//        		userDeviceDetails.put("userDeviceId", listOfUserDevices.get(j)[0].toString());
//        		userDeviceDetails.put("deviceTypeId", listOfUserDevices.get(j)[1].toString());
//        		userDeviceDetails.put("deviceTypeName", listOfUserDevices.get(j)[2].toString());
//        		listOfDevices.add(userDeviceDetails);
//        	}
//        	allforecastdto.setListOfUserDevices(listOfDevices);
        	listOfForecastdto.add(allforecastdto);
        }
       //}
   	response.put("listOfForecast",listOfForecastdto);
	   response.put("key","200");
	
        
        }
        catch (Exception e) {
            System.out.println("Error in checkExistence" + e.getMessage());
            e.printStackTrace();
            response.put("message",CustomMessages.getCustomMessages("ISE"));
     	   response.put("key","500");
           
        }
        return response;
    }
    
    
	/*
	 * public int createForeCast(List<HashMap<String,String>> listOfForecast,
	 * HashMap<String,Integer> slotMap, AllUser user, Date todate ) {
	 * 
	 * try { Date orgtodate = todate; BigDecimal evPower = null; BigDecimal
	 * powerLoad = null; int recordCount = allforecastrepo.getForeCastCount();
	 * List<AllForecast> alForeCastList = new ArrayList<AllForecast>(); for(int
	 * i=0;i<listOfForecast.size() ; i++) { HashMap<String,String> foreCastData =
	 * listOfForecast.get(i); todate = orgtodate; for(int j=1;j<=7;j++) {
	 * evPower=new BigDecimal(foreCastData.get("PpvD"+j)); powerLoad =new
	 * BigDecimal(foreCastData.get("PlD"+j)); AllForecast allforecast = new
	 * AllForecast(); allforecast.setAllUser(user);
	 * allforecast.setForecastId(recordCount+1); allforecast.setEvPower(evPower);
	 * allforecast.setSolarPower(evPower); allforecast.setGeneratorPower(evPower);
	 * allforecast.setUserLoad(powerLoad); allforecast.setForecastDate(todate);
	 * allforecast.setTimeslotId(slotMap.get(foreCastData.get("slot"))); todate =
	 * new Date(todate.getTime() + (1000 * 60 * 60 * 24));
	 * alForeCastList.add(allforecast); recordCount = recordCount + 1;
	 * 
	 * } allforecastrepo.saveAll(alForeCastList);
	 * 
	 * } } catch(Exception e) { e.printStackTrace(); return -1;
	 * 
	 * } return 1; }
	 */
    
    
	/*
	 * public int updateForeCast(List<HashMap<String,String>> listOfForecast,
	 * HashMap<String,Integer> slotMap, AllUser user, Date todate ) {
	 * 
	 * try { BigDecimal evPower = null; BigDecimal powerLoad = null; Date orgtodate=
	 * todate; int recordCount = allforecastrepo.getForeCastCount(); for(int
	 * i=0;i<listOfForecast.size() ; i++) { HashMap<String,String> foreCastData =
	 * listOfForecast.get(i); todate = orgtodate; for(int j=1;j<=7;j++) {
	 * evPower=new BigDecimal(foreCastData.get("PpvD"+j)); powerLoad =new
	 * BigDecimal(foreCastData.get("PlD"+j)); if(j == 7) { AllForecast allforecast =
	 * new AllForecast(); allforecast.setAllUser(user);
	 * allforecast.setForecastId(recordCount+1); allforecast.setEvPower(evPower);
	 * allforecast.setSolarPower(evPower); allforecast.setGeneratorPower(evPower);
	 * allforecast.setUserLoad(powerLoad); allforecast.setForecastDate(todate);
	 * allforecast.setTimeslotId(slotMap.get(foreCastData.get("slot"))); todate =
	 * new Date(todate.getTime() + (1000 * 60 * 60 * 24));
	 * allforecastrepo.save(allforecast); recordCount = recordCount + 1;
	 * 
	 * } else { allforecastrepo.updateForeCast(evPower, powerLoad, user.getUserId(),
	 * todate, slotMap.get(foreCastData.get("slot"))); todate = new
	 * Date(todate.getTime() + (1000 * 60 * 60 * 24));
	 * 
	 * }
	 * 
	 * 
	 * } } } catch(Exception e) { e.printStackTrace(); return -1; } return 1; }
	 */
    
    public HashMap<String,AllTimeslot> getListOfSlots(List<AllTimeslot> listOfSlots){
    	
    	HashMap<String,AllTimeslot> timeSlotMap = new HashMap<String, AllTimeslot>();
    	for(int i=0;i < listOfSlots.size() ; i++) {
    		timeSlotMap.put(listOfSlots.get(i).getTimeSlotName(), listOfSlots.get(i));
    	}
    	return timeSlotMap;
    }
    
    
	/*
	 * public int updateForecastData(List<HashMap<String,Object>> listOfForecast,
	 * HashMap<String,AllTimeslot> slotMap, AllUser user ) {
	 * 
	 * try { BigDecimal evPower = null, solarPower = null, genPower = null;
	 * BigDecimal powerLoad = null; Date tempDateStore = null; int recordCount =
	 * allforecastrepo.getForeCastCount(); List<AllForecast> alForeCastList = new
	 * ArrayList<AllForecast>(); for(int i=0;i<listOfForecast.size() ; i++) {
	 * HashMap<String,Object> foreCastData = listOfForecast.get(i); tempDateStore =
	 * (Date)foreCastData.get("date"); evPower=new
	 * BigDecimal((String)foreCastData.get("EV-POWER")); // Continue for Solar and
	 * Others solarPower=new BigDecimal((String)foreCastData.get("SOLAR-POWER"));
	 * genPower=new BigDecimal((String)foreCastData.get("GEN-POWER")); powerLoad
	 * =new BigDecimal((String)foreCastData.get("POWER-LOAD")); int count=
	 * allforecastrepo.getForeCastByUser(user.getUserId(), tempDateStore,
	 * slotMap.get((String)foreCastData.get("slot")).getTimeSlotId()); if(count ==
	 * 0) { AllForecast allforecast = new AllForecast();
	 * allforecast.setAllUser(user); allforecast.setForecastId(recordCount+1);
	 * allforecast.setEvPower(evPower); allforecast.setSolarPower(solarPower);
	 * allforecast.setGeneratorPower(genPower); allforecast.setUserLoad(powerLoad);
	 * allforecast.setForecastDate(tempDateStore); allforecast.setTimeslotId(
	 * slotMap.get((String)foreCastData.get("slot")));
	 * alForeCastList.add(allforecast); recordCount = recordCount + 1; } else {
	 * 
	 * allforecastrepo.updateForeCast(evPower, genPower,solarPower, powerLoad,
	 * user.getUserId(), tempDateStore,
	 * slotMap.get((String)foreCastData.get("slot")).getTimeSlotId()); }
	 * 
	 * } allforecastrepo.saveAll(alForeCastList); } catch(Exception e) {
	 * e.printStackTrace(); return -1;
	 * 
	 * } return 1; }
	 */
    
public Map<String,Object> getForecastFromDevice(String usn){

	Map<String,Object> responseFrombcnetwork = new HashMap<String, Object>();
	HashMap<String,Object> response= new HashMap<String, Object>();
	try {
		DeviceRepository devicerepo = allforecastrepo.getDeviceUrl(usn);
		AllUser alluser = allforecastrepo.getUserIdByUSN(usn);
		JSONObject input = CommonUtility.prepareInput("date", "currentDate");
		 final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
		  final long HOUR = 3600*1000; // in milli-seconds.
		  final long HALFHOUR = 1800*1000;
	        Date now=new Date(new Date().getTime() +5*HOUR+HALFHOUR  );
	        System.out.println("input "+input +" "+usn );
	        
	        
		Calendar c = Calendar.getInstance(); 
		c.setTime(now); 
		c.add(Calendar.DATE, 1);
		now = c.getTime();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String formatted = sdf1.format(now);
		//deleteForecast(sdf1.parse(formatted), alluser.getUserId());
		 responseFrombcnetwork = httpconnectorhelper.sendPost(devicerepo.getUrl()+"/agent/fetchForecastData",input);	
		 insertIntoForecast(responseFrombcnetwork, alluser);
			response.put("responseStatus", "1");
			response.put("responseMessage", "The request was successfully served.");
			response.put("response", null);
			response.put("customMessage", null);
			// createEventSetObjects(file);
			// Create Workbook instance holding reference to .xlsx file

		} catch (Exception e) {
			e.printStackTrace();
			response.put("responseStatus", "2");
			response.put("responseMessage", "Internal Server Error.");
			response.put("customMessage", null);
			response.put("response", null);
		} finally {

		}
		return response;

	}
	

public void insertIntoForecast(Map<String,Object> data, AllUser alluser){
	
	try {	
		DBUtil dbutil = new DBUtil();
		int count = allforecastrepo.getForeCastCount();
		Date today = new Date();
		ArrayList<Integer> timeSlotsList = new ArrayList<>();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        String toDates = sdf.format(today);
	BigDecimal power ;
		ArrayList<HashMap<String,Object>> listOfForecasts= (ArrayList<HashMap<String,Object>>)(data.get("forcastInfo"));
		
		Map<Integer,AllForecast> foreCastMap = new HashMap<Integer, AllForecast>();
		for (int i=0;i<listOfForecasts.size();i++) {
			count++;
			HashMap<String,Object> forecastdata = (HashMap<String,Object>) listOfForecasts.get(i);
		AllForecast allforecast = new AllForecast();
		System.out.println("time stamp"+(String)forecastdata.get("timestamp"));
		
		if(((String)forecastdata.get("timestamp")).equalsIgnoreCase("23:45-24:00")) {
			forecastdata.put("timestamp","23:45-00:00");
		} else if(((String)forecastdata.get("timestamp")).equalsIgnoreCase("13:15-14:30")) {
			forecastdata.put("timestamp","13:15-13:30");
		}  else if(((String)forecastdata.get("timestamp")).equalsIgnoreCase("20:00.-20:15")) {
			forecastdata.put("timestamp","20:00-20:15");
		}
		AllTimeslot alltimeslot=allforecastrepo.getAllTimeSlots((String)forecastdata.get("timestamp"));
		TimeslotRates timeslotrate= allforecastrepo.getTimeSlotrate(alltimeslot.getTimeSlotId());
		
		try {
		power = new BigDecimal(((double)forecastdata.get("forecast_info1")));
		} catch(ClassCastException e) {
			power = new BigDecimal((int)forecastdata.get("forecast_info1"));
		}	
		
		if (!timeSlotsList.contains(alltimeslot.getTimeSlotId())) {
			timeSlotsList.add(alltimeslot.getTimeSlotId());
			String dates[]=((String)forecastdata.get("date")).split("T");
			Date forecastDate=sdf.parse(dates[0]);
			if (((String)forecastdata.get("meter_type")).equalsIgnoreCase("PV")) {
				allforecast.setSolarPower(power);
			} else if (((String)forecastdata.get("meter_type")).equalsIgnoreCase("ExternalGenerator")) {
				allforecast.setGeneratorPower(power);
				//System.out.println("");
			} else if (((String)forecastdata.get("meter_type")).equalsIgnoreCase("Battery")) {
				allforecast.setEvPower(power);
				System.out.println("");
			} else if (((String)forecastdata.get("meter_type")).equalsIgnoreCase("EV")) {
				allforecast.setEvPower(power);
				System.out.println("");
			} else if (((String)forecastdata.get("meter_type")).equalsIgnoreCase("Loadmeter")) {
				allforecast.setUserLoad(power);
				System.out.println("");
			}
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String timeSlots[]=((String)forecastdata.get("timestamp")).split("-");
			Date startTime=sdf1.parse(dates[0]+" "+timeSlots[0]+":00");
			Timestamp sts= new java.sql.Timestamp(startTime.getTime());
			Date endTime=sdf1.parse(dates[0]+" "+timeSlots[1]+":00");
			Timestamp ets= new java.sql.Timestamp(endTime.getTime());
			allforecast.setAllTimeSlot(alltimeslot);
			//allforecast.setForecastDate(forecastDate);
			allforecast.setForecastDate(today);
			allforecast.setForecastId(count);
			allforecast.setStartTime(sts);
			allforecast.setEndTime(ets);
			allforecast.setPricePerUnit(timeslotrate.getRatePerUnit());
			allforecast.setAllUser(alluser);
			foreCastMap.put(alltimeslot.getTimeSlotId(), allforecast);
			} else {
				//AllForecast af = (AllForecast) foreCastMap.get(alltimeslot.getTimeSlotId());
				if (((String)forecastdata.get("meter_type")).equalsIgnoreCase("PV")) {
					 foreCastMap.get(alltimeslot.getTimeSlotId()).setSolarPower(power);
				} else if (((String)forecastdata.get("meter_type")).equalsIgnoreCase("ExternalGenerator")) {
					foreCastMap.get(alltimeslot.getTimeSlotId()).setGeneratorPower(power);
					//System.out.println("");
				} else if (((String)forecastdata.get("meter_type")).equalsIgnoreCase("Battery")) {
					foreCastMap.get(alltimeslot.getTimeSlotId()).setEvPower(power);
					System.out.println("");
				} else if (((String)forecastdata.get("meter_type")).equalsIgnoreCase("EV")) {
					foreCastMap.get(alltimeslot.getTimeSlotId()).setEvPower(power);
					System.out.println("");
				} else if (((String)forecastdata.get("meter_type")).equalsIgnoreCase("Loadmeter")) {
					foreCastMap.get(alltimeslot.getTimeSlotId()).setUserLoad(power);
					System.out.println("");
				}
			}
		
		}
		List<AllForecast> listOFAlForeCast = new ArrayList<AllForecast>(foreCastMap.values());
		allforecastrepo.saveAll(listOFAlForeCast);
	}
	catch(Exception e) {
		e.printStackTrace();
	}
    
}

public void deleteForecast(Date date, int userId){
	
	try {	
		 allforecastrepo.deleteForecast(date,userId);
		}
	catch(Exception e) {
		e.printStackTrace();
	}
    
}


public void updateForecastData(String meterType, BigDecimal power, int userId, Date today, int timeSlotId ) {
	
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

