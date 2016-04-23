package by.bsuir.mpp.transpony.entity.document;

public enum DocumentType {
    FUEL_CONSUMPTION("/Users/Andrew/Develop/fuel_consumption"),
    PROFIT("/Users/Andrew/Develop/profit"),
    ROUTE("/Users/Andrew/Develop/route"),
    WAYBILL("/Users/Andrew/Develop/waybill"),
    STATUS_TRIP("/Users/Andrew/Develop/status_trip");

    private String path;

    DocumentType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
