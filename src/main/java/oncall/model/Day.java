package oncall.model;

import oncall.exception.ErrorMessages;

public enum Day {
    MON(1, "월", false),
    TUE(2, "화", false),
    WED(3, "수", false),
    THU(4, "목", false),
    FRI(5, "금", false),
    SAT(6, "토", true),
    SUN(7, "일", true);

    private final int dayNumber;
    private final String day;
    private final boolean isHoliday;

    Day(int dayNumber, String day, boolean isHoliday){
        this.dayNumber = dayNumber;
        this.day = day;
        this.isHoliday = isHoliday;
    }

    public static Day getDay(String input){
        for (Day day : values()){
            if (day.day.equals(input)){
                return day;
            }
        }
        throw new IllegalArgumentException(ErrorMessages.INVALID_INPUT.getMessage());
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
        for (Day day : values()){
            if (day.dayNumber == number){
                return day;
            }
        }
        throw new IllegalArgumentException("[SYSTEM] 잘못된 요일 값입니다.");
    }

}
