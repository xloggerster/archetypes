Ext.define('Starter.store.Users', {
    extend: 'Ext.data.Store',
    model: 'Starter.model.User',
    autoLoad: true,
    remoteSort: true,
    autoSync : true,
    sorters: [ {
        property: 'lastName',
        direction: 'ASC'
    }]
});