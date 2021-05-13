package fs_project.model.requestModel;

import fs_project.model.dataEntity.Item;

import java.util.Set;

public class ReservationAvailabilityRequestModel {
    private Set<Item> items;

    public ReservationAvailabilityRequestModel() {
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
