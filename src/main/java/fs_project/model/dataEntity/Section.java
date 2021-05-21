package fs_project.model.dataEntity;

import javax.persistence.*;
import java.util.List;


/**
 * The type Section.
 */
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

    /**
     * Instantiates a new Section.
     *
     * @param id    the id
     * @param name  the name
     * @param items the items
     */
    public Section(long id, String name, List<Item> items) {
        this.id = id;
        this.name = name;
        this.items = items;
    }

    /**
     * Instantiates a new Section.
     */
    public Section() {

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
     * Sets room id.
     *
     * @param roomId the room id
     */
    public void setRoomId(Room roomId) {
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
     * @param items the items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }
}
