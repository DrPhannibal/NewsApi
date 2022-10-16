package com.fararuNews.utility;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Utils {

    public static LocalDateTime getUtc() {

        return LocalDateTime.now(ZoneOffset.UTC);

    }
}
