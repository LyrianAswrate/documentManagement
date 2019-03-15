package hu.due.document.management.service.user;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
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

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO getUserByUsername(String username) {
		return modelMapper.map(repository.findByUsername(username), UserDTO.class);
	}

	@Override
	public List<UserDTO> getAllActiveUser() {
		List<UserDTO> users = new ArrayList<>();
		repository.findAll().stream().forEach(entity -> users.add(modelMapper.map(entity, UserDTO.class)));
		return users;
	}

	@Transactional
	@Override
	public void deleteUser(Long userId) {
		repository.deleteById(userId);
	}

	@Transactional
	@Override
	public void save(UserDTO user) {
		User entity = null;
		if (user.getId() == null) {
			entity = new User();
		} else {
			entity = repository.getOne(user.getId());
		}

		entity.setFullname(user.getUsername());
		entity.setFullname(user.getFullname());
		entity.setPassword(user.getPassword());
		entity.setRole(user.getRole());
		entity.setEmail(user.getEmail());
		entity.setEnabled(user.getEnabled());

		if (entity.getId() == null) {
			repository.save(entity);
		}
	}

}
