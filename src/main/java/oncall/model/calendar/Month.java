package oncall.model.calendar;

import oncall.exception.ErrorMessages;

import java.util.Arrays;

public enum Month {
    JAN(1,31),
    FAB(2,28),
    MAR(3,31),
    APR(4,30),
    MAY(5,31),
    JUN(6,30),
    JUL(7,31),
    AUG(8, 31),
    SEP(9,30),
    OCT(10, 31),
    NOV(11, 30),
    DEC(12,31);

    private final int monthNumber;
    private final int endDate;

    Month(int monthNumber, int endDate){
        this.monthNumber = monthNumber;
        this.endDate = endDate;
    }

    public static Month getMonth(int input){
        return Arrays.stream(Month.values())
                .filter(m -> m.monthNumber == input)
                .findFirst()
                .orElseThrow( () -> new IllegalArgumentException(ErrorMessages.INVALID_INPUT.getMessage()));
    }

    public int getEndDate(){
        return endDate;
    }

    public int getMonthNumber(){
        return monthNumber;
    }
}
