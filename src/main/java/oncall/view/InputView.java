package oncall.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public void breakLine(){
        System.out.println();
    }

    public String inputOncallRequire(){
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        return Console.readLine();
    }

    public String inputWeekdayWorkers(){
        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        String input = Console.readLine();
        breakLine();
        return input;
    }

    public String inputHolidayWorkers(){
        System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        return Console.readLine();
    }
}
