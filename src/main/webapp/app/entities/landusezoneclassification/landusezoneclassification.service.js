(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Landusezoneclassification', Landusezoneclassification);

    Landusezoneclassification.$inject = ['$resource'];

    function Landusezoneclassification ($resource) {
        var resourceUrl =  'api/landusezoneclassifications/:id';

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
