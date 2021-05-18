package fs_project;

import fs_project.model.Attributes.ReservationType;
import fs_project.model.dataEntity.*;
import fs_project.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TestData {
    private final boolean TESTDATA_ENABLED = true;


    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    ItemRepo itemRepo;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private SectionRepo sectionRepo;

    @PostConstruct
    private void postConstruct() {
        if (!TESTDATA_ENABLED) return;

        User user1 = new User("admin","admin","ADMIN");
        User user2 = new User("test1","password","USER");
        User user3 = new User("test2","password","USER");
        User user4 = new User("test3","password","USER");
        User user5 = new User("test4","password","USER");
        User user6 = new User("test5","password","USER");

        Room room1 = new Room();

        room1.setName("kjemi");
        room1.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s");
        room1.setLocation("Dragvoll");

        Room room2 = new Room();

        room2.setName("fysikklabben");
        room2.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s");
        room2.setLocation("Dragvoll");

        roomRepo.save(room1);
        roomRepo.save(room2);

        Item pc = new Item();
        pc.setName("PC");
        pc.setRoomId(room2);

        Item koke = new Item();
        koke.setName("Koke");
        koke.setRoomId(room1);

        Item centrifuge = new Item();
        centrifuge.setName("Centrifuge");
        centrifuge.setRoomId(room1);

        List<Item> ting1 = new ArrayList<>();
        ting1.add(koke);
        ting1.add(centrifuge);
        room1.setItems(ting1);

        List<Item> ting2 = new ArrayList<>();
        ting2.add(pc);
        room2.setItems(ting2);

        List<Item> ting3 = new ArrayList<>();
        ting3.add(koke);



        Reservation reservation = new Reservation(
                user1,
                LocalDateTime.parse("2021-05-21 11:00", formatter),
                LocalDateTime.parse("2021-05-21 14:00", formatter),
                ting1,
                ReservationType.RESERVATION
        );
        itemRepo.save(koke);
        itemRepo.save(pc);
        itemRepo.save(centrifuge);

        Section section = new Section();
        section.setItems(ting3);
        section.setName("Seksjon for koking");
        ArrayList<Section> room1Sections = new ArrayList<>();
        room1Sections.add(section);
        room1.setSections(room1Sections);

        sectionRepo.save(section);


        itemRepo.save(koke);
        itemRepo.save(pc);


        roomRepo.save(room1);
        roomRepo.save(room2);

        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);
        userRepo.save(user4);
        userRepo.save(user5);
        userRepo.save(user6);
        reservationRepo.save(reservation);
    }
}
