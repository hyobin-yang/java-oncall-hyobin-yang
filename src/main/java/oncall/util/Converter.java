package oncall.util;

import oncall.exception.ErrorMessages;

public class Converter {
    public static int convertToNumber(String input){
        try{
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException(ErrorMessages.INVALID_INPUT.getMessage());
        }
    }
}
