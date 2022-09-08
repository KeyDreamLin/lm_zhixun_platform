package ${package.Service};

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import ${package.Entity}.${entity};
import ${voPackage}.${entity}Vo;
import ${boPackage}.${entity}Bo;
import java.util.List;

/**
* ${table.comment!} 服务类
* @author ${author}
* @since ${date}
*/
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
    * 查询${title}管理列表信息
    * @method: find${entity}List
    * @result : List<${entity}>
    * 创建人:${author}
    * 创建时间：${date}
    * @version 1.0.0
    * @return
    */
    List<${entity}Bo> find${entity}List() ;

    /**
    * 查询${title}管理列表信息并分页
    * 方法名：find${entity}<br/>
    * 创建人：${author} <br/>
    * 时间：${date}<br/>
    * @param ${voLowerClassName}Vo
    * @return IPage<${entity}Bo><br />
    * @throws <br/>
    * @since 1.0.0<br />
    */
    IPage<${entity}Bo> find${entity}Page(${entity}Vo ${voLowerClassName}Vo);

    /**
    * 保存&修改${title}管理
    * 方法名：saveupdate${entity}<br/>
    * 创建人：${author} <br/>
    * 时间：${date}<br/>
    * @param ${voLowerClassName}
    * @return ${entity}Bo<br />
    * @throws <br/>
    * @since 1.0.0<br />
    */
    ${entity}Bo saveupdate${entity}(${entity} ${voLowerClassName});

    /**
    * 根据Id查询${title}管理明细信息
    * 方法名：get${entity}ById<br/>
    * 创建人：${author} <br/>
    * 时间：${date}<br/>
    * @param id
    * @return ${entity}Bo <br />
    * @throws <br/>
    * @since 1.0.0<br />
    */
    ${entity}Bo get${entity}ById(Long id);

    /**
    * 根据Id删除${title}管理
    * 方法名：delete${entity}ById<br/>
    * 创建人：${author} <br/>
    * 时间：${date}<br/>
    * @param id
    * @return int <br />
    * @throws <br/>
    * @since 1.0.0<br />
    */
    int delete${entity}ById(Long id) ;

    /**
    * 根据${title}管理ids批量删除${title}管理
    * 方法名：delBatch${entity}<br/>
    * 创建人：${author} <br/>
    * 时间：${date}<br/>
    * @param ids
    * @return boolean <br />
    * @throws <br/>
    * @since 1.0.0<br />
    */
    boolean delBatch${entity}(String ids);

}