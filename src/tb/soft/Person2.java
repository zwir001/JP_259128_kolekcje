package tb.soft;

import java.util.Objects;

public class Person2 extends Person{

    public Person2(String first_name, String last_name) throws PersonException {
        super(first_name, last_name);
    }

    public Person2(String first_name, String last_name, int birth_year, String job_name) {
        super(first_name, last_name, birth_year, job_name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person2 person2 = (Person2) o;
        return getBirthYear() == person2.getBirthYear() && getFirstName().equals(person2.getFirstName()) && getLastName().equals(person2.getLastName()) && getJob() == person2.getJob();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getBirthYear(), getJob());
    }
    }




