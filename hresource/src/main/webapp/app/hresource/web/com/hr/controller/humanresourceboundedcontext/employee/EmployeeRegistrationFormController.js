Ext.define('Hresource.hresource.web.com.hr.controller.humanresourceboundedcontext.employee.EmployeeRegistrationFormController', {
     extend: 'Hresource.view.fw.frameworkController.FrameworkViewController',
     alias: 'controller.EmployeeRegistrationFormController',
     onComGrpChange: function(me, newValue, oldValue, eOpts) {
          var jsonData = {};
          jsonData.findKey = this.view.down('#combo_ext_11140').getValue();
          var scope = this.getView();
          Ext.Ajax.request({
               url: 'secure/CommunicationType/findByCommGroupId',
               async: true,
               method: 'POST',
               sender: scope,
               jsonData: jsonData,
               me: me,
               success: function(response, scope) {
                    var responseText = Ext.JSON.decode(response.responseText);
                    if (responseText.response.success) {
                         var responseData = responseText.response.data;
                         var combo_ext_11040 = scope.sender.down('#combo_ext_11040');
                         scope.sender.controller.setComboComponentData(responseData, combo_ext_11040, 'commTypeName', 'commType');
                    } else if (!sessionTimeOutFlag) {
                         scope.sender.controller.responseHandler(responseText);
                    }
               },
               failure: function(response, scope) {
                    if (!sessionTimeOutFlag) {
                         var responseText = Ext.JSON.decode(response.responseText);
                         scope.sender.controller.responseHandler(responseText);
                    }
               }
          }, scope);
     },
     onSaveClick: function(me, e, eOpts) {
          var componentArray = [this.view.down('#combo_ext_8978'), this.view.down('#textfield_ext_9067'), this.view.down('#textfield_ext_9158'), this.view.down('#combo_ext_9251'), this.view.down('#combo_ext_9346'), this.view.down('#textfield_ext_9443'), this.view.down('#textfield_ext_9542'), this.view.down('#combo_ext_10038'), this.view.down('#combo_ext_10320'), this.view.down('#combo_ext_10418'), this.view.down('#combo_ext_10519'), this.view.down('#textfield_ext_10621'), this.view.down('#textfield_ext_10130'), this.view.down('#textfield_ext_10224'), this.view.down('#combo_ext_11140'), this.view.down('#combo_ext_11040'), this.view.down('#textfield_ext_11242'), this.view.down('#combo_ext_11854'), this.view.down('#combo_ext_11760'), this.view.down('#textfield_ext_12145'), this.view.down('#combo_ext_12047'), this.view.down('#textfield_ext_12245'), this.view.down('#textfield_ext_12347')];
          var invalidCompoentArray = this.validateComponents(componentArray);
          if (invalidCompoentArray.length > 0) {
               return;
          }
          var jsonData = {};
          jsonData.title = this.view.down('#combo_ext_8978').getValue();
          jsonData.firstName = this.view.down('#textfield_ext_9067').getValue();
          jsonData.lastname = this.view.down('#textfield_ext_9158').getValue();
          jsonData.gender = this.view.down('#combo_ext_9251').getValue();
          jsonData.timezone = this.view.down('#combo_ext_9346').getValue();
          jsonData.phno = this.view.down('#textfield_ext_9443').getValue();
          jsonData.emailid = this.view.down('#textfield_ext_9542').getValue();
          jsonData.addressType = this.view.down('#combo_ext_10038').getValue();
          jsonData.country = this.view.down('#combo_ext_10320').getValue();
          jsonData.state = this.view.down('#combo_ext_10418').getValue();
          jsonData.city = this.view.down('#combo_ext_10519').getValue();
          jsonData.zipcode = this.view.down('#textfield_ext_10621').getValue();
          jsonData.address1 = this.view.down('#textfield_ext_10130').getValue();
          jsonData.address2 = this.view.down('#textfield_ext_10224').getValue();
          jsonData.comGrp = this.view.down('#combo_ext_11140').getValue();
          jsonData.comType = this.view.down('#combo_ext_11040').getValue();
          jsonData.comData = this.view.down('#textfield_ext_11242').getValue();
          jsonData.depttype = this.view.down('#combo_ext_11854').getValue();
          jsonData.desgType = this.view.down('#combo_ext_11760').getValue();
          jsonData.reportofficer = this.view.down('#textfield_ext_12145').getValue();
          jsonData.jobtype = this.view.down('#combo_ext_12047').getValue();
          jsonData.pan = this.view.down('#textfield_ext_12245').getValue();
          jsonData.contactId = this.view.down('#textfield_ext_12347').getValue();
          var scope = this.getView();
          Ext.Ajax.request({
               url: 'secure/EmployeeDSWS/empMethod',
               async: true,
               method: 'POST',
               sender: scope,
               jsonData: jsonData,
               me: me,
               maskEnable: true,
               maskEle: scope,
               success: function(response, scope) {
                    var responseText = Ext.JSON.decode(response.responseText);
                    if (responseText.response.success) {
                         Ext.Msg.alert('Server Response', responseText.response.message);
                    } else if (!sessionTimeOutFlag) {
                         scope.sender.controller.responseHandler(responseText);
                    }
               },
               failure: function(response, scope) {
                    if (!sessionTimeOutFlag) {
                         var responseText = Ext.JSON.decode(response.responseText);
                         scope.sender.controller.responseHandler(responseText);
                    }
               }
          }, scope);
     },
     onStateChange: function(me, newValue, oldValue, eOpts) {
          var jsonData = {};
          jsonData.findKey = this.view.down('#combo_ext_10418').getValue();
          var scope = this.getView();
          Ext.Ajax.request({
               url: 'secure/City/findByStateId',
               async: true,
               method: 'POST',
               sender: scope,
               jsonData: jsonData,
               me: me,
               success: function(response, scope) {
                    var responseText = Ext.JSON.decode(response.responseText);
                    if (responseText.response.success) {
                         var responseData = responseText.response.data;
                         var combo_ext_10519 = scope.sender.down('#combo_ext_10519');
                         scope.sender.controller.setComboComponentData(responseData, combo_ext_10519, 'cityName', 'cityId');
                    } else if (!sessionTimeOutFlag) {
                         scope.sender.controller.responseHandler(responseText);
                    }
               },
               failure: function(response, scope) {
                    if (!sessionTimeOutFlag) {
                         var responseText = Ext.JSON.decode(response.responseText);
                         scope.sender.controller.responseHandler(responseText);
                    }
               }
          }, scope);
     },
     onResetClick: function(me, e, eOpts) {
          this.view.down('#combo_ext_8978').reset();
          this.view.down('#textfield_ext_9067').reset();
          this.view.down('#textfield_ext_9158').reset();
          this.view.down('#combo_ext_9251').reset();
          this.view.down('#combo_ext_9346').reset();
          this.view.down('#textfield_ext_9443').reset();
          this.view.down('#textfield_ext_9542').reset();
          this.view.down('#combo_ext_10038').reset();
          this.view.down('#textfield_ext_10130').reset();
          this.view.down('#textfield_ext_10224').reset();
          this.view.down('#combo_ext_10320').reset();
          this.view.down('#combo_ext_10418').reset();
          this.view.down('#combo_ext_10519').reset();
          this.view.down('#textfield_ext_10621').reset();
          this.view.down('#combo_ext_11140').reset();
          this.view.down('#combo_ext_11040').reset();
          this.view.down('#textfield_ext_11242').reset();
          this.view.down('#combo_ext_11854').reset();
          this.view.down('#combo_ext_11760').reset();
          this.view.down('#combo_ext_12047').reset();
          this.view.down('#textfield_ext_12145').reset();
          this.view.down('#textfield_ext_12245').reset();
          this.view.down('#textfield_ext_12347').reset();
     },
     onCountryChange: function(me, newValue, oldValue, eOpts) {
          var jsonData = {};
          jsonData.findKey = this.view.down('#combo_ext_10320').getValue();
          var scope = this.getView();
          Ext.Ajax.request({
               url: 'secure/State/findByCountryId',
               async: true,
               method: 'POST',
               sender: scope,
               jsonData: jsonData,
               me: me,
               success: function(response, scope) {
                    var responseText = Ext.JSON.decode(response.responseText);
                    if (responseText.response.success) {
                         var responseData = responseText.response.data;
                         var combo_ext_10418 = scope.sender.down('#combo_ext_10418');
                         scope.sender.controller.setComboComponentData(responseData, combo_ext_10418, 'stateName', 'stateId');
                    } else if (!sessionTimeOutFlag) {
                         scope.sender.controller.responseHandler(responseText);
                    }
               },
               failure: function(response, scope) {
                    if (!sessionTimeOutFlag) {
                         var responseText = Ext.JSON.decode(response.responseText);
                         scope.sender.controller.responseHandler(responseText);
                    }
               }
          }, scope);
     }
});