Ext.define('Hresource.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Hresource.view.appreportui.ReportViewController',
	            'Hresource.view.appreportui.datagrid.DataGridPanel',
	            'Hresource.view.appreportui.datagrid.DataGridView',
	            'Hresource.view.appreportui.querycriteria.QueryCriteriaView',
	            'Hresource.view.appreportui.chart.ChartView',
	            'Hresource.view.appreportui.datapoint.DataPointView',
	            'Hresource.view.googlemaps.map.MapPanel',
	            'Hresource.view.appreportui.chartpoint.ChartPointView'
	            ],
	xtype : 'reportView',
	controller : 'reportViewController',
	layout : 'border',
	reportJSON:null,
	bodyStyle:{
        background:'#f6f6f6'
    },
	listeners : {
		scope : 'controller',
		afterrender : 'afterRenderReport',
		boxready : 'fetchReportData',
		added:'onReportAdded'
	}
});
