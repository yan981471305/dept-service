package demo.ms.dept.api;

import com.github.pagehelper.PageInfo;
import demo.ms.dept.api.dto.DeptDTO;
import demo.ms.dept.api.dto.DeptQueryDTO;
import demo.ms.dept.config.Response;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * Copyright (c) 2018-present, Sinovatio Corporation.
 * All rights reserved.
 * <p/>Created by yanfeng on 2019-05-16 15:59:01.
 *
 * @author yanfeng
 * @since 2019-05-16 15:59:01
 */
@FeignClient(value = "dept-service",path = "/api/v1/dept")
@Api(value = "施工单位管理接口", description = "施工单位管理相关服务")
public interface DeptClient {

    /**
     * 查询施工单位管理详情.
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("/view/{id}")
    Response<DeptDTO> get(@PathVariable(value = "id") Integer id);

    /**
     * 查询施工单位管理列表/分页.
     *
     * @param deptQueryDTO 查询参数
     * @return 分页/列表
     */
    @GetMapping("/")
    Response<PageInfo<DeptDTO>> getList(DeptQueryDTO deptQueryDTO);

    /**
     * 新增施工单位管理.
     *
     * @param deptDTO 新增数据
     * @return 成功返回数据/失败返回异常
     */
    @PostMapping("/insert")
    Response<DeptDTO> add(@RequestBody DeptDTO deptDTO);

    /**
     * 修改施工单位管理.
     *
     * @param deptDTO 修改数据
     * @return 成功返回数据/失败返回异常
     */
    @PutMapping("/update/{id}")
    Response<DeptDTO> update(@PathVariable(value = "id") Integer id,
                                     @RequestBody DeptDTO deptDTO);

}
