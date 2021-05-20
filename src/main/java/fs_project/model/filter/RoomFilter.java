package fs_project.model.filter;
//fra sys2
public class RoomFilter {
    private RoomSearchCriteria roomSearchCriteria;
    private RoomPage roomPage;

    public RoomFilter(RoomSearchCriteria roomSearchCriteria, RoomPage roomPage) {
        this.roomSearchCriteria = roomSearchCriteria;
        this.roomPage = roomPage;
    }

    public RoomFilter() {
    }

    public RoomSearchCriteria getRoomSearchCriteria() {
        return roomSearchCriteria;
    }

    public void setRoomSearchCriteria(RoomSearchCriteria roomSearchCriteria) {
        this.roomSearchCriteria = roomSearchCriteria;
    }

    public RoomPage getRoomPage() {
        return roomPage;
    }

    public void setRoomPage(RoomPage roomPage) {
        this.roomPage = roomPage;
    }


    @Override
    public String toString() {
        return "RoomFilter{" +
                "roomSearchCriteria=" + roomSearchCriteria +
                ", roomPage=" + roomPage +
                '}';
    }

}
