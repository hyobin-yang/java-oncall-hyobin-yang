package oncall.model.calendar;

import oncall.exception.ErrorMessages;

import java.util.Arrays;

public enum Day {
    MON(1, "월", false),
    TUE(2, "화", false),
    WED(3, "수", false),
    THU(4, "목", false),
    FRI(5, "금", false),
    SAT(6, "토", true),
    SUN(7, "일", true);

    public static final int TOTAL_DAY_COUNT = 7;

    private final int dayNumber;
    private final String day;
    private final boolean isHoliday;

    Day(int dayNumber, String day, boolean isHoliday){
        this.dayNumber = dayNumber;
        this.day = day;
        this.isHoliday = isHoliday;
    }

    public static Day getDay(String input){
        return Arrays.stream(Day.values())
                .filter(d -> d.day.equals(input))
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException(ErrorMessages.INVALID_INPUT.getMessage()));
    }

    public boolean isHoliday(){
        return isHoliday;
    }

    public int getDayNumber(){
        return dayNumber;
    }

    public String getDayName(){
        return day;
    }

    public static Day getDayBuyNumber(int number){
        return Arrays.stream(Day.values())
                .filter(d -> d.dayNumber == number)
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException(ErrorMessages.INVALID_INPUT.getMessage()));

    }

}
