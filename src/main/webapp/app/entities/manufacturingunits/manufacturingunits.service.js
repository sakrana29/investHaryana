(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Manufacturingunits', Manufacturingunits);

    Manufacturingunits.$inject = ['$resource'];

    function Manufacturingunits ($resource) {
        var resourceUrl =  'api/manufacturingunits/:id';

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
