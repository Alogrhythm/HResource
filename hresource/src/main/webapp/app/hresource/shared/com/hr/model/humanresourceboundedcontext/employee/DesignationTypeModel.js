Ext.define('Hresource.hresource.shared.com.hr.model.humanresourceboundedcontext.employee.DesignationTypeModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "designationTypeCode",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "designationTypeDesc",
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