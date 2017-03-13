(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Projectprocessflowsteps', Projectprocessflowsteps);

    Projectprocessflowsteps.$inject = ['$resource'];

    function Projectprocessflowsteps ($resource) {
        var resourceUrl =  'api/projectprocessflowsteps/:id';

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
