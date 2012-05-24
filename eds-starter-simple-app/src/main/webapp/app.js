Ext.Loader.setConfig({
	enabled: true
});

Ext.require('Ext.direct.*', function() {
	var chartDataPoller = new Ext.direct.PollingProvider({
		id: 'chartDataPoller',
		type: 'polling',
		interval: 5 * 1000, // 5 seconds
		url: Ext.app.POLLING_URLS.chart
	});

	Ext.direct.Manager.addProvider(Ext.app.REMOTING_API, chartDataPoller);
});

Ext.application({
	controllers: [ 'Poll', 'User' ],
	autoCreateViewport: true,
	name: 'Starter'
});
