(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Emmision_pollution_controll', Emmision_pollution_controll);

    Emmision_pollution_controll.$inject = ['$resource'];

    function Emmision_pollution_controll ($resource) {
        var resourceUrl =  'api/emmision-pollution-controlls/:id';

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
