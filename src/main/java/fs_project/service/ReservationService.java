package fs_project.service;

import fs_project.exceptions.*;
import fs_project.mapping.dto.ReservationRequestDto;
import fs_project.mapping.dto.ReservationResponse;
import fs_project.mapping.item.ItemMapper;
import fs_project.mapping.reservation.ReservationMapper;
import fs_project.model.Attributes.ReservationType;
import fs_project.model.dataEntity.Item;
import fs_project.model.dataEntity.Reservation;
import fs_project.model.dataEntity.User;
import fs_project.model.filter.ReservationPage;
import fs_project.model.filter.ReservationSearchCriteria;
import fs_project.mapping.dto.ReservationAvailabilityRequestModel;
import fs_project.mapping.dto.ReservationAvailabilityResponseModel;
import fs_project.mapping.dto.ReservationResponseModel;
import fs_project.repo.ReservationCriteriaRepo;
import fs_project.repo.ReservationRepo;
import fs_project.repo.UserRepo;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The type Reservation service.
 */
@Service
public class ReservationService {
    /**
     * The Reservation repo.
     */
    @Autowired
    ReservationRepo reservationRepo;
    /**
     * The Reservation criteria repo.
     */
    @Autowired
    ReservationCriteriaRepo reservationCriteriaRepo;
    /**
     * The User repo.
     */
    @Autowired
    UserRepo userRepo;

    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    @Autowired
    private ReservationMapper reservationMapper;

    /**
     * The Item mapper.
     */
    @Autowired
    ItemMapper itemMapper;

    /**
     * Gets reservation by id,  TODO.
     *
     * @param id the id
     * @return the reservation
     */
    public Reservation getReservation(long id) {
        return null;
    }

//    public Reservation createReservation(ReservationPostRequestModel reservationPostRequestModel) throws BadHttpRequest {
//        Reservation r = reservationPostRequestModel.convert(userService.getThisUser());
//        reservationRepo.save(r);
//        return r;
//    }

    /**
     * Creates reservation checks the reservation type.if reservation is of type maintenance, checks which reservations were overwritten
     *
     * @param reservationPostRequestModel the reservation post request model
     * @return the reservation response
     * @throws BadHttpRequest the bad http request
     */
    public ReservationResponse createReservation(@NotNull @Valid ReservationRequestDto reservationPostRequestModel) throws BadHttpRequest {
        @Valid Reservation r = reservationMapper.reservationRequestToReservation(reservationPostRequestModel);
        @Valid User user;
        @Valid ReservationResponse res;
        System.out.println(reservationPostRequestModel.toString());
        try {
            user = userService.getThisUser();
            r.setUser(user);
            r.setItems(itemMapper.itemDTOListToItemList(reservationPostRequestModel.getItems()));
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

    /**
     * overwrites reservations affected by maintenance
     * @param affected
     * @param maintenanceStart
     * @param maintenanceEnd
     */
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

    /**
     * finds reservations affected by maintenance
     * @param reservation
     * @return
     */
    private Set<Reservation> findReservationsAffectedBy(@NotNull @Valid Reservation reservation) {
        return reservationRepo
                .findAffectedReservations
                        (reservation.getStartTime(), reservation.getEndTime(),
                                reservation.getId(), reservation.getItems());
    }

    /**
     * Update reservation reservation.
     *
     * @param reservation the reservation
     * @param id          the id
     * @return the reservation
     */
    public Reservation updateReservation(Reservation reservation, long id) {
        return null;
    }

    /**
     * Delete reservation boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean deleteReservation(long id) {
        Reservation r = reservationRepo.getOne(id);
        if(r.getUser() == userService.getThisUser() || userService.getThisUser().getRole().equals("ADMIN")){
            reservationRepo.deleteById(id);
            return true;
        }
        else{
            throw new UnauthorizedException(ResponseErrStatus.FORBIDDEN_ROLE, "you cant delete another users reservations");
        }
    }

    /**
     * Gets reservations.
     *
     * @return the reservations
     */
    public Set<ReservationResponseModel> getReservations() {
        Set<ReservationResponseModel> rrm = new HashSet<ReservationResponseModel>();
        reservationRepo.getUpcomingReservationsByUser(userService.getThisUser().getId()).forEach(reservation -> rrm.add(new ReservationResponseModel(reservation)));
        return rrm;
    }

    /**
     * Gets user reservations.
     *
     * @param id the id
     * @return the user reservations
     */
    public Set<Reservation> getUserReservations(long id) {
        return reservationRepo.getReservationsByUser(userRepo.getOne(id));
    }

    /**
     * Gets available times for a reservation with a given set of itms.
     *
     * @param reservationAvailabilityRequestModel the reservation availability request model
     * @return the available reservations
     */
    public ReservationAvailabilityResponseModel getAvailableReservations(ReservationAvailabilityRequestModel reservationAvailabilityRequestModel) {
        ReservationAvailabilityResponseModel response = new ReservationAvailabilityResponseModel();
        System.out.println(reservationAvailabilityRequestModel.toString());
        List<Item> items = reservationAvailabilityRequestModel.getItems();
        if(items != null && items.size() > 0){
            items.forEach(item -> {
                Set<Reservation> reservations = reservationRepo.getItemReservationsNextSevenDays(item.getItemId());
                response.addItemToTimeTable(reservations);
            });
        }else{
            response.createReservedTable();
        }
        return response;
    }

    /**
     * Gets reservation history.
     *
     * @return the reservation history
     */
    public Set<ReservationResponseModel> getReservationHistory() {
        Set<ReservationResponseModel> rrm = new HashSet<ReservationResponseModel>();
        reservationRepo.getReservationHistoryByUser(userService.getThisUser().getId()).forEach(reservation -> rrm.add(new ReservationResponseModel(reservation)));
        return rrm;
    }

    /**
     * Gets reservations with filter.
     *
     * @param reservationPage           the reservation page
     * @param reservationSearchCriteria the reservation search criteria
     * @return the reservations with filter
     */
    public Page<ReservationResponseModel> getReservationsWithFilter(ReservationPage reservationPage, ReservationSearchCriteria reservationSearchCriteria) {
        return reservationMapper.reservationPageToReservationResponseModelPage(reservationCriteriaRepo.findAllWithFilters(reservationPage, reservationSearchCriteria));
    }
}
