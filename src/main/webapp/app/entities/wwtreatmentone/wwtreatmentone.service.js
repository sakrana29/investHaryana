(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Wwtreatmentone', Wwtreatmentone);

    Wwtreatmentone.$inject = ['$resource'];

    function Wwtreatmentone ($resource) {
        var resourceUrl =  'api/wwtreatmentones/:id';

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
