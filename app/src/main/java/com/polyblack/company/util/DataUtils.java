package com.polyblack.company.util;

import com.polyblack.company.pojo.Employee;
import com.polyblack.company.pojo.Speciality;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SuppressWarnings("deprecation")
public class DataUtils {
    private static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
    private static SimpleDateFormat readFormat_ddMMyyyy = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
    private static SimpleDateFormat readFormat_MMddyyyy = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
    private static SimpleDateFormat readFormat_yyyyddMM = new SimpleDateFormat("yyyy-dd-MM", Locale.getDefault());
    private static final String UNDEFINED = "-";

    public static Employee beautifyEmployee(Employee employee) {
        employee.setBirthday(convertDateFormat(employee.getBirthday()));
        employee.setFName(convertNameString(employee.getFName()));
        employee.setLName(convertNameString(employee.getLName()));
        return employee;
    }

    private static String convertDateFormat(String originalDate) {
        if (originalDate != null && !originalDate.equals("")) {
            SimpleDateFormat testFormat = new SimpleDateFormat();
            if (originalDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                testFormat = readFormat_yyyyddMM;
            } else if (originalDate.matches("(((0)[1-9])|((1)[0-2]))(-)\\d{2}(-)\\d{4}")) {
                testFormat = readFormat_MMddyyyy;
            } else {
                testFormat = readFormat_ddMMyyyy;
            }
            try {
                Date date = testFormat.parse(originalDate);
                originalDate = format.format(date);
                return originalDate;
            } catch (ParseException | NullPointerException e) {
                e.printStackTrace();
            }
        }

        return UNDEFINED;
    }

    public static String getAge(String birthday) {
        try {
            Date currentDate = new Date();
            Date birthdayDate = format.parse(birthday);
            int difference = currentDate.getYear() - birthdayDate.getYear();
            return String.valueOf(difference);
        } catch (ParseException | NullPointerException e) {
            return UNDEFINED;
        }
    }


    private static String convertNameString(String name) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        return name;
    }

    public static String specialitiesToString(List<Speciality> specialities) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < specialities.size(); i++) {
            stringBuilder.append(specialities.get(i).getName());
            if (i != specialities.size() - 1) {
                stringBuilder.append("; ");
            }
        }
        return stringBuilder.toString();
    }
}
