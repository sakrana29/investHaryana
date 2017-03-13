(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Projectype', Projectype);

    Projectype.$inject = ['$resource'];

    function Projectype ($resource) {
        var resourceUrl =  'api/projectypes/:id';

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
