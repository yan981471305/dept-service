package demo.ms.dept.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.github.pagehelper.PageInfo;
import demo.ms.dept.api.dto.DeptDTO;
import demo.ms.dept.api.dto.DeptQueryDTO;
import demo.ms.dept.dao.DeptDao;
import demo.ms.dept.entity.DeptEntity;
import demo.ms.dept.exception.BaseException;
import demo.ms.dept.exception.DeptErrorCodeException;
import demo.ms.dept.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * Copyright (c) 2018-present, Sinovatio Corporation.
 * All rights reserved.
 * <p/>Created by yanfeng on 2019-05-16 15:59:01.
 *
 * @author yanfeng
 * @since 2019-05-16 15:59:01
 */
@Slf4j
@Service
public class DeptServiceImpl extends ModelMapper implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Autowired

    private StringRedisTemplate template;

    /**
     * 存储数据或修改数据
     *
     * @param modelMap
     * @param mapName
     */
    public void setKey(String mapName, Map<String, Object> modelMap) {
        HashOperations<String, Object, Object> hps = template.opsForHash();
        hps.putAll(mapName, modelMap);
    }


    /**
     * 获取数据Map
     *
     * @param mapName
     * @return
     */
    public Map<Object, Object> getMapValue(String mapName) {
        HashOperations<String, Object, Object> hps = this.template.opsForHash();
        return hps.entries(mapName);

    }

    /**
     * 获取数据value
     *
     * @param mapName
     * @param hashKey
     * @return
     */
    public Object getValue(String mapName, String hashKey) {
        HashOperations<String, Object, Object> hps = this.template.opsForHash();
        return hps.get(mapName, hashKey);

    }

    /**
     * 批量删除缓存数据
     *
     * @param keys
     */
    public void deleteData(List<String> keys) {
        // 执行批量删除操作时先序列化template
        template.setKeySerializer(new JdkSerializationRedisSerializer());
        template.delete(keys);
    }

    /**
     * 查询 施工单位管理 详情.
     *
     * @param deptId 主键
     * @return 详情
     */
    @Override
    public DeptDTO getDept(Integer deptId) {
        DeptEntity dept = deptDao.selectByPrimaryKey( deptId );
        if (dept == null) {
            throw new BaseException( DeptErrorCodeException.DEPT_STATTUS );
        }
        DeptDTO map = this.map( dept, DeptDTO.class );
        System.out.println( "mao:     " + map );
        return map;
    }


    /**
     * 查询 施工单位管理 列表.
     *
     * @param deptQueryDTO 查询参数
     * @return 分页/列表
     */
    @Override
    public PageInfo<DeptDTO> queryList(DeptQueryDTO deptQueryDTO) {
        // selectByQueryParams 需要自己写实现
        List<DeptDTO> result = deptDao.selectDate( deptQueryDTO ).stream()
                .map( dept -> this.map( dept, DeptDTO.class ) )
                .collect( Collectors.toList() );

        return new PageInfo<>( result );
    }

    /**
     * 新增 施工单位管理 记录.
     *
     * @param deptDTO 新增数据
     * @return 成功返回数据/失败返回异常
     */
    @Override
    public DeptDTO addDept(DeptDTO deptDTO) {
        DeptEntity dept = this.map( deptDTO, DeptEntity.class );
        if (deptDao.insert( dept ) != 1) {
            throw new BaseException();
        }
        return this.map( dept, DeptDTO.class );
    }

    /**
     * 修改 施工单位管理 记录.
     *
     * @param id      主键
     * @param deptDTO 修改数据
     * @return 成功返回数据/失败返回异常
     */
    @Override
    public DeptDTO updateDept(Integer id, DeptDTO deptDTO) {
        DeptEntity dept = this.map( deptDTO, DeptEntity.class );
        //dept.setDeptId(id);
        if (deptDao.updateByPrimaryKey( dept ) != 1) {
            throw new BaseException();
        }
        return this.map( dept, DeptDTO.class );
    }

    @Override
    public void export(HttpServletResponse response, HttpServletRequest request) throws IOException {

        List<DeptEntity> deptList = deptDao.selectAll();
        encodeName( "部门信息", request, response );
        // 使用easyPOI的生成workbook的方法
        Workbook workbook = ExcelExportUtil.exportExcel( new ExportParams( "部门详细信息表", "部门信息" ), DeptEntity.class, deptList );
        // 用流的方式将workbook写到页面
        workbook.write( response.getOutputStream() );
        //   关闭workbook
        workbook.close();

    }

    /**
     * excle导出文件名处理
     *
     * @param fileName
     * @param request
     * @param response
     */
    private void encodeName(String fileName, HttpServletRequest request, HttpServletResponse response) {
        try {
            String agent = request.getHeader( "USER-AGENT" ).toLowerCase();
            response.setContentType( "application/vnd.ms-excel" );
            String codedFileName = java.net.URLEncoder.encode( fileName, "UTF-8" );
            if (agent.contains( "firefox" )) {
                response.setCharacterEncoding( "utf-8" );
                response.setHeader( "content-disposition", "attachment;filename=" + new String( fileName.getBytes(), "ISO8859-1" ) + ".xls" );
            } else {
                response.setHeader( "content-disposition", "attachment;filename=" + codedFileName + ".xls" );
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}

