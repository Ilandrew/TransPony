package by.bsuir.mpp.transpony.entity;

import by.bsuir.mpp.transpony.entity.user.User;

import java.math.BigDecimal;
import java.util.List;

public class Route {
    protected Integer id;
    protected BigDecimal totalLength;
    protected Integer count;
    protected User owner;
    protected List<CheckPoint> points;
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public BigDecimal getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(BigDecimal totalLength) {
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

    public boolean equals(Route route) {
        return this.getId() == route.getId();
    }
}
