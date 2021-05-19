package fs_project.model.responseModel;

import fs_project.model.dataEntity.Reservation;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Set;

public class ReservationAvailabilityResponseModel {
    private boolean[][] timetable;

    public ReservationAvailabilityResponseModel() {
        this.timetable = new boolean[7][10];
        for (int i = 0; i < LocalDateTime.now().getHour()-6; i++) {
            timetable[0][i] = true;
        }

    }

    public void addItemToTimeTable(Set<Reservation> itemReservations){
        System.out.println(itemReservations);
        for (Reservation r: itemReservations) {
            int day = (int)LocalDate.now().until(r.getStartTime().toLocalDate(), ChronoUnit.DAYS);
            if(day > 6) return;
            boolean[] reservationTimeTable = r.toTimeTable();
            System.out.println(Arrays.toString(reservationTimeTable));
            for (int i = 0; i < 10; i++) {
                timetable[day][i] |= reservationTimeTable[i];
            }
        }
    }

    public void createReservedTable(){
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 10; j++) {
                timetable[i][j] = true;
            }
        }
    }

    public boolean[][] getTimetable() {
        return timetable;
    }

    public void setTimetable(boolean[][] timetable) {
        this.timetable = timetable;
    }
}
