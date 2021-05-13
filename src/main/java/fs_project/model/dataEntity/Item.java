package fs_project.model.dataEntity;


import javax.persistence.*;

@Entity
@Table(name = "Item")
public class Item {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;

    @ManyToOne
    @JoinColumn(
            name="room_id",
            updatable = true
    )
    private Room roomId;

    private String name;

    public Item(long itemId, Room roomId, String name) {
        this.itemId = itemId;
        this.roomId = roomId;
        this.name = name;
    }

    public Item() {

    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
