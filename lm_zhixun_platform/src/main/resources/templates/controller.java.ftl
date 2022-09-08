package ${package.Controller};

import com.baomidou.mybatisplus.core.metadata.IPage;
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import ${voPackage}.${entity}Vo;
import ${boPackage}.${entity}Bo;
import com.lm.controller.BaseController;
import com.lm.common.ex.lthrow.ValidatorExceptionThrow;
import com.lm.common.r.UserResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import com.lm.tool.LmAssert;

/**
 * ${title}web Api
 * @author ${author}
 * @since ${date}
 */
@Controller
@Slf4j
public class ${table.controllerName} extends BaseController {
    @Autowired
    private I${entity}Service ${voLowerClassName}Service;

    /**
    * 查询${title}列表信息
    ** @path : /admin/${voLowerClassName}/load
    * @method: find${entity}List
    * @result : List<${entity}>
    * 创建人:${author}
    * 创建时间：${date}
    * @return
    */
    @GetMapping("/${voLowerClassName}/load")
    @ResponseBody
    public List<${entity}Bo> find${voLowerClassName}List() {
        return ${voLowerClassName}Service.find${entity}List();
    }


    /**
    * 查询${title}列表信息并分页
    * @path : /admin/${voLowerClassName}/list
    * @method: find${entity}s
    * @param : ${entity}Vo
    * @result : IPage<${entity}Bo>
    * 创建人:${author}
    * 创建时间：${date}
    * @version 1.0.0
    */
    @GetMapping("/${voLowerClassName}/list")
    @ResponseBody
    public IPage<${entity}Bo> find${entity}s(@RequestBody ${entity}Vo ${voLowerClassName}Vo){
        return ${voLowerClassName}Service.find${entity}Page(${voLowerClassName}Vo);
    }

    /**
    * 保存和修改${title}
    * @method: saveupdate
    * @path : /admin/${voLowerClassName}/save
    * @param : ${entity}
    * @result : ${entity}
    * 创建人:${author}
    * 创建时间：${date}
    * @version 1.0.0
    */
    @PostMapping("/${voLowerClassName}/saveupdate")
    @ResponseBody
    public ${entity}Bo saveupdate${entity}(@RequestBody ${entity} ${voLowerClassName}) {
        return ${voLowerClassName}Service.saveupdate${entity}(${voLowerClassName});
    }

    /**
    * 根据${title}id查询明细信息
    * @method: get/{id}
    * @path : /admin/${voLowerClassName}/get/{id}
    * @param : id
    * @result : ${entity}Bo
    * 创建人:${author}
    * 创建时间：${date}
    * @version 1.0.0
    */
    @GetMapping("/${voLowerClassName}/get/{id}")
    @ResponseBody
    public ${entity}Bo get${entity}ById(@PathVariable("id") String id) {
        if(LmAssert.isEmpty(id)){
            throw new ValidatorExceptionThrow(UserResultEnum.ID_NOT_EMPTY);
        }
        return ${voLowerClassName}Service.get${entity}ById(new Long(id));
    }

    /**
    * 根据${title}id删除${title}
    * @method: delete/{id}
    * @path : /admin/${voLowerClassName}/delete/{id}
    * @param : id
    * @result : int
    * 创建人:${author}
    * 创建时间：${date}
    * @version 1.0.0
    */
    @PostMapping("/${voLowerClassName}/delete/{id}")
    @ResponseBody
    public int delete${entity}ById(@PathVariable("id") String id) {
        if(LmAssert.isEmpty(id)){
            throw new ValidatorExceptionThrow(UserResultEnum.ID_NOT_EMPTY);
        }
        return ${voLowerClassName}Service.delete${entity}ById(new Long(id));
    }

    /**
    * 根据${title}ids批量删除${title}
    * @method: ${voLowerClassName}/delBatch
    * @path : /admin/${voLowerClassName}/delBatch
    * @param : ${entity}Vo
    * @result : boolean
    * 创建人:${author}
    * 创建时间：${date}
    * @version 1.0.0
    */
    @PostMapping("/${voLowerClassName}/delBatch")
    public boolean del${entity}(@RequestBody ${entity}Vo ${voLowerClassName}Vo) {
        log.info("你要批量删除的IDS是:{}", ${voLowerClassName}Vo.getBatchIds());
        if (LmAssert.isEmpty(${voLowerClassName}Vo.getBatchIds())) {
            throw new ValidatorExceptionThrow(UserResultEnum.ID_NOT_EMPTY);
        }
        return ${voLowerClassName}Service.delBatch${entity}(${voLowerClassName}Vo.getBatchIds());
    }
}
