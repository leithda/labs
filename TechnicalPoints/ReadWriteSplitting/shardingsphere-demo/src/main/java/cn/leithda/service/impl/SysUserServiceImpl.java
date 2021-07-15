package cn.leithda.service.impl;

import cn.leithda.dao.SysUserDao;
import cn.leithda.entity.SysUser;
import cn.leithda.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 用户信息表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2021-07-09 14:17:09
 */
@Slf4j
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserDao sysUserDao;

    @Override
    public SysUser queryById(Long userId) {
        return this.sysUserDao.queryById(userId);
    }

    @Override
    public List<SysUser> selectAll() {
        return sysUserDao.selectAll(new SysUser());
    }

    @Override
    public int save() {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(ThreadLocalRandom.current().nextLong());
        sysUser.setLoginName(UUID.randomUUID().toString().substring(8).replace("-","&"));
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        return sysUserDao.insert(sysUser);
    }

    @Override
    public int update() {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(1L);
        long nextLong = ThreadLocalRandom.current().nextLong();
        log.info("修改后deptId: {}", nextLong);
        sysUser.setDeptId(nextLong);
        sysUser.setUpdateTime(new Date());
        return sysUserDao.update(sysUser);
    }

    @Override
    public int delete(Long userId) {
        return sysUserDao.delete(userId);
    }

    @Override
    public List<SysUser> selectAfterInsert() {
        save();
        return selectAll();
    }

    @Override
    @Transactional
    public List<SysUser> selectAfterUpdate() {
        update();
        return selectAll();
    }

    @Override
    public List<SysUser> selectAfterDelete(Long userId) {
        HintManager.getInstance().setMasterRouteOnly();
        delete(userId);
        return selectAll();
    }
}
