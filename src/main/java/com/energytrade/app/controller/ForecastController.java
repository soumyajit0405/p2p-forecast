package com.energytrade.app.controller;

import java.util.Hashtable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.energytrade.app.services.ForecastService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin
@RestController
public class ForecastController extends AbstractBaseController
{
    @Autowired
    private ForecastService forecastService;
    
    @RequestMapping(value =REST+"addForecast/{userId}" , method =  RequestMethod.POST , headers =  "Accept=application/json" )
    public HashMap<String,Object> addForeCast(@RequestHeader("auth-token") String authToken ,@RequestBody HashMap<String,Object> listOfData, @PathVariable("userId") int userId) {
        
    	HashMap<String,Object> response=new HashMap<String, Object>();
    	response.put("response", forecastService.addForeCast(listOfData, userId,authToken));
    	return response;
    }
    
    @RequestMapping(value =REST+"getForecastDetails/{userId}" , method =  RequestMethod.GET , headers =  "Accept=application/json" )
    public HashMap<String,Object> getForecastDetails(@PathVariable("userId") int userId) {
        
    	HashMap<String,Object> response=new HashMap<String, Object>();
    	response.put("response", forecastService.getForecastDetails(userId));
    	return response;
    }
    
    @RequestMapping(value =REST+"getForecastFromDevice/{usn}" , method =  RequestMethod.GET , headers =  "Accept=application/json" )
    public HashMap<String,Object> getForecastFromDevice(@PathVariable("usn") String usn) {
        
    	HashMap<String,Object> response=new HashMap<String, Object>();
    	response.put("response", forecastService.getForecastFromDevice(usn));
    	return response;
    }
    
}
