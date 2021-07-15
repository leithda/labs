package cn.leithda.controller;

import cn.leithda.entity.SysUser;
import cn.leithda.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 用户信息表(SysUser)表控制层
 *
 * @author makejava
 * @since 2021-07-09 14:14:58
 */
@RestController
@RequestMapping("sysUser")
@Slf4j
public class SysUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;

    /**
     * 新增数据
     */
    @PostMapping("save")
    public int save() {
        return sysUserService.save();
    }

    /**
     * 根据主键删除数据
     *
     * @param userId 主键
     */
    @PostMapping("delete/{userId}")
    public int delete(@PathVariable("userId") Long userId) {
        return sysUserService.delete(userId);
    }

    /**
     * 修改数据
     */
    @PostMapping("update")
    public int update() {
        return sysUserService.update();
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     */
    @GetMapping("selectOne")
    public SysUser selectOne(Long id) {
        return this.sysUserService.queryById(id);
    }

    /**
     * 查询所有数据
     */
    @GetMapping("selectAll")
    public List<SysUser> selectAll() {
        return sysUserService.selectAll();
    }

    /**
     * 新增后查询
     */
    @PostMapping("selectAfterInsert")
    public List<SysUser> selectAfterInsert() {
        return sysUserService.selectAfterInsert();
    }

    /**
     * 修改后查询
     */
    @PostMapping("selectAfterUpdate")
    public List<SysUser> selectAfterUpdate() {
        return sysUserService.selectAfterUpdate();
    }

    /**
     * 删除后查询
     *
     * @param userId 主键
     */
    @PostMapping("selectAfterDelete/{userId}")
    public List<SysUser> selectAfterDelete(@PathVariable("userId") Long userId) {
        return sysUserService.selectAfterDelete(userId);
    }
}
