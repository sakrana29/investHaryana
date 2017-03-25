(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Wwtreatmentthree', Wwtreatmentthree);

    Wwtreatmentthree.$inject = ['$resource'];

    function Wwtreatmentthree ($resource) {
        var resourceUrl =  'api/wwtreatmentthrees/:id';

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
