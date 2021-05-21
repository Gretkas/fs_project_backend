package fs_project.model.filter;

/**
 * The type Reservation filter. Used for filtering/searching/sorting/paging reservations.
 */
public class ReservationFilter {
    private ReservationSearchCriteria reservationSearchCriteria;
    private ReservationPage reservationPage;

    /**
     * Instantiates a new Reservation filter.
     *
     * @param reservationSearchCriteria the reservation search criteria
     * @param reservationPage           the reservation page
     */
    public ReservationFilter(ReservationSearchCriteria reservationSearchCriteria, ReservationPage reservationPage) {
        this.reservationSearchCriteria = reservationSearchCriteria;
        this.reservationPage = reservationPage;
    }

    /**
     * Instantiates a new Reservation filter.
     */
    public ReservationFilter() {
    }

    /**
     * Gets reservation search criteria.
     *
     * @return the reservation search criteria
     */
    public ReservationSearchCriteria getReservationSearchCriteria() {
        return reservationSearchCriteria;
    }

    /**
     * Sets reservation search criteria.
     *
     * @param reservationSearchCriteria the reservation search criteria
     */
    public void setReservationSearchCriteria(ReservationSearchCriteria reservationSearchCriteria) {
        this.reservationSearchCriteria = reservationSearchCriteria;
    }

    /**
     * Gets reservation page.
     *
     * @return the reservation page
     */
    public ReservationPage getReservationPage() {
        return reservationPage;
    }

    /**
     * Sets reservation page.
     *
     * @param reservationPage the reservation page
     */
    public void setReservationPage(ReservationPage reservationPage) {
        this.reservationPage = reservationPage;
    }


    @Override
    public String toString() {
        return "ReservationFilter{" +
                "reservationSearchCriteria=" + reservationSearchCriteria +
                ", reservationPage=" + reservationPage +
                '}';
    }

}
