(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Waste_water_naturetype', Waste_water_naturetype);

    Waste_water_naturetype.$inject = ['$resource'];

    function Waste_water_naturetype ($resource) {
        var resourceUrl =  'api/waste-water-naturetypes/:id';

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
