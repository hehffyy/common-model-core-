1.修改了xdoclet的2个hibernate配置文件hibernate-column.xdt、hibernate.xdt，增加对备注和索引的支持
使用xdoclet-hibernate-module-1.2.3.jar覆盖myEclipse的同名文件即可

2.项目属性中——MyEclipse——XDoclet，增加标准HibernateDoclet，并修改Hibernate属性为3.0

3.项目右键——MyEclipse——Run XDoclet 可生成hibernate.mapping文件

4.运行com.alltest.HibernateSchemaExport 可生成物理表


hibernate演示

1)索引示例
@hibernate.property
@hibernate.column name="F2" length="32" not-null="true" index="M_TableDef_0"

@hibernate.property 
@hibernate.column name="F1" length="32" not-null="true" index="M_TableDef_0,M_TableDef_1"

2)备注示例
@hibernate.class comment=""

@hibernate.property
@hibernate.column comment=""

3)一对多List
@hibernate.list inverse="true" cascade="all"
@hibernate.collection-key column="FCatalogGuid"
@hibernate.collection-index column="FDispOrder"
@hibernate.collection-one-to-many class="com.butone.model.TableDef" 

4)多对一

@hibernate.many-to-one cascade="none" class="com.butone.model.TableDef" not-found="ignore"
@hibernate.column name="FTableGuid" index="M_Concept_Table" comment="概念主表定义"

注意：索引必须声明在@hibernate.column上






