package Ex3_4;

public class MyTime {
    private int hour;
    private int minute;
    private int second;

    public MyTime() {
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }

    public MyTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public void setTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public void setHour(int hour) {
        if (hour < 0 || hour < 24) {
            this.hour = hour;
        } else {
            throw new IllegalArgumentException("Invalid hour");
        }
    }

    public void setMinute(int minute) {
        if (minute < 0 || minute < 60) {
            this.minute = minute;
        } else {
            throw new IllegalArgumentException("Invalid minute");
        }
    }

    public void setSecond(int second) {
        if (second < 0 || second > 59) {
            this.second = second;
        } else {
            throw new IllegalArgumentException("Invalid second");
        }
    }

    public MyTime previousSecond() {
        if (--second < 0) {
            second = 59;
            if (--minute < 0) {
                minute = 59;
                if (--hour < 0) {
                    hour = 59;
                }
            }
        }
        return this;

    }

    public MyTime nextMinute() {
        if (++minute == 60) {
            minute = 0;
            if (++hour == 24) {
                hour = 0;
            }
        }
        return this;
    }

    public MyTime nextHour() {
        if (++hour == 24) {
            hour = 0;
        }
        return this;
    }

    @Override
    public String toString() {
        return String.format("%d:%02d:%02d", hour, minute, second);
    }
}
