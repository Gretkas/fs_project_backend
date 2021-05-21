package fs_project.model.dataEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import fs_project.model.Attributes.ReservationType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * The type Reservation.
 */
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

    private String title;

    /**
     * Instantiates a new Reservation.
     *
     * @param user      the user
     * @param startTime the start time
     * @param endTime   the end time
     * @param items     the items
     * @param type      the type
     * @param title     the title
     */
    public Reservation( User user, LocalDateTime startTime, LocalDateTime endTime, List<Item> items, ReservationType type, String title) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.items = items;
        this.type = type;
        this.title = title;
    }

    /**
     * Instantiates a new Reservation. Used for testing purposes.
     *
     * @param startTime the start time
     * @param endTime   the end time
     */
    public Reservation(LocalDateTime startTime, LocalDateTime endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Instantiates a new Reservation.
     */
    public Reservation() {}

    /**
     * Method used to find the timeframe of the given reservaiton,
     * maps it in the form of a boolean matrix so it can be easily merged with other reservation timetables for availability checks
     *
     * @return the boolean [ ] [ ]
     */
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
                for (int i = startHour; i <= endHour; i++) {
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
                    for (int i = 0; i <= endHour; i++) {
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Sets start time.
     *
     * @param startTime the start time
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Sets end time.
     *
     * @param endTime the end time
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Sets items.
     *
     * @param nodes the nodes
     */
    public void setItems(List<Item> nodes) {
        this.items = nodes;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public ReservationType getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(ReservationType type) {
        this.type = type;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
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
