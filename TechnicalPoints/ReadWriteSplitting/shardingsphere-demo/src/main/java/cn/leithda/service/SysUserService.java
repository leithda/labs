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
     * 使用主库通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    SysUser queryById(Long userId);


    /**
     * 查询所有用户信息，select*，使用从库
     */
    List<SysUser> selectAll();

}
