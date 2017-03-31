(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Watersupplysource', Watersupplysource);

    Watersupplysource.$inject = ['$resource'];

    function Watersupplysource ($resource) {
        var resourceUrl =  'api/watersupplysources/:id';

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
