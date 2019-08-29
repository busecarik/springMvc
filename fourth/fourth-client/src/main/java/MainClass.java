import com.busecarik.service.User;
import com.busecarik.service.UserService;
import com.busecarik.service.UserService_Service;

public class MainClass {

    public static void main(String[] arg) {
        //URL: http://localhost:8080/fourth/userservice?wsdl

        UserService service = new UserService_Service().getUserServiceImpPort();

        User user = service.getUser("buse");

        User userInsert = new User();
        userInsert.setUsername("mary");
        userInsert.setEmail("mary@gmail.com");
        userInsert.setPassword("asd");
        userInsert.setBirthday("10-04-1997");
        userInsert.setEnabled(true);
        userInsert.setSex(1);

        service.addUser(user);

        service.deleteUser("john");

        System.out.println(user.getEmail());
    }
}
