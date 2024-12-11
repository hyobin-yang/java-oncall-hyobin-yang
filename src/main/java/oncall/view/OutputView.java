package oncall.view;

public class OutputView {

    private static final String MONTH = "월";
    private static final String DATE = "일";
    private static final String IS_HOLIDAY = "(휴일)";

    public void printAssignment(int month, int date, String day, boolean isLegalHoliday, String workerName){
        if (isLegalHoliday){
            day += IS_HOLIDAY;
        }
        System.out.printf(month + MONTH + " " + date + DATE + " " + day + " " + workerName);
        System.out.println();
    }
}
