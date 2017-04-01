(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Projectdetail', Projectdetail);

    Projectdetail.$inject = ['$resource', 'DateUtils'];

    function Projectdetail ($resource, DateUtils) {
        var resourceUrl =  'api/projectdetails/:id';

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
