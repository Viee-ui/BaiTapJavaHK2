package Ex3_5;

import java.util.*;

public class MyDate {
    private int year;
    private int month;
    private int day;

    private static final String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private static final String[] DAYS = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private static final int[] DAYS_IN_MONTHS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public MyDate(int year, int month, int day) {
        setDate(year, month, day);
    }

    public void setDate(int year, int month, int day) {
        if (!isValidDate(year, month, day)) {
            throw new IllegalArgumentException("Invalid year, month, or day!");
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static boolean isValidDate(int year, int month, int day) {
        if (year < 1 || year > 9999 || month < 1 || month > 12) {
            return false;
        }
        int dayMax = DAYS_IN_MONTHS[month - 1];
        if (month == 2 && isLeapYear(year)) {
            dayMax = 29;
        }
        return day >= 1 && day <= dayMax;
    }

    public static int getDayOfWeek(int year, int month, int day) {
        int y = year, m = month;
        if (m < 3) {
            m += 12;
            y--;
        }
        int k = y % 100;
        int j = y / 100;
        int dayOfWeek = (day + (13 * (m + 1)) / 5 + k + (k / 4) + (j / 4) + (5 * j)) % 7;
        return (dayOfWeek + 5) % 7;
    }

    public void setYear(int year) {
        if (year < 1 || year > 9999) {
            throw new IllegalArgumentException("Invalid year!");
        }
        this.year = year;
    }

    public void setMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month!");
        }
        this.month = month;
    }

    public void setDay(int day) {
        if (!isValidDate(this.year, this.month, day)) {
            throw new IllegalArgumentException("Invalid day!");
        }
        this.day = day;
    }

    public int getYear() { return year; }
    public int getMonth() { return month; }
    public int getDay() { return day; }

    @Override
    public String toString() {
        return DAYS[getDayOfWeek(year, month, day)] + " " + day + " " + MONTHS[month - 1] + " " + year;
    }

    public MyDate nextDay() {
        if (isValidDate(year, month, day + 1)) {
            day++;
        } else if (month < 12) {
            month++;
            day = 1;
        } else {
            if (year == 9999) throw new IllegalStateException("Year out of range!");
            year++;
            month = 1;
            day = 1;
        }
        return this;
    }

    public MyDate nextMonth() {
        if (month == 12) {
            if (year == 9999) throw new IllegalStateException("Year out of range!");
            year++;
            month = 1;
        } else {
            month++;
        }
        day = Math.min(day, DAYS_IN_MONTHS[month - 1] + (month == 2 && isLeapYear(year) ? 1 : 0));
        return this;
    }

    public MyDate nextYear() {
        if (year == 9999) throw new IllegalStateException("Year out of range!");
        year++;
        if (month == 2 && day == 29 && !isLeapYear(year)) {
            day = 28;
        }
        return this;
    }

    public MyDate previousDay() {
        if (day > 1) {
            day--;
        } else if (month > 1) {
            month--;
            day = DAYS_IN_MONTHS[month - 1] + (month == 2 && isLeapYear(year) ? 1 : 0);
        } else {
            if (year == 1) throw new IllegalStateException("Year out of range!");
            year--;
            month = 12;
            day = 31;
        }
        return this;
    }

    public MyDate previousMonth() {
        if (month == 1) {
            if (year == 1) throw new IllegalStateException("Year out of range!");
            year--;
            month = 12;
        } else {
            month--;
        }
        day = Math.min(day, DAYS_IN_MONTHS[month - 1] + (month == 2 && isLeapYear(year) ? 1 : 0));
        return this;
    }

    public MyDate previousYear() {
        if (year == 1) throw new IllegalStateException("Year out of range!");
        year--;
        if (month == 2 && day == 29 && !isLeapYear(year)) {
            day = 28;
        }
        return this;
    }
}
