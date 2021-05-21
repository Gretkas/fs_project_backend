package fs_project.model.dataEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * The type Room.
 */
@Entity
@Table(name = "Room")
public class Room {

    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;
    private String location;

    private int maxNumber;

    private String name;
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name="room_item",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name="room_section",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "section_id")
    )
    private List<Section> sections;

    /**
     * Instantiates a new Room.
     *
     * @param id          the id
     * @param description the description
     * @param location    the location
     * @param name        the name
     * @param items       the items
     * @param sections    the sections
     */
    public Room(long id, String description, String location, String name, List<Item> items, List<Section> sections) {
        this.id = id;
        this.description = description;
        this.location = location;
        this.name = name;
        this.items = items;
        this.sections = sections;
    }

    /**
     * Instantiates a new Room.
     */
    public Room() {

    }


    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
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

    /**
     * Gets sections.
     *
     * @return the sections
     */
    public List<Section> getSections() {
        return sections;
    }

    /**
     * Gets max number.
     *
     * @return the max number
     */
    public int getMaxNumber() {
        return maxNumber;
    }

    /**
     * Sets max number.
     *
     * @param maxNumber the max number
     */
    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }


    /**
     * Sets sections.
     *
     * @param sections the sections
     */
    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", items=" + items +
                ", sections=" + sections +
                '}';
    }
}
