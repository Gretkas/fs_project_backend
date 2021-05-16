package fs_project.controller;

import fs_project.mapping.dto.ReservationRequestDto;
import fs_project.mapping.dto.ReservationResponse;
import fs_project.model.dataEntity.Reservation;
import fs_project.model.requestModel.ReservationAvailabilityRequestModel;
import fs_project.model.requestModel.ReservationPostRequestModel;
import fs_project.model.responseModel.ReservationAvailabilityResponseModel;
import fs_project.model.responseModel.ReservationResponseModel;
import fs_project.model.responseModel.UserResponseModel;
import fs_project.repo.ReservationRepo;
import fs_project.service.ReservationService;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    ReservationService reservationService;


    @GetMapping(value="/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> getReservation(@PathVariable long id){
        return ResponseEntity.ok(reservationService.getReservation(id));
    }

    @GetMapping(value = "/available", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationAvailabilityResponseModel> getAvailableReservations(@RequestBody ReservationAvailabilityRequestModel reservationAvailabilityRequestModel){
        return ResponseEntity.ok(reservationService.getAvailableReservations(reservationAvailabilityRequestModel));
    }

    @GetMapping(value="", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ReservationResponseModel>> getReservations(){
        return ResponseEntity.ok(reservationService.getReservations());
    }

    @GetMapping(value="/history", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ReservationResponseModel>> getReservationHistory(){
        return ResponseEntity.ok(reservationService.getReservationHistory());
    }

    @GetMapping(value="/user/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Reservation>> getUserReservations(@PathVariable long id){
        return ResponseEntity.ok(reservationService.getUserReservations(id));
    }

    @PutMapping(value="/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> putReservation(@RequestBody Reservation reservation, @PathVariable long id){
        return ResponseEntity.ok(reservationService.updateReservation(reservation, id));
    }
    @PostMapping(value="", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationResponse> postReservation(@NotNull @RequestBody ReservationRequestDto reservation) throws BadHttpRequest {
        return ResponseEntity.ok(reservationService.createReservation(reservation));
    }

    @DeleteMapping(value="/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> deleteReservation(@PathVariable long id){
        return ResponseEntity.ok(reservationService.deleteReservation(id));
    }





}
