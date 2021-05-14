package fs_project;

import fs_project.model.dataEntity.Item;
import fs_project.model.dataEntity.Room;
import fs_project.model.dataEntity.User;
import fs_project.repo.ReservationRepo;
import fs_project.repo.RoomRepo;
import fs_project.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
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
    private RoomRepo roomRepo;

    @Autowired
    private UserRepo userRepo;

    @PostConstruct
    private void postConstruct() {
        if (!TESTDATA_ENABLED) return;

        User user1 = new User("admin","admin_password","ADMIN");
        User user2 = new User("test1","password","USER");
        User user3 = new User("test2","password","USER");
        User user4 = new User("test3","password","USER");
        User user5 = new User("test4","password","USER");
        User user6 = new User("test5","password","USER");

        Room room1 = new Room();
        room1.setName("kjemi");

        Room room2 = new Room();
        room2.setName("fysikklabben");

        Item pc = new Item();
        pc.setName("PC");
        pc.setRoomId(room2);

        Item koke = new Item();
        koke.setName("Koke");
        koke.setRoomId(room1);

        Set<Item> ting1 = new HashSet<>();
        ting1.add(koke);
        room1.setItems(ting1);

        Set<Item> ting2 = new HashSet<>();
        ting2.add(pc);
        room2.setItems(ting2);
    }
}
