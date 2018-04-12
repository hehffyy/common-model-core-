
    drop table M_ActBizOperation cascade constraints;

    drop table M_ActBizRuleOption cascade constraints;

    drop table M_ActFieldOption cascade constraints;

    drop table M_ActFormOption cascade constraints;

    drop table M_ActFormPluginOption cascade constraints;

    drop table M_ActLogicPluginOption cascade constraints;

    drop table M_ActTablePermission cascade constraints;

    drop table M_Biz cascade constraints;

    drop table M_BizLogicPlugin cascade constraints;

    drop table M_BizMaterialGroup cascade constraints;

    drop table M_BizNav cascade constraints;

    drop table M_BizRelation cascade constraints;

    drop table M_BizResourceFile cascade constraints;

    drop table M_BizRule cascade constraints;

    drop table M_BizRuleAction cascade constraints;

    drop table M_BizSelectMaterial cascade constraints;

    drop table M_Catalog cascade constraints;

    drop table M_Codekeydef cascade constraints;

    drop table M_DataBase cascade constraints;

    drop table M_ExtendAttribute cascade constraints;

    drop table M_FieldDef cascade constraints;

    drop table M_FileImportPlugin cascade constraints;

    drop table M_Flow cascade constraints;

    drop table M_FlowAct cascade constraints;

    drop table M_FlowActGroup cascade constraints;

    drop table M_FlowCoopSetp cascade constraints;

    drop table M_FlowCooperation cascade constraints;

    drop table M_Form cascade constraints;

    drop table M_FormLogicPlugin cascade constraints;

    drop table M_IndexDef cascade constraints;

    drop table M_Material cascade constraints;

    drop table M_MaterialGroup cascade constraints;

    drop table M_MaterialNav cascade constraints;

    drop table M_SysDict cascade constraints;

    drop table M_SysDictItem cascade constraints;

    drop table M_TableDef cascade constraints;

    drop table M_TableLogicPlugin cascade constraints;

    drop table M_VssFileSystem cascade constraints;

    create table M_ActBizOperation (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FactGuid varchar2(32 char) not null,
        FOperation varchar2(50 char) not null,
        primary key (fguid),
        unique (FactGuid, FOperation)
    );

    comment on table M_ActBizOperation is
        '环节业务操作';

    create table M_ActBizRuleOption (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FactGuid varchar2(32 char) not null,
        FRuleGuid varchar2(32 char) not null,
        FOrder number(10,0),
        primary key (fguid),
        unique (FactGuid, FRuleGuid)
    );

    comment on table M_ActBizRuleOption is
        '环节业务规则选项';

    create table M_ActFieldOption (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FactGuid varchar2(32 char) not null,
        FTableGuid varchar2(32 char) not null,
        FfieldGuid varchar2(32 char) not null,
        FreadonlyExpr varchar2(1000 char),
        FcalcExpr varchar2(1000 char),
        fvisibleExpr varchar2(1000 char),
        frequiredExpr varchar2(1000 char),
        fconstraintExpr varchar2(1000 char),
        ftipInfo varchar2(100 char),
        Freadonly number(1,0),
        FdefaultValue varchar2(1000 char),
        primary key (fguid),
        unique (FactGuid, FTableGuid, FfieldGuid)
    );

    comment on table M_ActFieldOption is
        '环节字段选项';

    create table M_ActFormOption (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FactGuid varchar2(32 char) not null,
        FformGuid varchar2(32 char) not null,
        FvisibleExpr varchar2(1000 char),
        fdefaultForm number(1,0),
        FMainTable varchar2(32 char),
        FOrder number(10,0),
        FVisibleUIExpr varchar2(1000 char),
        primary key (fguid),
        unique (FactGuid, FformGuid)
    );

    comment on table M_ActFormOption is
        '环节表单选项';

    create table M_ActFormPluginOption (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        fpluginGuid varchar2(32 char) not null,
        FactGuid varchar2(32 char) not null,
        FformGuid varchar2(32 char) not null,
        FenabledExpr varchar2(1000 char),
        FtipInfo varchar2(200 char),
        primary key (fguid),
        unique (fpluginGuid, FactGuid, FformGuid)
    );

    comment on table M_ActFormPluginOption is
        '环节表单选项';

    create table M_ActLogicPluginOption (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FactGuid varchar2(32 char) not null,
        FPluginGuid varchar2(32 char) not null,
        FOrder number(10,0),
        FGroupName varchar2(20 char),
        FvisibleExpr varchar2(1000 char),
        FVisibleUIExpr varchar2(1000 char),
        FautoServiceCronExpr varchar2(50 char),
        FOuterService number(1,0),
        primary key (fguid),
        unique (FactGuid, FPluginGuid)
    );

    comment on table M_ActLogicPluginOption is
        '环节逻辑插件选项';

    comment on column M_ActLogicPluginOption.FOuterService is
        '是否发布外部服务';

    create table M_ActTablePermission (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FactGuid varchar2(32 char) not null,
        FtableGuid varchar2(32 char) not null,
        Fpermission varchar2(30 char),
        FReadOnly number(1,0),
        FReadonlyExpr varchar2(1000 char),
        FinsertCondition varchar2(1000 char),
        FdeleteCondition varchar2(1000 char),
        FFilter varchar2(1000 char),
        FRefreshAfterNew number(1,0),
        FtablePlugins varchar2(1000 char),
        FOrder number(10,0),
        primary key (fguid),
        unique (FactGuid, FtableGuid)
    );

    comment on table M_ActTablePermission is
        '环节表单选项';

    create table M_Biz (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FCatalogGuid varchar2(32 char),
        FName varchar2(100 char) not null,
        FDispName varchar2(100 char) not null,
        FDispOrder number(10,0),
        FBizKind varchar2(20 char),
        FBizType varchar2(20 char),
        FDeleted number(1,0),
        FDatabaseName varchar2(20 char),
        FORMCreated number(1,0),
        FLocked number(1,0),
        primary key (fguid),
        unique (FCatalogGuid, FName)
    );

    comment on table M_Biz is
        '业务表';

    comment on column M_Biz.FBizKind is
        '业务类型';

    comment on column M_Biz.FBizType is
        '业务类型';

    comment on column M_Biz.FDeleted is
        '删除标记';

    create table M_BizLogicPlugin (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FName varchar2(200 char) not null,
        FBizGuid varchar2(32 char) not null,
        FTriggerKind varchar2(30 char),
        FTriggerEvents varchar2(200 char),
        FUIScript blob,
        FSupports varchar2(20 char),
        FDispOrder number(10,0),
        FLogicClass varchar2(100 char),
        FParameter blob,
        FDispName varchar2(100 char) not null,
        FRelBizDatas varchar2(1000 char),
        FDesc varchar2(200 char),
        primary key (fguid),
        unique (FName, FBizGuid)
    );

    create table M_BizMaterialGroup (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FGroupGuid varchar2(32 char),
        FBizGuid varchar2(32 char),
        FCondition varchar2(2000 char),
        FName varchar2(200 char),
        FisSys number(1,0),
        FDispOrder number(10,0),
        primary key (fguid)
    );

    comment on table M_BizMaterialGroup is
        '业务与审批事项的对照关系';

    create table M_BizNav (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FbizGuid varchar2(32 char) not null,
        Flabel varchar2(50 char) not null,
        FdispOrder number(10,0),
        Fforms varchar2(255 char),
        primary key (fguid)
    );

    comment on table M_BizNav is
        '业务表';

    create table M_BizRelation (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FDispOrder number(10,0),
        FBizGuid varchar2(32 char) not null,
        FRelGuid varchar2(32 char) not null,
        FRelType varchar2(32 char) not null,
        primary key (fguid),
        unique (FBizGuid, FRelGuid)
    );

    comment on table M_BizRelation is
        '业务关系表';

    comment on column M_BizRelation.FBizGuid is
        '业务GUID';

    comment on column M_BizRelation.FRelGuid is
        '引用业务GUID';

    comment on column M_BizRelation.FRelType is
        '引用类型';

    create table M_BizResourceFile (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FBizGuid varchar2(32 char) not null,
        ffileName varchar2(255 char) not null,
        ffileKind varchar2(20 char),
        fdispName varchar2(200 char),
        ffileSize number(10,0),
        fcontent blob,
        fmd5 varchar2(1024 char),
        primary key (fguid),
        unique (FBizGuid, ffileName)
    );

    create table M_BizRule (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FRelBizDatas varchar2(1000 char),
        FBizGuid varchar2(32 char) not null,
        FName varchar2(200 char) not null,
        FKind varchar2(20 char),
        FTriggerEvents varchar2(100 char),
        FDataModels blob,
        FVerifyLogic blob,
        FverifyExpr clob,
        FtipExpr clob,
        FOrder number(10,0),
        FSupervise number(1,0),
        primary key (fguid),
        unique (FBizGuid, FName)
    );

    create table M_BizRuleAction (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FRelBizDatas varchar2(1000 char),
        FRuleGuid varchar2(32 char),
        FName varchar2(200 char),
        FCondition varchar2(1000 char),
        FLogicPlugin varchar2(1000 char),
        FPluginConfigure blob,
        FOrder number(10,0),
        primary key (fguid)
    );

    create table M_BizSelectMaterial (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FRequired number(1,0),
        FOriginalRequired number(1,0),
        FMedium varchar2(32 char),
        FGroupGuid varchar2(32 char),
        FMaterialGuid varchar2(32 char),
        FIsDefSelect number(1,0),
        FMtNums number(10,0),
        FMtOrder number(10,0),
        primary key (fguid)
    );

    comment on table M_BizSelectMaterial is
        '事项与材料对照关系';

    create table M_Catalog (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FParentGuid varchar2(32 char),
        FName varchar2(100 char) not null,
        FDispName varchar2(100 char) not null,
        FDispOrder number(10,0),
        FKind varchar2(255 char),
        FPath varchar2(1000 char),
        FPathName varchar2(2000 char),
        FLastModifyTime timestamp,
        FSyncTime timestamp,
        primary key (fguid),
        unique (FParentGuid, FName)
    );

    create table M_Codekeydef (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        Fname varchar2(32 char),
        Fdescription varchar2(100 char),
        Fparentid varchar2(32 char),
        Fformat blob,
        Fproperty number(10,0),
        Fcreatekind varchar2(32 char),
        Forder number(10,0),
        primary key (fguid)
    );

    comment on table M_Codekeydef is
        '通用编码定义';

    create table M_DataBase (
        FName varchar2(20 char) not null,
        FVerNum number(10,0) not null,
        FDesc varchar2(200 char),
        FType varchar2(20 char),
        FJdbcUrl varchar2(100 char),
        FJdbcClass varchar2(100 char),
        FUserName varchar2(30 char),
        FPassword varchar2(30 char),
        FCatalog varchar2(30 char),
        FSchema varchar2(30 char),
        FIsSystem number(1,0),
        FIsDefaultDatabase number(1,0),
        primary key (FName)
    );

    create table M_ExtendAttribute (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FModel varchar2(50 char) not null,
        FKey varchar2(50 char) not null,
        FValue varchar2(1000 char),
        primary key (fguid),
        unique (FModel, FKey)
    );

    create table M_FieldDef (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FTableGuid varchar2(32 char) not null,
        FName varchar2(30 char) not null,
        FdispName varchar2(60 char) not null,
        FDataType varchar2(10 char) not null,
        FLength number(10,0),
        FScale number(10,0),
        FAutoFillDef blob,
        FIsPrimary number(1,0),
        FUnique number(1,0),
        FIsSysField number(1,0),
        FX5RelationAlias number(10,0),
        FNotNull number(1,0),
        FFieldKind varchar2(20 char) not null,
        FProperties blob,
        FDispOrder number(10,0),
        FrefField varchar2(32 char),
        FLogicFieldKind varchar2(20 char),
        FLogicFieldConfigure blob,
        FEditStyle varchar2(20 char),
        FLookupDef blob,
        FReadonlyExpr varchar2(1000 char),
        FVisibleExpr varchar2(1000 char),
        FRequiredExpr varchar2(1000 char),
        FConstraintExpr varchar2(1000 char),
        FConstraintTip varchar2(100 char),
        FtaskField number(1,0),
        FqueryField number(1,0),
        FDataFormatRegex varchar2(20 char),
        primary key (fguid),
        unique (FTableGuid, FName)
    );

    comment on column M_FieldDef.FTableGuid is
        '所属表';

    comment on column M_FieldDef.FName is
        '字段名';

    create table M_FileImportPlugin (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FRelBizDatas varchar2(1000 char),
        FBizGuid varchar2(32 char) not null,
        FName varchar2(200 char) not null,
        FFileType varchar2(50 char) not null,
        FImportType varchar2(50 char) not null,
        FDataModels blob,
        FMappingLogic blob,
        FAsync number(1,0),
        primary key (fguid),
        unique (FBizGuid, FName)
    );

    create table M_Flow (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FbizGuid varchar2(32 char) not null,
        Fmodel blob,
        Fdiagram blob,
        FName varchar2(100 char) not null,
        FDispName varchar2(60 char) not null,
        FfuncTemplet varchar2(255 char) not null,
        FfuncTemplet2 varchar2(255 char),
        FLimitDays number(19,2),
        FdayConutKind varchar2(10 char),
        FRecTitleExpr varchar2(1000 char),
        FLimitDateExpr varchar2(1000 char),
        FLimitStartDateExpr varchar2(1000 char),
        FlimitEffectActivity varchar2(100 char),
        FflowViewActivity varchar2(20 char),
        fdisabled number(1,0),
        fCoopControl number(1,0),
        FfinishKind varchar2(20 char),
        FmaterialTemplate varchar2(20 char),
        fsilenceFinish number(1,0),
        fOldExecutorDialog number(1,0),
        FDispOrder number(10,0),
        FLastModifyTime timestamp,
        FSyncTime timestamp,
        primary key (fguid),
        unique (FbizGuid, FName)
    );

    comment on column M_Flow.FRecTitleExpr is
        '案卷标题';

    comment on column M_Flow.FLimitStartDateExpr is
        '限办开始日期表达式';

    comment on column M_Flow.FlimitEffectActivity is
        '限办生效环节(如果为空启动后立即生效，否则制定环节批转后生效)';

    comment on column M_Flow.FflowViewActivity is
        '案卷浏览环节';

    comment on column M_Flow.FfinishKind is
        '默认办结类型';

    comment on column M_Flow.FmaterialTemplate is
        '要件模板';

    create table M_FlowAct (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FuploadField varchar2(50 char),
        FName varchar2(100 char) not null,
        FflowGuid varchar2(32 char) not null,
        FDispName varchar2(60 char) not null,
        FLimitDays number(10,1),
        FdayConutKind varchar2(10 char),
        FLimitDateExpr varchar2(1000 char),
        Fproperties blob,
        FflowActKind varchar2(20 char),
        FflowEntry number(1,0),
        FfuncTemplet varchar2(255 char),
        FfuncTemplet2 varchar2(255 char),
        FMainTable varchar2(32 char),
        FcodeFields varchar2(200 char),
        FflowCoopGuid varchar2(32 char),
        FVisible number(1,0),
        FisCoopReceiver number(1,0),
        FforbidWithdraw number(1,0),
        FmaterialAuth varchar2(30 char),
        FmobileEnable number(1,0),
        primary key (fguid),
        unique (FName, FflowGuid)
    );

    create table M_FlowActGroup (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FName varchar2(100 char) not null,
        FflowGuid varchar2(32 char) not null,
        FincludeActGuids varchar2(1000 char),
        FLimitDays number(10,1),
        FdayConutKind varchar2(10 char),
        FLimitDateExpr varchar2(1000 char),
        primary key (fguid),
        unique (FName, FflowGuid)
    );

    create table M_FlowCoopSetp (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FName varchar2(100 char) not null,
        FDispName varchar2(60 char) not null,
        FfromFlow varchar2(32 char) not null,
        FfromAct varchar2(32 char) not null,
        FWaitAct varchar2(32 char),
        FtoFlow varchar2(32 char) not null,
        FtoAct varchar2(32 char) not null,
        FallowRepeat number(1,0),
        fsync number(1,0),
        FInfoPacket blob,
        primary key (fguid)
    );

    create table M_FlowCooperation (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FName varchar2(100 char) not null,
        FDispName varchar2(60 char) not null,
        Fmodel blob,
        Fdiagram blob,
        primary key (fguid)
    );

    create table M_Form (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FBizGuid varchar2(32 char) not null,
        FnavGuid varchar2(32 char),
        FName varchar2(32 char) not null,
        FDispName varchar2(50 char),
        FKind varchar2(10 char),
        FContent blob,
        FModelStruct blob,
        FUrl varchar2(500 char),
        FisFragment number(1,0),
        FDispOrder number(10,0),
        FLastModifyTime timestamp,
        FSyncTime timestamp,
        primary key (fguid),
        unique (FBizGuid, FName)
    );

    create table M_FormLogicPlugin (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FformGuid varchar2(32 char),
        FelementId varchar2(255 char),
        FLogicClass varchar2(100 char),
        FParameter blob,
        FDispName varchar2(100 char) not null,
        FRelBizDatas varchar2(1000 char),
        FDesc varchar2(200 char),
        primary key (fguid)
    );

    create table M_IndexDef (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        Fname varchar2(30 char) not null unique,
        FtableGuid varchar2(32 char) not null,
        FfieldNames varchar2(200 char) not null,
        FisUnique number(10,0),
        primary key (fguid)
    );

    comment on table M_IndexDef is
        '索引定义';

    create table M_Material (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FgroupGuid varchar2(32 char),
        FmaterialName varchar2(200 char),
        Frequired number(1,0),
        ForiginalRequired number(1,0),
        FDispOrder number(10,0),
        primary key (fguid)
    );

    comment on table M_Material is
        '审批材料';

    create table M_MaterialGroup (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FParentGuid varchar2(32 char),
        FgroupName varchar2(200 char),
        FGroupKind varchar2(20 char) not null,
        FgroupCode varchar2(50 char),
        FApproveCode varchar2(50 char),
        FSubApproveCode varchar2(50 char),
        FLimitDays number(10,0),
        FbelongDeptId varchar2(64 char),
        FbelongDeptName varchar2(200 char),
        FApproveKind varchar2(2 char),
        FapproveLevel varchar2(2 char),
        FInvest varchar2(2 char),
        FDispOrder number(10,0),
        FPath varchar2(2000 char),
        primary key (fguid)
    );

    comment on table M_MaterialGroup is
        '材料分组表';

    create table M_MaterialNav (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FbizGuid varchar2(32 char) not null,
        Flabel varchar2(50 char) not null,
        FdispOrder number(10,0),
        Fmaterials varchar2(255 char),
        primary key (fguid)
    );

    comment on table M_MaterialNav is
        '业务表';

    create table M_SysDict (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FName varchar2(100 char) not null,
        primary key (fguid)
    );

    comment on table M_SysDict is
        '系统字典表';

    create table M_SysDictItem (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FType varchar2(50 char) not null,
        Fcode varchar2(20 char) not null,
        Fname varchar2(100 char),
        FDispOrder number(10,0),
        primary key (fguid),
        unique (FType, Fcode)
    );

    comment on table M_SysDictItem is
        '系统字典表';

    create table M_TableDef (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FName varchar2(30 char) not null,
        FRefTable varchar2(32 char),
        FDispName varchar2(60 char) not null,
        FbizGuid varchar2(32 char) not null,
        FDispOrder number(10,0),
        FOrderBy varchar2(200 char),
        Fcondition varchar2(1000 char),
        FMasterTable varchar2(32 char),
        FForeignKeys varchar2(30 char),
        Fcascade varchar2(30 char),
        Ftemplate varchar2(32 char),
        fKind varchar2(20 char),
        FIsview number(1,0),
        FstructChanged number(1,0),
        Fproperties blob,
        Fdeleted number(1,0),
        FLastModifyTime timestamp,
        FSyncTime timestamp,
        primary key (fguid)
    );

    comment on column M_TableDef.FRefTable is
        '记录引用的工作表GUID';

    comment on column M_TableDef.FMasterTable is
        '主表Guid';

    comment on column M_TableDef.FForeignKeys is
        '对应于主表主键的外键字段';

    comment on column M_TableDef.Fcascade is
        '明细表级联定义';

    comment on column M_TableDef.Ftemplate is
        '模板';

    comment on column M_TableDef.fKind is
        '表类型';

    create table M_TableLogicPlugin (
        fguid varchar2(32 char) not null,
        FVerNum number(10,0) not null,
        FEvent varchar2(20 char) not null,
        FTableGuid varchar2(32 char) not null,
        FName varchar2(100 char) not null,
        FLogicClass varchar2(100 char),
        FParameter blob,
        FDispName varchar2(100 char) not null,
        FRelBizDatas varchar2(1000 char),
        FDesc varchar2(200 char),
        primary key (fguid),
        unique (FTableGuid, FName)
    );

    create table M_VssFileSystem (
        FGUID varchar2(32 char) not null,
        FModelGuid varchar2(32 char),
        FModelKind varchar2(20 char),
        FCreateTime timestamp,
        FContent blob,
        primary key (FGUID)
    );

    create index M_BizRuleAction_Rule on M_BizRuleAction (FRuleGuid);

    create index M_BizSelectMaterial_GroupGuid on M_BizSelectMaterial (FGroupGuid);

    create index M_Form_NavGuid on M_Form (FnavGuid);

    create index M_FormLogicPlugin_Form on M_FormLogicPlugin (FformGuid);

    create index M_IndexDef_Table on M_IndexDef (FtableGuid);

    create index M_Material_Group on M_Material (FgroupGuid);

    create index M_MaterialGroup_PID on M_MaterialGroup (FParentGuid);

    create index M_MaterialNav_Biz on M_MaterialNav (FbizGuid);

    create index M_TableDef_Biz on M_TableDef (FbizGuid);

    create index M_VssFileSystem_Model on M_VssFileSystem (FModelGuid);
