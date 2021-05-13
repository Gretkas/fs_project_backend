package fs_project.model.dataEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import fs_project.model.Attributes.ReservationType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Reservation")
public class Reservation {

    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
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
    private Set<Item> items;

    @Column(name="type")
    private ReservationType type;

    public Reservation(long id, User user, LocalDateTime startTime, LocalDateTime endTime, Set<Item> items, ReservationType type) {
        this.id = id;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.items = items;
        this.type = type;
    }

    public Reservation() {

    }

    public boolean[] toTimeTable(){
        boolean[] timeTable = new boolean[10];
        for (int i = startTime.getHour()-8; i < endTime.getHour()-8; i++) {
            timeTable[i] = true;
        }

        return timeTable;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Set<Item> getNodes() {
        return items;
    }

    public void setNodes(Set<Item> nodes) {
        this.items = nodes;
    }

    public ReservationType getType() {
        return type;
    }

    public void setType(ReservationType type) {
        this.type = type;
    }
}
