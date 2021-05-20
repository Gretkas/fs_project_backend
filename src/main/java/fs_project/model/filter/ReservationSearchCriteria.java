package fs_project.model.filter;
//fra sys2
public class ReservationSearchCriteria {
    private String name;
    private boolean showPreviousReservations;

    public ReservationSearchCriteria() {
    }

    public boolean isShowPreviousReservations() {
        return showPreviousReservations;
    }

    public void setShowPreviousReservations(boolean showPreviousReservations) {
        this.showPreviousReservations = showPreviousReservations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
