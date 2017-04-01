(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Regular_electrict_load_type', Regular_electrict_load_type);

    Regular_electrict_load_type.$inject = ['$resource'];

    function Regular_electrict_load_type ($resource) {
        var resourceUrl =  'api/regular-electrict-load-types/:id';

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
