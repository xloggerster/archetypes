Ext.define('Starter.store.Users', {
    extend: 'Ext.data.Store',
    model: 'Starter.model.User',
    autoLoad: true,
    buffered: true,
    pageSize: 25,
    remoteSort: true,
    autoSync : true,
    sorters: [ {
        property: 'lastName',
        direction: 'ASC'
    }]
});