package fs_project.mapping.dto;

import fs_project.model.dataEntity.Item;

import java.util.List;


public class ReservationAvailabilityRequestModel {
    private List<Item> items;

    public ReservationAvailabilityRequestModel() {
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ReservationAvailabilityRequestModel{" +
                "items=" + items +
                '}';
    }
}
