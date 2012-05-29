Ext.define('Starter.controller.User', {
	extend: 'Ext.app.Controller',

	stores: [ 'Users' ],
	models: [ 'User' ],
	views: [ 'StorePanel' ],

	refs: [ {
		ref: 'storePanel',
		selector: 'storepanel'
	}, {
		ref: 'deleteButton',
		selector: 'storepanel button[action=delete]'
	} ],

	init: function() {
		this.control({
			'storepanel': {
				itemclick: this.onItemClick
			},
			'storepanel button[action=delete]': {
				click: this.deleteUser
			},
			'storepanel button[action=new]': {
				click: this.newUser
			},
			'storepanel #filtertextfield': {
				change: this.onChange,
			}
		});
	},

	onChange: function(field, newValue) {
		console.log(newValue);
		var myStore = this.getUsersStore();
		if (newValue) {
		    myStore.remoteFilter = false;
			myStore.clearFilter(true);
			myStore.remoteFilter = true;
			myStore.filter('filter', newValue);
		} else {
			myStore.clearFilter();
		}
	},
	
	onItemClick: function(button, record) {
		this.getDeleteButton().enable();
	},

	deleteUser: function() {
		this.getDeleteButton().disable();
		var sm = this.getStorePanel().getSelectionModel();
		this.getUsersStore().remove(sm.getSelection());
	},

	newUser: function() {
		var newUser = this.getUserModel().create({
			lastName: 'New',
			firstName: 'Person',
			email: 'new@email.com'
		});

		this.getUsersStore().insert(0, newUser);
		this.getStorePanel().getPlugin('storePanelRowEditing').startEdit(0, 0);
	}

});