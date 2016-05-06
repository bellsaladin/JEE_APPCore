package com.seosoft.erp.util.components.composite;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.event.AjaxBehaviorEvent;

@FacesComponent("inputDate")
public class InputDate extends UIInput implements NamingContainer {

    // Fields -------------------------------------------------------------------------------------
	
	private int val1;
    
    // Actions ------------------------------------------------------------------------------------

    /**
     * Returns the component family of {@link UINamingContainer}.
     * (that's just required by composite component)
     */
    @Override
    public String getFamily() {
        return UINamingContainer.COMPONENT_FAMILY;
    }

    /**
     * Set the selected and available values of the day, month and year fields based on the model.
     */
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        Calendar calendar = Calendar.getInstance();
        int maxYear = getAttributeValue("maxyear", calendar.get(Calendar.YEAR));
        int minYear = getAttributeValue("minyear", maxYear - 100);
        Date date = (Date) getValue();

        if (date != null) {
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);

            if (year > maxYear || minYear > year) {
                throw new IllegalArgumentException(
                    String.format("Year %d out of min/max range %d/%d.", year, minYear, maxYear));
            }
        }

        UIInput dayComponent = (UIInput)findComponent("day"); 
        UIInput monthComponent = (UIInput)findComponent("month"); 
        UIInput yearComponent = (UIInput)findComponent("year"); 
        dayComponent.setValue(calendar.get(Calendar.DATE)); 
        monthComponent.setValue(calendar.get(Calendar.MONTH) + 1); 
        yearComponent.setValue(calendar.get(Calendar.YEAR));
        
        setDays(createIntegerArray(1, calendar.getActualMaximum(Calendar.DATE)));
        setMonths(createIntegerArray(1, calendar.getActualMaximum(Calendar.MONTH) + 1));
        setYears(createIntegerArray(maxYear, minYear));
        super.encodeBegin(context);
    }

    /**
     * Returns the submitted value in dd-MM-yyyy format.
     */
    @Override
    public Object getSubmittedValue() {
        return this;
    }

    /**
     * Converts the submitted value to concrete {@link Date} instance.
     */
    @Override
    protected Object getConvertedValue(FacesContext context, Object newSubmittedValue) throws ConverterException {
        UIInput dayComponent = (UIInput)findComponent("day");
        UIInput monthComponent = (UIInput)findComponent("month");
        UIInput yearComponent = (UIInput)findComponent("year");
        int day = Integer.parseInt((String)dayComponent.getSubmittedValue());
        int month = Integer.parseInt((String)monthComponent.getSubmittedValue());
        int year = Integer.parseInt((String)yearComponent.getSubmittedValue());
        return(new GregorianCalendar(year, month-1, day).getTime());
     }

    /**
     * Update the available days based on the selected month and year, if necessary.
     */
    public void updateDaysIfNecessary(AjaxBehaviorEvent event) {
    	UIInput dayComponent = (UIInput)findComponent("day");
        UIInput monthComponent = (UIInput)findComponent("month");
        UIInput yearComponent = (UIInput)findComponent("year");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.MONTH, (Integer) monthComponent.getValue() - 1);
        calendar.set(Calendar.YEAR, (Integer) yearComponent.getValue());
        int maxDay = calendar.getActualMaximum(Calendar.DATE);

        if (getDays().length != maxDay) {
            setDays(createIntegerArray(1, maxDay));
            if ((Integer) dayComponent.getValue() > maxDay) {
            	dayComponent.setValue(maxDay); // Fix the selected value if it exceeds new max value.
            }
            FacesContext context = FacesContext.getCurrentInstance(); // Update day field.
            context.getPartialViewContext().getRenderIds().add(dayComponent.getClientId(context));
        }
    }

    // Helpers ------------------------------------------------------------------------------------

    /**
     * Return specified attribute value or otherwise the specified default if it's null.
     */
    @SuppressWarnings("unchecked")
    private <T> T getAttributeValue(String key, T defaultValue) {
        T value = (T) getAttributes().get(key);
        return (value != null) ? value : defaultValue;
    }

    /**
     * Create an integer array with values from specified begin to specified end, inclusive.
     */
    private static Integer[] createIntegerArray(int begin, int end) {
        int direction = (begin < end) ? 1 : (begin > end) ? -1 : 0;
        int size = Math.abs(end - begin) + 1;
        Integer[] array = new Integer[size];

        for (int i = 0; i < size; i++) {
            array[i] = begin + (i * direction);
        }

        return array;
    }

    // Getters/setters ----------------------------------------------------------------------------

    public Integer[] getDays() {
        return (Integer[]) getStateHelper().get("days");
    }

    public void setDays(Integer[] days) {
        getStateHelper().put("days", days);
    }

    public Integer[] getMonths() {
        return (Integer[]) getStateHelper().get("months");
    }

    public void setMonths(Integer[] months) {
        getStateHelper().put("months", months);
    }

    public Integer[] getYears() {
        return (Integer[]) getStateHelper().get("years");
    }

    public void setYears(Integer[] years) {
        getStateHelper().put("years", years);
    }

	public int getVal1() {
		return val1;
	}

}