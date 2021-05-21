package fs_project.controller;

import fs_project.mapping.dto.RoomDTO;
import fs_project.model.dataEntity.Reservation;
import fs_project.model.dataEntity.Room;
import fs_project.model.filter.RoomFilter;
import fs_project.service.ReservationService;
import fs_project.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("rooms")
public class RoomController {

    @Autowired
    RoomService roomService;


    @GetMapping(value="/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RoomDTO> getRoom(@PathVariable long id){
        return ResponseEntity.ok(roomService.getRoom(id));
    }

    @GetMapping(value="", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RoomDTO>> getRooms(){
        return ResponseEntity.ok(roomService.getRooms());
    }

    @PostMapping(value="/filter", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<RoomDTO>> getFilteredRooms(@RequestBody RoomFilter roomFilter){
        return ResponseEntity.ok(roomService.getRoomsWithFilters(roomFilter.getRoomPage(), roomFilter.getRoomSearchCriteria()));
    }


    @PutMapping(value="", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> putRoom(@RequestBody Room room){
        return ResponseEntity.ok(roomService.updateRoom(room));
    }
    @PostMapping(value="", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> postRoom(@RequestBody Room room){
        return ResponseEntity.ok(roomService.createRoom(room));
    }

    @DeleteMapping(value="/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteRoom(@PathVariable long id){
        return ResponseEntity.ok(roomService.deleteRoom(id));
    }





}