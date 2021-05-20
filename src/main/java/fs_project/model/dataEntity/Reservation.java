package fs_project.model.dataEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import fs_project.model.Attributes.ReservationType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Reservation")
public class Reservation {

    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;


    @Basic(optional = false)
    @JsonFormat(locale = "no", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Basic(optional = false)
    @JsonFormat(locale = "no", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @ManyToMany
    @JoinTable(
            name="reservation_item",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items = new ArrayList<>();

    @Column(name="type")
    private ReservationType type;

    public Reservation( User user, LocalDateTime startTime, LocalDateTime endTime, List<Item> items, ReservationType type) {

        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.items = items;
        this.type = type;
    }

    public Reservation() {

    }

    public boolean[][] toTimeTable() {
        boolean[][] timeTable = new boolean[7][10];
        int startDay = (int) LocalDate.now().until(startTime.toLocalDate(), ChronoUnit.DAYS);
        int endDay = (int) LocalDate.now().until(endTime.toLocalDate(), ChronoUnit.DAYS);
        if(startDay > 6 || endDay < 0 || startDay > endDay) return timeTable;
        if(startDay < 0) startDay = 0;
        if(endDay > 6 ) endDay=6;
        int startHour = startTime.getHour()-7;
        int endHour = endTime.getHour()-7;
        if(startHour > 9 || endHour < 0) return timeTable;
        if(startHour < 0) startHour = 0;
        if(endHour > 9) endHour = 9;

        for (int j = startDay; j < endDay+1; j++) {
            if(startDay==endDay){
                for (int i = startHour; i < endHour; i++) {
                    timeTable[j][i] = true;
                }
            }
            else{
                if (j == startDay) {
                    for (int i = startHour; i < 10; i++) {
                        timeTable[j][i] = true;
                    }
                }
                else if(j == endDay){
                    System.out.println("I AM HERE");
                    System.out.println(endHour);
                    for (int i = 0; i < endHour; i++) {
                        timeTable[j][i] = true;
                    }
                }
                else {
                    for (int i = 0; i < 10; i++) {
                        timeTable[j][i] = true;
                    }
                }
            }
        }
        return timeTable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> nodes) {
        this.items = nodes;
    }

    public ReservationType getType() {
        return type;
    }

    public void setType(ReservationType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", user=" + user +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", items=" + items +
                ", type=" + type +
                '}';
    }
}
