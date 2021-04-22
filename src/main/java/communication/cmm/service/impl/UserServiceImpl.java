package communication.cmm.service.impl;


import communication.cmm.mapper.UserMapper;
import communication.cmm.model.User;
import communication.cmm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private UserMapper userMapper;
    @Override
    public void insert(User user) {
          userMapper.insert(user);
    }
}
