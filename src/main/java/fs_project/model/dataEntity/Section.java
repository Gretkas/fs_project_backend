package fs_project.model.dataEntity;

import javax.persistence.*;
import java.util.Set;


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
            name="section_node",
            joinColumns = @JoinColumn(name = "section_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Item> items;

    public Section(long id, String name, Set<Item> items) {
        this.id = id;
        this.name = name;
        this.items = items;
    }

    public Section() {

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
}
