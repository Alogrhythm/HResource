Ext.define('Hresource.view.databrowsercalendar.DBCalendar', {
	extend : 'Hresource.view.databrowsercalendar.DBCalendarView',
	requires : [ 'Hresource.view.databrowsercalendar.DBCalendarController',
	             'Hresource.view.databrowsercalendar.DBCalendarView','Ext.layout.container.Card',
	             'Ext.calendar.view.Day', 'Ext.calendar.view.Week',
	             'Ext.calendar.view.Month',
	             'Ext.calendar.form.EventDetails',
	             'Ext.calendar.data.EventMappings'],
	controller : 'databrowsercalendar',
	items : [],
	/*listeners : {
		afterrender : 'loadData',
		scope : "controller"
	}*/
});
