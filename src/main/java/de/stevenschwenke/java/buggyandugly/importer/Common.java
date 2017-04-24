package de.stevenschwenke.java.buggyandugly.importer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {

    /**
     * Format of String: yyyy-mm-dd'T'HH:mm:ss
     */
    public static Date convertStringToDate(final String formattedString) {
        final String importantPartOfDate = formattedString.substring(0, 19);
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            return sdf.parse(importantPartOfDate);
        } catch (final ParseException e) {
//            LOG.error("Could not parse from String to Date", e);
        }
        return new Date();
    }

    public static Date convertYearStringToDate(final String year) {
        if(year==null){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        try {
            return sdf.parse(year);
        } catch (ParseException e) {
//            LOG.error("Could not parse from String to Date", e);
        }
        return null;
    }
}
