package fs_project.service;

import fs_project.model.dataEntity.Item;
import fs_project.model.dataEntity.Reservation;
import fs_project.model.requestModel.ReservationAvailabilityRequestModel;
import fs_project.model.responseModel.ReservationAvailabilityResponseModel;
import fs_project.repo.ReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ReservationService {
    @Autowired
    ReservationRepo reservationRepo;

    public Reservation getReservation(long id) {
        return null;
    }

    public Reservation createReservation(Reservation reservation) {
        return null;
    }

    public Reservation updateReservation(Reservation reservation, long id) {
        return null;
    }

    public Reservation deleteReservation(long id) {
        return null;
    }

    public Set<Reservation> getReservations() {
        return null;
    }

    public Set<Reservation> getUserReservations(long id) {
        return null;
    }

    public ReservationAvailabilityResponseModel getAvailableReservations(ReservationAvailabilityRequestModel reservationAvailabilityRequestModel) {
        ReservationAvailabilityResponseModel response = new ReservationAvailabilityResponseModel();
        Set<Item> items = reservationAvailabilityRequestModel.getItems();
        items.forEach(item -> {
            Set<Reservation> reservations = reservationRepo.getItemReservationsNextSevenDays(item.getItemId());
            response.addItemToTimeTable(reservations);
        });


        return response;
    }
}
