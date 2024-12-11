package oncall.dto;

import oncall.model.Day;
import oncall.model.Month;

public record OncallRequireDTO(Month month, Day day) {
}
