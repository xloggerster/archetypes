Ext.define('Starter.controller.User', {
	extend: 'Ext.app.Controller',

	stores: [ 'Users' ],
	models: [ 'User' ],
	views: [ 'StorePanel' ]

});