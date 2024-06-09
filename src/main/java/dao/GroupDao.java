package dao;

import entity.Group;
import entity.Student;
import util.ConnectionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDao implements Dao<Integer, Group> {

    private static final GroupDao INSTANCE = new GroupDao();

    public static GroupDao getInstance() {
        return INSTANCE;
    }

    private GroupDao() {
    }

    private static final String FIND_ALL = """
            SELECT * FROM groups
            """;

    @Override
    public List<Group> findAll() {
        try {
            var connection = ConnectionManager.get();
            var prepareStatement = connection.prepareStatement(FIND_ALL);
            var resultSet = prepareStatement.executeQuery();
            List<Group> groups = new ArrayList<>();
            while (resultSet.next()) {
                groups.add(new Group(
                        resultSet.getObject("id", Integer.class),
                        resultSet.getObject("group_no", Integer.class)
                ));
            }
            return groups;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student findById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(Group entity) {

    }

    @Override
    public Group save(Group entity) throws SQLException {
        return null;
    }
}
