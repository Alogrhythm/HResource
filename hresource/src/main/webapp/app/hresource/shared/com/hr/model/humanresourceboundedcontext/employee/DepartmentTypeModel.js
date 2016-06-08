Ext.define('Hresource.hresource.shared.com.hr.model.humanresourceboundedcontext.employee.DepartmentTypeModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "departmentTypeCode",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "departmentTypeDesc",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "versionId",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "entityAudit",
          "reference": "EntityAudit"
     }, {
          "name": "primaryDisplay",
          "type": "string",
          "defaultValue": ""
     }]
});