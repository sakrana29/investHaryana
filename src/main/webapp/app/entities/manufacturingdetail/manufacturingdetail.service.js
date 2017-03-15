(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Manufacturingdetail', Manufacturingdetail);

    Manufacturingdetail.$inject = ['$resource'];

    function Manufacturingdetail ($resource) {
        var resourceUrl =  'api/manufacturingdetails/:id';

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
