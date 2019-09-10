<#if data??>
    <#assign className = "${data.table_name?cap_first}Controller">
    <#assign voName = "${data.table_name?cap_first}">
    <#assign serviceName = "${voName}Service">

package ${packageName};

    @Controller
    @RequestMapping("/${data.table_name?uncap_first}")
    @ResponseBody
    public class ${className}{
        private @Autowired
        ${serviceName} ${serviceName?uncap_first};

        @RequestMapping("/save")
        public ResultModel save(HttpServletRequest request, ${voName} ${voName?uncap_first}) throws ServiceException, Exception{
            return ResultModel.success(${serviceName?uncap_first}.save(${voName?uncap_first},getUser(request)));
        }

        @RequestMapping("/edit")
        public ResultModel edit(HttpServletRequest request, ${voName} ${voName?uncap_first}) throws ServiceException, Exception{
            return ResultModel.success(${serviceName?uncap_first}.edit(${voName?uncap_first},getUser(request)));
        }

        @RequestMapping("/del")
        public ResultModel del(HttpServletRequest request,${voName} ${voName?uncap_first}) throws ServiceException, Exception{
            return ResultModel.success(${serviceName?uncap_first}.del(sysVariable,getUser(request)));
        }

        @RequestMapping("/findByPage")
        public ResultModel findByPage(${voName} ${voName?uncap_first}) throws ServiceException{
            return ResultModel.success(${serviceName?uncap_first}.findByPage(sysVariable));
        }

        @RequestMapping("/findById")
        public ResultModel findById(String id){
            return ResultModel.success(${serviceName?uncap_first}.findById(id));
        }
    }
</#if>
