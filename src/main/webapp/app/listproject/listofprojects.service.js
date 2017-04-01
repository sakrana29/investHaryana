(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Projectlist', Projectlist);

    Projectlist.$inject = ['$resource'];

    function Projectlist ($resource) {
        var resourceUrl =  '/api/ProjectList/:id';


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
