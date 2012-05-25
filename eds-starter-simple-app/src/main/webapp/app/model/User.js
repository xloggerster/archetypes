Ext.define('Starter.model.User', {
	extend: 'Ext.data.Model',

	idgen: 'uuid',

	fields: [ {
		name: 'lastName',
		type: 'string'
	}, {
		name: 'firstName',
		type: 'string'
	}, {
		name: 'email',
		type: 'string'
	}, {
		name: 'department',
		type: 'string'
	} ],

	validations: [ {
		type: 'email',
		field: 'email'
	}, {
		type: 'presence',
		field: 'lastName'
	} ],

	proxy: {
		type: 'direct',
		api: {
			read: storeService.read,
			create: storeService.create,
			update: storeService.update,
			destroy: storeService.destroy
		},
		reader: {
			root: 'records'
		}
	}
});