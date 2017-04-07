(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('assignServiceDialog', assignServiceDialog);

    assignServiceDialog.$inject = ['$resource'];

    function assignServiceDialog ($resource) {
        var resourceUrl =  'api/countries/:id';

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
