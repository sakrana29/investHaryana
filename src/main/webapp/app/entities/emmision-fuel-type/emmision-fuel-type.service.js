(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Emmision_fuel_type', Emmision_fuel_type);

    Emmision_fuel_type.$inject = ['$resource'];

    function Emmision_fuel_type ($resource) {
        var resourceUrl =  'api/emmision-fuel-types/:id';

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
