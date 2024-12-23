package oncall.repository;

import oncall.dto.OncallRequireDTO;
import oncall.model.calendar.Day;
import oncall.model.oncall.HolidayWorkers;
import oncall.model.oncall.OncallAssignment;
import oncall.model.oncall.WeekdayWorkers;

import java.util.LinkedList;
import java.util.List;

public class WorkersRepository {

    private WeekdayWorkers weekdayWorkers;
    private HolidayWorkers holidayWorkers;

    private int weekdayWorkerNumber = 0;
    private int holidayWorkerNumber = 0;
    private int dayNumber;
    private String previousWorkerName = "";

    private final LinkedList<OncallAssignment> assignments = new LinkedList<>();

    public void addWeekdayWorkers(List<String> workers){
        this.weekdayWorkers = new WeekdayWorkers(workers);
    }

    public void addHolidayWorkers(List<String> workers){
        this.holidayWorkers = new HolidayWorkers(workers);
    }

    public void setPreviousDayNumber(int number){
        dayNumber = number;
    }

    public void assignHolidayWorker(OncallRequireDTO requireDTO, int date){
        if (isWorkInARow(holidayWorkers.getNameByIndex(holidayWorkerNumber))){
            holidayWorkers.changeWorkerToNext(holidayWorkerNumber);
        }
        String workerName = holidayWorkers.getNameByIndex(holidayWorkerNumber);
        assignments.add(new OncallAssignment(requireDTO.month(), date, Day.getDayBuyNumber(dayNumber), workerName, false));
        if (holidayWorkers.isSameAsWorkersSize(holidayWorkerNumber + 1)){
            holidayWorkerNumber= 0;
            return;
        }
        updateAssignmentInformation(workerName);
        holidayWorkerNumber += 1;
    }

    public void assignLegalHolidayWorker(OncallRequireDTO requireDTO, int date){
        if (isWorkInARow(holidayWorkers.getNameByIndex(holidayWorkerNumber))){
            holidayWorkers.changeWorkerToNext(holidayWorkerNumber);
        }
        String workerName = holidayWorkers.getNameByIndex(holidayWorkerNumber);
        assignments.add(new OncallAssignment(requireDTO.month(), date, Day.getDayBuyNumber(dayNumber), workerName, true));
        if (holidayWorkers.isSameAsWorkersSize(holidayWorkerNumber + 1)){
            holidayWorkerNumber= 0;
            return;
        }
        updateAssignmentInformation(workerName);
        holidayWorkerNumber += 1;
    }

    public void assignWeekdayWorker(OncallRequireDTO requireDTO, int date){
        if (isWorkInARow(weekdayWorkers.getNameByIndex(weekdayWorkerNumber))){
            weekdayWorkers.changeWorkerToNext(weekdayWorkerNumber);
        }
        String workerName = weekdayWorkers.getNameByIndex(weekdayWorkerNumber);
        assignments.add(new OncallAssignment(requireDTO.month(), date, Day.getDayBuyNumber(dayNumber), workerName, false));
        if (weekdayWorkers.isSameAsWorkersSize(weekdayWorkerNumber + 1)){
            weekdayWorkerNumber = 0;
            return;
        }
        updateAssignmentInformation(workerName);
        weekdayWorkerNumber += 1;
    }

    private void updateAssignmentInformation(String workerName){
        previousWorkerName = workerName;
        updateDayNumber();
    }

    private boolean isWorkInARow(String name){
        return (previousWorkerName.equals(name));
    }

    private void updateDayNumber(){
        if (dayNumber == 7){
            dayNumber = 1;
            return;
        }
        dayNumber += 1;
    }

    public LinkedList<OncallAssignment> getAssignments() {
        return assignments;
    }
}
