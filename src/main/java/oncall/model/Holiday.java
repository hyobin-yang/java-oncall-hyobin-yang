package oncall.model;

public enum Holiday {

    NEW_YEAR_S("신정", 1, 1),
    INDEPENDENCE_MOVEMENT_IN_MARCH("삼일절", 3, 1),
    CHILDREN_S_DAY("어린이날", 5, 5),
    MEMORIAL("현충일", 6, 6),
    NATIONAL_LIBERATION("광복절", 8, 15),
    NATIONAL_FOUNDATION("개천절", 10, 3),
    HANGUL_PROCLAMATION("한글날", 10, 9),
    CHRISTMAS("성탄절", 12, 25);

    private final String holidayName;
    private final int month;
    private final int day;

    Holiday(String holidayName, int month, int day){
        this.holidayName = holidayName;
        this.month = month;
        this.day = day;
    }

    //TODO: 공휴일인지 검사
}
