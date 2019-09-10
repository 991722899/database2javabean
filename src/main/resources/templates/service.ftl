<#if data??>
    <#assign className = "${data.table_name?cap_first}Service">
    <#assign voName = "${data.table_name?cap_first}">
    <#assign repositoryName = "${voName}Repository">

package ${packageName};

    @Service
    public class ${className}{
        private  @Autowired
        ${repositoryName} ${repositoryName?uncap_first};

        public ${voName} save(${voName} ${voName?uncap_first}, SysUser sysUser) throws ServiceException, JsonProcessingException {
            addEntitySysUserInfo(${voName?uncap_first},sysUser);
            int result = ${repositoryName?uncap_first}.save(${voName?uncap_first});
            if(result==0){
                throw new ServiceException("添加失败");
            }
            return ${voName?uncap_first};
        }

        public List<${voName}> findByList(${voName} ${voName?uncap_first}){
            return ${repositoryName?uncap_first}.findByModel(${voName?uncap_first});
        }

        public PageResult<${voName}> findByPage(final ${voName} ${voName?uncap_first}){
            Page<${voName}> page = PageHelper.startPage(${voName?uncap_first}.getPage(), ${voName?uncap_first}.getRows())
                .doSelectPage(new ISelect() {
                    @Override
                    public void doSelect() {
                        ${repositoryName?uncap_first}.findByModel(${voName?uncap_first});
                    }
                });
                return new PageResult<${voName}>().from(page);
        }

        public ${voName} edit(${voName} ${voName?uncap_first},SysUser sysUser) throws ServiceException, JsonProcessingException {
            editEntitySysUserInfo(${voName?uncap_first},sysUser);
            int result = ${repositoryName?uncap_first}.edit(${voName?uncap_first});
            if(result==0){
                throw new ServiceException("修改失败");
            }
            return ${voName?uncap_first};
        }

        public ${voName} del(SysVariable sysVariable,SysUser sysUser) throws ServiceException{
            ${voName} db${voName} = new ${voName}();
            editEntitySysUserInfo(db${voName},sysUser);
            db${voName}.setStatus(EnableStatus.disable.getValue());
            db${voName}.setIds(${voName?uncap_first}.getIds());
            int result = ${repositoryName?uncap_first}.edit(db${voName});
            if(result==0){
                throw new ServiceException("删除失败");
            }
            return db${voName};
        }

        public ${voName} findById(String id){
            return ${repositoryName?uncap_first}.findById(id);
        }
    }
</#if>
