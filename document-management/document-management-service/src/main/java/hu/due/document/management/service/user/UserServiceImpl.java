package hu.due.document.management.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.due.document.management.dto.CustomePageDTO;
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
    public List<UserDTO> getAllUsers() {
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

        entity.setUsername(user.getUsername());
        entity.setFullname(user.getFullname());
        entity.setPassword(user.getPassword());
        entity.setRole(user.getRole());
        entity.setEmail(user.getEmail());
        entity.setEnabled(user.getEnabled());

        if (entity.getId() == null) {
            repository.save(entity);
        }
    }

    @Override
    public CustomePageDTO<UserDTO> findAll(Pageable pageable) {
        Page<User> page = repository.findAll(pageable);
        CustomePageDTO<UserDTO> customePage = new CustomePageDTO<>();
        customePage.setContent(page.getContent().stream().map(map -> modelMapper.map(map, UserDTO.class)).collect(Collectors.toList()));
        customePage.setTotalElements(page.getTotalElements());
        return customePage;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        UserDTO user = null;
        Optional<User> opt = repository.findById(userId);
        if (opt.isPresent()) {
            user = modelMapper.map(opt.get(), UserDTO.class);
        }
        return user;
    }

}
