(function() {
    'use strict';
    angular
        .module('investhryApp')
        .factory('Electricrequirement', Electricrequirement);

    Electricrequirement.$inject = ['$resource', 'DateUtils'];

    function Electricrequirement ($resource, DateUtils) {
        var resourceUrl =  'api/electricrequirements/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.temp_load_demand_date = DateUtils.convertDateTimeFromServer(data.temp_load_demand_date);
                        data.regular_load_demand_date = DateUtils.convertDateTimeFromServer(data.regular_load_demand_date);
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
