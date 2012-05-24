Ext.define('Starter.view.StorePanel', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.storepanel',

	title: 'STORE_READ and STORE_MODIFY',
	store: 'Users',

	initComponent: function() {
		var me = this;

		Ext.applyIf(me, {
			columns: [ {
				xtype: 'gridcolumn',
				dataIndex: 'firstName',
				flex: 1,
				text: 'First Name'
			}, {
				xtype: 'gridcolumn',
				dataIndex: 'lastName',
				flex: 1,
				text: 'Last Name'
			}, {
				xtype: 'gridcolumn',
				dataIndex: 'email',
				flex: 1,
				text: 'Email'
			} ]
		});

		me.callParent(arguments);
	}

});