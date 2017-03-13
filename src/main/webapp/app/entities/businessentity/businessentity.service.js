(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Businessentity', Businessentity);

    Businessentity.$inject = ['$resource'];

    function Businessentity ($resource) {
        var resourceUrl =  'api/businessentities/:id';

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
