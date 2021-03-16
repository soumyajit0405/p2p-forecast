package com.energytrade.app.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.energytrade.app.dao.ForecastDao;


@Service("forecastService")
public class ForecastService extends AbstractBaseService
{
    @Autowired
    private ForecastDao forecastdao;
    
    public HashMap<String,Object> addForeCast(HashMap<String,Object> listOfData, int userId, String authToken) {
        return this.forecastdao.addForeCast(listOfData, userId, authToken);
    }
    
    public HashMap<String,Object> getForecastDetails(int userId) {
        return this.forecastdao.getForecastDetails(userId);
    }
        
    public Map<String,Object> getForecastFromDevice(String usn){
    	return this.forecastdao.getForecastFromDevice(usn);
    }
    
}