(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Modeofdisposalfor_discharge', Modeofdisposalfor_discharge);

    Modeofdisposalfor_discharge.$inject = ['$resource'];

    function Modeofdisposalfor_discharge ($resource) {
        var resourceUrl =  'api/modeofdisposalfor-discharges/:id';

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
