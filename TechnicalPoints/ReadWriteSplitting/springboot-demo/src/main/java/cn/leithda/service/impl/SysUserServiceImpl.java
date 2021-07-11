package cn.leithda.service.impl;

import cn.leithda.annotations.ReadOnly;
import cn.leithda.entity.SysUser;
import cn.leithda.dao.SysUserDao;
import cn.leithda.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2021-07-09 14:17:09
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserDao sysUserDao;

    @Override
    public SysUser queryById(Long userId) {
        return this.sysUserDao.queryById(userId);
    }

    @Override
    @ReadOnly
    public SysUser queryByIdWithSlave(Long userId) {
        return this.sysUserDao.queryById(userId);
    }

    @Override
    public List<SysUser> selectAll() {
        return sysUserDao.selectAll(new SysUser());
    }
}
