package fs_project.service;

import fs_project.model.dataEntity.Item;
import fs_project.model.dataEntity.Reservation;
import fs_project.model.requestModel.ReservationAvailabilityRequestModel;
import fs_project.model.requestModel.ReservationPostRequestModel;
import fs_project.model.responseModel.ReservationAvailabilityResponseModel;
import fs_project.repo.ReservationRepo;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ReservationService {
    @Autowired
    ReservationRepo reservationRepo;

    @Autowired
    UserService userService;

    public Reservation getReservation(long id) {
        return null;
    }

    public Reservation createReservation(ReservationPostRequestModel reservationPostRequestModel) throws BadHttpRequest {
        Reservation r = reservationPostRequestModel.convert(userService.getThisUser());
        reservationRepo.save(r);
        return r;
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
