package com.dl.code.utils;




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class DateUtil {
	public static Date convert(String time) {
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	Date date=null;
	try {
		date=sdf.parse(time);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	java.sql.Date dateTime=new java.sql.Date(date.getTime());
	return dateTime;

}}
