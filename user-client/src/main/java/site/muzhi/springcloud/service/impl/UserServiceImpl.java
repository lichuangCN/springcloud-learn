package site.muzhi.springcloud.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import site.muzhi.springcloud.domain.User;
import site.muzhi.springcloud.service.IUserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: LiChuang
 * @date: 2020/02/15
 * @description:
 */
@Service
public class UserServiceImpl implements IUserService {
    List<User> userList;

    @PostConstruct
    public void initData() {
        userList = new ArrayList<>(16);
        userList.add(new User(1L, "macro", "123456"));
        userList.add(new User(2L, "andy", "123456"));
        userList.add(new User(3L, "mark", "123456"));
    }

    @Override
    public void create(User user) {
        userList.add(user);
    }

    @Override
    public User query(Long id) {
        List<User> users = userList.stream()
                .filter(userItem -> userItem.getId().equals(id))
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(users)) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public void update(User user) {
        userList.stream()
                .filter(userItem -> user.getId().equals(user.getId()))
                .forEach(item -> {
                    item.setUsername(user.getUsername());
                    item.setPassword(user.getPassword());
                });
    }

    @Override
    public void delete(Long id) {
        User user = query(id);
        if (user != null) {
            userList.remove(user);
        }
    }

    @Override
    public User getByUsername(String username) {
        List<User> users = userList.stream()
                .filter(userItem -> userItem.getUsername().equals(username))
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(users)) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public List<User> queryUserByIds(List<Long> ids) {
        List<User> users = userList.stream()
                .filter(userItem -> ids.contains(userItem.getId()))
                .collect(Collectors.toList());
        return users;
    }
}
