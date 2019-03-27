package com.neno.repository;

import com.neno.vo.UserVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: root
 * @Date: 2019/3/26 23:39
 * <p>
 * 1、在数据库里面创建视图
 * 2、在项目里创建视图对应的实体类
 * 如下：hibernate实体类中必须有主键
 * @Entity
 * @Immutable
 * @Subselect(value = "select name,age from v_user")
 * public class UserVo implements Serializable {
 * @Id
 * private String name;
 * }
 */
@Repository
public interface UserVoRepository extends JpaRepository<UserVo, Object> {

}
