package demo.ms.dept.service;

import com.github.pagehelper.PageInfo;
import demo.ms.dept.api.dto.DeptDTO;
import demo.ms.dept.api.dto.DeptQueryDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
public interface DeptService {


    /**
     * 查询 施工单位管理 详情.
     *
     * @param deptId 主键
     * @return 详情
     */
    DeptDTO getDept(Integer deptId);


    /**
     * 查询 施工单位管理 列表.
     *
     * @param deptQueryDTO 查询参数
     * @return 分页/列表
     */
    PageInfo<DeptDTO> queryList(DeptQueryDTO deptQueryDTO);

    /**
     * 新增 施工单位管理 记录.
     *
     * @param deptDTO 新增数据
     * @return 成功返回数据/失败返回异常
     */
    DeptDTO addDept(DeptDTO deptDTO);

    /**
     * 修改 施工单位管理 记录.
     *
     * @param id      主键
     * @param deptDTO 修改数据
     * @return 成功返回数据/失败返回异常
     */
    DeptDTO updateDept(Integer id, DeptDTO deptDTO);

    void export( HttpServletResponse response, HttpServletRequest request) throws IOException;

    void setKey(String mapName, Map<String, Object> modelMap);

    Map<Object, Object> getMapValue(String mapName);
    Object getValue(String mapName, String hashKey);
    void deleteData(List<String> keys);
}
