package by.bsuir.mpp.transpony.entity;

import java.util.List;

public class Route {

    protected Integer id;
    protected Float totalLength;
    protected Integer count;
    protected User owner;
    protected List<CheckPoint> points;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(Float totalLength) {
        this.totalLength = totalLength;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<CheckPoint> getPoints() {
        return points;
    }

    public void setPoints(List<CheckPoint> points) {
        this.points = points;
    }
}
