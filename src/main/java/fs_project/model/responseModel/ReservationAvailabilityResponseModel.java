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
        if( LocalDateTime.now().getHour()-6 <= 9){
            for (int i = 0; i < LocalDateTime.now().getHour()-6; i++) {

                timetable[0][i] = true;
            }
        }else
            for (int i = 0; i < 10; i++) {

                timetable[0][i] = true;
            }

    }

    public void addItemToTimeTable(Set<Reservation> itemReservations){
        System.out.println(itemReservations);
        for (Reservation r: itemReservations) {
            int startDay = (int)LocalDate.now().until(r.getStartTime().toLocalDate(), ChronoUnit.DAYS);
            int endDay = (int)LocalDate.now().until(r.getEndTime().toLocalDate(), ChronoUnit.DAYS);
            if(startDay > 6 || endDay < 0 || startDay > endDay) return;
            if(startDay < 0) startDay = 0;
            if(endDay > 6 ) endDay=6;
            boolean[][] reservationTimeTable = r.toTimeTable();


            for (int i = startDay; i < endDay+1 ; i++) {
                for (int j = 0; j < 10; j++) {
                    timetable[i][j] |= reservationTimeTable[i][j];
                }
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
