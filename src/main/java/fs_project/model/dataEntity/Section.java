package fs_project.model.dataEntity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Section")
public class Section {

    @Id
    @Column(name = "section_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name="section_item",
            joinColumns = @JoinColumn(name = "section_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(
            name="room_id",
            updatable = true
    )
    private Room roomId;

    public Section(long id, String name, List<Item> items) {
        this.id = id;
        this.name = name;
        this.items = items;
    }

    public Section() {

    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
