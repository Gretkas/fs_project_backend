package fs_project.service;

import fs_project.exceptions.UnauthorizedException;
import fs_project.mapping.dto.SingleItemDTO;
import fs_project.mapping.dto.reservations.ItemReservationDto;
import fs_project.mapping.dto.reservations.ReservationRequestDto;
import fs_project.mapping.dto.reservations.ReservationResponse;
import fs_project.mapping.mappers.ItemMapper;
import fs_project.mapping.mappers.ReservationMapper;
import fs_project.model.Attributes.ReservationType;
import fs_project.model.dataEntity.Item;
import fs_project.model.dataEntity.Reservation;
import fs_project.model.dataEntity.User;
import fs_project.repo.ReservationCriteriaRepo;
import fs_project.repo.ReservationRepo;
import fs_project.repo.UserRepo;
import javassist.tools.web.BadHttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class ReservationServiceTest {

    private User user1;
    private  User user2;



    @Autowired
    private ReservationService reservationService;

    @MockBean
    private UserService userService;




    private ReservationRequestDto  createReservationRequest(){
        ItemReservationDto item1 = new ItemReservationDto();
        item1.setItemId(1L);
        item1.setName("test");
        item1.setRoomId(1L);

        SingleItemDTO singleItem = new SingleItemDTO();
        singleItem.setName("Test");
        singleItem.setItemId(1L);
        ArrayList<SingleItemDTO> itemList = new ArrayList<>();
        itemList.add(singleItem);


        ReservationRequestDto request = new ReservationRequestDto();
        request.setItems(itemList);
        request.setTitle("TestReservation");
        request.setStartTime(LocalDateTime.of(2021, Month.AUGUST, 12, 12, 0));
        request.setEndTime(LocalDateTime.of(2021, Month.AUGUST, 12, 14, 0));
        request.setType(ReservationType.RESERVATION);

        return request;
    }

    @BeforeEach
    public void setUsers(){
        user1 = new User("admin",new BCryptPasswordEncoder().encode("admin"),"ADMIN");
        user2 = new User("1234",new BCryptPasswordEncoder().encode("1234"),"USER");
    }

    @Test
    void createReservation() throws BadHttpRequest {
        ReservationRequestDto reservation = createReservationRequest();
        user1.setId(1L);
        Mockito.when(userService.getThisUser()).thenReturn(user1);

        ReservationResponse res = reservationService.createReservation(reservation);
        assertEquals(res.getType(), ReservationType.RESERVATION);

        assertEquals(res.getType(), ReservationType.RESERVATION);
        assertEquals(res.getStartTime(), LocalDateTime.of(2021, Month.AUGUST, 12, 12, 0) );

    }

    @Test
    @Transactional
    void deleteReservation() throws BadHttpRequest {
        ReservationRequestDto reservation = createReservationRequest();
        user2.setId(1L);
        Mockito.when(userService.getThisUser()).thenReturn(user2);

        ReservationResponse res = reservationService.createReservation(reservation);

        assertTrue(reservationService.deleteReservation(4));

        ReservationResponse res2 = reservationService.createReservation(reservation);
        Mockito.when(userService.getThisUser()).thenReturn(user1);
        assertTrue(reservationService.deleteReservation(5));

        ReservationResponse res3 = reservationService.createReservation(reservation);
        Mockito.when(userService.getThisUser()).thenReturn(user2);
        try{
            reservationService.deleteReservation(1);
        }
        catch (Exception e){
            assertEquals(UnauthorizedException.class, e.getClass());
        }
    }

    @Test
    void getReservations() {
    }

    @Test
    void getUserReservations() {
    }

    @Test
    void getAvailableReservations() {
    }

    @Test
    void getReservationHistory() {
    }

    @Test
    void getReservationsWithFilter() {
    }
}