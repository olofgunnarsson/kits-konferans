package se.kits;

import org.junit.Test;
import rx.Observable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("Convert2MethodRef")
public class TestObs {

    private Person anna = new Person("Anna", "Johansson");
    private Person johan = new Person("Johan", "Johansson");
    private Person selma = new Person("Selma", "Johansson");
    private Person petter = new Person("Petter", "Karlsson");
    private Person fredrik = new Person("Fredrik", "Karlsson");
    private Person david = new Person("David", "Karlsson");

    private HashMap<Person, List<Person>> children = new HashMap<Person, List<Person>>() {{
        put(anna, Arrays.asList(johan, selma));
        put(petter, Arrays.asList(fredrik, david));
    }};


    private Observable<Person> people = Observable.just(anna, johan, petter, fredrik);

    @Test
    public void map() {
        Observable<Integer> integers = Observable.just(1, 2, 3, 4, 5, 6);
        integers
                .map(integer -> integer * 2)
                .subscribe(System.out::println);
    }

    @Test


    public void flatMap() {
        Observable<Person> people = Observable.just(anna, petter);
        people
                .flatMap((person) -> getRelativesObservable(person))
                .subscribe(System.out::println);
    }

    public Observable<Person> getRelativesObservable(Person person)


    {
        return Observable.from(children.get(person));
    }

    @Test
    public void groupBy1() {
        people
                .groupBy(person -> person.lastName)
                .flatMap(groupedByLastName ->
                        groupedByLastName.toList()
                                .map(groupedPeople ->
                                        "People with lastname " + groupedByLastName.getKey() +
                                                " are " + Arrays.toString(groupedPeople.toArray())))
                .subscribe(System.out::println);
    }

    @Test
    public void groupBy() throws Exception {
        Observable.from(Arrays.asList(
                "a", "b", "c",
                "a", "b", "c",
                "a", "b", "c",
                "a", "b", "c"))
                .groupBy(s -> s)
                .flatMap(grouped -> grouped.reduce((s1, s2) -> s1 + s2))
                .subscribe(System.out::println);

    }

    public static class Person {
        public final String firstName;
        public final String lastName;

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String toString() {
            return firstName + " " + lastName;
        }
    }
}
