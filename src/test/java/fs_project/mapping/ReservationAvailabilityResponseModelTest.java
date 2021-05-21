package fs_project.mapping;

import fs_project.mapping.dto.reservations.ReservationAvailabilityResponseModel;
import fs_project.model.Attributes.ReservationType;
import fs_project.model.dataEntity.Reservation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ReservationAvailabilityResponseModelTest {

    private boolean[][] timeTable = {
            {true,true,true,true,true,true,true,true,true,true},
            {false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false},
            {false,false,false,false,false,false,false,false,false,false},
            {true,true,true,true,true,true,true,true,true,true},
    };

    @Test
    public void testAddItemToTimeTable(){
        LocalDateTime start = LocalDateTime.now().withHour(7);
        LocalDateTime end = LocalDateTime.now().withHour(17);

        Reservation reservation1 = new Reservation(start,end);

        LocalDateTime start2 = LocalDateTime.now().withHour(7).withDayOfYear(LocalDateTime.now().getDayOfYear()+6);
        LocalDateTime end2 = LocalDateTime.now().withHour(17).withDayOfYear(LocalDateTime.now().getDayOfYear()+6);

        Reservation reservation2 = new Reservation(start2,end2);

        ReservationAvailabilityResponseModel rarm = new ReservationAvailabilityResponseModel();
        Set<Reservation> reservations = new HashSet<>();
        reservations.add(reservation1);
        reservations.add(reservation2);
        rarm.addItemToTimeTable(reservations);

        for (int i = 0; i < rarm.getTimetable().length; i++) {
            for (int j = 0; j < rarm.getTimetable()[i].length; j++) {
                assertEquals(rarm.getTimetable()[i][j],timeTable[i][j]);
            }
        }
    }
}
