(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Wastewaterdetail', Wastewaterdetail);

    Wastewaterdetail.$inject = ['$resource'];

    function Wastewaterdetail ($resource) {
        var resourceUrl =  'api/wastewaterdetails/:id';

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
