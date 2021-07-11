package cn.leithda.dao;

import cn.leithda.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息表(SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2021-07-09 14:14:58
 */
public interface SysUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    SysUser queryById(Long userId);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUser 实例对象
     * @return 对象列表
     */
    List<SysUser> selectAll(SysUser sysUser);

}

