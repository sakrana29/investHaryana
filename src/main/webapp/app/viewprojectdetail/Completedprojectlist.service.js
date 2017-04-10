(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Completedprojectlist', Completedprojectlist);

    Completedprojectlist.$inject = ['$resource'];

    function Completedprojectlist ($resource) {
        var resourceUrl =  '/api/ProjectList/:parametersCompleted';

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
