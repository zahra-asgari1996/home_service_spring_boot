package ir.maktab.data.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
    @Column
    private String city;
    @Column
    private String street;
    @Column
    private String alley;
    @Column
    private String plaque;

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Address setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getAlley() {
        return alley;
    }

    public Address setAlley(String alley) {
        this.alley = alley;
        return this;
    }

    public String getPlaque() {
        return plaque;
    }

    public Address setPlaque(String plaque) {
        this.plaque = plaque;
        return this;
    }
}
