import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

public class StudentDaoTest {

    private static PostgreSQLContainer<?> postgresContainer;
    private static Connection connection;

    @BeforeAll
    public static void setUp() throws SQLException {
        postgresContainer = new PostgreSQLContainer<>("postgres:13.1")
                .withDatabaseName("testdb")
                .withUsername("testuser")
                .withPassword("testpass");
        postgresContainer.start();

        connection = DriverManager.getConnection(
                postgresContainer.getJdbcUrl(),
                postgresContainer.getUsername(),
                postgresContainer.getPassword()
        );

        String createTableSQL = "CREATE TABLE students (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "email VARCHAR(255), " +
                "gender VARCHAR(10), " +
                "group_id INT, " +
                "class_id INT" +
                ")";
        connection.prepareStatement(createTableSQL).execute();
    }

    @AfterAll
    public static void tearDown() throws SQLException {
        connection.close();
        postgresContainer.stop();
    }

    @Test
    void testSave() throws SQLException {
        StudentDao studentDao = new StudentDao();
        studentDao.setConnection(connection); // Assuming you have a setter for the connection in your DAO

        Student student = new Student();
        student.setName("John Doe");
        student.setEmail("john.doe@example.com");
        student.setGender(Gender.MALE);
        student.setGroup(1);
        student.setClasses(2);

        Student savedStudent = studentDao.save(student);

        assertEquals(1, savedStudent.getId());
        assertEquals("John Doe", savedStudent.getName());
        assertEquals("john.doe@example.com", savedStudent.getEmail());
        assertEquals(Gender.MALE, savedStudent.getGender());
        assertEquals(1, savedStudent.getGroup());
        assertEquals(2, savedStudent.getClasses());
    }
}

// Your DAO implementation, assuming it implements an interface with the save method
class StudentDao implements SomeDaoInterface {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private static final String SAVE_SQL =
            "INSERT INTO students (name, email, gender, group_id, class_id)" +
                    " VALUES (?, ?, ?, ?, ?)";

    @Override
    public Student save(Student entity) throws SQLException {
        var preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS);
        preparedStatement.setObject(1, entity.getName());
        preparedStatement.setObject(2, entity.getEmail());
        preparedStatement.setObject(3, entity.getGender().name());
        preparedStatement.setObject(4, entity.getGroup());
        preparedStatement.setObject(5, entity.getClasses());

        preparedStatement.executeUpdate();

        var generatedKeys = preparedStatement.getGeneratedKeys();
        generatedKeys.next();
        entity.setId(generatedKeys.getObject("id", Integer.class));

        return entity;
    }
}

// Sample classes for Student and Gender enum
class Student {
    private int id;
    private String name;
    private String email;
    private Gender gender;
    private int group;
    private int classes;

    // getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }
    public int getGroup() { return group; }
    public void setGroup(int group) { this.group = group; }
    public int getClasses() { return classes; }
    public void setClasses(int classes) { this.classes = classes; }
}

enum Gender {
    MALE, FEMALE
}

// Assuming there's an interface defining the save method
interface SomeDaoInterface {
    Student save(Student entity) throws SQLException;
}
