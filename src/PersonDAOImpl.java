import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersonDAOImpl implements PersonDAO {

    public Connection connectToDatabase() throws Exception {
        final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DATABASE = null;
        Connection conn = null;
        Properties infoDB = new Properties();
        infoDB.load(new FileInputStream("db.properties"));
        DATABASE = "jdbc:mysql://localhost:3306/" + infoDB.getProperty("TABLE_NAME") + "?user="
                + infoDB.getProperty("USER") + "&password=" + infoDB.getProperty("PASSWORD");
        Class.forName(JDBC_DRIVER).getDeclaredConstructor().newInstance();
        System.out.println("Connecting to a selected database...");
        conn = DriverManager.getConnection(DATABASE);
        return conn;
    }

    @Override
    public void addPerson(Person Person) {

        PreparedStatement statement = null;
        Connection conn = null;

        String query = "INSERT INTO Person VALUES (?, ?, ?, ?, ?)";

        try {
            conn = connectToDatabase();
            System.out.println("Inserting values into the table...");
            statement = conn.prepareStatement(query);
            statement.setInt(1, Person.getIdPerson());
            statement.setString(2, Person.getName());
            statement.setString(3, Person.getSurname());
            statement.setInt(4, Person.getAge());
            statement.setString(5, Person.getEmail());
            statement.executeUpdate();
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception exception) {
                System.out.println("Exception: " + exception);
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception exception) {
                System.out.println("Exception: " + exception);
            }
        }
        System.out.println("Person insterted!");
    }

    @Override
    public Person getPerson(int id) {

        PreparedStatement statement = null;
        Connection conn = null;
        ResultSet resultSet = null;
        Person person = null;

        String query = "SELECT * FROM Person WHERE idPerson=?";

        try {
            conn = connectToDatabase();
            System.out.println("Extracting info from the table...");
            statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idPerson = resultSet.getInt("idPerson");
                String name = resultSet.getString("Name");
                String surname = resultSet.getString("Surname");
                int age = resultSet.getInt("Age");
                String email = resultSet.getString("Email");
                person = new Person(idPerson, name, surname, age, email);
            }

        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception exception) {
                System.out.println("Exception: " + exception);
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception exception) {
                System.out.println("Exception: " + exception);
            }
        }
        return person;
    }

    @Override
    public void getPersonList() {

        List<Person> listPersons = new ArrayList<>();
        PreparedStatement statement = null;
        Connection conn = null;
        ResultSet resultSet = null;
        Person personEntry = null;

        String query = "SELECT * FROM Person";

        try {
            conn = connectToDatabase();
            System.out.println("Extracting info from the table...");
            statement = conn.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idPerson = resultSet.getInt("idPerson");
                String name = resultSet.getString("Name");
                String surname = resultSet.getString("Surname");
                int age = resultSet.getInt("Age");
                String email = resultSet.getString("Email");
                personEntry = new Person(idPerson, name, surname, age, email);
                listPersons.add(personEntry);
            }
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception exception) {
                System.out.println("Exception: " + exception);
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception exception) {
                System.out.println("Exception: " + exception);
            }
        }
        for (Person person : listPersons) {
            System.out.println("Name: " + person.getName() + " - " + "Surname: " + person.getSurname() + " - " + "Age: "
                    + person.getAge() + " - " + "Email: " + person.getEmail());
        }
    }

    @Override
    public void removePerson(int id) {

        PreparedStatement statement = null;
        Connection conn = null;

        String query = "DELETE FROM Person WHERE idPerson=?";

        try {
            conn = connectToDatabase();
            System.out.println("Extracting info from the table...");
            statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception exception) {
            System.out.println("Exception: " + exception);
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception exception) {
                System.out.println("Exception: " + exception);
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception exception) {
                System.out.println("Exception: " + exception);
            }
        }
        System.out.println("Person removed!");
    }
}