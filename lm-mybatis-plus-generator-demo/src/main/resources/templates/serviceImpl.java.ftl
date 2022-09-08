package ${package.ServiceImpl};


import ${superServiceImplClassPackage};
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import ${voPackage}.${entity}Vo;
import ${boPackage}.${entity}Bo;
import com.lm.tool.LmAssert;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
/**
* ${table.comment!} 服务实现类
*
* @author ${author}
* @since ${date}
*/
@Service
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    /**
    * 查询${title}管理列表信息
    * @method: find${entity}List
    * @result : List<${entity}>
    * 创建人:${author}
    * 创建时间：${date}
    * @return List<${entity}Bo>
    */
    public List<${entity}Bo> find${entity}List(){
        // 1：设置查询条件
        LambdaQueryWrapper<${entity}> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 2：查询发布的 0 未发布  1 发布
        lambdaQueryWrapper.eq(${entity}::getStatus, 1);
        lambdaQueryWrapper.eq(${entity}::getIsdelete, 0);
        List<${entity}> list = this.list(lambdaQueryWrapper);
        List<${entity}Bo> list_bo = new ArrayList<>();
            list.forEach(item->{
            ${entity}Bo bo = new ${entity}Bo();
            BeanUtils.copyProperties(item,bo);
            list_bo.add(bo);
        });
        return list_bo;
    }

    /**
    * 查询${title}管理列表信息并分页
    * 方法名：find${entity}<br/>
    * 创建人：${author} <br/>
    * 时间：${date}<br/>
    * @param ${voLowerClassName}Vo
    * @return IPage<${entity}Bo><br />
    */
    @Override
    public IPage<${entity}Bo> find${entity}Page(${entity}Vo ${voLowerClassName}Vo){
        // 先设置分页信息
        Page<${entity}> page = new Page<>(${voLowerClassName}Vo.getPageNo(),${voLowerClassName}Vo.getPageSize());
        // 设置条件查询
        LambdaQueryWrapper<${entity}> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 2：查询发布的 0 未发布  1 发布
        lambdaQueryWrapper.eq(${entity}::getStatus, 1);
        lambdaQueryWrapper.eq(${entity}::getIsdelete, 0);
        lambdaQueryWrapper.and(LmAssert.isNotEmpty(${voLowerClassName}Vo.getKeyword()) , wrapper->{wrapper
            .like(${entity}::getUsername, ${voLowerClassName}Vo.getKeyword())
            .or()
            .like(${entity}::getAccount, ${voLowerClassName}Vo.getKeyword());
        });
        // 根据时间排降序
        lambdaQueryWrapper.orderByDesc(${entity}::getCreateTime);
        // 查询分页返回
        IPage<${entity}> results = this.page(page, lambdaQueryWrapper);
        IPage<${entity}Bo> results_bo = new Page<>();
        ArrayList<${entity}Bo> results_list_bo = new ArrayList<>();
        BeanUtils.copyProperties(results,results_bo,"records");
        results.getRecords().forEach(item->{
        ${entity}Bo ${voLowerClassName}Bo = new ${entity}Bo();
            BeanUtils.copyProperties(item,${voLowerClassName}Bo);
            results_list_bo.add(${voLowerClassName}Bo);
        });
        results_bo.setRecords(results_list_bo);
        return results_bo;
    }

    /**
    * 保存&修改${title}管理
    * 方法名：saveupdate${entity}<br/>
    * 创建人：${author} <br/>
    * 时间：${date}<br/>
    * @param ${voLowerClassName}
    * @return ${entity}Bo<br />
    */
    @Override
    public ${entity}Bo saveupdate${entity}(${entity} ${voLowerClassName}){
        boolean flag = this.saveOrUpdate(${voLowerClassName});
        ${entity}Bo ${voLowerClassName}Bo = new ${entity}Bo();
        BeanUtils.copyProperties(${voLowerClassName},${voLowerClassName}Bo);
        return flag ? ${voLowerClassName}Bo : null;
    }

    /**
    * 根据Id删除${title}管理
    * 方法名：delete${entity}ById<br/>
    * 创建人：${author} <br/>
    * 时间：${date}<br/>
    * @param id
    * @return int <br />
    */
    @Override
    public int delete${entity}ById(Long id){
        boolean b = this.removeById(id);
        return b ? 1 : 0;
    }

    /**
    * 根据Id查询${title}管理明细信息
    * 方法名：get${entity}ById<br/>
    * 创建人：${author} <br/>
    * 时间：${date}<br/>
    * @param id
    * @return ${entity}Bo <br />
    */
    @Override
    public ${entity}Bo get${entity}ById(Long id){
        ${entity} byId = this.getById(id);
        ${entity}Bo ${voLowerClassName}Bo = new ${entity}Bo();
        BeanUtils.copyProperties(byId,${voLowerClassName}Bo);
        return ${voLowerClassName}Bo;
    }

    /**
    * 根据${title}管理ids批量删除${title}管理
    * 方法名：delBatch${entity}<br/>
    * 创建人：${author} <br/>
    * 时间：${date}<br/>
    * @param ids
    * @return boolean <br />
    */
    @Override
    public boolean delBatch${entity}(String ids){
        // 1 : 把数据分割
        String[] strings = ids.split(",");
        // 2: 组装成一个List<${entity}>
        List<${entity}> ${voLowerClassName}List = Arrays.stream(strings).map(idstr -> {
            ${entity} ${voLowerClassName} = new ${entity}();
            ${voLowerClassName}.setId(new Long(idstr));
            ${voLowerClassName}.setIsdelete(1);
            return ${voLowerClassName};
        }).collect(Collectors.toList());
        // 3: 批量删除
        return this.updateBatchById(${voLowerClassName}List);
    }

}
