(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Particular', Particular);

    Particular.$inject = ['$resource'];

    function Particular ($resource) {
        var resourceUrl =  'api/particulars/:id';

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
