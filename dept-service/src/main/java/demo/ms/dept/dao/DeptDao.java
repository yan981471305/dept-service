package demo.ms.dept.dao;


import demo.ms.dept.api.dto.DeptQueryDTO;
import demo.ms.dept.entity.DeptEntity;
import demo.ms.dept.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Copyright (c) 2018-present, Sinovatio Corporation.
 * All rights reserved.
 * <p/>Created by yanfeng on 2019-05-16 15:59:01.
 *
 * @author yanfeng
 * @since 2019-05-16 15:59:01
 */
@Mapper
public interface DeptDao extends MyMapper<DeptEntity> {
    List<DeptEntity> selectDate(DeptQueryDTO queryDTO);

    List<DeptEntity> findTree();
}
