package tb.soft;

import java.util.Objects;
/* Klasa Person2 zdefiniowane metody hashcode oraz equals */
public class Person2 extends Person {

    public Person2(String first_name, String last_name) throws PersonException {
        super(first_name, last_name);
    }

    public Person2(String first_name, String last_name, int birth_year) {
        super(first_name, last_name, birth_year);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return getBirthYear() == person.getBirthYear() && getFirstName().equals(person.getFirstName()) && getLastName().equals(person.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getBirthYear());
    }
}




