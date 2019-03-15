package hu.due.document.management.service.user;

import hu.due.document.management.dto.UserDTO;

public interface UserService {

	public UserDTO getUserByUsername(String username);

}
