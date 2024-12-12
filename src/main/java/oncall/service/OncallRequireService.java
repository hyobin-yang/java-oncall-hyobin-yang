package oncall.service;

import oncall.dto.OncallRequireDTO;
import oncall.model.calendar.Day;
import oncall.model.calendar.Month;
import oncall.util.Converter;

import java.util.Arrays;
import java.util.List;

public class OncallRequireService {

    private static final String DELIMITER = ",";

    public OncallRequireDTO registerOncallRequire(String input){
        List<String> require = Arrays.asList(input.trim().split(DELIMITER));
        return new OncallRequireDTO(getMonth(require.get(0)), getDay(require.get(1)));
    }

    private Month getMonth(String rawMonth){
        int month = Converter.convertToNumber(rawMonth);
        return Month.getMonth(month);
    }

    private Day getDay(String rawDay){
        String day = rawDay.trim();
        return Day.getDay(day);
    }
}
