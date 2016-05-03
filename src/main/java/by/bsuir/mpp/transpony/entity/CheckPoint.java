package by.bsuir.mpp.transpony.entity;

public class CheckPoint {
    protected Integer id;
    protected Float x;
    protected Float y;
    protected String name;
    protected String pointType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPointType() {
        return pointType;
    }

    public void setPointType(String pointType) {
        this.pointType = pointType;
    }

    public boolean equals(CheckPoint checkPoint) {
        if (getName().equals(checkPoint.getName()) &&
                getPointType().equals(checkPoint.getPointType())) {
            return true;
        }
        return false;
    }
}
