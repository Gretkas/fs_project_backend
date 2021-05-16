package fs_project.service;

import fs_project.model.dataEntity.Item;
import fs_project.model.dataEntity.Reservation;
import fs_project.model.requestModel.ReservationAvailabilityRequestModel;
import fs_project.model.requestModel.ReservationPostRequestModel;
import fs_project.model.responseModel.ReservationAvailabilityResponseModel;
import fs_project.model.responseModel.ReservationResponseModel;
import fs_project.repo.ReservationRepo;
import fs_project.repo.UserRepo;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ReservationService {
    @Autowired
    ReservationRepo reservationRepo;
    @Autowired
    UserRepo userRepo;

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

    public boolean deleteReservation(long id) {
        reservationRepo.deleteById(id);
        return true;
    }

    public Set<ReservationResponseModel> getReservations() {
        Set<ReservationResponseModel> rrm = new HashSet<ReservationResponseModel>();
        reservationRepo.getUpcomingReservationsByUser(userService.getThisUser().getId()).forEach(reservation -> rrm.add(new ReservationResponseModel(reservation)));
        return rrm;
    }

    public Set<Reservation> getUserReservations(long id) {
        return reservationRepo.getReservationsByUser(userRepo.getOne(id));
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

    public Set<ReservationResponseModel> getReservationHistory() {
        Set<ReservationResponseModel> rrm = new HashSet<ReservationResponseModel>();
        reservationRepo.getReservationHistoryByUser(userService.getThisUser().getId()).forEach(reservation -> rrm.add(new ReservationResponseModel(reservation)));
        return rrm;
    }
}
