package fs_project.model.filter;
//fra sys2
public class ReservationSearchCriteria {
    private String title;
    private boolean showPreviousReservations;

    public ReservationSearchCriteria() {
    }

    public boolean isShowPreviousReservations() {
        return showPreviousReservations;
    }

    public void setShowPreviousReservations(boolean showPreviousReservations) {
        this.showPreviousReservations = showPreviousReservations;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}