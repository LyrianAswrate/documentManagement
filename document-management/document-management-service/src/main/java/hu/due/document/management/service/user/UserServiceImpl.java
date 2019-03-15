package hu.due.document.management.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.due.document.management.dto.UserDTO;
import hu.due.document.management.service.entity.User;
import hu.due.document.management.service.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDTO getUserByUsername(String username) {
		return mapToDTO(repository.findByUsername(username));
	}

	private UserDTO mapToDTO(User user) {
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setPassword(user.getPassword());
		dto.setRole(user.getRole());
		dto.setFullname(user.getFullname());
		dto.setEnabled(user.getEnabled());
		dto.setEmail(user.getEmail());
		return dto;
	}

}
