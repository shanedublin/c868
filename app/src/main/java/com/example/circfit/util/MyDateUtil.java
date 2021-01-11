package com.example.circfit.util;

import java.time.format.DateTimeFormatter;

public final class MyDateUtil {

    private MyDateUtil(){}
    public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/YYYY");
    public static final DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("MM/dd/YYYY hh:mm:ss a");
}
