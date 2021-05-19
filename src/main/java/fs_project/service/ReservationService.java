package fs_project.service;

import fs_project.exceptions.BadRequestException;
import fs_project.exceptions.FatalException;
import fs_project.exceptions.ResponseErrStatus;
import fs_project.exceptions.ServerErrorException;
import fs_project.mapping.dto.AvailableItemsRequest;
import fs_project.mapping.dto.ReservationRequestDto;
import fs_project.mapping.dto.ReservationResponse;
import fs_project.mapping.item.ItemMapper;
import fs_project.mapping.reservation.ReservationMapper;
import fs_project.model.Attributes.ReservationType;
import fs_project.model.dataEntity.Item;
import fs_project.model.dataEntity.Reservation;
import fs_project.model.dataEntity.User;
import fs_project.model.requestModel.ReservationAvailabilityRequestModel;
import fs_project.model.requestModel.ReservationPostRequestModel;
import fs_project.model.responseModel.ReservationAvailabilityResponseModel;
import fs_project.model.responseModel.ReservationResponseModel;
import fs_project.repo.ReservationRepo;
import fs_project.repo.UserRepo;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReservationService {
    @Autowired
    ReservationRepo reservationRepo;
    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    ItemMapper itemMapper;

    public Reservation getReservation(long id) {
        return null;
    }

//    public Reservation createReservation(ReservationPostRequestModel reservationPostRequestModel) throws BadHttpRequest {
//        Reservation r = reservationPostRequestModel.convert(userService.getThisUser());
//        reservationRepo.save(r);
//        return r;
//    }

    public ReservationResponse createReservation(@NotNull ReservationRequestDto reservationPostRequestModel) throws BadHttpRequest {
        Reservation r = reservationMapper.reservationRequestToReservation(reservationPostRequestModel);
        User user;
        ReservationResponse res;
        try {
            user = userService.getThisUser();
            r.setUser(user);
        } catch (UsernameNotFoundException e) {
            // evt. some other handling
            throw new FatalException(ResponseErrStatus.USER_NOT_FOUND, "User session not recognized", e);
        }

        r = saveReservation(r);
        if (r.getId() == 0) // save failed due to db problems, or validation fail (foreign key etc)
            throw new ServerErrorException(ResponseErrStatus.DB_SAVE_FAILED, "Could not complete reservation");

        try {
            res = mapReservationResponse(r);
        } catch (Exception e) {
            throw new ServerErrorException(ResponseErrStatus.UNEXPECTED_MAPPING_FAIL, "Could not complete reservation", e);
        }

        return res;
    }

    // TODO remove after mapstruct decorator is implemented
    private ReservationResponse mapReservationResponse(Reservation reservation) {
        if (reservation.getType() == ReservationType.RESERVATION) {
            return reservationMapper.reservationToStandardResponse(reservation);
        } else if (reservation.getType() == ReservationType.MAINTENANCE) {
            return reservationMapper.reservationToMaintenanceResponse(reservation, "Ok"); // todo implement result
        } else {
            throw new ServerErrorException
                    (ResponseErrStatus.DB_SAVE_FAILED, "Could not complete reservation");
        }
    }

    private Reservation saveReservation(Reservation reservation) {
        if (reservation.getType() == ReservationType.RESERVATION) {
            return reservationRepo.save(reservation);
        } else if (reservation.getType() == ReservationType.MAINTENANCE) {
            return reservationRepo.saveMaintenanceReservation(reservation);
        } else {
            throw new BadRequestException
                    (ResponseErrStatus.ILLEGAL_RESERVATION_TYPE, "Illegal Reservation Type provided");
        }
    }



    public Reservation updateReservation(Reservation reservation, long id) {
        return null;
    }

    public Reservation deleteReservation(long id) {
        return null;
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
        System.out.println(reservationAvailabilityRequestModel.toString());
        List<Item> items = reservationAvailabilityRequestModel.getItems();
        if(items != null && items.size() > 0){
            items.forEach(item -> {
                Set<Reservation> reservations = reservationRepo.getItemReservationsNextSevenDays(item.getItemId());
                response.addItemToTimeTable(reservations);
            });
        }



        return response;
    }

    public Set<ReservationResponseModel> getReservationHistory() {
        Set<ReservationResponseModel> rrm = new HashSet<ReservationResponseModel>();
        reservationRepo.getReservationHistoryByUser(userService.getThisUser().getId()).forEach(reservation -> rrm.add(new ReservationResponseModel(reservation)));
        return rrm;
    }
}
