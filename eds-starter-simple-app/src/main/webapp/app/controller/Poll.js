Ext.define('Starter.controller.Poll', {
	extend: 'Ext.app.Controller',

	stores: [ 'PageHits' ],
	models: [ 'PageHit' ],
	views: [ 'PollPanel' ],

	// refs: [ {
	// ref: 'startStopButton',
	// selector: 'pollchart button[action=control]'
	// } ],

	init: function() {
		var provider = Ext.direct.Manager.getProvider('chartDataPoller');
		provider.addListener('data', this.onData, this);

		// this.control({
		// 'pollchart': {
		// add: this.onAdd,
		// destroy: this.stopPolling,
		// beforeactivate: this.startPolling,
		// beforedeactivate: this.stopPolling
		// },
		// 'pollchart button[action=control]': {
		// click: this.startOrStop
		// }
		// });
	},

	
	onData: function(provider, event) {
		if (event.data) {
			var store = this.getPageHitsStore(), model = this.getPageHitModel();
			store.removeAll(true);
			
			Ext.each(Ext.Date.monthNames, function(name, ix) {
				store.add(model.create({month: name.substring(0,3), hit: event.data[ix]}));
			});
		}
	},
//
// onAdd: function(cmp) {
// this.provider = Ext.direct.Manager.getProvider('chartdatapoller');
// this.startPolling();
// },
//
// startOrStop: function() {
// if (!this.provider.isConnected()) {
// this.startPolling();
// } else {
// this.stopPolling();
// }
// },
//	
// startPolling: function() {
// var button = this.getStartStopButton();
// if (button) {
// button.setText(i18n.chart_stop);
// button.setIconCls('icon-stop');
// }
//		
// if (!this.provider.isConnected()) {
// this.provider.addListener('data', this.onData, this);
// this.provider.connect();
// }
// },
//	
// stopPolling: function() {
// var button = this.getStartStopButton();
// if (button) {
// button.setText(i18n.chart_start);
// button.setIconCls('icon-start');
// }
//		
// if (this.provider.isConnected()) {
// this.provider.removeListener('data', this.onData);
// this.provider.disconnect();
// }
// }

});
