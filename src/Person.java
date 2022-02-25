public class Person {

    private int idPerson;
    private String name;
    private String surname;
    private int age;
    private String email;

    public Person(int idPerson, String name, String surname, int age, String email) {
        this.idPerson = idPerson;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int newIdPerson) {
        idPerson = newIdPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
