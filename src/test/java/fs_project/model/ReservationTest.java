package fs_project.model;

import fs_project.model.Attributes.ReservationType;
import fs_project.model.dataEntity.Reservation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class ReservationTest {

    private boolean[][] timeTable = {
            {true,true,true,true,true,true,true,true,true,true},
            {false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false},
    };

    private boolean[][] timeTableTwoDays = {
            {true,true,true,true,true,true,true,true,true,true},
            {true,true,true,true,true,true,true,true,true,true},
            {false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false},
    };

    @Test
    public void testToTimeTable(){
        LocalDateTime start = LocalDateTime.now().withHour(7);
        LocalDateTime end = LocalDateTime.now().withHour(17);

        Reservation reservation = new Reservation(start,end);

        for (int i = 0; i < reservation.toTimeTable().length; i++) {
            for (int j = 0; j < reservation.toTimeTable()[i].length; j++) {
                assertEquals(reservation.toTimeTable()[i][j],timeTable[i][j]);
            }
        }
    }

    @Test
    public void testToTimeTableTwoDays(){
        LocalDateTime start = LocalDateTime.now().withHour(7);
        LocalDateTime end = LocalDateTime.now().withDayOfYear(LocalDateTime.now().getDayOfYear()+1).withHour(17);

        Reservation reservation = new Reservation(start,end);

        for (int i = 0; i < reservation.toTimeTable().length; i++) {
            for (int j = 0; j < reservation.toTimeTable()[i].length; j++) {
                assertEquals(reservation.toTimeTable()[i][j],timeTableTwoDays[i][j]);
            }
        }
    }
}
