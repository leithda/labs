package cn.leithda.controller;

import cn.leithda.entity.SysUser;
import cn.leithda.service.SysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息表(SysUser)表控制层
 *
 * @author makejava
 * @since 2021-07-09 14:14:58
 */
@RestController
@RequestMapping("sysUser")
public class SysUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysUser selectOne(Long id) {
        return this.sysUserService.queryById(id);
    }

    /**
     * 查询所有用户，默认查询从库
     */
    @GetMapping("selectAll")
    public List<SysUser> selectAll(){
        return sysUserService.selectAll();
    }

}
