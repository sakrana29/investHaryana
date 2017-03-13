(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Approvalforms', Approvalforms);

    Approvalforms.$inject = ['$resource'];

    function Approvalforms ($resource) {
        var resourceUrl =  'api/approvalforms/:id';

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
