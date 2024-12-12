package oncall.model.oncall;

import java.util.List;

public class HolidayWorkers {

    private List<String> workers;

    public HolidayWorkers(List<String> workers){
        this.workers = workers;
    }

    public String getNameByIndex(int index){
        return workers.get(index);
    }

    public void changeWorkerToNext(int index){
        String temp = workers.get(index);
        if ((index + 1) >= workers.size()){
            workers.set(index, workers.get(0));
            workers.set(0, temp);
            return;
        }
        workers.set(index, workers.get(index+1));
        workers.set(index+1, temp);
    }

    public boolean isSameAsWorkersSize(int number){
        return number == workers.size();
    }
}
