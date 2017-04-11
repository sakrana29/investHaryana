(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Pendingprojectlist', Pendingprojectlist);

    Pendingprojectlist.$inject = ['$resource'];

    function Pendingprojectlist ($resource) {
        var resourceUrl =  '/api/Projectpending/:parametersPending';

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
