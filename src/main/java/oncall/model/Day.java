package oncall.model;

public enum Day {
    MON("월", false),
    TUE("화", false),
    WED("수", false),
    THU("목", false),
    FRI("금", false),
    SAT("토", true),
    SUN("일", true);

    private final String day;
    private final boolean isHoliday;

    Day(String day, boolean isHoliday){
        this.day = day;
        this.isHoliday = isHoliday;
    }

    //TODO: 공휴일 검사
}
