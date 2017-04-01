(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('DepartmentStats', DepartmentStats);

    DepartmentStats.$inject = ['$resource'];

    function DepartmentStats ($resource) {
        var resourceUrl =  'api/departmentstatscollection/:id';

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
