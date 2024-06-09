package dao;

import entity.Gender;
import entity.Student;
import util.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;



public class StudentDao implements Dao<Integer, Student> {

    private static final StudentDao INSTANCE = new StudentDao();


    private static final String SAVE_SQL =
            "INSERT INTO students (name, email, gender, group_id, class_id)" +
                    " VALUES (?, ?, ?, ?, ?)";

    private static final String FINDBYID_SQL =
    "SELECT id, name, email, gender, group_id, class_id FROM students WHERE id = (?)";

    private static final String FINDBYGROUP_ID_SQL =
    "SELECT id, name, email, gender, group_id, class_id FROM students WHERE group_id = (?)";

    @Override
    public Student save(Student entity) throws SQLException {
        var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS); {
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

    @Override
    public List<Student> findAll() {
        return null;
    }

    public List<Student> findAllByGroupId(Integer groupId) {
        List<Student> students = new ArrayList<>();
        try {
            var connection = ConnectionManager.get();
            PreparedStatement preparedStatement = connection.prepareStatement(FINDBYGROUP_ID_SQL);
            preparedStatement.setInt(1, groupId);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                students.add(new Student(
                        resultSet.getObject("id", Integer.class),
                        resultSet.getObject("name", String.class),
                        resultSet.getObject("email", String.class),
                        Gender.valueOf(resultSet.getObject("gender", String.class)),
                        resultSet.getObject("group_id", Integer.class),
                        resultSet.getObject("class_id", Integer.class)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    @Override
    public Student findById(Integer id) {
        Student entity = null;
        try (var connection = ConnectionManager.get();
             PreparedStatement ps = connection.prepareStatement(FINDBYID_SQL)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    entity = new Student();
                    entity.setId(rs.getInt("id"));
                    entity.setName(rs.getString("name"));
                    entity.setEmail(rs.getString("email"));
                    entity.setGender(Gender.valueOf(rs.getString("gender")));
                    entity.setGroup(rs.getInt("group_id"));
                    entity.setClasses((rs.getInt("class_id")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(Student entity) {

    }

    public static StudentDao getInstance() {
        return INSTANCE;
    }

    private StudentDao() {
    }

//User
}