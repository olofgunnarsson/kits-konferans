package se.kits.dresser;

import se.kits.clothing.Clothing;
import se.kits.people.Person;

public class DressedPerson {
    public final Person person;
    public final Clothing clothing;

    public DressedPerson(Person person, Clothing clothing) {
        this.person = person;
        this.clothing = clothing;
    }
}
