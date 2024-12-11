package oncall.model;

public record OncallAssignment(Month month, int date, Day day, String workerName, boolean isLegalHoliday) {
}
