package cn.leithda.service;

import cn.leithda.entity.SysUser;

import java.util.List;

/**
 * 用户信息表(SysUser)表服务接口
 *
 * @author makejava
 * @since 2021-07-09 14:14:58
 */
public interface SysUserService {

    /**
     * 使用通过ID查询单个用户
     *
     * @param userId 主键
     */
    SysUser queryById(Long userId);


    /**
     * 查询所有用户信息
     */
    List<SysUser> selectAll();

    /**
     * 新增用户
     */
    int save();

    /**
     * 修改用户
     */
    int update();

    /**
     * 删除用户
     *
     * @param userId 主键
     */
    int delete(Long userId);

    /**
     * 新增后查询
     */
    List<SysUser> selectAfterInsert();

    /**
     * 更新后查询
     */
    List<SysUser> selectAfterUpdate();

    /**
     * 删除后查询
     *
     * @param userId 主键
     */
    List<SysUser> selectAfterDelete(Long userId);
}
