package fs_project.mapping.dto;

import fs_project.model.dataEntity.Item;
import fs_project.model.dataEntity.Room;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The type Item response model. Contains relevant information about an item.
 */
public class ItemResponseModel {

    private long itemId;

    private long roomId;

    private String name;


    /**
     * Instantiates a new Item response model.
     *
     * @param item the item
     */
    public ItemResponseModel(Item item) {
        this.itemId = item.getItemId();
        this.roomId = item.getRoom().getId();
        this.name = item.getName();
    }

    /**
     * Instantiates a new Item response model.
     */
    public ItemResponseModel() {
    }

    /**
     * Gets item id.
     *
     * @return the item id
     */
    public long getItemId() {
        return itemId;
    }

    /**
     * Sets item id.
     *
     * @param itemId the item id
     */
    public void setItemId(long itemId) {
        this.itemId = itemId;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
