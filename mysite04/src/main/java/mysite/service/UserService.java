package mysite.service;

import mysite.repository.UserRepository;
import mysite.vo.UserVo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final int PAGE_SIZE = 5;

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void join(UserVo userVo) {
        userRepository.insert(userVo);
    }

    public UserVo getUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public UserVo getUser(Long id) {
        return userRepository.findById(id);
    }

    public UserVo getUser(String email) {
        return userRepository.findByEmail(email);
    }

    public void update(UserVo userVo) {
        userRepository.update(userVo);
    }
}
