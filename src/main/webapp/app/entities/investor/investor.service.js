(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Investor', Investor);

    Investor.$inject = ['$resource', 'DateUtils'];

    function Investor ($resource, DateUtils) {
        var resourceUrl =  'api/investors/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.createdate = DateUtils.convertDateTimeFromServer(data.createdate);
                        data.updatedate = DateUtils.convertDateTimeFromServer(data.updatedate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
