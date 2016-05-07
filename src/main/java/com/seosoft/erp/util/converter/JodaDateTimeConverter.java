package com.seosoft.erp.util.converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import com.sun.faces.util.MessageFactory;
 
/**
 * Converter to be used with dates.
 * Usage: converts a date years, ex: 01/01/14 will be transformed to 01/01/2014
 * @author Laabidi RAISSI
 *
 */
//@Component("jodaDateTimeConverter")
@FacesConverter("jodaDateTimeConverter")
public class JodaDateTimeConverter extends DateTimeConverter{
 
 private static final Date DEFAULT_END_DATE = new LocalDate(2999, 12, 31).toDate();
  
 @Override
 public Object getAsObject(FacesContext context, UIComponent component, String value) {
  if(value == null){
   return null;
  }
  value = value.split(";")[0];
  String pattern = (String)component.getAttributes().get("custompattern");
  String[] patterns = pattern.split(";");
  Object ret = null;
  for(String pat: patterns){
   ret = fixDate(context, component, value, pat);
   if(ret instanceof Date){
    return ret;
   }
  }
  throw new ConverterException((FacesMessage)ret);
 }
  
 public String getAsString(FacesContext context, UIComponent component, Object value) {
  if(value == null){
   return "";
  }
  if (context == null || component == null) {
   throw new NullPointerException();
  }
 
  try {
   String pattern = ((String)component.getAttributes().get("custompattern")).split(";")[0];
   SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, getLocale());
   String res = dateFormat.format(value);
   String defaultStr = dateFormat.format(DEFAULT_END_DATE);
   if(defaultStr.equals(res)){
    return "";
   }
   return dateFormat.format(value);
 
  } catch (ConverterException e) {
   throw new ConverterException(MessageFactory.getMessage(context, STRING_ID, value, MessageFactory.getLabel(context, component)), e);
  } catch (Exception e) {
   throw new ConverterException(MessageFactory.getMessage(context, STRING_ID, value, MessageFactory.getLabel(context, component)), e);
  }
 }
  
 
 /**
  * A methods that takes a String value, and format it. Then it transforms years before 30 to 2000s.
  * Years after 30 are transformed to 1900s
  * If the entered value can't be formatted or if the year is contained between 100 and 999,
  * This method returns {@link javax.faces.application.FacesMessage} instead of a Date
  * @param context
  * @param component
  * @param value the String value entered by the user
  * @param pattern the pattern
  * @return
  * @throws ConverterException
  */
 private Object fixDate(FacesContext context, UIComponent component, String value, String pattern) throws ConverterException{
  DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
  LocalDateTime ldt = null;
  try{
   ldt = formatter.parseLocalDateTime(value);
  }catch(Exception e){
            Object[] params = new Object[3];
            params[0] = value;
            params[1] = formatter.print(new DateTime( new Date()));
            params[2] = MessageFactory.getLabel(context, component);
             
            return MessageFactory.getMessage("javax.faces.converter.DateTimeConverter.DATE", FacesMessage.SEVERITY_ERROR, params);
  }
  //Get the year and see if the year value is valid, i.e. year must be < 100 or >=1900
  int yy = ldt.getYear();
  if(yy >= 100 && yy <1900){
   return MessageFactory.getMessage(
                    context, "javax.faces.converter.DateTimeConverter.DATE", value,
                    MessageFactory.getLabel(context, component));
  }
  if(yy < 100){
   int c = yy%100;
   if(c <= 30){
    yy = c + 2000;
   }else{
    yy = c + 1900;
   }
   return ldt.withYear(yy).toDate();
  }
  return ldt.toDate();
 
 }
}