package fs_project.service;

import fs_project.exceptions.BadRequestException;
import fs_project.exceptions.FatalException;
import fs_project.exceptions.ResponseErrStatus;
import fs_project.exceptions.ServerErrorException;
import fs_project.mapping.dto.ReservationRequestDto;
import fs_project.mapping.dto.ReservationResponse;
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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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

    @Autowired
    private ReservationMapper reservationMapper;

    public Reservation getReservation(long id) {
        return null;
    }

//    public Reservation createReservation(ReservationPostRequestModel reservationPostRequestModel) throws BadHttpRequest {
//        Reservation r = reservationPostRequestModel.convert(userService.getThisUser());
//        reservationRepo.save(r);
//        return r;
//    }

    public ReservationResponse createReservation(@NotNull @Valid ReservationRequestDto reservationPostRequestModel) throws BadHttpRequest {
        @Valid Reservation r = reservationMapper.reservationRequestToReservation(reservationPostRequestModel);
        @Valid User user;
        @Valid ReservationResponse res;
        try {
            user = userService.getThisUser();
            r.setUser(user);
        } catch (UsernameNotFoundException e) {
            // evt. some other handling
            throw new FatalException(ResponseErrStatus.USER_NOT_FOUND, "User session not recognized", e);
        }

        r = reservationRepo.save(r);
        if (r.getId() == 0) // save failed due to db problems, or validation fail (foreign key etc)
            throw new ServerErrorException(ResponseErrStatus.DB_SAVE_FAILED, "Could not complete reservation");

        try {
            if (r.getType() == ReservationType.MAINTENANCE) {
                @NotNull @Valid Set<Reservation> affectedReservations;
                try {
                    affectedReservations = findReservationsAffectedBy(r);
                    updateAffectedReservations(affectedReservations, r.getStartTime(), r.getEndTime());
                    reservationRepo.saveAll(affectedReservations);
                } catch (Exception e) {
                    throw new ServerErrorException
                            (ResponseErrStatus.DB_UPDATE_FAILED,
                                    "Could not complete maintenance reservation, " +
                                            "cause: failed to update affected reservations", e);
                }
                String result = affectedReservations.size() + " reservations were affected";
                res = reservationMapper.reservationToMaintenanceResponse(r, result);
            } else {
                res = reservationMapper.reservationToStandardResponse(r);
            }
        } catch (Exception e) {
            throw new ServerErrorException(ResponseErrStatus.UNEXPECTED_MAPPING_FAIL, "Could not complete reservation", e);
        }

        return res;
    }

    private void updateAffectedReservations(@NotNull Set<Reservation> affected, LocalDateTime maintenanceStart, LocalDateTime maintenanceEnd) {
        affected.forEach(r -> {
            if (r.getEndTime().isAfter(maintenanceStart) && r.getEndTime().isBefore(maintenanceEnd)) {
                r.setEndTime(maintenanceStart);
            }
            if (r.getStartTime().isAfter(maintenanceStart) && r.getStartTime().isBefore(maintenanceEnd)) {
                r.setStartTime(maintenanceEnd);
            }
        });
    }

    private Set<Reservation> findReservationsAffectedBy(@NotNull @Valid Reservation reservation) {
        return reservationRepo
                .findAffectedReservations
                        (reservation.getStartTime(), reservation.getEndTime(),
                                reservation.getId(), reservation.getItems());
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
