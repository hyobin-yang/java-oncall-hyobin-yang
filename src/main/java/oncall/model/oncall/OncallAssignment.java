package oncall.model.oncall;

import oncall.model.calendar.Day;
import oncall.model.calendar.Month;

public record OncallAssignment(Month month, int date, Day day, String workerName, boolean isLegalHoliday) {
}
