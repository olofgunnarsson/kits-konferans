package se.kits.dresser;

import se.kits.clothing.Clothing;
import se.kits.people.Person;

public class DressedPerson {
    public final Person person;
    public final Clothing shirt;
    public final Clothing pants;
    public final Clothing shoes;

    public DressedPerson(Person person, Clothing shirt, Clothing pants, Clothing shoes) {
        this.person = person;
        this.shirt = shirt;
        this.pants = pants;
        this.shoes = shoes;
    }
}
