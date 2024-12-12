package oncall.controller;

import oncall.dto.OncallAssignmentDTO;
import oncall.dto.OncallRequireDTO;
import oncall.handler.RetryHandler;
import oncall.service.OncallAssignmentService;
import oncall.service.OncallRequireService;
import oncall.view.InputView;
import oncall.view.OutputView;

import java.util.List;

public class OncallController {

    private final InputView inputView;
    private final OutputView outputView;
    private final OncallRequireService oncallRequireService;
    private final OncallAssignmentService oncallAssignmentService;

    public OncallController(InputView inputView, OutputView outputView, OncallRequireService oncallRequireService, OncallAssignmentService oncallAssignmentService){
        this.inputView = inputView;
        this.outputView = outputView;
        this.oncallRequireService = oncallRequireService;
        this.oncallAssignmentService = oncallAssignmentService;
    }

    public void run(){
        OncallRequireDTO requireDTO = RetryHandler.retryWithReturn(this::inputOncallRequire);
        inputView.breakLine();
        RetryHandler.retryWithoutReturn(this::registerWorkers);
        inputView.breakLine();
        assignOncall(requireDTO);
        printResult();
    }

    private OncallRequireDTO inputOncallRequire(){
        return oncallRequireService.registerOncallRequire(inputView.inputOncallRequire());
    }

    private void registerWorkers(){
        oncallAssignmentService.registerWorkers(inputView.inputWeekdayWorkers(), inputView.inputHolidayWorkers());
    }

    private void assignOncall(OncallRequireDTO requireDTO){
        oncallAssignmentService.assign(requireDTO);
    }

    private void printResult(){
        List<OncallAssignmentDTO> resultsDTO = oncallAssignmentService.getOncallAssignmentDTO();
        resultsDTO.forEach( dto -> outputView.printAssignment(dto.month(), dto.date(), dto.day(),
                                                                dto.isLegalHoliday(), dto.workerName()));
    }

}
