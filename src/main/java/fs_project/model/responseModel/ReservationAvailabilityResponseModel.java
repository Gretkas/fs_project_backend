package fs_project.model.responseModel;

import fs_project.model.dataEntity.Reservation;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public class ReservationAvailabilityResponseModel {
    private boolean[][] timetable;

    public ReservationAvailabilityResponseModel() {
        this.timetable = new boolean[7][10];
        for (int i = 0; i < LocalDateTime.now().getHour()-7; i++) {
            timetable[0][i] = true;
        }
    }

    public void addItemToTimeTable(Set<Reservation> itemReservations){
        for (Reservation r: itemReservations) {
            int day = (int)LocalDate.now().until(r.getStartTime().toLocalDate(), ChronoUnit.DAYS);
            boolean[] reservationTimeTable = r.toTimeTable();
            for (int i = 0; i < 10; i++) {
                timetable[day][i] |= reservationTimeTable[i];
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
