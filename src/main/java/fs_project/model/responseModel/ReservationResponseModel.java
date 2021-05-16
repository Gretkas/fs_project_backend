package fs_project.model.responseModel;

import fs_project.model.Attributes.ReservationType;
import fs_project.model.dataEntity.Item;
import fs_project.model.dataEntity.Reservation;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReservationResponseModel {
    private UserResponseModel user;
    private Set<ItemResponseModel> items;
    private LocalDateTime startTime, endTime;
    private String roomName;
    private long id;
    ReservationType type;
    private String sectionName;


    public ReservationResponseModel(Reservation reservation) {
        this.user = new UserResponseModel(reservation.getUser());
        this.items = new HashSet<ItemResponseModel>();
        reservation.getItems().forEach(item -> items.add(new ItemResponseModel(item)));
        this.startTime = reservation.getStartTime();
        this.endTime = reservation.getEndTime();
        this.type = reservation.getType();
        this.roomName = reservation.getItems().get(0).getRoom().getName();
        this.id = reservation.getId();
        this.sectionName = reservation.getSectionName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public ReservationResponseModel() {
    }

    public UserResponseModel getUser() {
        return user;
    }

    public void setUser(UserResponseModel user) {
        this.user = user;
    }

    public Set<ItemResponseModel> getItems() {
        return items;
    }

    public void setItems(Set<ItemResponseModel> items) {
        this.items = items;
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

    public ReservationType getType() {
        return type;
    }

    public void setType(ReservationType type) {
        this.type = type;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
}
