package fs_project.controller;

import fs_project.mapping.dto.reservations.ReservationRequestDto;
import fs_project.mapping.dto.reservations.ReservationResponse;
import fs_project.model.dataEntity.Reservation;
import fs_project.model.filter.ReservationFilter;
import fs_project.mapping.dto.reservations.ReservationAvailabilityRequestModel;
import fs_project.mapping.dto.reservations.ReservationAvailabilityResponseModel;
import fs_project.mapping.dto.reservations.ReservationResponseModel;
import fs_project.service.ReservationService;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * The type Reservation controller.
 */
@RestController
@RequestMapping("/reservations")
public class ReservationController {

    /**
     * The Reservation service.
     */
    @Autowired
    ReservationService reservationService;


    /**
     * Get reservation response entity by id.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping(value="/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> getReservation(@PathVariable long id){
        return ResponseEntity.ok(reservationService.getReservation(id));
    }

    /**
     * Get filtered rooms response entity.
     * Is a post-request so that body can be added
     * @param roomFilter the room filter
     * @return the response entity
     */
    @PostMapping(value="/filter", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ReservationResponseModel>> getFilteredRooms(@RequestBody ReservationFilter roomFilter){
        return ResponseEntity.ok(reservationService.getReservationsWithFilter(roomFilter.getReservationPage(), roomFilter.getReservationSearchCriteria()));
    }

    /**
     * Get available reservations response entity.
     * Is a post-request so that body can be added
     * Used to check for available times for a reservation with a set of given items
     * @param reservationAvailabilityRequestModel the reservation availability request model
     * @return the response entity
     */
    @PostMapping(value = "/available", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationAvailabilityResponseModel> getAvailableReservations(@RequestBody ReservationAvailabilityRequestModel reservationAvailabilityRequestModel){
        return ResponseEntity.ok(reservationService.getAvailableReservations(reservationAvailabilityRequestModel));
    }

    /**
     * Get reservations response entity.
     *
     * @return the response entity
     */
    @GetMapping(value="", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ReservationResponseModel>> getReservations(){
        return ResponseEntity.ok(reservationService.getReservations());
    }

    /**
     * Get reservation history response entity for the current user
     *
     * @return the response entity
     */
    @GetMapping(value="/history", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ReservationResponseModel>> getReservationHistory(){
        return ResponseEntity.ok(reservationService.getReservationHistory());
    }

    /**
     * Get user reservations response entity.
     *
     * @param userId the userId
     * @return the response entity
     */
    @GetMapping(value="/user/{userId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Reservation>> getUserReservations(@PathVariable long userId){
        return ResponseEntity.ok(reservationService.getUserReservations(userId));
    }

    /**
     * Put reservation response entity.
     *
     * @param reservation the reservation
     * @param reservationId          the id
     * @return the response entity
     */
    @PutMapping(value="/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> putReservation(@RequestBody Reservation reservation, @PathVariable long reservationId){
        return ResponseEntity.ok(reservationService.updateReservation(reservation, reservationId));
    }

    /**
     * Post reservation response entity.
     * Creates a new reservation
     * @param reservation the reservation
     * @return the response entity
     * @throws BadHttpRequest the bad http request
     */
    @PostMapping(value="", produces = APPLICATION_JSON_VALUE)
    @Validated
    public ResponseEntity<ReservationResponse> postReservation(@NotNull @RequestBody @Validated ReservationRequestDto reservation) throws BadHttpRequest {
        return ResponseEntity.ok(reservationService.createReservation(reservation));
    }

    /**
     * Delete reservation response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(value="/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteReservation(@PathVariable long id){
        return ResponseEntity.ok(reservationService.deleteReservation(id));
    }





}
