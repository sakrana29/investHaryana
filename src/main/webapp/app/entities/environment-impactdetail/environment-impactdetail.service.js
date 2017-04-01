(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Environment_impactdetail', Environment_impactdetail);

    Environment_impactdetail.$inject = ['$resource', 'DateUtils'];

    function Environment_impactdetail ($resource, DateUtils) {
        var resourceUrl =  'api/environment-impactdetails/:id';

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
