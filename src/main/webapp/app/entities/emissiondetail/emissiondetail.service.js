(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Emissiondetail', Emissiondetail);

    Emissiondetail.$inject = ['$resource', 'DateUtils'];

    function Emissiondetail ($resource, DateUtils) {
        var resourceUrl =  'api/emissiondetails/:id';

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
