package by.bsuir.mpp.transpony.entity;

public class Cargo {

    protected Integer id;
    protected String name;
    protected Integer weight;
    protected Integer volume;
    protected String cargoType;
    protected Provider provider;
    protected Float prise;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getCargoType() {
        return cargoType;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Float getPrise() {
        return prise;
    }

    public void setPrise(Float prise) {
        this.prise = prise;
    }
}
