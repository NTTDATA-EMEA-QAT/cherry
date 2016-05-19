package io.magentys.narrators;

import io.magentys.Narrator;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import static io.magentys.utils.Requires.requiresNotNull;

public class SysoutNarrator implements Narrator {

    public static SysoutNarrator sysout() {
        return new SysoutNarrator();
    }


    @Override
    public void narrate(String level, String message) {
        requiresNotNull(level,"Level was null");
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime date = LocalDateTime.now();
        String timestamp = date.toString(formatter);
        System.out.println(String.format("%s [%s]:\t%s", timestamp, level.toUpperCase(), message));
    }
}
