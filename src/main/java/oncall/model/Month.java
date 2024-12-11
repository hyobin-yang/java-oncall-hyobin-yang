package oncall.model;

public enum Month {
    JAN(31),
    FAB(28),
    MAR(31),
    APR(30),
    MAY(31),
    JUN(30),
    JUL(31),
    AUG(31),
    SEP(30),
    OCT(31),
    NOV(30),
    DEC(31);

    private final int endDate;

    Month(int endDate){
        this.endDate = endDate;
    }

    public int getEndDate(){
        return endDate;
    }
}
