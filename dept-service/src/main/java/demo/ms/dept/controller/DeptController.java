package demo.ms.dept.controller;

import com.github.pagehelper.PageInfo;
import demo.ms.dept.api.DeptClient;
import demo.ms.dept.api.dto.DeptDTO;
import demo.ms.dept.api.dto.DeptQueryDTO;
import demo.ms.dept.config.Response;
import demo.ms.dept.entity.DeptEntity;
import demo.ms.dept.service.DeptService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2018-present, Sinovatio Corporation.
 * All rights reserved.
 * <p/>Created by yanfeng on 2019-05-16 15:59:01.
 *
 * @author yanfeng
 * @since 2019-05-16 15:59:01
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/dept")
public class DeptController implements DeptClient {
    private static final String mapName="mapName";
    @Autowired
    private DeptService deptService;

    /**
     * 根据ID查询施工单位管理详情.
     *
     * @param id 主键
     * @return 详情
     */
    @Override
    @ApiOperation("获取施工单位管理详情")
    public Response<DeptDTO> get(Integer id) {
        return Response.ok( deptService.getDept( id ) );
    }

    /**
     * 根据条件查询列表，带分页功能.
     *
     * @param deptQueryDTO 查询参数
     * @return 分页/列表
     */
    @Override
    @ApiOperation("获取施工单位管理列表")
    public Response<PageInfo<DeptDTO>> getList(DeptQueryDTO deptQueryDTO) {
        return Response.ok( deptService.queryList( deptQueryDTO ) );
    }

    /**
     * 新增施工单位管理.
     *
     * @param deptDTO 新增数据
     * @return 成功返回数据/失败返回异常
     */
    @Override
    @ApiOperation("新增施工单位管理")
    public Response<DeptDTO> add(DeptDTO deptDTO) {
        return Response.ok( deptService.addDept( deptDTO ) );
    }

    /**
     * 修改施工单位管理.
     *
     * @param deptDTO 修改数据
     * @return 成功返回数据/失败返回异常
     */
    @Override
    @ApiOperation("修改施工单位管理")
    public Response<DeptDTO> update(Integer id, DeptDTO deptDTO) {
        return Response.ok( deptService.updateDept( id, deptDTO ) );
    }

    @GetMapping("/exportExcel")
    @ApiOperation("导出")
    public void export(HttpServletResponse response, HttpServletRequest request) throws IOException {
        deptService.export( response,request );
    }

    @GetMapping( "/add")
    @ResponseBody
    @ApiOperation("redis add")
    public Map<Object, Object> addUser(){
        Map<String, Object> modelMap=new HashMap<String,Object>();
        DeptEntity user=new DeptEntity();
        user.setDeptName("肉包");
        user.setOrgNo("9个月");
        //存放hash值
        modelMap.put("roubao", user.getDeptName());
        modelMap.put("yuefen", user.getOrgNo());
        deptService.setKey(mapName, modelMap);
        //获取map集合
        Map<Object, Object> modelMap1= deptService.getMapValue(mapName);
        Object value= deptService.getValue(mapName, "deptName");
        System.out.println(" value : "+value);
        modelMap1.put("从缓存中根据key取到的value", value);
        return modelMap1;
    }

    @GetMapping( "/delete")
    @ResponseBody
    @ApiOperation("redis delete")
    public Map<Object, Object> deleteUser(){
        //获取即将删除的key值，这里我们做的批量删除
        List<String> keys=new ArrayList<>();
        keys.add("yuefen");
        //开始执行删除操作
        deptService.deleteData(keys);
        //获取map集合
        Map<Object, Object> modelMap1= deptService.getMapValue(mapName);
        Object value= deptService.getValue(mapName, "deptName");
        System.out.println(" value : "+value);
        modelMap1.put("从缓存中根据key取到的value", value);
        return modelMap1;
    }

}