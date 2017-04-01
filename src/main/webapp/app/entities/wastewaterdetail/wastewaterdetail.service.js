(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Wastewaterdetail', Wastewaterdetail);

    Wastewaterdetail.$inject = ['$resource', 'DateUtils'];

    function Wastewaterdetail ($resource, DateUtils) {
        var resourceUrl =  'api/wastewaterdetails/:id';

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
