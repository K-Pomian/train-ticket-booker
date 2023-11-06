package pl.pomian.trainticketbooker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pomian.trainticketbooker.models.User;
import pl.pomian.trainticketbooker.models.UserDto;
import pl.pomian.trainticketbooker.repositories.UserRepository;

@Service
public class UserManagementService {

    private UserRepository userRepository;

    @Autowired
    public UserManagementService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        return UserDto.fromUser(user);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUserPassword(Long id, byte[] newPassword) {
        userRepository.updatePasswordById(newPassword, id);
    }
}
