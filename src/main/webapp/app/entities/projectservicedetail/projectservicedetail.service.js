(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Projectservicedetail', Projectservicedetail);

    Projectservicedetail.$inject = ['$resource', 'DateUtils'];

    function Projectservicedetail ($resource, DateUtils) {
        var resourceUrl =  'api/projectservicedetails/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.markrequiredondate = DateUtils.convertDateTimeFromServer(data.markrequiredondate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
