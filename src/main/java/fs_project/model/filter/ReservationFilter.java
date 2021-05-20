package fs_project.model.filter;

public class ReservationFilter {
    private ReservationSearchCriteria reservationSearchCriteria;
    private ReservationPage reservationPage;

    public ReservationFilter(ReservationSearchCriteria reservationSearchCriteria, ReservationPage reservationPage) {
        this.reservationSearchCriteria = reservationSearchCriteria;
        this.reservationPage = reservationPage;
    }

    public ReservationFilter() {
    }

    public ReservationSearchCriteria getReservationSearchCriteria() {
        return reservationSearchCriteria;
    }

    public void setReservationSearchCriteria(ReservationSearchCriteria reservationSearchCriteria) {
        this.reservationSearchCriteria = reservationSearchCriteria;
    }

    public ReservationPage getReservationPage() {
        return reservationPage;
    }

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
