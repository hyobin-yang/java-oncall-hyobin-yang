package oncall.config;

import oncall.controller.OncallController;
import oncall.repository.WorkersRepository;
import oncall.service.OncallAssignmentService;
import oncall.service.OncallRequireService;
import oncall.view.InputView;
import oncall.view.OutputView;

public class AppConfig {
    private InputView inputView(){
        return new InputView();
    }

    private OutputView outputView(){
        return new OutputView();
    }

    private WorkersRepository workersRepository(){
        return new WorkersRepository();
    }

    private OncallRequireService oncallRequireService(){
        return new OncallRequireService();
    }

    private OncallAssignmentService oncallAssignmentService(){
        return new OncallAssignmentService(workersRepository());
    }

    public OncallController controller() {
        return new OncallController(inputView(), outputView(), oncallRequireService(), oncallAssignmentService());
    }
}
