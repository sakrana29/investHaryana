(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Userrole', Userrole);

    Userrole.$inject = ['$resource'];

    function Userrole ($resource) {
        var resourceUrl =  'api/userroles/:id';

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
