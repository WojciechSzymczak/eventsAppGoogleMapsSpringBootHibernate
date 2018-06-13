package com.events.eventsapp.util;

import com.events.eventsapp.configuration.exception.InvalidArgumentTypeException;

import java.sql.Timestamp;

public class DateAndTimeUtil {

    //TODO
    //make this method more rigorous
    public static Timestamp getTimestamp(String inputDate, String inputTime) throws InvalidArgumentTypeException {

        Timestamp timestamp = null;
        int year = 0;
        int month = 0;
        int day = 0;
        int hour = 0;
        int minute = 0;

        if (inputDate == null || inputDate.trim().equals("")) {
            throw new InvalidArgumentTypeException("Invalid date format. Please make sure" +
                    " the date you are entering is in YYYY-MM-DD format, for example:" +
                    " 2012-03-21.");
        } else if (inputTime == null || inputTime.trim().equals("")) {
            throw new InvalidArgumentTypeException("Invalid time format. Please make sure" +
                    " the time you are entering is in HH:MM AM/PM format, for example:" +
                    " 01:34 PM or 11:23 AM");
        }

        try {

            String [] inputDateArray = inputDate.split("-");

            year = Integer.parseInt(inputDateArray[0]);
            month = Integer.parseInt(inputDateArray[1]);
            day = Integer.parseInt(inputDateArray[2]);


        } catch (NumberFormatException e) {
            throw new InvalidArgumentTypeException("Invalid date format. Please make sure" +
                    " the date you are entering is in YYYY-MM-DD format, for example:" +
                    " 2012-03-21.");
        }

        try {

            String [] inputTimeArray = inputTime.split(" ");
            String [] inputTimeArraySplit = inputTimeArray[0].split(":");


            if (inputTimeArray.length<1 || inputTimeArraySplit.length!=2) {
                throw new InvalidArgumentTypeException("Invalid time format. Please make sure" +
                        " the time you are entering is in HH:MM AM/PM format, for example:" +
                        " 01:34 PM or 11:23 AM");
            }

            hour = Integer.parseInt(inputTimeArraySplit[0]);
            minute = Integer.parseInt(inputTimeArraySplit[1]);

        } catch (NumberFormatException e) {
            throw new InvalidArgumentTypeException("Invalid time format. Please make sure" +
                    " the time you are entering is in HH:MM AM/PM format, for example:" +
                    " 01:34 PM or 11:23 AM");
        }

        timestamp = new Timestamp(year-1900,month-1,day,hour,minute,0,0);

        return timestamp;
    }

    public static Timestamp getTimestamp(int year, int month, int day, int hour, int minute) {

        return new Timestamp(year - 1900,month - 1,day,hour,minute,0,0);

    }

}
