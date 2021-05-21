package fs_project.model.dataEntity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Item.
 */
@Entity
@Table(name = "Item")
public class Item {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(
            name="room_id",
            updatable = true
    )
    private Room roomId;

    @ManyToMany
    @JoinTable(
            name="reservation_item",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id")
    )
    private List<Reservation> reservations;

    private String name;

    /**
     * Instantiates a new Item.
     *
     * @param itemId the item id
     * @param roomId the room id
     * @param name   the name
     */
    public Item(long itemId, Room roomId, String name) {
        this.itemId = itemId;
        this.roomId = roomId;
        this.name = name;
    }

    /**
     * Instantiates a new Item.
     */
    public Item() {

    }

    /**
     * Gets room id.
     *
     * @return the room id
     */
    public Room getRoomId() {
        return roomId;
    }

    /**
     * Gets reservations.
     *
     * @return the reservations
     */
    public List<Reservation> getReservations() {
        return reservations;
    }

    /**
     * Sets reservations.
     *
     * @param reservations the reservations
     */
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
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
     * Gets room.
     *
     * @return the room
     */
    public Room getRoom() {
        return roomId;
    }

    /**
     * Sets room id.
     *
     * @param roomId the room id
     */
    public void setRoomId(Room roomId) {
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
