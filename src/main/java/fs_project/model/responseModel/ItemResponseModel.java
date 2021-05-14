package fs_project.model.responseModel;

import fs_project.model.dataEntity.Item;
import fs_project.model.dataEntity.Room;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ItemResponseModel {

    private long itemId;

    private long roomId;

    private String name;


    public ItemResponseModel(Item item) {
        this.itemId = item.getItemId();
        this.roomId = item.getRoom().getId();
        this.name = item.getName();
    }

    public ItemResponseModel() {
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
