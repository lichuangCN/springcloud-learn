package site.muzhi.springcloud.service;

import site.muzhi.springcloud.domain.User;

import java.util.List;

/**
 * @author: LiChuang
 * @date: 2020/02/15
 * @description:
 */
public interface IUserService {
    void create(User user);

    User query(Long id);

    void update(User user);

    void delete(Long id);

    User getByUsername(String username);

    List<User> queryUserByIds(List<Long> ids);
}
