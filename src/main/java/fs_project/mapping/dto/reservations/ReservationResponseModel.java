package fs_project.mapping.dto.reservations;

import fs_project.mapping.dto.ItemResponseModel;
import fs_project.mapping.dto.users.UserResponseModel;
import fs_project.model.Attributes.ReservationType;
import fs_project.model.dataEntity.Reservation;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Reservation response model. Used to retrieve reservations.
 */
public class ReservationResponseModel {
    private UserResponseModel user;
    private Set<ItemResponseModel> items;
    private LocalDateTime startTime, endTime;
    private String roomName;
    private long id;
    /**
     * The Type.
     */
    ReservationType type;
    private String title;
    private long roomId;


    /**
     * Instantiates a new Reservation response model.
     *
     * @param reservation the reservation
     */
    public ReservationResponseModel(Reservation reservation) {
        this.user = new UserResponseModel(reservation.getUser());
        this.items = new HashSet<ItemResponseModel>();
        reservation.getItems().forEach(item -> items.add(new ItemResponseModel(item)));
        this.startTime = reservation.getStartTime();
        this.endTime = reservation.getEndTime();
        this.type = reservation.getType();
        this.roomName = reservation.getItems().get(0).getRoom().getName();
        this.id = reservation.getId();
        this.title = reservation.getTitle();
        this.roomId = reservation.getItems().get(0).getRoom().getId();
    }

    /**
     * Gets room id.
     *
     * @return the room id
     */
    public long getRoomId() {
        return roomId;
    }

    /**
     * Sets room id.
     *
     * @param roomId the room id
     */
    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets room name.
     *
     * @return the room name
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Sets room name.
     *
     * @param roomName the room name
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * Instantiates a new Reservation response model.
     */
    public ReservationResponseModel() {
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public UserResponseModel getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(UserResponseModel user) {
        this.user = user;
    }

    /**
     * Gets items.
     *
     * @return the items
     */
    public Set<ItemResponseModel> getItems() {
        return items;
    }

    /**
     * Sets items.
     *
     * @param items the items
     */
    public void setItems(Set<ItemResponseModel> items) {
        this.items = items;
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
}
