(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Departmentservice', Departmentservice);

    Departmentservice.$inject = ['$resource'];

    function Departmentservice ($resource) {
        var resourceUrl =  'api/departmentservices/:id';

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
