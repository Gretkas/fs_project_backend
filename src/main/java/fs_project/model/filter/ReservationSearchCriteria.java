package fs_project.model.filter;

/**
 * The type Reservation search criteria. contains search criteria for reservations
 */
//fra sys2
public class ReservationSearchCriteria {
    private String title;
    private boolean showPreviousReservations;

    /**
     * Instantiates a new Reservation search criteria.
     */
    public ReservationSearchCriteria() {
    }

    /**
     * Is show previous reservations boolean.
     *
     * @return the boolean
     */
    public boolean isShowPreviousReservations() {
        return showPreviousReservations;
    }

    /**
     * Sets show previous reservations.
     *
     * @param showPreviousReservations the show previous reservations
     */
    public void setShowPreviousReservations(boolean showPreviousReservations) {
        this.showPreviousReservations = showPreviousReservations;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
