package oncall.service;

import oncall.dto.OncallAssignmentDTO;
import oncall.dto.OncallRequireDTO;
import oncall.exception.ErrorMessages;
import oncall.model.*;
import oncall.repository.WorkersRepository;

import java.util.*;

public class OncallAssignmentService {

    private static final String DELIMITER = ",";

    private final WorkersRepository workersRepository;

    public OncallAssignmentService(WorkersRepository workersRepository){
        this.workersRepository = workersRepository;
    }

    public void registerWorkers(String weekdayWorkers, String holidayWorkers){
        workersRepository.addWeekdayWorkers(validateWorkers(weekdayWorkers));
        workersRepository.addHolidayWorkers(validateWorkers(holidayWorkers));
    }

    private List<String> validateWorkers(String rawWorkers){
        List<String> workers = Arrays.asList(rawWorkers.split(DELIMITER));
        if (workers.size() < 5 || workers.size() > 35){
            throw new IllegalArgumentException(ErrorMessages.INVALID_INPUT.getMessage());
        }
        for (String worker : workers){
            if (worker.length() > 5 || worker.isEmpty()){
                throw new IllegalArgumentException(ErrorMessages.INVALID_INPUT.getMessage());
            }
        }
        if (workers.size() != Set.copyOf(workers).size()){
            throw new IllegalArgumentException(ErrorMessages.INVALID_INPUT.getMessage());
        }
        return workers;
    }

    public void assign(OncallRequireDTO requireDTO){
        workersRepository.setPreviousDayNumber(requireDTO.day().getDayNumber());
        int dayNumber = requireDTO.day().getDayNumber();
        for (int date = 1; date <= requireDTO.month().getEndDate(); date++){
            updateAssign(findDayInformation(requireDTO.month(), date, Day.getDayBuyNumber(dayNumber)), requireDTO, date);
            if (dayNumber + 1 > 7){
                dayNumber = 1;
                continue;
            }
            dayNumber += 1;
        }
    }

    private DayInformation findDayInformation(Month month, int date, Day day){
        if (Holiday.isLegalHoliday(month.getMonthNumber(), date)){
            return DayInformation.LEGAL_HOLIDAY;
        }
        if (day.isHoliday()){
            return DayInformation.HOLIDAY;
        }
        return DayInformation.WEEKDAY;
    }

    private void updateAssign(DayInformation dayInformation, OncallRequireDTO requireDTO, int date){
        if (dayInformation.equals(DayInformation.HOLIDAY)){
            workersRepository.assignHolidayWorker(requireDTO, date);
        }
        if (dayInformation.equals(DayInformation.LEGAL_HOLIDAY)){
            workersRepository.assignLegalHolidayWorker(requireDTO, date);
        }
        if (dayInformation.equals(DayInformation.WEEKDAY)){
            workersRepository.assignWeekdayWorker(requireDTO, date);
        }
    }

    public List<OncallAssignmentDTO> getOncallAssignmentDTO(){
        LinkedList<OncallAssignmentDTO> resultDTO = new LinkedList<>();
        LinkedList<OncallAssignment> assignments = workersRepository.getAssignments();
        for (OncallAssignment assignment : assignments){
            int month = assignment.month().getMonthNumber();
            int date = assignment.date();
            String day = assignment.day().getDayName();
            String workerName = assignment.workerName();
            boolean isLegalHoliday = assignment.isLegalHoliday();
            resultDTO.add(new OncallAssignmentDTO(month, date, day, workerName, isLegalHoliday));
        }
        return resultDTO;
    }

}