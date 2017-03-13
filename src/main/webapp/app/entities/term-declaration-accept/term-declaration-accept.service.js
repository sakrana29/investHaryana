(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Term_declaration_accept', Term_declaration_accept);

    Term_declaration_accept.$inject = ['$resource', 'DateUtils'];

    function Term_declaration_accept ($resource, DateUtils) {
        var resourceUrl =  'api/term-declaration-accepts/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.applicationdate = DateUtils.convertDateTimeFromServer(data.applicationdate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
