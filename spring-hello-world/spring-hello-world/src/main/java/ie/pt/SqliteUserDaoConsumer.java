package ie.pt;

import java.util.List;

public class SqliteUserDaoConsumer {

    public static void main(String[] args) {


        UserDao dao = new SqliteUserDao();

        List<User> users = dao.getUsers();

        for (User user : users) {
            System.out.println(user);
        }

        dao.close();
    }
}
