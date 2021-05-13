package fs_project.model.dataEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Room")
public class Room {

    @Id
    @Column(name = "room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @ManyToMany
    @JoinTable(
            name="section_node",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Item> items;

    @ManyToMany
    @JoinTable(
            name="section_node",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "section_id")
    )
    private Set<Section> sections;


    public Room(long id, String name, Set<Item> items, Set<Section> sections) {
        this.id = id;
        this.name = name;
        this.items = items;
        this.sections = sections;
    }

    public Room() {

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

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Set<Section> getSections() {
        return sections;
    }

    public void setSections(Set<Section> sections) {
        this.sections = sections;
    }
}
