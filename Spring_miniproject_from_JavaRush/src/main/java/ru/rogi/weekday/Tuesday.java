package ru.rogi.weekday;

public class Tuesday implements WeekDay{
    public String getWeekDayName() {
        return this.getClass().getSimpleName().substring(0,1).toLowerCase() + this.getClass().getSimpleName().substring(1);
    }
}
