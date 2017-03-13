(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Environment_impactdetail', Environment_impactdetail);

    Environment_impactdetail.$inject = ['$resource'];

    function Environment_impactdetail ($resource) {
        var resourceUrl =  'api/environment-impactdetails/:id';

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
