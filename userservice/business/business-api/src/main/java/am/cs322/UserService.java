package am.cs322;

import am.cs322.model.UserDTO;

public interface UserService {

    UserDTO createUser(String firstName, String lastName);

}
