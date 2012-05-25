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
		directFn: storeService.read,
		reader: {
			root: 'records'
		}
	}
});