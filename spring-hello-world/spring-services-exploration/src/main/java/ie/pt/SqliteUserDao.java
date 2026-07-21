package ie.pt;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDao
 * This is a class for reading and writing to the users table in the db
 *  Note: make sure to call .close() when finished with the database
 * @author Aidan Killeen
 * @version 1.0
 */
@Repository // use @Repository instead of @Component for Database related beans
public class SqliteUserDao implements UserDao {

    private static String defaultFileName = "C:\\work\\training\\java\\userdb.db";
    private Connection conn;

    /**
     * create the dao object using the default filename
     *
     */
    public SqliteUserDao() {

        // call the other constructor
        // with the defaultFileName
        this(defaultFileName);
    }

    /**
     *
     * @param fileName the filename for the database.
     *                 note: file will be created if it doesn't exist
     */
    public SqliteUserDao(String fileName) {
        String connectionString = "jdbc:sqlite:" + fileName;
        try {
            conn = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createDatabase() {
        try {
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("DROP TABLE IF EXISTS users");

            stmt.executeUpdate("""
                CREATE TABLE users (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        email TEXT NOT NULL,
                        active INTEGER NOT NULL
                       )
                """);
            stmt.executeUpdate("""
                INSERT INTO users
                (name, email, active)
                VALUES('Zoe', 'zoe@gmail.com', 1), 
                    ('Yvonne', 'yvonne@gmail.com', 0), 
                    ('Wendy', 'wendy@gmail.com', 1), 
                    ('Xavier', 'xav@gmail.com', 0), 
                    ('Veronica', 'v@gmail.com', 1); 
                """);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getUsers() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                boolean active = rs.getBoolean("active");

                User u = new User(id, name, email, active);
                users.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<User> getActiveUsers(boolean active) {

        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE active = " + (active ? 1 : 0);

        // TODO - finish this


        return users;
    }
    public User getUser(int id) {

        User u = null;

        // note - we can improve this!
        String sql = "SELECT * FROM users WHERE id = " + id;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {

                String name = rs.getString("name");
                String email = rs.getString("email");
                boolean active = rs.getBoolean("active");

                u = new User(id, name, email, active);

            } else {
                // TODO - decide what to do if user not found
                // System.out.println("user not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return u;
    }


    public boolean deleteUser(int id) {

        String sql = "DELETE FROM users WHERE id = " + id;

        try {
            Statement stmt = conn.createStatement();
            int n = stmt.executeUpdate(sql);

            if (n == 0) {
                // no records deleted
                // TODO - is this an error?
            }
            return n != 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User updateUser(User u) {

        /*
        String sql = "UPDATE users " +
                "SET " +
                "name = '" + u.getName() + "', " +
                "email = '"+ u.getEmail() + "'," +
                "active = " + (u.isActive() ? 1 : 0) +
                " WHERE id = " + u.getId();
        */

        String sql = """
                        UPDATE users 
                        SET name = ?,
                        email = ?,
                        active = ?
                        WHERE id = ?""";

        //System.out.println(sql);
        try {
            //Statement stmt = conn.createStatement();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getEmail());
            stmt.setBoolean(3, u.isActive());
            stmt.setInt(4, u.getId());
            int n = stmt.executeUpdate();
            if (n == 0) {
                // no records updated
                // TODO - is this an error
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return u;
    }

    public User addUser(User userToAdd) {

        User addedUser = null;

        /*
        String sql = String.format("""
                INSERT INTO users
                (Name, Email, Active)
                VALUES('%s', '%s', %d)
                """, userToAdd.getName(),
                userToAdd.getEmail(),
                userToAdd.isActive() ? 1 : 0);
        */
        // a prepared statement gets the db engine to the substitutions
        // use ?'s as place holders
        String sql = """
                INSERT INTO users
                (Name, Email, Active)
                VALUES(?, ?, ?)""";
        // System.out.println(sql);

        try {
            //Statement stmt = conn.createStatement();
            PreparedStatement stmt = conn.prepareStatement(sql);
            // replace the ?s with values
            // NB: the database engine is doing this
            // we are not putting values directly into the sql
            stmt.setString(1, userToAdd.getName());
            stmt.setString(2, userToAdd.getEmail());
            stmt.setBoolean(3, userToAdd.isActive());

            stmt.executeUpdate();

            // Get the newly created id
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int newId = rs.getInt(1);
                addedUser = new User(newId,
                                    userToAdd.getName(),
                                    userToAdd.getEmail(),
                                    userToAdd.isActive());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return addedUser;
    }
}
