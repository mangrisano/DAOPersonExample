public interface PersonDAO {

    public void getPersonList();

    public Person getPerson(int id);

    public void removePerson(int id);

    public void addPerson(Person Person);

}
