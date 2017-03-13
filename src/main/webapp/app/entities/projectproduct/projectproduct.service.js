(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Projectproduct', Projectproduct);

    Projectproduct.$inject = ['$resource'];

    function Projectproduct ($resource) {
        var resourceUrl =  'api/projectproducts/:id';

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
