package oncall.dto;

import oncall.model.calendar.Day;
import oncall.model.calendar.Month;

public record OncallRequireDTO(Month month, Day day) {
}
