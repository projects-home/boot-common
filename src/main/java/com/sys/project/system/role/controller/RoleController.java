package com.sys.project.system.role.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sys.framework.web.controller.BaseController;
import com.sys.framework.web.domain.AjaxResult;
import com.sys.framework.web.page.TableDataInfo;
import com.sys.project.system.role.domain.Role;
import com.sys.project.system.role.service.IRoleService;

/**
 * 角色信息
 * 
 *
 */
@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController
{
    private String prefix = "system/role";

    @Autowired
    private IRoleService roleService;

    @GetMapping()
    public String role()
    {
        return prefix + "/role";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Role role)
    {
        startPage();
        List<Role> list = roleService.selectRoleList(role);
        return getDataTable(list);
    }

    /**
     * 新增角色
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存角色
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated Role role)
    {
        return toAjax(roleService.insertRole(role));

    }

    /**
     * 修改角色
     */
    @GetMapping("/edit/{roleId}")
    public String edit(@PathVariable("roleId") Long roleId, ModelMap mmap)
    {
        mmap.put("role", roleService.selectRoleById(roleId));
        return prefix + "/edit";
    }
}