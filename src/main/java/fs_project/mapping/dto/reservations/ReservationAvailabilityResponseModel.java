package fs_project.mapping.dto.reservations;

import fs_project.model.dataEntity.Reservation;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Set;

/**
 * The type Reservation availability response model. used to check which times are free for a reservation containing a given set of items
 */
public class ReservationAvailabilityResponseModel {
    private boolean[][] timetable;

    /**
     * Instantiates a new Reservation availability response model. sets hours which has passed in the current day as busy
     */
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

    /**
     * Add item to time table. uses logical or to combine the availabilities of the passed reservations, called once for each item that must be checked
     *
     * @param itemReservations the item reservations
     */
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

    /**
     * Create reserved table. returns completely busy timetable, used if user has not selected any items yet on the frontend
     */
    public void createReservedTable(){
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 10; j++) {
                timetable[i][j] = true;
            }
        }
    }

    /**
     * Get timetable boolean [ ] [ ].
     *
     * @return the boolean [ ] [ ]
     */
    public boolean[][] getTimetable() {
        return timetable;
    }

    /**
     * Sets timetable.
     *
     * @param timetable the timetable
     */
    public void setTimetable(boolean[][] timetable) {
        this.timetable = timetable;
    }
}
