package ${boPackage};

<#list table.importPackages as pkg>
import ${pkg};
</#list>
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* ${entity}Bo参数类
* 创建人:${author}<br/>
* 时间：${date} <br/>
*
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ${entity}Bo implements java.io.Serializable  {
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    // ${field.comment}
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->
}