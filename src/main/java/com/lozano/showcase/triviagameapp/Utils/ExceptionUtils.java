package com.lozano.showcase.triviagameapp.Utils;

public class ExceptionUtils {

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void throwException(Throwable exception, Object dummy) throws T {
        throw (T) exception;
    }

    public static void throwException(Throwable exception){
        ExceptionUtils.<RuntimeException>throwException(exception, null);
    }
}
