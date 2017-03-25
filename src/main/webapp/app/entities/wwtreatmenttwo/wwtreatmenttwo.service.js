(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Wwtreatmenttwo', Wwtreatmenttwo);

    Wwtreatmenttwo.$inject = ['$resource'];

    function Wwtreatmenttwo ($resource) {
        var resourceUrl =  'api/wwtreatmenttwos/:id';

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
