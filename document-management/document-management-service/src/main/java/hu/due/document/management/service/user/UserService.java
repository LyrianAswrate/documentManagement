package hu.due.document.management.service.user;

import java.util.List;

import org.springframework.data.domain.Pageable;

import hu.due.document.management.dto.CustomePageDTO;
import hu.due.document.management.dto.UserDTO;

public interface UserService {

    public UserDTO getUserByUsername(String username);

    public List<UserDTO> getAllUsers();

    public void deleteUser(Long userId);

    public void save(UserDTO user);

    public CustomePageDTO<UserDTO> findAll(Pageable pageable);

    public UserDTO getUserById(Long userId);

}
