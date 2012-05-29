Ext.define('Starter.model.PageHit', {
	extend: 'Ext.data.Model',

	idgen: 'uuid',

	fields: [ {
		name: 'month',
		type: 'string'
	}, {
		name: 'hit',
		type: 'int'
	} ]

});