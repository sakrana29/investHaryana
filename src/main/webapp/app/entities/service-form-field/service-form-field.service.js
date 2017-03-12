(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('ServiceFormField', ServiceFormField);

    ServiceFormField.$inject = ['$resource'];

    function ServiceFormField ($resource) {
        var resourceUrl =  'api/service-form-fields/:id';

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
