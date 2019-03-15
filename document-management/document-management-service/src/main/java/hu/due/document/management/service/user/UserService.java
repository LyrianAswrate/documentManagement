package hu.due.document.management.service.user;

import java.util.List;

import hu.due.document.management.dto.UserDTO;

public interface UserService {

	public UserDTO getUserByUsername(String username);

	public List<UserDTO> getAllActiveUser();

	void deleteUser(Long userId);

	void save(UserDTO user);

}
