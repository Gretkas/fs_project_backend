package fs_project.model.requestModel;

import fs_project.model.Attributes.ReservationType;
import fs_project.model.dataEntity.Item;
import fs_project.model.dataEntity.Reservation;
import fs_project.model.dataEntity.User;
import javassist.tools.web.BadHttpRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class ReservationPostRequestModel {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Item> items;
    private ReservationType type;
    private String title;


    public Reservation convert(User u) throws BadHttpRequest {
        validate();
        return new Reservation(u, startTime, endTime, items, type, title);
    }

    private void validate() throws BadHttpRequest {
        if(startTime.isAfter(endTime) || items.size() <= 0) throw new BadHttpRequest();
    }

    public ReservationPostRequestModel() {
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

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public ReservationType getType() {
        return type;
    }

    public void setType(ReservationType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
