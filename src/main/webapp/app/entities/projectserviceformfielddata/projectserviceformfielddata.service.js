(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Projectserviceformfielddata', Projectserviceformfielddata);

    Projectserviceformfielddata.$inject = ['$resource'];

    function Projectserviceformfielddata ($resource) {
        var resourceUrl =  'api/projectserviceformfielddata/:id';

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
