(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Businessentitys', Businessentitys);

    Businessentitys.$inject = ['$resource'];

    function Businessentitys ($resource) {
        var resourceUrl =  'api/businessentitys/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
