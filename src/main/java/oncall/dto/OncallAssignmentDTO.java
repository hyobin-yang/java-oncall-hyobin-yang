package oncall.dto;

public record OncallAssignmentDTO(int month, int date, String day, String workerName, boolean isLegalHoliday) {
}
